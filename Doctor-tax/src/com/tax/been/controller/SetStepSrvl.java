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

import org.json.JSONArray;

import com.tax.bean.util.DbConnector;
import com.tax.been.dao.SetStepDAO;

/**
 * Servlet implementation class SetStepSrvl
 */
@WebServlet("/SetStepSrvl")
public class SetStepSrvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetStepSrvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("method").equals("clickSave")){
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			SetStepDAO ss = new SetStepDAO();
			ss.doTruncate();
			try{
				JSONArray jArray = new JSONArray(request.getParameter("json"));
				//System.out.println(jArray.getJSONObject(0).get("inputEnd"));
				for(int i = 0 ; i<jArray.length();i++){
					String start = jArray.getJSONObject(i).getString("inputStart");
					String end = jArray.getJSONObject(i).getString("inputEnd");
					String percent = jArray.getJSONObject(i).getString("inputPercent");
					String id = Integer.toString(i+1);
					
					ss.setId(id);
					ss.setStart(start);
					ss.setEnd(end);
					ss.setPercent(percent);
					ss.doSave();
				}
				out.println(jArray);
			}catch(Exception e){
				System.out.println(e);
			}
			
			
		}else if(request.getParameter("method").equals("genStep")){
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			DbConnector db = new DbConnector();
			String sql = "SELECT * FROM step_tax";
			try{
				JSONArray jArray = db.getJsonAutoComplete(sql);
				
				System.out.println(jArray);
				
			}catch(Exception e){}
		}
	}

}
