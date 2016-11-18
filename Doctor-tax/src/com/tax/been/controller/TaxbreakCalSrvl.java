package com.tax.been.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
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
import org.json.JSONException;
import org.json.JSONObject;

import com.tax.bean.util.DbConnector;
import com.tax.been.dao.TaxbreakCalDAO;

/**
 * Servlet implementation class TaxbreakCal
 */
@WebServlet("/TaxbreakCal")
public class TaxbreakCalSrvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TaxbreakCalSrvl() {
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
		// TODO Auto-generated method stub
		// doGet(request, response);
		if (request.getParameter("method").equals("InsertTra")) {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			TaxbreakCalDAO db = new TaxbreakCalDAO();

			// get Max id tra_tax
			String sql = "select max(id) as 'max' from tra_tax;";
			DbConnector conn = new DbConnector();
			conn.doConnect();
			ArrayList<HashMap<String, String>> listData = conn.getData(sql);
			String _max = listData.get(0).get("max");

			// System.out.println(_max);
			int max = 0;
			if (_max == null || _max == "") {
				max += 1;
			} else {
				max = Integer.parseInt(_max) + 1;
			}

			// Get Date time Current
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			String df = dateFormat.format(date);

			try {
				JSONArray jsonAray = new JSONArray(request.getParameter("json"));
				for (int i = 0; i < jsonAray.length(); i++) {
					int tax_id = Integer.parseInt(jsonAray.getJSONObject(i).getString("id"));
					double value = Double.parseDouble(jsonAray.getJSONObject(i).getString("value"));
					db.setDoctor_id("010");
					db.setId(max);
					db.setDatestam(df);
					db.setTax_id(tax_id);
					db.setTax_break(value);
					db.doSave();

				}

			} catch (JSONException e) {
				e.printStackTrace();
			}
			JSONObject jsonResult = new JSONObject();
			try {
				jsonResult.put("result", "Insert Data Success");
				out.println(jsonResult);
			} catch (JSONException e) {
				e.printStackTrace();
			}

		} else if (request.getParameter("method").equals("getOrder")) {
			response.setContentType("application/html");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			DbConnector dbMain = new DbConnector();
			dbMain.doConnect();
			String sqlMain = "select t1.group_id,t2.name_group from order_tax t1 join group_tax t2 on group_id = id_group group by t1.group_id,t2.name_group,list_group order by list_group;";
			String taxOrderMain = "";

			DbConnector dbSub = new DbConnector();
			dbSub.doConnect();
			String sqlSub = "select t1.id,tax_order,tax_percent,tax_rate,tax_amount,type,tax_list,max_val,group_id "
					+ "from order_tax t1 join group_tax t2 on group_id = id_group order by group_id,tax_list;";
			String taxOrderSub = "";

			String result = "";

			try {
				ArrayList<HashMap<String, String>> listDataMain = dbMain.getData(sqlMain);

				for (int i = 0; i < listDataMain.size(); i++) {
					String group_main = listDataMain.get(i).get("group_id");
					String name_group = listDataMain.get(i).get("name_group");

					taxOrderMain += "<div class=\"panel panel-default\">"
							+ "<button type=\"button\" class=\"btn btn-primary text-left btn-block\"data-toggle=\"collapse"
							+ "\" " + "data-target=\"#coll-" + group_main + "\"><span class=\"pull-left\">" + name_group
							+ "</span></button>";

					taxOrderSub += "" + "<div id=\"coll-" + group_main + "\" class=\"panel-collapse collapse fade "
							+ "\">" + "<div class=\"panel-body\">";

					try {
						ArrayList<HashMap<String, String>> listDataSub = dbMain.getData(sqlSub);

						for (int j = 0; j < listDataSub.size(); j++) {

							String tax_id = listDataSub.get(j).get("id");
							String tax_order = listDataSub.get(j).get("tax_order");
							// String tax_percent =
							// listDataSub.get(j).get("tax_percent");
							// String tax_rate =
							// listDataSub.get(j).get("tax_rate");
							String tax_amount = listDataSub.get(j).get("tax_amount");
							String type = listDataSub.get(j).get("type");
							// String tax_list =
							// listDataSub.get(j).get("tax_list");
							String max_val = listDataSub.get(j).get("max_val");
							String group_sub = listDataSub.get(j).get("group_id");

							if (group_main.equals(group_sub)) {

								if (type.equals("s")) {
									tax_order = "";
								}

								taxOrderSub += "<div class=\"row form-group\">"
										+ "<div class=\"col-sm-6 text-right control-label\">" + tax_order + "</div>"
										+ "<div class=\"col-sm-6 col-sm-3\" id=\"taxOrder\">";
								if (type.equals("f")) {
									taxOrderSub += "" + "<select class=\"form-control input-sm Tax\" id=\"" + tax_id
											+ "\">" + "<option value=\"0\">ไม่มี</option>";
									for (int k = 1; k <= Integer.parseInt(max_val); k++) {
										taxOrderSub += "<option value=\"" + k + "\">" + k + " คน</option>";
									}
									taxOrderSub += "" + "</select>";

								} else if (type.equals("a")) {
									taxOrderSub += "<div class=\"input-group\">";
									taxOrderSub += "<input id = \"" + tax_id
											+ "\" type=\"number\" class=\"form-control input-sm bfh-number Tax\" "
											+ " placeholder=\"จำนวนเงิน\" aria-describedby=\"basic-addon1\" value=\""
											+ tax_amount + "\" disabled> "
											+ "  <span class=\"input-group-addon\">บาท</span>" + " </div>";

								} else if (type.equals("s")) {

									taxOrderSub += "<div class=\"input-group\">";
									taxOrderSub += "<input id = \"" + tax_id
											+ "\" type=\"hidden\" class=\"form-control input-sm bfh-number Tax\" "
											+ " placeholder=\"จำนวนเงิน\" aria-describedby=\"basic-addon1\" value=\""
											+ tax_amount + "\"> " + " </div>";

								} else {
									taxOrderSub += "<div class=\"input-group\">";
									taxOrderSub += "<input id = \"" + tax_id
											+ "\" type=\"number\" class=\"form-control input-sm bfh-number Tax\" "
											+ " placeholder=\"จำนวนเงิน\" aria-describedby=\"basic-addon1\"> "
											+ "  <span class=\"input-group-addon\">บาท</span>" + " </div>";
								}
								taxOrderSub += "" + "</div>" + "</div>";

							}

						}

						taxOrderSub += "" + "</div>" + "</div>";

						taxOrderMain += taxOrderSub;

					} catch (Exception e) {
						out.println(e);
					}
					taxOrderMain += "</div>";
					result += taxOrderMain;
					taxOrderMain = "";
					taxOrderSub = "";
				}

				System.out.println(result);

			} catch (Exception e) {
				out.println(e);
			}
			dbMain.doCommit();
			// System.out.println(taxOrderMain);
			out.println(result);

		}

	}

}
