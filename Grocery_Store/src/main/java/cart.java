

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Servlet implementation class cart
 */
@WebServlet("/cart")
public class cart extends HttpServlet {
	static Scanner sc = new Scanner(System.in);
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		PrintWriter prnt = response.getWriter();
		try {
			response.getWriter().append("Served at: ").append(request.getContextPath());
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("user.jsp");
			String itcode = request.getParameter("lblItemCode");
			//String itname=request.getParameter("itemName");
			String price = request.getParameter("lblItemPrice");
			Double newData = new Double(price);
			int valuePrice = newData.intValue();
			//int disc = Integer.parseInt(request.getParameter("disc"));
			String qtyParameterName = "qty" +itcode;
			int qua = Integer.parseInt(request.getParameter(qtyParameterName));
			int itemTotalPrice = qua * valuePrice;
			String purby = session.getAttribute("uid").toString();
			//String oid = session.getAttribute("key").toString();
			//int generatedKey = 0;
			if(session.getAttribute("key") == null) {
			createorder( itcode, qua, itemTotalPrice , purby, session,price);
			}else {
				
				updateorder(itcode, qua, itemTotalPrice , purby, session ,  price, Integer.parseInt(session.getAttribute("key").toString()));
			}
			
			response.sendRedirect("user.jsp"); 
		} catch (Exception e) {
			// TODO: handle exception
			prnt.println("<h1>"+e+"</h1>");
		}
	}
public void createorder(String itcode,int qua,int itemTotalPrice ,String purby, HttpSession sess , String price) throws Exception {
	
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/store","pallavi","Passwd@123");
	try {
		
	
	long millis = System.currentTimeMillis();
    java.sql.Date date = new java.sql.Date(millis);
	int noit = 1;
    boolean status = false;
	
	con.setAutoCommit(false);
	String st = "INSERT INTO Purchase_orders(final_cost , purchased_by , purchased_on  , no_of_items , order_status )"+" values(?,?,?,?,?)";
	PreparedStatement s = con.prepareStatement(st,Statement.RETURN_GENERATED_KEYS);
	s.setInt(1,itemTotalPrice);
	s.setString(2,purby);
	
	s.setDate(3,date);
	s.setInt(4,noit);
	s.setBoolean(5,status);
	
	int ret = s.executeUpdate();
	ResultSet rs = s.getGeneratedKeys();
	int generatedKey = 0;
	if (rs.next()) {
	    generatedKey = rs.getInt(1);
	}
	sess.setAttribute("key",generatedKey);  
	
	//Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/store","pallavi","Passwd@123");
	String st1 = "INSERT INTO Purchase_items(item_id , quantity , total_price  , purchase_order_id  )"+" values(?,?,?,?)";
	PreparedStatement s1 = con.prepareStatement(st1);
	s1.setString(1,itcode);
	s1.setInt(2,qua);
	
	s1.setString(3,price);
	s1.setInt(4,generatedKey);
	
	s1.executeUpdate();
	
	con.commit();
	
	} catch (Exception e) {
		// TODO: handle exception
		con.rollback();
		}
	
}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

void updateorder(String itcode,int qua,int itemTotalPrice ,String purby, HttpSession sess , String price,int generatedKey) throws Exception {
	
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/store","pallavi","Passwd@123");
	String query="SELECT * FROM Purchase_items where item_id='"+itcode+"' and purchase_order_id = "+generatedKey; 
	PreparedStatement s2 = con.prepareStatement(query);
	ResultSet rst=s2.executeQuery(query);
	if(!rst.isBeforeFirst() && rst.getRow() == 0)
	{

		String st1 = "INSERT INTO Purchase_items(item_id , quantity , total_price  , purchase_order_id  )"+" values(?,?,?,?)";
		PreparedStatement s1 = con.prepareStatement(st1);
		s1.setString(1,itcode);
		s1.setInt(2,qua);
		
		s1.setString(3,price);
		s1.setInt(4,generatedKey);
		
		s1.executeUpdate();
		
	}
	else
	{
		while(rst.next()) {
			String query1="update Purchase_items set quantity='"+qua+"' where item_id='"+itcode+"'";
			PreparedStatement s1 = con.prepareStatement(query1);
			s1.executeUpdate();
		}	
	}
	
}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		response.setContentType("text/html");
		PrintWriter prnt = response.getWriter();
		String dtype = "<!DOCTYPE HTML>\n";
		
		try {
			
			String itcode = request.getParameter("itcode");
			//String itname=request.getParameter("itname");
			//int price = Integer.parseInt(request.getParameter("price"));
			//int disc = Integer.parseInt(request.getParameter("disc"));
			int qua = Integer.parseInt(request.getParameter("quantity"));
			
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/store","pallavi","Passwd@123");
			
			
			if(qua > 0) {
				
				
				
			}
			
		}catch(Exception e) {
			prnt.println("<h1>"+e+"</h1>");
		}
		
	}

}
