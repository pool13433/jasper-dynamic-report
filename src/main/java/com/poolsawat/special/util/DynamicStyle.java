package com.poolsawat.special.util;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;

public class DynamicStyle {

	private final String FONT_THSARABUN_TTF = "/fonts/THSARABUN.TTF";
	private final String FONT_THSARABUN_ALIAS = "TH SarabunPSK";
	private final String COLOR_BLUE_LIGHT = "#90CAF9";
	
	//https://en.m.wikipedia.org/wiki/List_of_Crayola_crayon_colors
	
	public Font getFont(int size) {
		Font font = new Font(size, FONT_THSARABUN_ALIAS, FONT_THSARABUN_TTF,
				Font.PDF_ENCODING_Identity_H_Unicode_with_horizontal_writing, true);
		return font;
	}

	public Style getAmountStyle() {
		Style amountStyle = new Style("amount");
		amountStyle.setTextColor(Color.BLACK);
		amountStyle.setHorizontalAlign(HorizontalAlign.RIGHT);
		amountStyle.setVerticalAlign(VerticalAlign.MIDDLE);
		amountStyle.setFont(getFont(14));
		amountStyle.setPaddingRight(5);
		amountStyle.setTransparency(Transparency.OPAQUE);
		amountStyle.setBorderRight(Border.THIN());
		amountStyle.setBorderBottom(Border.THIN());
		return amountStyle;
	}

	public Style getDetailAmountStyle() {
		Style detailAmountStyle = new Style("detailAmount");
		detailAmountStyle.setBorderLeft(Border.THIN());
		detailAmountStyle.setBorderRight(Border.THIN());
		detailAmountStyle.setTextColor(Color.blue);
		detailAmountStyle.setHorizontalAlign(HorizontalAlign.RIGHT);
		detailAmountStyle.setFont(getFont(14));
		detailAmountStyle.setPaddingRight(2);
		detailAmountStyle.setTransparency(Transparency.OPAQUE);
		detailAmountStyle.setBorderBottom(Border.THIN());
		return detailAmountStyle;
	}

	public Style getBudgetReportDetailAmountStyle() {
		Style detailAmountStyle = new Style("detailAmount");
		detailAmountStyle.setBorderLeft(Border.THIN());
		detailAmountStyle.setBorderRight(Border.THIN());
		detailAmountStyle.setBorderTop(Border.THIN());
		detailAmountStyle.setBorderBottom(Border.THIN());
		detailAmountStyle.setTextColor(Color.black);
		detailAmountStyle.setHorizontalAlign(HorizontalAlign.RIGHT);
		detailAmountStyle.setFont(new Font(8, Font._FONT_ARIAL, true));
		detailAmountStyle.setTransparency(Transparency.OPAQUE);
		return detailAmountStyle;
	}

	public Style getHeaderStyle1() {
		Style headerStyle = new Style("header");
		headerStyle.setFont(new Font(12, Font._FONT_ARIAL, true, false, false));
		headerStyle.setBorder(Border.THIN());
		headerStyle.setTextColor(Color.BLACK);
		headerStyle.setHorizontalAlign(HorizontalAlign.CENTER);
		headerStyle.setVerticalAlign(VerticalAlign.MIDDLE);
		headerStyle.setTransparency(Transparency.OPAQUE);
		headerStyle.setStretchWithOverflow(true);
		headerStyle.setPaddingLeft(5);
		return headerStyle;
	}

	public Style getHeaderStyle2() {
		Style headerStyle = new Style("header");
		headerStyle.setFont(Font.ARIAL_MEDIUM_BOLD);
		headerStyle.setBorder(Border.THIN());
		headerStyle.setBackgroundColor(new Color(204, 204, 204));
		headerStyle.setTextColor(Color.blue);
		headerStyle.setHorizontalAlign(HorizontalAlign.CENTER);
		headerStyle.setVerticalAlign(VerticalAlign.MIDDLE);
		headerStyle.setTransparency(Transparency.OPAQUE);
		headerStyle.setFont(new Font(8, Font._FONT_ARIAL, true));
		headerStyle.setStretchWithOverflow(true);
		return headerStyle;
	}

	public Style getHeadBorderSquareStyle() {
		Style squareStyle = new Style("headerBorderSquare");
		squareStyle.setTextColor(Color.BLACK);
		squareStyle.setTransparency(Transparency.OPAQUE);
		
		squareStyle.setPaddingTop(5);
		squareStyle.setPaddingRight(5);
		squareStyle.setPaddingBottom(5);
		squareStyle.setPaddingLeft(5);

		squareStyle.setHorizontalAlign(HorizontalAlign.CENTER);
		squareStyle.setVerticalAlign(VerticalAlign.MIDDLE);
		
		squareStyle.setFont(getFont(16));
		
		squareStyle.setBorderRight(Border.THIN());
		squareStyle.setBorderTop(Border.THIN());
		squareStyle.setBorderBottom(Border.THIN());
		squareStyle.setBorderLeft(Border.THIN());
		squareStyle.setBackgroundColor(Color.pink); //Color.getHSBColor(346,96,63)

		return squareStyle;
	}
	
	public Style extendHeadBlankStyle(String styleAlias) {
		Style squareStyle = new Style(styleAlias);
		squareStyle.setTextColor(Color.BLACK);
		squareStyle.setTransparency(Transparency.OPAQUE);
		
		squareStyle.setPaddingTop(5);
		squareStyle.setPaddingRight(5);
		squareStyle.setPaddingBottom(5);
		squareStyle.setPaddingLeft(5);

		squareStyle.setHorizontalAlign(HorizontalAlign.LEFT);
		squareStyle.setVerticalAlign(VerticalAlign.MIDDLE);		
		squareStyle.setFont(getFont(16));				
		return squareStyle;
	}
	
	public Style extendBodyBorderSquareStyle(String styleAlias) {
		Style squareStyle = new Style(styleAlias);
		squareStyle.setTransparency(Transparency.OPAQUE);
		
		squareStyle.setPaddingTop(5);
		squareStyle.setPaddingRight(5);
		squareStyle.setPaddingBottom(5);
		squareStyle.setPaddingLeft(5);

		//squareStyle.setHorizontalAlign(HorizontalAlign.CENTER);
		//squareStyle.setVerticalAlign(VerticalAlign.MIDDLE);
		
		//squareStyle.setFont(getFont(16));
		
		squareStyle.setBorderRight(Border.THIN());
		squareStyle.setBorderTop(Border.THIN());
		squareStyle.setBorderBottom(Border.THIN());
		squareStyle.setBorderLeft(Border.THIN());
		//squareStyle.setBackgroundColor(Color.gray); //Color.getHSBColor(346,96,63)

		return squareStyle;
	}
	
	public Map<String,Map<String,Object>> getStyleDispatcher(){
		Map<String,Map<String,Object>> mapStyle = new HashMap<String, Map<String,Object>>();
		
		Map<String,Object> mapTextAlign = new HashMap<String, Object>();
		mapTextAlign.put("center", HorizontalAlign.CENTER);
		mapTextAlign.put("justify", HorizontalAlign.JUSTIFY);
		mapTextAlign.put("left", HorizontalAlign.LEFT);
		mapTextAlign.put("right", HorizontalAlign.RIGHT);
		mapStyle.put("text-align", mapTextAlign);
		
		Map<String,Object> mapVerticalAlign = new HashMap<String, Object>();
		mapVerticalAlign.put("top", VerticalAlign.TOP);
		mapVerticalAlign.put("justified", VerticalAlign.JUSTIFIED);
		mapVerticalAlign.put("middle", VerticalAlign.MIDDLE);
		mapVerticalAlign.put("bottom", VerticalAlign.BOTTOM);
		mapStyle.put("vertical-align", mapTextAlign);
		
		Map<String,Object> mapBackgroundColor = new HashMap<String, Object>();
		mapBackgroundColor.put("red", Color.RED);
		mapBackgroundColor.put("black", Color.BLACK);
		mapBackgroundColor.put("blue", Color.BLUE);
		mapBackgroundColor.put("green", Color.GREEN);
		mapBackgroundColor.put("pink", Color.PINK);
		mapBackgroundColor.put("cyan", Color.CYAN);
		mapBackgroundColor.put("gray", Color.GRAY);
		mapBackgroundColor.put("orange", Color.ORANGE);
		mapBackgroundColor.put("white", Color.WHITE);
		mapBackgroundColor.put("yellow", Color.YELLOW);
		mapStyle.put("background-color", mapBackgroundColor);
		return mapStyle;
	}

}
