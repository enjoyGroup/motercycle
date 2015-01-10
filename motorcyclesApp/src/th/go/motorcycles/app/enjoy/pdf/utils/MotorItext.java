package th.go.motorcycles.app.enjoy.pdf.utils;

import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

public class MotorItext {
	private static MotorItext instance = null;
	private BaseFont bfComic;

	private Font font6;
	private Font font7;
	private Font font8;
	private Font font9;
	private Font font10;
	private Font fontL;

	private Font font8Bold;
	private Font font9Bold;
	private Font font10Bold;
	private Font font11Bold;
	private Font font12Bold;
	private Font fontLBold;
	private Font fontXLBold;
    
	private Font font10UnderLine;
	private Font font8UnderLine;
	private Font fontSymbol12;
	private Font fontSymbol16;
	private Font fontSymbolL;
	
	public MotorItext( ) {
		String fontName 	= 	PdfConfig.FONTNAME;
		String fontSymbol 	= 	PdfConfig.FONTSYMBOL;
		
		try{
			bfComic 		= 	BaseFont.createFont(fontName, BaseFont.IDENTITY_H,BaseFont.EMBEDDED);
			font6 			= 	new Font(bfComic, 6);
			font7 			= 	new Font(bfComic, 7);
			font8 			= 	new Font(bfComic, 8);
			font8Bold 		= 	new Font(bfComic, 8, Font.BOLD);
			font9 			= 	new Font(bfComic, 9);
			font9Bold 		= 	new Font(bfComic, 9, Font.BOLD);
			font10 			= 	new Font(bfComic, 10);
			font10Bold 		= 	new Font(bfComic, 10, Font.BOLD);
			font11Bold 		= 	new Font(bfComic, 11, Font.BOLD);
			font10UnderLine = 	FontFactory.getFont(fontName, 10,Font.UNDERLINE);
			font8UnderLine = 	FontFactory.getFont(fontName, 8,Font.UNDERLINE);
			font12Bold 		= 	new Font(bfComic, 12, Font.BOLD);
			font12Bold 		= 	new Font(bfComic, 16, Font.BOLD);
			fontL 			= 	new Font(bfComic, 18);
			fontLBold 		= 	new Font(bfComic, 18, Font.BOLD);
			fontXLBold 		= 	new Font(bfComic, 24, Font.BOLD);
	
			BaseFont bfSym 	= 	BaseFont.createFont(fontSymbol,BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			fontSymbol12 	= 	new Font(bfSym, 12);
			fontSymbol16 	= 	new Font(bfSym, 16, Font.BOLD);
			fontSymbolL 	= 	new Font(bfSym, 18);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	// ================================== Cell Management Function	========================================================
	public PdfPCell setCell(String text, Font font, int colSpans, int align) {
		Paragraph paragraph = new Paragraph(text, font);
		return setCell (paragraph,colSpans,align,false) ; 
	}

	public PdfPCell setCell(String text, Font font, int colSpans, int rowSpans, int align) {
		Paragraph paragraph = new Paragraph(text, font);
		return setCell(paragraph, colSpans, rowSpans, align, false) ; 
	}
	
	public PdfPCell setCell(String text, Font font, int colSpans, int align, boolean noWarp) {
		Paragraph paragraph = new Paragraph(text, font);
		return setCell (paragraph,colSpans,align,noWarp) ; 
	}
	
	public PdfPCell setCell(Paragraph paragraph, int colSpans, int align) {
		return setCell (paragraph,colSpans,align,false) ;  
	}
	
	public PdfPCell setCell(Paragraph paragraph, int colSpans, int align, boolean noWarp) {
		PdfPCell cell = new PdfPCell(paragraph);

		cell.setColspan(colSpans);
		cell.setHorizontalAlignment(align);
		cell.setPaddingBottom(5);
		cell.setNoWrap(noWarp);
		return cell;
	}
	
	public PdfPCell setCell(Paragraph paragraph, int colSpans, int rowSpans, int align, boolean noWarp){
		PdfPCell cell = new PdfPCell(paragraph);

		cell.setColspan(colSpans);
		cell.setRowspan(rowSpans);
		cell.setHorizontalAlignment(align);
		cell.setPaddingBottom(5);
		cell.setNoWrap(noWarp);
		return cell;
	}
	
	public PdfPCell setCell(PdfPTable table, int colSpans) {
		PdfPCell cell = new PdfPCell(table);
		cell.setColspan(colSpans);
		return cell;
	}
	
	public PdfPCell setCell(PdfPTable table, int colSpans, int border) {
		PdfPCell cell = new PdfPCell(table);
		cell.setColspan(colSpans);
		if (border == 0) {
			cell.setBorder(border);
		} else {
			cell.setBorder(border + 1);
		}
		return cell;
	}
	
	public PdfPCell setCell(PdfPTable table, int colSpans, int align, boolean noWarp) {
		PdfPCell cell = new PdfPCell(table);

		cell.setColspan(colSpans);
		cell.setHorizontalAlignment(align);
		cell.setPaddingBottom(5);
		cell.setNoWrap(noWarp);
		return cell;
	}
	
	public PdfPCell setCellWithBorder(Phrase myPhrase, int colSpans, int align, int border) {
		PdfPCell cell = new PdfPCell(myPhrase);

		cell.setColspan(colSpans);
		cell.setHorizontalAlignment(align);
		cell.setUseBorderPadding(true);
		// cell.setPaddingBottom(3); //��ͧ��ҧ��ҹ...
		if (border == 0) {
			cell.setBorder(border);
		} else {
			cell.setBorder(border + 1);
		}
		return cell;
	}
	
	
	public PdfPCell setCellWB(Paragraph paragraph, int colSpans, int align, int border) {
		return setCellWB(paragraph,colSpans,align, border ,false,false);
	}
	public PdfPCell setCellWB(String text, Font font, int colSpans,int align, int border) {
		Paragraph p = new Paragraph(text, font);
		return setCellWB(p,colSpans,align, border ,false,false);
	}
	public PdfPCell setCellWB(String text, Font font, int colSpans, int rowSpans, int align, int border) {
		Paragraph p = new Paragraph(text, font);
		return setCellWB(p, colSpans, rowSpans, align, border, false, false);
	}
	public PdfPCell setCellWB(Paragraph paragraph, int colSpans,int align, int border, boolean space) {
		return setCellWB(paragraph,colSpans,align, border, space, false);
	}
	public PdfPCell setCellWB(Paragraph Paragraph, int colSpans,int align, int border, boolean space ,boolean noWrap) {
		PdfPCell cell = new PdfPCell(Paragraph);
		cell.setColspan(colSpans);
		cell.setHorizontalAlignment(align);
		cell.setUseBorderPadding(true);
		cell.setNoWrap(noWrap);
		if (space) {
			cell.setPaddingBottom(5); // ��ͧ��ҧ��ҹ...
		}
		if (border == 0) {
			cell.setBorder(border);
		} else {
			cell.setBorder(border + 1);
		}
		return cell;
	}
	
	public PdfPCell setCellWB(PdfPTable table, int colSpans, int align, int border, boolean space, boolean noWrap) {
		PdfPCell cell = new PdfPCell(table);
		cell.setColspan(colSpans);
		cell.setHorizontalAlignment(align);
		cell.setUseBorderPadding(true);
		cell.setNoWrap(noWrap);
		if (space) {
			cell.setPaddingBottom(5); // ��ͧ��ҧ��ҹ...
		}
		if (border == 0) {
			cell.setBorder(border);
		} else {
			cell.setBorder(border + 1);
		}
		return cell;
	}
	
	public PdfPCell setCellWB(Paragraph Paragraph, int colSpans, int rowSpans, int align, int border, boolean space, boolean noWrap){
		PdfPCell cell = new PdfPCell(Paragraph);
		cell.setColspan(colSpans);
		cell.setRowspan(rowSpans);
		cell.setHorizontalAlignment(align);
		cell.setUseBorderPadding(true);
		cell.setNoWrap(noWrap);
		
		if(space){
			cell.setPaddingBottom(5); // ��ͧ��ҧ��ҹ...
		}
		if(border == 0){
			cell.setBorder(border);
		}else{
			cell.setBorder(border + 1);
		}
		return cell;
	}
	public static MotorItext getInstance() {
		return instance;
	}
	public static void setInstance(MotorItext instance) {
		MotorItext.instance = instance;
	}
	public BaseFont getBfComic() {
		return bfComic;
	}
	public void setBfComic(BaseFont bfComic) {
		this.bfComic = bfComic;
	}
	public Font getFont6() {
		return font6;
	}
	public void setFont6(Font font6) {
		this.font6 = font6;
	}	
	public Font getFont7() {
		return font7;
	}
	public void setFont7(Font font7) {
		this.font7 = font7;
	}
	public Font getFont8() {
		return font8;
	}
	public void setFont8(Font font8) {
		this.font8 = font8;
	}
	public Font getFont9() {
		return font9;
	}
	public void setFont9(Font font9) {
		this.font9 = font9;
	}
	public Font getFont10() {
		return font10;
	}
	public void setFont10(Font font10) {
		this.font10 = font10;
	}
	public Font getFontL() {
		return fontL;
	}
	public void setFontL(Font fontL) {
		this.fontL = fontL;
	}
	public Font getFont8Bold() {
		return font8Bold;
	}
	public void setFont8Bold(Font font8Bold) {
		this.font8Bold = font8Bold;
	}
	public Font getFont9Bold() {
		return font9Bold;
	}
	public void setFont9Bold(Font font9Bold) {
		this.font9Bold = font9Bold;
	}
	public Font getFont10Bold() {
		return font10Bold;
	}
	public void setFont10Bold(Font font10Bold) {
		this.font10Bold = font10Bold;
	}
	public Font getFont11Bold() {
		return font11Bold;
	}
	public void setFont11Bold(Font font11Bold) {
		this.font11Bold = font11Bold;
	}
	public Font getFont12Bold() {
		return font12Bold;
	}
	public void setFont12Bold(Font font12Bold) {
		this.font12Bold = font12Bold;
	}
	public Font getFontLBold() {
		return fontLBold;
	}
	public void setFontLBold(Font fontLBold) {
		this.fontLBold = fontLBold;
	}
	public Font getFontXLBold() {
		return fontXLBold;
	}
	public void setFontXLBold(Font fontXLBold) {
		this.fontXLBold = fontXLBold;
	}
	public Font getFont10UnderLine() {
		return font10UnderLine;
	}
	public void setFont10UnderLine(Font font10UnderLine) {
		this.font10UnderLine = font10UnderLine;
	}
	public Font getFont8UnderLine() {
		return font8UnderLine;
	}
	public void setFont8UnderLine(Font font8UnderLine) {
		this.font8UnderLine = font8UnderLine;
	}
	public Font getFontSymbol12() {
		return fontSymbol12;
	}
	public void setFontSymbol12(Font fontSymbol12) {
		this.fontSymbol12 = fontSymbol12;
	}
	public Font getFontSymbol16() {
		return fontSymbol16;
	}
	public void setFontSymbol16(Font fontSymbol16) {
		this.fontSymbol16 = fontSymbol16;
	}
	public Font getFontSymbolL() {
		return fontSymbolL;
	}
	public void setFontSymbolL(Font fontSymbolL) {
		this.fontSymbolL = fontSymbolL;
	}
}
