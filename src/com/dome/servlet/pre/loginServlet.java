package com.dome.servlet.pre;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dome.dao.user.UserDao;
import com.dome.dao.user.UserDaoImpl;
import com.dome.entity.User;
import com.dome.param.UserParams;
import com.dome.service.user.UserService;
import com.dome.service.user.Impl.UserServiceImpl;
import com.dome.servlet.AbstractServlet;
import com.dome.utils.PrintUtil;
import com.dome.utils.ReturnResult;

@WebServlet(urlPatterns = {"/login"}, name = "login")
public class loginServlet extends AbstractServlet{

	private static final long serialVersionUID = 1L;
	
	private UserService userService;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		userService = new UserServiceImpl();		
	}
		
	@Override
	public Class getServletClass() {	
		return loginServlet.class;
	}
	
	public int index(HttpServletRequest request,HttpServletResponse response) throws Exception{
			String name = request.getParameter("username");
			String pwd = request.getParameter("pwd");
			String rember = request.getParameter("rember");
			User user= null;
			user = userService.FinderUser(name, pwd);
			UserParams up = new UserParams();			
			int sss=0;
			if(user!= null) {
				request.getSession().setMaxInactiveInterval(10*60);
				request.getSession().setAttribute("user", user.getuName());
				 ReturnResult result = new ReturnResult();
				 sss=1;
				if("y".equals(rember)) {
					up.setRem(0);
					Cookie cookie1= new Cookie("name",name);
					Cookie cookie2= new Cookie("pwd",pwd);					
					cookie1.setMaxAge(1*60);
					cookie2.setMaxAge(1*60);					
					response.addCookie(cookie1);
					response.addCookie(cookie2);					
				}
				System.out.println(user.getuName());
				System.out.println(user.getuPwd());
					up.setStates(0);					
					up.setUser(user);
				  PrintUtil.write(up, response);				  
			}else {
				System.out.println("404");
			}	
			return sss;

	}
	
}
