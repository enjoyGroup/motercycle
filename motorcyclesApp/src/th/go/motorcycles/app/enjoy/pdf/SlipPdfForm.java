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
		
		try{
			document.add(this.genHeader());
			document.add(this.brLine());
			document.add(this.genDetail());
			document.add(this.brLine());
			document.add(this.genProduct());
			document.add(this.brLine());
			document.add(this.genTotalCost());
			document.add(this.brLine());
			document.add(this.brLine());
			document.add(this.brLine());
			document.add(this.genFooter());
			
			
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
		
		float[] 	subW	 		= {20f, 40f};
		PdfPTable 	subTab 			= new PdfPTable(subW);
		JSONObject 	jsonObjectMain  = this.formDataObj;
		String      companyName		= (String) jsonObjectMain.get("CompanyName");
		String      companyAddress	= (String) jsonObjectMain.get("CompanyAddress");
		
		table.addCell(setCellWB(companyName, getFont12Bold(), 2, Element.ALIGN_LEFT, 0));
		
		table.addCell(setCellWB(companyAddress, getFont8(), 1, Element.ALIGN_LEFT, 0));
		table.addCell(setCellWB("ใบกำกับภาษี/ใบเสร็จรับเงิน", getFont12Bold(), 1, Element.ALIGN_CENTER, 0));
		
		subTab.addCell(setCellWB("โทรศัพท์ :", getFont8Bold(), 1, Element.ALIGN_LEFT, 0));
		subTab.addCell(setCellWB("02-992-8233", getFont8(), 1, Element.ALIGN_LEFT, 0));
		table.addCell(setCellWB(subTab, 1, Element.ALIGN_LEFT, 0, false, false));
		
		table.addCell(setCellWB("เอกสารออกเป็นชุด", getFont8(), 1, Element.ALIGN_CENTER, 0));
		
		subTab.addCell(setCellWB("เลขประจำตัวผู้เสียภาษีอากร:", getFont8Bold(), 1, Element.ALIGN_LEFT, 0));
		subTab.addCell(setCellWB("xxxxxxxxxxxx", getFont8(), 1, Element.ALIGN_LEFT, 0));
		table.addCell(setCellWB(subTab, 1, Element.ALIGN_LEFT, 0, false, false));
		
		table.setWidthPercentage(100);
	
		return table;
	}
	
	private PdfPTable genDetail() throws DocumentException, MalformedURLException, IOException {
		
		float[] 	widths	 		= {70f ,10f ,30f};
		PdfPTable 	table 			= new PdfPTable(widths);
		
		float[] 	subWL	 		= {5f, 65f};
		PdfPTable 	subTabL 		= new PdfPTable(subWL);
		
		float[] 	subWR	 		= {10f, 20f};
		PdfPTable 	subTabR 		= new PdfPTable(subWR);
		JSONObject 	jsonObjectMain  = this.formDataObj;
		String      invoiceId		= (String) jsonObjectMain.get("invoiceId");
		String      invoiceDate		= (String) jsonObjectMain.get("invoiceDate");
		String      cusNameDisp		= (String) jsonObjectMain.get("cusNameDisp");
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
		
		float[] 	widths	 		= {20f ,20f ,20f ,20f ,20f};
		PdfPTable 	table 			= new PdfPTable(widths);
		JSONObject 	jsonObjectDetail= null;
		
		table.addCell(setCell("ยี่ห้อ", getFont8Bold(), 1, 1, Element.ALIGN_CENTER));
		table.addCell(setCell("รุ่น", getFont8Bold(), 1, 1, Element.ALIGN_CENTER));
		table.addCell(setCell("หมายเลขเครื่อง", getFont8Bold(), 1, 1, Element.ALIGN_CENTER));
		table.addCell(setCell("หมายเลขตัวถัง", getFont8Bold(), 1, 1, Element.ALIGN_CENTER));
		table.addCell(setCell("ซีซี.", getFont8Bold(), 1, 1, Element.ALIGN_CENTER));

		// ดึงข้อมูลขึ้นมาแสดงบนหน้าจอ
		jsonObjectDetail = this.formDataObj;
		table.addCell(setCell((String) jsonObjectDetail.get("brandName"),   getFont8(), 1, 1, Element.ALIGN_CENTER));
		table.addCell(setCell((String) jsonObjectDetail.get("model"), 	    getFont8(), 1, 1, Element.ALIGN_CENTER));
		table.addCell(setCell((String) jsonObjectDetail.get("chassisDisp"), getFont8(), 1, 1, Element.ALIGN_CENTER));
		table.addCell(setCell((String) jsonObjectDetail.get("engineNoDisp"),getFont8(), 1, 1, Element.ALIGN_CENTER));
		table.addCell(setCell((String) jsonObjectDetail.get("size"), 		getFont8(), 1, 1, Element.ALIGN_CENTER));
		
		table.setWidthPercentage(100);
	
		return table;
	}
	
	private PdfPTable genTotalCost() throws DocumentException, MalformedURLException, IOException {
		
		float[] 	widths	 		= {55f ,5f ,50f};
		PdfPTable 	table 			= new PdfPTable(widths);
		
		PdfPTable 	subTabL 		= new PdfPTable(1);
		
		float[] 	subWR	 		= {20f, 30f};
		PdfPTable 	subTabR 		= new PdfPTable(subWR);
		
		// ดึงข้อมูลขึ้นมาแสดงบนหน้าจอ
		JSONObject 	jsonObjectDetail= null;
		jsonObjectDetail = this.formDataObj;

		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCell((String) jsonObjectDetail.get("totalAmountThai"), getFont10(), 1, 1, Element.ALIGN_CENTER));
		table.addCell(setCellWB(subTabL, 1, Element.ALIGN_LEFT, 0, false, false));
		
		table.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		
		subTabR.addCell(setCell("\n มูลค่าสินค้า \n", getFont8Bold(), 1, 1, Element.ALIGN_RIGHT));
		subTabR.addCell(setCell("\n " + (String) jsonObjectDetail.get("priceAmount") + " \n", getFont8(), 1, 1, Element.ALIGN_RIGHT));
		subTabR.addCell(setCell("\n ภาษีมูลค่าเพิ่ม 7% \n", getFont8Bold(), 1, 1, Element.ALIGN_RIGHT));
		subTabR.addCell(setCell("\n " + (String) jsonObjectDetail.get("vatAmount")   + " \n", getFont8(), 1, 1, Element.ALIGN_RIGHT));
		subTabR.addCell(setCell("\n จำนวนเงินรวมทั้งสิ้น 7% \n", getFont8Bold(), 1, 1, Element.ALIGN_RIGHT));
		subTabR.addCell(setCell("\n " + (String) jsonObjectDetail.get("totalAmount") + " \n", getFont8(), 1, 1, Element.ALIGN_RIGHT));
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
	
}
