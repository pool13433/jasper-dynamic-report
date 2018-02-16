package com.poolsawat.special.example;

import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.grid;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;
import static net.sf.dynamicreports.report.builder.DynamicReports.stl;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.Date;

import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.grid.ColumnTitleGroupBuilder;
import net.sf.dynamicreports.report.builder.style.ConditionalStyleBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

import com.ibm.icu.text.SimpleDateFormat;
import com.poolsawat.special.util.Templates;

public class ColumnTitleGroupReport {
	public ColumnTitleGroupReport() {
		build();
	}

	private void build() {
		ConditionalStyleBuilder evenCond = stl.conditionalStyle(new EvenConditionExpression())
		    	.setBackgroundColor(new Color(255, 210, 210));
		
		StyleBuilder evenStyle = stl.style()
		    	.conditionalStyles(evenCond);		
		
		TextColumnBuilder<String> itemPcName = col.column("PcName", "PcName", type.stringType()).setStyle(evenStyle);
		TextColumnBuilder<String> itemMouseName = col.column("MouseName", "MouseName", type.stringType()).setStyle(evenStyle);
		TextColumnBuilder<String> itemKeyBoardName = col.column("KeyBoardName", "KeyBoardName", type.stringType());
		TextColumnBuilder<String> itemMonitorName = col.column("MonitorName", "MonitorName", type.stringType()).setStyle(evenStyle);
		TextColumnBuilder<BigDecimal> itemPrice = col.column("Price", "Price", type.bigDecimalType()).setStyle(evenStyle);
		TextColumnBuilder<Integer> itemUnit = col.column("Unit", "Unit", type.integerType()).setStyle(evenStyle);
		TextColumnBuilder<Integer> itemSalary = col.column("Salary", "Salary", type.integerType()).setStyle(evenStyle);
		TextColumnBuilder<String> itemYears = col.column("Years", new SimpleExpression()).setStyle(evenStyle);

		ColumnTitleGroupBuilder titleLevel2 = grid.titleGroup("Level 2", itemPcName, itemMouseName);
		ColumnTitleGroupBuilder titleLevel31 = grid.titleGroup("Level 31", itemKeyBoardName, itemMonitorName);
		ColumnTitleGroupBuilder titleLevel32 = grid.titleGroup("Level 32", itemSalary, itemYears);
		ColumnTitleGroupBuilder titleLevel33 = grid.titleGroup("Level 33", titleLevel31, titleLevel32);
		
		try {
			report().setTemplate(Templates.reportTemplate)
					.columnGrid(titleLevel2, titleLevel33, itemPrice, itemUnit)
					.columns(itemPcName, itemMouseName, itemKeyBoardName, itemMonitorName, itemSalary, itemYears,
							itemPrice, itemUnit)
					.title(Templates.createTitleComponent("ColumnTitleGroup"))
					.pageFooter(Templates.footerComponent)
					.setDataSource(createDataSource())
					.show()
					/*.toPdf(new FileOutputStream("/JasperReport/output/test_report"
							+ new SimpleDateFormat("yyyyMMdd_Hms").format(new Date()) + ".pdf"))*/
					.toXlsx(new FileOutputStream("/JasperReport/output/test_report"
							+ new SimpleDateFormat("yyyyMMdd_Hms").format(new Date()) + ".xlsx"))		
							;
		} catch (DRException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private class SimpleExpression extends AbstractSimpleExpression<String> {
		private static final long serialVersionUID = 1L;

		@Override
		public String evaluate(ReportParameters reportParameters) {
			//Integer quantity = reportParameters.getValue("quantity");
			//BigDecimal unitPrice = reportParameters.getValue("unitprice");			
			return "express";
		}
	}
	
	private class EvenConditionExpression extends AbstractSimpleExpression<Boolean> {
		private static final long serialVersionUID = 1L;

		@Override
		public Boolean evaluate(ReportParameters reportParameters) {
			int recordIndex = reportParameters.getReportRowNumber();
			return (recordIndex % 2 == 0);
		}
	}

	private JRDataSource createDataSource() {
		DRDataSource dataSource = new DRDataSource("PcName", "MouseName", "KeyBoardName", "MonitorName", "Price",
				"Unit", "Salary", "Years");
		dataSource.add("Acer", "OKER", "Asus", "DEll", new BigDecimal(500), 9, new Integer(999), "2018");
		dataSource.add("Acer", "OKER", "Asus", "DEll", new BigDecimal(500), 9, new Integer(8888), "2019");
		dataSource.add("Acer", "OKER", "Asus", "DEll", new BigDecimal(500), 9, new Integer(8888), "2019");
		dataSource.add("Acer", "OKER", "Asus", "DEll", new BigDecimal(500), 9, new Integer(8888), "2019");
		dataSource.add("Acer", "OKER", "Asus", "DEll", new BigDecimal(500), 9, new Integer(8888), "2019");
		dataSource.add("Acer", "OKER", "Asus", "DEll", new BigDecimal(500), 9, new Integer(8888), "2019");
		dataSource.add("Acer", "OKER", "Asus", "DEll", new BigDecimal(500), 9, new Integer(8888), "2019");
		return dataSource;
	}

	public static void main(String[] args) {
		new ColumnTitleGroupReport();
	}
}
