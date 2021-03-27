<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>礼品列表</title>
  <%@ include file="/aaa.jsp" %>
  <link rel="stylesheet" href="${ctx}/static/layui/css/layui.css" media="all">
   <link rel="stylesheet" href="${ctx}/css/public.css" media="all">
   <script type="text/javascript" charset="utf-8" src="${ctx}/baidu/ueditor.config.js"></script>
   <script type="text/javascript" charset="utf-8" src="${ctx}/baidu/ueditor.all.min.js"> </script>
   <script type="text/javascript" charset="utf-8" src="${ctx}/baidu/lang/zh-cn/zh-cn.js"></script>
</head>
<body>
<img alt="" src="${ctx}/images/u3413.png">
<span style="font-family:'Arial Negreta', 'Arial Normal', 'Arial';font-weight:700;font-size:18px">礼品列表</span>
<p><span style="font-family:'Arial Normal', 'Arial';font-weight:400;font-size:12px;color:#999999;">礼品管理</span></p>

<form class="layui-form" id="form1">
  <div class="layui-form-item">
    <label class="layui-form-label">礼品名称：</label>
    <div class="layui-input-inline">
      <input type="text" id="giftname" name="name" required lay-verify="required" placeholder="请输入礼品名称" autocomplete="off" class="layui-input">
       
    </div>
                   <input  id="rember" type="checkbox" name="rember" class="layui-input" value="y" title="">
      <div class="layui-form-mid layui-word-aux">是否参与促销、赠送、凑单</div>
 
  </div>
  </div>
    <div class="layui-form-item">
    <label class="layui-form-label">成本价：</label>
    <div class="layui-input-inline">
      <input type="text" id="giftprice" name="giftprice" required lay-verify="required" placeholder="请输入成本价" autocomplete="off" class="layui-input">
    </div>   
    <div class="layui-form-mid layui-word-aux">成本价只能是数值，且不能超过两位小数</div>
  </div>
    </div>
    
    <div class="layui-form-item">
    <label class="layui-form-label">兑换所需积分：</label>
    <div class="layui-input-inline">
      <input type="text" id="giftfen" name="giftfen" required lay-verify="required" placeholder="请输入积分数" autocomplete="off" class="layui-input">
    </div>
    <div class="layui-form-mid layui-word-aux">兑换所需积分只能是数字，必须大于等于0，0表示能兑换</div>
  </div>
  
   <div class="layui-form-item layui-form-text">
    <label class="layui-form-label">简单介绍:</label>
    <div class="layui-input-block">
      <textarea  id="textfile" name="desc" placeholder="简单介绍，在1至300个字符之间" class="layui-textarea"></textarea>
    </div>
  </div>
  
    <div class="layui-form-item">
        <label class="layui-form-label">详细信息：</label>
    <div class="layui-input-inline">
    <script id="editor" type="text/plain" style="width:666px;height:300px;"></script>
    </div>
</div>
  <div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" lay-submit="" lay-filter="a1" id="aaa">立即提交</button>
      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
  </div>
</form>
</body>

<script src="${ctx}/static/layui/layui.js"></script>  
<script>
var ue = UE.getEditor('editor');

layui.use(['form','jquery','upload'], function () {
    var $ = layui.jquery,
        form = layui.form,
        layer = layui.layer
    ,upload = layui.upload;    
    form.on('submit(a1)', function (data) {
        data = data.field;
    if($("#rember").is(":checked")){
        var re= $('input[name="rember"]').val();
        }else{
		var re ="n";
            }
    if(!(/^\d+(\.\d{1,2})?$/.test(data.giftprice))){
    	layer.msg('成本价小数位要位2位');
        return false;
        }   
    if(isNaN(data.giftprice)){
   	 layer.msg('成本价必须为数字');
        return false;
       }  
    if(isNaN(data.giftfen)){
   	 layer.msg('积分必须为数字');
        return false;
       }        
	if(!(/^[\s\S]{1,6}$/).test(data.giftprice)){
		layer.msg('成本价不能超过九位');
        return false;
		}
	if(!(/^[\s\S]{1,8}$/).test(data.giftfen)){
		layer.msg('积分不能超过八位');
        return false;
		}
	if(!((/^(\+|-)?\d+$/.test( data.giftfen ))|| (data.giftfen)<0)){
		 layer.msg('积分必须为正整数');
         return false;
		}
       
    var aa=$('#giftname').val();
    var bb=$("#giftprice").val();
    var cc=$("#giftfen").val();
    var dd=re;
    var ee=baiduText;
    var gg=$('#textfile').val();  
    var baiduText=ue.getContentTxt();
    var baidu=ue.getContent();
    var fd = new FormData($("#form1")[0]);
	fd.append("giftname",aa);
	fd.append("giftprice",bb);
	fd.append("giftfen",cc);
	fd.append("baiduText",baiduText);
	fd.append("re",re);
	fd.append("textfile",gg);
        $.ajax({
            url:'${ctx}/table?action=giftText',
            type:'POST',
          	contentType:false,
            processData:false,
            dataType:'json',
            data:fd               
            ,               
            success:function(res){         
                	  if(res.states == 0){  
                		  parent.layer.close(parent.layer.index);                                
                  }else{
                	    layer.msg("添加失败");
                      }                   
            },
            error:function(){
                layer.msg("访问失败")
            },              
        })
        return false;
    });


</script>


</html>