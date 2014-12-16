package th.go.motorcycles.app.enjoy.form;

import java.util.ArrayList;
import java.util.List;

import th.go.motorcycles.app.enjoy.bean.SummarySaleDetailBean;

public class SummarySaleDetailForm {
	
	private String 						invoiceId;
	private String 						invoiceDateFrom;
	private String 						invoiceDateTo;
	private String 						brandName;
	private String 						model;
	private String 						cusName;
	private List<SummarySaleDetailBean> dataList;
	
	public SummarySaleDetailForm(){
		this.invoiceId 				= "";
		this.invoiceDateFrom 		= "";
		this.invoiceDateTo 			= "";
		this.brandName 				= "";
		this.model 					= "";
		this.cusName 				= "";
		this.dataList				= new ArrayList<SummarySaleDetailBean>();
	}
	
	
	public List<SummarySaleDetailBean> getDataList() {
		return dataList;
	}


	public void setDataList(List<SummarySaleDetailBean> dataList) {
		this.dataList = dataList;
	}


	public String getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}
	public String getInvoiceDateFrom() {
		return invoiceDateFrom;
	}
	public void setInvoiceDateFrom(String invoiceDateFrom) {
		this.invoiceDateFrom = invoiceDateFrom;
	}
	public String getInvoiceDateTo() {
		return invoiceDateTo;
	}
	public void setInvoiceDateTo(String invoiceDateTo) {
		this.invoiceDateTo = invoiceDateTo;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	
	

}
