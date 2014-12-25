package th.go.motorcycles.app.enjoy.dao;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import th.go.motorcycles.app.enjoy.bean.UserDetailsBean;
import th.go.motorcycles.app.enjoy.utils.EnjoyConectDbs;
import th.go.motorcycles.app.enjoy.utils.EnjoyEncryptDecrypt;

public class UserDetailsDao {	
	private EnjoyConectDbs db = null;	
	public UserDetailsDao(){
		db = new EnjoyConectDbs();
	}
	
	public UserDetailsBean userSelect(String userId, String pass){
		System.out.println("[EnjoyMotorUserDao][userSelect][Begin]");
		
		String 				sql			 	= null;
		ResultSet 			rs 				= null;
		UserDetailsBean		userBean		= null;
        String				passWord		= null;
        DateFormat 			dateFormat		= null;
        Date 				date			= null;
		try{
		    dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		    date 	   = new Date();

		    passWord	= EnjoyEncryptDecrypt.enCryption(userId, pass);
//			sql 		= " SELECT userUniqueId, userId, userName, userSurname, userPrivilege, userLevel, companyId, companyName, companyAddress ";
			sql 		= " SELECT t1.userUniqueId, t1.userId, t1.userName, t1.userSurname, t1.userPrivilege, t1.userLevel, t1.companyId, ";
			sql 		= sql + " t2.companyName, t2.branchName, t2.companyAddress, t2.tin ";
			sql 		= sql + " FROM userdetails t1, company t2 where t1.companyId = t2.companyId and t1.userId = '" + userId + "' and t1.userPassword = '" + passWord + "' and t1.userStatus = 'A'";
			
			System.out.println("[EnjoyMotorUserDao][userSelect] sql :: " + sql);
			
		    rs 			= this.db.executeQuery(sql);
		    
		    while(rs.next()){
		    	userBean	= new UserDetailsBean();
		    	
		    	userBean.setUserUniqueId(rs.getString("userUniqueId"));
		    	userBean.setUserId(rs.getString("userId"));
		    	userBean.setUserName(rs.getString("userName"));
		    	userBean.setUserSurname(rs.getString("userSurname"));
		    	userBean.setUserPrivilege(rs.getString("userSurname"));
		    	userBean.setUserLevel(rs.getString("userLevel"));
		    	userBean.setCompanyId(rs.getString("companyId"));
		    	userBean.setCompanyName(rs.getString("companyName"));
		    	userBean.setTel(rs.getString("tel"));
		    	userBean.setBranchName(rs.getString("branchName"));
		    	userBean.setCompanyAddress(rs.getString("companyAddress"));
		    	userBean.setTin(rs.getString("tin"));
			    userBean.setCurrentDate(dateFormat.format(date));
		    }
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("[EnjoyMotorUserDao][userSelect][End]");
		}
		
		return userBean;
	}
}
