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
import com.tax.been.process.CalculateTax;

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
			PrintWriter out = response.getWriter();
			//String id = request.getParameter("id");
			String month = request.getParameter("month");
			String year = request.getParameter("year");
			
			CalculateTax cal = new CalculateTax();
			cal.setMonth(month);
			cal.setYear(year);
			
			out.println(cal.Calculate());
			

		}
	}// POST

}
