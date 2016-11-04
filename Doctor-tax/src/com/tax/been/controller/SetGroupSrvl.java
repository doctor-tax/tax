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
import com.tax.been.dao.SetGroupDAO;

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
			String sql = "SELECT t1.*,t2._max,t2.max_list FROM group_tax t1,"
					+ "(SELECT MAX(id_group) AS '_max',MAX(list_group) AS 'max_list' FROM group_tax) t2 "
					+ "ORDER BY list_group";
			ArrayList<HashMap<String,String>> listData = db.getData(sql);
			String option = "";
			int max = Integer.parseInt(listData.get(0).get("_max"));
			int oldMaxList = Integer.parseInt(listData.get(0).get("max_list"));
			++max;
			int maxList = oldMaxList+1;
			try{
					for(int i = 0 ; i < listData.size() ;i++){
						String id = listData.get(i).get("id_group");
						String name = listData.get(i).get("name_group");
						String list = listData.get(i).get("list_group");
						option += "<tr ondblclick = \"clickRow('"+id+"','"+name+"','"+list+"')\"><td>"+id+"</td>"
								+ "<td>"+name+"</td>"
								+ "<td>"+list+"</td></tr>";
					}
					
					obj.put("html",option);
					obj.put("max",max);
					obj.put("maxlist", maxList);
					obj.put("oldMax", oldMaxList);
				
			}catch(Exception e){}
			//System.out.println(option);
			out.println(obj);
			
		}else if(request.getParameter("method").equals("checkMode")){
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			
			String id = request.getParameter("id");
			PrintWriter out = response.getWriter();
			JSONObject obj =new JSONObject();
			DbConnector db = new DbConnector();
			db.doConnect();
			String sql = "SELECT * FROM group_tax WHERE id_group = '" + id + "'";
			try{
			obj = db.getJsonData(sql);
			}catch(Exception e){System.out.println(e);}
			out.println(obj);
		}else if(request.getParameter("method").equals("save")){
			PrintWriter out = response.getWriter();
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String list = request.getParameter("list");
			String mode = request.getParameter("mode");
			String oldList = request.getParameter("oldList");
			int listInt = Integer.parseInt(list);
			
			SetGroupDAO sd = new SetGroupDAO();
			sd.setId(id);
			sd.setName(name);
			sd.setList(list);
			if(mode.equals("New")){
				sd.doSave();
				out.println("Insert Success!");
			}else if(mode.equals("Update")){
				int oldListInt = Integer.parseInt(oldList);
				if(listInt > oldListInt){
                	sd.setOldList(oldListInt);
                	sd.setListMode("Greater");
                	sd.doManageList();
                	
                }else if(listInt < oldListInt){
                	sd.setOldList(oldListInt);
                	sd.setListMode("Less");
                	sd.doManageList();
                }
				sd.doUpdate();
				out.println("Update Success!");
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
