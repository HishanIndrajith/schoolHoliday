package it.codegen.crawler;

import it.codegen.crawler.models.CrawlResult;
import it.codegen.crawler.models.SchoolHolidayEdarabia;
import it.codegen.crawler.models.SchoolHolidayEurope;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class Crawler
{
	// Autowiring the csvTool instance
	@Autowired
	private CSVTool csvTool;

	public void crawlAndSaveEdarabiaData(){
		// The website is https://www.edarabia.com/
		String initialURL = "https://www.edarabia.com/school-holidays-uae/";

		try
		{
			Document doc = Jsoup.connect( initialURL ).get();
			Elements linkElements = doc.select( "ul.double-col li" );
			for ( Element link : linkElements )
			{
				Element childElement = link.child( 0 );
				String url = childElement.absUrl( "href" );
				CrawlResult result = this.getSchoolHolidaysFromEdarabiaURL(url);
				csvTool.writeToCSVEdarabia(result);
			}
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
	}

	public void crawlAndSaveSchoolHolidaysEuropeData(){
		// The website is https://www.schoolholidayseurope.eu
		String initialURL = "https://www.schoolholidayseurope.eu/school-holidays-scotland/";
		String initialTitle = "";

		try
		{
			Document doc = Jsoup.connect( initialURL ).get();
			initialTitle = doc.title();
			Elements linkElements = doc.select( "#menu-item-80 .sub-menu li .sub-menu li a[href]" );
			for ( Element linkElement : linkElements )
			{
				String url = linkElement.absUrl( "href" );
				CrawlResult result = this.getSchoolHolidaysFromSchoolholidaysEuropeURL(url);
				csvTool.writeToCSVEurope( result);
			}
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}


	}

	private CrawlResult getSchoolHolidaysFromEdarabiaURL(String url){
		List<SchoolHolidayEdarabia> schoolHolidaysEdarabia = new ArrayList<>(  );
		CrawlResult crawlResult = new CrawlResult( );
		try
		{
			Document doc = Jsoup.connect(url).get();
			Application.log.info(doc.title());
			Application.log.info("===============");
			Elements allTables = doc.select(".news-post .table-responsive table");
			for ( Element table : allTables) {
				String tableHeading="All";
				int tableBodyIndex=0;
				if(table.children().size()==2){
					tableHeading = table.child( 0 ).child( 0 ).text();
					tableBodyIndex=1;
				}
				Application.log.info("{}",tableHeading);
				Element tBody = table.child( tableBodyIndex );
				Elements tBodyRows = tBody.children();
				for(Element row : tBodyRows){
					if(row.children().size()==2){
						String date = row.child( 0 ).text();
						String description = row.child( 1 ).text();
						schoolHolidaysEdarabia.add( new SchoolHolidayEdarabia(tableHeading,date,description) );
					}
				}
			}
			crawlResult.setTitle( doc.title());
			crawlResult.setSchoolHolidayListEdarabia( schoolHolidaysEdarabia );
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
		return crawlResult;
	}

	private CrawlResult getSchoolHolidaysFromSchoolholidaysEuropeURL(String url){
		List<SchoolHolidayEurope> schoolHolidays = new ArrayList<>(  );
		CrawlResult crawlResult = new CrawlResult( );
		try
		{
			Document doc = Jsoup.connect(url).get();
			Application.log.info(doc.title());
			Application.log.info("===============");
			Elements allTables = doc.select(".zebra");
			for ( Element table : allTables) {
				Element tHead = table.select( "thead" ).first();
				Elements columnNames = tHead.child( 0 ).children();
				int indexHolidayName=0;
				int indexStartDat = 0;
				int indexEnddate = 0;
				boolean isRegionAvailable = false;
				int indexRegion = 0;
				for(int i = 0 ;i<columnNames.size();i++)
				{
					Element column = columnNames.get( i );
					if ( column.select( "strong" ).text().equals( "School Holiday" ) )
					{
						indexHolidayName = i;
					}
					if ( column.select( "strong" ).text().equals( "Start date" ) )
					{
						indexStartDat = i;
					}
					if ( column.select( "strong" ).text().equals( "End date" ) )
					{
						indexEnddate = i;
					}
					if ( column.select( "strong" ).text().equals( "Region" ) )
					{
						isRegionAvailable = true;
						indexRegion = i;
					}
				}
				if(indexHolidayName+indexStartDat+indexEnddate !=0){
					// Valid table of school holidays
					Elements tBodyRows = table.select( "tbody" ).first().children();
					for(Element row : tBodyRows){
						if(row.children().size()>1){
						// Data rows without warning rows
							Elements cells = row.children();
							String holidayType = cells.get( indexHolidayName ).text();
							String startDate = cells.get( indexStartDat ).text();
							String endDate = cells.get( indexEnddate ).text();
							String region = "All";
							if(isRegionAvailable){
								region = cells.get( indexRegion ).text();
							}

							SchoolHolidayEurope schoolHoliday = new SchoolHolidayEurope( holidayType, region , startDate,endDate);
							schoolHolidays.add( schoolHoliday );
						}
					}

				}
			}

			crawlResult.setTitle( doc.title());
			crawlResult.setSchoolHolidayListEurope( schoolHolidays);
		}
		catch ( IOException e )
		{
			Application.log.error( e.getMessage() );
		}
		return crawlResult;
	}

}
