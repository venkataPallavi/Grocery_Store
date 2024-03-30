package lab_ass;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class reg
 */
@WebServlet("/reg")
public class reg extends HttpServlet {
	static Scanner sc = new Scanner(System.in);
	private static final long serialVersionUID = 1L;

   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter prnt = response.getWriter();
		
		//RequestDispatcher reqDisp = request.getRequestDispatcher("/login.html");
		//reqDisp.include(request, response);
		String dtype = "<!DOCTYPE HTML>\n";
		String show = "";
		

		
		try {
			int pinc = Integer.parseInt(request.getParameter("pin"));
			
			/*boolean val = addRegis(request.getParameter("User_type"),request.getParameter("uname"),
					request.getParameter("eid"),request.getParameter("pass"),request.getParameter("fname"),
					request.getParameter("lname"),
					request.getParameter("ph"),request.getParameter("add"),pinc);*/
			
			String Utype=request.getParameter("User_type");
			String usname = request.getParameter("uname");
			String emid = request.getParameter("eid");
			String passwd = request.getParameter("pass");
			String finame = request.getParameter("fname");
			String laname = request.getParameter("lname");
			String pho = request.getParameter("ph");
			String address = request.getParameter("add");
			
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/store","pallavi","Passwd@123");

			
			String st = "INSERT INTO User(User_type , User_name , Email_id , Password , first_name , last_name , Phone_number , Address ,Pincode )"+" values(?,?,?,?,?,?,?,?,?)";
			PreparedStatement s = con.prepareStatement(st);
			s.setInt(1,Integer.parseInt(Utype));
			s.setString(2,usname);
			s.setString(3,emid);
			s.setString(4,passwd);
			s.setString(5,finame);
			s.setString(6,laname);
			s.setString(7,pho);
			s.setString(8,address);
			s.setInt(9,pinc);
			
			
			
			boolean allOk=true;
			String uname= request.getParameter("uname");
			String query="SELECT User_name FROM User where User_name='"+uname+"'";
			String DbUserName="";
			//Statement stat;
			
			ResultSet rst=s.executeQuery(query);
			
			if(rst.next() == false)
			{
				s.executeUpdate();
				response.sendRedirect("login.html"); 
			}
			else				
			{
				allOk=false;
				//String show;
				//ServletResponse response;
				//response.setContentType("text/html");
				//PrintWriter prnt = response.getWriter();
				prnt.println("User name already exists, please choose another");
				uname="";
			}
			
			//con.close();
			//action("login");
			//return true;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			show = "<h1>failed to insert</h1>";
			prnt.println("<h1>"+e+"</h1>");
		}
		
		 
		
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {

		doPost(request,response);
		
	}

}
