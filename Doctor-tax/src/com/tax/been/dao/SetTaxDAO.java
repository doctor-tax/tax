package com.tax.been.dao;

import com.tax.bean.util.DbConnector;

public class SetTaxDAO {
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMax() {
		return max;
	}
	public void setMax(String max) {
		this.max = max;
	}
	public String getList() {
		return list;
	}
	public void setList(String list) {
		this.list = list;
	}
	private String order;
	private String rate;
	private String amount;
	private String type;
	private String max;
	private String list;
	private String group;
	private String percent;
	
	public String getPercent() {
		return percent;
	}
	public void setPercent(String percent) {
		this.percent = percent;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public void doSave(){
		DbConnector conn = new DbConnector();
		conn.doConnect();
		conn.doSave("INSERT INTO order_tax(id,tax_order, tax_percent, tax_rate, tax_amount, type, tax_list, max_val,group_id) "
				+ "VALUES ('"+getId()+"','"+getOrder()+"',"+getPercent()+","
						+getRate()+","+getAmount()+",'"+getType()+"','"+getList()+"','"+getMax()+"','"+getGroup()+"')");
		conn.doCommit();
		
	}
	
	public void doUpdate(){
		DbConnector conn = new DbConnector();
		conn.doConnect();
		conn.doUpdate("UPDATE order_tax SET tax_order = '"+getOrder()+"',tax_percent = '"+getPercent()+"',tax_rate = '"+getRate()+"',tax_amount = '"+getAmount()+"',tax_list = '"+getList()+"',max_val = '"+getMax()+"',group_id = '"+getGroup()+"'"
				+ "WHERE id = '"+getId()+"'");
		conn.doCommit();
	}
	
	public void doDelete(){
		DbConnector conn = new DbConnector();
		conn.doConnect();
		conn.doDelete("DELETE FROM order_tax WHERE id = '"+getId()+"'");
		conn.doCommit();
	}
}
