<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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


<div>
<form action="date1" method = "post">

<input type="checkbox" id="vehicle1" name="1mon">
<label for="vehicle1"> During last month</label><br>
<h3>OR</h3>
<input type="checkbox" id="vehicle1" name="2mon" >
<label for="vehicle1"> During last 2months</label><br>
<h3>OR</h3>
From Date :<input class = "field" type="date" id="query1" name="from" placeholder="Enter a Date">
To Date :<input class = "field" type="date" id="query2" name="to" placeholder="Enter a Date">
 
 <button class = "btn1" style ="padding: 10px 30px; cursor: pointer; border-radius:30px; outline:none; background: linear-gradient(to right,#ff105f,#ffad06);">Submit</button>
</form>

</div>

<script>

function validatio()
{
//console.log(val);
	//e.preventDeafult();
	var valid = true;
	let a = document.getElementById("query1").value;
	let b = document.getElementById("query2").value;

	
	
	if(a=="" ){
		alert("please enter From date");
		return false;
	}
	
	if(b=="" ){
		alert("please enter To date");
		return false;
	}
	
	return true;
}


</script>
<div id = "Footer"></div>	

</body>
</html>