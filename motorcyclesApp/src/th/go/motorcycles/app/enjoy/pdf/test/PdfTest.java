package th.go.motorcycles.app.enjoy.pdf.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import th.go.motorcycles.app.enjoy.dao.InvoicedetailsDao;
import th.go.motorcycles.app.enjoy.pdf.utils.PdfFormService;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfWriter;

public class PdfTest {

	public static void main(String[] args) {
		try {
//			writePDF("SlipPdfForm", "D:/motor/JSON/motor.json", "D:/motor/PDF/MotorPdfForm.pdf");
//			writePDF("SlipPdfTypeTwoForm", "D:/motor/JSON/motor.json", "D:/motor/PDF/SlipPdfTypeTwoForm.pdf");
//			writePDF("SummarySalePdfForm", "D:/motor/JSON/motor.json", "D:/motor/PDF/SummarySalePdfForm.pdf");

			writeSlipPdfFormPDFFormDB("SlipPdfForm", "5700000001", "D:/SlipPdfForm.pdf");
//			writeSlipPdfTypeTwoFormPDFFormDB("SlipPdfTypeTwoForm", "D:/SlipPdfTypeTwoForm.pdf");
//			writeSummarySalePDFFormDB("SummarySalePdfForm", "D:/SummarySalePdfForm.pdf");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void writePDF(String formName, String filePath, String pdfPath) throws Exception{
	    String 				formClass					= null;
	    JSONParser 			parser 						= null;
		Document 			document					= null;
		File 				f 							= null;
		FileOutputStream 	fos 						= null;
		PdfWriter 			writer 						= null;
		PdfFormService 		pdfForm 					= null;
		Object 				obj 						= null;
		JSONObject 			jsonObject 					= null;
		
		try{
			System.out.println("formName :: " + formName);
			
			formClass					= "th.go.motorcycles.app.enjoy.pdf."+formName;
			document 					= new Document(PageSize.A4);
			parser 						= new JSONParser();
			f 							= new File(pdfPath);
			fos            				= new FileOutputStream(f.getAbsolutePath());			
			writer 						= PdfWriter.getInstance( document,fos  );
	
			document.addTitle("Motor shop Form");
			System.out.println(formClass);
	
			Class c 					= 	Class.forName(formClass);
			pdfForm 	        		= 	(PdfFormService) c.newInstance();
		
			document.open();
			
			obj 						= parser.parse(new FileReader(filePath));
			jsonObject 					= (JSONObject) obj;
			
			pdfForm.setJSONObject(writer, jsonObject);
			pdfForm.createForm(document);
	
			document.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
	}
	
	public static void writeSlipPdfFormPDFFormDB(String formName, String invoiceId, String pdfPath) throws Exception{
	    String 				formClass					= null;
		Document 			document					= null;
		File 				f 							= null;
		FileOutputStream 	fos 						= null;
		PdfWriter 			writer 						= null;
		PdfFormService 		pdfForm 					= null;
//		JSONParser 			parser 						= null;
//		Object 				obj 						= null;
		JSONObject 			jsonObject 					= null;
		InvoicedetailsDao   invoicedetailsDao			= null;
		try{
			System.out.println("formName :: " + formName);
			
			formClass					= "th.go.motorcycles.app.enjoy.pdf."+formName;
			if (formName.equals("SummarySalePdfForm")) {
				document 				= new Document(PageSize.A4);
			} else {
				document 				= new Document(PageSize.A4);
			}	
			//parser 						= new JSONParser();
			f 							= new File(pdfPath);
			fos            				= new FileOutputStream(f.getAbsolutePath());			
			writer 						= PdfWriter.getInstance( document,fos  );
	
			document.addTitle("Motor shop Form");
			System.out.println(formClass);
	
			Class c 					= 	Class.forName(formClass);
			pdfForm 	        		= 	(PdfFormService) c.newInstance();
		
			document.open();
			
			// สร้าง json Object มาจาก DB
			invoicedetailsDao			= new InvoicedetailsDao();
			jsonObject 					= invoicedetailsDao.InvoiceSalePDF(invoiceId);
					
			pdfForm.setJSONObject(writer, jsonObject);
			pdfForm.createForm(document);
	
			document.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
	}
	
	public static void writeSummarySalePDFFormDB(String formName, String pdfPath) throws Exception{
	    String 				formClass					= null;
		Document 			document					= null;
		File 				f 							= null;
		FileOutputStream 	fos 						= null;
		PdfWriter 			writer 						= null;
		PdfFormService 		pdfForm 					= null;
//		JSONParser 			parser 						= null;
//		Object 				obj 						= null;
		JSONObject 			jsonObject 					= null;
		InvoicedetailsDao   invoicedetailsDao			= null;
		try{
			System.out.println("formName :: " + formName);
			
			formClass					= "th.go.motorcycles.app.enjoy.pdf."+formName;
			if (formName.equals("SummarySalePdfForm")) {
				document 				= new Document(PageSize.A4);
			} else {
				document 				= new Document(PageSize.A5);
			}	
			//parser 						= new JSONParser();
			f 							= new File(pdfPath);
			fos            				= new FileOutputStream(f.getAbsolutePath());			
			writer 						= PdfWriter.getInstance( document,fos  );
	
			document.addTitle("Motor shop Form");
			System.out.println(formClass);
	
			Class c 					= 	Class.forName(formClass);
			pdfForm 	        		= 	(PdfFormService) c.newInstance();
		
			document.open();
			
			// สร้าง json Object มาจาก DB
			invoicedetailsDao			= new InvoicedetailsDao();
			jsonObject 					= invoicedetailsDao.SummarySalePDF("", "");
					
			pdfForm.setJSONObject(writer, jsonObject);
			pdfForm.createForm(document);
	
			document.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
	}
}
