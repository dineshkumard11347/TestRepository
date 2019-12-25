import java.io.*;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class Register2 extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside post method..");
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("Request reached servlet..");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String userName = request.getParameter("userName");
		String userPass = request.getParameter("userPass");
		String userEmail = request.getParameter("userEmail");
		String userCountry = request.getParameter("userCountry");
		System.out.println("userName::"+userName);
		System.out.println("userPass::"+userPass);
		System.out.println("userEmail::"+userEmail);
		System.out.println("userCountry::"+userCountry);
		
		RequestDispatcher disp = null;
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			System.out.println("Connecting to a selected database...");
			Connection con = DriverManager.getConnection("jdbc:mysql://edureka1-demo.cawn4ptm8c9j.us-east-2.rds.amazonaws.com:3306/edureka", "edureka1", "edureka1234");
			System.out.println("Connected database successfully...");
			
			Statement stmt = con.createStatement();
			 
			  String sql = "INSERT INTO registration VALUES (null, '"+userName+"','"+userPass+"','"+userEmail+"','"+userCountry+"')";
			  System.out.println("sql::"+sql);
			  stmt.executeUpdate(sql);
		      
		      System.out.println("Inserted records into the table...");
			
		      request.setAttribute("response", "Record added successfully");
		      
		      out.print("Record added successfully");  
		      disp = request.getRequestDispatcher("/success.html");
		      disp.forward(request, response);

		} catch (Exception e2) {
			e2.printStackTrace();
			disp = request.getRequestDispatcher("/failure.html");
		    disp.forward(request, response);
		}

		out.close();
	}

}