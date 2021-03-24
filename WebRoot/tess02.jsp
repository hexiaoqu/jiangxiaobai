<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <link rel="stylesheet" href="./static/layui/css/layui.css"><!--引入layui文件，-->
</head>
<body>
 <div class="layui-upload">
            <div class="layui-upload">
              <button type="button" class="layui-btn layui-btn-normal" id="test-upload-change">选择文件</button>            
              
              <button type="button" class="layui-btn" id="test-upload-change-action">开始上传</button>
    <div class="layui-upload-list">
    <img class="layui-upload-img" id="previewImg">
    <p id="demoText"></p>
  </div>
            </div>
          </div>

<div class="layui-upload">
    <button type="button" class="layui-btn" id="test1">选择图片</button>
    <button type="button" style="display:none;" class="layui-btn" id="send1">上传</button>
    <button type="button"  class="layui-btn" id="send11">上传</button>
    <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;width: 88%">
        预览图：
    <div class="layui-upload-list uploader-list" style="overflow: auto;" id="demo1"></div>
    </blockquote>
</div>
</body>
 <script src="./static/layui/layui.js"></script>  
<script>

layui.use('upload', function(){
	 var $ = layui.jquery
	 ,upload = layui.upload;
	 var files = null;

	//上传前的询问框
	 $("#send11").click(function(){
		var a =  $("#demo1").children("div").length;
		if (a==0) {
			layer.msg("请选择图片")
		} else {
			var buy = document.getElementById('send1'); 
			//判断是否存在子节点
			if(!document.body.contains(document.getElementById("dd1"))) {
			     layer.msg("图片已上传，请重新选择！")
			     return;
			}
			layer.confirm('确定上传图片吗，上传之后无法修改？', {icon: 3, title:'提示'}, function(index){
				 buy.click();
			})
		}
	 	
	 })
	upload.render({
    elem: '#test-upload-change'
    ,url: '/upload/'
    ,auto: false
    //,multiple: true
    ,bindAction: '#test-upload-change-action'
    ,done: function(res){
      console.log(res)
    }
  });
	
	 upload.render({
		    elem: '#test1'
		    ,url:  '上传接口'
		    ,multiple: true
		    ,auto:false
		    ,bindAction:"#send1"
		    ,size: 10*1024
		    , choose: function (obj) {
		    files = obj.pushFile();
		    	 obj.preview(function(index, file, result){
		    		 $('#demo1').append(
		 		            '<div  class="file-iteme">' +
//		  		            '<div class="handle"><span value="'+res.data.sel+'" class="glyphicon glyphicon-trash">X</span></div>' +
		 		            '<div class="handle" id="dd1"><span class="glyphicon glyphicon-trash">X</span></div>' +
		 	 	            '<img src="'+ result +'" alt="'+ file.name +'"  width="100" height="100" class="layui-upload-img">' +
//		  		            '<img src="'+ res.data.addImgs +'" width="100" height="100"  class="layui-upload-img">' +
		 	 	            '<div class="info">' + file.name + '</div>' +
		 		            '</div>'
		 		        );
		    		 	 $(".file-iteme .handle").click(function () {
		    		 		delete files[index]; //删除对应的文件
		    		 		$(this).parent().remove(); 
						})
		    	      });
	        }
		,done: function(res,index,upload){
				if(res.code === 0){
					var a = res.data
					a.no = 1
					layer.msg("上传成功")
					 websocket.send(JSON.stringify(a));
//		 			$("#demo1").load(location.href + " #demo1")
					delete files[index]; //delete queue
					$(".glyphicon-trash").parent().remove();
				}else{
					layer.msg("上传失败，再重新选择一遍吧！")
				}
		}
		,errror:function(){
			layer.msg("好像出问题了，刷新一下试试吧！")
		}
	 });
		});
</script>
</html>