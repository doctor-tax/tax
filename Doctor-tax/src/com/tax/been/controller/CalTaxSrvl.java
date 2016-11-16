package com.tax.been.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.tax.bean.util.DbConnector;
import com.tax.been.dao.CalTaxDAO;

/**
 * Servlet implementation class CalTaxSrvl
 */
@WebServlet("/CalTaxSrvl")
public class CalTaxSrvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CalTaxSrvl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("method").equals("save")) {
			String id = request.getParameter("id");
			String month = request.getParameter("month");
			int monthInt = Integer.parseInt(month);
			String year = request.getParameter("year");
			System.out.println(id + "   " + month + "   " + year);
			PrintWriter out = response.getWriter();
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
							+ " (SELECT t7.id,t7.hcode,t7.doctor_id,t7.doctor_income,t7.income,t7.sum_pay_tax,SUM(t7.sum_tax_break) AS sum_tax_break,'201603' AS 'stdate' FROM"
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
							+ " (SELECT MAX(id) AS '_max' FROM tra_tax) t4,(SELECT SUM(doctor_income) AS 'Income' FROM doctor_income "
							+ " WHERE doctor_month BETWEEN '201601' AND '201602') t5,(SELECT sum_pay_tax FROM pay_tax WHERE tax_period = '201601') t6"
							+ " WHERE t3.doctor_id = '01' AND doctor_month = '201602' AND t1.id=t4._max AND group_id <> 0) t7"
							+ " GROUP BY t7.id,t7.doctor_id,t7.income,t7.hcode,t7.doctor_income,t7.sum_pay_tax) t8,"
							+ " (select t1.tax_break,t2.type,t2.tax_percent,t2.tax_rate,t2.tax_amount from tra_tax t1  "
							+ " LEFT JOIN order_tax t2 on t1.tax_id=t2.id,(SELECT MAX(id) AS '_max' FROM tra_tax) t3 where "
							+ " t2.group_id=0 " + " and t1.id=t3._max " + " ) t9 ) t10"
							+ " group by t10.id,t10.hcode,t10.doctor_income,t10.Income,t10.sum_pay_tax,t10.sum_tax_break,t10.stdate,t10.sum_donate";
			DbConnector db = new DbConnector();
			db.doConnect();
			JSONObject obj = db.getJsonData(sql);
			CalTaxDAO cd = new CalTaxDAO();
			try {
				String tranId = obj.getString("id");
				//String docId = obj.getString("doctor_id");
				String docIncome = Double.toString(obj.getDouble("Income"));
				// obj.getDouble("income");
				String taxBreak = obj.getString("sum_tax_break");
				String date = obj.getString("stdate");
				// System.out.println(tranId+" "+docId+" "+docIncome+"
				// "+taxBreak+" "+date);
				cd.setTranId(tranId);
				cd.setDocId(id);
				cd.setDocIncome(docIncome);
				cd.setTaxBreak(taxBreak);

				Double net = Double.parseDouble(docIncome) - Double.parseDouble(taxBreak);
				System.out.println(net);
				Double tax = 0.00;
				System.out.println(tax);
				String sqlStep = "SELECT * FROM step_tax";
				ArrayList<HashMap<String, String>> listData = db.getData(sqlStep);
				for (int i = 0; i < listData.size(); i++) {
					Double start = Double.parseDouble(listData.get(i).get("step_start"));
					Double end = Double.parseDouble(listData.get(i).get("step_end"));
					Double percent = Double.parseDouble(listData.get(i).get("step_percent"));

					if (net < 0) {
						break;
					} else if (net <= end) {
						tax = tax + ((net - start) * percent / 100);
						break;
					} else if (net > end) {
						tax = tax + ((end - start) * percent / 100);
					}

				}

				if (monthInt > 1) {
					String beforeMonth = year + String.format("%02d", monthInt - 1);
					Double oldTax = Double
							.parseDouble(db
									.getData(
											"select sum(pay_tax) AS sum from pay_tax where tax_period between '201601' AND '"
													+ beforeMonth + "' AND doctor_id = '" + id + "'")
									.get(0).get("sum"));
					tax = tax - oldTax;
				}
				NumberFormat formatter = new DecimalFormat("#0.00");
				out.println(formatter.format(tax));

				System.out.println("tax-teaxBreak = " + taxBreak + ",taxStep = " + tax);
				// cd.doSave();
			} catch (Exception e) {
				System.out.println(e);
			}

		}
	}// POST

}
