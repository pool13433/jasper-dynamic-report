package com.poolsawat.special.export;

import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.export;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;

import java.math.BigDecimal;
import java.util.Date;

import net.sf.dynamicreports.jasper.builder.export.JasperXlsExporterBuilder;
import net.sf.dynamicreports.jasper.constant.JasperProperty;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.poolsawat.special.util.Templates;

public class ReportJasterXLS {

	static private Logger LOGGER = LoggerFactory.getLogger(ReportJasterXLS.class);

	public ReportJasterXLS() {	
		buildXLS();
	}

	public void buildXLS() {
		try {
			JasperXlsExporterBuilder xlsExporter = export.xlsExporter("/JasperReport/output/report.xls").setDetectCellType(true)
					.setIgnorePageMargins(true).setWhitePageBackground(false).setRemoveEmptySpaceBetweenColumns(true);

			report().setColumnTitleStyle(Templates.columnTitleStyle)
					.addProperty(JasperProperty.EXPORT_XLS_FREEZE_ROW, "2")
					.ignorePageWidth()
					.ignorePagination()
					.columns(col.column("Item", "item", type.stringType()),
							col.column("Quantity", "quantity", type.integerType()),
							col.column("Unit price", "unitprice", type.bigDecimalType()))
					.setDataSource(createDataSourceXLS()).toXls(xlsExporter);
		} catch (DRException e) {
			LOGGER.error("build error", e);
		}
	}

	private JRDataSource createDataSource() {
		DRDataSource dataSource = new DRDataSource("item", "orderdate", "quantity", "unitprice");
		dataSource.add("Notebook", new Date(), 1, new BigDecimal(500));
		return dataSource;
	}

	private JRDataSource createDataSourceXLS() {
		DRDataSource dataSource = new DRDataSource("item", "quantity", "unitprice");
		for (int i = 0; i < 50; i++) {
			dataSource.add("Book", (int) (Math.random() * 10) + 1, new BigDecimal(Math.random() * 100 + 1));
		}
		return dataSource;
	}

	public static void main(String[] args) {
		new ReportJasterXLS();
	}

}
