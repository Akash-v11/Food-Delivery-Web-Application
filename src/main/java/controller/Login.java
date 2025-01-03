package controller; 


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daoImplementation.UserDaoImplementation;
import databaseutil.MyConnection;
import model.User;
 
  
@WebServlet("/Login")
public class Login extends HttpServlet 
{
  
	private Connection con;

	@Override
	public void init() throws ServletException {
		  con = MyConnection.connect();
 	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		UserDaoImplementation udaoI = new UserDaoImplementation();
		
		User user = udaoI.getUserEmail(email);
		if(user != null)
		{
			if(user.getPassword().equals(password)) 
			{
				HttpSession session = req.getSession();
				session.setAttribute("LoggedInUser", user);
				
				System.out.println("LoggedInUser fetched from login" + user);
				
//				resp.sendRedirect("GetRestaurant");
				req.getRequestDispatcher("GetRestaurant").forward(req, resp);
//		        req.getRequestDispatcher("home.jsp").forward(req, resp); 

			}
			else
			{
				resp.sendRedirect("pwdincorrect.jsp");				
			}
		}
		else 
		{
			resp.sendRedirect("invaliduser.jsp");		
  		}
 	}
	 
	 
	private void destory() {
		try {
			con.close();
		} catch (SQLException e) {
 			e.printStackTrace();
		}

	}
}

 
