package th.go.motorcycles.app.enjoy.bean;

import java.io.Serializable;

public class CustomerBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String cusCode;
	private String custName;
	private String custSurname;
	private String custFullname;
	private String houseNumber;
	private String mooNumber;
	private String soiName;
	private String streetName;
	private String subdistrictName;
	private String subdistrictCode;
	private String districtName;
	private String districtCode;
	private String provinceName;
	private String provinceCode;
	private String idType;
	private String idNumber;
	private String cusStatus; 
	private String address ;
	
	public CustomerBean(){
		this.cusCode 			= "";
		this.custName 			= "";
		this.custSurname 		= "";
		this.custFullname       = "";
		this.houseNumber 		= "";
		this.mooNumber 			= "";
		this.soiName 		    = "";
		this.streetName 		= "";
		this.subdistrictName 	= "";
		this.subdistrictCode 	= "";
		this.districtName 		= "";
		this.districtCode 		= "";
		this.provinceName 		= "";
		this.provinceCode 		= "";
		this.idType 		    = "";
		this.idNumber 		    = "";
		this.cusStatus          = "A"; 
		this.address            = "";
	}

 
 

	public String getCusCode() {
		return cusCode;
	}
 

	public void setCusCode(String cusCode) {
		this.cusCode = cusCode;
	}
 

	public String getCusStatus() {
		return cusStatus;
	} 

	public void setCusStatus(String cusStatus) {
		this.cusStatus = cusStatus;
	}
 
	public String getIdType() {
		return idType;
	}
  
	public void setIdType(String idType) {
		this.idType = idType;
	} 

	public String getIdNumber() {
		return idNumber;
	}
  
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
 

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustSurname() {
		return custSurname;
	}

	public void setCustSurname(String custSurname) {
		this.custSurname = custSurname;
	}

	public String getCustFullname() {
		return custFullname;
	} 

	public void setCustFullname(String custFullname) {
		this.custFullname = custFullname;
	}
 
	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getMooNumber() {
		return mooNumber;
	}

	public void setMooNumber(String mooNumber) {
		this.mooNumber = mooNumber;
	}
 

	public String getSoiName() {
		return soiName;
	}

  
	public void setSoiName(String soiName) {
		this.soiName = soiName;
	}
 

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getSubdistrictName() {
		return subdistrictName;
	}

	public void setSubdistrictName(String subdistrictName) {
		this.subdistrictName = subdistrictName;
	}

	public String getSubdistrictCode() {
		return subdistrictCode;
	}

	public void setSubdistrictCode(String subdistrictCode) {
		this.subdistrictCode = subdistrictCode;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
 
	public String getAddress() {
		return address;
	}
 
	public void setAddress(String address) {
		this.address = address;
	}
 
	
}
