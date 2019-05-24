package it.codegen.crawler;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import it.codegen.crawler.models.CrawlResult;
import it.codegen.crawler.models.SchoolHolidayEdarabia;
import it.codegen.crawler.models.SchoolHolidayEurope;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

@Component
public class CSVTool
{
	public void writeToCSVEdarabia( CrawlResult result){
		List<SchoolHolidayEdarabia> schoolHolidaysEdarabia = result.getSchoolHolidayListEdarabia();
		String title = result.getTitle();
		String folderName = "EdarabiaResults";
		new File(folderName).mkdir();
		// name of generated csv
		final String CSV_LOCATION = folderName+"/"+title+".csv ";
		try (FileWriter writer = new FileWriter(CSV_LOCATION);) {
			// Create Mapping Strategy to arrange the
			// column name in order
			ColumnPositionMappingStrategy mappingStrategy=
					new ColumnPositionMappingStrategy();
			mappingStrategy.setType( SchoolHolidayEdarabia.class);

			// Arrange column name as provided in below array.
			String[] columns = new String[]
					{ "schoolTypeName", "date", "description"};
			mappingStrategy.setColumnMapping(columns);

			// Createing StatefulBeanToCsv object
			StatefulBeanToCsvBuilder<SchoolHolidayEdarabia> builder=
					new StatefulBeanToCsvBuilder(writer);
			StatefulBeanToCsv beanWriter =
					builder.withMappingStrategy(mappingStrategy).build();

			// Write list to StatefulBeanToCsv object
			beanWriter.write(new SchoolHolidayEdarabia( "School type","Date","Description" ));
			beanWriter.write( schoolHolidaysEdarabia );

		} catch (Exception e) {
			Application.log.error("{}",e);
		}
	}

	public void writeToCSVEurope( CrawlResult result){
		List<SchoolHolidayEurope> schoolHolidaysEurope = result.getSchoolHolidayListEurope();
		String title = result.getTitle();
		String folderName = "SchoolHolidayEuropeResults";
		new File(folderName).mkdir();
		// name of generated csv
		final String CSV_LOCATION = folderName+"/"+title+".csv ";
		try (FileWriter writer = new FileWriter(CSV_LOCATION);) {
			// Create Mapping Strategy to arrange the
			// column name in order
			ColumnPositionMappingStrategy mappingStrategy=
					new ColumnPositionMappingStrategy();
			mappingStrategy.setType( SchoolHolidayEurope.class);

			// Arrange column name as provided in below array.
			String[] columns = new String[]
					{ "description", "region", "startDate", "endDate"};
			mappingStrategy.setColumnMapping(columns);

			// Createing StatefulBeanToCsv object
			StatefulBeanToCsvBuilder<SchoolHolidayEurope> builder=
					new StatefulBeanToCsvBuilder(writer);
			StatefulBeanToCsv beanWriter =
					builder.withMappingStrategy(mappingStrategy).build();

			// Write list to StatefulBeanToCsv object
			beanWriter.write(new SchoolHolidayEurope( "School-Holiday Type","Region","Start Date","End Date" ));
			beanWriter.write( schoolHolidaysEurope );

		} catch (Exception e) {
			Application.log.error("{}",e);
		}
	}
}
