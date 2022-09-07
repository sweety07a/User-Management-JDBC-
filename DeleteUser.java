package userManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 @WebServlet("/deleteturl")
public class DeleteUser extends HttpServlet {

	private final static String query = "delete from user where id=?";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//get PrintWriter
		PrintWriter pw = res.getWriter();
		//set content
		res.setContentType("text/html");
		//Linking Bootstrap
	   pw.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");
	   
		//get the values
	   int id = Integer.parseInt(req.getParameter("id"));
		//load the JDBC driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(Exception e) {
			e.printStackTrace();
		}
		//generate the connection
		try(Connection con = DriverManager.getConnection("jdbc:mysql:///usermanage","root","Sweety12@");
				PreparedStatement ps = con.prepareStatement(query);){
			//set the values
			ps.setInt(1,id);
			
			//execute the query
			int count = ps.executeUpdate();
			pw.println("<div class='card' style='margin:auto;width:300px;margin-top:100px'>");
			if(count==1) {
				pw.println("<h2 class='bg-danger text-light text-center'>Record Deleted Successfully</h2>");
			}else {
				pw.println("<h2 class='bg-danger text-light text-center'>Record Not Deleted</h2>");
			}
		}catch(SQLException se) {
			pw.println("<h2 lass='bg-danger text-light text-center'>"+se.getMessage()+"</h2>");
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		pw.println("<a href='home.html'><button class=\"btn btn-success btn-block btn-lg gradient-custom-4 text-body\"> Home </button></a>");
		pw.println("</div>");
		//close the stream
		pw.close();
	
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}
}
