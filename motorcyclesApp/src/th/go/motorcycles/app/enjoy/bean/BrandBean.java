package th.go.motorcycles.app.enjoy.bean;

import java.io.Serializable;

public class BrandBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String brandCode;
	private String brandtName;
	private String brandtSurname;
	private String brandtFullname;
	private String brandName ;
	private String brandStatus; 
	private	String errMsg;
	
	public BrandBean(){
		this.brandCode 			= "";
		this.brandName       	= "";
		this.brandtName 		= "";
		this.brandtSurname 		= "";
		this.brandtFullname     = "";
		this.brandStatus        = ""; 
		this.errMsg				= "";
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public String getBrandtName() {
		return brandtName;
	}

	public void setBrandtName(String brandtName) {
		this.brandtName = brandtName;
	}

	public String getBrandtSurname() {
		return brandtSurname;
	}

	public void setBrandtSurname(String brandtSurname) {
		this.brandtSurname = brandtSurname;
	}

	public String getBrandtFullname() {
		return brandtFullname;
	}

	public void setBrandtFullname(String brandtFullname) {
		this.brandtFullname = brandtFullname;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getBrandStatus() {
		return brandStatus;
	}

	public void setBrandStatus(String brandStatus) {
		this.brandStatus = brandStatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
	
}
