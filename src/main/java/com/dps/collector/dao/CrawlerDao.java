package com.dps.collector.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dps.collector.bean.Air;
import com.dps.collector.crawler.Crawler;
import com.dps.collector.util.ConfigUtil;

public class CrawlerDao {
	private static final Logger log = LoggerFactory.getLogger(CrawlerDao.class);
	private Connection conn = null;

	public CrawlerDao() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("成功加载MySQL驱动程序");
		conn = DriverManager.getConnection(ConfigUtil.getObject("url"));
	}

	public boolean update(Air airBean) throws SQLException {
		boolean flag = true;
		PreparedStatement ps = null;
		String sql = "update air_data set aqi=?,area=?,position_name=?,station_code=?,so2=?,so2_24h=?,no2=?,no2_24h=?,pm10=?,pm10_24h=?,co=?,co_24h=?,o3=?,o3_24h=?,o3_8h=?,o3_8h_24h=?,pm2_5=?,pm2_5_24h=?,primary_pollutant=?,quality=?,time_point=?,humidity=?,temperature=? WHERE station_code = ? and time_point=? ";
		ps = conn.prepareStatement(sql);
		ps.setString(1, String.valueOf(airBean.getAqi()));
		ps.setString(2, airBean.getArea());
		ps.setString(3, airBean.getPositionName());
		ps.setString(4, airBean.getStationCode());
		ps.setString(5, airBean.getSo2());
		ps.setString(6, airBean.getSo2_24h());
		ps.setString(7, airBean.getNo2());
		ps.setString(8, airBean.getNo2_24h());
		ps.setString(9, airBean.getPm10());
		ps.setString(10, airBean.getPm10_24h());
		ps.setString(11, airBean.getCo());
		ps.setString(12, airBean.getCo_24h());
		ps.setString(13, airBean.getO3());
		ps.setString(14, airBean.getO3_24h());
		ps.setString(15, airBean.getO3_8h());
		ps.setString(16, airBean.getO3_8h_24h());
		ps.setString(17, airBean.getPm25());
		ps.setString(18, airBean.getPm25_24h());
		ps.setString(19, airBean.getPrimaryPollutant());
		ps.setString(20, airBean.getQuality());
		ps.setString(21, airBean.getTimePoint());
		ps.setString(22, airBean.getHumidity());
		ps.setString(23, airBean.getTemperature());
		ps.setString(24, airBean.getStationCode());
		ps.setString(25, airBean.getTimePoint());
		int i = ps.executeUpdate();
		if (i == 0) {
			flag = false;
		}
		log.info("update the airdata");
		return flag;

	}

	public boolean insert(Air airBean) throws SQLException {
		boolean flag = true;
		PreparedStatement ps = null;
		String sql = "insert into air_data(aqi,area,position_name,station_code,so2,so2_24h,no2,no2_24h,pm10,pm10_24h,co,co_24h,o3,o3_24h,o3_8h,o3_8h_24h,pm2_5,pm2_5_24h,primary_pollutant,quality,time_point,humidity,temperature) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		ps = conn.prepareStatement(sql);
		ps.setString(1, String.valueOf(airBean.getAqi()));
		ps.setString(2, airBean.getArea());
		ps.setString(3, airBean.getPositionName());
		ps.setString(4, airBean.getStationCode());
		ps.setString(5, airBean.getSo2());
		ps.setString(6, airBean.getSo2_24h());
		ps.setString(7, airBean.getNo2());
		ps.setString(8, airBean.getNo2_24h());
		ps.setString(9, airBean.getPm10());
		ps.setString(10, airBean.getPm10_24h());
		ps.setString(11, airBean.getCo());
		ps.setString(12, airBean.getCo_24h());
		ps.setString(13, airBean.getO3());
		ps.setString(14, airBean.getO3_24h());
		ps.setString(15, airBean.getO3_8h());
		ps.setString(16, airBean.getO3_8h_24h());
		ps.setString(17, airBean.getPm25());
		ps.setString(18, airBean.getPm25_24h());
		ps.setString(19, airBean.getPrimaryPollutant());
		ps.setString(20, airBean.getQuality());
		ps.setString(21, airBean.getTimePoint());
		ps.setString(22, airBean.getHumidity());
		ps.setString(23, airBean.getTemperature());
		int i = ps.executeUpdate();
		if (i == 0) {
			flag = false;
		}
		return flag;
	}

	public boolean isExist(String time, String stationCode) throws SQLException {
		boolean flag = false;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select id from air_data where station_code = ? and time_point=?";

		ps = conn.prepareStatement(sql);
		ps.setString(1, stationCode);
		ps.setString(2, time);
		rs = ps.executeQuery();

		while (rs.next()) {

			String re =rs.getString("id");
			flag = true;
		}
		
		//System.out.println("");
		return flag;

	}
/*
	public static void main(String[] args) {
		try {
			CrawlerDao dao = new CrawlerDao();
			dao.isExist("2016", "1");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
*/
}
