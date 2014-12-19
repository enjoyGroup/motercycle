package th.go.motorcycles.app.enjoy.form;

import java.util.ArrayList;
import java.util.List;

import th.go.motorcycles.app.enjoy.bean.BrandBean; 

public class BrandForm {
	
	private List<BrandBean>  listBrand;
	private BrandBean 		brandBean; 
	
	public BrandForm(){
		this.listBrand 	= new ArrayList<BrandBean>();
		this.brandBean 	= new BrandBean();
	}

	public List<BrandBean> getListBrand() {
		return listBrand;
	}

	public void setListBrand(List<BrandBean> listBrand) {
		this.listBrand = listBrand;
		System.out.println("[xxxxxxxxxxxxxxxxxxxx]");
	}

	public BrandBean getBrandBean() {
		return brandBean;
	}

	public void setBrandBean(BrandBean brandBean) {
		this.brandBean = brandBean;
	}

	 
}
