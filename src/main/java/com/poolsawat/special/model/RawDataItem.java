package com.poolsawat.special.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RawDataItem {
	
	@JsonProperty(value="text")
	@JsonInclude(Include.NON_NULL)
	private String text;
	
	@JsonProperty(value="label")
	@JsonInclude(Include.NON_NULL)
	private String label;
	
	@JsonProperty(value="style")
	@JsonInclude(Include.NON_NULL)
	private String style;
	
	@JsonProperty(value="childs")
	@JsonInclude(Include.NON_NULL)
	private List<RawDataItem> childs;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public List<RawDataItem> getChilds() {
		return childs;
	}
	public void setChilds(List<RawDataItem> childs) {
		this.childs = childs;
	}
	
	
}
