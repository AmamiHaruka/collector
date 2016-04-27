package com.dps.collector.bean;

public class Forecast {
	private String highTemperature;
	private String lowTemperature;
	private String highHumidity;
	private String lowHumidity;
	private String dayKazePower;
	private String nightKazePower;
	private String dayDirection;
	private String nightDirection;
	
	public String getDayKazePower() {
		return dayKazePower;
	}
	public void setDayKazePower(String dayKazePower) {
		this.dayKazePower = dayKazePower;
	}
	public String getNightKazePower() {
		return nightKazePower;
	}
	public void setNightKazePower(String nightKazePower) {
		this.nightKazePower = nightKazePower;
	}
	public String getDayDirection() {
		return dayDirection;
	}
	public void setDayDirection(String dayDirection) {
		this.dayDirection = dayDirection;
	}
	public String getNightDirection() {
		return nightDirection;
	}
	public void setNightDirection(String nightDirection) {
		this.nightDirection = nightDirection;
	}
	public String getHighTemperature() {
		return highTemperature;
	}
	public void setHighTemperature(String highTemperature) {
		this.highTemperature = highTemperature;
	}
	public String getLowTemperature() {
		return lowTemperature;
	}
	public void setLowTemperature(String lowTemperature) {
		this.lowTemperature = lowTemperature;
	}
	public String getHighHumidity() {
		return highHumidity;
	}
	public void setHighHumidity(String highHumidity) {
		this.highHumidity = highHumidity;
	}
	public String getLowHumidity() {
		return lowHumidity;
	}
	public void setLowHumidity(String lowHumidity) {
		this.lowHumidity = lowHumidity;
	}
}
