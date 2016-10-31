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

import org.json.JSONObject;

import com.tax.bean.util.DbConnector;

/**
 * Servlet implementation class SetGroupSrvl
 */
@WebServlet("/SetGroupSrvl")
public class SetGroupSrvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetGroupSrvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("method").equals("genTable")){
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			DbConnector db = new DbConnector();
			JSONObject obj = new JSONObject();
			db.doConnect();
			String sql = "SELECT t1.*,t2._max FROM group_tax t1,(SELECT MAX(id_group) AS '_max' FROM group_tax) t2";
			ArrayList<HashMap<String,String>> listData = db.getData(sql);
			String option = "";
			int max = Integer.parseInt(listData.get(0).get("_max"));
			++max;
			try{
					for(int i = 0 ; i < listData.size() ;i++){
						String id = listData.get(i).get("id_group");
						String name = listData.get(i).get("name_group");
						String list = listData.get(i).get("list_group");
						option += "<tr ondblclick = \"clickRow('"+id+"')\"><td>"+id+"</td>"
								+ "<td>"+name+"</td>"
								+ "<td>"+list+"</td></tr>";
					}
					
					obj.put("html",option);
					obj.put("max",max);
				
			}catch(Exception e){}
			//System.out.println(option);
			out.println(obj);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
