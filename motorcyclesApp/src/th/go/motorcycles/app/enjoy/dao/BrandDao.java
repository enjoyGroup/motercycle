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
	
	public List<BrandBean> findBrand(BrandBean bean){
		System.out.println("[BrandDao][findBrand][Begin] :"+bean.getBrandCode());
		
		String 				sql			 		= null;
		ResultSet 			rs 					= null; 
		BrandBean			brandBean			= null;
		List<BrandBean>		list				= null;	           
		
		try{ 
			System.out.println(bean.getBrandCode());
			System.out.println(bean.getBrandtFullname());

			sql = "SELECT * FROM brand a  LEFT JOIN  subdistrict s ON a.subdistrictCode=s.subdistrictId LEFT JOIN district d "
				+ "ON a.districtCode=d.districtId LEFT JOIN province p ON a.provinceCode=p.provinceId where  brandStatus = 'A'";
			
			if(bean.getBrandCode()=="" && (bean.getBrandtFullname()=="")){
				sql += " order by brandCode";
			}else{
				if(bean.getBrandCode()!=""){
					sql += " and brandCode = '"+bean.getBrandCode()+"'";
				}
				if(bean.getBrandtFullname()!=""){
					sql += " and (SELECT CONCAT(a.brandName, ' ', a.brandSurname) as fullName) = '"+bean.getBrandtFullname()+"'";
				}
				 
			} 	 
			   
			list		= new ArrayList<BrandBean>();
			
			System.out.println("[BrandDao][findBrand] sql :: " + sql);
			
		    rs 			= this.db.executeQuery(sql);
		    
		    while(rs.next()){
                brandBean	= new BrandBean();  
		    	brandBean.setBrandCode(EnjoyUtils.nullToStr(rs.getString("brandCode")));
		    	brandBean.setBrandtName(EnjoyUtils.nullToStr(rs.getString("brandName"))); 
		    	brandBean.setBrandtSurname(EnjoyUtils.nullToStr(rs.getString("brandSurname")));  
				brandBean.setBrandStatus(EnjoyUtils.nullToStr(rs.getString("brandStatus")));
		    	list.add(brandBean);
		    	
		    } 
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("[BrandDao][findBrand][End]");
		}
		
		return list;
	}
	
	public List<BrandBean> findAllBrand(){
		System.out.println("[BrandDao][findAllBrand][Begin]");
		
		String 				sql			 		= null;
		ResultSet 			rs 					= null;  
		BrandBean			brandBean			= null;
		List<BrandBean>		list				= null;	          
		
		try{  
			sql = "SELECT * FROM branddetails ";
			 
			list		= new ArrayList<BrandBean>();
			
			System.out.println("[BrandDao][findAllBrand] sql :: " + sql);
			
		    rs 			= this.db.executeQuery(sql);
		    
		    while(rs.next()){
                brandBean	= new BrandBean();  
		    	brandBean.setBrandCode(EnjoyUtils.nullToStr(rs.getString("brandCode")));
		    	brandBean.setBrandtName(EnjoyUtils.nullToStr(rs.getString("brandName"))); 
				brandBean.setBrandStatus(EnjoyUtils.nullToStr("A"));

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
		
		String 				sql			 	= null;
		ResultSet 			rs 				= null;  
		BrandBean			brandBean		= null; 	
		
		try{
			if(bean.getBrandCode()!=null && bean.getBrandCode()!=""){ 
				sql = "SELECT * FROM branddetails a  LEFT JOIN  subdistrict s ON a.subdistrictCode=s.subdistrictId LEFT JOIN district d "
						+ "ON a.districtCode=d.districtId LEFT JOIN province p ON a.provinceCode=p.provinceId where  brandStatus = 'A' and brandCode = '"
						+ bean.getBrandCode()+"'";
			}
			
			brandBean	= new BrandBean();   
			System.out.println("[BrandDao][findBrandByBrandCode] sql :: " + sql); 
		    rs 			= this.db.executeQuery(sql);
		    
		    while(rs.next()){ 
		    	brandBean.setBrandCode(EnjoyUtils.nullToStr(rs.getString("brandCode")));
		    	brandBean.setBrandtName(EnjoyUtils.nullToStr(rs.getString("brandName"))); 
		    	brandBean.setBrandtSurname(EnjoyUtils.nullToStr(rs.getString("brandSurname"))); 
				brandBean.setBrandStatus(EnjoyUtils.nullToStr(rs.getString("brandStatus")));
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
		String      brandCode       = null; 
		
		try{ 
		    
			sql = "insert into branddetails(brandName)"
				 + " values ( '" + brandBean.getBrandtName() +"') ";
			
			System.out.println("[BrandDao][insertBrand] sql :: " + sql);
			
            this.db.execute(sql); 
			
            System.out.println("[BrandDao][insertBrand] sql :: Success");
            
            sql 		= "SELECT brandCode as lastId FROM brand ORDER BY brandCode DESC LIMIT 1";
			rs 			= this.db.executeQuery(sql);
			System.out.println("[BrandDao][insertBrand] sql :: " + sql);
			while (rs.next()) {
				brandCode	= EnjoyUtils.nullToStr(rs.getString("lastId"));
			} 
			System.out.println("[BrandDao][insertBrand] brandCodeNew : " + brandCode);
			
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
			sql 	= "update  branddetails set brandName='"+bean.getBrandtName()+
					"' where  brandCode = '"+bean.getBrandCode()+"'";
			
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
		System.out.println("[BrandDadeleteBrandmer][Begin]");
		
		String 				sql			 		= null;
		boolean				lv_ret				= false;
		String				brandCode		    = null;
		
		try{
			brandCode    = EnjoyUtils.nullToStr(bean.getBrandCode());
			
			sql 		= "delete from branddetails where brandCode = '" + brandCode + "'";
		 
			System.out.println("[BrandDao][deleteBrand] sql :: " + sql);
			
			lv_ret 			= this.db.execute(sql);
			
			System.out.println("[BrandDao][deleteBrand] lv_ret :: " + lv_ret);
			findBrand(new BrandBean());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("[BrandDao][deleteBrand][End]");
		}
		return lv_ret;
	}

}
