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
			String year = request.getParameter("year");
			System.out.println(id + "   " + month + "   " + year);
			PrintWriter out = response.getWriter();
			String sql = "DECLARE " + "@SetYear CHAR(4)," + "@SetStrMonth CHAR(2)," + "@SetEndMonth CHAR(2),"
					+ "@IDDoctor CHAR(50)SET " + "@SetYear = '2016'SET " + "@SetStrMonth = '01'SET "
					+ "@SetEndMonth = '03'SET "
					+ "@IDDoctor = '01'SELECT t6.id,t6.doctor_id,t6.income,SUM(t6.sum_tax_break) "
					+ "AS sum_all_tax_break,@SetYear+@SetEndMonth AS 'stdate' FROM "
					+ "(SELECT t1.*,t2.type,t2.tax_rate,t2.tax_percent,t2.tax_amount,t5.Income," + "CASE "
					+ "WHEN t2.type='a' THEN t1.tax_break "
					+ "WHEN t2.type='s' AND (t5.Income*t2.tax_percent)/100 >= t2.tax_rate THEN t2.tax_rate "
					+ "WHEN t2.type='s' AND (t5.Income*t2.tax_percent)/100 < t2.tax_rate THEN (t5.Income*t2.tax_percent)/100 "
					+ "WHEN t2.type='f' THEN t2.tax_amount * t1.tax_break WHEN t2.type='fr' THEN t1.tax_break "
					+ "WHEN t2.type='p' AND t1.tax_break >= (t5.Income*t2.tax_percent)/100 THEN (t5.Income*t2.tax_percent)/100 "
					+ "WHEN t2.type='p' AND t1.tax_break < (t5.Income*t2.tax_percent)/100 THEN t1.tax_break "
					+ "WHEN t2.type='pr' AND t1.tax_break >= (t5.Income*t2.tax_percent)/100 THEN t2.tax_rate "
					+ "WHEN t2.type='pr' AND t1.tax_break < (t5.Income*t2.tax_percent)/100 THEN t1.tax_break "
					+ "WHEN t2.type='r' AND t1.tax_break >= t2.tax_rate THEN t2.tax_rate WHEN t2.type='r' AND t1.tax_break < t2.tax_rate THEN t1.tax_break "
					+ "END AS 'sum_tax_break' FROM tra_tax t1 LEFT JOIN order_tax t2 ON t1.tax_id=t2.id "
					+ "LEFT JOIN doctor_income t3  ON t1.doctor_id=t3.doctor_id , (SELECT MAX(id) AS '_max' FROM tra_tax) t4,"
					+ "(SELECT SUM(doctor_income) AS 'Income' FROM doctor_income WHERE MONTH(doctor_month) BETWEEN @SetStrMonth AND @SetEndMonth) "
					+ "t5 WHERE t3.doctor_id = @IDDoctor AND YEAR(t3.doctor_month)=@SetYear AND MONTH(t3.doctor_month) = @SetEndMonth AND t1.id=t4._max) t6 GROUP BY t6.id,t6.doctor_id,t6.income";
			System.out.println(sql);
			DbConnector db = new DbConnector();
			db.doConnect();
			JSONObject obj = db.getJsonData(sql);
			CalTaxDAO cd = new CalTaxDAO();
			try {
				System.out.println("try");
				String tranId = obj.getString("id");
				String docId = obj.getString("doctor_id");
				String docIncome = Double.toString(obj.getDouble("income"));
						//obj.getDouble("income");
				String taxBreak = obj.getString("sum_all_tax_break");
				String date = obj.getString("stdate");
				System.out.println(tranId+" "+docId+" "+docIncome+" "+taxBreak+" "+date);
				cd.setTranId(tranId);
				cd.setDocId(docId);
				cd.setDocIncome(docIncome);
				cd.setTaxBreak(taxBreak);

				db.doDisconnect();

				db.doConnect();

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
						tax = tax+((net - start) * percent / 100);
						break;
					} else if (net > end) {
						tax = tax + ((end - start) * percent / 100);
					}
					

				}
				NumberFormat formatter = new DecimalFormat("#0.00");     
				out.println(formatter.format(tax));
				
				System.out.println(docIncome + "-" + taxBreak + "= taxStep "+tax);
				// cd.doSave();
			} catch (Exception e) {
				System.out.println(e);
			}

			
		}
	}// POST

}
