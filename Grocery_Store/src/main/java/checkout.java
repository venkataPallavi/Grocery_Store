

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
 * Servlet implementation class checkout
 */
@WebServlet("/checkout")
public class checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public checkout() {
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
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/store","pallavi","Passwd@123");
			int orderid = Integer.parseInt(request.getParameter("lblItemCode"));
			String query ="select item_id,quantity,total_price  from Purchase_items where purchase_order_id = '" +orderid  +"';";
			Statement s = con.createStatement();
			ResultSet r = s.executeQuery(query);
			int itemCount = 0;
			int totalVal = 0;
			prnt.println("<div>");
			prnt.println("<table style = 'border-collapse : collapse'>");
			prnt.println("<tr>");
			prnt.println("<th style = 'background-color : green; padding: 8px; '>Item</th><th style = 'background-color : green; padding: 8px;'>Quantity</th><th style = 'background-color : green; padding: 8px; '>Cost</th>");
			prnt.println("</tr>");
			while(r.next()){
				itemCount++;
				totalVal = totalVal + r.getInt("total_price");				
				String item_id = r.getString(1);
				int quantity = r.getInt(2);
				int total_price = r.getInt(3);
				prnt.println("<tr >");
				prnt.println("<td style ='padding: 8px' >"+item_id+"</td><td style ='padding: 8px'>"+quantity+"</td><td style ='padding: 8px'>"+total_price+"</td>");
				prnt.println("</tr>");
			}
			prnt.println("</table>");
			prnt.println("</div>");
			prnt.println("<div>");
			prnt.println("<br>");			prnt.println("<br>");

			prnt.println("<span>Total Items : </span>" + itemCount + "</br>");
			prnt.println("<span>Final Cost : </span>" + totalVal);
			prnt.println("</div>");
			var dateVal = LocalDateTime.now().getYear() + "-" + LocalDateTime.now().getMonth().getValue() + "-" + LocalDateTime.now().getDayOfMonth();
			//prnt.println(dateVal);
			String queryUpdate ="update Purchase_orders set final_cost = "+totalVal+", no_of_items= "+ itemCount +", order_status = true, purchased_on = '"+ dateVal +"' where purchase_order_id = " +orderid  +";";
			//prnt.println(queryUpdate);
			prnt.println("Sucsessfully completed ");
			Statement s1 = con.createStatement();
			s1.executeUpdate(queryUpdate);
			
		}catch(SQLException e) {
			
			prnt.println("<h1>"+e+"</h1>");
		}
	}

}
