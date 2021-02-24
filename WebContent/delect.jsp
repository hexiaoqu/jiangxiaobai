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
<title>删除</title>

<INPUT type="button" value="返回" onclick="location='index.jsp'">
</head>
<body>

<%
int id=Integer.parseInt(request.getParameter("id"));
Connection conn=JDBCUtil.getConnection();
String sql="delete from `order` where id=?";
PreparedStatement pstmt=conn.prepareStatement(sql);
pstmt.setObject(1, id);
pstmt.executeUpdate();
response.sendRedirect("index.jsp");
%>

</body>
</html>