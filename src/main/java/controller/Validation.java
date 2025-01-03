package controller; 

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Validation")
public class Validation extends HttpServlet 
{

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get the input parameters
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");

        System.out.println("Password: " + password);
        System.out.println("Confirm Password: " + confirmPassword);

 
        // Check if the passwords match
        if (password != null && password.equals(confirmPassword)) {
             // If passwords match, forward to "registerData" or perform necessary actions
             RequestDispatcher rd = req.getRequestDispatcher("Register");
             rd.forward(req, resp);  // Use forward instead of include to maintain the request flow
//        	resp.getWriter().println("Success");
        } else {
            // If passwords do not match, redirect to a page with an error message or provide feedback
            req.setAttribute("errorMessage", "Passwords do not match.");
            RequestDispatcher rd = req.getRequestDispatcher("registerpage.jsp"); // Redirect back to register page or error page
            rd.forward(req, resp);
        }
    }
}
