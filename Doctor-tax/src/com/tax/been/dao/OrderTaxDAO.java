package com.tax.been.dao;

public class OrderTaxDAO {
	private String id;
	private String tax_order;
	private String tax_percent;
	private String tax_rate;
	private String tax_amount;
	private String type;
	private String tax_list;
	private String max_val;
	private String group_id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTax_order() {
		return tax_order;
	}

	public void setTax_order(String tax_order) {
		this.tax_order = tax_order;
	}

	public String getTax_percent() {
		return tax_percent;
	}

	public void setTax_percent(String tax_percent) {
		this.tax_percent = tax_percent;
	}

	public String getTax_rate() {
		return tax_rate;
	}

	public void setTax_rate(String tax_rate) {
		this.tax_rate = tax_rate;
	}

	public String getTax_amount() {
		return tax_amount;
	}

	public void setTax_amount(String tax_amount) {
		this.tax_amount = tax_amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTax_list() {
		return tax_list;
	}

	public void setTax_list(String tax_list) {
		this.tax_list = tax_list;
	}

	public String getMax_val() {
		return max_val;
	}

	public void setMax_val(String max_val) {
		this.max_val = max_val;
	}

	public String getGroup_id() {
		return group_id;
	}

	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}

}
