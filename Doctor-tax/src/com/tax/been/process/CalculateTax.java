package com.tax.been.process;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.json.JSONObject;

import com.tax.bean.util.DbConnector;
import com.tax.been.dao.CalTaxDAO;

public class CalculateTax {
	DbConnector db = new DbConnector();
	CalTaxDAO cd = new CalTaxDAO();
	
	public CalculateTax() {
		if(db == null){
			db = new DbConnector();
		}
		
		if(cd == null){
			cd = new CalTaxDAO();
		}
		
		// TODO Auto-generated constructor stub
	}
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
		
		db.doConnect();
		int monthInt = Integer.parseInt(getMonth());
		String beforeMonth = getYear() + String.format("%02d", monthInt - 1);
		String status = "Calculate Success!!!";
		
		
			long startTime = System.nanoTime();
			
			// sql คำนวณลดหย่อน
			
			String sql = "IF (SELECT MAX(id) AS '_max' FROM tra_tax WHERE doctor_id='" + id + "') > 0"
					+ " SELECT t10.id,t10.hcode,t10.doctor_income,t10.Income,t10.sum_pay_tax,t10.stdate,t10.sum_tax_break,SUM(t10.sum_donate) as 'sum_donate' FROM "
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
					+ "' AND doctor_id = '" + id
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
					+ " group by t10.id,t10.hcode,t10.doctor_income,t10.Income,t10.sum_pay_tax,t10.sum_tax_break,t10.stdate"
					+ " ELSE"
					+ " select t4.hcode,t4.doctor_income,t4.Income,t4.sum_pay_tax,t4.stdate,sum(t4.sum_tax_break) sum_tax_break,t4.sum_donate from"
					+ " (select t1.hcode,t1.doctor_income,t3.Income,t2.sum_pay_tax,'" + getYear() + getMonth() + "' AS 'stdate'," + " CASE"
					+ " WHEN t0.type='a'  THEN t0.tax_amount" + " WHEN t0.type='s'  THEN (t3.Income*t0.tax_percent)/100"
					+ " END" + " AS 'sum_tax_break',0 AS 'sum_donate'"
					+ " from doctor_income t1,(select * from order_tax where type in ('a','s')) t0,"
					+ " (SELECT CASE WHEN ISNULL(COUNT(sum_pay_tax),0)=0 THEN 0 WHEN (SELECT sum_pay_tax FROM pay_tax WHERE tax_period = '" +beforeMonth+ "' AND doctor_id='"+id+"') <> 0 THEN "
					+ " (SELECT sum_pay_tax FROM pay_tax WHERE tax_period = '"+beforeMonth+"' AND doctor_id='"+id+"') ELSE 0 END AS 'sum_pay_tax' FROM pay_tax WHERE tax_period = '"+beforeMonth+"' "
					+ " AND doctor_id='"+id+"') t2, (SELECT SUM(doctor_income) AS 'Income' FROM doctor_income WHERE doctor_month BETWEEN '"+getYear()+"01' AND '" + getYear() + getMonth() + "' and doctor_id='"+id+"') t3 "
					+ " where t1.doctor_id='"+id+"') t4 group by t4.hcode,t4.doctor_income,t4.Income,t4.sum_pay_tax,t4.stdate,t4.sum_donate";

			JSONObject obj = db.getJsonData(sql);
			long endTime = System.nanoTime();
			long duration = (endTime - startTime); 
			//System.out.println("QRY CAL TIME === "+duration);
			
			try {
				long startTime1 = System.nanoTime();
				
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
				long endTime1 = System.nanoTime();
				long duration1 = (endTime1 - startTime1); 
				//System.out.println("SET DAO TIME === "+duration1);
				Double net = Double.parseDouble(income) - Double.parseDouble(taxBreak) - Double.parseDouble(donate);
				Double tax = 0.00;
				// คำนวนขั้นบันได
				String sqlStep = "SELECT * FROM step_tax";
				long startTime2 = System.nanoTime();
				
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

				long endTime2 = System.nanoTime();
				long duration2 = (endTime2 - startTime2); 
				//System.out.println("CAL STEP TIME === "+duration2);
				cd.setPayTax(Double.toString(tax));
				long startTime3 = System.nanoTime();
				cd.doSave();
				long endTime3 = System.nanoTime();
				long duration3 = (endTime3 - startTime3); 
				//System.out.println("DAO SAVE TIME === "+duration3);
				// System.out.println("Net = " + net + ",taxStep = " + tax);
				//db.doDisconnect();
			} catch (Exception e) {
				e.printStackTrace();
				status = "Calculate Fail!!";
			}

		//}
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

	public String Close() {
		String status = "Close Complete";
		DbConnector db = new DbConnector();
		db.doConnect();
		ArrayList<HashMap<String, String>> check = db
				.getData("SELECT * FROM pay_tax WHERE tax_period ='" + getYear() + getMonth() + "'");
		if (check.size() != 0 && check.get(0).get("status").equals("c")) {
			status = "This Month is Close!!!";
		} else if (check.size() == 0) {
			status = "This Month No Record";
		} else {
			String date = getYear() + getMonth();
			CalTaxDAO cd = new CalTaxDAO();
			cd.setDate(date);
			String checked = cd.doClose();
			if (checked.equals("S")) {
				status = "Close This Month Success!!!";
			} else {
				status = "Close This Month Fail Please try again!!!";
			}
		}
		return status;
	}
}
