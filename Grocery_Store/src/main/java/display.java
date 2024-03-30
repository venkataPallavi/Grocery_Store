

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * Servlet implementation class display
 */
@WebServlet("/display")
public class display extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public display() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
		
		try {
			HttpSession session = request.getSession();
			//Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/store","pallavi","Passwd@123");
			String num = request.getParameter("items");
			session.setAttribute("selectedItem",num);
			response.sendRedirect("user.jsp");
			//String t = request.getParameter("items");
			/*
			session.setAttribute("selectedItem",num);
			String query ="select item_code , item_name , price , stock , discount , discription , image  from Item LIMIT "+num+";";
			Statement s = con.createStatement();
			ResultSet r = s.executeQuery(query);
			
			String itname="";
			//out.print("<form action = 'cart' method = 'get'>");
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
				//session.setAttribute("sitname", itname);
				//itname.value(
				prnt.print("<div class = 'div1 col-md-4' style='float:left'>");
						prnt.print("<div class = 'div2'>");
						
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
			
			
			//out.print("</form >");
			con.close();	
			*/
	}catch(Exception e){
		prnt.println("<h1>"+e+"</h1>");
		prnt.println("can't connect to Database");
	}
			
		
	
	}

}
