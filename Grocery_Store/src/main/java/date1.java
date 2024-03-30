

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
 * Servlet implementation class date1
 */
@WebServlet("/date1")
public class date1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public date1() {
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
		doGet(request, response);
		
		response.setContentType("text/html");
		PrintWriter prnt = response.getWriter();
		String dtype = "<!DOCTYPE HTML>\n";
		prnt.println("<div id='it col-md-12' style='display:flex'>");
		try {
		String fromdate = request.getParameter("from").toString();
		String todate = request.getParameter("to").toString();
		String month = request.getParameter("1mon");
		String month2 = request.getParameter("2mon");
		String query="select purchase_order_id , final_cost , purchased_on , no_of_items   from Purchase_orders where purchased_on BETWEEN '"+fromdate+"' AND '"+todate+"'";  
		Month todayMonth = LocalDateTime.now().getMonth();
		int todayYear = LocalDateTime.now().getYear();
		if(month != null)
		{
			var lastMonth = todayMonth.getValue()-1;			
			var firstDate = todayYear+"-"+ lastMonth +"-01";
			var lastDate = todayYear+"-"+ lastMonth +"-30";
			query="select purchase_order_id , final_cost , purchased_on , no_of_items   from Purchase_orders where purchased_on BETWEEN '"+firstDate+"' AND '"+lastDate+"'"; 
		}
		else if(month2 != null)
		{
			var last2Month = todayMonth.getValue()-2;	
			var lastMonth = todayMonth.getValue()-1;	
			var firstDate = todayYear+"-"+ last2Month +"-01";
			var lastDate = todayYear+"-"+ lastMonth +"-30";
			query="select purchase_order_id , final_cost , purchased_on , no_of_items   from Purchase_orders where purchased_on BETWEEN '"+firstDate+"' AND '"+lastDate+"'";
		}
		
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/store","pallavi","Passwd@123");
		 
		
		Statement s = con.createStatement();
		ResultSet r = s.executeQuery(query);
		

		while(r.next()){
			//if(r!=null){
		    //String usname = r.getString(2);
	int purchase_order_id = r.getInt(1);
	int final_cost = r.getInt(2);
	String date = r.getDate(3).toString();
	int no_of_items = r.getInt(4);
	//int pinc = r.getInt(6);
	//String laname = r.getString(3);
			//String query = "";
	
			prnt.println("<div class = 'div1 col-md-4'>");
			prnt.println("<br>");
			prnt.println("<div class = 'div2'>");
			prnt.println("<br>");
			prnt.println("<span><b>purchase_order_id : </b>"+purchase_order_id+"</span><br>");
			prnt.println("<span><b>final_cost : </b>"+final_cost+"</span><br>");
			prnt.println("<span><b>purchased_on : </b>"+date+"</span><br>");
			prnt.println("<span><b>no_of_items : </b>"+no_of_items+"</span><br>");
						//out.print("<span><b>Phone : </b>"+pho+"</span><br>");
						//out.print("<span><b>Address  :</b>"+address+"</span><br>");
						//out.print("<span><b>PinCode  :</b>"+pinc+"</span>");
			prnt.println("<br>");
						
			prnt.println("</div>");		
			prnt.println("</div>");
			prnt.println("<br>");prnt.println("<br>");	
			
		//}
	}
		//else{
			
		//	out.print("no values found");
			
		//}
		
		
		
		con.close();	
}catch(SQLException e){
	prnt.println("<h1>"+e+"</h1>");
	prnt.println("can't connect to Database");
}
	prnt.println("</div>");

}
		
		

}
