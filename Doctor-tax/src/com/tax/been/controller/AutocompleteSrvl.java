package com.tax.been.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.tax.bean.util.DbConnector;

/**
 * Servlet implementation class AutocompleteSrvl
 */
@WebServlet("/AutocompleteSrvl")
public class AutocompleteSrvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AutocompleteSrvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		DbConnector db = new DbConnector();
		String term = request.getParameter("term");
		String tb = request.getParameter("tb");
		String value = request.getParameter("value");
		//System.out.println(term);
		if(tb.equals("DOC_NAME")){
			String sql = "SELECT doctor_id,doctor_name FROM doctor_income WHERE doctor_id+doctor_name LIKE '%"+value+"%'";
			JSONArray jArray = db.getJsonAutoComplete(sql);
			out.println(jArray);
		}
		
	}

}
