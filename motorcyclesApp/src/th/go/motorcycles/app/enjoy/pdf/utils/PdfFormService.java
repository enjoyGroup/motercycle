package th.go.motorcycles.app.enjoy.pdf.utils;

import org.json.simple.JSONObject;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;

public interface PdfFormService {
	public Document createForm(Document document);
	public void setJSONObject(PdfWriter writer, JSONObject jsonObject);
}
