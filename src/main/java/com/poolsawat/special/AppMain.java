package com.poolsawat.special;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poolsawat.special.model.RawDataReport;

public class AppMain {
	static private Logger LOGGER = LoggerFactory.getLogger(AppMain.class);

	public static void main(String[] args) {
		InputStream inJson = AppMain.class.getResourceAsStream("/json/datasource.2.0.json");
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
			mapper.configure(JsonParser.Feature.ALLOW_TRAILING_COMMA, false);
			RawDataReport raw = new ObjectMapper().readValue(inJson, RawDataReport.class);
			LOGGER.info("raw ::=="+mapper.writerWithDefaultPrettyPrinter().writeValueAsString(raw));
		} catch (JsonParseException e) {
			LOGGER.error("JsonParseException error ::",e);
		} catch (JsonMappingException e) {
			LOGGER.error("JsonMappingException error ::",e);
		} catch (IOException e) {
			LOGGER.error("IOException error ::",e);
		}
	}
}
