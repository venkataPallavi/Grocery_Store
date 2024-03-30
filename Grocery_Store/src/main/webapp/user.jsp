<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<script>
$(".bupt").click(function(ele){
	let id = ele.target.attributes["data-id"];
	//session.setAttribute("itemIdTODelete", id.value);
	$("#lblItemCode").val(id);
});
</script>
<style>
.bupt{

padding: 10px 30px; cursor: pointer; border-radius:30px; outline:none; background: linear-gradient(to right,#ff105f,#ffad06);
transition: .2s opacity;
}
.div1{
			padding: 15px;
			border: 2px solid black;
			//width: 95%;
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

.shopping-container{
 display:flex
}

.counter-container svg{
	width : 3rem;
	colour: red;
}

.counter-container span{
	user-select : none;
	positon : absolute;
	background : red;
	width : 1.3rem;
	height : 1.3rem;
	font-size:.8rem;
	border-radius:50%;
	font-weight: bold;
	display : flex;
	align-item : center;
	justify-content: center;
	top:.2rem;
	right: .5rem;
}



<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

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


<div class="shopping-container">
	<div class="counter-container">
	
		
		<form action = "Cart1" method = "post">
		<button style="font-size:20px"  >cart <i class="fa fa-shopping-bag"></i></button>
		</form>
	</div>


</div>

<form style= "float : right ;" id="form" action = "ser" method = "post" > 
  <input class = "field" type="search" id="query" name="q" placeholder="Search...">
  <button style ="padding: 10px 30px; cursor: pointer; border-radius:30px; outline:none; background: linear-gradient(to right,#ff105f,#ffad06);">Search</button>
</form>
<form action = "display" method = post>
 <label for="items">Items display:</label>
 
<select name="items" id="display">
  <option selected value="10">10</option>
  <option value="5">5</option>
  <option value="3">3</option>
  <option value="15">15</option>
  <option value="1">1</option>
</select> 

 <button style ="padding: 10px 30px; cursor: pointer; border-radius:30px; outline:none; background: linear-gradient(to right,#ff105f,#ffad06);">get items</button>

</form>

<div id = "it">

<h2 style="text-align:center;color:red;">ITEMS</h2>

<div id="it col-md-12" >


			<% 
				try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/store","pallavi","Passwd@123");){
					
					String query ="select item_code , item_name , price , stock , discount , discription , image  from Item;";
					
					if(session.getAttribute("selectedItem") != null)
					{
						String numStr = session.getAttribute("selectedItem").toString();
					
					//int num = Integer.parseInt(request.getParameter("items"));
					//String t = request.getParameter("items");
					
					
						if(numStr != null)
						{
							int num = Integer.parseInt(numStr);
							query ="select item_code , item_name , price , stock , discount , discription , image  from Item LIMIT "+num+";";
						}
					}
					
					Statement s = con.createStatement();
					ResultSet r = s.executeQuery(query);
					
					String itname="";
					out.print("<form onsubmit = 'validation()' action = 'cart' method = 'get'>");
					while(r.next()){
						 itname = r.getString(2);
						String itcode = r.getString(1);
						String img = r.getString(7);
						float price = r.getInt(3);
						int stock = r.getInt(4);
						String descr = r.getString(6);
						float disc = r.getInt(5);
						String sc = "spanCode"+itcode;
						String sn = "spanName"+itcode;
						String qty = "qty"+itcode;
						//session.setAttribute("sitname", itname);
						//itname.value(
						out.print("<div class = 'div1 col-md-4' >");
								out.print("<div class = 'div2'>");
								
								   out.print("<span ><b>ItemCode : </b><span name = 'itemCode' id="+sc+">"+itcode);
								   out.print("<input type='label' style='display:none' id='lblItemCode' name='lblItemCode' value='"+itcode+"'");
								   out.print("</span></span><br>");
									out.print("<span><b>Name : </b><span name = 'itemName' id='"+sn+"'" +">"+itname);
									out.print("</span></span><br>");									
									out.print("<span name = 'itemPrice'><b>Price : </b>"+price+"</span><br>");
									out.print("<input type='label' style='display:none' name='lblItemPrice' value='"+price+"'");
									out.print("<span name = 'itemStock'><b>stock : </b>"+stock+"</span><br>");
									out.print("<span name = 'itemDisc'><b>Discount : </b>"+disc+"%</span><br>");
									out.print("<span name = 'itemDescr'><b>Description  :</b>"+descr+"</span>");
									out.print("<br>");
									out.print("<input id='bupt' class='bupt' data-id='"+ itcode +"' type ='submit' value = 'add to cart' ><br><br>");
									out.print("Quaintity :"+"<input type ='number' id ='Q' name =" + qty + " class = 'field' placeholder='Quaintity' value = '' ><br>");
								out.print("</div>");
							
								out.print("<div class='div3'>");
									
									out.print("<image height=100px width=100px align=right border='2px solid black' src = '"+img+"' alt = 'cannot load the image'> ");
									
								out.print("</div>");
								
								
						out.print("</div>");
						out.print("<br>");out.print("<br>");	
					}
					
					
					out.print("</form >");
					con.close();	
			}catch(SQLException e){
				out.println("<h1>"+e+"</h1>");
				out.print("can't connect to Database");
			}
		%>
		
</div>
</div>
<script>

function validation()
{
//console.log(val);
	//e.preventDeafult();
	var valid = true;
	let a = document.getElementById("Q").value;
	
	if(a=="" ){
		alert("please enter your Quantity");
		return false;
	}

	
	if(isNaN(a)){
			alert("quantity should be numbers");
			return false;
	}
	return true;
}

</script>

<div id = "Footer"></div>	

</body>
</html>