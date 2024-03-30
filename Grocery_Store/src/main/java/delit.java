

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

/**
 * Servlet implementation class updit
 */
@WebServlet("/delit")
public class delit extends HttpServlet {
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
		try {
			HttpSession session = request.getSession();
		    
		//String itname=request.getParameter("itname");
		String itcode = request.getParameter("lblItemCode");
		//String itcode = session.getAttribute("itemIdTODelete").toString();
		//int price = Integer.parseInt(request.getParameter("price"));
		//int stock = Integer.parseInt(request.getParameter("stock"));
		//int disc = Integer.parseInt(request.getParameter("disc"));
		//String dis=request.getParameter("dis");
		//String img=request.getParameter("img");
		
		
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/store","pallavi","Passwd@123");
		//String st = "INSERT INTO Item( item_name , price , stock , discount , discription , image )"+" values(?,?,?,?,?,?,?)";
		
		
			
		//boolean allOk=true;
		//String uname= request.getParameter("itcode");
		String query="delete from Item where item_code='"+itcode+"'";
		PreparedStatement s = con.prepareStatement(query);
		//String DbUserName="";
		//Statement stat;
		s.executeUpdate();
		response.sendRedirect("shopke.jsp"); 
		/*
		ResultSet rst=s.executeQuery(query);
		
		if(rst.next() == false)
		{
			s.executeUpdate();
			response.sendRedirect("shopke.jsp"); 
		}
		else				
		{
			allOk=false;
			//String show;
			//ServletResponse response;
			//response.setContentType("text/html");
			//PrintWriter prnt = response.getWriter();
			prnt.println("item code already exists, please choose another");
			itcode="";
		}
		*/
		}catch(SQLException e) {
			prnt.println("<h1>"+e+"</h1>");
		}

	


	
	}

}
