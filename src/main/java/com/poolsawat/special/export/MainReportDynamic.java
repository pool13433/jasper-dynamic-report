package com.poolsawat.special.export;

import java.awt.Color;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.fdvs.dj.core.DJConstants;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.ImageBanner;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.ImageScaleMode;
import ar.com.fdvs.dj.domain.constants.Page;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.icu.text.SimpleDateFormat;
import com.poolsawat.special.model.ExampleDataSource;
import com.poolsawat.special.model.FieldData;
import com.poolsawat.special.model.MarginData;
import com.poolsawat.special.model.PageData;
import com.poolsawat.special.util.DynamicBuild;
import com.poolsawat.special.util.DynamicStyle;

public class MainReportDynamic implements DynamicBuild {
	static private Logger LOGGER = LoggerFactory.getLogger(MainReportDynamic.class);

	private DynamicStyle style;

	public MainReportDynamic() {
		this.style = new DynamicStyle();
	}

	public void buidReport() {
		// https://www.programcreek.com/java-api-examples/index.php?api=ar.com.fdvs.dj.domain.builders.ColumnBuilder
		try {
			String fileName = new SimpleDateFormat("yyyyMMdd_Hms").format(new Date());

			ExampleDataSource exDS = new ObjectMapper().readValue(
					this.getResourceAsStreamByte("datasource_small.json"), ExampleDataSource.class);
			JasperPrint jasperPrints = getCombineJPPCriteriaAndData(exDS);
			// new ReportGenarater().sourceFormatPDF(jasperPrints,
			// "/JasperReport/output/test_report"+fileName+".pdf");
			new ReportGenarater()
					.sourceFormatXLS(jasperPrints, "/JasperReport/output/test_report" + fileName + ".xlsx");
			LOGGER.info(" Genarate Success");
		} catch (JsonParseException e) {
			LOGGER.error("JsonParseException error", e);
		} catch (JsonMappingException e) {
			LOGGER.error("JsonMappingException error", e);
		} catch (IOException e) {
			LOGGER.error("IOException error", e);
		}
	}

	public JasperPrint getCombineJPPCriteriaAndData(ExampleDataSource exDS) {
		try {
			Map<String, Object> myMap = new HashMap<String, Object>();
			myMap.put("reportCriteria", this.getMetaCriteiraDS(exDS.getMetaCriteria()));
			myMap.put("reportData", exDS.getMetaData());

			DynamicReport drData = getBuildTableData(exDS);
			JasperReport jprReportData = DynamicJasperHelper.generateJasperReport(drData, new ClassicLayoutManager(),
					null);
			DynamicReport drCriteria = getBuildTableCriteria(exDS);
			JasperReport jprReportCriteria = DynamicJasperHelper.generateJasperReport(drCriteria,
					new ClassicLayoutManager(), null);

			DynamicReportBuilder drb = new DynamicReportBuilder();
			drb.addConcatenatedReport(jprReportCriteria, "reportCriteria", DJConstants.DATA_SOURCE_ORIGIN_PARAMETER,
					DJConstants.DATA_SOURCE_TYPE_COLLECTION, false).addConcatenatedReport(jprReportData, "reportData",
					DJConstants.DATA_SOURCE_ORIGIN_PARAMETER, DJConstants.DATA_SOURCE_TYPE_COLLECTION, false);			
			// this.appendTitle(drb, exDS.getReportTitle());
			this.appendSubTitle(drb, exDS.getReportSubTitle());
			this.appendPageOptions(drb, exDS.getReportOptions());
			// this.appendTitleImage(drb);

			/*
			 * AutoText name1 = new AutoText(exDS.getReportTitle(),
			 * AutoText.POSITION_HEADER, HorizontalBandAlignment.LEFT);
			 * name1.setStyle(style.extendHeadBlankStyle("title"));
			 * drb.addAutoText(name1);
			 */
			drb.addFirstPageImageBanner("/JasperReport/images/xxxxx.png", new Integer(200), new Integer(200),
					ImageBanner.ALIGN_RIGHT, ImageScaleMode.FILL);

			DynamicReport drPK = drb.build();

			JasperPrint jasperReportCriteria = DynamicJasperHelper.generateJasperPrint(drPK,
					new ClassicLayoutManager(), myMap);
			return jasperReportCriteria;
		} catch (JRException e) {
			LOGGER.error("combineJasperPrint error", e);
		}
		return null;
	}

	public void appendTitle(DynamicReportBuilder drb, String title) {
		drb.setTitle(title).setTitleHeight(new Integer(30)).setSubtitleHeight(new Integer(20))
				.setDetailHeight(new Integer(15)).setTitleStyle(style.extendHeadBlankStyle("title"));
	}

	public void appendSubTitle(DynamicReportBuilder drb, String suTitle) {
		drb.setSubtitle(suTitle).setSubtitleStyle(style.extendHeadBlankStyle("subtitle"));
	}

	public void appendPageOptions(DynamicReportBuilder drb, PageData pageOptions) {
		MarginData margin = pageOptions.getPageMargin();
		drb.setLeftMargin(margin.getTop()).setRightMargin(margin.getRight()).setTopMargin(margin.getTop())
				.setBottomMargin(margin.getBottom())
				// .setColumnsPerPage(new Integer(10))
				.setDetailHeight(15).setWhenNoDataAllSectionNoDetail().setColumnSpace(new Integer(5))
				.setUseFullPageWidth(pageOptions.isPageFluid());
		if ("A4".equals(pageOptions.getPageSize().toUpperCase())) {
			if ("PORTRAIT".equals(pageOptions.getPageOrientation().toUpperCase())) {
				drb.setPageSizeAndOrientation(Page.Page_A4_Portrait()); // Page.Page_A4_Portrait();,
																		// Page_A4_Landscape()
			} else {
				drb.setPageSizeAndOrientation(Page.Page_A4_Landscape()); // Page.Page_A4_Portrait();,
																			// Page_A4_Landscape()
			}
		} else {
			drb.setPageSizeAndOrientation(Page.Page_Letter_Landscape()); // Page.Page_A4_Portrait();,
																			// Page_A4_Landscape()
		}
	}

	public void appendColumns(DynamicReportBuilder drb, Map<String, FieldData> headerDS) {
		for (Entry<String, FieldData> entry : headerDS.entrySet()) {
			try {
				FieldData field = (FieldData) entry.getValue();
				Style cssBody = style.extendBodyBorderSquareStyle("border" + entry.getKey());
				cssBody.setFont(style.getFont(field.getFontSize()));
				cssBody.setHorizontalAlign((HorizontalAlign) style.getStyleDispatcher().get(field.getTextAlignAlias())
						.get(field.getTextAlign()));
				cssBody.setVerticalAlign((VerticalAlign) style.getStyleDispatcher().get(field.getVerticalAlignAlias())
						.get(field.getVerticalAlign()));
				cssBody.setBackgroundColor((Color) style.getStyleDispatcher().get(field.getBackgroundColorAlias())
						.get(field.getBackgroundColor()));
				drb.addStyle(cssBody);

				AbstractColumn column = ColumnBuilder.getNew()
						.setColumnProperty(entry.getKey(), String.class.getName()).setTitle(field.getLabel())
						.setWidth(new Integer(field.getWidth())).setHeaderStyle(style.getHeadBorderSquareStyle())
						.setStyle(cssBody).build();
				drb.addColumn(column);
			} catch (ColumnBuilderException e) {
				LOGGER.error("ColumnBuilderException error", e);
			}
		}
	}

	public void appendColumns(DynamicReportBuilder drb, LinkedHashMap<String, Object> headerDS) {
		for (Entry<String, Object> entry : headerDS.entrySet()) {
			Style cssBody = style.extendHeadBlankStyle("blank" + entry.getKey());
			AbstractColumn column = ColumnBuilder.getNew().setColumnProperty(entry.getKey(), String.class.getName())
					.setWidth(new Integer(15)).setHeaderStyle(style.getHeadBorderSquareStyle()).setStyle(cssBody)
					.build();
			drb.addColumn(column);
		}
	}

	public DynamicReport getBuildTableCriteria(ExampleDataSource exDS) {
		DynamicReportBuilder drb = new DynamicReportBuilder();
		// this.appendTitle(drb,"November 2018 sales report");
		// this.appendSubTitle(drb,
		// "The items in this report correspond to the main products: DVDs, Books, Foods and Magazines");
		// this.appendTitleImage(drb);
		List<Map<String, Object>> criteriaList = exDS.getMetaCriteria();
		if (criteriaList != null) {
			if (criteriaList.size() > 0) {
				this.appendColumns(drb, this.getMetaCriteraiField(criteriaList.get(0)));
			}
		}
		this.appendPageOptions(drb, exDS.getReportOptions());
		drb.setIgnorePagination(false);
		DynamicReport dr = drb.build();
		return dr;
	}

	public DynamicReport getBuildTableData(ExampleDataSource exDS) {
		DynamicReportBuilder drb = new DynamicReportBuilder();
		this.appendColumns(drb, exDS.getMetaHeader());
		this.appendPageOptions(drb, exDS.getReportOptions());
		drb.setIgnorePagination(false);
		DynamicReport dr = drb.build();
		return dr;
	}

	public List<LinkedHashMap<String, Object>> getMetaCriteiraDS(List<Map<String, Object>> metaCriteriaList) {
		List<LinkedHashMap<String, Object>> metaList = new ArrayList<LinkedHashMap<String, Object>>();
		for (Map<String, Object> map : metaCriteriaList) {
			metaList.add(getMetaCriteraiField(map));
		}
		return metaList;
	}

	private LinkedHashMap<String, Object> getMetaCriteraiField(Map<String, Object> map) {
		int indexColumn = 0;
		LinkedHashMap<String, Object> meta = new LinkedHashMap<String, Object>();
		for (Entry<String, Object> entry : map.entrySet()) {
			meta.put("KEY" + indexColumn, entry.getKey());
			meta.put("KEY" + (indexColumn + 1), entry.getValue());
			indexColumn += 2;
		}
		return meta;
	}

	public void appendTitleImage(DynamicReportBuilder drb) {
		// add Banner
		// D:\\HardCoreCode\\workspace-luna\\MyReport\\src\\main\\resources\\images\\jasperreports.png
		// ./images/jasperreports.png
		drb.addImageBanner("/JasperReport/images/xxxxx.png", new Integer(150), new Integer(150),
				ImageBanner.ALIGN_RIGHT);
	}

	

	

	private byte[] getResourceAsStreamByte(String datasourceName) {
		try {
			return Files.readAllBytes(Paths.get("/JasperReport/input/" + datasourceName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void appendColumnsMerge(DynamicReportBuilder drb) {
		// TODO Auto-generated method stub
		/*
		 * drb.addColumn(columnAge); drb.addColumn(columnName); DJGroup g1 = new
		 * GroupBuilder() .setCriteriaColumn((PropertyColumn) columnAge)
		 * .setGroupLayout
		 * (GroupLayout.VALUE_IN_HEADER_WITH_HEADERS_AND_COLUMN_NAME) .build();
		 * drb.addGroup(g1);
		 */
	}

	public static void main(String[] args) {
		new MainReportDynamic().buidReport();
	}

}
