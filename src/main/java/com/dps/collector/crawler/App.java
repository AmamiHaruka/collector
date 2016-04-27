package com.dps.collector.crawler;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Timer;

import com.dps.collector.task.CrawlerTask;
import com.dps.collector.task.WeatherForecastCrawlerTask;
import com.dps.collector.util.ConfigUtil;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException, ClassNotFoundException
    {
    	Calendar date = Calendar.getInstance();
		date.set(date.get(Calendar.YEAR), date.get(Calendar.MONTH),
				date.get(Calendar.DATE), 0, 0, 0);
		Timer t = new Timer();
		Long time = Long.valueOf(ConfigUtil.getObject("terminal_time"));
		Long time1 = Long.valueOf(ConfigUtil.getObject("forecast_time"));
		
		//t.schedule(new CrawlerTask(), date.getTime(),time*1000*60);
		t.schedule(new WeatherForecastCrawlerTask(), date.getTime(), time1*1000*60*60);
		
    }
}
