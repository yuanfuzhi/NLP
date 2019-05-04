<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<title>上传文件</title>
<style>
	.title{
	text-align: center;
	color: transparent;	
	-webkit-text-stroke: 1px black;
	letter-spacing: 0.04em;
	}
	
	  .iframe_page{
        margin: 0 auto;
        width: 600px;
        height: 400px;
        background-color: #dfdfdf;
    }
    .iframe_page #ifyulan{
        width: 600px;
        height: 400px;
        background-color: #adb9cd;
        border: 3px dashed #cc9797;
    }
    .divcss{
    text-align:center;
}
    .div1css {
    display: inline-block;
    width: 100px;
}

    
</style>

<script type="text/javascript">
$(document).ready(function () {
	 $("#addFile").change(function () {
	        showyulan();
	    });
	 $("#deleteFile").on('click',function(){
		 var obj = document.getElementById('addFile') ; 
		 obj.outerHTML=obj.outerHTML; 
		});	
	 
	 
});

function showyulan(){
	 var file = $('#addFile').get(0).files[0];//获得传至input的文件
	$('#previewFile').css("display","inline");//显示预览按钮
	$('#deleteFile').css("display","inline");//显示预览按钮
	$('#submitFile').css("display","inline");//显示预览按钮
	var reader = new FileReader();//文件读取器
	reader.readAsDataURL(file);//获取文件URL,结果存至reader.result
	reader.onload = function(){
		$("#previewFile").on('click',function(){
			$('#divyulan').css("display","block");
			$("#ifyulan").attr("src",reader.result);
		});		
	}; 
	
} 		
</script>
</head>
<body>
<h1 class = "title">基于引用句子语义扩展的抽取式科技文献自动摘要系统</h1>
<form method="post" action="/NLP/Upload" enctype="multipart/form-data">
<div class ="divcss">
 选择一个PDF文件:
 <input id="addFile" type="file" name="uploadFile" accept="application/pdf"/>
</div>
<div class ="divcss">
<div class ="div1css"><input type="button" id="previewFile" style="display:none" value="预览" /></div>
<div class ="div1css"><input type="button" id="deleteFile"  style="display:none" value="删除" /></div>
<div class ="div1css"><input type="submit" id="submitFile"  style="display:none" value="上传" /></div>
<div class="iframe_page" id= "divyulan" style="display:none">
    <iframe id="ifyulan" frameborder="0" scrolling="auto" align="middle"></iframe>
</div>
</div> 
</form>
</body>
</html>