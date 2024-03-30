package lab_ass;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class log
 */
@WebServlet("/log")
public class log extends HttpServlet {
	static Scanner sc = new Scanner(System.in);
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter prnt = response.getWriter();
		String dtype = "<!DOCTYPE HTML>\n";
		String show = "";
		//Connection con= null;

		PreparedStatement s = null;
		PreparedStatement s1 = null;
		ResultSet rst = null;
		ResultSet rst1 = null;

		try {
			
			String usname = request.getParameter("usname");
     		String passwd = request.getParameter("passwd");

     		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/store","pallavi","Passwd@123");
    		
			boolean allOk=false;
			//String uname= request.getParameter("uname");
			String query="SELECT User_name,user_id,User_type FROM User where User_name='"+usname+"' and Password = '"+passwd+"'"; //and User_type = 'shopkeeper'";
			//String query1="SELECT User_name FROM User where User_name='"+usname+"' and Password = '"+passwd+"' and User_type = 'Buyer'";

			String DbUserName="";
			//Statement stat;
			 s = con.prepareStatement(query);
			 //s1 = con.prepareStatement(query1);
			rst=s.executeQuery(query);
			//rst1=s1.executeQuery(query1);
			String ut = "";
			int utId = 2;
			
			if(rst.isBeforeFirst())
			{
				while(rst.next()) {
					//s.executeUpdate();
					//Session("webpath") = Request.ServerVariables("URL") 
				    HttpSession session = request.getSession();
				    
					session.setAttribute("user",rst.getString("User_name"));  
					session.setAttribute("uid",rst.getString("user_id"));  
					session.setAttribute("usertype",rst.getString("User_type"));  
					ut = rst.getString("User_type").toString();
				}
				if(Integer.parseInt(ut) == utId)
				{
					response.sendRedirect("user.jsp"); 			
				}
				else
				{
					response.sendRedirect("shopke.jsp"); 			
				}
				con.close();
			}
			/*else if(rst1.next() == true)
			{
				con.close();
				//s.executeUpdate();
				response.sendRedirect("user.jsp"); 
			}
			*/
			else				
			{
				allOk=false;
				//String show;
				//ServletResponse response;
				//response.setContentType("text/html");
				//PrintWriter prnt = response.getWriter();
				prnt.println("User not found please try again");
				con.close();
				//uname="";
			}			
		}catch(Exception e) {
			prnt.println("<h1>"+e+"</h1>");
			//con.close();
		}
		finally {
			//con.close();
		}
		
		
	}

}
