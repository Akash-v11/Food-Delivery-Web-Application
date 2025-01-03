package controller; 

 
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoImplementation.UserDaoImplementation;
import model.User;
 
 
@WebServlet("/FetchOne")
public class FetchOne extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("uid"));
            UserDaoImplementation udaoi = new UserDaoImplementation();
            User user = udaoi.fetch_ID(id);

            if (user != null) {
                req.getSession().setAttribute("LoggedInUser", user);
                resp.sendRedirect("editUser.jsp");
            } else {
                resp.sendRedirect("error.jsp?message=User+not+found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("error.jsp?message=An+unexpected+error+occurred");
        }
    }
}

