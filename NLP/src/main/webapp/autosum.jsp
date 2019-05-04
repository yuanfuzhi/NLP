<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<title>Insert title here</title>
		<script type="text/javascript">
		if (window.XMLHttpRequest)
		  {// code for IE7+, Firefox, Chrome, Opera, Safari
		  xmlhttp=new XMLHttpRequest();
		  }
		else
		  {// code for IE6, IE5
		  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		  }
		xmlhttp.open("GET","static/paper.xml",false);
		xmlhttp.send();
		xmlDoc=xmlhttp.responseXML; 
		x=xmlDoc.getElementsByTagName("p");
		y=xmlDoc.getElementsByTagName("r");
		</script>
</head>

<body>
<div id='value'>
		
</div>
<script type="text/javascript">
	var map=new Map();
	function settitle(query,reference,id){	
	var datapar = {
	"query" : query,
	"reference" :reference	
	};
	var options = {
			url : "/NLP/GetTitle",
			type : "get",
			data : datapar,
			async: true,
			success : function(data) {
				//alert(data);	
				map.set(id,data);
			}
		};
	$.ajax(options);
	}
	
	function gettitle(element){
		element.title=map.get(element.id);
	}

	for (var i=0;i<x.length;i++){
	var context=x[i].childNodes[0].nodeValue;
	var text;
	if(x[i].getAttribute("type")=="txt"){	
		document.write(context+"</br>");
	}else{
		var id=x[i].getAttribute("id");
		for(var j=0;j<y.length;j++){
			if(y[j].getAttribute("id")==id){
				settitle(context,y[j].childNodes[0].nodeValue,id);
			}
		}
		document.write("<a href='javascript:void(0)' id="+id+" onmouseover='gettitle(this)' target='_self' title=''>"+context+"</a></br>");
	}
	console.log(map);
	}
</script>
</body>
</html>