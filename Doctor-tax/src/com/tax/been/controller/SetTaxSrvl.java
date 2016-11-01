package com.tax.been.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tax.bean.util.DbConnector;
import com.tax.been.dao.OrderTaxDAO;

/**
 * Servlet implementation class SetTaxSrvl
 */
@WebServlet("/SetTaxSrvl")
public class SetTaxSrvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SetTaxSrvl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("method").equals("genRoleGroup")) {
			System.out.println(request.getParameter("ID"));
			System.out.println(request.getParameter("id"));
			response.setContentType("application/html");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			DbConnector db = new DbConnector();
			db.doConnect();
			String sql = "SELECT * FROM group_tax";
			String option = "";
			try {
				ArrayList<HashMap<String, String>> listData = db.getData(sql);

				for (int i = 0; i < listData.size(); i++) {
					String value = listData.get(i).get("id_group");
					String name = listData.get(i).get("name_group");
					option += "<option value=\"" + value + "\">" + name + " </option>";
				}

			} catch (Exception e) {
			}

			System.out.println(option);
			out.println(option);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
