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
	private String 								totalAmount;
	private String 								remark;
	private String 								commAmount;
	private String 								flagAddSales;
	private String 								printType;
	private String 								vatFlag;
	private String								cusCode;
	private String								motorcyclesCode;
	private String								userUniqueId;
	
	public EntrySaleDetailForm(){
		this.customerBean 				= new CustomerBean();
		this.productBean 				= new ProductBean();
		this.invoiceId					= "";
		this.recordAddDate				= "";
		this.priceAmount				= "0.00";
		this.vatAmount					= "0.00";
		this.totalAmount				= "0.00";
		this.remark						= "";
		this.commAmount					= "0.00";
		this.flagAddSales				= "N";
		this.printType					= "1";
		this.vatFlag					= "N";
		this.cusCode					= "";
		this.motorcyclesCode			= "";
		this.userUniqueId				= "";
	}

	public String getCusCode() {
		return cusCode;
	}

	public void setCusCode(String cusCode) {
		this.cusCode = cusCode;
	}

	public String getMotorcyclesCode() {
		return motorcyclesCode;
	}

	public void setMotorcyclesCode(String motorcyclesCode) {
		this.motorcyclesCode = motorcyclesCode;
	}

	public String getUserUniqueId() {
		return userUniqueId;
	}

	public void setUserUniqueId(String userUniqueId) {
		this.userUniqueId = userUniqueId;
	}

	public String getVatFlag() {
		return vatFlag;
	}

	public void setVatFlag(String vatFlag) {
		this.vatFlag = vatFlag;
	}

	public String getPrintType() {
		return printType;
	}

	public void setPrintType(String printType) {
		this.printType = printType;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
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
