

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

/**
 * Servlet implementation class addit
 */
@WebServlet("/addit")
public class addit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
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
			
		String itname=request.getParameter("itname");
		String itcode = request.getParameter("itcode");
		int price = Integer.parseInt(request.getParameter("price"));
		int stock = Integer.parseInt(request.getParameter("stock"));
		int disc = Integer.parseInt(request.getParameter("disc"));
		String dis=request.getParameter("dis");
		String img=request.getParameter("img");
		
		
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/store","pallavi","Passwd@123");
		String st = "INSERT INTO Item(item_code , item_name , price , stock , discount , discription , image )"+" values(?,?,?,?,?,?,?)";
		PreparedStatement s = con.prepareStatement(st);
		s.setString(1,itcode);
		s.setString(2,itname);
		
		s.setInt(3,price);
		s.setInt(4,stock);
		s.setInt(5,disc);
		s.setString(6,dis);
		s.setString(7,img);
		
		
		
		boolean allOk=true;
		//String uname= request.getParameter("itcode");
		String query="SELECT item_code FROM Item where item_code='"+itcode+"'";
		String DbUserName="";
		//Statement stat;
		
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
		
		}catch(SQLException e) {
			prnt.println("<h1>"+e+"</h1>");
		}

	}
}
