package th.go.motorcycles.app.enjoy.form;

import java.util.ArrayList;
import java.util.List;

import th.go.motorcycles.app.enjoy.bean.CustomerBean; 

public class CustomerForm {
	
	private List<CustomerBean>  listCustomer;
	private String 				custCode;
	private String 				proPrice;
	
	public CustomerForm(){
		this.listCustomer 	= new ArrayList<CustomerBean>();
		this.custCode 		= ""; 
	}

	public List<CustomerBean> getListCustomer() {
		return listCustomer;
	}

	public void setListCustomer(List<CustomerBean> listCustomer) {
		this.listCustomer = listCustomer;
	}

	public String getCustCode() {
		return custCode;
	}

	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}

	public String getProPrice() {
		return proPrice;
	}

	public void setProPrice(String proPrice) {
		this.proPrice = proPrice;
	}

	 
}
