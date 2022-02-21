package employee_data;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class createemployee
 */
@WebServlet("/createemployee")
public class createemployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection connection;
	Statement stmt;
	public void init() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/employeedata";
			connection = DriverManager.getConnection(url,"root","LeoLM10#");
			stmt=connection.createStatement();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		String name=request.getParameter("name");
		int age=Integer.parseInt(request.getParameter("age"));
		int salary=Integer.parseInt(request.getParameter("salary"));
		String designation=request.getParameter("desig");
		try {
			int result=stmt.executeUpdate("insert into employee values("+id+",'"+name+"',"+age+","+salary+",'"+designation+"')");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.print("<b>"+result+ "row created</b>");
			out.print("<br>");
			out.print("<a href=home.html>Return to homepage</a>");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}