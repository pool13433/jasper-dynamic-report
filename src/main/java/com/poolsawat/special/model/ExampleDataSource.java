package com.poolsawat.special.model;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExampleDataSource {
	
	@JsonProperty(value="report_title")
	private String reportTitle;
	
	@JsonProperty(value="report_sub_title")
	private String reportSubTitle;	
	
	@JsonProperty(value="meta_header")
	private Map<String,FieldData> metaHeader;
	
	@JsonProperty(value="meta_data")
	private List<Map<String,Object>> metaData;
	
	@JsonProperty(value="report_options")
	private PageData reportOptions;	
	
	@JsonProperty(value="meta_criteria")
	private List<Map<String,Object>> metaCriteria;

	public String getReportTitle() {
		return reportTitle;
	}

	public void setReportTitle(String reportTitle) {
		this.reportTitle = reportTitle;
	}

	public String getReportSubTitle() {
		return reportSubTitle;
	}

	public void setReportSubTitle(String reportSubTitle) {
		this.reportSubTitle = reportSubTitle;
	}

	public Map<String, FieldData> getMetaHeader() {
		return metaHeader;
	}

	public void setMetaHeader(Map<String, FieldData> metaHeader) {
		this.metaHeader = metaHeader;
	}

	public List<Map<String, Object>> getMetaData() {
		return metaData;
	}

	public void setMetaData(List<Map<String, Object>> metaData) {
		this.metaData = metaData;
	}

	public PageData getReportOptions() {
		return reportOptions;
	}

	public void setReportOptions(PageData reportOptions) {
		this.reportOptions = reportOptions;
	}

	public List<Map<String, Object>> getMetaCriteria() {
		return metaCriteria;
	}

	public void setMetaCriteria(List<Map<String, Object>> metaCriteria) {
		this.metaCriteria = metaCriteria;
	}
	
	
	
}
