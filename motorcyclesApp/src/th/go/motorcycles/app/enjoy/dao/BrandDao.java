package th.go.motorcycles.app.enjoy.dao;

import java.sql.ResultSet; 
import java.util.ArrayList;
import java.util.List;

import th.go.motorcycles.app.enjoy.bean.BrandBean;
import th.go.motorcycles.app.enjoy.exception.EnjoyException;
import th.go.motorcycles.app.enjoy.utils.EnjoyConectDbs; 
import th.go.motorcycles.app.enjoy.utils.EnjoyUtils;

public class BrandDao {	 
	private EnjoyConectDbs db = null;	
	
	public BrandDao(){
		db = new EnjoyConectDbs();
	}
	
	public List<BrandBean> findAllBrand(){
System.out.println("[BrandDao][findAllBrand][Begin]");
		
		String 				sql			 	= null;
		ResultSet 			rs 				= null;  
		BrandBean			brandBean		= null;
		List<BrandBean>		list			= null;	         
		
		try{  
			sql = "SELECT * FROM branddetails ";
			 
			list		= new ArrayList<BrandBean>();
			
			System.out.println("[BrandDao][findAllBrand] sql :: " + sql);
			
		    rs 			= this.db.executeQuery(sql);
		    
		    while(rs.next()){
                brandBean	= new BrandBean();  
		    	brandBean.setBrandCode(EnjoyUtils.nullToStr(rs.getString("brandCode")));
		    	brandBean.setBrandName(EnjoyUtils.nullToStr(rs.getString("brandName"))); 
		    	list.add(brandBean);	
		    } 
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("[BrandDao][findAllBrand][End]");
		}
		
		return list;
	}	
	
	public BrandBean findBrandByBrandCode(BrandBean bean){
		System.out.println("[BrandDao][findBrandByBrandCode][Begin]");
		
		String 				sql			 		= null;
		ResultSet 			rs 					= null;  
		BrandBean			brandBean			= null; 	
		
		try{
			if(bean.getBrandCode()!=null && bean.getBrandCode()!=""){ 
				sql = "SELECT * FROM branddetails where brandCode = '"
						+ bean.getBrandCode() + "'";
			}
			
			brandBean	= new BrandBean();   
			System.out.println("[BrandDao][findBrandByBrandCode] sql :: " + sql); 
		    rs 			= this.db.executeQuery(sql);
		    
		    while(rs.next()){ 
		    	brandBean.setBrandCode(EnjoyUtils.nullToStr(rs.getString("brandCode")));
		    	brandBean.setBrandName(EnjoyUtils.nullToStr(rs.getString("brandName"))); 
		    } 
		   
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("[BrandDao][findBrandByBrandCode][End]");
		}
		
		return brandBean;
	}
	
	 
	public String insertBrand(BrandBean brandBean){
		System.out.println("[BrandDao][insertBrand][Begin]");
		
		String 		sql			 	= null;
		ResultSet 	rs 				= null;  
		String      brandCode         = null; 
		
		try{ 
			 sql 		= "SELECT brandCode as lastId FROM branddetails ORDER BY brandCode DESC LIMIT 1";
			 rs 		= this.db.executeQuery(sql);
			 System.out.println("[BrandDao][insertBrand] sql :: " + sql);
			  while (rs.next()) {
				  brandCode	= EnjoyUtils.nullToStr(rs.getString("lastId"));
			  } 

			sql = "insert into branddetails ( brandName )"
				 + " values ('"+  brandBean.getBrandName() + "') ";
			
			System.out.println("[BrandDao][insertBrand] sql :: " + sql);
			
            this.db.execute(sql); 
			
		    System.out.println("[BrandDao][insertBrand] brandCode : " + brandCode);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("[BrandDao][insertBrand][End]");
		}
		
		return brandCode;
	}
	
	public boolean  updateBrand(BrandBean bean){
		System.out.println("[BrandDao][updateBrand][Begin]");
		
		String 				sql			 		= null;
		boolean				lv_ret				= false; 
		
		try{ 
			sql 	= "update  branddetails set brandName='"+bean.getBrandName()+
					"' where  brandCode = '" + bean.getBrandCode() + "'";
			
			System.out.println("[BrandDao][updateBrand] sql :: " + sql);
			
			lv_ret 			= this.db.execute(sql);
			
			System.out.println("[BrandDao][updateBrand] lv_ret :: " + lv_ret);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("[BrandDao][updateBrand][End]");
		}
		return lv_ret;
	}
	
	public boolean deleteBrand(BrandBean bean){
		System.out.println("[BrandDao][deleteBrand][Begin]");
		
		String 				sql			 		= null;
		boolean				lv_ret				= false;
		String				brandCode		        = null;
		String				brandStatus	        = null;
		
		try{
			brandCode    = EnjoyUtils.nullToStr(bean.getBrandCode());
			brandStatus    = EnjoyUtils.nullToStr(bean.getBrandStatus());
			
			sql 		= "update branddetails set brandStatus = '"+ brandStatus +"'  where brandCode = '" + brandCode +"'";
		 
			System.out.println("[BrandDao][deleteBrand] sql :: " + sql);
			
			lv_ret 			= this.db.execute(sql);
			
			System.out.println("[BrandDao][deleteBrand] lv_ret :: " + lv_ret);
			findBrandByBrandCode(new BrandBean());
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("[BrandDao][deleteBrand][End]");
		}
		return lv_ret;
	}
	
}
