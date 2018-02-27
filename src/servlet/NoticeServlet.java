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

import model.Notice;
import model.User;
import utils.InstanceUtil;
import utils.JsonUtil;

public class NoticeServlet extends HttpServlet{
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
				if("count".equals(type)) {
					int count = InstanceUtil.ndi.getUnread(user.getId()).length;
					res.put("ret", "true");
					res.put("data",""+count);
				}else if("unread".equals(type)) {
					Notice[] arr = InstanceUtil.ndi.getUnread(user.getId());
					InstanceUtil.ndi.read(arr);
					res.put("ret", "true");
					res.put("data", arr);
				}else if("all".equals(type)) {
					Notice[] arr = InstanceUtil.ndi.getAll(user.getId());
					res.put("ret", "true");
					res.put("data", arr);
				}else {
					res.put("ret", "false");
					res.put("reason", "´íÎóÇëÇó");
				}
			}else {
				res.put("ret", "false");
				res.put("reason", "ÄúÎ´µÇÂ¼");
			}
		}else {
			res.put("ret", "false");
			res.put("reason", "ÄúÎ´µÇÂ¼");
		}
		result = gson.toJson(res);
		System.out.println(result);
		out.print(result);
		out.flush();
		out.close();	
	}

}
