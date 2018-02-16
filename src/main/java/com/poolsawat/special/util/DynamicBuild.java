/**
 * 
 */
package com.poolsawat.special.util;

import java.util.LinkedHashMap;
import java.util.Map;

import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;

import com.poolsawat.special.model.ExampleDataSource;
import com.poolsawat.special.model.FieldData;
import com.poolsawat.special.model.PageData;

/**
 * @author PoolPC
 *
 */
public interface DynamicBuild {		
	
		public DynamicReport getBuildTableCriteria(ExampleDataSource dsCriteria);
		public DynamicReport getBuildTableData(ExampleDataSource dsData);
	
		public void appendTitle(DynamicReportBuilder drb,String title);
		public void appendSubTitle(DynamicReportBuilder drb,String subTitle);
		public void appendPageOptions(DynamicReportBuilder drb,PageData pageOptions);
		public void appendColumns(DynamicReportBuilder drb,Map<String, FieldData> headerDS);
		public void appendColumns(DynamicReportBuilder drb,LinkedHashMap<String, Object> headerDS);	
		public void appendTitleImage(DynamicReportBuilder drb);
		public void appendColumnsMerge(DynamicReportBuilder drb);
		
		
}
