package com.tax.been.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.tax.bean.util.DbConnector;

public class SetGroupDAO {
	private String id;
	private String listMode;
	private int oldList;
	public int getOldList() {
		return oldList;
	}
	public void setOldList(int oldList) {
		this.oldList = oldList;
	}
	public String getListMode() {
		return listMode;
	}
	public void setListMode(String listMode) {
		this.listMode = listMode;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getList() {
		return list;
	}
	public void setList(String list) {
		this.list = list;
	}
	private String name;
	private String list;
	
	public void doSave(){
		DbConnector conn = new DbConnector();
		conn.doConnect();
		conn.doSave("INSERT INTO group_tax (id_group, name_group, list_group) "
				+ "VALUES ('"+getId()+"','"+getName()+"','"+getList()+"')");
		conn.doCommit();
	}
	
	public void doUpdate(){
		DbConnector conn = new DbConnector();
		conn.doConnect();
		conn.doUpdate("UPDATE group_tax SET name_group = '"+getName()+"',list_group = '"+getList()+"'"
				+ "WHERE id_group = '"+getId()+"'");
		conn.doCommit();
	}
	
	public void doDelete(){
		DbConnector conn = new DbConnector();
		conn.doConnect();
		conn.doDelete("DELETE FROM group_tax WHERE id_group = '"+getId()+"'");
		conn.doCommit();
	}
	
	public void doManageList(){
		//System.out.println(getListMode());
		DbConnector db = new DbConnector();
		db.doConnect();
		if(getListMode().equals("Greater")){
			System.out.println("Great");
			String sql = "SELECT * FROM group_tax WHERE list_group >"+getOldList()+"AND list_group <= "+getList();
			ArrayList<HashMap<String,String>> listData = db.getData(sql);
			//System.out.println("SetTaxDAOGreat>>>>>>>>>>"+listData);
			for(int i=0;i<listData.size();i++){
				int nList = Integer.parseInt(listData.get(i).get("list_group"));
				--nList;
				db.doUpdate("UPDATE group_tax SET list_group = '"+nList+"' WHERE id_group ="+listData.get(i).get("id_group"));
				db.doCommit();
			}
		}else if(getListMode().equals("Less")){
			System.out.println("Less");
			String sql = "SELECT * FROM group_tax WHERE list_group >="+getList()+"AND list_group < "+getOldList();
			ArrayList<HashMap<String,String>> listData = db.getData(sql);
			//System.out.println("SetTaxDAOLess>>>>>>>>>>"+listData);
			for(int i=0;i<listData.size();i++){
				int nList = Integer.parseInt(listData.get(i).get("list_group"));
				++nList;
				db.doUpdate("UPDATE group_tax SET list_group = '"+nList+"' WHERE id_group ="+listData.get(i).get("id_group"));
				db.doCommit();
			}
		}
	}
}
