package th.go.motorcycles.app.enjoy.form;

import java.util.ArrayList;
import java.util.List;

import th.go.motorcycles.app.enjoy.bean.AddressBean;
import th.go.motorcycles.app.enjoy.bean.CustomerBean; 

public class CustomerForm {
	
	private List<CustomerBean>  listCustomer;
	private CustomerBean 		customerBean; 
	private AddressBean 		addressBean; 
	
	public CustomerForm(){
		this.listCustomer 	= new ArrayList<CustomerBean>();
		this.customerBean 	= new CustomerBean();
		this.addressBean    = new AddressBean();
	}

	public List<CustomerBean> getListCustomer() {
		return listCustomer;
	}

	public void setListCustomer(List<CustomerBean> listCustomer) {
		this.listCustomer = listCustomer;
	}

	public CustomerBean getCustomerBean() {
		return customerBean;
	}

	public void setCustomerBean(CustomerBean customerBean) {
		this.customerBean = customerBean;
	}

	public AddressBean getAddressBean() {
		return addressBean;
	}

	public void setAddressBean(AddressBean addressBean) {
		this.addressBean = addressBean;
	}

	 

	 
}
