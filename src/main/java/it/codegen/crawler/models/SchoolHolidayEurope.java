package it.codegen.crawler.models;

public class SchoolHolidayEurope
{
	private String description;
	private String region;
	private String startDate;
	private String endDate;

	public SchoolHolidayEurope( String description, String region, String startDate, String endDate )
	{
		this.description = description;
		this.region = region;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription( String description )
	{
		this.description = description;
	}

	public String getStartDate()
	{
		return startDate;
	}

	public void setStartDate( String startDate )
	{
		this.startDate = startDate;
	}

	public String getEndDate()
	{
		return endDate;
	}

	public void setEndDate( String endDate )
	{
		this.endDate = endDate;
	}

	public String getRegion()
	{
		return region;
	}

	public void setRegion( String region )
	{
		this.region = region;
	}
}
