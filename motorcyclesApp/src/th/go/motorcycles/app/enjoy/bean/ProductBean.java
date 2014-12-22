package th.go.motorcycles.app.enjoy.bean;

public class ProductBean {
	private String brandCode;
	private String brandName;
	private String model;
	private String chassis;
	private String engineNo;
	private String chassisDisp;
	private String engineNoDisp;
	private String size;
	
	public ProductBean(){
		this.brandCode 		= "";
		this.brandName 		= "";
		this.model 			= "";
		this.chassis 		= "";
		this.engineNo 		= "";
		this.chassisDisp 	= "";
		this.engineNoDisp 	= "";
		this.size 			= "";
	}


	public String getChassisDisp() {
		return chassisDisp;
	}


	public void setChassisDisp(String chassisDisp) {
		this.chassisDisp = chassisDisp;
	}


	public String getEngineNoDisp() {
		return engineNoDisp;
	}


	public void setEngineNoDisp(String engineNoDisp) {
		this.engineNoDisp = engineNoDisp;
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
	
}
