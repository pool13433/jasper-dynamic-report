package com.poolsawat.special;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class AppProperties {
	Logger logger = LoggerFactory.getLogger(AppProperties.class);

	public AppProperties() {
		logger.info("Hello World");
	}
}
