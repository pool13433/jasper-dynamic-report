package com.poolsawat.special.export;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.builder.ReportTemplateBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;
import net.sf.dynamicreports.report.builder.component.ImageBuilder;
import net.sf.dynamicreports.report.builder.crosstab.CrosstabColumnGroupBuilder;
import net.sf.dynamicreports.report.builder.tableofcontents.TableOfContentsCustomizerBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.jasperreports.engine.JRDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.icu.text.SimpleDateFormat;
import com.poolsawat.special.util.CMPStyle;
import com.poolsawat.special.util.Templates;

public class MainJasperDynamic {

	static private Logger LOGGER = LoggerFactory.getLogger(MainJasperDynamic.class);

	public static void main(String[] args) {

		String fileName = new SimpleDateFormat("yyyyMMdd_Hms").format(new Date());

		new MainJasperDynamic().buildDynamicReportPDF(fileName);
	}

	public static ComponentBuilder<?, ?> addComponentTitle() {
		// https://findusages.com/search/net.sf.dynamicreports.jasper.builder.JasperReportBuilder/title$1
		return cmp
				.horizontalList()
				.add(addComponentTitleImage(),
						cmp.text("xxxxxxxxxxx").setStyle(CMPStyle.bold18CenteredStyle)
								.setHorizontalAlignment(HorizontalAlignment.RIGHT)).newRow().add(cmp.line()).newRow()
				.add(cmp.verticalGap(10));
	}

	
	public static ComponentBuilder<?, ?> addComponentTitleImage(){		
		try {
			InputStream img = MainJasperDynamic.class.getResourceAsStream("/images/jasperreports.png");			
			
			return  cmp.horizontalList().add(
					  cmp.image(img).setFixedDimension(80, 80),
			          cmp.text("yyyyyyyyyyyyyyyyyyyyyyyyy")
			          //.setStyle(titleStyle)
			          .setHorizontalAlignment(HorizontalAlignment.LEFT),
			          cmp.text("xxxxxxxxxxxxxxxxxx")
			          //.setStyle(title2Style)
			          .setHorizontalAlignment(HorizontalAlignment.RIGHT))
			          .newRow(10).add(cmp.filler().setStyle(stl.style().setTopBorder(stl.pen2Point())).setFixedHeight(10));
		} catch (Exception e) {
			LOGGER.error("addComponentTitleImage error", e);
		}
		return null;
	}

	public static ReportTemplateBuilder addComponentTemplate() {
		TableOfContentsCustomizerBuilder tableOfContentsCustomizer = tableOfContentsCustomizer().setHeadingStyle(0,
				stl.style(CMPStyle.rootStyle).bold());

		return template().setLocale(Locale.ENGLISH).setColumnStyle(CMPStyle.columnStyle)
				.setColumnTitleStyle(CMPStyle.columnTitleStyle).setGroupStyle(CMPStyle.groupStyle)
				.setGroupTitleStyle(CMPStyle.groupStyle).setSubtotalStyle(CMPStyle.subtotalStyle)
				.highlightDetailEvenRows().crosstabHighlightEvenRows()
				.setCrosstabGroupStyle(CMPStyle.crosstabGroupStyle)
				.setCrosstabGroupTotalStyle(CMPStyle.crosstabGroupTotalStyle)
				.setCrosstabGrandTotalStyle(CMPStyle.crosstabGrandTotalStyle)
				.setCrosstabCellStyle(CMPStyle.crosstabCellStyle)
				.setTableOfContentsCustomizer(tableOfContentsCustomizer);
	}

	private void buildDynamicReportPDF(String fileName) {
		try {
			ImageBuilder image = cmp.image(new ImageExpression()).setFixedDimension(48, 48);
			HorizontalListBuilder itemComponent = cmp.horizontalList(image, cmp.verticalList(
					cmp.text(new ItemExpression()), bcode.ean128(new BarcodeExpression()).setFixedHeight(24)));
			
			CrosstabColumnGroupBuilder<Integer> columnYearGroup = ctab.columnGroup(new YearExpression());
			
			report().setTemplate(addComponentTemplate())
					.fields(field("image", String.class), 
							field("barcode", String.class))
					/*.columns(col.componentColumn("Image", image), col.column("Item", "item", type.stringType()),
							col.componentColumn("Item", itemComponent))*/					
					.title(addComponentTitle())
					.pageFooter(cmp.pageXofY().setStyle(stl.style(CMPStyle.boldCenteredStyle).setTopBorder(stl.pen1Point())))
					.setDataSource(createDataSource())
					.show();
					//.toPdf(new FileOutputStream("/JasperReport/output/test_report" + fileName + ".pdf"));
		} catch (Exception e) {
			LOGGER.error("FileNotFoundException error", e);
		} 
	}
	
	private class YearExpression extends AbstractSimpleExpression<Integer> {
		private static final long serialVersionUID = 1L;

		@Override
		public Integer evaluate(ReportParameters reportParameters) {
			Calendar c = Calendar.getInstance();
			c.setTime((Date) reportParameters.getValue("orderdate"));
			return c.get(Calendar.YEAR);
		}
	}

	private JRDataSource createDataSource() {
		DRDataSource dataSource = new DRDataSource("item", "image", "barcode");
		dataSource.add("PDA", "pda", "100264832717658");
		dataSource.add("Camera", "camera", "100364875790352");
		dataSource.add("Camera", "camera", "100764935316351");
		dataSource.add("USB", "usb", "100864565780343");
		dataSource.add("PDA", "pda", "100264865712551");
		dataSource.add("USB", "usb", "100268834723431");
		return dataSource;
	}

	public class ImageExpression extends AbstractSimpleExpression<InputStream> {
		private static final long serialVersionUID = 1L;

		public InputStream evaluate(ReportParameters reportParameters) {
			return Templates.class.getResourceAsStream("images/" + reportParameters.getValue("image") + ".png");
		}
	}

	public class ItemExpression extends AbstractSimpleExpression<String> {
		private static final long serialVersionUID = 1L;

		public String evaluate(ReportParameters reportParameters) {
			return reportParameters.getValue("item");
		}
	}

	public class BarcodeExpression extends AbstractSimpleExpression<String> {
		private static final long serialVersionUID = 1L;

		public String evaluate(ReportParameters reportParameters) {
			return reportParameters.getValue("barcode");
		}
	}
}
