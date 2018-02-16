package com.poolsawat.special.example;

import java.math.BigDecimal;
import java.util.Date;
import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import net.sf.dynamicreports.report.builder.FieldBuilder;
import net.sf.dynamicreports.report.builder.component.TextFieldBuilder;
import net.sf.dynamicreports.report.builder.component.VerticalListBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.ListType;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

import com.poolsawat.special.util.Templates;

public class FlowColumnPairsReport {
	public FlowColumnPairsReport() {
		build();
	}

	private void build() {
		StyleBuilder textStyle = stl.style(Templates.columnStyle).setBorder(stl.pen1Point());

		FieldBuilder<Integer> idField = field("id", type.integerType());
		FieldBuilder<String> itemField = field("item", type.stringType());
		FieldBuilder<Integer> quantityField = field("quantity", type.integerType());
		FieldBuilder<BigDecimal> unitPriceField = field("unitprice", type.bigDecimalType());
		FieldBuilder<Date> orderDateField = field("orderdate", type.dateType());
		FieldBuilder<Date> orderYearField = field("orderdate", type.dateYearType());
		FieldBuilder<Date> orderMonthField = field("orderdate", type.dateMonthType());
		FieldBuilder<Date> orderDayField = field("orderdate", type.dateDayType());

		try {
			report().setTemplate(Templates.reportTemplate)
					.setColumnStyle(textStyle)
					.columnGrid(ListType.HORIZONTAL_FLOW)
					.fields(idField, itemField, quantityField, unitPriceField, orderDateField, orderYearField,
							orderMonthField, orderDayField)
					.columns(col.componentColumn(columnPair("Id", idField)),
							col.componentColumn(columnPair("Item", itemField).setFixedWidth(400)),
							col.componentColumn(columnPair("Quantity", quantityField)),
							col.componentColumn(columnPair("Unit price", unitPriceField)),
							col.componentColumn(columnPair("Order date", orderDateField)),
							col.componentColumn(columnPair("Order year", orderYearField)),
							col.componentColumn(columnPair("Order month", orderMonthField)),
							col.componentColumn(columnPair("Order day", orderDayField)))
					.title(Templates.createTitleComponent("FlowColumnPairs")).detailFooter(cmp.verticalGap(20))
					.pageFooter(Templates.footerComponent).setDataSource(createDataSource()).show();
		} catch (DRException e) {
			e.printStackTrace();
		}
	}

	private VerticalListBuilder columnPair(String title, FieldBuilder<?> value) {
		TextFieldBuilder<String> titleCmp = cmp.text(title).setStyle(Templates.columnTitleStyle);
		TextFieldBuilder<?> valueCmp = cmp.text(value);
		return cmp.verticalList(titleCmp, valueCmp);
	}

	private JRDataSource createDataSource() {
		DRDataSource dataSource = new DRDataSource("id", "item", "orderdate", "quantity", "unitprice");
		dataSource.add(5, "Notebook", new Date(), 1, new BigDecimal(500));
		dataSource.add(8, "Book", new Date(), 7, new BigDecimal(300));
		dataSource.add(15, "PDA", new Date(), 2, new BigDecimal(250));
		return dataSource;
	}

	public static void main(String[] args) {
		new FlowColumnPairsReport();
	}
}
