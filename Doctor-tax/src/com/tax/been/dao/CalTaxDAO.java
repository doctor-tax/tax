package com.tax.been.dao;

import com.tax.bean.util.DbConnector;

public class CalTaxDAO {
	
	private String date;
	private String payTax;
	private String income;
	private String hcode;
	private String donate;
	private String sumPayTax;
	private String docId;
	private String docIncome;
	private String taxBreak;
	
	public String getPayTax() {
		return payTax;
	}
	public void setPayTax(String payTax) {
		this.payTax = payTax;
	}
	public String getSumPayTax() {
		return sumPayTax;
	}
	public void setSumPayTax(String sumPayTax) {
		this.sumPayTax = sumPayTax;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getIncome() {
		return income;
	}
	public void setIncome(String income) {
		this.income = income;
	}
	public String getHcode() {
		return hcode;
	}
	public void setHcode(String hcode) {
		this.hcode = hcode;
	}
	public String getDonate() {
		return donate;
	}
	public void setDonate(String donate) {
		this.donate = donate;
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
	
	
	public void doSave(){
		DbConnector conn = new DbConnector();
		conn.doConnect();
		//System.out.println(getHcode());
		conn.doSave("INSERT INTO pay_tax(doctor_id,pay_tax, tax_period, hcode, doctor_income, income, sum_tax_break, sum_pay_tax,sum_donate,status) "
				+ "VALUES ('"+getDocId()+"',"+getPayTax()+","+getDate()+",'"
						+getHcode()+"',"+getDocIncome()+","+getIncome()+","+getTaxBreak()+","+getSumPayTax()+","+getDonate()+",'a')");
		conn.doCommit();
		
	} 
	
	public void doDelete(){
		DbConnector conn = new DbConnector();
		conn.doConnect();
		//System.out.println(getHcode());
		conn.doSave("DELETE FROM pay_tax WHERE tax_period = '"+getDate()+"'");
		conn.doCommit();
	}
	
	public String doClose(){
		String status = "";
		try{
			DbConnector conn = new DbConnector();
			conn.doConnect();
			//System.out.println(getHcode());
			conn.doSave("UPDATE pay_tax SET status = 'c' WHERE tax_period = '"+getDate()+"'");
			conn.doCommit();
			status = "S";
			return status;
		}catch(Exception e){
			status = "E";
			return status;
		}
	}
}//Main Method
