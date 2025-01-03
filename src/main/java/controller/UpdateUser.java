package controller; 
 
import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoImplementation.UserDaoImplementation;
import model.User;

@WebServlet("/UpdateUser")
public class UpdateUser extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("uid"));
            String username = req.getParameter("username");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String mobile = req.getParameter("mobile");

            UserDaoImplementation udaoi = new UserDaoImplementation();
            User existingUser = udaoi.fetch_ID(id);

            if (existingUser != null) {
                // Retain immutable details
                LocalDateTime accCreated = existingUser.getAccCreated();
                LocalDateTime lastlogin = existingUser.getLastlogin();

                // Update user with new data
                User updatedUser = new User(id, username, email, password, mobile, accCreated, lastlogin);
                int status = udaoi.update(updatedUser);

                if (status == 1) {
                    req.getSession().setAttribute("LoggedInUser", updatedUser);
                    System.out.println("User updated: " + updatedUser);
                    resp.sendRedirect("userdetails.jsp");
                } else {
                    resp.sendRedirect("failure.jsp");
                }
            } else {
                resp.sendRedirect("error.jsp?message=User+not+found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("error.jsp?message=An+unexpected+error+occurred");
        }
    }
}
