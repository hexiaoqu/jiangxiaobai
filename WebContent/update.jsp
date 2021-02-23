<%@ page language="java" import="utils.*,java.util.*,entity.*,java.math.BigDecimal" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
       <%@page import="java.sql.Connection"%>
    <%@page import="java.sql.PreparedStatement"%>
    <%@page import="java.sql.ResultSet"%>
        <%
    String path =request.getContextPath();
    String basePath= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
  	request.setCharacterEncoding("UTF-8");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>修改</title>
</head>
<body>
<%	
	String id = request.getParameter("id");
	List<order> list = new ArrayList();
	String sql ="select * from `order` where id =?";
	Connection conn = JDBCUtil.getConnection();
	PreparedStatement ps = conn.prepareStatement(sql);	
	ps.setString(1, id);
	ResultSet rs= ps.executeQuery();
	while(rs.next()){
		order or = new order();
		or.setId(rs.getInt("id"));
		or.setName(rs.getString(2));
		or.setDishMegs(rs.getString("dish_megs"));
		or.setTimes(rs.getString("times"));
		or.setAddress(rs.getString("address"));
		or.setStates(rs.getInt("states"));
		or.setSumPrice(rs.getBigDecimal("sum_price"));
		
		list.add(or);
	}
	
	
%>		

<form action="update.jsp" method="post">
		<input type="hidden" name="id">
<table border="1">
<tr>
	<th>编号</th>
	<th>姓名</th>
	<th>菜单内容</th>
	<th>时间</th>
	<th>地址</th>
	<th>订单状态</th>
	<th>价格</th>
</tr>		
		<%	
		for(int i=0;i<list.size();i++){
				order obj=(order)list.get(i);%>
				<tr>
					<td><%=obj.getId()%></td>
					<td><%=obj.getName()%></td>
					<td><%=obj.getDishMegs()%></td>					
					<td><%=obj.getTimes()%></td>
					<td><%=obj.getAddress()%></td>
					<td><%=obj.getStates()%></td>
					<td><%=obj.getSumPrice()%></td>
				</tr>
				<tr>
	<th><input type="text" name="n1" value=<%=obj.getId()%>></th>
	<th><input type="text" name="n2" value=<%=obj.getName()%>></th>
	<th><input type="text" name="n3" value=<%=obj.getDishMegs()%>></th>
	<th><input type="text" name="n4" value=<%=obj.getTimes()%>></th>
	<th><input type="text" name="n5" value=<%=obj.getAddress()%>></th>
	<th><input type="text" name="n6" value=<%=obj.getStates()%>></th>
	<th><input type="text" name="n7" value=<%=obj.getSumPrice()%>></th>
</tr>
			<%}%>
	
</table>
<%
	String  a1= request.getParameter("n1");
	String  a2= request.getParameter("n2");
	String  a3= request.getParameter("n3");
	String  a4= request.getParameter("n4");
	String  a5= request.getParameter("n5");
	String  a6= request.getParameter("n6");
	String  a7= request.getParameter("n7");
%>
<input type="submit" value="修改">
<%
	String sqq = "update `order` set name=?,dish_megs=?,times=?,address=?,states=?,sum_price=? where id=?";
	Connection con= JDBCUtil.getConnection();
	PreparedStatement pps = conn.prepareStatement(sqq);
	pps.setString(1, a2);
	pps.setString(2, a3);
	pps.setString(3, a4);
	pps.setString(4, a5);
	pps.setString(5, a6);
	pps.setString(6, a7);
	pps.setString(7, id);
	pps.executeUpdate();
	JDBCUtil.close(con, pps);
	JDBCUtil.close(conn, ps);
	JDBCUtil.closeRs(rs);
%>
</form>
</body>
</html>