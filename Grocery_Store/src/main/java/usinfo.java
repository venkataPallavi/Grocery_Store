

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class usinfo
 */
@WebServlet("/usinfo")
public class usinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		response.setContentType("text/html");
		PrintWriter prnt = response.getWriter();
		String dtype = "<!DOCTYPE HTML>\n";
		prnt.println("<div id='it col-md-12' style='display:flex'>");
		try{
			
			
			//HttpSession session = request.getSession();
			//String usname = session.getAttribute("user").toString();//request.getParameter("usname");
			//String usname=(String)session.getAttribute("user"); 
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/store","pallavi","Passwd@123");
			String usname = request.getParameter("user");
			String q1 = "select user_id from User where User_name = '"+usname+"'";
			
			Statement s = con.createStatement();
			ResultSet r = s.executeQuery(q1);
			r.next();
			int userid = r.getInt(1);
			
			String query ="select purchase_order_id , final_cost , purchased_on , no_of_items   from Purchase_orders where purchased_by = '" + userid +"';";

			
			Statement s1 = con.createStatement();
			ResultSet r1 = s1.executeQuery(query);
			
			//String usname = "";
			
			
			while(r1.next()){
				//if(r!=null){
			    //String usname = r.getString(2);
		int purchase_order_id = r1.getInt(1);
		int final_cost = r1.getInt(2);
		String date = r1.getDate(3).toString();
		int no_of_items = r1.getInt(4);
		//int pinc = r.getInt(6);
		//String laname = r.getString(3);
				//String query = "";
		
				prnt.println("<div class = 'div1 col-md-4' style='float:left'>");
				prnt.println("<br>");
				prnt.println("<div class = 'div2'>");
				prnt.println("<br>");
				prnt.println("<span><b>purchase_order_id : </b>"+purchase_order_id+"</span><br>");
				prnt.println("<span><b>final_cost : </b>"+final_cost+"</span><br>");
				prnt.println("<span><b>purchased_on : </b>"+date+"</span><br>");
				prnt.println("<span><b>no_of_items : </b>"+no_of_items+"</span><br>");
							//out.print("<span><b>Phone : </b>"+pho+"</span><br>");
							//out.print("<span><b>Address  :</b>"+address+"</span><br>");
							//out.print("<span><b>PinCode  :</b>"+pinc+"</span>");
				prnt.println("<br>");
							
				prnt.println("</div>");		
				prnt.println("</div>");
				prnt.println("<br>");prnt.println("<br>");	
				
			//}
		}
			//else{
				
			//	out.print("no values found");
				
			//}
			
			
			
			con.close();	
	}catch(SQLException e){
		prnt.println("<h1>"+e+"</h1>");
		prnt.println("can't connect to Database");
	}
		prnt.println("</div>");
	
	}

}
