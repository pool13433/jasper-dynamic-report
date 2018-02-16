package com.poolsawat.special.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MarginData {

	@JsonProperty(value = "top")
	private int top;

	@JsonProperty(value = "right")
	private int right;

	@JsonProperty(value = "bottom")
	private int bottom;

	@JsonProperty(value = "left")
	private int left;

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public int getRight() {
		return right;
	}

	public void setRight(int right) {
		this.right = right;
	}

	public int getBottom() {
		return bottom;
	}

	public void setBottom(int bottom) {
		this.bottom = bottom;
	}

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

}
