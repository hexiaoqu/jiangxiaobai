package com.dome.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dome.entity.User;
import com.dome.utils.EmptyUtils;





@WebFilter(filterName = "LoginFilter",value= {"/*"},asyncSupported=true,dispatcherTypes= {DispatcherType.REQUEST})
public class LoginFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest)arg0;
		HttpServletResponse resp = (HttpServletResponse)arg1;
		

		String requestURL = req.getRequestURI(); 

		if(requestURL.contains("Home")||requestURL.contains("pre")||requestURL.contains("page/")||requestURL.contains("backend/")) {
			HttpSession session = req.getSession();
			User user = (User)session.getAttribute("user");
			if(EmptyUtils.isEmpty(user)) {
				resp.sendRedirect(req.getContextPath() + "/");
			}else {
			//	System.out.println("start------------------LoginFilter");
				arg2.doFilter(req, resp);  //放行到servlet
			//	System.out.println("end-----------------LoginFilter");
				return;
			}
		}else {
			//System.out.println("start------------------LoginFilter");
			arg2.doFilter(req, resp);
			//System.out.println("end-----------------LoginFilter");
			return;
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
}
