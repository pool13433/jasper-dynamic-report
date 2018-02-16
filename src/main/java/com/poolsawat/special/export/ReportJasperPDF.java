package com.poolsawat.special.export;

import static net.sf.dynamicreports.report.builder.DynamicReports.export;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;
import net.sf.dynamicreports.jasper.builder.export.JasperPdfExporterBuilder;
import net.sf.dynamicreports.report.builder.column.ColumnBuilder;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.poolsawat.special.util.Templates;

public class ReportJasperPDF {

	static private Logger LOGGER = LoggerFactory.getLogger(ReportJasperPDF.class);
	
	private ColumnBuilder<?, ?> columns;
	private JRDataSource datasource;
	
	public ReportJasperPDF(ColumnBuilder<?, ?> columns,JRDataSource datasource){
		this.columns = columns;
		this.datasource = datasource;
	}
	
	private void build() {
		try {
			JasperPdfExporterBuilder pdfExporter = export.pdfExporter("/JasperReport/output/report.pdf").setEncrypted(true)
					.setUserPassword("1234");

			report().setTemplate(Templates.reportTemplate)
					/*.columns(col.column("Item", "item", type.stringType()),
							col.column("Quantity", "quantity", type.integerType()),
							col.column("Unit price", "unitprice", type.bigDecimalType()))*/
					.columns(this.columns)
					.title(Templates.createTitleComponent("EncryptedPdfReport")).pageFooter(Templates.footerComponent)
					//.setDataSource(createDataSource())
					.setDataSource(this.datasource)
					.toPdf(pdfExporter);
		} catch (DRException e) {
			e.printStackTrace();
		}
	}

	

}
