package com.poolsawat.special.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RawDataMargin {
	@JsonProperty(value = "top")
	private Float top;

	@JsonProperty(value = "right")
	private Float right;

	@JsonProperty(value = "bottom")
	private Float bottom;

	@JsonProperty(value = "left")
	private Float left;

	public Float getTop() {
		return top;
	}

	public void setTop(Float top) {
		this.top = top;
	}

	public Float getRight() {
		return right;
	}

	public void setRight(Float right) {
		this.right = right;
	}

	public Float getBottom() {
		return bottom;
	}

	public void setBottom(Float bottom) {
		this.bottom = bottom;
	}

	public Float getLeft() {
		return left;
	}

	public void setLeft(Float left) {
		this.left = left;
	}

}
