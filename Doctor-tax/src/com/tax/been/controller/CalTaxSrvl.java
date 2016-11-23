package com.tax.been.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
			long startTime = System.nanoTime();

			response.setContentType("application/text");
			response.setCharacterEncoding("UTF-8");

			PrintWriter out = response.getWriter();
			String id = request.getParameter("id");
			String hcode = request.getParameter("hcode");
			String month = request.getParameter("month");
			String year = request.getParameter("year");
			CalculateTax cal = new CalculateTax();
			cal.setId(id);
			cal.setMonth(month);
			cal.setYear(year);
			cal.setHcode(hcode);
			
			
			out.print(cal.Calculate());
			
			long endTime = System.nanoTime();
			long duration = (endTime - startTime); 
			System.out.println("SRVL TIME === "+duration);
			

		}else if(request.getParameter("method").equals("rollback")){
			PrintWriter out = response.getWriter();
			String month = request.getParameter("month");
			String year = request.getParameter("year");
			
			CalculateTax cal = new CalculateTax();
			cal.setMonth(month);
			cal.setYear(year);
			
			out.print(cal.RollBack());
		}else if(request.getParameter("method").equals("getDbTable")){
			//System.out.println("genTable");
			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			DbConnector db = new DbConnector();
			String month = request.getParameter("month");
			String year = request.getParameter("year");
			String sql = "SELECT doctor_id,doctor_name,doctor_income,hcode,'' AS status FROM doctor_income WHERE doctor_month ='"+year+month+"'";
			JSONObject jsonObj = db.getJsonArrayData(sql);
			//System.out.println(jsonObj);
			out.println(jsonObj);
		}else if(request.getParameter("method").equals("close")){
			PrintWriter out = response.getWriter();
			String month = request.getParameter("month");
			String year = request.getParameter("year");
			
			CalculateTax cal = new CalculateTax();
			cal.setMonth(month);
			cal.setYear(year);
			
			out.print(cal.Close());
		}
	}// POST

}
