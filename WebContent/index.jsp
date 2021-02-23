<%@ page language="java" import="utils.*,java.util.*,entity.*,java.math.BigDecimal" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%>
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
<meta charset="UTF-8">
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
		or.setName(rs.getString(2));
		or.setDishMegs(rs.getString("dish_megs"));
		or.setTimes(rs.getString("times"));
		or.setAddress(rs.getString("address"));
		or.setStates(rs.getInt("states"));
		or.setSumPrice(rs.getBigDecimal("sum_price"));
		list.add(or);
	}
	JDBCUtil.close(conn, ps);
	JDBCUtil.closeRs(rs);
%>
  

<br>

<form action="delect.jsp" method="post">
		<!-- 用于存放选择的id，然后会随表单提交给服务器处理页面 -->
		<input type="hidden" name="id">
<table border="1">
<tr>
<td><a href="add.jsp">增加</a> </td>
</tr>
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
						<%-- <a href="doRemove.jsp?id=<%=obj.getId()%>">删除</a> --%>
						<a href="update.jsp?id=<%=obj.getId()%>">修改</a> 
						<a href="delect.jsp?id=<%=obj.getId()%>">删除</a> 
					</td>
				</tr>
			<%}%>

</table>
</form>
	
</body>
</html>