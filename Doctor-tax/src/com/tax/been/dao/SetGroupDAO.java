package com.tax.been.dao;

import com.tax.bean.util.DbConnector;

public class SetGroupDAO {
	private String id;
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
}
