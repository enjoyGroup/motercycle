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
		
		table.addCell(setCellWB("����ѷ��ԹԤ�ҹ¹�� �ӡѴ", getFont10Bold(), 1, Element.ALIGN_CENTER, 0));
		table.addCell(setCellWB("16/29-32 ����1 ��� ������ �ǧ������ ᢵ������ ��ا෾��ҹ�� 10220", getFont8Bold(), 1, Element.ALIGN_CENTER, 0));
		table.addCell(setCellWB("���Ѿ�� : 02-992-8233 ῡ�� 02-530-7035 �Ţ��Шӵ�Ǽ�����������ҡ� : 0105549002794", getFont8Bold(), 1, Element.ALIGN_CENTER, 0));
//		table.addCell(setCellWB("�ѹ��� 24 ��Ȩԡ�¹ 2557 �֧�ѹ��� 26 ��Ȩԡ�¹ 2557", getFont10Bold(), 1, Element.ALIGN_CENTER, 0));
		
		table.setWidthPercentage(100);
	
		return table;
	}
	
	private PdfPTable genProduct() throws DocumentException, MalformedURLException, IOException {
		
		float[] 	widths	 		= {5f ,10f ,15f ,18f ,10f ,10f ,10f, 15f};
		PdfPTable 	table 			= new PdfPTable(widths);
		JSONObject 	jsonObjectMain  = null;
		JSONObject 	jsonObjectDetail= null;
		JSONArray 	listJSONArray 	= null;
		
		try {
			table.addCell(setCellWB("�ѹ��� 24 ��Ȩԡ�¹ 2557 �֧ �ѹ��� 26 ��Ȩԡ�¹ 2557", getFont8Bold(), 8, Element.ALIGN_CENTER, 0));
			
			table.addCell(setCell("�ӴѺ", getFont8Bold(), 1, 1, Element.ALIGN_CENTER));
			table.addCell(setCell("�Ţ㺡ӡѺ����", getFont8Bold(), 1, 1, Element.ALIGN_CENTER));
			table.addCell(setCell("�����١���", getFont8Bold(), 1, 1, Element.ALIGN_CENTER));
			table.addCell(setCell("��������´ö¹��", getFont8Bold(), 1, 1, Element.ALIGN_CENTER));
			table.addCell(setCell("�ҤҢ��", getFont8Bold(), 1, 1, Element.ALIGN_CENTER));
			table.addCell(setCell("�Ҥ�����", getFont8Bold(), 1, 1, Element.ALIGN_CENTER));
			table.addCell(setCell("�ҤҢ���ط��", getFont8Bold(), 1, 1, Element.ALIGN_CENTER));
			table.addCell(setCell("�����˵�", getFont8Bold(), 1, 1, Element.ALIGN_CENTER));
				
			// �֧�����Ţ�����ʴ���˹�Ҩ�
			jsonObjectMain = this.formDataObj;
			listJSONArray  = (JSONArray) jsonObjectMain.get("invoicelist");
			
			for(int i=0;i<listJSONArray.size();i++){
				jsonObjectDetail = (JSONObject) listJSONArray.get(i);

				table.addCell(setCell(String.valueOf(i + 1), getFont8(), 1, 1, Element.ALIGN_CENTER));
				table.addCell(setCell((String) jsonObjectDetail.get("invoiceId"), 		getFont8(), 1, 1, Element.ALIGN_CENTER));
				table.addCell(setCell((String) jsonObjectDetail.get("cusNameDisp"), 	getFont8(), 1, 1, Element.ALIGN_LEFT));
				table.addCell(setCell((String) jsonObjectDetail.get("motorcyclesDisp"), getFont8(), 1, 1, Element.ALIGN_LEFT));
				table.addCell(setCell((String) jsonObjectDetail.get("priceAmount"), 	getFont8(), 1, 1, Element.ALIGN_LEFT));
				table.addCell(setCell((String) jsonObjectDetail.get("vatAmount"), 		getFont8(), 1, 1, Element.ALIGN_RIGHT));
				table.addCell(setCell((String) jsonObjectDetail.get("totalAmount"), 	getFont8(), 1, 1, Element.ALIGN_RIGHT));
				table.addCell(setCell((String) jsonObjectDetail.get("remark"), 			getFont8(), 1, 1, Element.ALIGN_RIGHT));
			}
						
			table.addCell(setCellWB("", getFont8(), 4, Element.ALIGN_CENTER, 0));
			table.addCell(setCellWB("���", getFont8Bold(), 1, Element.ALIGN_RIGHT, 0));
			table.addCell(setCellWB("117,757.01", getFont8(), 1, Element.ALIGN_RIGHT, 0));
			table.addCell(setCellWB("", getFont8(), 1, Element.ALIGN_LEFT, 0));
			
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
