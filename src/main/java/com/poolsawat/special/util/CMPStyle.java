package com.poolsawat.special.util;

import static net.sf.dynamicreports.report.builder.DynamicReports.stl;

import java.awt.Color;

import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.VerticalAlignment;

public class CMPStyle {
	public static final StyleBuilder rootStyle;
	public static final StyleBuilder boldStyle;
	public static final StyleBuilder italicStyle;
	public static final StyleBuilder boldCenteredStyle;
	public static final StyleBuilder bold12CenteredStyle;
	public static final StyleBuilder bold18CenteredStyle;
	public static final StyleBuilder bold22CenteredStyle;
	public static final StyleBuilder columnStyle;
	public static final StyleBuilder columnTitleStyle;
	public static final StyleBuilder groupStyle;
	public static final StyleBuilder subtotalStyle;
	
	public static final StyleBuilder crosstabGroupStyle;
	public static final StyleBuilder crosstabGroupTotalStyle;
	public static final StyleBuilder crosstabGrandTotalStyle;
	public static final StyleBuilder crosstabCellStyle;
	
	static{
		rootStyle = stl.style().setPadding(2);
		boldStyle = stl.style(rootStyle).bold();
		italicStyle = stl.style(rootStyle).italic();
		boldCenteredStyle = stl.style(boldStyle).setAlignment(HorizontalAlignment.CENTER, VerticalAlignment.MIDDLE);
		bold12CenteredStyle = stl.style(boldCenteredStyle).setFontSize(12);
		bold18CenteredStyle = stl.style(boldCenteredStyle).setFontSize(18);
		bold22CenteredStyle = stl.style(boldCenteredStyle).setFontSize(22);
		columnStyle = stl.style(rootStyle).setVerticalAlignment(VerticalAlignment.MIDDLE);
		columnTitleStyle = stl.style(columnStyle).setBorder(stl.pen1Point())
				.setHorizontalAlignment(HorizontalAlignment.CENTER).setBackgroundColor(Color.LIGHT_GRAY).bold();
		groupStyle = stl.style(boldStyle).setHorizontalAlignment(HorizontalAlignment.LEFT);
		subtotalStyle = stl.style(boldStyle).setTopBorder(stl.pen1Point());
		
		crosstabGroupStyle = stl.style(columnTitleStyle);
		crosstabGroupTotalStyle = stl.style(columnTitleStyle).setBackgroundColor(new Color(170, 170, 170));
		crosstabGrandTotalStyle = stl.style(columnTitleStyle).setBackgroundColor(new Color(140, 140, 140));
		crosstabCellStyle = stl.style(columnStyle).setBorder(stl.pen1Point());
		
	}
}
