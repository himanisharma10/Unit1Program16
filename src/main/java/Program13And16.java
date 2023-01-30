
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Program13And16
 */
public class Program13And16 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final String URL = "jdbc:mysql://localhost:3306/datascience";
	final String USER = "root";
	final String PASSWORD = "password";
	final String DRIVER = "com.mysql.cj.jdbc.Driver";
	Connection conn = null;


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			// Set the response content type and
			// get the PrintWriter object.
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

			// Set up HTML table formatting for the output
			out.println("<html><body>");
			out.println("<h3>User Details</h3>");
			out.println("<table border=1><tr>" 
					+ "<td><b>User Id</b></td>" 
					+ "<td><b>Name</b></td>"
					+ "<td><b>Password</b></td>" 
					+ "<td><b>Email</b></td>" 
					+ "<td><b>Phone</b></td>" + "</tr>");

			// Create JDBC statement object, construct
			// the SQL query and execute the query.
			Statement stmt = conn.createStatement();
			String sql = "select user_id, name, password, email_id, mobile from datascience.registeredusers;";
			ResultSet rs = stmt.executeQuery(sql);

			// Loop through the result set to
			// retrieve the individual data items.
			while (rs.next()) {
				int userId = rs.getInt("user_id");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String emailId = rs.getString("email_id");
				String mobile = rs.getString("mobile");

				out.println("<tr>" 
				+ "<td>" + userId + "</td>" 
				+ "<td>" + name + "</td>" 
				+ "<td>" + password + "</td>"
				+ "<td>" + emailId + "</td>" 
				+ "<td>" + mobile + "</td>" + "</tr>");

			}
			out.println("</table></body></html>");

			// Close Result set, Statement
			// and PrintWriter objects.
			rs.close();
			stmt.close();
			out.close();

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void destroy() {

		// Close connection object.
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
