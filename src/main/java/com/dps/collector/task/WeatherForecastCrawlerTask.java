package com.dps.collector.task;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dps.collector.bean.StationForecast;
import com.dps.collector.crawler.WeatherForecastCrawler;
import com.dps.collector.dao.WeatherForecastCrawlerDao;
import com.dps.collector.util.ConfigUtil;

public class WeatherForecastCrawlerTask extends java.util.TimerTask{
	private static final Logger log = LoggerFactory
			.getLogger(WeatherForecastCrawlerTask.class);
	@Override
	public void run() {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy/MM/dd HH:mm:ss");// 可以方便地修改日期格式
		String time = dateFormat.format(now);
		log.info(time + "开始爬取预报...........");
		WeatherForecastCrawler wc = new WeatherForecastCrawler();
		StationForecast sf =wc.getForecast(ConfigUtil.getObject("huairouurl").split(" ")[0], ConfigUtil.getObject("huairouurl").split(" ")[1]);
		StationForecast sf1 =wc.getForecast(ConfigUtil.getObject("beijingurl").split(" ")[0], ConfigUtil.getObject("beijingurl").split(" ")[1]);
		StationForecast sf2 =wc.getForecast(ConfigUtil.getObject("changpingurl").split(" ")[0], ConfigUtil.getObject("changpingurl").split(" ")[1]);
		StationForecast sf3 =wc.getForecast(ConfigUtil.getObject("chaoyangurl").split(" ")[0], ConfigUtil.getObject("chaoyangurl").split(" ")[1]);
		StationForecast sf4 =wc.getForecast(ConfigUtil.getObject("haidianurl").split(" ")[0], ConfigUtil.getObject("haidianurl").split(" ")[1]);
		StationForecast sf5 =wc.getForecast(ConfigUtil.getObject("shunyiurl").split(" ")[0], ConfigUtil.getObject("shunyiurl").split(" ")[1]);
		StationForecast sf6 =wc.getForecast(ConfigUtil.getObject("shijingshanurl").split(" ")[0], ConfigUtil.getObject("shijingshanurl").split(" ")[1]);
		try {
			save(sf);
			save(sf1);
			save(sf2);
			save(sf3);
			save(sf4);
			save(sf5);
			save(sf6);
		} catch (ClassNotFoundException e) {
			log.error(e.getLocalizedMessage(),e);
		} catch (SQLException e) {
			log.error(e.getLocalizedMessage(),e);
		}
		
		
	}
	public void save (StationForecast sf) throws ClassNotFoundException, SQLException{
		WeatherForecastCrawlerDao dao = new WeatherForecastCrawlerDao();
		if(!dao.isExist(sf.getDate(), sf.getStation()));{
			dao.insert(sf);
		}
	}

}
