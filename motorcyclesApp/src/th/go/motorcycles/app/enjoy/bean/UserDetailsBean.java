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
	
	public UserDetailsBean(){
		this.userUniqueId 	= "";
		this.userId 		= "";
		this.userPassword 	= "";
		this.userName 		= "";
		this.userSurname 	= "";
		this.userPrivilege 	= "";
		this.userLevel 		= "";
		this.userStatus 	= "";
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
}
