package th.go.motorcycles.app.enjoy.pdf.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import th.go.motorcycles.app.enjoy.bean.UserDetailsBean;
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

//			writeSlipPdfFormPDFFormDB("SlipPdfForm", "5700000002", "D:/SlipPdfForm.pdf");
			writeSlipPdfTypeTwoFormPDFFormDB("SlipPdfTypeTwoForm", "5700000002", "D:/SlipPdfTypeTwoForm.pdf");
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
		UserDetailsBean 	userBean					= null;
		try{
			System.out.println("formName :: " + formName);
			
			formClass					= "th.go.motorcycles.app.enjoy.pdf."+formName;
			document 					= new Document(PageSize.A5.rotate());
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
			userBean					= new UserDetailsBean();
			userBean.setCompanyName("บริษัท สหมอเตอร์ไซด์ จำกัด (สำนักงานใหญ่)");
			userBean.setTel("02-5215365");
			userBean.setCompanyAddress("10/102-104 ม.4 ถ.นนทบุรี-ปทุมธานี ต.บางขะแยง อ.เมือง จ.ปทุมธานี 12000");
			userBean.setBranchName("สำนักงานใหญ่");
			userBean.setTin("0135552000683");
			jsonObject 					= invoicedetailsDao.InvoiceSalePDF(invoiceId,userBean);
					
			pdfForm.setJSONObject(writer, jsonObject);
			pdfForm.createForm(document);
	
			document.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
	}
	
	public static void writeSlipPdfTypeTwoFormPDFFormDB(String formName, String invoiceId, String pdfPath) throws Exception{
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
		UserDetailsBean 	userBean					= null;
		try{
			System.out.println("formName :: " + formName);
			
			formClass					= "th.go.motorcycles.app.enjoy.pdf."+formName;
			document 					= new Document(PageSize.A5.rotate());
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
			userBean					= new UserDetailsBean();
			userBean.setCompanyName("บริษัท สหมอเตอร์ไซด์ จำกัด (สำนักงานใหญ่)");
			userBean.setTel("02-5215365");
			userBean.setCompanyAddress("10/102-104 ม.4 ถ.นนทบุรี-ปทุมธานี ต.บางขะแยง อ.เมือง จ.ปทุมธานี 12000");
			userBean.setBranchName("สำนักงานใหญ่");
			userBean.setTin("0135552000683");
			jsonObject 					= invoicedetailsDao.InvoiceSalePDF(invoiceId,userBean);
					
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
		UserDetailsBean 	userBean					= null;
		try{
			System.out.println("formName :: " + formName);
			
			formClass					= "th.go.motorcycles.app.enjoy.pdf."+formName;
			document 				= new Document(PageSize.A4.rotate());
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
			userBean					= new UserDetailsBean();
			userBean.setCompanyName("บริษัท สหมอเตอร์ไซด์ จำกัด (สำนักงานใหญ่)");
			userBean.setCompanyAddress("10/102-104 ม.4 ถ.นนทบุรี-ปทุมธานี ต.บางขะแยง อ.เมือง จ.ปทุมธานี 12000");
			userBean.setBranchName("สำนักงานใหญ่");
			userBean.setTin("0135552000683");
			jsonObject 					= invoicedetailsDao.SummarySalePDF("", "", "" , "","","",userBean);
					
			pdfForm.setJSONObject(writer, jsonObject);
			pdfForm.createForm(document);
	
			document.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
	}
}
