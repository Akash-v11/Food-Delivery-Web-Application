package controller; 
 
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoImplementation.UserDaoImplementation;
import model.User;

 
@WebServlet("/FetchAllUserDetails")
public class FetchAllUserDetails extends HttpServlet 
{
	
	
	private List<User> User;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		UserDaoImplementation udaoimp = new UserDaoImplementation();
		User = udaoimp.fetchAll();
		
	    req.getSession().setAttribute("AllUserDetails", User); 
		
//	    req.getRequestDispatcher("userdetails.jsp").forward(req, resp);
 	    resp.sendRedirect("userdetails.jsp");
 	}
	
	
}
