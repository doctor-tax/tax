package com.tax.been.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.tax.bean.util.DbConnector;

/**
 * Servlet implementation class TaxbreakAdmin
 */
@WebServlet("/TaxbreakAdminSrvl")
public class TaxbreakAdminSrvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TaxbreakAdminSrvl() {
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
		/*
		 * if (request.getParameter("method").equals("getDbTable")) {
		 * response.setContentType("application/html");
		 * response.setCharacterEncoding("UTF-8"); PrintWriter out =
		 * response.getWriter(); DbConnector db = new DbConnector();
		 * db.doConnect(); String sql =
		 * "SELECT t1.*,t2.name_group FROM order_tax t1 " +
		 * "join group_tax t2 on t1.group_id=t2.id_group " +
		 * "order by t2.list_group,t1.tax_list"; String dbtb = ""; try {
		 * ArrayList<HashMap<String, String>> listData = db.getData(sql);
		 * 
		 * for (int i = 0; i < listData.size(); i++) { String id =
		 * listData.get(i).get("id"); String tax_order =
		 * listData.get(i).get("tax_order"); String tax_list =
		 * listData.get(i).get("tax_list"); String group =
		 * listData.get(i).get("name_group");
		 * 
		 * dbtb += "<tr ondblclick=\"getID('" + id + "')\"><td>" + group +
		 * "</td>" + "<td>" + tax_list + "</td>" + "<td>" + tax_order +
		 * "</td></tr>";
		 * 
		 * }
		 * 
		 * } catch (Exception e) { out.println(e); } db.doCommit();
		 * System.out.println(dbtb); out.println(dbtb); }
		 */

		System.out.println(request.getParameter("medthod"));
		if (request.getParameter("medthod").equals("getDbTable")) {
			response.setContentType("application/html");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			DbConnector db = new DbConnector();
			db.doConnect();
			String sql = "SELECT t1.id,t2.name_group,t1.tax_list,t1.tax_order FROM order_tax t1 join group_tax t2 on t1.group_id=t2.id_group order by t2.list_group,t1.tax_list";
			JSONObject jsonObj = db.getJsonArrayData(sql);
			out.println(jsonObj);
			System.out.println(jsonObj);
		}
	}

}
