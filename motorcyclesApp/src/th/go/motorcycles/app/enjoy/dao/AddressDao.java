package th.go.motorcycles.app.enjoy.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import th.go.motorcycles.app.enjoy.bean.AddressBean;
import th.go.motorcycles.app.enjoy.exception.EnjoyException;
import th.go.motorcycles.app.enjoy.utils.EnjoyConectDbs;
import th.go.motorcycles.app.enjoy.utils.EnjoyUtils;

public class AddressDao {	
	
	private EnjoyConectDbs db = null;
	
	public AddressDao(){
		db = new EnjoyConectDbs();
	}
	
	public List<String> provinceList(String province){
		System.out.println("[AddressDao][provinceList][Begin]");
		
		String 							sql			 		= null;
		ResultSet 						rs 					= null;
        List<String> 					list 				= new ArrayList<String>();
		
		try{
//			province	= "ก";
			sql 		= " select provinceName from province where provinceId <> 00 and provinceName like ('"+province+"%') order by provinceName asc limit 10 ";
			
			System.out.println("[AddressDao][provinceList] sql :: " + sql);
			
		    rs 			= this.db.executeQuery(sql);
		    
		    while(rs.next()){
		    	
		    	list.add(EnjoyUtils.nullToStr(rs.getString("provinceName")));
		    }
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("[AddressDao][provinceList][End]");
		}
		
		return list;
	}
	
	public List<String> districtList(String province, String district){
		System.out.println("[AddressDao][districtList][Begin]");
		
		String 							sql			 		= null;
		ResultSet 						rs 					= null;
        List<String> 					list 				= new ArrayList<String>();
        String							provinceId			= null;
		
		try{
			/*Begin check province section*/
			sql 		= "select provinceId from province where provinceId <> 00 and provinceName = '"+province+"'";
			
			System.out.println("[AddressDao][districtList] sql :: " + sql);
			
		    rs 			= this.db.executeQuery(sql);
		    
		    while(rs.next()){
		    	provinceId = rs.getString("provinceId");
		    }
		    /*End check province section*/
		    
		    if(provinceId==null){
		    	list.add("กรุณาระบุจังหวัด");
		    }else{
		    	sql 		= "select districtName from district where districtId <> 0000 and provinceId <> 00 and districtName like ('"+district+"%') and provinceId = "+provinceId+" order by districtName asc limit 10";
				
				System.out.println("[AddressDao][districtList] sql :: " + sql);
				
			    rs 			= this.db.executeQuery(sql);
			    while(rs.next()){
			    	list.add(rs.getString("districtName"));
			    }
		    }
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("[AddressDao][districtList][End]");
		}
		
		return list;
	}
	
	public List<String> subdistrictList(String province, String district, String subdistrict){
		System.out.println("[AddressDao][subdistrictList][Begin]");
		
		String 							sql			 		= null;
		ResultSet 						rs 					= null;
        List<String> 					list 				= new ArrayList<String>();
        String							provinceId			= null;
        String							districtId			= null;
		
		try{
			
			/*Begin check province section*/
			sql 		= "select provinceId from province where provinceId <> 00 and provinceName = '"+province+"'";
			
			System.out.println("[AddressDao][subdistrictList] province sql :: " + sql);
			
		    rs 			= this.db.executeQuery(sql);
		    
		    while(rs.next()){
		    	provinceId = rs.getString("provinceId");
		    }
		    /*End check province section*/
		    
		    /*Begin check district section*/
			sql 		= "select districtId from district where districtId <> 0000 and districtName = '"+district+"'";
			
			System.out.println("[AddressDao][subdistrictList] district sql :: " + sql);
			
		    rs 			= this.db.executeQuery(sql);
		    
		    while(rs.next()){
		    	districtId = rs.getString("districtId");
		    }
		    /*End check district section*/
		    
		    if(provinceId==null){
		    	list.add("กรุณาระบุจังหวัด");
		    }else if(districtId==null){
		    	list.add("กรุณาระบุอำเภอ");
		    }else{
		    	sql 		= "select subdistrictName" 
								   + " from subdistrict "
								   + "  where subdistrictId <> 000000 "
								   + " and provinceId <> 00 "
								   + " and districtId <> 0000 "
								   + " and subdistrictName like ('"+subdistrict+"%') "
								   + " and provinceId = "+provinceId
								   + " and districtId = "+districtId
								   + " order by subdistrictName asc limit 10";
				
				System.out.println("[AddressDao][subdistrictList] subdistrict sql :: " + sql);
				
			    rs 			= this.db.executeQuery(sql);
			    while(rs.next()){
			    	list.add(rs.getString("subdistrictName"));
			    }
		    }
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("[AddressDao][subdistrictList][End]");
		}
		
		return list;
	}
	
	public AddressBean validateAddress(String province, String district, String subdistrict){
		System.out.println("[AddressDao][validateAddress][Begin]");
		
		String 							sql			 		= null;
		ResultSet 						rs 					= null;
        String							provinceId			= null;
        String							districtId			= null;
        String							subdistrictId		= null;
        String							errMsg				= null;
        AddressBean						addressBean			= new AddressBean();
		
		try{
			/*Begin check province section*/
			sql 		= "select provinceId from province where provinceId <> 00 and provinceName = '"+province+"'";
			
			System.out.println("[AddressDao][validateAddress] province sql :: " + sql);
			
		    rs 			= this.db.executeQuery(sql);
		    while(rs.next())provinceId = rs.getString("provinceId").trim();
		    if(provinceId==null)throw new EnjoyException("ระบุจังหวัดผิด");
		    /*End check province section*/
		    
		    /*Begin check district section*/
			sql 		= "select districtId from district where districtId <> 0000 and provinceId = "+provinceId+" and districtName = '"+district+"'";
			
			System.out.println("[AddressDao][validateAddress] district sql :: " + sql);
			
		    rs 			= this.db.executeQuery(sql);
		    while(rs.next())districtId = rs.getString("districtId").trim();
		    if(districtId==null)throw new EnjoyException("ระบุอำเภอผิด");
		    /*End check district section*/
		    
		    /*Begin check subDistrict section*/
			sql 		= "select subdistrictId from subdistrict where subdistrictId <> 000000 and provinceId = "+provinceId+" and districtId = "+districtId+" and subdistrictName = '"+subdistrict+"'";
			
			System.out.println("[AddressDao][validateAddress] subdistrict sql :: " + sql);
			
		    rs 			= this.db.executeQuery(sql);
		    while(rs.next())subdistrictId = rs.getString("subdistrictId").trim();
		    if(subdistrictId==null)throw new EnjoyException("ระบุตำบลผิด");
		    /*End check subDistrict section*/
		    
		    System.out.println("[AddressDao][validateAddress] " + provinceId + ", " + districtId + ", " + subdistrictId);
		    
		    addressBean.setProvinceId(provinceId);
		    addressBean.setDistrictId(districtId);
		    addressBean.setSubdistrictId(subdistrictId);
		    
		}catch(EnjoyException e){
			errMsg = e.getMessage();
			addressBean.setErrMsg(errMsg);
			e.printStackTrace();
		}catch(Exception e){
			errMsg = e.getMessage();
			addressBean.setErrMsg(errMsg);
			e.printStackTrace();
		}finally{
			System.out.println("[AddressDao][validateAddress][End]");
		}
		return addressBean;
	}
	
}















