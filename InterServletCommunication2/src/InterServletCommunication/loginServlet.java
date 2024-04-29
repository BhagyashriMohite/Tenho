package InterServletCommunication;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class loginServlet
 */

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
  @Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	 String Username = request.getParameter("Username");
	 String Password = request.getParameter("Password");
	 
	 try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","root");
		
		Statement statement = con.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from user where email='"+Username+"' and password='"+Password+"'");
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("homeServlet");
		
		if(resultSet.next()){
			request.setAttribute("message","Welcome to InterServlet Communication " +Username);
			requestDispatcher.forward(request, response);
		}
		else{
			requestDispatcher=request.getRequestDispatcher("Login.html");
			requestDispatcher.include(request, response);
			
			
		}
		
	}
	 
	 
	 
	 
	 catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}

	}

}
