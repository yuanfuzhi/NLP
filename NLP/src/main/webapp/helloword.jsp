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
		xmlhttp.open("GET","static/cd_catalog.xml",false);
		xmlhttp.send();
		xmlDoc=xmlhttp.responseXML; 
		x=xmlDoc.getElementsByTagName("CD");
		
		function displayCDInfo(i)
		{
		artist=(x[i].getElementsByTagName("ARTIST")[0].childNodes[0].nodeValue);
		title=(x[i].getElementsByTagName("TITLE")[0].childNodes[0].nodeValue);
		year=(x[i].getElementsByTagName("YEAR")[0].childNodes[0].nodeValue);
		country=(x[i].getElementsByTagName("COUNTRY")[0].childNodes[0].nodeValue);
		company=(x[i].getElementsByTagName("COMPANY")[0].childNodes[0].nodeValue);
		price=(x[i].getElementsByTagName("PRICE")[0].childNodes[0].nodeValue);
		txt="Artist: "+artist+"<br />Title: "+title+"<br />Year: "+year+"<br />Country: "+country+"<br />Company: "+company+"<br />Price: "+price  ;
		document.getElementById("showCD").innerHTML=txt;
		}
		</script>
</head>

<body>
<div id='showCD'>Click on a CD to display album information.</div><br>
	<script>
		document.write("<table border='1'>");
		for (var i=0;i<x.length;i++)
		  { 
		  document.write("<tr onclick='displayCDInfo(" + i + ")'>");
		  document.write("<td>");
		  document.write(x[i].getElementsByTagName("ARTIST")[0].childNodes[0].nodeValue);
		  document.write("</td><td>");
		  document.write(x[i].getElementsByTagName("TITLE")[0].childNodes[0].nodeValue);
		  document.write("</td></tr>");
		  }
		document.write("</table>");
	</script>

</body>
</html>