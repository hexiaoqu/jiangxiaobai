package com.dome.servlet.pre;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dome.servlet.AbstractServlet;


@WebServlet(urlPatterns = {"/Home"}, name="Home")
public class HomeServlet extends AbstractServlet{

	@Override
	public Class getServletClass() {
		// TODO Auto-generated method stub
		return HomeServlet.class;
	}
	
	public String index(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		
		return "pre/index";
	}
	

}
