package th.go.motorcycles.app.enjoy.dao;

import java.sql.ResultSet; 
import java.util.ArrayList;
import java.util.List;

import th.go.motorcycles.app.enjoy.bean.CustomerBean;
import th.go.motorcycles.app.enjoy.bean.MotorDetailBean;
import th.go.motorcycles.app.enjoy.exception.EnjoyException;
import th.go.motorcycles.app.enjoy.utils.EnjoyConectDbs; 
import th.go.motorcycles.app.enjoy.utils.EnjoyUtils;

public class MotorDetailDao {	 
	private EnjoyConectDbs db = null;	
	
	public MotorDetailDao(){
//		db = new EnjoyConectDbs();
	}
	
	public List<MotorDetailBean> findModelDetail(MotorDetailBean bean){
		String 					sql			 		= null;
		ResultSet 				rs 					= null; 
		MotorDetailBean			motorDetailBean		= null;
		List<MotorDetailBean>	list				= null;	         
		
		try{ 
			System.out.println(bean.getBrandSearch());
			System.out.println(bean.getModelSearch());

			this.db    	= new EnjoyConectDbs();
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
			this.db.setDisconnection(rs);
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
			this.db    	= new EnjoyConectDbs();
			sql 		= " SELECT brandName FROM branddetails WHERE brandName LIKE ('"+brandName+"%') ORDER BY brandName ASC LIMIT 10 ";
			
			System.out.println("[MotorDetailDao][brandNameList] sql :: " + sql);
			
		    rs 			= this.db.executeQuery(sql);		    
		    while(rs.next()){		    	
		    	list.add(EnjoyUtils.nullToStr(rs.getString("brandName")));
		    }			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.db.setDisconnection(rs);
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
			this.db    		= new EnjoyConectDbs();			
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
			this.db.setDisconnection(rs);
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
			this.db    	= new EnjoyConectDbs();
			sql 		= " SELECT branchName FROM company WHERE branchName LIKE ('" + branchName + "%') ORDER BY branchName ASC LIMIT 10 ";			
			System.out.println("[MotorDetailDao][branchNameList] sql :: " + sql);			
		    rs 			= this.db.executeQuery(sql);		    
		    while(rs.next()){		    	
		    	list.add(EnjoyUtils.nullToStr(rs.getString("branchName")));
		    }		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.db.setDisconnection(rs);
			System.out.println("[MotorDetailDao][brandNameList][End]");
		}
		
		return list;
	}
	
	
	public boolean updateMotorcycles(MotorDetailBean bean){
		System.out.println("[MotorDetailDao][updateMotorcycles][Begin]::"+bean.getMotorcyclesCode());
		
		String 				sql			 		= null;
		boolean				lv_ret				= false; 

		try{ 
			this.db = new EnjoyConectDbs();
			sql 	= "update  motorcyclesdetails  set brandCode  = '"+bean.getBrandCode()+"', model ='"+bean.getModel()+
					  "', chassis ='"+bean.getChassis()+"', engineNo ='"+bean.getEngineNo()+
					  "', size ='"+bean.getSize()+"', companyId ='"+bean.getCompanyId()+ "' where  motorcyclesCode  = '"+bean.getMotorcyclesCode()+"'";
			
			System.out.println("[MotorDetailDao][updateMotorcycles] sql :: " + sql);
			
			lv_ret 			= this.db.execute(sql);
			
			System.out.println("[MotorDetailDao][updateMotorcycles] lv_ret :: " + lv_ret); 
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.db.setDisconnection();
			System.out.println("[MotorDetailDao][updateMotorcycles][End]");
		}
		return lv_ret;
	}
	 
	public boolean deleteMotorcycles(MotorDetailBean bean){
		System.out.println("[MotorDetailDao][deleteMotorcycles][Begin]");
		
		String 				sql			 		= null;
		boolean				lv_ret				= false;
		String				motorCode		    = null; 
		
		try{
			this.db    = new EnjoyConectDbs();
			motorCode    = EnjoyUtils.nullToStr(bean.getMotorcyclesCode()); 
			
			sql 	   = "update customer set cusStatus = 'I'  where cusCode = '" + motorCode +"'";
		 
			System.out.println("[MotorDetailDao][deleteMotorcycles] sql :: " + sql);
			
			lv_ret 			= this.db.execute(sql);
			
			System.out.println("[MotorDetailDao][deleteMotorcycles] lv_ret :: " + lv_ret);
			 
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.db.setDisconnection();
			System.out.println("[MotorDetailDao][deleteMotorcycles][End]");
		}
		return lv_ret;
	} 
	
	public boolean insertMotorcycles(MotorDetailBean motorDetailBean){
		System.out.println("[CustomerDao][insertCustomer][Begin]");
		
		String 		sql			 	= null;
		ResultSet 	rs 				= null;    
		boolean		lv_ret			= false;
		try{ 
			this.db    = new EnjoyConectDbs();   
			System.out.println("[CustomerDao][insertCustomer]  :: " + motorDetailBean);  
			
			sql = "insert into motorcyclesdetails  (motorcyclesCode,brandCode,model, chassis, engineNo, size,companyId, motorcyclesStatus)"
				 + " values ('"+ motorDetailBean.getMotorcyclesCode()+ "', '"
				 + motorDetailBean.getBrandCode() + "', '" 
				 + motorDetailBean.getModel() + "', '"  
				 + motorDetailBean.getChassis() + "', '" 
				 + motorDetailBean.getEngineNo() + "', '" 
				 + motorDetailBean.getSize()+ "', '" 
				 + motorDetailBean.getCompanyId() + "', 'A') ";
			
			System.out.println("[CustomerDao][insertCustomer] sql :: " + sql);
			
			lv_ret 	= this.db.execute(sql);  
		    System.out.println("[CustomerDao][insertCustomer] cusCode : " + motorDetailBean.getMotorcyclesCode());
 
		    
		}catch(Exception e){  
			e.printStackTrace();
		}finally{
			this.db.setDisconnection();
			System.out.println("[CustomerDao][insertCustomer][End]");
		}
		
		return lv_ret;
	}
	
}
