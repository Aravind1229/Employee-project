package employee_data;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class updateemployee
 */
@WebServlet("/updateemployee")
public class updateemployee extends HttpServlet {
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
		int salary=Integer.parseInt(request.getParameter("salary"));
		try {
			int result=stmt.executeUpdate("update employee set salary="+salary+" where id="+id);
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			if(result==1) {
			out.print("<b>salary updated for employee id="+id+"</b>");
			}
			out.print("<br>");
			out.print("<a href=home.html>Return to homepage</a>");
			
			
		} catch (SQLException e) {
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