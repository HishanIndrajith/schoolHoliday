package it.codegen.crawler.models;

public class SchoolHolidayEdarabia
{
	private String schoolTypeName;
	private String date;
	private String description;

	public SchoolHolidayEdarabia( String schoolTypeName, String date, String description )
	{
		this.schoolTypeName = schoolTypeName;
		this.date = date;
		this.description = description;
	}

	public String getSchoolTypeName()
	{
		return schoolTypeName;
	}

	public void setSchoolTypeName( String schoolTypeName )
	{
		this.schoolTypeName = schoolTypeName;
	}

	public String getDate()
	{
		return date;
	}

	public void setDate( String date )
	{
		this.date = date;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription( String description )
	{
		this.description = description;
	}
}
