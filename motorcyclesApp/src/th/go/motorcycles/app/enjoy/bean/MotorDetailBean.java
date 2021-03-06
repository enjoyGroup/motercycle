package th.go.motorcycles.app.enjoy.bean;

import java.io.Serializable;

public class MotorDetailBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String brandSearch;
	private String modelSearch;
	private String companySearch;
	private String motorcyclesCode;
	private String brandCode;
	private String brandName;
	private String model;
	private String chassis;
	private String engineNo;
	private String size;
	private String companyId;
	private String companyName;
	private String branchName;
	private String motorcyclesStatus;
	private	String errMsg;
	
	
	public MotorDetailBean(){
		this.brandSearch 			= "";
		this.modelSearch 			= "";
		this.companySearch			= "";
		this.motorcyclesCode 		= "";
		this.brandCode       		= "";
		this.brandName 				= "";
		this.model 					= "";
		this.chassis 		    	= "";
		this.engineNo 				= "";
		this.size 					= "";
		this.companyId 				= "";
		this.companyName 			= "";
		this.branchName				= "";
		this.motorcyclesStatus 		= "";
		this.errMsg				    = "";
	}

	public String getBranchName() {
		return branchName;
	}


	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}


	public String getBrandSearch() {
		return brandSearch;
	}

	public void setBrandSearch(String brandSearch) {
		this.brandSearch = brandSearch;
	}

	public String getModelSearch() {
		return modelSearch;
	}

	public void setModelSearch(String modelSearch) {
		this.modelSearch = modelSearch;
	}

	public String getMotorcyclesCode() {
		return motorcyclesCode;
	}

	
	public String getCompanySearch() {
		return companySearch;
	}

	public void setCompanySearch(String companySearch) {
		this.companySearch = companySearch;
	}

	public void setMotorcyclesCode(String motorcyclesCode) {
		this.motorcyclesCode = motorcyclesCode;
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
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

	public String getChassis() {
		return chassis;
	}

	public void setChassis(String chassis) {
		this.chassis = chassis;
	}

	public String getEngineNo() {
		return engineNo;
	}

	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getMotorcyclesStatus() {
		return motorcyclesStatus;
	}

	public void setMotorcyclesStatus(String motorcyclesStatus) {
		this.motorcyclesStatus = motorcyclesStatus;
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

	@Override
	public String toString() {
		return "MotorDetailBean [brandSearch=" + brandSearch + ", modelSearch="
				+ modelSearch + ", companySearch=" + companySearch
				+ ", motorcyclesCode=" + motorcyclesCode + ", brandCode="
				+ brandCode + ", brandName=" + brandName + ", model=" + model
				+ ", chassis=" + chassis + ", engineNo=" + engineNo + ", size="
				+ size + ", companyId=" + companyId + ", companyName="
				+ companyName + ", branchName=" + branchName
				+ ", motorcyclesStatus=" + motorcyclesStatus + "]";
	}
	
	
}
