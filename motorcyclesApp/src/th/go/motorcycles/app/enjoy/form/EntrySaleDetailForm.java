package th.go.motorcycles.app.enjoy.form;

import th.go.motorcycles.app.enjoy.bean.CustomerBean;
import th.go.motorcycles.app.enjoy.bean.ProductBean;
import th.go.motorcycles.app.enjoy.main.ConfigFile;

public class EntrySaleDetailForm {
	
	/*Flag สำหรับเก็บ A- ใบเพิ่มหนี้ , C- ใบลดหนี้, N- ไม่มีเพิ่มเติม*/
	public static final String FLAG_A 		= "A";
	public static final String FLAG_C 		= "C";
	public static final String FLAG_N 		= "N";
	
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
//	private String 								vatFlag;
	private String								cusCode;
	private String								motorcyclesCode;
	private String								userUniqueId;
	private String								color;
	private String								invoiceIdAddSales;
	private String								flagCredit;
	private String								creditAmount;
	private String								vat;
	private String								userLevel;
	private String 								formatInvoie;
	private String 								remarkAddSales;
	private String 								commVatAmount;
	private String 								commTotalAmount;
	private String								creditVatAmount;
	private String								creditTotalAmount;
	
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
//		this.vatFlag					= "N";
		this.cusCode					= "";
		this.motorcyclesCode			= "";
		this.userUniqueId				= "";
		this.color						= "";
		this.invoiceIdAddSales			= "";
		this.flagCredit					= FLAG_N;
		this.creditAmount				= "0.00";
		this.vat						= ConfigFile.getVAT_RATE();
		this.userLevel					= "";
		this.formatInvoie				= "";
		this.remarkAddSales				= "";
		this.commVatAmount				= "0.00";
		this.commTotalAmount			= "0.00";
		this.creditVatAmount			= "0.00";
		this.creditTotalAmount			= "0.00";
	}

	public String getCreditVatAmount() {
		return creditVatAmount;
	}

	public void setCreditVatAmount(String creditVatAmount) {
		this.creditVatAmount = creditVatAmount;
	}

	public String getCreditTotalAmount() {
		return creditTotalAmount;
	}

	public void setCreditTotalAmount(String creditTotalAmount) {
		this.creditTotalAmount = creditTotalAmount;
	}

	public String getRemarkAddSales() {
		return remarkAddSales;
	}

	public void setRemarkAddSales(String remarkAddSales) {
		this.remarkAddSales = remarkAddSales;
	}

	public String getCommVatAmount() {
		return commVatAmount;
	}

	public void setCommVatAmount(String commVatAmount) {
		this.commVatAmount = commVatAmount;
	}

	public String getCommTotalAmount() {
		return commTotalAmount;
	}

	public void setCommTotalAmount(String commTotalAmount) {
		this.commTotalAmount = commTotalAmount;
	}

	public String getFormatInvoie() {
		return formatInvoie;
	}

	public void setFormatInvoie(String formatInvoie) {
		this.formatInvoie = formatInvoie;
	}

	public String getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}

	public String getVat() {
		return vat;
	}

	public void setVat(String vat) {
		this.vat = vat;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getInvoiceIdAddSales() {
		return invoiceIdAddSales;
	}

	public void setInvoiceIdAddSales(String invoiceIdAddSales) {
		this.invoiceIdAddSales = invoiceIdAddSales;
	}

	public String getFlagCredit() {
		return flagCredit;
	}

	public void setFlagCredit(String flagCredit) {
		this.flagCredit = flagCredit;
	}

	public String getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(String creditAmount) {
		this.creditAmount = creditAmount;
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

//	public String getVatFlag() {
//		return vatFlag;
//	}
//
//	public void setVatFlag(String vatFlag) {
//		this.vatFlag = vatFlag;
//	}

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
