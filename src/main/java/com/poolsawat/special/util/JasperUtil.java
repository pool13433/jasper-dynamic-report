package com.poolsawat.special.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class JasperUtil {
	
	static private Logger LOGGER = LoggerFactory.getLogger(JasperUtil.class);
	
	public void readPIctureFromURL() {
		try {
			FileUtils.copyURLToFile(new URL("http://stevenbaggiero.com/wp-content/uploads/2017/08/home-holiday-to-do-today-160725-tease-01_bfe2763c6779cbd2a90c21de48b07757.jpg"), new File("/JasperReport/images/xxxxx.png"));
		} catch (Exception e) {
			LOGGER.error("readPIctureFromURL error",e);
		}
	}
	
	public Properties getProperties() {
		try {
			Resource resource = new ClassPathResource("/application.properties");
			Properties props = PropertiesLoaderUtils.loadProperties(resource);
			return props;
		} catch (IOException e) {
			LOGGER.error("getProperties error ::", e);
		}
		return new Properties();
	}
	
	public static void main(String[] args) {
		new JasperUtil().readPIctureFromURL();
	}
}
