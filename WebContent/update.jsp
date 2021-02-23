<%@ page language="java" import="utils.*,java.util.*,entity.*,java.math.BigDecimal" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
       <%@page import="java.sql.Connection"%>
    <%@page import="java.sql.PreparedStatement"%>
    <%@page import="java.sql.ResultSet"%>
        <%
    String path =request.getContextPath();
    String basePath= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>修改</title>
</head>
<body>
	取相对路径：<%=path %><br>
	取基础绝对路径<%=basePath %><br>
</body>
</html>