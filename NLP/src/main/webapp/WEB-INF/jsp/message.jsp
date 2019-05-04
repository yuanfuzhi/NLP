<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<meta name="description" content="this is my page">
<meta name="content-type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script>
function DispayPrice()
    {
        var nSel = document.getElementById("selected");
        var index = nSel.selectedIndex; // 选中索引
        var msgkey= nSel.options[index].value;
        var msgvalue = nSel.options[index].text; // 选中文本		
		var datapar = {
				"key" : msgkey,
				"value" : msgvalue
			};
        
		var options = {
				url : "/NLP/GetResult",
				type : "get",
				data : datapar,
				success : function(data) {
					//alert(data);				
					$("#revalue").text(data)
				}
			};
			$.ajax(options);					
    }
</script>

<title>文件上传结果</title>
</head>
<body>
    <center>
        <h2>${message}</h2>
    </center>
    PDF的内容为：
    <div style="height:300px;overflow:auto">
    ${value}
    </div>
    请选择引用：
	<select id="selected" onChange="DispayPrice();">
        <c:forEach items="${reference}" var="entry">            
            <option value="${entry.key}" selected="selected">
                ${entry.value}
            </option>                        
        </c:forEach>
    </select>
    对应的自动摘要为：<br>
	<span id="revalue"></span>

</body>
</html>