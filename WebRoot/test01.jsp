<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Layui</title>
     <%@ include file="/aaa.jsp" %>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${ctx}/lib/layui-v2.5.5/css/layui.css" media="all">
  <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body> 
 
<div class="demoTable">
  搜索ID：
  <div class="layui-inline">
    <input class="layui-input" name="id" id="demoReload" autocomplete="off">
  </div>
  <button class="layui-btn" data-type="reload">搜索</button>
</div>
 
<table class="layui-hide" id="LAY_table_user" lay-filter="user"></table> 
               
          
<script src="${ctx}/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述 JS 路径需要改成你本地的 -->
<script>
layui.use('table', function(){
  var table = layui.table;
  
  //方法级渲染
  table.render({
    elem: '#LAY_table_user'
    ,url: '${ctx}/table?action=giftFirstTable'
    	  ,cols: [[
              {type: "checkbox", width: 60},
              {field: 'lid', width: 100, title: 'ID', sort: true},
              {field: 'lname', width: 100, title: '礼品名称'},
              {field: 'hot', width: 100, title: '是否参与活动',align: "center"},
              {field: 'lread', width: 150, title: '简单介绍'},
              {field: 'ltime', width: 200, title: '发布时间', sort: true,templet:function(d){
                  return layui.util.toDateString(d.ltime, 'yyyy年-MM月-dd日 HH:mm:ss')}
                  },
              {field: 'lprice', width: 150, title: '所需积分', sort: true},
          ]]
    ,id: 'testReload'
    	, limits: [10, 15, 20, 25, 50, 100]
         ,limit: 10
    ,page: true
    ,height: 310
  });
  var re = "1";
  var $ = layui.$, active = {
    reload: function(){
      var demoReload = $('#demoReload');
      console.log(demoReload.val());
      //执行重载
      table.reload('testReload', {
        page: {
          curr: 1 //重新从第 1 页开始
        }
        ,where: {
          key: {
         			hot:re,
        	  lname: demoReload.val()
          }
        }
      }, 'data');
    }
  };
  
  $('.demoTable .layui-btn').on('click', function(){
	 
    var type = $(this).data('type');
    active[type] ? active[type].call(this) : '';
  });
});
</script>

</body>
</html>