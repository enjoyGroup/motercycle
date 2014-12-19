package th.go.motorcycles.app.enjoy.pdf;

import java.io.ByteArrayOutputStream;

import org.json.simple.JSONObject;

import th.go.motorcycles.app.enjoy.bean.UserDetailsBean;
import th.go.motorcycles.app.enjoy.dao.InvoicedetailsDao;
import th.go.motorcycles.app.enjoy.pdf.utils.PdfFormService;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfWriter;

public class ViewPdfMainForm {
	
	public ByteArrayOutputStream writeSummarySalePDFFormDB(String formName,
														   String invoiceId, 
														   String invoiceDateFrom,
														   String invoiceDateTo,
														   String brandName,
														   String model,
														   String cusName,
														   UserDetailsBean userBean) throws Exception{
	    String 					formClass					= null;
		Document 				document					= null;
		PdfWriter 				writer 						= null;
		PdfFormService 			pdfForm 					= null;
		ByteArrayOutputStream 	buffer 						= null;
		JSONObject 				jsonObject 					= null;
		InvoicedetailsDao  	 	invoicedetailsDao			= null;
		try{
			System.out.println("formName :: " + formName);
			
			formClass					= "th.go.motorcycles.app.enjoy.pdf."+formName;
			document 					= new Document(PageSize.A4.rotate());

			buffer 						=	new ByteArrayOutputStream();
			writer 						=	PdfWriter.getInstance( document, buffer );
	
			document.addTitle("Motor shop Form");
			Class c 					= 	Class.forName(formClass);
			pdfForm 	        		= 	(PdfFormService) c.newInstance();
			document.open();
			
			// สร้าง json Object มาจาก DB
			invoicedetailsDao			= new InvoicedetailsDao();
			jsonObject 					= invoicedetailsDao.SummarySalePDF(invoiceId, invoiceDateFrom, invoiceDateTo, brandName, model, cusName, userBean);
					
			pdfForm.setJSONObject(writer, jsonObject);
			pdfForm.createForm(document);
	
			document.close();
			writer.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		    formClass					= null;
			document					= null;
			writer 						= null;
			pdfForm 					= null;
			jsonObject 					= null;
			invoicedetailsDao			= null;
		}
		return buffer;
	}

}
