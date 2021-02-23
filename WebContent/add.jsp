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
<title>增加</title>
</head>
<body>
<%
	request.setCharacterEncoding("UTF-8");
	String name = request.getParameter("name");
	String dish = request.getParameter("megs");
	String times = request.getParameter("time");
	String address =request.getParameter("address");
	String states = request.getParameter("states");
	String sum = request.getParameter("price");
%>
<div align="center">
<form action="add.jsp" method="post" name="myform">
<TABLE   border="0" cellpadding="0" cellspacing="0" align="center" width="530">
  <TR>
    <TD width="107" height="36">用户名：</TD>
    <TD width="524"><INPUT name="name" type="text" maxlength="16"></TD>
  </TR>
  <tr> 
  <TD width="524">选择菜品：</TD>
  <TD width="524"><input type="checkbox" name="megs" value="小菜">小菜</TD>
  <TD width="524"><input type="checkbox" name="megs" value="肉">肉</TD>
  <TD width="524"><input type="checkbox" name="megs" value="汤">汤</TD>
  <TD width="524"><input type="checkbox" name="megs" value="米饭">米饭</TD>
  </tr>
    <TR>
    <TD width="107" height="36">送餐时间：</TD>
    <TD width="524"><INPUT name="time" type="text"></TD>
  </TR>
    <TR>
    <TD width="107" height="36">地址：</TD>
    <TD width="524"><INPUT name="address" type="text"></TD>
  </TR>
    <TR>
    <TD width="107" height="36">订单状态：</TD>
    <TD width="524">
		<INPUT name="states" type="radio"   value=0 checked>未完成&nbsp; 
	    <INPUT name="states" type="radio" value=1 class="input">完成
	</TD>
  </TR>
    <TR>
    <TD width="117" height="36">金额：</TD>
    <TD width="524"><INPUT name="price" type="text"></TD>
  </TR>
  <TR><TD colspan="2" align="center">
  <INPUT type="submit" value="提 交">
 	
  
  </TD></TR>
</TABLE>
</form>
</div>
<%		
	BigDecimal ss;
	if(sum != null){
		ss= new BigDecimal(sum);
		ss=ss.setScale(2, BigDecimal.ROUND_HALF_UP);
		List<order> list = new ArrayList();
		Connection conn = JDBCUtil.getConnection();
		String sql = "insert into `order` values(?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, null);
		ps.setString(2,name);
		ps.setString(3,dish);
		ps.setString(4, times);
		ps.setString(5, address);
		ps.setInt(6,0);
		ps.setBigDecimal(7, ss);
		ps.execute();
		JDBCUtil.close(conn, ps);
		response.sendRedirect("index.jsp");
	}else{
		out.print("输入错误！");
	}
		
		
	
%>


</body>
</html>