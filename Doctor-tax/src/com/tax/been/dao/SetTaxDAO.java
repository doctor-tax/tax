package com.tax.been.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.tax.bean.util.DbConnector;

public class SetTaxDAO {
	private String id;
	private String listMode;
	public String getListMode() {
		return listMode;
	}
	public void setListMode(String listMode) {
		this.listMode = listMode;
	}
	public String getOldList() {
		return oldList;
	}
	public void setOldList(String oldList) {
		this.oldList = oldList;
	}
	private String oldList;
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
		conn.doDelete("DELETE FROM tra_tax WHERE tax_id = '"+getId()+"'");
		conn.doDelete("DELETE FROM order_tax WHERE id = '"+getId()+"'");
		conn.doCommit();
	}
	
	public void doManageList(){
		//System.out.println(getListMode());
		DbConnector db = new DbConnector();
		db.doConnect();
		if(getListMode().equals("Greater")){
			System.out.println("Great");
			String sql = "SELECT * FROM order_tax WHERE group_id ="+getGroup()+"AND tax_list >"+getOldList()+"AND tax_list <= "+getList();
			ArrayList<HashMap<String,String>> listData = db.getData(sql);
			//System.out.println("SetTaxDAOGreat>>>>>>>>>>"+listData);
			for(int i=0;i<listData.size();i++){
				int nList = Integer.parseInt(listData.get(i).get("tax_list"));
				--nList;
				db.doUpdate("UPDATE order_tax SET tax_list = '"+nList+"' WHERE id ="+listData.get(i).get("id"));
				db.doCommit();
			}
		}else if(getListMode().equals("Less")){
			System.out.println("Less");
			String sql = "SELECT * FROM order_tax WHERE group_id ="+getGroup()+"AND tax_list >="+getList()+"AND tax_list < "+getOldList();
			ArrayList<HashMap<String,String>> listData = db.getData(sql);
			//System.out.println("SetTaxDAOLess>>>>>>>>>>"+listData);
			for(int i=0;i<listData.size();i++){
				int nList = Integer.parseInt(listData.get(i).get("tax_list"));
				++nList;
				db.doUpdate("UPDATE order_tax SET tax_list = '"+nList+"' WHERE id ="+listData.get(i).get("id"));
				db.doCommit();
			}
		}else if(getListMode().equals("Delete")){
			System.out.println("Delete");
			String sql = "SELECT * FROM order_tax WHERE group_id ="+getGroup()+"AND tax_list > "+getList();
			ArrayList<HashMap<String,String>> listData = db.getData(sql);
			System.out.println("SetTaxDAODELETE>>>>>>>>>>"+listData);
			for(int i=0;i<listData.size();i++){
				int nList = Integer.parseInt(listData.get(i).get("tax_list"));
				--nList;
				db.doUpdate("UPDATE order_tax SET tax_list = '"+nList+"' WHERE id ="+listData.get(i).get("id"));
				db.doCommit();
			}
		}
	}
}
