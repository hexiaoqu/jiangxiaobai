<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>后台管理-登陆</title>
    <%@ include file="/aaa.jsp" %>
   <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${ctx}/lib/layui-v2.5.5/css/layui.css" media="all">
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>
        html, body {width: 100%;height: 100%;overflow: hidden}
        body {background: #1E9FFF;}
        body:after {content:'';background-repeat:no-repeat;background-size:cover;-webkit-filter:blur(3px);-moz-filter:blur(3px);-o-filter:blur(3px);-ms-filter:blur(3px);filter:blur(3px);position:absolute;top:0;left:0;right:0;bottom:0;z-index:-1;}
        .layui-container {width: 100%;height: 100%;overflow: hidden}
        .admin-login-background {width:360px;height:300px;position:absolute;left:50%;top:40%;margin-left:-180px;margin-top:-100px;}
        .logo-title {text-align:center;letter-spacing:2px;padding:14px 0;}
        .logo-title h1 {color:#1E9FFF;font-size:25px;font-weight:bold;}
        .login-form {background-color: #87CEEB ;boder:1px solid #fff;border-radius:3px;padding:14px 20px;box-shadow:0 0 8px #eeeeee;}
        .login-form .layui-form-item {position:relative;}
        .login-form .layui-form-item label {position:absolute;left:1px;top:1px;width:38px;line-height:36px;text-align:center;color:#d2d2d2;}
        .login-form .layui-form-item input {padding-left:36px;}
        .captcha {width:60%;display:inline-block;}
        .captcha-img {display:inline-block;width:34%;float:righst;}
        .captcha-img img {height:34px;border:1px solid #e6e6e6;height:36px;width:100%;}
         body {background-image:url("${ctx}/images/G6.jpg");background-size:cover;background-repeat:no-repeat;height:100%;width:100%;}
        #container{height:100%;width:100%;}
        input:-webkit-autofill {-webkit-box-shadow:inset 0 0 0 1000px #fff;background-color:transparent;}
        .admin-login-background {width:300px;height:300px;position:absolute;left:50%;top:40%;margin-left:-150px;margin-top:-100px;}
        .admin-header {text-align:center;margin-bottom:20px;color:#ffffff;font-weight:bold;font-size:40px}
        .admin-input {border-top-style:none;border-right-style:solid;border-bottom-style:solid;border-left-style:solid;height:50px;width:300px;padding-bottom:0px;}
        .admin-input::-webkit-input-placeholder {color:#a78369}
        .layui-icon-username {color:#a78369 !important;}
        .layui-icon-username:hover {color:#9dadce !important;}
        .layui-icon-password {color:#a78369 !important;}
        .layui-icon-password:hover {color:#9dadce !important;}
        .admin-input-username {border-top-style:solid;border-radius:10px 10px 0 0;}
        .admin-input-verify {border-radius:0 0 10px 10px;}
        .admin-button {margin-top:20px;font-weight:bold;font-size:18px;width:300px;height:50px;border-radius:5px;background-color:#a78369;border:1px solid #d8b29f}
        .admin-icon {margin-left:260px;margin-top:10px;font-size:30px;}
        i {position:absolute;}
        .admin-captcha {position:absolute;margin-left:205px;margin-top:-40px;}
    </style>
</head>
<body>
<div class="layui-container">
    <div class="admin-login-background">
        <div class="layui-form login-form">
            <form class="layui-form" action="">
                <div class="layui-form-item logo-title">
                    <h1>*江小白*后台登录</h1>
                </div>
                <div class="layui-form-item">
                    <label class="layui-icon layui-icon-username" for="username"></label>
                    <input type="text" id="username" name="username" lay-verify="required|account" placeholder="用户名或者邮箱" autocomplete="off" class="layui-input" value="">
                </div>
                <div class="layui-form-item">
                    <label class="layui-icon layui-icon-password" for="password"></label>
                    <input type="password" id="pwd" name="password" lay-verify="required|password" placeholder="密码" autocomplete="off" class="layui-input" value="">
                </div>
                <div class="layui-form-item">
                    <label class="layui-icon layui-icon-vercode" for="captcha"></label>                  
                </div>
                <div class="layui-form-item" >
                <input  id="rember" type="checkbox" name="rember" class="layui-input" value="y" title="记住密码">
                   
                   <a name="aaa" href="${ctx}/login?action=bbb"  style="padding-left:66px">忘记密码？ </a>
                </div>
                <div class="layui-form-item">
                    <button class="layui-btn layui-btn layui-btn-normal layui-btn-fluid" lay-submit="" lay-filter="login">登 入</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="${ctx}/lib/jquery-3.4.1/jquery-3.4.1.min.js" charset="utf-8"></script>
<script src="${ctx}/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script src="${ctx}/lib/jq-module/jquery.particleground.min.js" charset="utf-8"></script>
<div class="footer">
  <span class="padding-5">|</span><a target="_blank" href="http://www.miitbeian.gov.cn"></a>
</div>


<script src="./js/jquery.cookie.js" charset="utf-8"></script>
<script src="./lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script>

    layui.use(['form','jquery'], function () {
        var $ = layui.jquery,
            form = layui.form,
            layer = layui.layer;        
        // 登录过期的时候，跳出ifram框架
        if (top.location != self.location) top.location = self.location;

   
        $(document).ready(function(){
            $('.layui-container').particleground({
                dotColor:'#7ec7fd',
                lineColor:'#7ec7fd'
            });
				
       if($.cookie("name")!=null && $.cookie("pwd")!=null){
                         	$('input[name="username"]').val($.cookie("name"));
                          	$('input[name="password"]').val($.cookie("pwd"));	
                         }           	
        });
        $('.bind-password').on('click', function () {
            if ($(this).hasClass('icon-5')) {
                $(this).removeClass('icon-5');
                $("input[name='password']").attr('type', 'password');
            } else {
                $(this).addClass('icon-5');
                $("input[name='password']").attr('type', 'text');
            }
        });

        $('.icon-nocheck').on('click', function () {
            if ($(this).hasClass('icon-check')) {
                $(this).removeClass('icon-check');
            } else {
                $(this).addClass('icon-check');
            }
        });


		
        // 进行登录操作
        form.on('submit(login)', function (data) {
            data = data.field;
            if (data.username == '') {
                layer.msg('用户名不能为空');
                return false;
            }
            
            if (data.password == '') {
                layer.msg('密码不能为空');
                return false;
            }
            if($("#rember").is(":checked")){
                var re= $('input[name="rember"]').val();
                }else{
				var re ="n";
                    }
            $.ajax({
                url:'${ctx}/login?action=index',
                type:'post',
                dataType:'json',
                data:{
                    "username":$('input[name="username"]').val(),
                    "pwd":$('input[name="password"]').val(),                  
                    "rember":re
                },               
                success:function(res){           
                    	  if(res.states == 0){   
                              window.location.href="${ctx}/Home?action=index";
                            //  $.session.set('a',"11");                                          
                      }else{
                    	  layer.msg("已登录或密码错误");
                          }                             
                },
                error:function(){
                    //用户输入与接口内容不对应，显示文字
                    layer.msg("访问失败")
                },              
            })
            return false;
        });
    });
</script>
</body>
</html>