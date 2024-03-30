

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Cart1
 */
@WebServlet("/Cart1")
public class Cart1 extends HttpServlet {
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
		HttpSession session = request.getSession();
		try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/store","pallavi","Passwd@123");){
			//HttpSession session = request.getSession();
			//String usname = session.getAttribute("user").toString();//request.getParameter("usname");
			
			int key=Integer.parseInt(session.getAttribute("key").toString()); 
			String query ="select *  from Purchase_items where purchase_order_id = '" + key +"';";

			Statement s = con.createStatement();
			ResultSet r = s.executeQuery(query);
			int  purchase_order_id = 0;
			//String usname = "";
			while(r.next()){
				//if(r!=null){
			   // String usname = r.getString(1);
				int purchase_id = r.getInt(1);
				String item_id = r.getString(2);
				//String pho = r.getString(4);
				//String address = r.getString(5);
				int quantity = r.getInt(3);
				int total_price = r.getInt(4);
				 purchase_order_id = r.getInt(5);
				//String query = "";
				
				prnt.println("<div class = 'div1 col-md-4' >");
				prnt.println("<div class = 'div2'>");
				prnt.println("<span><b>Purchase_id : </b>"+purchase_id+"</span><br>");
				prnt.println("<span><b>item_id : </b>"+item_id+"</span><br>");
				prnt.println("<span><b>quantity : </b>"+quantity+"</span><br>");
				prnt.println("<span><b> total_price : </b>"+total_price+"</span><br>");
				prnt.println("<span><b>purchase_order_id : </b>"+purchase_order_id+"</span><br>");
				//prnt.println("<span><b>Address  :</b>"+address+"</span>");
				//prnt.println("<span><b>PinCode  :</b>"+pinc+"</span>");
				prnt.println("<br>");
							
						prnt.println("</div>");		
						prnt.println("</div>");
						
						prnt.println("<br>");prnt.println("<br>");	
			//}
		}
			
			
			prnt.println("<form  action = 'checkout' method = 'post'>");
			prnt.println("<input type='label' style='display:none' name='lblItemCode' value ='"+ purchase_order_id +"'>");
			prnt.println(" <button class='bdet'  type='submit'>Check Out</button> ");
			prnt.println("</form>");	
			
			
			con.close();	
	}catch(SQLException e){
		prnt.println("<h1>"+e+"</h1>");
		prnt.println("can't connect to Database");
	}
		
		

	}
	
	

}
