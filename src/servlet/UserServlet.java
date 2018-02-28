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
import model.User;
import utils.InstanceUtil;
import utils.JsonUtil;
import utils.ToolsUtil;

public class UserServlet  extends HttpServlet{
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
		
		
		String type = JsonUtil.removeQuote(map.get("type").toString());
		if("login".equals(type)) {
			String email = JsonUtil.removeQuote(map.get("email").toString());
			String psw = JsonUtil.removeQuote(map.get("psw").toString());
			String t = JsonUtil.removeQuote(map.get("t").toString());
			User user = InstanceUtil.udi.login(email, psw);
			if(user==null) {
				res.put("ret", "false");
				res.put("reason", "�˺Ż��������");
			}else if(user.getIsLandlord() && Integer.parseInt(t)==1){
				session.setAttribute("user", user);
				res.put("ret", "true");
			}else if((!user.getIsLandlord()) && Integer.parseInt(t)!=1){
				session.setAttribute("user", user);
				res.put("ret", "true");
			}else if((!user.getIsLandlord()) && Integer.parseInt(t)==1){
				res.put("ret", "false");
				res.put("reason", "�㲻�Ƿ���");
			}else if(user.getIsLandlord() && Integer.parseInt(t)!=1){
				res.put("ret", "false");
				res.put("reason", "�㲻�Ƿ���");
			}
		}else if("regist".equals(type)) {
			String email = JsonUtil.removeQuote(map.get("email").toString());
			String userName = JsonUtil.removeQuote(map.get("name").toString());
			String psw = JsonUtil.removeQuote(map.get("psw").toString());
			String phone = JsonUtil.removeQuote(map.get("phone").toString());
			String t = JsonUtil.removeQuote(map.get("t").toString());
			if(InstanceUtil.udi.hasUserWithEmail(email)) {
				res.put("ret", "false");
				res.put("reason", "�������ѱ�ע��");
			}else {
				User u = new User();
				u.setEmail(email);
				u.setHead("1.gif");
				u.setIsLandlord(Integer.parseInt(t)==1);
				u.setPassword(psw);
				u.setPhone(phone);
				u.setUserName(userName);
				String id;
				while( InstanceUtil.udi.hasUser( id=ToolsUtil.generateUserId() )) {};
				u.setId(id);
				if(InstanceUtil.udi.regist(u)) {
					res.put("ret", "true");
				}else {
					res.put("ret", "false");
					res.put("reason", "����������");
				}
			}
		}else if("logout".equals(type)) {
			if(session!=null) {
				User u = (User)session.getAttribute("user");
				Admin a = (Admin)session.getAttribute("admin");
				if(u!=null) {
					session.removeAttribute("user");
					res.put("ret", "true");
				}else if(a!=null) {
					session.removeAttribute("admin");
					res.put("ret", "true");
				}else {
					res.put("ret", "false");
					res.put("reason", "��δ��¼");
				}
			}else {
				res.put("ret", "false");
				res.put("reason", "��δ��¼");
			}
			
		}else if("info".equals(type)) {
			if(session!=null) {
				
				User u = (User)session.getAttribute("user");
				if(u!=null) {
					User ur = InstanceUtil.udi.getUserWithId(u.getId());
					if(ur!=null) {
						res.put("ret", "true");
						res.put("data", new User[] {ur});
					}else {
						res.put("ret", "false");
						res.put("reason", "��ȡʧ��");
					}
				}else {
					res.put("ret", "false");
					res.put("reason", "��δ��¼");
				}
			}else {
				res.put("ret", "false");
				res.put("reason", "��δ��¼");
			}
		}else if("all".equals(type)) {
			if(session!=null) {
				Admin a = (Admin)session.getAttribute("admin");
				if(a!=null) {
					User[] arr = InstanceUtil.udi.getAll();
					if(arr!=null) {
						res.put("ret", "true");
						res.put("data", arr);
					}else {
						res.put("ret", "false");
						res.put("reason", "��ȡʧ��");
					}
				}else {
					res.put("ret", "false");
					res.put("reason", "��δ��¼");
				}
			}else {
				res.put("ret", "false");
				res.put("reason", "��δ��¼");
			}
		}else if("statu".equals(type)){
			if(session!=null) {
				User u = (User)session.getAttribute("user");
				if(u!=null) {
					res.put("ret", "true");
					res.put("isLogin", "true");
					res.put("data", u);
				}else {
					res.put("ret", "true");
					res.put("isLogin", "false");
				}
			}else {
				res.put("ret", "true");
				res.put("isLogin", "false");
			}
		}else if("edit".equals(type)){
			if(session!=null) {
				User u = (User)session.getAttribute("user");
				if(u!=null) {
					String name = JsonUtil.removeQuote(map.get("name").toString());
					String phone = JsonUtil.removeQuote(map.get("phone").toString());
					u.setPhone(phone);
					u.setUserName(name);
					if(InstanceUtil.udi.updateInfo(u)) {
						res.put("ret", "true");
						session.setAttribute("user", u);
					}else {
						res.put("ret", "false");
						res.put("reason", "�޸�ʧ��");
					}
					
				}else {
					res.put("ret", "false");
					res.put("reason", "��δ��¼");
				}
			}else {
				res.put("ret", "false");
				res.put("reason", "��δ��¼");
			}
		}else if("changePsw".equals(type)){
			if(session!=null) {
				User u = (User)session.getAttribute("user");
				if(u!=null) {
					String oldPsw = JsonUtil.removeQuote(map.get("oldPsw").toString());
					String newPsw = JsonUtil.removeQuote(map.get("newPsw").toString());
					if(u.getPassword().equals(oldPsw)) {
						u.setPassword(newPsw);
						if(InstanceUtil.udi.updateInfo(u)) {
							res.put("ret", "true");
							session.setAttribute("user", u);
						}else {
							res.put("ret", "false");
							res.put("reason", "�޸�ʧ�ܣ�����������");
						}
					}else {
						res.put("ret", "false");
						res.put("reason", "�޸�ʧ�ܣ�ԭ�������");
					}

				}else {
					res.put("ret", "false");
					res.put("reason", "��δ��¼");
				}
			}else {
				res.put("ret", "false");
				res.put("reason", "��δ��¼");
			}
		}else {
			res.put("ret", "false");
			res.put("reason", "��δ��¼");
		}
		result = gson.toJson(res);
		System.out.println(result);
		out.print(result);
		out.flush();
		out.close();	
			
	}
}
