package th.go.motorcycles.app.enjoy.form;


public class AddressDemoForm {
	
	private String 						province;
	private String 						district;
	private String 						subdistrict;
	
	public AddressDemoForm(){
		this.province 				= "";
		this.district 				= "";
		this.subdistrict 			= "";
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getSubdistrict() {
		return subdistrict;
	}

	public void setSubdistrict(String subdistrict) {
		this.subdistrict = subdistrict;
	}	

}
