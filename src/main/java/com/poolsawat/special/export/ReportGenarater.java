package com.poolsawat.special.export;

import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReportGenarater {

	static private Logger LOGGER = LoggerFactory.getLogger(ReportGenarater.class);
	
	public ReportGenarater(){
	}

	public void sourceFormatPDF(JasperPrint inJasperPrint,String outJasperPDF) {
		try {
			JRPdfExporter exporter = new JRPdfExporter();

			exporter.setExporterInput(new SimpleExporterInput(inJasperPrint));			
			//exporter.setExporterInput(SimpleExporterInput.getInstance(inJasperPrintList));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outJasperPDF));						
			SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
			//reportConfig.setSizePageToContent(true);
			//reportConfig.setForceLineBreakPolicy(false);	
			
			SimplePdfExporterConfiguration exportConfig = new SimplePdfExporterConfiguration();
			exportConfig.setMetadataAuthor("baeldung");
			exportConfig.setEncrypted(true);
			exportConfig.setAllowedPermissionsHint("PRINTING");					
			
			exporter.setConfiguration(reportConfig);
			exporter.setConfiguration(exportConfig);

			exporter.exportReport();
		} catch (JRException e) {
			LOGGER.error("sourceFormatPDF error :: {e}", e);
		}
	}

	public void sourceFormatXLS(JasperPrint inJasperPrint,String outJasperPDF) {
		try {
			JRXlsxExporter exporter = new JRXlsxExporter();
			exporter.setExporterInput(new SimpleExporterInput(inJasperPrint));
			//exporter.setExporterInput(SimpleExporterInput.getInstance(inJasperPrintList));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outJasperPDF));
			// Set input and output ...
			SimpleXlsxReportConfiguration reportConfig = new SimpleXlsxReportConfiguration();
			reportConfig.setSheetNames(new String[] { "report" });
			/*reportConfig.setOnePagePerSheet(true);
			reportConfig.setDetectCellType(true);
			reportConfig.setCollapseRowSpan(false);*/
			reportConfig.setWhitePageBackground(false);
			exporter.setConfiguration(reportConfig);
			exporter.exportReport();
		} catch (Exception e) {
			LOGGER.error("sourceFormatXLS error :: {e}", e);
		}
	}
}
