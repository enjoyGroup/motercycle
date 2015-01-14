package th.go.motorcycles.app.enjoy.bean;

import th.go.motorcycles.app.enjoy.main.Constants;

public class EntrySaleDetailBean {
	private String invoiceId;
	private String errMsg;
	private String errType;
	private String motorcyclesCode;
	
	public EntrySaleDetailBean(){
		this.invoiceId 			= "";
		this.errMsg 			= "";
		this.motorcyclesCode 	= "";
		this.errType			= Constants.ERR_ERROR;
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

	public String getErrType() {
		return errType;
	}

	public void setErrType(String errType) {
		this.errType = errType;
	}

	public String getMotorcyclesCode() {
		return motorcyclesCode;
	}

	public void setMotorcyclesCode(String motorcyclesCode) {
		this.motorcyclesCode = motorcyclesCode;
	}

}
