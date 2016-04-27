package com.dps.collector.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.dps.collector.bean.StationForecast;
import com.dps.collector.util.ConfigUtil;

public class WeatherForecastCrawlerDao {
	private static final Logger log = LoggerFactory.getLogger(WeatherForecastCrawlerDao.class);
	private Connection conn = null;
	public WeatherForecastCrawlerDao() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("成功加载MySQL驱动程序");
		conn = DriverManager.getConnection(ConfigUtil.getObject("url"));
	}
	public boolean insert(StationForecast sf) throws SQLException{
		boolean flag = true;
		PreparedStatement ps = null;
		String sql = "insert into forecast_data(date,station,forecast_oneday,forecast_twoday,forecast_threeday) values(?,?,?,?,?)";
		ps = conn.prepareStatement(sql);
		ps.setString(1, sf.getDate());
		ps.setString(2, sf.getStation());
		ps.setString(3, JSONObject.toJSONString(sf.getOneDay()));
		ps.setString(4, JSONObject.toJSONString(sf.getTwoDay()));
		ps.setString(5, JSONObject.toJSONString(sf.getThreeDay()));
		int i = ps.executeUpdate();
		if (i == 0) {
			flag = false;
		}
		log.info("insert the forecast data.");
		
		return flag;
		
	}
	public boolean isExist(String date,String station) throws SQLException{
		boolean flag = false;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select id from forecast_data where station = ? and date=?";

		ps = conn.prepareStatement(sql);
		ps.setString(1, station);
		ps.setString(2, date);
		rs = ps.executeQuery();

		while (rs.next()) {

			String re =rs.getString("id");
			flag = true;
		}
		
		//System.out.println("");
		return flag;
	}
}
