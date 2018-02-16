package com.poolsawat.special.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PageData {
		
	@JsonProperty(value = "page_fluid")
	private boolean pageFluid;;
	
	@JsonProperty(value = "page_size")
	private String pageSize; //A4,A5
	
	@JsonProperty(value = "page_orientation")
	private String pageOrientation; // landscape,portrait
	
	@JsonProperty(value = "page_margin")
	private MarginData pageMargin;

	public boolean isPageFluid() {		
		return pageFluid;
	}

	public void setPageFluid(boolean pageFluid) {
		this.pageFluid = pageFluid;
	}

	public String getPageSize() {
		if(pageSize == null){
			return "A4";
		}
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getPageOrientation() {
		if(pageOrientation == null){
			return "portrait";
		}
		return pageOrientation;
	}

	public void setPageOrientation(String pageOrientation) {
		this.pageOrientation = pageOrientation;
	}

	public MarginData getPageMargin() {
		if(pageMargin == null){
			pageMargin  = new MarginData();
			pageMargin.setBottom(10);
			pageMargin.setLeft(10);
			pageMargin.setTop(10);
			pageMargin.setRight(10);
		}
		return pageMargin;
	}

	public void setPageMargin(MarginData pageMargin) {
		this.pageMargin = pageMargin;
	}
	
	
	
}
