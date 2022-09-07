package userManage;

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

@WebServlet("/showdata")

public class showUserservelet extends HttpServlet {
	private final static String query = "select id,name,email,phone,city,dob,gender from user";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//get PrintWriter
		PrintWriter pw = res.getWriter();
		//set content
		res.setContentType("text/html");
		//Linking Bootstrap
	   pw.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");
	   
		//load the JDBC driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(Exception e) {
			e.printStackTrace();
		}
		//generate the connection
		try(Connection con = DriverManager.getConnection("jdbc:mysql:///usermanage","root","Sweety12@");
				PreparedStatement ps = con.prepareStatement(query);){
			//result set
			ResultSet rs= ps.executeQuery();
			pw.println("<div style='margin:auto;width:900px;margin-top:100px;'>");
	       pw.println("<table class='table table-hover table-striped'>");
	       pw.println("<tr>");	
	       pw.println("<th>ID</th>");	
	       pw.println("<th>Name</th>");	
	       pw.println("<th>Email</th>");	
	       pw.println("<th>Phone</th>");	
	       pw.println("<th>City</th>");	
	       pw.println("<th>DOB</th>");	
	       pw.println("<th>Gender</th>");	
	       pw.println("<th>Delete</th>");	
	       pw.println("</tr>");	
	       while(rs.next()) {
				pw.println("<tr>");
				pw.println("<td>"+rs.getInt(1)+"</td>");
				pw.println("<td>"+rs.getString(2)+"</td>");
				pw.println("<td>"+rs.getString(3)+"</td>");
				pw.println("<td>"+rs.getString(4)+"</td>");
				pw.println("<td>"+rs.getString(5)+"</td>");
				pw.println("<td>"+rs.getString(6)+"</td>");
				pw.println("<td>"+rs.getString(7)+"</td>");
				
				pw.println("<td><a href='deleteturl?id="+rs.getInt(1)+"'>Delete</a></td>");
				pw.println("<tr>");
			}
	       
	       pw.println("</table>");	
			
		}catch(SQLException se) {
			pw.println("<h2 lass='bg-danger text-light text-center'>"+se.getMessage()+"</h2>");
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		pw.println("<a href='home.html'><button class=\"btn btn-success btn-block btn-lg gradient-custom-4 text-body\">Home</button></a>");
		pw.println("</div");
		//close the stream
		pw.close();
		
	
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}
}
