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
<title>忘记密码</title>
    <link rel="stylesheet" href="<%=path%>/static/layui/css/layui.css"><!--引入layui文件，-->
</head>
  <style>
      
         body {background-image:url("./images/bg.jpg");height:100%;width:100%;}
        #container{height:100%;width:100%;}
        input:-webkit-autofill {-webkit-box-shadow:inset 0 0 0 1000px #fff;background-color:transparent;}

    </style>
<body>
    <div class="login bg-black " style="margin-left: 40%;margin-top:200px;" >
    <div class="layui-form-item logo-title">
                    <h1>*江小白*忘记密码</h1>
                </div>
        <form class="layui-form bg-blue" action="">                    
            <div class="layui-form-item margin-top-10">
                <label class="layui-form-label">用户名  
                    <i class="layui-icon">&#xe66f;</i>        
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="uname" name="username" value="" required lay-verify="username" placeholder="请输入用户名" autocomplete="off" class="layui-input" lay-verType="msg">      
                </div>
           </div>  
             <div class="layui-form-item">
                <label class="layui-form-label">邮箱
                    <i class="layui-icon"> &#xe672;</i>     
                </label>
                <div class="layui-input-inline">    
                <input type="text" name="email" lay-verify="email" autocomplete="off" class="layui-input" placeholder="请输入邮箱">         
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
    //Demo 拿到你所需要用的layui已经封装好的代码
    layui.use(['jquery','form','layer'], function(){
        var $ =layui.jquery;
        var form = layui.form;//表单验证
        var layer= layui.layer;
    //表单验证（页面自动关联,验证结果绑定在表单）
     form.verify({
        username: function(value, item){ //value：表单的值、item：表单的DOM对象 自定义验证规则          
            if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
                return '用户名不能有特殊字符';
            }
            if(/^\d+\d+\d$/.test(value)){
                return '用户名不能全为数字';
            }           
        } ,  
    });      
    //不想提交，可以监听，使用form.on
    form.on('submit(login)', function(data){
        //用ajax时需要注意你的url接口、采用哪一种方式type获取，它的使用的哪种数据类型datatype
        $.ajax({
            url:'<%=basePath%>loginServlet',
            type:'post',
            dataType:'json',
            data:{
                //监听的内容有哪些,你的登陆界面上用户输入的有哪些，就监听那些
                "username":$('input[name="username"]').val(),
                "email":$('input[name="email"]').val(),
            },
                    success:function(res){            
                if(res.status == 0){
                //res.code == 0,表示用户输入成功，1表示失败            
                        window.location.href="<%=basePath%>dl.jsp";  
                }else{
                //不等于0时填出的内容
                	layer.msg("1111")
                }
            },
            error:function(){
                //用户输入与接口内容不对应，显示文字
                layer.msg("访问失败")
            },
          
        })
		return false;//不会跳转到网址栏，只会跳转到你要的界面 一定要写。
    });     
  });
    </script>  
</body>
</html>