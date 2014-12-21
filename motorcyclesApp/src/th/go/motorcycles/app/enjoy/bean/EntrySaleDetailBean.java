package th.go.motorcycles.app.enjoy.bean;

public class EntrySaleDetailBean {
	private String invoiceId;
	private String errMsg;
	private String motorcyclesCode;
	
	public EntrySaleDetailBean(){
		this.invoiceId 			= "";
		this.errMsg 			= "";
		this.motorcyclesCode 	= "";
	}

	public String getMotorcyclesCode() {
		return motorcyclesCode;
	}

	public void setMotorcyclesCode(String motorcyclesCode) {
		this.motorcyclesCode = motorcyclesCode;
	}

	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

}
