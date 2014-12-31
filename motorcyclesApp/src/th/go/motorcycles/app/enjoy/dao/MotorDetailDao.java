package th.go.motorcycles.app.enjoy.dao;

import java.sql.ResultSet; 
import java.util.ArrayList;
import java.util.List;

import th.go.motorcycles.app.enjoy.bean.MotorDetailBean;
import th.go.motorcycles.app.enjoy.exception.EnjoyException;
import th.go.motorcycles.app.enjoy.utils.EnjoyConectDbs; 
import th.go.motorcycles.app.enjoy.utils.EnjoyUtils;

public class MotorDetailDao {	 
	private EnjoyConectDbs db = null;	
	
	public MotorDetailDao(){
		db = new EnjoyConectDbs();
	}
	
	public List<MotorDetailBean> findModelDetail(MotorDetailBean bean){
		String 					sql			 		= null;
		ResultSet 				rs 					= null; 
		MotorDetailBean			motorDetailBean		= null;
		List<MotorDetailBean>	list				= null;	         
		
		try{ 
			System.out.println(bean.getBrandSearch());
			System.out.println(bean.getModelSearch());

			sql = "SELECT * FROM motorcyclesdetails m "
					+ " LEFT JOIN branddetails b ON m.brandCode=b.brandCode"
					+ " LEFT JOIN company c ON m.companyId=c.companyId"
					+ " WHERE  motorcyclesStatus = 'A'";
			
			if( bean.getBrandSearch() == "" && ( bean.getModelSearch() == "" )){
				sql += " order by motorcyclesCode";
			}else{
				if(bean.getBrandSearch()!=""){
					sql += " and b.brandName = '" + bean.getBrandSearch() + "'";
				}
				if(bean.getModelSearch()!=""){
					sql += " and m.model = '" + bean.getModelSearch() + "'";
				}
				 
			} 	 
			   
			list		= new ArrayList<MotorDetailBean>();
			
			System.out.println("[MotorDetailDao][findModelDetail] sql :: " + sql);
			
		    rs 			= this.db.executeQuery(sql);
		    
		    while(rs.next()){
                motorDetailBean	= new MotorDetailBean();  
		    	motorDetailBean.setMotorcyclesCode(EnjoyUtils.nullToStr(rs.getString("motorcyclesCode")));
		    	motorDetailBean.setBrandCode(EnjoyUtils.nullToStr(rs.getString("brandCode"))); 
		    	motorDetailBean.setBrandName(EnjoyUtils.nullToStr(rs.getString("brandName")));  
				motorDetailBean.setModel(EnjoyUtils.nullToStr(rs.getString("model")));
				motorDetailBean.setChassis(EnjoyUtils.nullToStr(rs.getString("chassis")));
				motorDetailBean.setEngineNo(EnjoyUtils.nullToStr(rs.getString("engineNo")));
				motorDetailBean.setSize(EnjoyUtils.nullToStr(rs.getString("size")));
				motorDetailBean.setCompanyId(EnjoyUtils.nullToStr(rs.getString("companyId")));
				motorDetailBean.setCompanyName(EnjoyUtils.nullToStr(rs.getString("companyName")));
				motorDetailBean.setMotorcyclesStatus(EnjoyUtils.nullToStr(rs.getString("motorcyclesStatus")));
		    	list.add(motorDetailBean);	
		    } 
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("[MotorDetailDao][findModelDetail][End]");
		}
		
		return list;
	}
	
	public List<String> brandNameList(String brandName){
		System.out.println("[MotorDetailDao][brandNameList][Begin]");
		
		String 							sql			 		= null;
		ResultSet 						rs 					= null;
        List<String> 					list 				= new ArrayList<String>();
		
		try{
			sql 		= " SELECT brandName FROM branddetails WHERE brandName LIKE ('"+brandName+"%') ORDER BY brandName ASC LIMIT 10 ";
			
			System.out.println("[MotorDetailDao][brandNameList] sql :: " + sql);
			
		    rs 			= this.db.executeQuery(sql);
		    
		    while(rs.next()){
		    	
		    	list.add(EnjoyUtils.nullToStr(rs.getString("brandName")));
		    }
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("[MotorDetailDao][brandNameList][End]");
		}
		
		return list;
	}
	
	public List<String> modelList(String brandName, String model){
		System.out.println("[MotorDetailDao][modelList][Begin]");
		
		String 							sql			 		= null;
		ResultSet 						rs 					= null;
        List<String> 					list 				= new ArrayList<String>();
        String							brandCode			= null;
		
		try{
			
			if(!brandName.equals("")){
				sql 		= " SELECT brandCode FROM branddetails WHERE brandName = '" + brandName + "'";
				
				System.out.println("[MotorDetailDao][modelList] brandCode sql :: " + sql);
				
			    rs 			= this.db.executeQuery(sql);
			    while(rs.next())brandCode = EnjoyUtils.nullToStr(rs.getString("brandCode"));
			}
			
			if(brandCode == null){
				sql 		= " SELECT model FROM motorcyclesdetails WHERE model LIKE ('"+model+"%') AND motorcyclesStatus = 'A' ORDER BY model ASC LIMIT 10 ";
			}else{
				sql 		= " SELECT model FROM motorcyclesdetails WHERE brandCode = '" + brandCode + "' AND model LIKE ('"+model+"%') AND motorcyclesStatus = 'A' ORDER BY model ASC LIMIT 10 ";
			}
			
			System.out.println("[MotorDetailDao][modelList] sql :: " + sql);
			
		    rs 			= this.db.executeQuery(sql);
		    
		    while(rs.next()){
		    	
		    	list.add(EnjoyUtils.nullToStr(rs.getString("model")));
		    }
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("[MotorDetailDao][modelList][End]");
		}
		
		return list;
	}
	
	public List<String> branchNameList(String branchName){
		System.out.println("[MotorDetailDao][branchNameList][Begin]");
		
		String 							sql			 		= null;
		ResultSet 						rs 					= null;
        List<String> 					list 				= new ArrayList<String>();
		
		try{
			sql 		= " SELECT branchName FROM company WHERE branchName LIKE ('" + branchName + "%') ORDER BY branchName ASC LIMIT 10 ";
			
			System.out.println("[MotorDetailDao][branchNameList] sql :: " + sql);
			
		    rs 			= this.db.executeQuery(sql);
		    
		    while(rs.next()){
		    	
		    	list.add(EnjoyUtils.nullToStr(rs.getString("branchName")));
		    }
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("[MotorDetailDao][brandNameList][End]");
		}
		
		return list;
	}
}
