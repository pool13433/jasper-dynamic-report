package com.poolsawat.special.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FieldData {
	
	@JsonProperty(value = "label")
	private String label;

	@JsonProperty(value = "height")
	private short height;

	@JsonProperty(value = "width")
	private short width;

	@JsonProperty(value = "text-align")
	private String textAlign;
	
	@JsonProperty(value = "vertical-align")
	private String verticalAlign;

	@JsonProperty(value = "background-color")
	private String backgroundColor;
	
	@JsonProperty(value = "font-size")
	private int fontSize;

	public short getHeight() {
		return height;
	}

	public void setHeight(short height) {
		this.height = height;
	}

	public short getWidth() {
		return width;
	}

	public void setWidth(short width) {
		this.width = width;
	}

	public String getTextAlign() {
		return textAlign;
	}
	
	public String getTextAlignAlias() {
		return "text-align";
	}

	public void setTextAlign(String textAlign) {
		this.textAlign = textAlign;
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}
	
	public String getBackgroundColorAlias() {
		return "background-color";
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}


	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getVerticalAlign() {
		return verticalAlign;
	}
	
	public String getVerticalAlignAlias() {
		return "vertical-align";
	}

	public void setVerticalAlign(String verticalAlign) {
		this.verticalAlign = verticalAlign;
	}

	
	
	
	
}
