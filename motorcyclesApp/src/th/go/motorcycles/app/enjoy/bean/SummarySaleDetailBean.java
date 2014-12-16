package th.go.motorcycles.app.enjoy.bean;

public class SummarySaleDetailBean {
	
	private String 						invoiceId;
	private String 						cusName;
	private String						motorcyclesdetails;
	private String						priceAmount;
	private String						vatAmount;
	private String						commAmount;
	private String						remark;
	
	public SummarySaleDetailBean(){
		this.invoiceId 				= "";
		this.cusName 				= "";
		this.motorcyclesdetails 	= "";
		this.priceAmount 			= "";
		this.vatAmount	 			= "";
		this.commAmount 			= "";
		this.remark					= "";
	}

	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getMotorcyclesdetails() {
		return motorcyclesdetails;
	}

	public void setMotorcyclesdetails(String motorcyclesdetails) {
		this.motorcyclesdetails = motorcyclesdetails;
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

	public String getCommAmount() {
		return commAmount;
	}

	public void setCommAmount(String commAmount) {
		this.commAmount = commAmount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
