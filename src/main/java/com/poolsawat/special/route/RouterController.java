package com.poolsawat.special.route;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poolsawat.special.export.ReportGenarater;
import com.poolsawat.special.model.ExampleDataSource;
import com.poolsawat.special.model.SampleData;

@Controller
public class RouterController {
	static private Logger LOGGER = LoggerFactory.getLogger(RouterController.class);

	@Autowired
	private Environment env;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {

		LOGGER.info("index...");

		return "index";
	}

	@RequestMapping(value = "/sampleReport", method = RequestMethod.GET)
	public String report(HttpServletRequest request) {
		// final String DIR_PATH =
		// "D:/HardCoreCode/workspace-luna/MyReport/src/main/resources";
		try {
			// String DIR_PATH = properties.getProperty("dir_name");
			String DIR_PATH = env.getProperty("dir_name");
			
			LOGGER.info("sampleReport...");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "sampleReport";
	}

	@RequestMapping(value = "/getJson", method = RequestMethod.GET)
	@ResponseBody
	public SampleData savePOST() {
		SampleData data = new SampleData();
		data.setName("xxxxxxxxxxxxxx");
		return data;
	}

	public static void main(String[] args) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			String json = "[{\"name\":\"mkyong\", \"age\":29},{\"name\":\"mkyong\", \"age\":29, \"field\":29, \"class\":29}]";
			List<Map<String, Object>> map = new ArrayList<Map<String, Object>>();
			//map = mapper.readValue(new RouterController().getResourceAsStreamByte(), new TypeReference<List<Map<String, String>>>() {});
			ExampleDataSource ds = mapper.readValue(new RouterController().getResourceAsStreamByte(), ExampleDataSource.class);
			System.out.println(ds);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private byte[] getResourceAsStreamByte() {
		try {
			return Files.readAllBytes(Paths.get("/JasperReport/input/datasource.json"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private InputStream getResourceAsStream(HttpServletRequest request, String target) {
		return request.getSession().getServletContext().getResourceAsStream(target);
	}

	public List<SampleData> getSampleList() {
		List<SampleData> samples = new ArrayList<SampleData>();

		SampleData s1 = new SampleData();
		s1.setName("Sample1");
		s1.setCountry("Thailand");
		s1.setId(99);
		samples.add(s1);

		return samples;
	}

}
