package th.go.motorcycles.app.enjoy.pdf;

import java.io.IOException;
import java.net.MalformedURLException;

import org.json.simple.JSONObject;

import th.go.motorcycles.app.enjoy.pdf.utils.MotorItext;
import th.go.motorcycles.app.enjoy.pdf.utils.PdfFormService;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class SlipPdfForm extends MotorItext implements PdfFormService {
	
	private PdfWriter 	writer;
	private JSONObject 	formDataObj;
	
	private void setWriter(PdfWriter writer) {
		this.writer = writer;
	}

	public void setJSONObject(PdfWriter writer, JSONObject jsonObject) {
		this.formDataObj  	= jsonObject;
		setWriter(writer);
		
	}
	
	public Document createForm(Document document) {
		System.out.println("[SlipPdfForm][createForm][Begin]");
		JSONObject 	jsonObjectMain    = this.formDataObj;
		String      invoiceId		  = (String) jsonObjectMain.get("invoiceId");
		String      flagAddSales	  = (String) jsonObjectMain.get("flagAddSales");
		String      invoiceIdAddSales = (String) jsonObjectMain.get("invoiceIdAddSales");
		String      flagCredit		  = (String) jsonObjectMain.get("flagCredit");
		try{
			// ใบกำกับภาษี
			document.add(this.genHeader());
			document.add(this.brLine());
//			document.add(this.brLine());
			document.add(this.genDetail(invoiceId));
			document.add(this.brLine());
			document.add(this.brLine());
			document.add(this.genProduct());
			document.add(this.brLine());
			document.add(this.brLine());
			document.add(this.genTotalCost());
			document.add(this.brLine());
			document.add(this.brLine());
			document.add(this.brLine());
			document.add(this.genFooter());
			
			// ใบส่งเสริมการขาย
			if (flagAddSales.equals("Y")) {
				document.newPage();
				document.add(this.genHeader());
				document.add(this.brLine());
				document.add(this.brLine());
				document.add(this.genDetail(invoiceIdAddSales));
				document.add(this.brLine());
				document.add(this.brLine());
				document.add(this.genProductAddSales());
				document.add(this.brLine());
				document.add(this.brLine());
				document.add(this.genTotalCostAddSales());
				document.add(this.brLine());
				document.add(this.brLine());
				document.add(this.brLine());
				document.add(this.genFooter());
			}	
			
			// ใบเพิ่มหนี้/ลดหนี้
			if ((flagCredit.equals("A")) || (flagCredit.equals("C"))) {
				document.newPage();
				document.add(this.genHeaderCredit());
				document.add(this.brLine());
				document.add(this.brLine());
				document.add(this.genDetail(invoiceId));
				document.add(this.brLine());
				document.add(this.brLine());
				document.add(this.genProduct());
				document.add(this.brLine());
				document.add(this.brLine());
				document.add(this.genTotalCostCredit());
				document.add(this.brLine());
				document.add(this.brLine());
				document.add(this.brLine());
				document.add(this.genFooter());
			}
			
		}
		catch(DocumentException de){
			de.printStackTrace();
		}catch(MalformedURLException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		finally{
			System.out.println("[SlipPdfForm][createForm][End]");
		}
		
		return document;
	}
	
	private PdfPTable genHeader() throws DocumentException, MalformedURLException, IOException {
		
		float[] 	widths	 		= {60f, 40f};
		PdfPTable 	table 			= new PdfPTable(widths);
		
		float[] 	subW	 		= {24f, 36f};
		PdfPTable 	subTab 			= new PdfPTable(subW);
		JSONObject 	jsonObjectMain  = this.formDataObj;
		String      companyName		= (String) jsonObjectMain.get("CompanyName");
		String      companyAddress	= (String) jsonObjectMain.get("CompanyAddress");
		
		table.addCell(setCellWB(companyName, getFont12Bold(), 2, Element.ALIGN_LEFT, 0));
		
		table.addCell(setCellWB(companyAddress, getFont8(), 1, Element.ALIGN_LEFT, 0));
		table.addCell(setCellWB("		ใบกำกับภาษี/ใบเสร็จรับเงิน", getFont11Bold(), 1, Element.ALIGN_RIGHT, 0));
		
		subTab.addCell(setCellWB("โทรศัพท์ :", getFont8Bold(), 1, Element.ALIGN_LEFT, 0));
		subTab.addCell(setCellWB((String) jsonObjectMain.get("tel"), getFont8(), 1, Element.ALIGN_LEFT, 0));
		table.addCell(setCellWB(subTab, 1, Element.ALIGN_LEFT, 0, false, false));
		
		table.addCell(setCellWB("	", getFont8(), 1, Element.ALIGN_CENTER, 0)); // เอกสารออกเป็นชุด
		
		subTab.addCell(setCellWB("เลขประจำตัวผู้เสียภาษีอากร:", getFont8Bold(), 1, Element.ALIGN_LEFT, 0));
		subTab.addCell(setCellWB((String) jsonObjectMain.get("tin"), getFont8(), 1, Element.ALIGN_LEFT, 0));
		table.addCell(setCellWB(subTab, 1, Element.ALIGN_LEFT, 0, false, false));
		
		table.setWidthPercentage(100);
	
		return table;
	}
	
	private PdfPTable genDetail(String      invoiceId) throws DocumentException, MalformedURLException, IOException {
		
		float[] 	widths	 		= {70f ,7f ,33f};
		PdfPTable 	table 			= new PdfPTable(widths);
		
		float[] 	subWL	 		= {5f, 65f};
		PdfPTable 	subTabL 		= new PdfPTable(subWL);
		
		float[] 	subWR	 		= {10f, 23f};
		PdfPTable 	subTabR 		= new PdfPTable(subWR);
		JSONObject 	jsonObjectMain  = this.formDataObj;
//		String      invoiceId		= (String) jsonObjectMain.get("invoiceId");
		String      invoiceDate		= (String) jsonObjectMain.get("invoiceDate");
		String      cusNameDisp		= (String) jsonObjectMain.get("cusNameDisp") + 
										" (" + (String) jsonObjectMain.get("idNumber") + ") ";
		String      cusAddress		= (String) jsonObjectMain.get("cusAddress");
		
		table.addCell(setCellWB("", getFont12Bold(), 3, Element.ALIGN_LEFT, 0));
		
		subTabL.addCell(setCellWB("", getFont6(), 2, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("ชื่อ ", getFont8Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB(cusNameDisp , getFont8(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont6(), 2, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("ที่อยู่ ", getFont8Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB(cusAddress, getFont8(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont6(), 2, Element.ALIGN_LEFT, 0));
		table.addCell(setCell(subTabL, 1));
		
		table.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		
		subTabR.addCell(setCell("\n เลขที่ \n", getFont8Bold(), 1, 1, Element.ALIGN_CENTER));
		subTabR.addCell(setCell("\n " + invoiceId + " \n", getFont8(), 1, 1, Element.ALIGN_RIGHT));
		subTabR.addCell(setCell("\n วันที่ \n", getFont8Bold(), 1, 1, Element.ALIGN_CENTER));
		subTabR.addCell(setCell("\n " + invoiceDate + " \n", getFont8(), 1, 1, Element.ALIGN_RIGHT));
		table.addCell(setCell(subTabR, 1));
		
		table.setWidthPercentage(100);
	
		return table;
	}
	
	private PdfPTable genProduct() throws DocumentException, MalformedURLException, IOException {
		
		float[] 	widths	 		= {15f ,19f ,25f ,25f ,15f};
		PdfPTable 	table 			= new PdfPTable(widths);
		JSONObject 	jsonObjectDetail= null;
		
		table.addCell(setCell("ยี่ห้อ", getFont10Bold(), 1, 1, Element.ALIGN_CENTER));
		table.addCell(setCell("รุ่น", getFont10Bold(), 1, 1, Element.ALIGN_CENTER));
		table.addCell(setCell("หมายเลขตัวถัง", getFont10Bold(), 1, 1, Element.ALIGN_CENTER));
		table.addCell(setCell("หมายเลขเครื่อง", getFont10Bold(), 1, 1, Element.ALIGN_CENTER));
		table.addCell(setCell("สี", getFont10Bold(), 1, 1, Element.ALIGN_CENTER));

		// ดึงข้อมูลขึ้นมาแสดงบนหน้าจอ
		jsonObjectDetail = this.formDataObj;
		table.addCell(setCell((String) jsonObjectDetail.get("brandName"),   getFont7(), 1, 1, Element.ALIGN_CENTER));
		table.addCell(setCell((String) jsonObjectDetail.get("model"), 	    getFont7(), 1, 1, Element.ALIGN_CENTER));
		table.addCell(setCell((String) jsonObjectDetail.get("chassisDisp"), getFont7(), 1, 1, Element.ALIGN_CENTER));
		table.addCell(setCell((String) jsonObjectDetail.get("engineNoDisp"),getFont7(), 1, 1, Element.ALIGN_CENTER));
		table.addCell(setCell((String) jsonObjectDetail.get("color"), 		getFont7(), 1, 1, Element.ALIGN_CENTER));
		
		table.setWidthPercentage(100);
	
		return table;
	}
	
	private PdfPTable genTotalCost() throws DocumentException, MalformedURLException, IOException {
		
		float[] 	widths	 		= {55f ,10f ,35f};
		PdfPTable 	table 			= new PdfPTable(widths);
		
		PdfPTable 	subTabL 		= new PdfPTable(1);
		
		float[] 	subWR	 		= {20f, 15f};
		PdfPTable 	subTabR 		= new PdfPTable(subWR);
		
		// ดึงข้อมูลขึ้นมาแสดงบนหน้าจอ
		JSONObject 	jsonObjectDetail= null;
		jsonObjectDetail = this.formDataObj;

		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("หมายเหตุ : " + (String) jsonObjectDetail.get("remark"), getFont7(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCell((String) jsonObjectDetail.get("totalAmountThai"), getFont7(), 1, 1, Element.ALIGN_CENTER));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		table.addCell(setCellWB(subTabL, 1, Element.ALIGN_LEFT, 0, false, false));
		
		table.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		
		subTabR.addCell(setCell("\n มูลค่าสินค้า \n", getFont8Bold(), 1, 1, Element.ALIGN_RIGHT));
		subTabR.addCell(setCell("\n " + (String) jsonObjectDetail.get("priceAmount") + " \n", getFont7(), 1, 1, Element.ALIGN_RIGHT));
		subTabR.addCell(setCell("\n ภาษีมูลค่าเพิ่ม 7% \n", getFont8Bold(), 1, 1, Element.ALIGN_RIGHT));
		subTabR.addCell(setCell("\n " + (String) jsonObjectDetail.get("vatAmount")   + " \n", getFont7(), 1, 1, Element.ALIGN_RIGHT));
		subTabR.addCell(setCell("\n จำนวนเงินรวมทั้งสิ้น \n", getFont8Bold(), 1, 1, Element.ALIGN_RIGHT));
		subTabR.addCell(setCell("\n " + (String) jsonObjectDetail.get("totalAmount") + " \n", getFont7(), 1, 1, Element.ALIGN_RIGHT));
		table.addCell(setCell(subTabR, 1));
		
		table.setWidthPercentage(100);
	
		return table;
	}
	
	private PdfPTable genFooter() throws DocumentException, MalformedURLException, IOException {
		
		PdfPTable 	table 			= new PdfPTable(1);
		
		table.addCell(setCellWB("ผู้รับเงิน......................................................................", getFont8(), 1, Element.ALIGN_RIGHT, 0));
		
		table.setWidthPercentage(100);
	
		return table;
	}

	public PdfPTable brLine() throws DocumentException, MalformedURLException, IOException {
		
		PdfPTable 	table 			= new PdfPTable(1);
		
		table.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		
		table.setWidthPercentage(100);
	
		return table;
	}

	
	//**********************************************************************************//
	// ส่งเสริมการขาย
	//**********************************************************************************//
	private PdfPTable genProductAddSales() throws DocumentException, MalformedURLException, IOException {
		
		float[] 	widths	 		= {15f ,19f ,25f ,25f ,15f};
		PdfPTable 	table 			= new PdfPTable(widths);
		JSONObject 	jsonObjectDetail= null;
		
//		table.addCell(setCell("รายละเอียดใบกำกับ", getFont10Bold(), 5, 1, Element.ALIGN_CENTER));
		table.addCell(setCell("ยี่ห้อ", getFont10Bold(), 1, 1, Element.ALIGN_CENTER));
		table.addCell(setCell("รุ่น", getFont10Bold(), 1, 1, Element.ALIGN_CENTER));
		table.addCell(setCell("หมายเลขตัวถัง", getFont10Bold(), 1, 1, Element.ALIGN_CENTER));
		table.addCell(setCell("หมายเลขเครื่อง", getFont10Bold(), 1, 1, Element.ALIGN_CENTER));
		table.addCell(setCell("สี", getFont10Bold(), 1, 1, Element.ALIGN_CENTER));

		// ดึงข้อมูลขึ้นมาแสดงบนหน้าจอ
		jsonObjectDetail = this.formDataObj;
//		table.addCell(setCell((String) jsonObjectDetail.get("addSalesRemark"),   getFont10(), 5, 1, Element.ALIGN_CENTER));
		table.addCell(setCell("-", getFont7(), 1, 1, Element.ALIGN_CENTER));
		table.addCell(setCell("-", getFont7(), 1, 1, Element.ALIGN_CENTER));
		table.addCell(setCell("-", getFont7(), 1, 1, Element.ALIGN_CENTER));
		table.addCell(setCell("-", getFont7(), 1, 1, Element.ALIGN_CENTER));
		table.addCell(setCell("-", getFont7(), 1, 1, Element.ALIGN_CENTER));
		
		table.setWidthPercentage(100);
	
		return table;
	}
	
	private PdfPTable genTotalCostAddSales() throws DocumentException, MalformedURLException, IOException {		
		float[] 	widths	 		= {55f ,10f ,35f};
		PdfPTable 	table 			= new PdfPTable(widths);
		
		PdfPTable 	subTabL 		= new PdfPTable(1);
		
		float[] 	subWR	 		= {20f, 15f};
		PdfPTable 	subTabR 		= new PdfPTable(subWR);
		
		// ดึงข้อมูลขึ้นมาแสดงบนหน้าจอ
		JSONObject 	jsonObjectDetail= null;
		jsonObjectDetail = this.formDataObj;

		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("หมายเหตุ : " + (String) jsonObjectDetail.get("addSalesRemark"), getFont7(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCell((String) jsonObjectDetail.get("addSalesTotalAmountThai"), getFont7(), 1, 1, Element.ALIGN_CENTER));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		table.addCell(setCellWB(subTabL, 1, Element.ALIGN_LEFT, 0, false, false));
		
		table.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		
		subTabR.addCell(setCell("\n มูลค่าสินค้า \n", getFont8Bold(), 1, 1, Element.ALIGN_RIGHT));
		subTabR.addCell(setCell("\n " + (String) jsonObjectDetail.get("addSalesPriceAmount") + " \n", getFont7(), 1, 1, Element.ALIGN_RIGHT));
		subTabR.addCell(setCell("\n ภาษีมูลค่าเพิ่ม 7% \n", getFont8Bold(), 1, 1, Element.ALIGN_RIGHT));
		subTabR.addCell(setCell("\n " + (String) jsonObjectDetail.get("addSalesVatAmount")   + " \n", getFont7(), 1, 1, Element.ALIGN_RIGHT));
		subTabR.addCell(setCell("\n จำนวนเงินรวมทั้งสิ้น \n", getFont8Bold(), 1, 1, Element.ALIGN_RIGHT));
		subTabR.addCell(setCell("\n " + (String) jsonObjectDetail.get("addSalesTotalAmount") + " \n", getFont7(), 1, 1, Element.ALIGN_RIGHT));
		table.addCell(setCell(subTabR, 1));
		
		table.setWidthPercentage(100);
	
		return table;
	}
	//**********************************************************************************//
	
	//**********************************************************************************//
	// ใบเพิ่มหนี้/ใบลดหนี้
	//**********************************************************************************//
	private PdfPTable genHeaderCredit() throws DocumentException, MalformedURLException, IOException {
		
		float[] 	widths	 		= {60f, 40f};
		PdfPTable 	table 			= new PdfPTable(widths);
		
		float[] 	subW	 		= {24f, 36f};
		PdfPTable 	subTab 			= new PdfPTable(subW);
		JSONObject 	jsonObjectMain  = this.formDataObj;
		String      companyName		= (String) jsonObjectMain.get("CompanyName");
		String      companyAddress	= (String) jsonObjectMain.get("CompanyAddress");
		String      flagCredit		= (String) jsonObjectMain.get("flagCredit");
		
		table.addCell(setCellWB(companyName, getFont12Bold(), 2, Element.ALIGN_LEFT, 0));
		
		table.addCell(setCellWB(companyAddress, getFont8(), 1, Element.ALIGN_LEFT, 0));
		if (flagCredit.equals("A")) {
			table.addCell(setCellWB("ใบกำกับภาษี/ใบเพิ่มหนี้", getFont11Bold(), 1, Element.ALIGN_RIGHT, 0));
		} else if (flagCredit.equals("C")) {
			table.addCell(setCellWB("ใบกำกับภาษี/ใบลดหนี้", getFont11Bold(), 1, Element.ALIGN_RIGHT, 0));
		}	
		
		subTab.addCell(setCellWB("โทรศัพท์ :", getFont8Bold(), 1, Element.ALIGN_LEFT, 0));
		subTab.addCell(setCellWB((String) jsonObjectMain.get("tel"), getFont8(), 1, Element.ALIGN_LEFT, 0));
		table.addCell(setCellWB(subTab, 1, Element.ALIGN_LEFT, 0, false, false));
		
		table.addCell(setCellWB("", getFont8(), 1, Element.ALIGN_CENTER, 0));
		
		subTab.addCell(setCellWB("เลขประจำตัวผู้เสียภาษีอากร:", getFont8Bold(), 1, Element.ALIGN_LEFT, 0));
		subTab.addCell(setCellWB((String) jsonObjectMain.get("tin"), getFont8(), 1, Element.ALIGN_LEFT, 0));
		table.addCell(setCellWB(subTab, 1, Element.ALIGN_LEFT, 0, false, false));
		
		table.setWidthPercentage(100);
	
		return table;
	}
	
	private PdfPTable genTotalCostCredit() throws DocumentException, MalformedURLException, IOException {
		
		float[] 	widths	 		= {55f ,10f ,35f};
		PdfPTable 	table 			= new PdfPTable(widths);
		
		PdfPTable 	subTabL 		= new PdfPTable(1);
		
		float[] 	subWR	 		= {20f, 15f};
		PdfPTable 	subTabR 		= new PdfPTable(subWR);
		
		// ดึงข้อมูลขึ้นมาแสดงบนหน้าจอ
		JSONObject 	jsonObjectDetail= null;
		jsonObjectDetail = this.formDataObj;

		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCell((String) jsonObjectDetail.get("creditAmountThai"), getFont7(), 1, 1, Element.ALIGN_CENTER));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		table.addCell(setCellWB(subTabL, 1, Element.ALIGN_LEFT, 0, false, false));
		
		table.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		
		subTabR.addCell(setCell("\n จำนวนเงินรวมทั้งสิ้น \n", getFont8Bold(), 1, 1, Element.ALIGN_RIGHT));
		subTabR.addCell(setCell("\n " + (String) jsonObjectDetail.get("creditAmount") + " \n", getFont7(), 1, 1, Element.ALIGN_RIGHT));
		table.addCell(setCell(subTabR, 1));
		
		table.setWidthPercentage(100);
	
		return table;
	}
	//**********************************************************************************//
}
