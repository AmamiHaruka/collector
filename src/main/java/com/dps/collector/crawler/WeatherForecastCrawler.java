package com.dps.collector.crawler;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.dps.collector.bean.Forecast;
import com.dps.collector.bean.StationForecast;
import com.dps.collector.util.ConfigUtil;

public class WeatherForecastCrawler {
	private static final Logger log = LoggerFactory.getLogger(WeatherForecastCrawler.class);
	public StationForecast getForecast(String url,String station){
		StationForecast sf = new StationForecast();
		try {
			Document doc = Jsoup.connect(url).timeout(0).get();
			Elements datas = doc.select("#fore-semi");
			Elements forecasts = datas.select("#fore-semi-day");
			int i = 1;
			Date date=new Date();
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			String time=format.format(date);
			sf.setDate(time);
			sf.setStation(station);
			for (Element forecast:forecasts){
				if(i>1&&i<5){
					Forecast f = new Forecast();
					Element e = forecast.select("tr").get(1);
					Element daydetail = e.select("td").get(1);
					String nightDetailStr = e.select("td").get(3).html();
					String dayDetailStr = daydetail.html();
					String highTemperature =dayDetailStr.split("：")[1].split("<")[0].split("℃")[0];
					String highHumidity = dayDetailStr.split("：")[3].split("<")[0];
					String lowTemperature = nightDetailStr.split("：")[1].split("<")[0].split("℃")[0];
					String lowHumidity = nightDetailStr.split("：")[3].split("<")[0];
					String dayDirection= dayDetailStr.split("：")[4].split("<")[0];
					String dayKazePower =dayDetailStr.split("：")[5].split("<")[0];
					String nightDirection= nightDetailStr.split("：")[4].split("<")[0];
					String nightKazePower =nightDetailStr.split("：")[5].split("<")[0];
					f.setHighTemperature(highTemperature);
					f.setHighHumidity(highHumidity);
					f.setLowTemperature(lowTemperature);
					f.setLowHumidity(lowHumidity);
					f.setDayDirection(dayDirection);
					f.setDayKazePower(dayKazePower);
					f.setNightDirection(nightDirection);
					f.setNightKazePower(nightKazePower);
					//System.out.println(nightKazePower);
					if(i==2){
						sf.setOneDay(f);
					}else if(i==3){
						sf.setTwoDay(f);
					}else if(i==4){
						sf.setThreeDay(f);
					}
				}
				
				i++;
			}
			
		} catch (IOException e) {
			log.error(e.getLocalizedMessage(),e);
			StationForecast sfempty = new StationForecast();
			return sfempty;
		}
		
		
		
		
		return sf;
		
	}
	public static void main( String[] args ) throws IOException{
		WeatherForecastCrawler wc = new WeatherForecastCrawler();
		//StationForecast sf=wc.getForecast("http://www.22086.com/d7_beijing.html");
		//System.out.println(JSONObject.toJSONString(sf.getOneDay()));
	 }
}
