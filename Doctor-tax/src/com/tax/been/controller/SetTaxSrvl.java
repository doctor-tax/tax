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
import com.tax.been.dao.OrderTaxDAO;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("method").equals("genRoleGroup")) {
			response.setContentType("application/html");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			DbConnector db = new DbConnector();
			db.doConnect();
			String sql = "SELECT * FROM group_tax";
			String option = "";
			try {
				ArrayList<HashMap<String, String>> listData = db.getData(sql);
				System.out.print(listData);

				for (int i = 0; i < listData.size(); i++) {
					String value = listData.get(i).get("id_group");
					String name = listData.get(i).get("name_group");
					option += "<option value=\"" + value + "\">" + name + " </option>";
				}

			} catch (Exception e) {
			}

			System.out.println(option);
			out.println(option);
		}else if(request.getParameter("method").equals("GenAll")){
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			JSONObject obj =new JSONObject();
			PrintWriter out = response.getWriter();
			DbConnector db = new DbConnector();
			db.doConnect();
			
			String id = request.getParameter("id");
			String sql = "SELECT * FROM order_tax WHERE id = '"+id+"'";
			
			try{
				obj = db.getJsonData(sql);
				db.doDisconnect();
				
				db.doConnect();
				String sql1 = "SELECT MAX(tax_list) AS MAX_LIST FROM order_tax WHERE group_id =" + obj.getString("group_id");
				obj.put("MAX_LIST",db.getJsonData(sql1).getString("MAX_LIST"));
				
			}catch(Exception e){
				System.out.println(e);
			}
			out.println(obj);
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
            String oldList = request.getParameter("oldList");
            System.out.println(type);
            
            int listInt = Integer.parseInt(list);
            
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
            }else if(type.equals("a")){
            	System.out.println(type);
            	sd.setAmount(amount);
            	
                sd.setMax("0");
                sd.setPercent("0");
                sd.setRate("0");
            }else if(type.equals("s")){
            	sd.setPercent(percent);
                sd.setRate(rate);
                
                sd.setAmount("0");
                sd.setMax("0");
            }
            
            if(mode.equals("New")){
                sd.doSave();
                out.println("Insert Success!!!");
            }else if(mode.equals("Update")){
            	int oldListInt = Integer.parseInt(oldList);
            	if(listInt > oldListInt){
                	sd.setOldList(oldList);
                	sd.setListMode("Greater");
                	sd.doManageList();
                	
                }else if(listInt < oldListInt){
                	sd.setOldList(oldList);
                	sd.setListMode("Less");
                	sd.doManageList();
                }
                sd.doUpdate();
                out.println("Update Success!!!!");
            }
            
        }else if(request.getParameter("method").equals("delete")){
            PrintWriter out = response.getWriter();
            String id = request.getParameter("id");
            SetTaxDAO sd = new SetTaxDAO();
            sd.setId(id);
            sd.doDelete();
            
            out.print("Delete Success!");
        }else if(request.getParameter("method").equals("GenId")){
        	response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			JSONObject obj =new JSONObject();
			PrintWriter out = response.getWriter();
			DbConnector db = new DbConnector();
			db.doConnect();
			
			String sql = "select t1.max_id,t2.max_list from "
					+ "(SELECT MAX(id) AS max_id FROM order_tax) t1,"
					+ "(SELECT MAX(tax_list) as max_list FROM order_tax WHERE group_id=1) t2";
			try{
				int id = Integer.parseInt(db.getData(sql).get(0).get("max_id"));
				System.out.println("id=="+id);
				++id;
				int list;
				if(db.getData(sql).get(0).get("max_list").length() == 0){
					list = 1 ;
					System.out.println("Nodata=="+list);
				}else{
					list = Integer.parseInt(db.getData(sql).get(0).get("max_list"));
					System.out.println("list=="+list);
					++list;
				}
				
				
				obj.put("ID",id);
				obj.put("LIST", list);
				
			}catch(Exception e){System.out.print(e);}
			
			out.println(obj);
        }else if(request.getParameter("method").equals("changeGroup")){
        	response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			JSONObject obj =new JSONObject();
			PrintWriter out = response.getWriter();
			DbConnector db = new DbConnector();
			db.doConnect();
        	String Group = request.getParameter("group");
        	String sql = "SELECT MAX(tax_list) as max_list FROM order_tax WHERE group_id="+Group;
        	try{
				int list;
				if(db.getData(sql).get(0).get("max_list").length() == 0){
					list = 1 ;
					System.out.println("Nodata=="+list);
				}else{
					list = Integer.parseInt(db.getData(sql).get(0).get("max_list"));
					System.out.println("list=="+list);
					++list;
				}
				
				obj.put("LIST", list);
				
			}catch(Exception e){System.out.print(e);}
			
			out.println(obj);
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
