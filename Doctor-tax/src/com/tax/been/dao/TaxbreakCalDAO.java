package com.tax.been.dao;

import java.util.Date;

import com.tax.bean.util.DbConnector;

public class TaxbreakCalDAO {
	int id;
	String doctor_id;
	int tax_id;
	double tax_break;
	Date datestam;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(String doctor_id) {
		this.doctor_id = doctor_id;
	}

	public int getTax_id() {
		return tax_id;
	}

	public void setTax_id(int tax_id) {
		this.tax_id = tax_id;
	}

	public double getTax_break() {
		return tax_break;
	}

	public void setTax_break(double tax_break) {
		this.tax_break = tax_break;
	}

	public Date getDatestam() {
		return datestam;
	}

	public void setDatestam(Date datestam) {
		this.datestam = datestam;
	}

	public void doSave() {
		DbConnector conn = new DbConnector();
		conn.doConnect();
		conn.doSave("INSERT INTO tra_tax (id,doctor_id,tax_id,tax_break,datestam) " + " VALUES('" + getId() + "','"
				+ getDoctor_id() + "','" + getTax_id() + "','" + getTax_break() + "','" + getDatestam() + "')");
		conn.doCommit();
	}

	public void doUpdate() {
		// DbConnector conn = new DbConnector();
		// conn.doConnect();
		// conn.doUpdate("UPDATE order_tax SET tax_order = '" + getOrder() +
		// "',tax_percent = '" + getPercent()
		// + "',tax_rate = '" + getRate() + "',tax_amount = '" + getAmount() +
		// "',tax_list = '" + getList()
		// + "',max_val = '" + getMax() + "',group_id = '" + getGroup() + "'" +
		// "WHERE id = '" + getId() + "'");
		// conn.doCommit();
	}

	public void doDelete() {
		// DbConnector conn = new DbConnector();
		// conn.doConnect();
		// conn.doDelete("DELETE FROM order_tax WHERE id = '"+getId()+"'");
		// conn.doCommit();
	}

}
