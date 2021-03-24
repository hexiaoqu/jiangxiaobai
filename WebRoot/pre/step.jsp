<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
         <%
 String path = request.getContextPath();
 String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
 %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/aaa.jsp" %>
    <meta charset="utf-8">
    <title>修改密码页面</title>
    <meta name="renderer" content="webkit">       
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${ctx}/lib/layui-v2.5.5/css/layui.css" media="all">
    <link rel="stylesheet" href="${ctx}/css/public.css" media="all">
    <link rel="stylesheet" href="${ctx}/js/lay-module/step-lay/step.css" media="all">
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">

        <div class="layui-fluid">
            <div class="layui-card">
                <div class="layui-card-body" style="padding-top: 40px;">
                    <div class="layui-carousel" id="stepForm" lay-filter="stepForm" style="margin: 0 auto;">
                    
                        <div carousel-item>                   
                            <div>
                                <form class="layui-form" style="margin: 0 auto;max-width: 460px;padding-top: 40px;">
                                     <div class="layui-form-item">
               	 							<label class="layui-form-label">验证码：</label>
               	 							<div class="layui-input-block">
                    							<input type="text" name="code" lay-verify="title" autocomplete="off" placeholder="请输入验证码" class="layui-input">
                						</div>
           				 				</div>
                               
                   					 <div class="layui-form-item">
               	 							<label class="layui-form-label">密码：</label>
               	 							<div class="layui-input-block">
                    							<input type="text" name="pwd1" lay-verify="title" autocomplete="off" placeholder="请输入第一遍密码" class="layui-input">
                						</div>
           				 				</div>
           				 				<div class="layui-form-item">
               	 							<label class="layui-form-label">再次确认密码：</label>
               	 							<div class="layui-input-block">
                    							<input type="text" name="pwd2" lay-verify="title" autocomplete="off" placeholder="请输入第二遍密码" class="layui-input">
                						</div>
           				 				</div>    
           				 				       <div style="text-align: center;margin-top: 50px;">
                                     <button class="layui-btn" lay-submit lay-filter="formStep2">
                                                &emsp;确认密码&emsp;
                                            </button>
                                </div>                                               
                                </form>
                            </div>                         
                            <div>
                                <div style="text-align: center;margin-top: 90px;">
                                    <i class="layui-icon layui-circle"
                                       style="color: white;font-size:30px;font-weight:bold;background: #52C41A;padding: 20px;line-height: 80px;">&#xe605;</i>
                                    <div style="font-size: 24px;color: #333;font-weight: 500;margin-top: 30px;">
                                       	 修改成功
                                <div style="text-align: center;margin-top: 50px;">
                                     <button class="layui-btn layui-btn-primary" id="jump">点击去登录</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div style="color: #666;margin-top: 30px;margin-bottom: 40px;padding-left: 30px;">
                       
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>



<script src="${ctx}/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script src="${ctx}/js/lay-config.js?v=1.0.4" charset="utf-8"></script>
<script src="${ctx}/js/jquerysession.js" charset="utf-8"></script>
<script>
    layui.use([ 'form', 'step'], function () {
        var $ = layui.$,
            form = layui.form,
            step = layui.step;
		//var sess= $.session.get("code");
	
        step.render({
            elem: '#stepForm',
            filter: 'stepForm',
            width: '100%', //设置容器宽度
            stepWidth: '750px',
            height: '500px',
            stepItems: [{
                title: '输入新密码'
            }, 
           {
                title: '完成'
            }]

        
        });
		//var aa =$.session.get("email");
		// aa1 =sessionStorage.getItem('email');
		//var  aa2 = localStorage.getItem('email'); 
		var aa ='${email}';
        if(aa == "" ){
        	alert("验证码过期");
			 window.location = '${ctx}';
         }
        
        form.on('submit(formStep2)', function (data) {
        	 data = data.field;
             if (data.code == ''||data.code==null) {
                 layer.msg('验证码不能为空');
                 return false;
             }
             if (data.pwd1 == ''||data.pwd1==null) {
                 layer.msg('密码1不能为空');
                 return false;
             }
             if (data.pwd2 == ''||data.pwd1==null) {
                 layer.msg('密码2不能为空');
                 return false;
             }
             if (data.pwd1 != data.pwd2) {
                 layer.msg('两次密码输入不一致');
                 return false;
             }
             var abc = window.location.hash.substring(5,10);
             $.ajax({
                 url:'${ctx}/login?action=updatepwd',
                 type:'post',
                 dataType:'json',
                 data:{
                     //监听的内容有哪些,你的登陆界面上用户输入的有哪些，就监听那些
                     "code":$('input[name="code"]').val(),
                     "pwd1":$('input[name="pwd1"]').val(),                  
                     "url":abc                                  
                 },               
                 success:function(res){  
                     if(res.states==0){                        
                    	 step.next('#stepForm');       
                         }else{
                        	 layer.msg("验证码错误或过期")
                             }             	           
                 },
                 error:function(){
                    // alert($('input[name="code"]').val());
                     layer.msg("访问失败")
                 },              
             })
             return false;
         });

		$("#jump").click(function(){
			 window.location = '/JXB/';
			});
    });
    
</script>
</body>
</html>