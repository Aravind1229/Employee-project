package employee_data;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class displayemployee
 */
@WebServlet("/displayemployee")
public class displayemployee extends HttpServlet {
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ResultSet resultset = stmt.executeQuery("select * from employee");
			PrintWriter out = response.getWriter();
			out.print("<table>");
			out.print("<tr>");
			out.print("<th>");
			out.print("ID");
			out.print("</th>");
			out.print("<th>");
			out.print("NAME");
			out.print("</th>");
			out.print("<th>");
			out.print("AGE");
			out.print("</th>");
			out.print("<th>");
			out.print("SALARY");
			out.print("</th>");
			out.print("<th>");
			out.print("DESIGNATION");
			out.print("</th>");
			out.print("</tr>");
			while(resultset.next()) {
				out.print("<tr>");
				out.print("<td>");
				out.print(resultset.getInt(1));
				out.print("</td>");
				out.print("<td>");
				out.print(resultset.getString(2));
				out.print("</td>");
				out.print("<td>");
				out.print(resultset.getInt(3));
				out.print("</td>");
				out.print("<td>");
				out.print(resultset.getInt(4));
				out.print("</td>");
				out.print("<td>");
				out.print(resultset.getString(5));
				out.print("</td>");
				out.print("</tr>");
			}
			out.print("</table>");
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
