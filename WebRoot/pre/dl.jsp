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
    <link rel="stylesheet" href="${ctx}/css/dl.css" type="text/css">
	<style>body {background-image:url("${ctx}/images/G6.jpg");background-size:cover;background-repeat:no-repeat;height:100%;width:100%;}
	</style>
	<script src="${ctx}/lib/jquery-3.4.1/jquery-3.4.1.min.js" charset="utf-8"></script>
<script src="${ctx}/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script src="${ctx}/lib/jq-module/jquery.particleground.min.js" charset="utf-8"></script>
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

<div class="footer">
  <span class="paddinge-5">|</span><a target="_blank" href="http://www.miitbeian.gov.cn"></a>
</div>


<script src="${ctx}/js/jquery.cookie.js" charset="utf-8"></script>
<script src="${ctx}/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
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