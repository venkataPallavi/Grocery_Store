<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<style>

#it{
display :block;

}
#add{
display : none;
}
#details{
display : none;

}
#update{
display : none;

}
#reports{
display : none;

}

.bdet{
padding: 10px 30px; cursor: pointer; border-radius:30px; outline:none; background: linear-gradient(to right,#ff105f,#ffad06);
}
.bupt{
padding: 10px 30px; cursor: pointer; border-radius:30px; outline:none; background: linear-gradient(to right,#ff105f,#ffad06);

}
		.div1{
			padding: 15px;
			border: 2px solid black;
			//width: 450px;
			height: 200px;
			background-color: white;
			margin: auto;
		}
		.div2{
			padding: 5px;
			//width: 700px;
			height: 100px;
			background-color: white;
			float: left;
		}
		.div3{
			//width: 100px;
			height: 100px;
			background-color: white;
			float: right;
		}

.word {
            width:50px;
            overflow-wrap: break-word;
            word-wrap: break-word;
            word-break: break-word;
        }

.field {
  padding:10px;
  border-radius:10px;
              
  
}
 
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

.topnav {
  overflow: hidden;
  background-color: #333;
}

.topnav a {
  float: left;
  color: #333;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 17px;
}

.topnav a:hover {
  background-color: #04AA6D;
  color: black;
}

.topnav a.active {
  background-color: #04AA6D;
  color: white;
}



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
<h1>Welcome To Shopkeeper Page</h1>

<button type="submit"  class="btn3" style ="padding: 10px 30px; cursor: pointer; border-radius:30px; outline:none; background: linear-gradient(to right,#ff105f,#ffad06);" >Account details</button>

<button type="submit"  class="btn1" style ="padding: 10px 30px; cursor: pointer; border-radius:30px; outline:none; background: linear-gradient(to right,#ff105f,#ffad06);">items</button>
<button type="submit"  class="btn2" style ="padding: 10px 30px; cursor: pointer; border-radius:30px; outline:none; background: linear-gradient(to right,#ff105f,#ffad06);">create new item</button>
<button type="submit"  class="btn4" style ="padding: 10px 30px; cursor: pointer; border-radius:30px; outline:none; background: linear-gradient(to right,#ff105f,#ffad06);">Reports</button>

</div>
<div id= ser >
<form style= "float : right ;" id="form" action = "ser" method = "post" > 
  <input class = "field" type="search" id="query" name="q" placeholder="Search...">
  <button style ="padding: 10px 30px; cursor: pointer; border-radius:30px; outline:none; background: linear-gradient(to right,#ff105f,#ffad06);">Search</button><br><br>
</form>
<h2 style="text-align:center;color:red;">ITEMS</h2>


</div>
<div id = "details"> 

<h2 style="color:red;">Account Details</h2>

<%
		try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/store","pallavi","Passwd@123");){
					//HttpSession session = request.getSession();
					//String usname = session.getAttribute("user").toString();//request.getParameter("usname");
					String usname=(String)session.getAttribute("user"); 
					String query ="select Email_id , first_name , last_name , Phone_number , Address ,Pincode  from User where User_name = '" + usname +"';";

					Statement s = con.createStatement();
					ResultSet r = s.executeQuery(query);
					
					//String usname = "";
					while(r.next()){
						//if(r!=null){
					    //String usname = r.getString(2);
						String emid = r.getString(1);
				String finame = r.getString(2);
				String pho = r.getString(4);
				String address = r.getString(5);
				int pinc = r.getInt(6);
				String laname = r.getString(3);
						//String query = "";
						
						out.print("<div class = 'div1 col-md-4' style='float:left'>");
								out.print("<div class = 'div2'>");
									out.print("<span><b>Name : </b>"+usname+"</span><br>");
									out.print("<span><b>email-id : </b>"+emid+"</span><br>");
									out.print("<span><b>First Name : </b>"+finame+"</span><br>");
									out.print("<span><b>Last Name : </b>"+laname+"</span><br>");
									out.print("<span><b>Phone : </b>"+pho+"</span><br>");
									out.print("<span><b>Address  :</b>"+address+"</span><br>");
									out.print("<span><b>PinCode  :</b>"+pinc+"</span>");
									out.print("<br>");
									
								out.print("</div>");		
						out.print("</div>");
						out.print("<br>");out.print("<br>");	
					//}
				}
					//else{
						
					//	out.print("no values found");
						
					//}
					
					
					
					con.close();	
			}catch(SQLException e){
				out.println("<h1>"+e+"</h1>");
				out.print("can't connect to Database");
			}
	
	
	%>


</div>





<div id = "it">
<div id="it col-md-12" >

			<% 
				try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/store","pallavi","Passwd@123");){
					String query ="select item_code , item_name , price , stock , discount , discription , image  from Item;";
					Statement s = con.createStatement();
					ResultSet r = s.executeQuery(query);
					
					String itname="";
					while(r.next()){
						 itname = r.getString(2);
						String itcode = r.getString(1);
						String img = r.getString(7);
						float price = r.getInt(3);
						int stock = r.getInt(4);
						String dis = r.getString(6);
						float disc = r.getInt(5);
						String sc = "spanCode"+itcode;
						String sn = "spanName"+itcode;
						String sp = "spanprice"+itcode;
						String ss = "spanstock"+itcode;
						String sd = "spandiss"+itcode;
						String sd1 = "spandis"+itcode;
						String si = "spanimage"+itcode;
						//session.setAttribute("sitname", itname);
						//itname.value(
								
						out.print("<div class = 'div1 col-md-4' >");
								out.print("<div class = 'div2'>");
								
								   out.print("<span><b>ItemCode : </b><span id="+sc+">"+itcode);
								   out.print("</span></span><br>");
									out.print("<span><b>Name : </b><span id='"+sn+"'" +">"+itname);
									out.print("</span></span><br>");									
									out.print("<span><b>Price : </b><span id='"+sp+"'" +">"+price);
									out.print("</span></span><br>");
									out.print("<span><b>stock : </b><span id='"+ss+"'" +">"+stock);
									out.print("</span></span><br>");
									out.print("<span><b>Discount : </b><span id='"+sd+"'" +">"+disc);
									out.print("</span></span><br>");
									out.print("<span><b > Description  :</b><span class='word' id='"+sd1+"'" +">"+dis);
									out.print("</span></span><br>");
									
									out.print("<button  id='bupt' class='bupt' data-id='"+ itcode +"' type ='button'> Update </button >");
									out.print("<form  action = 'delit' method = 'post'>");
									out.print("<input type='label' style='display:none' name='lblItemCode' value ='"+ itcode +"'>");
									out.print(" <button class='bdet'  type='submit'>Delete</button> ");
									out.print("</form>");
									out.print("</div>");
							
								out.print("<div class='div3'>");
									
									out.print("<image  height=100px width=100px align=right border='2px solid black' src = '"+img+"' alt = 'cannot load the image'>");
									
								out.print("</div>");
								
								
						out.print("</div>");
						out.print("<br>");out.print("<br>");	
					}
					
					con.close();	
			}catch(SQLException e){
				out.println("<h1>"+e+"</h1>");
				out.print("can't connect to Database");
			}
		%>
		
</div>
</div>

<div id = "add">
<h2 style="text-align:center;color:red;">Create New Item</h2>

<form onsubmit = "validation()" method = "post" action = "addit">

Item_code : <input type="text" id ="c" class="field" name="itcode" placeholder="Item_code" required><br><br>
Item_name : <input type="text" id = "n" class="field" name="itname" placeholder="Item_name" required><br><br>
Price : <input type="number" id = "p" class="field" name="price" placeholder="price" required><br><br>
Stock : <input type="number" id = "s" class="field" name="stock" placeholder="stock" required><br><br>
Discount : <input type="number" id = "d" class="field" name="disc" placeholder="discount" required><br><br>
Description : <input type="text" id = "de" class="field" name="dis" placeholder="discription" required><br><br>
Image_url : <input type="text" id = "i" class="field" name="img" placeholder="image url" required><br><br>
<button style ="padding: 10px 30px; cursor: pointer; border-radius:30px; outline:none; background: linear-gradient(to right,#ff105f,#ffad06);" type="submit"  >save</button><br>
</form>
</div>

<div id = "update">
<h2 style="text-align:center;color:red;">Update Item</h2>
<form method = "post" action = "updit">

<input id = "txtitcode" type="text" class="field" name="itcode" placeholder="Item_code" value="" readonly><br><br>
<input id = "txtitname" type="text" class="field" name="itname" placeholder="Item_name" value="" required><br><br>
<input id = "txtitprice" type="number" class="field" name="price" placeholder="price" required><br><br>
<input id="txtitstock" type="number" class="field" name="stock" placeholder="stock" required><br><br>
<input id="txtitdiss" type="number" class="field" name="disc" placeholder="discount" required><br><br>
<input id = "txtitdis" type="text" class="field" name="dis" placeholder="discription" required><br><br>
<input id = "txtitimg" type="text" class="field" name="img" placeholder="image url" required><br><br>
<button style ="padding: 10px 30px; cursor: pointer; border-radius:30px; outline:none; background: linear-gradient(to right,#ff105f,#ffad06);" type="submit"  >save</button>
</form>
</div>

<div id = "reports">

<div class="topnav">
  <a class="active" href="http://localhost:8080/It_lab_ass1/usinfo.jsp">User info</a>
  <a href="http://localhost:8080/It_lab_ass1/date1.jsp">A day info</a>
  
  
</div>

</div>

<script>
$(document).ready(function(){
	// To display My account
	
	//String itname = (String)session.getAttribute("sitname");
	//$("itname").value(itname);
	
	
	$(".btn1").click(function(){		
    	$("#it").css("display", "block");
    	$("#ser").css("display", "block");
  		$("#add").css("display", "none");
  		$("#details").css("display", "none");
  		//$("#bdet").css("display", "none");
  		$("#update").css("display", "none");
  		$("#reports").css("display", "none");


	});
	
	$(".bdet").click(function(ele){
		let id = ele.target.attributes["data-id"];
		//session.setAttribute("itemIdTODelete", id.value);
	});

	// To display Items
	$(".btn2").click(function(){
    	
  		$("#it").css("display", "none");
  		$("#ser").css("display", "none");
  		$("#add").css("display", "block");
  		$("#details").css("display", "none");
  		//$("#bdet").css("display", "none");
  		$("#update").css("display", "none");
  		$("#reports").css("display", "none");


	});
	
	$(".btn3").click(function(){
    	
  		$("#it").css("display", "none");
  		$("#add").css("display", "none");
  		$("#ser").css("display", "none");
  		$("#details").css("display", "block");
  		//$("#bdet").css("display", "none");
  		$("#update").css("display", "none");
  		$("#reports").css("display", "none");


	});
	
	$(".bupt").click(function(){
    	
  		$("#it").css("display", "none");
  		$("#add").css("display", "none");
  		$("#ser").css("display", "none");
  		$("#details").css("display", "none");
  		$("#update").css("display", "block");
  		//$("#bdet").css("display", "none");
  		$("#reports").css("display", "none");


	});
	
$(".btn4").click(function(){
    	
  		$("#it").css("display", "none");
  		$("#add").css("display", "none");
  		$("#ser").css("display", "none");
  		$("#details").css("display", "none");
  		$("#update").css("display", "none");
  		//$("#bdet").css("display", "none");
  		$("#reports").css("display", "block");

	});
	

	
	$(".bupt").click(function(ele){
    	let id = ele.target.attributes["data-id"];
		//alert($("#spanName").text());
		$("#txtitname").val($("#spanName"+id.value).text());
		$("#txtitcode").val($("#spanCode"+id.value).text());
		$("#txtitprice").val($("#spanprice"+id.value).text());
		$("#txtitstock").val($("#spanstock"+id.value).text());
		$("#txtitdiss").val($("#spandiss"+id.value).text());
		$("#txtitdis").val($("#spandis"+id.value).text());
		$("#txtitimg").val($("#spanimg"+id.value).text());
	});
	
	
});

function validation()
{
//console.log(val);
	//e.preventDeafult();
	var valid = true;
	let a = document.getElementById("c").value;
	let b= document.getElementById("n").value;
	let c = document.getElementById("p").value;
	let d= document.getElementById("s").value;
	let e = document.getElementById("d").value;
	let f = document.getElementById("de").value;
	let g= document.getElementById("i").value;
	
	
	
	if(a=="" ){
		alert("please enter your Item code");
		return false;
	}

	if(b==""){
		alert("please enter your Item name");
		return false;
	}	

	if(c==""){
		alert("please enter your price location");
		return false;
	}
	if(d=="" ){
		alert("please enter your Stock");
		return false;
	}

	if(e==""){
		alert("please enter your Disscount");
		return false;
	}	
	if(f=="" ){
		alert("please enter your descreption");
		return false;
	}

	if(g==""){
		alert("please enter your image_url");
		return false;
	}	

	
	
	if(isNaN(c)){
			alert("Price should be numbers");
			return false;
	}
	if(isNaN(s)){
		alert("Price should be numbers");
		return false;
}
	if(isNaN(d)){
		alert("Price should be numbers");
		return false;
}
	return true;
}



</script>
<div id = "Footer"></div>	

</body>
</html>