package controller; 
   
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import databaseutil.MyConnection;


@WebServlet("/Register")
public class Register extends HttpServlet 
{
	private PreparedStatement pstmt;
    private int status;
    private Connection con;

    private static final String INSERT_QUERY = "INSERT INTO `user`(`username`, `email`, `password`, `mobile`, `accCreated`, `lastlogin`) VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";
    public static final String Check_Existing_Email_Query = "SELECT count(*) from `user` where email = ?";

    @Override
    public void init() throws ServletException {
         con = MyConnection.connect();
     }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        try
        {
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String mobile = req.getParameter("mobile");
 
            // Check if email already exists
            pstmt = con.prepareStatement(Check_Existing_Email_Query);
            pstmt.setString(1, email);
			ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) > 0) 
            {
            	System.out.println("Email already exists");
            	resp.sendRedirect("emailExists.jsp"); // Redirect to a page informing that the email already exists
                return;
            }

            pstmt = con.prepareStatement(INSERT_QUERY);
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.setString(4, mobile);
 
            status = pstmt.executeUpdate();

            if (status != 0) {
                System.out.println("Registration successful!");
                resp.sendRedirect("success.jsp");
            } else {
                System.out.println("Registration failed.");
                resp.sendRedirect("failure.jsp");
            }


        } 
        catch (SQLException e) {
            e.printStackTrace();
            resp.sendRedirect("failure.jsp");
        }  
    }

    @Override
    public void destroy() {
    	 MyConnection.disConnect(con, pstmt);
    }
} 
