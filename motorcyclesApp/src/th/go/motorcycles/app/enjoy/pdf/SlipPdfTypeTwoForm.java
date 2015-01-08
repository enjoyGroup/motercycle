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

public class SlipPdfTypeTwoForm extends MotorItext implements PdfFormService {
	
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
		System.out.println("[SlipPdfTypeTwoForm][createForm][Begin]");
		JSONObject 	jsonObjectMain    = this.formDataObj;
		String      invoiceId		  = (String) jsonObjectMain.get("invoiceId");
		String      flagAddSales	  = (String) jsonObjectMain.get("flagAddSales");
		String      invoiceIdAddSales = (String) jsonObjectMain.get("invoiceIdAddSales");
		String      flagCredit		  = (String) jsonObjectMain.get("flagCredit");
		try{
			// ใบกำกับภาษี
			document.add(this.genHeader());
			document.add(this.brLine());
			document.add(this.brLine());
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
			System.out.println("[SlipPdfTypeTwoForm][createForm][End]");
		}
		
		return document;
	}
	
	private PdfPTable genHeader() throws DocumentException, MalformedURLException, IOException {
		
		float[] 	widths	 		= {60f, 40f};
		PdfPTable 	table 			= new PdfPTable(widths);
		
		float[] 	subW	 		= {20f, 40f};
		PdfPTable 	subTab 			= new PdfPTable(subW);
		JSONObject 	jsonObjectMain  = this.formDataObj;
		String      companyName		= (String) jsonObjectMain.get("CompanyName");
		String      companyAddress	= (String) jsonObjectMain.get("CompanyAddress");
		
		table.addCell(setCellWB("    ", getFont12Bold(), 2, Element.ALIGN_LEFT, 0));   // companyName
			
		table.addCell(setCellWB("    ", getFont8(), 1, Element.ALIGN_LEFT, 0));		   // companyAddress
		table.addCell(setCellWB("    ", getFont12Bold(), 1, Element.ALIGN_CENTER, 0)); // ใบกำกับภาษี/ใบเสร็จรับเงิน
		
		subTab.addCell(setCellWB("    ", getFont8Bold(), 1, Element.ALIGN_LEFT, 0));   // โทรศัพท์ :
		subTab.addCell(setCellWB("    ", getFont8(), 1, Element.ALIGN_LEFT, 0));	   // (String) jsonObjectMain.get("tel")
		table.addCell(setCellWB(subTab, 1, Element.ALIGN_LEFT, 0, false, false));
		
		table.addCell(setCellWB("    ", getFont8(), 1, Element.ALIGN_CENTER, 0));      // เอกสารออกเป็นชุด
		
		subTab.addCell(setCellWB("   ", getFont8Bold(), 1, Element.ALIGN_LEFT, 0));   // เลขประจำตัวผู้เสียภาษีอากร:
		subTab.addCell(setCellWB("   ", getFont8(), 1, Element.ALIGN_LEFT, 0));       // (String) jsonObjectMain.get("tin")
		table.addCell(setCellWB(subTab, 1, Element.ALIGN_LEFT, 0, false, false));
		
		table.setWidthPercentage(100);
	
		return table;
	}
	
	private PdfPTable genDetail(String      invoiceId) throws DocumentException, MalformedURLException, IOException {
		
		float[] 	widths	 		= {70f ,10f ,30f};
		PdfPTable 	table 			= new PdfPTable(widths);
		
		float[] 	subWL	 		= {5f, 65f};
		PdfPTable 	subTabL 		= new PdfPTable(subWL);
		
		float[] 	subWR	 		= {10f, 20f};
		PdfPTable 	subTabR 		= new PdfPTable(subWR);
		JSONObject 	jsonObjectMain  = this.formDataObj;
//		String      invoiceId		= (String) jsonObjectMain.get("invoiceId");
		String      invoiceDate		= (String) jsonObjectMain.get("invoiceDate");
		String      cusNameDisp		= (String) jsonObjectMain.get("cusNameDisp") + 
										" ( " + (String) jsonObjectMain.get("idNumber") + " ) ";
		String      cusAddress		= (String) jsonObjectMain.get("cusAddress");
		
		table.addCell(setCellWB("", getFont12Bold(), 3, Element.ALIGN_LEFT, 0));
		
		subTabL.addCell(setCellWB("", getFont6(), 2, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB(" ชื่อ ", getFont8Bold(), 1, Element.ALIGN_LEFT, 0)); // ชื่อ
		subTabL.addCell(setCellWB(cusNameDisp , getFont8(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont6(), 2, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB(" ที่อยู่ ", getFont8Bold(), 1, Element.ALIGN_LEFT, 0)); // ที่อยู่
		subTabL.addCell(setCellWB(cusAddress, getFont8(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont6(), 2, Element.ALIGN_LEFT, 0));
		//table.addCell(setCell(subTabL, 1));
		table.addCell(setCellWB(subTabL, 1, Element.ALIGN_LEFT, 0, false, false));
		
		table.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		
		subTabR.addCell(setCellWB("\n     \n ", getFont8Bold(), 1, Element.ALIGN_CENTER, 0)); // เลขที่
		subTabR.addCell(setCellWB("\n " + invoiceId + " \n", getFont8(), 1, Element.ALIGN_RIGHT, 0));
		subTabR.addCell(setCellWB("\n     \n", getFont8Bold(), 1, Element.ALIGN_CENTER, 0));  // วันที่ 
		subTabR.addCell(setCellWB("\n " + invoiceDate + " \n", getFont8(), 1, Element.ALIGN_RIGHT, 0));
//		table.addCell(setCell(subTabR, 1));
		table.addCell(setCellWB(subTabR, 1, Element.ALIGN_LEFT, 0, false, false));
		
		table.setWidthPercentage(100);
	
		return table;
	}
	
	private PdfPTable genProduct() throws DocumentException, MalformedURLException, IOException {
		
		float[] 	widths	 		= {22f ,20f ,20f ,20f ,18f};
		PdfPTable 	table 			= new PdfPTable(widths);
		JSONObject 	jsonObjectDetail= null;
		
		table.addCell(setCellWB("    ", getFont10Bold(), 1, Element.ALIGN_CENTER, 0)); // ยี่ห้อ
		table.addCell(setCellWB("    ", getFont10Bold(), 1, Element.ALIGN_CENTER, 0)); // รุ่น
		table.addCell(setCellWB("    ", getFont10Bold(), 1, Element.ALIGN_CENTER, 0)); // หมายเลขเครื่อง
		table.addCell(setCellWB("    ", getFont10Bold(), 1, Element.ALIGN_CENTER, 0)); // หมายเลขตัวถัง
		table.addCell(setCellWB("    ", getFont10Bold(), 1, Element.ALIGN_CENTER, 0)); // ซีซี.

		// ดึงข้อมูลขึ้นมาแสดงบนหน้าจอ
		jsonObjectDetail = this.formDataObj;
		table.addCell(setCellWB((String) jsonObjectDetail.get("brandName"),   getFont10(), 1, Element.ALIGN_CENTER, 0));
		table.addCell(setCellWB((String) jsonObjectDetail.get("model"), 	  getFont10(), 1, Element.ALIGN_CENTER, 0));
		table.addCell(setCellWB((String) jsonObjectDetail.get("chassisDisp"), getFont10(), 1, Element.ALIGN_CENTER, 0));
		table.addCell(setCellWB((String) jsonObjectDetail.get("engineNoDisp"),getFont10(), 1, Element.ALIGN_CENTER, 0));
		table.addCell(setCellWB((String) jsonObjectDetail.get("color"), 		  getFont10(), 1, Element.ALIGN_CENTER, 0));
		
		table.setWidthPercentage(100);
	
		return table;
	}
	
	private PdfPTable genTotalCost() throws DocumentException, MalformedURLException, IOException {
		
		float[] 	widths	 		= {55f ,5f ,40f};
		PdfPTable 	table 			= new PdfPTable(widths);
		
		PdfPTable 	subTabL 		= new PdfPTable(1);
		
		float[] 	subWR	 		= {22f, 18f};
		PdfPTable 	subTabR 		= new PdfPTable(subWR);
		
		// ดึงข้อมูลขึ้นมาแสดงบนหน้าจอ
		JSONObject 	jsonObjectDetail= null;
		jsonObjectDetail = this.formDataObj;

		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("หมายเหตุ : " + (String) jsonObjectDetail.get("remark"), getFont10(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB((String) jsonObjectDetail.get("totalAmountThai"), getFont10(), 1, Element.ALIGN_CENTER, 0));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		table.addCell(setCellWB(subTabL, 1, Element.ALIGN_LEFT, 0, false, false));
		
		table.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		
		subTabR.addCell(setCellWB("\n มูลค่าสินค้า \n", getFont8Bold(), 1, Element.ALIGN_RIGHT, 0));
		subTabR.addCell(setCellWB("\n " + (String) jsonObjectDetail.get("priceAmount") + " \n", getFont8(), 1, Element.ALIGN_RIGHT, 0));
		subTabR.addCell(setCellWB("\n ภาษีมูลค่าเพิ่ม 7% \n", getFont8Bold(), 1, 1, Element.ALIGN_RIGHT, 0));
		subTabR.addCell(setCellWB("\n " + (String) jsonObjectDetail.get("vatAmount")   + " \n", getFont8(), 1, Element.ALIGN_RIGHT, 0));
		subTabR.addCell(setCellWB("\n จำนวนเงินรวมทั้งสิ้น 7% \n", getFont8Bold(), 1, 1, Element.ALIGN_RIGHT, 0));
		subTabR.addCell(setCellWB("\n " + (String) jsonObjectDetail.get("totalAmount") + " \n", getFont8(), 1, Element.ALIGN_RIGHT, 0));
//		table.addCell(setCell(subTabR, 1));
		table.addCell(setCellWB(subTabR, 1, Element.ALIGN_LEFT, 0, false, false));
		
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
		
		float[] 	widths	 		= {22f ,20f ,20f ,20f ,18f};
		PdfPTable 	table 			= new PdfPTable(widths);
		
		table.addCell(setCellWB("    ", getFont10Bold(), 1, Element.ALIGN_CENTER, 0)); // ยี่ห้อ
		table.addCell(setCellWB("    ", getFont10Bold(), 1, Element.ALIGN_CENTER, 0)); // รุ่น
		table.addCell(setCellWB("    ", getFont10Bold(), 1, Element.ALIGN_CENTER, 0)); // หมายเลขเครื่อง
		table.addCell(setCellWB("    ", getFont10Bold(), 1, Element.ALIGN_CENTER, 0)); // หมายเลขตัวถัง
		table.addCell(setCellWB("    ", getFont10Bold(), 1, Element.ALIGN_CENTER, 0)); // ซีซี.

		// ดึงข้อมูลขึ้นมาแสดงบนหน้าจอ
		table.addCell(setCellWB("    ", getFont10(), 1, Element.ALIGN_CENTER, 0));
		table.addCell(setCellWB("    ", getFont10(), 1, Element.ALIGN_CENTER, 0));
		table.addCell(setCellWB("    ", getFont10(), 1, Element.ALIGN_CENTER, 0));
		table.addCell(setCellWB("    ", getFont10(), 1, Element.ALIGN_CENTER, 0));
		table.addCell(setCellWB("    ", getFont10(), 1, Element.ALIGN_CENTER, 0));
		
		table.setWidthPercentage(100);
	
		return table;
	}
	
	private PdfPTable genTotalCostAddSales() throws DocumentException, MalformedURLException, IOException {
		
		float[] 	widths	 		= {55f ,5f ,40f};
		PdfPTable 	table 			= new PdfPTable(widths);
		
		PdfPTable 	subTabL 		= new PdfPTable(1);
		
		float[] 	subWR	 		= {22f, 18f};
		PdfPTable 	subTabR 		= new PdfPTable(subWR);
		
		// ดึงข้อมูลขึ้นมาแสดงบนหน้าจอ
		JSONObject 	jsonObjectDetail= null;
		jsonObjectDetail = this.formDataObj;

		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("หมายเหตุ : " + (String) jsonObjectDetail.get("addSalesRemark"), getFont10Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB((String) jsonObjectDetail.get("addSalesTotalAmountThai"), getFont10(), 1, Element.ALIGN_CENTER, 0));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		table.addCell(setCellWB(subTabL, 1, Element.ALIGN_LEFT, 0, false, false));
		
		table.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		
		subTabR.addCell(setCellWB("\n มูลค่าสินค้า \n", getFont8Bold(), 1, Element.ALIGN_RIGHT, 0));
		subTabR.addCell(setCellWB("\n " + (String) jsonObjectDetail.get("addSalesPriceAmount") + " \n", getFont8(), 1, Element.ALIGN_RIGHT, 0));
		subTabR.addCell(setCellWB("\n ภาษีมูลค่าเพิ่ม 7% \n", getFont8Bold(), 1, 1, Element.ALIGN_RIGHT, 0));
		subTabR.addCell(setCellWB("\n " + (String) jsonObjectDetail.get("addSalesVatAmount")   + " \n", getFont8(), 1, Element.ALIGN_RIGHT, 0));
		subTabR.addCell(setCellWB("\n จำนวนเงินรวมทั้งสิ้น 7% \n", getFont8Bold(), 1, 1, Element.ALIGN_RIGHT, 0));
		subTabR.addCell(setCellWB("\n " + (String) jsonObjectDetail.get("addSalesTotalAmount") + " \n", getFont8(), 1, Element.ALIGN_RIGHT, 0));
		table.addCell(setCellWB(subTabR, 1, Element.ALIGN_LEFT, 0, false, false));
		
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
		
		float[] 	subW	 		= {20f, 40f};
		PdfPTable 	subTab 			= new PdfPTable(subW);
		JSONObject 	jsonObjectMain  = this.formDataObj;
		String      companyName		= (String) jsonObjectMain.get("CompanyName");
		String      companyAddress	= (String) jsonObjectMain.get("CompanyAddress");
		String      flagCredit		= (String) jsonObjectMain.get("flagCredit");

		table.addCell(setCellWB("    ", getFont12Bold(), 2, Element.ALIGN_LEFT, 0));   // companyName
		
		table.addCell(setCellWB("    ", getFont8(), 1, Element.ALIGN_LEFT, 0));		   // companyAddress
		if (flagCredit.equals("A")) {
			table.addCell(setCellWB("                              ", getFont12Bold(), 1, Element.ALIGN_CENTER, 0)); // ใบเพิ่มหนี้
		} else if (flagCredit.equals("C")) {
			table.addCell(setCellWB("                              ", getFont12Bold(), 1, Element.ALIGN_CENTER, 0)); // ใบลดหนี้
		}	
		
		subTab.addCell(setCellWB("    ", getFont8Bold(), 1, Element.ALIGN_LEFT, 0));   // โทรศัพท์ :
		subTab.addCell(setCellWB("    ", getFont8(), 1, Element.ALIGN_LEFT, 0));	   // (String) jsonObjectMain.get("tel")
		table.addCell(setCellWB(subTab, 1, Element.ALIGN_LEFT, 0, false, false));
		
		table.addCell(setCellWB("    ", getFont8(), 1, Element.ALIGN_CENTER, 0));      // เอกสารออกเป็นชุด
		
		subTab.addCell(setCellWB("   ", getFont8Bold(), 1, Element.ALIGN_LEFT, 0));   // เลขประจำตัวผู้เสียภาษีอากร:
		subTab.addCell(setCellWB("   ", getFont8(), 1, Element.ALIGN_LEFT, 0));       // (String) jsonObjectMain.get("tin")
		table.addCell(setCellWB(subTab, 1, Element.ALIGN_LEFT, 0, false, false));
		
		table.setWidthPercentage(100);
	
		return table;
	}
	
	private PdfPTable genTotalCostCredit() throws DocumentException, MalformedURLException, IOException {
		
		float[] 	widths	 		= {55f ,5f ,40f};
		PdfPTable 	table 			= new PdfPTable(widths);
		
		PdfPTable 	subTabL 		= new PdfPTable(1);
		
		float[] 	subWR	 		= {22f, 18f};
		PdfPTable 	subTabR 		= new PdfPTable(subWR);
		
		// ดึงข้อมูลขึ้นมาแสดงบนหน้าจอ
		JSONObject 	jsonObjectDetail= null;
		jsonObjectDetail = this.formDataObj;

		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB((String) jsonObjectDetail.get("creditAmountThai"), getFont10(), 1, Element.ALIGN_CENTER, 0));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		table.addCell(setCellWB(subTabL, 1, Element.ALIGN_LEFT, 0, false, false));
		
		table.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		
		subTabR.addCell(setCellWB("\n จำนวนเงินรวมทั้งสิ้น 7% \n", getFont8Bold(), 1, Element.ALIGN_RIGHT, 0));
		subTabR.addCell(setCellWB("\n " + (String) jsonObjectDetail.get("creditAmount") + " \n", getFont8(), 1, Element.ALIGN_RIGHT, 0));
//		table.addCell(setCell(subTabR, 1));
		table.addCell(setCellWB(subTabR, 1, Element.ALIGN_LEFT, 0, false, false));
		
		table.setWidthPercentage(100);
	
		return table;
	}
	//**********************************************************************************//
}
