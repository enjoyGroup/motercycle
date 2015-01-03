package th.go.motorcycles.app.enjoy.dao;

import java.sql.ResultSet; 
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import th.go.motorcycles.app.enjoy.bean.BrandBean;
import th.go.motorcycles.app.enjoy.bean.CustomerBean;
import th.go.motorcycles.app.enjoy.bean.MotorDetailBean;
import th.go.motorcycles.app.enjoy.exception.EnjoyException;
import th.go.motorcycles.app.enjoy.form.MotorDetailForm;
import th.go.motorcycles.app.enjoy.utils.EnjoyConectDbs; 
import th.go.motorcycles.app.enjoy.utils.EnjoyUtils;

public class MotorDetailDao {	 
	private EnjoyConectDbs db = null;	
	
	public MotorDetailDao(){
//		db = new EnjoyConectDbs();
	}
	
	public List<MotorDetailBean> findModelDetail(MotorDetailBean bean,MotorDetailForm motorDetailForm){
		String 					sql			 		= null;
		ResultSet 				rs 					= null; 
		MotorDetailBean			motorDetailBean		= null;
		List<MotorDetailBean>	list				= null;	         
		
		try{ 
			System.out.println(bean.getBrandSearch());
			System.out.println(bean.getCompanySearch());

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
				if(bean.getCompanySearch()!=""){
					sql += " and c.branchName like '" + bean.getCompanySearch() + "%'";
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
				motorDetailBean.setBranchName(EnjoyUtils.nullToStr(rs.getString("branchName")));
				motorDetailBean.setMotorcyclesStatus(EnjoyUtils.nullToStr(rs.getString("motorcyclesStatus")));
		    	list.add(motorDetailBean);	
		    } 
		    
		    motorDetailForm.setListMotorDetail(list);
		    
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
	 
	public boolean deleteMotorcycles(String motorCode){
		System.out.println("[MotorDetailDao][deleteMotorcycles][Begin]");
		
		String 				sql			 		= null;
		boolean				lv_ret				= false;
		String				motorDelCode		= null; 
		
		try{
			this.db    = new EnjoyConectDbs();
			motorDelCode    = EnjoyUtils.nullToStr(motorCode); 
			
			sql 	   = "update motorcyclesdetails set motorcyclesStatus = 'I'  where motorcyclesCode = '" + motorDelCode +"'";
		 
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
	
	public boolean insertMotorcycles(MotorDetailBean motorDetailBean,MotorDetailForm motorForm){
		System.out.println("[MotorDetailDao][insertCustomer][Begin]");
		
		String 		sql			 	= null;
		ResultSet 	rs 				= null;    
		boolean		lv_ret			= false;
		try{ 
			this.db    = new EnjoyConectDbs();   
			System.out.println("[MotorDetailDao][insertCustomer]  :: " + motorDetailBean);  
			
			sql = "insert into motorcyclesdetails  (brandCode,model, chassis, engineNo, size,companyId, motorcyclesStatus)"
				 + " values ('" + motorDetailBean.getBrandCode() + "', '" 
				 + motorDetailBean.getModel() + "', '"  
				 + motorDetailBean.getChassis() + "', '" 
				 + motorDetailBean.getEngineNo() + "', '" 
				 + motorDetailBean.getSize()+ "', '" 
				 + motorDetailBean.getCompanyId() + "', 'A') ";
			
			System.out.println("[MotorDetailDao][insertCustomer] sql :: " + sql);
			
			lv_ret 	= this.db.execute(sql);  
		    System.out.println("[MotorDetailDao][insertCustomer] MotorcyclesCode : " + motorDetailBean.getMotorcyclesCode());
		    
		    motorForm.setMotorDetailBean(motorDetailBean);
		    
		}catch(Exception e){  
			e.printStackTrace();
		}finally{
			this.db.setDisconnection();
			System.out.println("[MotorDetailDao][insertCustomer][End]");
		}
		
		return lv_ret;
	}
	
	public BrandBean validateBrandName(String brandName){
		System.out.println("[MotorDetailDao][getBrandName][Begin]");
		BrandBean 				        bean		        = null;
		String 							sql			 		= null;
		ResultSet 						rs 					= null;  
		String                          brandCode           = null;
		String							errMsg				= null;
		   try{ 
			   this.db    = new EnjoyConectDbs();  
			   bean			= new BrandBean();
			   
			   System.out.println("[MotorDetailDao] brandName :: " + brandName);
			    
			   
			   sql 		= "SELECT brandCode FROM branddetails WHERE  brandName like '"+brandName+"%'";
				
			   System.out.println("[MotorDetailDao][getBrandName] branddetails sql :: " + sql);
				
			    rs 			= this.db.executeQuery(sql);
			    
			    while(rs.next()){
			    	brandCode = rs.getString("brandCode").trim();
			        if(brandCode==null)throw new EnjoyException("ระบุยี่ห้แผิด");
			    }
			    bean.setBrandCode(brandCode);
			   
		   }catch(EnjoyException e){
				errMsg = e.getMessage();
				bean.setErrMsg(errMsg);
				e.printStackTrace();
			}catch(Exception e){
				errMsg = e.getMessage();
				bean.setErrMsg(errMsg);
				e.printStackTrace();
			}finally{
				this.db.setDisconnection(rs);
				  System.out.println("[MotorDetailDao][getBrandName][End]");
			}
		   
		 
		   return bean;
		}
	
	
	public MotorDetailBean validateBranchName(String branchName){
		System.out.println("[MotorDetailDao][validateBranchName][Begin]");
		MotorDetailBean			        bean		        = null;
		String 							sql			 		= null;
		ResultSet 						rs 					= null;  
		Integer                         companyId           = null;
		String							errMsg				= null;
		   try{ 
			   this.db    = new EnjoyConectDbs();  
			   bean			= new MotorDetailBean();
			   
			   System.out.println("[MotorDetailDao] branchName :: " + branchName); 
			   
			   sql 		= "SELECT * FROM company WHERE  branchName like '"+branchName+"%'";
				
			   System.out.println("[MotorDetailDao][validateBranchName] company sql :: " + sql);
				
			    rs 			= this.db.executeQuery(sql);
			    
			    while(rs.next()){
			    	companyId =(Integer) rs.getObject("companyId");
			    	System.out.println("companyId ::"+companyId); 
			        if(companyId==null)throw new EnjoyException("ระบุบริษัทผิด");
			    }
			    
			    bean.setCompanyId(String.valueOf(companyId));
			   
		   }catch(EnjoyException e){
				errMsg = e.getMessage();
				bean.setErrMsg(errMsg);
				e.printStackTrace();
			}catch(Exception e){
				errMsg = e.getMessage();
				bean.setErrMsg(errMsg);
				e.printStackTrace();
			}finally{
				this.db.setDisconnection(rs);
				System.out.println("[MotorDetailDao][validateBranchName][End]");
			}
		   
		 
		   return bean;
		}
	
}
