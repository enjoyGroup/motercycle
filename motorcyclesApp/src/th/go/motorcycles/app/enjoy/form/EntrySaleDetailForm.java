package th.go.motorcycles.app.enjoy.form;

import th.go.motorcycles.app.enjoy.bean.CustomerBean;
import th.go.motorcycles.app.enjoy.bean.ProductBean;

public class EntrySaleDetailForm {
	
	private CustomerBean 						customerBean;
	private ProductBean							productBean;
	private String								invoiceId;
	private String								recordAddDate;
	private String 								priceAmount;
	private String 								vatAmount;
	private String 								remark;
	private String 								commAmount;
	private String 								flagAddSales;
	
	public EntrySaleDetailForm(){
		this.customerBean 				= new CustomerBean();
		this.productBean 				= new ProductBean();
		this.invoiceId					= "";
		this.recordAddDate				= "";
		this.priceAmount				= "";
		this.vatAmount					= "";
		this.remark						= "";
		this.commAmount					= "";
		this.flagAddSales				= "";
	}

	public CustomerBean getCustomerBean() {
		return customerBean;
	}

	public void setCustomerBean(CustomerBean customerBean) {
		this.customerBean = customerBean;
	}

	public ProductBean getProductBean() {
		return productBean;
	}

	public void setProductBean(ProductBean productBean) {
		this.productBean = productBean;
	}

	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getRecordAddDate() {
		return recordAddDate;
	}

	public void setRecordAddDate(String recordAddDate) {
		this.recordAddDate = recordAddDate;
	}

	public String getPriceAmount() {
		return priceAmount;
	}

	public void setPriceAmount(String priceAmount) {
		this.priceAmount = priceAmount;
	}

	public String getVatAmount() {
		return vatAmount;
	}

	public void setVatAmount(String vatAmount) {
		this.vatAmount = vatAmount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCommAmount() {
		return commAmount;
	}

	public void setCommAmount(String commAmount) {
		this.commAmount = commAmount;
	}

	public String getFlagAddSales() {
		return flagAddSales;
	}

	public void setFlagAddSales(String flagAddSales) {
		this.flagAddSales = flagAddSales;
	}
	
}
