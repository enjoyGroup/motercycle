package th.go.motorcycles.app.enjoy.bean;

import java.io.Serializable;

public class BrandBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String brandCode;
	private String brandName;
	private String brandStatus; 
	
	public BrandBean(){
		this.brandCode 			= "";
		this.brandName 			= "";
		this.brandStatus        = "A"; 
	}

	public String getBrandCode() {
		return brandCode;
	}
 

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}
 

	public String getBrandStatus() {
		return brandStatus;
	} 

	public void setBrandStatus(String brandStatus) {
		this.brandStatus = brandStatus;
	}
 
	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
