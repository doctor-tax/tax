package com.tax.been.dao;

import com.tax.bean.util.DbConnector;

public class SetStepDAO {
	private String id ;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getPercent() {
		return percent;
	}
	public void setPercent(String percent) {
		this.percent = percent;
	}
	private String start;
	private String end;
	private String percent;
	
	public void doSave() {
		DbConnector db = new DbConnector();
		db.doConnect();
		db.doSave("INSERT INTO step_tax(step_id,step_start,step_end,step_percent) "
				+ "VALUES('"+getId()+"','"+getStart()+"','"+getEnd()+"','"+getPercent()+"')");
		db.doCommit();
		db.doDisconnect();
	}
	
	public void doTruncate(){
		DbConnector db = new DbConnector();
		db.doConnect();
		db.doSave("TRUNCATE TABLE step_tax");
		db.doCommit();
		db.doDisconnect();
	}
}
