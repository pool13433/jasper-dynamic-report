package com.poolsawat.special.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RawDataReport {
	@JsonProperty(value="report_title")
	private String reportTitle;
	
	@JsonProperty(value="report_sub_title")
	private String reportSubTitle;
	
	@JsonProperty(value="meta_criteria")
	private List<RawDataItem[]> meta_criteria;
	
	@JsonProperty(value="meta_data")
	private List<RawDataItem[]> meta_data;
	
	@JsonProperty(value="meta_header")
	private List<RawDataItem> meta_header;
	
	@JsonProperty(value="meta_footer")
	private RawDataFooter meta_footer;
	
	@JsonProperty(value="report_options")
	private RawDataOptions reportOptions;

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

	public List<RawDataItem[]> getMeta_criteria() {
		return meta_criteria;
	}

	public void setMeta_criteria(List<RawDataItem[]> meta_criteria) {
		this.meta_criteria = meta_criteria;
	}

	public List<RawDataItem[]> getMeta_data() {
		return meta_data;
	}

	public void setMeta_data(List<RawDataItem[]> meta_data) {
		this.meta_data = meta_data;
	}

	public List<RawDataItem> getMeta_header() {
		return meta_header;
	}

	public void setMeta_header(List<RawDataItem> meta_header) {
		this.meta_header = meta_header;
	}

	public RawDataFooter getMeta_footer() {
		return meta_footer;
	}

	public void setMeta_footer(RawDataFooter meta_footer) {
		this.meta_footer = meta_footer;
	}

	public RawDataOptions getReportOptions() {
		return reportOptions;
	}

	public void setReportOptions(RawDataOptions reportOptions) {
		this.reportOptions = reportOptions;
	}

	
	
}
