package controller; 

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logoutservlet")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // Invalidate the session
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // Ends the session
        }
        
        // Set headers to prevent page caching
        response.setHeader("Cache-Control", "no-store"); // Prevent caching
        response.setHeader("Pragma", "no-cache"); // Older HTTP 1.0 cache directive
        response.setDateHeader("Expires", 0); // Explicitly set expiration time
        
        // Redirect the user to the login page
        response.sendRedirect("index.jsp");
    }
}
