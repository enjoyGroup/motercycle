package th.go.motorcycles.app.enjoy.bean;

public class UserDetailsBean {
	private String userUniqueId;
	private String userId;
	private String userPassword;
	private String userName;
	private String userSurname;
	private String userPrivilege;
	private String userLevel;
	private String userStatus;
	private String companyId;
	private String companyName;
	private String companyAddress;
	private String currentDate;
	
	public UserDetailsBean(){
		this.userUniqueId 	= "";
		this.userId 		= "";
		this.userPassword 	= "";
		this.userName 		= "";
		this.userSurname 	= "";
		this.userPrivilege 	= "";
		this.userLevel 		= "";
		this.userStatus 	= "";
		this.companyId 		= "";
		this.companyName 	= "";
		this.companyAddress	= "";
		this.currentDate	= "";
	}

	public String getUserUniqueId() {
		return userUniqueId;
	}

	public void setUserUniqueId(String userUniqueId) {
		this.userUniqueId = userUniqueId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserSurname() {
		return userSurname;
	}

	public void setUserSurname(String userSurname) {
		this.userSurname = userSurname;
	}

	public String getUserPrivilege() {
		return userPrivilege;
	}

	public void setUserPrivilege(String userPrivilege) {
		this.userPrivilege = userPrivilege;
	}

	public String getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
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

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}
	
}
