<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>gifttable</title>
     <%@ include file="/aaa.jsp" %>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${ctx}/lib/layui-v2.5.5/css/layui.css" media="all">
    <link rel="stylesheet" href="${ctx}/css/public.css" media="all">
</head>
<body>
<img alt="" src="${ctx}/images/u3413.png">
<span style="font-family:'Arial Negreta', 'Arial Normal', 'Arial';font-weight:700;font-size:18px">礼品列表</span>
<p><span style="font-family:'Arial Normal', 'Arial';font-weight:400;font-size:12px;color:#999999;">礼品管理</span></p>

<div class="layuimini-container">
    <div class="layuimini-main">

        <fieldset class="table-search-fieldset">
            <legend>搜索信息</legend>
            <div style="margin: 10px 10px 10px 10px">
                <form class="layui-form layui-form-pane" action="">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">礼品名称</label>
                            <div class="layui-input-inline">
                                <input type="text" id="lname" name="lname" autocomplete="off" class="layui-input">
                            </div>
                        </div>               
                        <div class="layui-inline">
                            <div class="layui-input-inline">
  <input  id="hot" type="checkbox" name="hot" class="layui-input" value="1" title="是否参与活动">
                            </div>
                        </div>
                        
                        <div class="layui-inline">
                            <button type="submit" class="layui-btn layui-btn-primary"  lay-submit lay-filter="data-search-btn"><i class="layui-icon"></i> 搜 索</button>
                        </div>
                    </div>
                </form>
            </div>
        </fieldset>

        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add"> 添加 </button>
                <button class="layui-btn layui-btn-sm layui-btn-danger data-delete-btn" lay-event="delete"> 删除 </button>
            </div>
        </script>

        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>
		 <script type="text/html" id="aaa">
          <img alt="" src="${ctx}/upload/images/20210326/20210326171344_994.jpg">
        </script>		
        <script type="text/html" id="currentTableBar">
            <a class="layui-btn layui-btn-normal layui-btn-xs data-count-edit" lay-event="edit">编辑</a>
            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete">删除</a>
        </script>

    </div>
</div>
<script src="${ctx}/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script>




    layui.use(['form', 'table'], function () {
        var $ = layui.jquery,
            form = layui.form,
            table = layui.table;
		var test;
       var tableIns = table.render({
            elem: '#currentTableId',
            url: '${ctx}/table?action=giftFirstTable',
            toolbar: '#toolbarDemo',
            defaultToolbar: ['filter', 'exports', 'print', {
                title: '提示',
                layEvent: 'LAYTABLE_TIPS',
                icon: 'layui-icon-tips'
            }],
            cols: [[
                {type: "checkbox", width: 60},
                {field: 'lid', width: 100, title: 'ID', sort: true},
                {field: 'lname', width: 100, title: '礼品名称'},
                {field: 'hot', width: 150, title: '是否参与活动',align: "center"},
                {field: 'lread', width: 150, title: '简单介绍'},
                {field: 'ltime', width: 250, title: '发布时间', sort: true,templet:function(d){
                    return layui.util.toDateString(d.ltime, 'yyyy年-MM月-dd日 HH:mm:ss')}
                    },
                {field: 'lprice', width: 200, title: '所需积分', sort: true},
                {title: '操作', minWidth: 120, toolbar: '#aaa', align: "center"},
                {title: '操作', minWidth: 100, toolbar: '#currentTableBar', align: "center"}
            ]],
            limits: [10, 15, 20, 25, 50, 100],
            limit: 15,
            id:'idTest',
            page: true,
            skin: 'line'
        });

    // 监听搜索操作
       form.on('submit(data-search-btn)', function () {
           var lname = $("#lname").val();
           if($("#hot").is(":checked")){
               var re= $('#hot').val();
               }else{
				var re ="0";
                   }
           if(lname.replace(/\s*/g, "") ==''){
           	  layer.msg("清输入要搜索的礼品名称");
           	  return false;
               }            
           //执行搜索重载           
           tableIns.reload('idTest', {
               url:'${ctx}/table?action=likeGift',
               page: {
                   curr: 1
               }
               , where: {
                   lname:lname,
               		hot:re
               }
           });

           return false;
       });


      
        /**
         * toolbar监听事件
         */
        table.on('toolbar(currentTableFilter)', function (obj) {
            if (obj.event === 'add') {  // 监听添加操作
                var index = layer.open({
                    title: '添加用户',
                    type: 2,
                    shade: 0.2,
                    maxmin:true,
                    shadeClose: true,
                    area: ['100%', '100%'],
                    content: '${ctx}/table?action=giftAdd',
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
            } else if (obj.event === 'delete') {  // 监听删除操作
                var checkStatus = table.checkStatus('currentTableId')
                    , data = checkStatus.data;
				if(checkStatus.data.length==0){
					parent.layer.msg('请先选择要删除的数据行！', {icon: 2});
					return ;
					}
				var ids = "";
				for(var i=0;i<checkStatus.data.length;i++){
					ids += checkStatus.data[i].lid+",";
				}
				parent.layer.msg('删除中...', {icon: 16,shade: 0.3,time:1000});
				 $.ajax({
                     url:'${ctx}/table?action=giftDeletes',
                     type:'post',
                     data:{'ids':ids},//向服务端发送删除的id
                     success:function(res){
                         if(res){
                        	 parent.layer.msg('删除成功！', {icon: 1,time:2000,shade:0.2});
         			        location.reload(true);      
                             }
                     },
                     error:function(){
                         //用户输入与接口内容不对应，显示文字
                         layer.msg("删除失败")
                     },
                 });
               // layer.alert(JSON.stringify(data));
            }
        });

        //监听表格复选框选择
        table.on('checkbox(currentTableFilter)', function (obj) {
            console.log(obj)
        });

        table.on('tool(currentTableFilter)', function (obj) {
            var data = obj.data;
            if (obj.event === 'edit') {
			var lid;
                var index = layer.open({
                    title: '编辑用户',
                    type: 2,
                    shade: 0.2,
                    maxmin:true,
                    shadeClose: true,
                    area: ['100%', '100%'],
                    content: '${ctx}/table?action=giftupdateList',
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
                return false;
            } else if (obj.event === 'delete') {
                layer.confirm('真的删除行么', function (index) {
					 $.ajax({
                        url:'${ctx}/table?action=giftDelete',
                        type:'post',
                        data:{'id':data.lid},//向服务端发送删除的id
                        success:function(res){
                            if(res){
                                obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                                layer.close(index);
                                console.log(index);
                                layer.msg("删除成功",{icon:1});
                            }
                            else{
                                layer.msg("删除失败",{icon:5});
                            }
                        }
                    });
                });
            }
        });

    });
</script>

</body>
</html>