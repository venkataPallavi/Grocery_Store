

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
 * Servlet implementation class ser
 */
@WebServlet("/ser")
public class ser extends HttpServlet {
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
		//HttpSession session = request.getSession();
		
		prnt.println("<div class ='it col-md-12' >");

			try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/store","pallavi","Passwd@123");){
				String ser =request.getParameter("q");
				String query ="select item_code , item_name , price , stock , discount , discription , image  from Item where item_name LIKE '%"+ser+"%';";
				Statement s = con.createStatement();
				ResultSet r = s.executeQuery(query);
				
				//String itname="";
				//out.print("<form action = 'cart' method = 'get'>");
				while(r.next()){
					String itname = r.getString(2);
					String itcode = r.getString(1);
					String img = r.getString(7);
					float price = r.getInt(3);
					int stock = r.getInt(4);
					String descr = r.getString(6);
					float disc = r.getInt(5);
					String sc = "spanCode"+itcode;
					String sn = "spanName"+itcode;
					//session.setAttribute("sitname", itname);
					//itname.value(
					prnt.println("<div class = 'div1 col-md-4' >");
					prnt.println("<div class = 'div2'>");
							
					prnt.println("<span ><b>ItemCode : </b><span name = 'itemCode' id="+sc+">"+itcode);
					prnt.println("<input type='label' style='display:none' name='lblItemCode' value='"+itcode+"'");
					prnt.println("</span></span><br>");
					prnt.println("<span><b>Name : </b><span name = 'itemName' id='"+sn+"'" +">"+itname);
					prnt.println("</span></span><br>");									
					prnt.println("<span name = 'itemPrice'><b>Price : </b>"+price+"</span><br>");
					prnt.println("<input type='label' style='display:none' name='lblItemPrice' value='"+price+"'");
					prnt.println("<span name = 'itemStock'><b>stock : </b>"+stock+"</span><br>");
					prnt.println("<span name = 'itemDisc'><b>Discount : </b>"+disc+"%</span><br>");
					prnt.println("<span name = 'itemDescr'><b>Description  :</b>"+descr+"</span>");
					prnt.println("<br>");
					prnt.println("<input id='bupt' class='bupt' data-id='"+ itcode +"' type ='submit' value = 'add to cart' ><br><br>");
					prnt.println("Quaintity :"+"<input type ='number' name = 'quantity' class = 'field' placeholder='Quaintity' value = '' ><br>");
					prnt.println("</div>");
						
					prnt.println("<div class='div3'>");
								
					prnt.println("<image height=100px width=100px align=right border='2px solid black' src = '"+img+"' alt = 'cannot load the image'> ");
								
					prnt.println("</div>");
							
							
					prnt.println("</div>");
					prnt.println("<br>");prnt.println("<br>");	
				}
				
		prnt.println("</div>");
				//prnt.println("</form >");
				con.close();	
		}catch(SQLException e){
			prnt.println("<h1>"+e+"</h1>");
			prnt.println("can't connect to Database");
		}
			
			
		}

}
