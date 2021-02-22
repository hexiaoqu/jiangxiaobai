<%@ page language="java" import="utils.*,java.util.*,entity.*,java.math.BigDecimal" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="java.sql.Connection"%>
    <%@page import="java.sql.PreparedStatement"%>
    <%@page import="java.sql.ResultSet"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>首页</title>
</head>
<body>
<%
	List<order> list = new ArrayList();
	Connection conn= JDBCUtil.getConnection();
	String sql ="select * from `order`";
	PreparedStatement ps = conn.prepareStatement(sql);
	ResultSet rs= ps.executeQuery();
	while(rs.next()){
		order or = new order();
		or.setId(rs.getInt("id"));
		or.setName(rs.getString("name"));
		or.setDishMegs(rs.getString("dish_megs"));
		or.setTimes(rs.getString("times"));
		or.setAddress(rs.getString("states"));
		or.setStates(rs.getInt("states"));
		or.setSumPrice(rs.getBigDecimal("sum_price"));
		list.add(or);
	}
%>
  
<a href="add.jsp">添加订单</a><br>
<form action="delect.jsp" method="post">
		<!-- 用于存放选择的id，然后会随表单提交给服务器处理页面 -->
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
	<th>操作</th>
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
					<td>
						<%-- <a href="doRemove.jsp?id=<%=obj.getId()%>">删除</a> --%>、
						<a href="update.jsp?id=<%=obj.getId()%>">修改</a> 
						<input type="button" value="删除" οnclick="myAction('<%=obj.getId()%>')"/>
					</td>
				</tr>
			<%}%>

</table>
</form>
	
</body>
</html>