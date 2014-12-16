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
		
		PdfPTable 	table 			= new PdfPTable(1);
		
		table.addCell(setCellWB("บริษัทคลินิคยานยนต์ จำกัด", getFont10Bold(), 1, Element.ALIGN_CENTER, 0));
		table.addCell(setCellWB("16/29-32 หมู่1 ถนน สายไหม แขวงสายไหม แขตสายไหม กรุงเทพมหานคร 10220", getFont8Bold(), 1, Element.ALIGN_CENTER, 0));
		table.addCell(setCellWB("โทรศัพท์ : 02-992-8233 แฟกซ์ 02-530-7035 เลขประจำตัวผู้เสียภาษีอากร : 0105549002794", getFont8Bold(), 1, Element.ALIGN_CENTER, 0));
		table.addCell(setCellWB("ใบกำกับภาษี/ใบเสร็จรับเงิน", getFont10Bold(), 1, Element.ALIGN_CENTER, 0));
		
		table.setWidthPercentage(100);
	
		return table;
	}
	
	private PdfPTable genDetail() throws DocumentException, MalformedURLException, IOException {
		
		float[] 	widths	 		= {60f ,15f ,35f};
		PdfPTable 	table 			= new PdfPTable(widths);
		
		float[] 	subWL	 		= {10f, 60f};
		PdfPTable 	subTabL 		= new PdfPTable(subWL);
		
		float[] 	subWR	 		= {15f, 20f};
		PdfPTable 	subTabR 		= new PdfPTable(subWR);
		
		table.addCell(setCellWB("", getFont12Bold(), 3, Element.ALIGN_LEFT, 0));
		
		subTabL.addCell(setCellWB("", getFont6(), 2, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("ชื่อลูกค้า : ", getFont8Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("ห้างหุ้นส่วนจำกัด รุ่งโรจน์ยานยนต์ (สาขาสำนักงานใหญ่)\n เลขประจำตัวผู้เสียภาษี 0103535010232", getFont8(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont6(), 2, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("ที่อยู่ : ", getFont8Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("9/250-251 หมู่. 8 แขวง อนุเสาวรีย์ \nเขต บางเขน จังหวัด กรุงเทพหมานคร 10220", getFont8(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont6(), 2, Element.ALIGN_LEFT, 0));
		table.addCell(setCellWB(subTabL, 1, Element.ALIGN_LEFT, 0, false, false));
		
		table.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		
		subTabR.addCell(setCellWB("เลขที่ใบกำกับภาษี : ", getFont8Bold(), 1, Element.ALIGN_RIGHT, 0));
		subTabR.addCell(setCellWB("57MC/2345", getFont8(), 1, Element.ALIGN_LEFT, 0));
		subTabR.addCell(setCellWB("วันที่ใบกำกับภาษี : ", getFont8Bold(), 1, Element.ALIGN_RIGHT, 0));
		subTabR.addCell(setCellWB("09/09/2557", getFont8(), 1, Element.ALIGN_LEFT, 0));
		subTabR.addCell(setCellWB("ครบกำหนดชำระ : ", getFont8Bold(), 1, Element.ALIGN_RIGHT, 0));
		subTabR.addCell(setCellWB("16/09/2557", getFont8(), 1, Element.ALIGN_LEFT, 0));
		table.addCell(setCellWB(subTabR, 1, Element.ALIGN_LEFT, 0, false, false));
		
		table.setWidthPercentage(100);
	
		return table;
	}
	
	private PdfPTable genProduct() throws DocumentException, MalformedURLException, IOException {
		
		float[] 	widths	 		= {5f ,35f ,20f ,20f ,20f};
		PdfPTable 	table 			= new PdfPTable(widths);
		
		float[] 	wDetail	 		= {10f ,25f};
		PdfPTable 	tabDetail 		= new PdfPTable(wDetail);
		
		table.addCell(setCellWB("ลำดับ", getFont8Bold(), 1, Element.ALIGN_CENTER ,0));
		table.addCell(setCellWB("รายละเอียด", getFont8Bold(), 1, Element.ALIGN_LEFT ,0));
		table.addCell(setCellWB("จำนวน", getFont8Bold(), 1, Element.ALIGN_RIGHT ,0));
		table.addCell(setCellWB("ราคา", getFont8Bold(), 1, Element.ALIGN_RIGHT ,0));
		table.addCell(setCellWB("เป็นเงิน", getFont8Bold(), 1, Element.ALIGN_RIGHT ,0));
		
		table.addCell(setCellWB("1", getFont8(), 1, Element.ALIGN_CENTER ,0));
		
		tabDetail.addCell(setCellWB("รถจัรยานยนต์ ", getFont8(), 1, Element.ALIGN_LEFT, 0));
		tabDetail.addCell(setCellWB("ฮอนด้า WW150E TH สี เทา-ดำ", getFont8(), 1, Element.ALIGN_LEFT, 0));
		tabDetail.addCell(setCellWB("หมายเลขเครื่อง ", getFont8(), 1, Element.ALIGN_LEFT, 0));
		tabDetail.addCell(setCellWB("KF20E-4023025", getFont8(), 1, Element.ALIGN_LEFT, 0));
		tabDetail.addCell(setCellWB("หมายเลขตัวถัง ", getFont8(), 1, Element.ALIGN_LEFT, 0));
		tabDetail.addCell(setCellWB("MLHKF2088E5023025", getFont8(), 1, Element.ALIGN_LEFT, 0));
		table.addCell(setCellWB(tabDetail, 1, Element.ALIGN_LEFT, 0, false, false));
		
		table.addCell(setCellWB("1", getFont8(), 1, Element.ALIGN_RIGHT ,0));
		table.addCell(setCellWB("67,757.01", getFont8(), 1, Element.ALIGN_RIGHT ,0));
		table.addCell(setCellWB("67,757.01", getFont8(), 1, Element.ALIGN_RIGHT ,0));
		
		table.setWidthPercentage(100);
	
		return table;
	}
	
	private PdfPTable genTotalCost() throws DocumentException, MalformedURLException, IOException {
		
		float[] 	widths	 		= {55f ,5f ,50f};
		PdfPTable 	table 			= new PdfPTable(widths);
		
		PdfPTable 	subTabL 		= new PdfPTable(1);
		
		float[] 	subWR	 		= {20f, 30f};
		PdfPTable 	subTabR 		= new PdfPTable(subWR);
		
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("   เจ็ดหมื่นสองพันห้าร้อยบาทถ้วน", getFont10Bold(), 1, Element.ALIGN_LEFT ,0));
		table.addCell(setCellWB(subTabL, 1, Element.ALIGN_LEFT, 0, false, false));
		
		table.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		
		subTabR.addCell(setCellWB("", getFont6(), 2, Element.ALIGN_LEFT ,1));
		subTabR.addCell(setCellWB("ยอดขายสุทธิ", getFont8Bold(), 1, Element.ALIGN_LEFT ,0));
		subTabR.addCell(setCellWB("67,756.01", getFont8(), 1, Element.ALIGN_RIGHT ,0));
		subTabR.addCell(setCellWB("บวก ภาษีมูลค่าเพิ่ม 7 %", getFont8Bold(), 1, Element.ALIGN_LEFT ,0));
		subTabR.addCell(setCellWB("4,742.99", getFont8(), 1, Element.ALIGN_RIGHT ,0));
		subTabR.addCell(setCellWB("", getFont6(), 2, Element.ALIGN_LEFT ,1));
		subTabR.addCell(setCellWB("ยอดรวมสุทธิ", getFont8Bold(), 1, Element.ALIGN_LEFT ,0));
		subTabR.addCell(setCellWB("72,500.00", getFont8(), 1, Element.ALIGN_RIGHT ,0));
		subTabR.addCell(setCellWB("", getFont6(), 2, Element.ALIGN_LEFT ,1));
		subTabR.addCell(setCellWB("", getFont6(), 2, Element.ALIGN_LEFT ,1));
		
		subTabR.addCell(setCellWB("", getFont10Bold(), 2, Element.ALIGN_LEFT ,0));
		subTabR.addCell(setCellWB("", getFont10Bold(), 2, Element.ALIGN_LEFT ,0));
		subTabR.addCell(setCellWB("ได้รับสินค้าในสภาพเรียบร้อยและถูกต้องแล้ว", getFont10Bold(), 2, Element.ALIGN_RIGHT ,0));
		
		table.addCell(setCellWB(subTabR, 1, Element.ALIGN_LEFT, 0, false, false));
		
		table.setWidthPercentage(100);
	
		return table;
	}
	
	private PdfPTable genFooter() throws DocumentException, MalformedURLException, IOException {
		
		float[] 	widths	 		= {40f ,30f ,30f};
		PdfPTable 	table 			= new PdfPTable(widths);
		
		table.addCell(setCellWB("หมายเหตุ:", getFont8Bold(), 3, Element.ALIGN_LEFT, 0));
		
		table.addCell(setCellWB("", getFont8Bold(), 1, Element.ALIGN_LEFT, 0));
		table.addCell(setCellWB("ผู้รับเงิน .....................................................", getFont8Bold(), 1, Element.ALIGN_RIGHT, 0));
		table.addCell(setCellWB("ผู้รับสินค้า .....................................................", getFont8Bold(), 1, Element.ALIGN_RIGHT, 0));
		
		table.addCell(setCellWB("", getFont8Bold(), 1, Element.ALIGN_LEFT, 0));
		table.addCell(setCellWB("(.....................................................)", getFont8Bold(), 1, Element.ALIGN_RIGHT, 0));
		table.addCell(setCellWB("(.....................................................)", getFont8Bold(), 1, Element.ALIGN_RIGHT, 0));
		
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
