package it.codegen.crawler.models;

import java.util.List;

public class CrawlResult
{
	private String title;
	private List<SchoolHolidayEdarabia> schoolHolidayListEdarabia;
	private List<SchoolHolidayEurope> schoolHolidayListEurope;

	public String getTitle()
	{
		return title;
	}

	public void setTitle( String title )
	{
		this.title = title;
	}

	public List<SchoolHolidayEdarabia> getSchoolHolidayListEdarabia()
	{
		return schoolHolidayListEdarabia;
	}

	public void setSchoolHolidayListEdarabia(
			List<SchoolHolidayEdarabia> schoolHolidayListEdarabia )
	{
		this.schoolHolidayListEdarabia = schoolHolidayListEdarabia;
	}

	public List<SchoolHolidayEurope> getSchoolHolidayListEurope()
	{
		return schoolHolidayListEurope;
	}

	public void setSchoolHolidayListEurope( List<SchoolHolidayEurope> schoolHolidayListEurope )
	{
		this.schoolHolidayListEurope = schoolHolidayListEurope;
	}
}
