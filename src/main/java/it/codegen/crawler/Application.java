package it.codegen.crawler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Hishan Indrajith on 24-May-19 03:29 PM.
 */
@SpringBootApplication
public class Application implements CommandLineRunner
{
	// Logger instance that is used for logging
	public static final Logger log = LoggerFactory.getLogger( Application.class );
	// Autowiring the crawler instance
	@Autowired
	private Crawler crawler;

	public static void main( String[] args )
	{
		// starting the Spring boot Application
		SpringApplication.run( Application.class );
	}

	@Override
	public void run( String... args ) throws Exception
	{
		// This method run after Spring boot application started
		log.info( "Application started " );
		log.info( "Crawling from https://www.edarabia.com " );
		crawler.crawlAndSaveEdarabiaData();
		log.info( "Crawling from https://www.schoolholidayseurope.eu" );
		crawler.crawlAndSaveSchoolHolidaysEuropeData();
	}

}
