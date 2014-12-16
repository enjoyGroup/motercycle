package th.go.motorcycles.app.enjoy.bean;

public class AddressBean {
	private String 						provinceId;
	private String 						districtId;
	private String 						subdistrictId;
	private	String						errMsg;
	
	public AddressBean(){
		this.provinceId 				= "";
		this.districtId 				= "";
		this.subdistrictId	 			= "";
		this.errMsg						= "";
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
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
	
	

	
}
