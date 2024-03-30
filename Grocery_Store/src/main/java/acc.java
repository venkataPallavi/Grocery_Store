

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
 * Servlet implementation class acc
 */
@WebServlet("/acc")
public class acc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public acc() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		PrintWriter prnt = response.getWriter();
		String dtype = "<!DOCTYPE HTML>\n";
		HttpSession session = request.getSession();
		try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/store","pallavi","Passwd@123");){
			//HttpSession session = request.getSession();
			//String usname = session.getAttribute("user").toString();//request.getParameter("usname");
			
			String usname=session.getAttribute("user").toString(); 
			String query ="select Email_id , first_name , last_name , Phone_number , Address ,Pincode  from User where User_name = '" + usname +"';";

			Statement s = con.createStatement();
			ResultSet r = s.executeQuery(query);
			
			//String usname = "";
			while(r.next()){
				//if(r!=null){
			   // String usname = r.getString(1);
				String emid = r.getString(1);
				String finame = r.getString(2);
				String pho = r.getString(4);
				String address = r.getString(5);
				int pinc = r.getInt(6);
				String laname = r.getString(3);
				//String query = "";
				
				prnt.println("<div class = 'div1 col-md-4' style='float:left'>");
				prnt.println("<div class = 'div2'>");
				prnt.println("<span><b>Name : </b>"+usname+"</span><br>");
				prnt.println("<span><b>email-id : </b>"+emid+"</span><br>");
				prnt.println("<span><b>First Name : </b>"+finame+"</span><br>");
				prnt.println("<span><b>Last Name : </b>"+laname+"</span><br>");
				prnt.println("<span><b>Phone : </b>"+pho+"</span><br>");
				prnt.println("<span><b>Address  :</b>"+address+"</span>");
				prnt.println("<span><b>PinCode  :</b>"+pinc+"</span>");
				prnt.println("<br>");
							
						prnt.println("</div>");		
						prnt.println("</div>");
						prnt.println("<br>");prnt.println("<br>");	
			//}
		}
			//else{
				
				//prnt.println("no values found");
				
			//s}
			
			
			
			con.close();	
	}catch(SQLException e){
		prnt.println("<h1>"+e+"</h1>");
		prnt.println("can't connect to Database");
	}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
