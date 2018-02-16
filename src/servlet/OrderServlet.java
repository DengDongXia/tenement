package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.Admin;
import model.House;
import model.HouseModel;
import model.OrderModel;
import model.User;
import utils.ConstansUtil;
import utils.InstanceUtil;
import utils.JsonUtil;
import utils.ToolsUtil;

public class OrderServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
		
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("cache-contol", "no-cache");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		String data = JsonUtil.getRequestPayload(request);
		System.out.println(data);
		Map<String, Object> map = JsonUtil.toMap(data);
		Map<String, Object> res = new HashMap<String, Object>();
		Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
		String result = "";
		HttpSession session = request.getSession(false); 
		
		if(session!=null) { 
			String type = JsonUtil.removeQuote(map.get("type").toString());
		
			User user = (User)session.getAttribute("user");
			Admin admin = (Admin)session.getAttribute("admin");
			if(user!=null) {
				if("publish".equals(type)) {
					String id = JsonUtil.removeQuote(map.get("houseId").toString());
					HouseModel hm = InstanceUtil.hdi.getHouseWithId(id);
					
					User publisher = InstanceUtil.odi.publishOrder(ToolsUtil.HouseModelToHouse(hm), user);
					if(publisher!=null) {
						res.put("ret", "true");
						res.put("data",publisher.getPhone());
					}else {
						res.put("ret", "false");
						res.put("reason", "获取房东信息失败");
					}
				}else if("get".equals(type)) {
					String filter = JsonUtil.removeQuote(map.get("filter").toString());
					String id = JsonUtil.removeQuote(map.get("id").toString());
					OrderModel[] arr  = null;
					
					if("id".equals(filter)) {
						arr = InstanceUtil.odi.getOrderWithId(id);
						if(arr!=null) {
							res.put("ret", "true");
							res.put("data", arr);
						}else {
							res.put("ret", "false");
							res.put("reason", "获取失败");
						}
						
					}else if("userId".equals(filter)) {
						arr = InstanceUtil.odi.getOrderWithUserId(id);
						String toPage = JsonUtil.removeQuote(map.get("toPage").toString());
						int sumPage = ToolsUtil.getPages(arr, ConstansUtil.ORDER_COUNT_PER_PAGE);
						if(Integer.parseInt(toPage) >sumPage) {
							res.put("ret", "false");
							res.put("reason", "请求页数过大");
						}else {
							res.put("ret","true");
							res.put("data", ToolsUtil.split(arr, Integer.parseInt(toPage), ConstansUtil.ORDER_COUNT_PER_PAGE));
						}
					}else if("publisherId".equals(filter)) {
						arr = InstanceUtil.odi.getOrderWithPublisher(id);
						String toPage = JsonUtil.removeQuote(map.get("toPage").toString());
						int sumPage = ToolsUtil.getPages(arr, ConstansUtil.ORDER_COUNT_PER_PAGE);
						if(Integer.parseInt(toPage) >sumPage) {
							res.put("ret", "false");
							res.put("reason", "请求页数过大");
						}else {
							res.put("ret","true");
							res.put("data", ToolsUtil.split(arr, Integer.parseInt(toPage), ConstansUtil.ORDER_COUNT_PER_PAGE));
						}
					}else if("publisherIdUnconfirm".equals("filter")) {
						arr = InstanceUtil.odi.getOrderWithPublisherUnconfirm(id);
						String toPage = JsonUtil.removeQuote(map.get("toPage").toString());
						int sumPage = ToolsUtil.getPages(arr, ConstansUtil.ORDER_COUNT_PER_PAGE);
						if(Integer.parseInt(toPage) >sumPage) {
							res.put("ret", "false");
							res.put("reason", "请求页数过大");
						}else {
							res.put("ret","true");
							res.put("data", ToolsUtil.split(arr, Integer.parseInt(toPage), ConstansUtil.ORDER_COUNT_PER_PAGE));
						}
					}else {
						res.put("ret", "false");
						res.put("reason", "没有权限");
						
					}
					
				}else if("confirm".equals(type)){
					String id = JsonUtil.removeQuote(map.get("id").toString());
					if(InstanceUtil.odi.confirmOrder(id)) {
						res.put("ret", "true");
					}else {
						res.put("ret", "false");
						res.put("reason", "服务器故障");
					}
				}else {
					res.put("ret", "false");
					res.put("reason", "错误请求");
				}
			}else if(admin!=null){
				if("get".equals(type)) {
					String filter = JsonUtil.removeQuote(map.get("filter").toString());
					if("all".equals(filter)) {
						OrderModel[] arr = InstanceUtil.odi.getOrder();
						if(arr!=null) {
							res.put("ret", "true");
							res.put("data", arr);
						}else {
							res.put("ret", "false");
							res.put("reason", "获取失败");
						}
					}else if("unConfirm".equals(filter)){
						OrderModel[] arr = InstanceUtil.odi.getOrderUnconfirm();
						if(arr!=null) {
							res.put("ret", "true");
							res.put("data", arr);
						}else {
							res.put("ret", "false");
							res.put("reason", "获取失败");
						}
					}else {
						res.put("ret", "false");
						res.put("reason", "请求错误");
					}
					
				}else if("audit".equals(type)){
					String id = JsonUtil.removeQuote(map.get("id").toString());
					String action = JsonUtil.removeQuote(map.get("action").toString());
	
					if(InstanceUtil.odi.auditOrder(id, Integer.parseInt(action))) {
						res.put("ret", "true");
						
					}else {
						res.put("ret", "false");
						res.put("reason", "服务器错误");
					}
				}else {
					res.put("ret", "false");
					res.put("reason", "错误请求");
				}
			}else {
				res.put("ret", "false");
				res.put("reason", "您未登录");
			}
			
				
		}
		result = gson.toJson(res);
		System.out.println(result);
		out.print(result);
		out.flush();
		out.close();	
	}
	

}
