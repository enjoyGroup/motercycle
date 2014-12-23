package th.go.motorcycles.app.enjoy.form;

import java.util.ArrayList;
import java.util.List;

import th.go.motorcycles.app.enjoy.bean.MotorDetailBean; 

public class MotorDetailForm {
	
	private List<MotorDetailBean>  	listMotorDetail;
	private MotorDetailBean 		motorDetailBean; 
	
	public MotorDetailForm(){
		this.listMotorDetail 	= new ArrayList<MotorDetailBean>();
		this.motorDetailBean 	= new MotorDetailBean();
	}

	public List<MotorDetailBean> getListMotorDetail() {
		return listMotorDetail;
	}

	public void setListMotorDetail(List<MotorDetailBean> listMotorDetail) {
		this.listMotorDetail = listMotorDetail;
	}

	public MotorDetailBean getMotorDetailBean() {
		return motorDetailBean;
	}

	public void setMotorDetailBean(MotorDetailBean motorDetailBean) {
		this.motorDetailBean = motorDetailBean;
	}	
}
