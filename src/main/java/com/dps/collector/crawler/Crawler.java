package com.dps.collector.crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.security.krb5.Config;

import com.dps.collector.bean.Air;
import com.dps.collector.util.ConfigUtil;

public class Crawler {
	private static final Logger log = LoggerFactory.getLogger(Crawler.class);
	public ArrayList<Air> getAir(String url){
		List <Air> list = new ArrayList<Air>();
		try {
			Document doc = Jsoup.connect(url).timeout(0).get();
			Elements datas = doc.select("#detail-data tbody tr");
			
			for (Element data:datas){
				Elements airs = data.select("td");
				Air airBean = new Air();
				airBean.setArea(doc.select("h2").html());
				String time = doc.select(".live_data_time p").html();
				String t =time.split("：")[1];
				airBean.setTimePoint(t);
				for(int i=0;i<airs.size();i++){
					if(!"_".equals(airs.get(i).html())){
						switch (i) {
						case 0:
							airBean.setPositionName(airs.get(i).html());
							String name = airs.get(i).html();
							if("万寿西宫".equals(name)){
								airBean.setStationCode("1");	
							}else if("定陵".equals(name)){
								airBean.setStationCode("2");
							}else if("东四".equals(name)){
								airBean.setStationCode("3");
							}else if("天坛".equals(name)){
								airBean.setStationCode("4");
							}else if("农展馆".equals(name)){
								airBean.setStationCode("5");
							}else if("官园".equals(name)){
								airBean.setStationCode("6");
							}else if("海淀区万柳".equals(name)){
								airBean.setStationCode("7");
							}else if("顺义新城".equals(name)){
								airBean.setStationCode("8");
							}else if("怀柔镇".equals(name)){
								airBean.setStationCode("9");
							}else if("昌平镇".equals(name)){
								airBean.setStationCode("10");
							}else if("奥体中心".equals(name)){
								airBean.setStationCode("11");
							}else if("古城".equals(name)){
								airBean.setStationCode("12");
							}
							break;
						case 1:
							airBean.setAqi(Integer.valueOf(airs.get(i).html()));
							break;
						case 2:
							airBean.setQuality(airs.get(i).html());
							break;
						case 3:
							airBean.setPrimaryPollutant(airs.get(i).html());
							break;
						case 4:
							airBean.setPm25(airs.get(i).html());
							break;
						case 5:
							airBean.setPm10(airs.get(i).html());
							break;
						case 6:
							airBean.setCo(airs.get(i).html());
							break;
						case 7:
							airBean.setNo2(airs.get(i).html());
							break;
						case 8:
							airBean.setO3(airs.get(i).html());
							break;
						case 9:
							airBean.setO3_8h(airs.get(i).html());
							break;
						case 10:
							airBean.setSo2(airs.get(i).html());
							break;
						default:
							break;
						}
					}
					
				}
				airBean=enhancement(airBean);
				list.add(airBean);
				
			}
			
			//System.out.println(datas.html());
		} catch (IOException e) {
			log.error("无法连接");
			log.error(e.getMessage(),e);
		}
		
		
		return (ArrayList<Air>) list;
	}
	
	public Air enhancement(Air air){
		if("1".equals(air.getStationCode())){
			air.setTemperature(getTemp(ConfigUtil.getObject("1url")));
			air.setHumidity(getHumidity(ConfigUtil.getObject("1url")));
		}else if("2".equals(air.getStationCode())){
			air.setTemperature(getTemp(ConfigUtil.getObject("2url")));
			air.setHumidity(getHumidity(ConfigUtil.getObject("2url")));
		}else if("3".equals(air.getStationCode())){
			air.setTemperature(getTemp(ConfigUtil.getObject("3url")));
			air.setHumidity(getHumidity(ConfigUtil.getObject("3url")));
		}else if("4".equals(air.getStationCode())){
			air.setTemperature(getTemp(ConfigUtil.getObject("4url")));
			air.setHumidity(getHumidity(ConfigUtil.getObject("4url")));
		}else if("5".equals(air.getStationCode())){
			air.setTemperature(getTemp(ConfigUtil.getObject("5url")));
			air.setHumidity(getHumidity(ConfigUtil.getObject("5url")));
		}else if("6".equals(air.getStationCode())){
			air.setTemperature(getTemp(ConfigUtil.getObject("6url")));
			air.setHumidity(getHumidity(ConfigUtil.getObject("6url")));
		}else if("7".equals(air.getStationCode())){
			air.setTemperature(getTemp(ConfigUtil.getObject("7url")));
			air.setHumidity(getHumidity(ConfigUtil.getObject("7url")));
		}else if("8".equals(air.getStationCode())){
			air.setTemperature(getTemp(ConfigUtil.getObject("8url")));
			air.setHumidity(getHumidity(ConfigUtil.getObject("8url")));
		}else if("9".equals(air.getStationCode())){
			air.setTemperature(getTemp(ConfigUtil.getObject("9url")));
			air.setHumidity(getHumidity(ConfigUtil.getObject("9url")));
		}else if("10".equals(air.getStationCode())){
			air.setTemperature(getTemp(ConfigUtil.getObject("10url")));
			air.setHumidity(getHumidity(ConfigUtil.getObject("10url")));
		}else if("11".equals(air.getStationCode())){
			air.setTemperature(getTemp(ConfigUtil.getObject("11url")));
			air.setHumidity(getHumidity(ConfigUtil.getObject("11url")));
		}else if("12".equals(air.getStationCode())){
			air.setTemperature(getTemp(ConfigUtil.getObject("12url")));
			air.setHumidity(getHumidity(ConfigUtil.getObject("12url")));
		}
		
		
		return air;
		
	}
	public String getTemp(String url) {
		Document doc = null;
		try {
			doc = Jsoup.connect(url).timeout(0).get();
		} catch (IOException e) {
			log.error("获取温度失败",e);

			return "";

		}
		Elements E = doc.select(".weather_day_brief_hours36 li");
		String temp =E.get(0).html();
		temp = temp.split("：")[1].split(" ")[0];
		//log.info(temp);
		return temp;
	}
	public String getHumidity(String url){
		Document doc = null;
		try {
			doc = Jsoup.connect(url).timeout(0).get();
		} catch (IOException e) {
			log.error("获取湿度失败",e);

			return "";

		}
		Elements E = doc.select(".weather_day_brief_hours36 li");
		String humid =E.get(9).html();
		humid = humid.split("：")[1];
		//log.info(humid);
		return humid;	
	}
	/*
	public static void main( String[] args ) throws IOException{
		Crawler c = new Crawler();
		List list=c.getAir(ConfigUtil.getObject("airurl"));
		System.out.println(list);
	 }
	 */
}
