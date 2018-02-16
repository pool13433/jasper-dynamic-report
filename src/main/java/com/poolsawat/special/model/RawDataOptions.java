package com.poolsawat.special.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RawDataOptions {
		@JsonProperty(value="page_size")
		private String  pageSize;
		
		@JsonProperty(value="page_orientation")
		private String  pageOrientation;
		
		@JsonProperty(value="page_margin")
		private RawDataMargin  pageMargin;

		public String getPageSize() {
			return pageSize;
		}

		public void setPageSize(String pageSize) {
			this.pageSize = pageSize;
		}

		public String getPageOrientation() {
			return pageOrientation;
		}

		public void setPageOrientation(String pageOrientation) {
			this.pageOrientation = pageOrientation;
		}

		public RawDataMargin getPageMargin() {
			return pageMargin;
		}

		public void setPageMargin(RawDataMargin pageMargin) {
			this.pageMargin = pageMargin;
		}
		
		
		
}
