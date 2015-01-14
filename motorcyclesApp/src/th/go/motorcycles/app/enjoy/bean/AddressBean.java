package th.go.motorcycles.app.enjoy.bean;

import th.go.motorcycles.app.enjoy.main.Constants;

public class AddressBean {
	private String 						provinceId;
	private String 						districtId;
	private String 						subdistrictId;
	private	String						errMsg;
	private String 						errType;
	
	public AddressBean(){
		this.provinceId 				= "";
		this.districtId 				= "";
		this.subdistrictId	 			= "";
		this.errMsg						= "";
		this.errType					= Constants.ERR_ERROR;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public String getSubdistrictId() {
		return subdistrictId;
	}

	public void setSubdistrictId(String subdistrictId) {
		this.subdistrictId = subdistrictId;
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
	
}
