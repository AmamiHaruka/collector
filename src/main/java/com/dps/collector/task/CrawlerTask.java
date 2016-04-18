package com.dps.collector.task;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dps.collector.bean.Air;
import com.dps.collector.crawler.Crawler;
import com.dps.collector.dao.CrawlerDao;
import com.dps.collector.util.ConfigUtil;


public class CrawlerTask extends java.util.TimerTask{
	private static final Logger log = LoggerFactory
			.getLogger(CrawlerTask.class);
	@Override
	public void run() {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy/MM/dd HH:mm:ss");// 可以方便地修改日期格式
		String time = dateFormat.format(now);
		log.info(time + "开始爬取...........");
		Crawler crawler = new Crawler();
    	CrawlerDao dao = null;
		try {
			dao = new CrawlerDao();
		} catch (ClassNotFoundException e) {
			log.error(e.getLocalizedMessage(),e);
		} catch (SQLException e) {
			log.error(e.getLocalizedMessage(),e);
		}
    	List <Air> list = crawler.getAir(ConfigUtil.getObject("airurl"));
    	for(Air air :list){
    		try {
				if(dao.isExist(air.getTimePoint(), air.getStationCode())){
					dao.update(air);
					log.info("已存在更新数据");
				}else{
					dao.insert(air);
					log.info("不存在将新数据写入数据库");
				}
			} catch (SQLException e) {
				log.error(e.getLocalizedMessage(),e);
			}
    	}
    	log.info("全部处理完毕");
		
	}

}
