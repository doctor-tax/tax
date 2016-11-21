package com.tax.been.process;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

import com.tax.bean.util.DbConnector;
import com.tax.been.dao.CalTaxDAO;

public class CalculateTax {
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	private String month;
	private String year;
	private String id;
	private String hcode;

	public String getHcode() {
		return hcode;
	}

	public void setHcode(String hcode) {
		this.hcode = hcode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String Calculate() {
		// System.out.println("id "+id);
		int monthInt = Integer.parseInt(getMonth());
		String beforeMonth = getYear() + String.format("%02d", monthInt - 1);
		String status = "Insert Success!!!";
		DbConnector db = new DbConnector();
		db.doConnect();
		ArrayList<HashMap<String, String>> check = db.getData("SELECT * FROM pay_tax WHERE tax_period ='" + getYear()
				+ getMonth() + "' AND doctor_id = '" + id + "' AND hcode = '" + hcode + "'");
		if (check.size() != 0 && check.get(0).get("status").equals("c")) {
			status = "This Month is Close!!!";
		} else if (check.size() != 0 && check.get(0).get("status").equals("a")) {
			status = "This Month has Calculated Please RollBack First";
		} else {
			CalTaxDAO cd = new CalTaxDAO();
			// cd.setDate(getYear()+getMonth());
			// cd.doDelete();
			/*ArrayList<HashMap<String, String>> listData = db
					.getData("SELECT * FROM doctor_income WHERE doctor_month ='" + getYear() + getMonth() + "'");
			// System.out.println("size = "+listData.size());
			if (listData.size() == 0) {
				return ("Not found doctor income");
			}*/

			// sql คำนวณลดหย่อน
			String sql = "SELECT t10.id,t10.hcode,t10.doctor_income,t10.Income,t10.sum_pay_tax,t10.stdate,t10.sum_tax_break,SUM(t10.sum_donate) as 'sum_donate' FROM "
					+ "(SELECT t8.*, " + "CASE" + "	WHEN t9.type='a'  THEN t9.tax_break"
					+ "	WHEN t9.type='s'  AND ((t8.Income-t8.sum_tax_break)*t9.tax_percent)/100 >= t9.tax_rate THEN t9.tax_rate"
					+ "	WHEN t9.type='s'  AND ((t8.Income-t8.sum_tax_break)*t9.tax_percent)/100 < t9.tax_rate THEN ((t8.Income-t8.sum_tax_break)*t9.tax_percent)/100"
					+ "	WHEN t9.type='f'  THEN t9.tax_amount * t9.tax_break"
					+ "	WHEN t9.type='fr'  THEN t9.tax_break"
					+ "	WHEN t9.type='p'  AND t9.tax_break >= ((t8.Income-t8.sum_tax_break)*t9.tax_percent)/100 THEN ((t8.Income-t8.sum_tax_break)*t9.tax_percent)/100"
					+ "	WHEN t9.type='p'  AND t9.tax_break < ((t8.Income-t8.sum_tax_break)*t9.tax_percent)/100 THEN t9.tax_break"
					+ "	WHEN t9.type='pr'  AND t9.tax_break >= ((t8.Income-t8.sum_tax_break)*t9.tax_percent)/100 THEN t9.tax_rate"
					+ "	WHEN t9.type='pr'  AND t9.tax_break < ((t8.Income-t8.sum_tax_break)*t9.tax_percent)/100 THEN t9.tax_break"
					+ "	WHEN t9.type='r'  AND t9.tax_break >= t9.tax_rate THEN t9.tax_rate"
					+ "	WHEN t9.type='r'  AND t9.tax_break < t9.tax_rate THEN t9.tax_break" + "	ELSE 0" + " END"
					+ " AS 'sum_donate'" + " FROM"
					+ " (SELECT t7.id,t7.hcode,t7.doctor_id,t7.doctor_income,t7.income,t7.sum_pay_tax,SUM(t7.sum_tax_break) AS sum_tax_break,'"
					+ getYear() + getMonth() + "' AS 'stdate' FROM"
					+ " (SELECT t1.*,t2.type,t2.tax_rate,t2.tax_percent,t2.tax_amount,t5.Income,t3.hcode,t3.doctor_income,t6.sum_pay_tax,t2.group_id,"
					+ " CASE" + "	WHEN t2.type='a'  THEN t1.tax_break"
					+ "	WHEN t2.type='s'  AND (t5.Income*t2.tax_percent)/100 >= t2.tax_rate THEN t2.tax_rate"
					+ "	WHEN t2.type='s'  AND (t5.Income*t2.tax_percent)/100 < t2.tax_rate THEN (t5.Income*t2.tax_percent)/100"
					+ "	WHEN t2.type='f'  THEN t2.tax_amount * t1.tax_break"
					+ "	WHEN t2.type='fr'  THEN t1.tax_break"
					+ "	WHEN t2.type='p'  AND t1.tax_break >= (t5.Income*t2.tax_percent)/100 THEN (t5.Income*t2.tax_percent)/100"
					+ "	WHEN t2.type='p'  AND t1.tax_break < (t5.Income*t2.tax_percent)/100 THEN t1.tax_break"
					+ "	WHEN t2.type='pr'  AND t1.tax_break >= (t5.Income*t2.tax_percent)/100 THEN t2.tax_rate"
					+ "	WHEN t2.type='pr'  AND t1.tax_break < (t5.Income*t2.tax_percent)/100 THEN t1.tax_break"
					+ "	WHEN t2.type='r'  AND t1.tax_break >= t2.tax_rate THEN t2.tax_rate"
					+ "	WHEN t2.type='r'  AND t1.tax_break < t2.tax_rate THEN t1.tax_break" + "	ELSE 0" + " END"
					+ " AS 'sum_tax_break'"
					+ " FROM tra_tax t1 LEFT JOIN order_tax t2 ON t1.tax_id=t2.id LEFT JOIN doctor_income t3  ON t1.doctor_id=t3.doctor_id ,"
					+ " (SELECT MAX(id) AS '_max' FROM tra_tax WHERE doctor_id='" + id
					+ "') t4,(SELECT SUM(doctor_income) AS 'Income' FROM doctor_income "
					+ " WHERE doctor_month BETWEEN '" + getYear() + "01' AND '" + getYear() + getMonth()
					+ "') t5,(SELECT CASE WHEN ISNULL(COUNT(sum_pay_tax),0)=0 THEN 0 WHEN (SELECT sum_pay_tax FROM pay_tax WHERE tax_period = '"
					+ beforeMonth + "' AND doctor_id='" + id
					+ "') <> 0 THEN (SELECT sum_pay_tax FROM pay_tax WHERE tax_period = '" + beforeMonth
					+ "' AND doctor_id='" + id + "') ELSE 0 END AS 'sum_pay_tax' FROM pay_tax WHERE tax_period = '"
					+ beforeMonth + "' AND doctor_id='" + id + "') t6" + " WHERE t3.doctor_id = '" + id
					+ "' AND doctor_month = '" + getYear() + getMonth() + "' AND t1.id=t4._max AND group_id <> 0) t7"
					+ " GROUP BY t7.id,t7.doctor_id,t7.income,t7.hcode,t7.doctor_income,t7.sum_pay_tax) t8,"
					+ " (select t1.tax_break,t2.type,t2.tax_percent,t2.tax_rate,t2.tax_amount from tra_tax t1  "
					+ " LEFT JOIN order_tax t2 on t1.tax_id=t2.id,(SELECT MAX(id) AS '_max' FROM tra_tax WHERE doctor_id='"
					+ id + "') t3 where " + " t2.group_id=0 " + " and t1.id=t3._max " + " ) t9 ) t10"
					+ " group by t10.id,t10.hcode,t10.doctor_income,t10.Income,t10.sum_pay_tax,t10.sum_tax_break,t10.stdate";

			JSONObject obj = db.getJsonData(sql);
			/*
			 * System.out.println("Size"+obj.length()); if(obj.length()==0){
			 * return ("Not found doctor income"); }
			 */

			System.out.println(obj);

			try {
				// รหัสโรงพยาบาล
				String hcode = obj.getString("hcode");
				// เงินเดือนหมอของเดือนนี้
				String income = obj.getString("Income");
				// เงินเดือนหมอทั้งหมด
				String docIncome = obj.getString("doctor_income");
				// เงินบริจาค
				String donate = obj.getString("sum_donate");
				// ลดหย่อน
				String taxBreak = obj.getString("sum_tax_break");

				String date = obj.getString("stdate");

				// ภาษีที่จ่ายไปแล้วในเดือนก่อน
				String sumPayTax = obj.getString("sum_pay_tax");

				cd.setDocId(id);
				cd.setDocIncome(docIncome);
				cd.setTaxBreak(taxBreak);
				cd.setDate(date);
				cd.setDonate(donate);
				cd.setHcode(hcode);
				cd.setIncome(income);

				Double net = Double.parseDouble(income) - Double.parseDouble(taxBreak) - Double.parseDouble(donate);
				Double tax = 0.00;
				// คำนวนขั้นบันได
				String sqlStep = "SELECT * FROM step_tax";

				ArrayList<HashMap<String, String>> listData1 = db.getData(sqlStep);
				for (int n = 0; n < listData1.size(); n++) {

					Double start = Double.parseDouble(listData1.get(n).get("step_start"));
					Double end = Double.parseDouble(listData1.get(n).get("step_end"));
					Double percent = Double.parseDouble(listData1.get(n).get("step_percent"));

					if (net < 0) {
						break;
					} else if (net <= end) {
						tax = tax + ((net - (start - 1)) * percent / 100);
						break;
					} else if (net > end) {
						tax = tax + ((end - (start - 1)) * percent / 100);
					}

				}
				// ลบส่วนที่จ่ายไปแล้ว
				if (monthInt > 1) {

					Double oldTax = Double.parseDouble(sumPayTax);
					tax = tax - oldTax;
					cd.setSumPayTax(Double.toString(tax + oldTax));
				} else {

					cd.setSumPayTax(Double.toString(tax));
				}

				cd.setPayTax(Double.toString(tax));

				cd.doSave();

				// System.out.println("Net = " + net + ",taxStep = " + tax);

			} catch (Exception e) {
				e.printStackTrace();
				status = "Insert Fail!!";
			}

		}
		return status;

	}

	public String RollBack() {

		String status = "Roll Back Complete";
		DbConnector db = new DbConnector();
		db.doConnect();
		ArrayList<HashMap<String, String>> check = db
				.getData("SELECT * FROM pay_tax WHERE tax_period ='" + getYear() + getMonth() + "'");
		if (check.size() != 0 && check.get(0).get("status").equals("c")) {
			status = "This Month is Close!!!";
		} else if (check.size() == 0) {
			status = "Ready To Calculate";
		} else {
			String date = getYear() + getMonth();
			CalTaxDAO cd = new CalTaxDAO();
			cd.setDate(date);
			cd.doDelete();
		}
		return (status);
	}

	public void Close() {

	}
}
