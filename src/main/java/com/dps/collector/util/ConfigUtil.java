package com.dps.collector.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Config
 * 
 * @author duxj3 duxj3@asiainfo.com
 * @version Create time：2014年15月12日 下午3:15:45
 * 
 * @version 1.0.0
 */

public final class ConfigUtil {
	private static Properties properties;
	private static final Logger logger = LoggerFactory
			.getLogger(ConfigUtil.class);

	private ConfigUtil() {
	}

	static {
		try {
			properties = new Properties();
			String filePath = "init.properties";
			InputStream in = new BufferedInputStream(ConfigUtil.class
					.getClassLoader().getResourceAsStream(filePath));
			properties.load(in);
			in.close();
		} catch (IOException e) {
			logger.error("读取配置信息出错！", e);
		}
	}

	public static String getObject(String prepKey) {
		return properties.getProperty(prepKey);
	}

	public static Properties getLoadFile(String fileName) {
		Properties propertie = new Properties();
		InputStream in;
		try {
			in = new BufferedInputStream(ConfigUtil.class.getClassLoader()
					.getResourceAsStream(fileName));
			propertie.load(in);
			in.close();
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
		return propertie;
	}

}
