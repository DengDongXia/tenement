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
import utils.InstanceUtil;
import utils.JsonUtil;

public class AdminServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
		System.out.println("Test");
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
		
		String type = JsonUtil.removeQuote(map.get("type").toString());
		
		if("login".equals(type)) {
			String userName = JsonUtil.removeQuote(map.get("userName").toString());
			String psw = JsonUtil.removeQuote(map.get("psw").toString());
			Admin admin = InstanceUtil.adi.login(userName, psw);
			if(admin==null) {
				res.put("ret", "false");
				res.put("reason", "ÕËºÅ»òÃÜÂë´íÎó¡£");
			}else {
				session.setAttribute("admin", admin);
				res.put("ret", "true");
			}
		}else if("logout".equals(type)) {	
			session.removeAttribute("admin");
			res.put("ret", "true");
		}else {
			res.put("ret", "false");
			res.put("reason", "Î´Öª²ÎÊý");
		}
						
		result = gson.toJson(res);
		System.out.println(result);
		out.print(result);
		out.flush();
		out.close();
	}

}
