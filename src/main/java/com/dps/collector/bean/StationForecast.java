package com.dps.collector.bean;

public class StationForecast {
	private String date;  //日期
	private String station;
	private Forecast oneDay;
	private Forecast twoDay;
	private Forecast threeDay;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
	public Forecast getOneDay() {
		return oneDay;
	}
	public void setOneDay(Forecast oneDay) {
		this.oneDay = oneDay;
	}
	public Forecast getTwoDay() {
		return twoDay;
	}
	public void setTwoDay(Forecast twoDay) {
		this.twoDay = twoDay;
	}
	public Forecast getThreeDay() {
		return threeDay;
	}
	public void setThreeDay(Forecast threeDay) {
		this.threeDay = threeDay;
	}
	
}
