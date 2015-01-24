package th.go.motorcycles.app.enjoy.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import th.go.motorcycles.app.enjoy.bean.ComboBean;
import th.go.motorcycles.app.enjoy.bean.SummarySaleDetailBean;

public class SummarySaleDetailForm {
	
	private String 									invoiceId;
	private String 									invoiceDateFrom;
	private String 									invoiceDateTo;
	private String 									brandName;
	private String 									model;
	private String 									cusName;
	private List<SummarySaleDetailBean> 			dataList;
	HashMap<Integer, List<SummarySaleDetailBean>>	hashTable;
	int												pageNum;
    int												totalPage;
    List<ComboBean>									companyComboList;
    private String 									company;
	
	public SummarySaleDetailForm(){
		this.invoiceId 				= "";
		this.invoiceDateFrom 		= "";
		this.invoiceDateTo 			= "";
		this.brandName 				= "";
		this.model 					= "";
		this.cusName 				= "";
		this.dataList				= new ArrayList<SummarySaleDetailBean>();
		this.hashTable				= new HashMap<Integer, List<SummarySaleDetailBean>>();
		this.pageNum				= 1;
		this.totalPage				= 0;
		this.companyComboList		= new ArrayList<ComboBean>();
		this.company				= "";
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public List<ComboBean> getCompanyComboList() {
		return companyComboList;
	}

	public void setCompanyComboList(List<ComboBean> companyComboList) {
		this.companyComboList = companyComboList;
	}

	public List<SummarySaleDetailBean> getDataList() {
		return dataList;
	}


	public void setDataList(List<SummarySaleDetailBean> dataList) {
		this.dataList = dataList;
	}


	public HashMap<Integer, List<SummarySaleDetailBean>> getHashTable() {
		return hashTable;
	}


	public void setHashTable(HashMap<Integer, List<SummarySaleDetailBean>> hashTable) {
		this.hashTable = hashTable;
	}


	public int getPageNum() {
		return pageNum;
	}


	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}


	public int getTotalPage() {
		return totalPage;
	}


	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}


	public String getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}
	public String getInvoiceDateFrom() {
		return invoiceDateFrom;
	}
	public void setInvoiceDateFrom(String invoiceDateFrom) {
		this.invoiceDateFrom = invoiceDateFrom;
	}
	public String getInvoiceDateTo() {
		return invoiceDateTo;
	}
	public void setInvoiceDateTo(String invoiceDateTo) {
		this.invoiceDateTo = invoiceDateTo;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	
	

}
