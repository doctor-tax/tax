package com.tax.been.dao;

import com.tax.bean.util.DbConnector;

public class CalTaxDAO {
	private String tranId;
	private String date;
	public String getTranId() {
		return tranId;
	}
	public void setTranId(String tranId) {
		this.tranId = tranId;
	}
	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}
	public String getDocIncome() {
		return docIncome;
	}
	public void setDocIncome(String docIncome) {
		this.docIncome = docIncome;
	}
	public String getTaxBreak() {
		return taxBreak;
	}
	public void setTaxBreak(String taxBreak) {
		this.taxBreak = taxBreak;
	}
	private String docId;
	private String docIncome;
	private String taxBreak;
	
	public void doSave(){
		DbConnector conn = new DbConnector();
		conn.doConnect();
		//conn.doSave("INSERT INTO order_tax(id,tax_order, tax_percent, tax_rate, tax_amount, type, tax_list, max_val,group_id) "
		//		+ "VALUES ('"+getId()+"','"+getOrder()+"',"+getPercent()+","
		//				+getRate()+","+getAmount()+",'"+getType()+"','"+getList()+"','"+getMax()+"','"+getGroup()+"')");
		conn.doCommit();
		
	} 
}//Main Method
