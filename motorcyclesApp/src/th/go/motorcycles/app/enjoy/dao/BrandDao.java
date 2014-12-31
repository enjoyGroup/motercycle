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
//		db = new EnjoyConectDbs();
	}

	public List<BrandBean> findAllBrand(){
		System.out.println("[BrandDao][findAllBrand][Begin]");
		
		String 				sql			 		= null;
		ResultSet 			rs 					= null;  
		BrandBean			brandBean			= null;
		List<BrandBean>		list				= null;	          
		
		try{  
			this.db     = new EnjoyConectDbs();
			sql 		= "SELECT * FROM branddetails ";
			 
			list		= new ArrayList<BrandBean>();
			
			System.out.println("[BrandDao][findAllBrand] sql :: " + sql);
			
		    rs 			= this.db.executeQuery(sql);
		    
		    while(rs.next()){
                brandBean	= new BrandBean();  
		    	brandBean.setBrandCode(EnjoyUtils.nullToStr(rs.getString("brandCode")));
		    	brandBean.setBrandtName(EnjoyUtils.nullToStr(rs.getString("brandName"))); 
				brandBean.setBrandStatus(EnjoyUtils.nullToStr("U"));

		    	list.add(brandBean);
		    	
		    } 			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.db.setDisconnection(rs);
			System.out.println("[BrandDao][findAllBrand][End]");
		}
		
		return list;
	}	
	
	public boolean insertBrand(BrandBean brandBean){
		System.out.println("[BrandDao][insertBrand][Begin]");
		
		String 		sql			 	= null;
		boolean		lv_ret			= false; 
		
		try{ 
			this.db     = new EnjoyConectDbs();
			sql = "INSERT INTO branddetails(brandName)"
				 + " values ( '" + brandBean.getBrandName() +"') ";
			
			System.out.println("[BrandDao][insertBrand] sql :: " + sql);
			
			lv_ret 		= this.db.execute(sql);
			
            System.out.println("[BrandDao][insertBrand] sql :: Success");
         
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.db.setDisconnection();
			System.out.println("[BrandDao][insertBrand][End]");
		}
		return lv_ret;
	}
	
	public boolean  updateBrand(BrandBean bean){
		System.out.println("[BrandDao][updateBrand][Begin]");
		
		String 				sql			 		= null;
		boolean				lv_ret				= false; 
		
		try{ 
			this.db = new EnjoyConectDbs();
			sql 	= "UPDATE branddetails SET brandName = '"+bean.getBrandName()+
					"' WHERE  brandCode = '"+bean.getBrandCode()+"'";
			
			System.out.println("[BrandDao][updateBrand] sql :: " + sql);
			
			lv_ret 			= this.db.execute(sql);
			
			System.out.println("[BrandDao][updateBrand] lv_ret :: " + lv_ret);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.db.setDisconnection();
			System.out.println("[BrandDao][updateBrand][End]");
		}
		return lv_ret;
	}
}
