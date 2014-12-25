package th.go.motorcycles.app.enjoy.pdf;

import java.io.IOException;
import java.net.MalformedURLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import th.go.motorcycles.app.enjoy.pdf.utils.MotorItext;
import th.go.motorcycles.app.enjoy.pdf.utils.PdfFormService;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class SummarySalePdfForm extends MotorItext implements PdfFormService {
	
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
			document.add(this.genProduct());
			document.add(this.brLine());
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
		JSONObject 	jsonObjectMain  = this.formDataObj;
		String      companyName		= (String) jsonObjectMain.get("CompanyName");
		String      companyAddress	= (String) jsonObjectMain.get("CompanyAddress");
		
		table.addCell(setCellWB(companyName, getFont10Bold(), 1, Element.ALIGN_CENTER, 0));
		table.addCell(setCellWB(companyAddress, getFont8Bold(), 1, Element.ALIGN_CENTER, 0));
//		table.addCell(setCellWB("โทรศัพท์ : 02-992-8233 แฟกซ์ 02-530-7035 เลขประจำตัวผู้เสียภาษีอากร : 0105549002794", getFont8Bold(), 1, Element.ALIGN_CENTER, 0));
		
		table.setWidthPercentage(100);
	
		return table;
	}
	
	private PdfPTable genProduct() throws DocumentException, MalformedURLException, IOException {
		
		float[] 	widths	 		= {5f ,7f ,10f ,15f ,10f ,10f ,7f ,7f ,7f, 10f};
		PdfPTable 	table 			= new PdfPTable(widths);
		JSONObject 	jsonObjectMain  = null;
		JSONObject 	jsonObjectDetail= null;
		JSONArray 	listJSONArray 	= null;
		String		summaryAmount   = "";
		try {
			//table.addCell(setCellWB("วันที่ 24 พฤศจิกายน 2557 ถึง วันที่ 26 พฤศจิกายน 2557", getFont8Bold(), 8, Element.ALIGN_CENTER, 0));
			table.addCell(setCellWB("", getFont8Bold(), 10, Element.ALIGN_CENTER, 0));
			
			table.addCell(setCell("ลำดับ",		 getFont8Bold(), 1, 1, Element.ALIGN_CENTER));
			table.addCell(setCell("เลขใบกำกับภาษี",	 getFont8Bold(), 1, 1, Element.ALIGN_CENTER));
			table.addCell(setCell("ชื่อลูกค้า",		 getFont8Bold(), 1, 1, Element.ALIGN_CENTER));
			table.addCell(setCell("รายละเอียดรถยนต์", getFont8Bold(), 1, 1, Element.ALIGN_CENTER));
			table.addCell(setCell("เลขตัวถัง", 		 getFont8Bold(), 1, 1, Element.ALIGN_CENTER));
			table.addCell(setCell("เลขเครื่องยนต์", 	 getFont8Bold(), 1, 1, Element.ALIGN_CENTER));
			table.addCell(setCell("ราคาขาย", 		 getFont8Bold(), 1, 1, Element.ALIGN_CENTER));
			table.addCell(setCell("ราคาภาษี",		 getFont8Bold(), 1, 1, Element.ALIGN_CENTER));
			table.addCell(setCell("ราคาขายสุทธิ",	 getFont8Bold(), 1, 1, Element.ALIGN_CENTER));
			table.addCell(setCell("หมายเหตุ",		 getFont8Bold(), 1, 1, Element.ALIGN_CENTER));
				
			// ดึงข้อมูลขึ้นมาแสดงบนหน้าจอ
			jsonObjectMain = this.formDataObj;
			listJSONArray  = (JSONArray) jsonObjectMain.get("invoicelist");
			summaryAmount  = (String)    jsonObjectMain.get("SummaryAmount");
			for(int i=0;i<listJSONArray.size();i++){
				jsonObjectDetail = (JSONObject) listJSONArray.get(i);

				table.addCell(setCell(String.valueOf(i + 1), getFont8(), 1, 1, Element.ALIGN_CENTER));
				table.addCell(setCell((String) jsonObjectDetail.get("invoiceId"), 		getFont8(), 1, 1, Element.ALIGN_CENTER));
				table.addCell(setCell((String) jsonObjectDetail.get("cusNameDisp"), 	getFont8(), 1, 1, Element.ALIGN_LEFT));
				table.addCell(setCell((String) jsonObjectDetail.get("motorcyclesDisp"), getFont8(), 1, 1, Element.ALIGN_LEFT));
				table.addCell(setCell((String) jsonObjectDetail.get("chassisDisp"), 	getFont8(), 1, 1, Element.ALIGN_LEFT));
				table.addCell(setCell((String) jsonObjectDetail.get("EngineNoDisp"), 	getFont8(), 1, 1, Element.ALIGN_LEFT));
				table.addCell(setCell((String) jsonObjectDetail.get("priceAmount"), 	getFont8(), 1, 1, Element.ALIGN_RIGHT));
				table.addCell(setCell((String) jsonObjectDetail.get("vatAmount"), 		getFont8(), 1, 1, Element.ALIGN_RIGHT));
				table.addCell(setCell((String) jsonObjectDetail.get("totalAmount"), 	getFont8(), 1, 1, Element.ALIGN_RIGHT));
				table.addCell(setCell((String) jsonObjectDetail.get("remark"), 			getFont8(), 1, 1, Element.ALIGN_LEFT));
			}
						
			table.addCell(setCellWB("", getFont8Bold(), 10, Element.ALIGN_CENTER, 0));
			table.addCell(setCellWB("", getFont8(), 7, Element.ALIGN_CENTER, 0));
			table.addCell(setCellWB("รวม", getFont8Bold(), 1, Element.ALIGN_RIGHT, 0));
			table.addCell(setCellWB(summaryAmount, getFont8(), 1, Element.ALIGN_RIGHT, 0));
			table.addCell(setCellWB("บาท", getFont8(), 1, Element.ALIGN_LEFT, 0));
			
			table.setWidthPercentage(100);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return table;
	}

	public PdfPTable brLine() throws DocumentException, MalformedURLException, IOException {
		
		PdfPTable 	table 			= new PdfPTable(1);
		
		table.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		
		table.setWidthPercentage(100);
	
		return table;
	}
	
}
