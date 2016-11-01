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
import com.tax.been.dao.SetGroupDAO;
import com.tax.been.dao.SetTaxDAO;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("method").equals("genRoleGroup")){
			response.setContentType("application/html");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			DbConnector db = new DbConnector();
			db.doConnect();
			String sql = "SELECT * FROM group_tax";
			String option = "";
			try{
				ArrayList<HashMap<String,String>> listData = db.getData(sql);
				
					for(int i = 0 ; i < listData.size() ;i++){
						String value = listData.get(i).get("id_group");
						String name = listData.get(i).get("name_group");
						option += "<option value=\""+value+"\">"+name+" </option>";
					}
				
			}catch(Exception e){}
			
			System.out.println(option);
			out.println(option);
		}else if(request.getParameter("method").equals("save")){
			PrintWriter out = response.getWriter();
			String mode = request.getParameter("mode");
			String type = request.getParameter("type");
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String amount = request.getParameter("amount");
			String max = request.getParameter("max");
			String list = request.getParameter("list");
			String group = request.getParameter("group");
			String percent = request.getParameter("percent");
			String rate = request.getParameter("rate");
			
			SetTaxDAO sd = new SetTaxDAO();
			sd.setType(type);
			sd.setId(id);
			sd.setOrder(name);
			sd.setList(list);
			sd.setGroup(group);
			if(type.equals("f")){
				sd.setAmount(amount);
				sd.setMax(max);
				
				sd.setPercent("0");
				sd.setRate("0");
			}else if(type.equals("r")){
				sd.setRate(rate);
				
				sd.setAmount("0");
				sd.setMax("0");
				sd.setPercent("0");
			}else if(type.equals("p")){
				sd.setPercent(percent);
				
				sd.setAmount("0");
				sd.setMax("0");
				sd.setRate("0");
			}else if(type.equals("fr")){
				sd.setAmount("0");
				sd.setMax("0");
				sd.setRate("0");
				sd.setPercent("0");
			}else if(type.equals("pr")){
				sd.setPercent(percent);
				sd.setRate(rate);
				
				sd.setAmount("0");
				sd.setMax("0");
			}
			
			if(mode.equals("New")){
				sd.doSave();
				out.println("Insert Success!!!");
			}else if(mode.equals("Update")){
				sd.doUpdate();
				out.println("Update Success!!!!");
			}
			
		}else if(request.getParameter("method").equals("delete")){
			PrintWriter out = response.getWriter();
			String id = request.getParameter("id");
			SetGroupDAO ud = new SetGroupDAO();
			ud.setId(id);
			ud.doDelete();
			
			out.print("Delete Success!");
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
