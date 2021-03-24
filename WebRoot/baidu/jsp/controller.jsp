<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.baidu.ueditor.ActionEnter"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%

    request.setCharacterEncoding( "utf-8" );
	response.setHeader("Content-Type" , "text/html");
	String savePath="D:/githob/jiangxiaobai/WebRoot/";
	String rootPath = application.getRealPath( "/" );
	System.out.println(rootPath);
	out.write( new ActionEnter( request, savePath ).exec() );
	
%>