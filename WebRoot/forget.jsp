<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
 <%
 String path = request.getContextPath();
 String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登入</title>
    <link rel="stylesheet" href="<%=path%>/static/layui/css/layui.css"><!--引入layui文件，-->
</head>
<body>
    <div class="login bg-black " style="margin-left: 40%;margin-top:200px;" >
    <h2 style="margin-left:150px">登入界面</h2>
        <form class="layui-form bg-blue" action="">                    
            <div class="layui-form-item margin-top-10">
                <label class="layui-form-label">用户名  
                    <i class="layui-icon">&#xe66f;</i>        
                </label>
                <div class="layui-input-inline">
                    <input type="username" id="uname" name="username"  required lay-verify="username" placeholder="请输入密码" autocomplete="off" class="layui-input" lay-verType="msg">      
                </div>
           </div>  
             <div class="layui-form-item">
                <label class="layui-form-label">密码
                    <i class="layui-icon"> &#xe672;</i>     
                </label>
                <div class="layui-input-inline">             
                <input type="password" id=""upwd name="pwd"  required lay-verify="userpassword" placeholder="请输入密码" autocomplete="off" class="layui-input">
                </div>
                             
            <div class="layui-form-item">
                <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="login">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </form>
</div>
   <script src="./static/layui/layui.js"></script>  <!--//--引入layui中的js文件 -->
    <script> 
  
    </script>  
</body>
</html>