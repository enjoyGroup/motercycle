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
		
		table.addCell(setCellWB("����ѷ��ԹԤ�ҹ¹�� �ӡѴ", getFont10Bold(), 1, Element.ALIGN_CENTER, 0));
		table.addCell(setCellWB("16/29-32 ����1 ��� ������ �ǧ������ ᢵ������ ��ا෾��ҹ�� 10220", getFont8Bold(), 1, Element.ALIGN_CENTER, 0));
		table.addCell(setCellWB("���Ѿ�� : 02-992-8233 ῡ�� 02-530-7035 �Ţ��Шӵ�Ǽ�����������ҡ� : 0105549002794", getFont8Bold(), 1, Element.ALIGN_CENTER, 0));
		table.addCell(setCellWB("㺡ӡѺ����/������Ѻ�Թ", getFont10Bold(), 1, Element.ALIGN_CENTER, 0));
		
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
		subTabL.addCell(setCellWB("�����١��� : ", getFont8Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("��ҧ�����ǹ�ӡѴ ����è���ҹ¹�� (�Ң��ӹѡ�ҹ�˭�)\n �Ţ��Шӵ�Ǽ���������� 0103535010232", getFont8(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont6(), 2, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("������� : ", getFont8Bold(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("9/250-251 ����. 8 �ǧ ͹��������� \nࢵ �ҧࢹ �ѧ��Ѵ ��ا෾��ҹ�� 10220", getFont8(), 1, Element.ALIGN_LEFT, 0));
		subTabL.addCell(setCellWB("", getFont6(), 2, Element.ALIGN_LEFT, 0));
		table.addCell(setCellWB(subTabL, 1, Element.ALIGN_LEFT, 0, false, false));
		
		table.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		
		subTabR.addCell(setCellWB("�Ţ���㺡ӡѺ���� : ", getFont8Bold(), 1, Element.ALIGN_RIGHT, 0));
		subTabR.addCell(setCellWB("57MC/2345", getFont8(), 1, Element.ALIGN_LEFT, 0));
		subTabR.addCell(setCellWB("�ѹ���㺡ӡѺ���� : ", getFont8Bold(), 1, Element.ALIGN_RIGHT, 0));
		subTabR.addCell(setCellWB("09/09/2557", getFont8(), 1, Element.ALIGN_LEFT, 0));
		subTabR.addCell(setCellWB("�ú��˹����� : ", getFont8Bold(), 1, Element.ALIGN_RIGHT, 0));
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
		
		table.addCell(setCellWB("�ӴѺ", getFont8Bold(), 1, Element.ALIGN_CENTER ,0));
		table.addCell(setCellWB("��������´", getFont8Bold(), 1, Element.ALIGN_LEFT ,0));
		table.addCell(setCellWB("�ӹǹ", getFont8Bold(), 1, Element.ALIGN_RIGHT ,0));
		table.addCell(setCellWB("�Ҥ�", getFont8Bold(), 1, Element.ALIGN_RIGHT ,0));
		table.addCell(setCellWB("���Թ", getFont8Bold(), 1, Element.ALIGN_RIGHT ,0));
		
		table.addCell(setCellWB("1", getFont8(), 1, Element.ALIGN_CENTER ,0));
		
		tabDetail.addCell(setCellWB("ö����ҹ¹�� ", getFont8(), 1, Element.ALIGN_LEFT, 0));
		tabDetail.addCell(setCellWB("�͹��� WW150E TH �� ��-��", getFont8(), 1, Element.ALIGN_LEFT, 0));
		tabDetail.addCell(setCellWB("�����Ţ����ͧ ", getFont8(), 1, Element.ALIGN_LEFT, 0));
		tabDetail.addCell(setCellWB("KF20E-4023025", getFont8(), 1, Element.ALIGN_LEFT, 0));
		tabDetail.addCell(setCellWB("�����Ţ��Ƕѧ ", getFont8(), 1, Element.ALIGN_LEFT, 0));
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
		subTabL.addCell(setCellWB("   �������ͧ�ѹ������ºҷ��ǹ", getFont10Bold(), 1, Element.ALIGN_LEFT ,0));
		table.addCell(setCellWB(subTabL, 1, Element.ALIGN_LEFT, 0, false, false));
		
		table.addCell(setCellWB("", getFont12Bold(), 1, Element.ALIGN_LEFT, 0));
		
		subTabR.addCell(setCellWB("", getFont6(), 2, Element.ALIGN_LEFT ,1));
		subTabR.addCell(setCellWB("�ʹ����ط��", getFont8Bold(), 1, Element.ALIGN_LEFT ,0));
		subTabR.addCell(setCellWB("67,756.01", getFont8(), 1, Element.ALIGN_RIGHT ,0));
		subTabR.addCell(setCellWB("�ǡ ������Ť������ 7 %", getFont8Bold(), 1, Element.ALIGN_LEFT ,0));
		subTabR.addCell(setCellWB("4,742.99", getFont8(), 1, Element.ALIGN_RIGHT ,0));
		subTabR.addCell(setCellWB("", getFont6(), 2, Element.ALIGN_LEFT ,1));
		subTabR.addCell(setCellWB("�ʹ����ط��", getFont8Bold(), 1, Element.ALIGN_LEFT ,0));
		subTabR.addCell(setCellWB("72,500.00", getFont8(), 1, Element.ALIGN_RIGHT ,0));
		subTabR.addCell(setCellWB("", getFont6(), 2, Element.ALIGN_LEFT ,1));
		subTabR.addCell(setCellWB("", getFont6(), 2, Element.ALIGN_LEFT ,1));
		
		subTabR.addCell(setCellWB("", getFont10Bold(), 2, Element.ALIGN_LEFT ,0));
		subTabR.addCell(setCellWB("", getFont10Bold(), 2, Element.ALIGN_LEFT ,0));
		subTabR.addCell(setCellWB("���Ѻ�Թ������Ҿ���º������ж١��ͧ����", getFont10Bold(), 2, Element.ALIGN_RIGHT ,0));
		
		table.addCell(setCellWB(subTabR, 1, Element.ALIGN_LEFT, 0, false, false));
		
		table.setWidthPercentage(100);
	
		return table;
	}
	
	private PdfPTable genFooter() throws DocumentException, MalformedURLException, IOException {
		
		float[] 	widths	 		= {40f ,30f ,30f};
		PdfPTable 	table 			= new PdfPTable(widths);
		
		table.addCell(setCellWB("�����˵�:", getFont8Bold(), 3, Element.ALIGN_LEFT, 0));
		
		table.addCell(setCellWB("", getFont8Bold(), 1, Element.ALIGN_LEFT, 0));
		table.addCell(setCellWB("����Ѻ�Թ .....................................................", getFont8Bold(), 1, Element.ALIGN_RIGHT, 0));
		table.addCell(setCellWB("����Ѻ�Թ��� .....................................................", getFont8Bold(), 1, Element.ALIGN_RIGHT, 0));
		
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
