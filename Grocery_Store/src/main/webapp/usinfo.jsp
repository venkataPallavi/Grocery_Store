<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style >



</style>
<script
src="https://code.jquery.com/jquery-3.6.1.min.js"
integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ="
crossorigin="anonymous"></script>

<script>
	$(function(){
		$('#header').load('header.html');
		$('#Footer').load('Footer.html');

	});


</script>
</head>
<body>
<div id = "header"></div> 

<%@ page import ="java.sql.Connection" %>
<%@ page import = "java.sql.DriverManager"%>
<%@ page import ="java.sql.SQLException" %>
<%@ page import = "java.sql.ResultSet"%>
<%@ page import = "java.sql.Statement"%>


<div>
<form action="usinfo" method = "post">
User Name :<input class = "field" type="text" id="query" name="user" placeholder="Enter User Name">
 <button class = "btn1" style ="padding: 10px 30px; cursor: pointer; border-radius:30px; outline:none; background: linear-gradient(to right,#ff105f,#ffad06);">Submit</button>
</form>




</div>

<script>

function validatio()
{
//console.log(val);
	//e.preventDeafult();
	var valid = true;
	let a = document.getElementById("query").value;
	
	
	if(a=="" ){
		alert("please enter your User name");
		return false;
	}

	
	return true;
}


</script>
<div id = "Footer"></div>	

</body>
</html>