package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.House;
import model.HouseModel;
import model.User;
import utils.ConstansUtil;
import utils.InstanceUtil;
import utils.JsonUtil;
import utils.ToolsUtil;

public class HouseServlet extends HttpServlet{
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
			User user = (User)session.getAttribute("user");
			if(user!=null) {
				String type = JsonUtil.removeQuote(map.get("type").toString());
				if("all".equals(type)) {
					HouseModel[] arr = null;
					
					String toPage = JsonUtil.removeQuote(map.get("toPage").toString());
					String filter = JsonUtil.removeQuote(map.get("filter").toString());
					String sort = JsonUtil.removeQuote(map.get("sort").toString());
					if("price".equals(filter)) {
						String minPrice = JsonUtil.removeQuote(map.get("minPrice").toString());
						String maxPrice = JsonUtil.removeQuote(map.get("maxPrice").toString());
						double minP = 0;
						double maxP = Double.MAX_VALUE;
						if(!minPrice.equals("")) {
							minP = Double.parseDouble(minPrice);
						}
						if(!maxPrice.equals("")) {
							maxP = Double.parseDouble(maxPrice);
						}
						if("priceUp".equals(sort)) {
							arr = InstanceUtil.hdi.getHouseWithPriceSortByPrice(minP, maxP, true);
						}else if("priceDown".equals(sort)){
							arr = InstanceUtil.hdi.getHouseWithPriceSortByPrice(minP, maxP, false);
						}else if("timeUp".equals(sort)) {
							arr = InstanceUtil.hdi.getHouseWithPriceSortByTime(minP, maxP, true);
						}else if("timeDown".equals(sort)) {
							arr = InstanceUtil.hdi.getHouseWithPriceSortByTime(minP, maxP, false);
						}
						
					}else if("keyword".equals(filter)){
						String keyword = JsonUtil.removeQuote(map.get("keywordParam").toString());
						if("priceUp".equals(sort)) {
							arr = InstanceUtil.hdi.getHouseWithKeywordSortByPrice(keyword, true);
						}else if("priceDown".equals(sort)){
							arr = InstanceUtil.hdi.getHouseWithKeywordSortByPrice(keyword, false);
						}else if("timeUp".equals(sort)) {
							arr = InstanceUtil.hdi.getHouseWithKeywordSortByTime(keyword, true);
						}else if("timeDown".equals(sort)) {
							arr = InstanceUtil.hdi.getHouseWithKeywordSortByTime(keyword, false);
						}
					}else if("publisherId".equals(filter)) {
						String id = JsonUtil.removeQuote(map.get("id").toString());
						if("priceUp".equals(sort)) {
							arr = InstanceUtil.hdi.getHouseWithPublisherIdSortByPrice(id, true);
						}else if("priceDown".equals(sort)){
							arr = InstanceUtil.hdi.getHouseWithPublisherIdSortByPrice(id, false);
						}else if("timeUp".equals(sort)) {
							arr = InstanceUtil.hdi.getHouseWithPublisherIdSortByTime(id, true);
						}else if("timeDown".equals(sort)) {
							arr = InstanceUtil.hdi.getHouseWithPublisherIdSortByTime(id, false);
						}
						
					}else if("none".equals(filter)) {
						if("priceUp".equals(sort)) {
							arr = InstanceUtil.hdi.getHouseSortByPrice(true);
						}else if("priceDown".equals(sort)){
							arr = InstanceUtil.hdi.getHouseSortByPrice(false);
						}else if("timeUp".equals(sort)) {
							arr = InstanceUtil.hdi.getHouseSortByTime(true);
						}else if("timeDown".equals(sort)) {
							arr = InstanceUtil.hdi.getHouseSortByTime(false);
						}
					}
					if(arr!=null) {
						int sumPage = ToolsUtil.getPages(arr, ConstansUtil.HOUSE_COUNT_PER_PAGE);
						if(sumPage==0) {
							res.put("ret", "false");
							res.put("reason", "没有数据");	
						}
						else if(Integer.parseInt(toPage) > sumPage) {
							res.put("ret", "false");
							res.put("reason", "请求页数超过总页数");		
						}else {
							arr = ToolsUtil.split(arr, Integer.parseInt(toPage), ConstansUtil.HOUSE_COUNT_PER_PAGE);
							res.put("ret", "true");
							res.put("data", arr);
							res.put("sumPage", ""+sumPage);
							res.put("nowPage", toPage);
						}
					}else {
						res.put("ret","false");
						res.put("reason", "请求错误");
					}
					
				}else if("publish".equals(type)) {
					if(user.getIsLandlord()) {
						String title = JsonUtil.removeQuote(map.get("title").toString());
						String province = JsonUtil.removeQuote(map.get("province").toString());
						String city = JsonUtil.removeQuote(map.get("city").toString());
						String region = JsonUtil.removeQuote(map.get("region").toString());
						String address = JsonUtil.removeQuote(map.get("address").toString());
						String price = JsonUtil.removeQuote(map.get("price").toString());
						String comment = JsonUtil.removeQuote(map.get("comment").toString());
						String count = JsonUtil.removeQuote(map.get("count").toString());
						House hm = new House();
						hm.setAddress(address);
						hm.setCity(city);
						hm.setComment(comment);
						hm.setCount(Integer.parseInt(count));
						hm.setPrice(Double.parseDouble(price));
						hm.setProvince(province);
						hm.setPublisher(user.getId());
						hm.setRegion(region);
						hm.setTitle(title);
						ArrayList l = (ArrayList)map.get("pic");
						
						ArrayList<String> li = new ArrayList<String>();
						for(Object o:l) {
							li.add(JsonUtil.removeQuote(o.toString()));
						}
						String[] pi = new String[l.size()];
						String[] pic = li.toArray(pi);
						
						if(InstanceUtil.hdi.publishHouse(hm, pic)) {
							res.put("ret", "true");
						}else {
							res.put("ret", "false");
							res.put("reason","服务器故障");
						}
					}else {
						res.put("ret", "false");
						res.put("reason", "你不是房东，不能发布信息");
					}
				}else if("edit".equals(type)) {
					if(user.getIsLandlord()) {
						String id = JsonUtil.removeQuote(map.get("id").toString());
						
						String title = JsonUtil.removeQuote(map.get("title").toString());
						String province = JsonUtil.removeQuote(map.get("province").toString());
						String city = JsonUtil.removeQuote(map.get("city").toString());
						String region = JsonUtil.removeQuote(map.get("region").toString());
						String address = JsonUtil.removeQuote(map.get("address").toString());
						String price = JsonUtil.removeQuote(map.get("price").toString());
						String comment = JsonUtil.removeQuote(map.get("comment").toString());
						String count = JsonUtil.removeQuote(map.get("count").toString());
						House hm = new House();
						hm.setId(Integer.parseInt(id));
						hm.setAddress(address);
						hm.setCity(city);
						hm.setComment(comment);
						hm.setCount(Integer.parseInt(count));
						hm.setPrice(Double.parseDouble(price));
						hm.setProvince(province);
						hm.setPublisher(user.getId());
						hm.setRegion(region);
						hm.setTitle(title);
			
						
						if(InstanceUtil.hdi.editHouse(hm)) {
							res.put("ret", "true");
						}else {
							res.put("ret", "false");
							res.put("reason","服务器故障");
						}
					}else {
						res.put("ret", "false");
						res.put("reason", "你不是房东，不能编辑信息");
					}
				}else if("delete".equals(type)) {
					if(user.getIsLandlord()) {
						String id = JsonUtil.removeQuote(map.get("id").toString());
						House house = new House();
						house.setId(Integer.parseInt(id));
						if(InstanceUtil.hdi.deleteHouse(house)) {
							res.put("ret", "true");
						}else {
							res.put("ret", "false");
							res.put("reason","服务器故障");
						}
					}else {
						res.put("ret", "false");
						res.put("reason", "你不是房东，不能编辑信息");
					}
				}else if("info".equals(type)) {
					String id = JsonUtil.removeQuote(map.get("id").toString());
					HouseModel hm = InstanceUtil.hdi.getHouseWithId(id);
					if(hm!=null) {
						res.put("ret","true");
						res.put("data", hm);
					}else {
						res.put("ret","false");
						res.put("reason", "获取信息失败");
					}
					
				}else {
					res.put("ret","false");
					res.put("reason", "请求错误");
				}
			}else {
				res.put("ret", "false");
				res.put("reason", "您未登录");
			}
		}else {
			res.put("ret", "false");
			res.put("reason", "您未登录");
		}
		result = gson.toJson(res);
		System.out.println(result);
		out.print(result);
		out.flush();
		out.close();	
		
		
	}

}
