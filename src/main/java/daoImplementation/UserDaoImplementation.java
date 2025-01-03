package daoImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import daoInterface.UserDaoInterface;
import databaseutil.MyConnection;
import model.User;

// Insert, FetchAll, FetchSpecific, Update, Delete
public class UserDaoImplementation implements UserDaoInterface {
    private final String INSERT_QUERY = "INSERT INTO `user`(`username`, `email`, `password`, `mobile`, `accCreated`, `lastlogin`) VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";
    private final String FETCHALL_QUERY = "SELECT * FROM `user`";
    private final String FETCH_ID = "SELECT * FROM `user` WHERE `uid` = ?";
    private final String UPDATE_QUERY = "UPDATE `user` SET `username` = ?, `email` = ?, `password` = ?, `mobile` = ? WHERE `uid` = ?";
    private final String DELETE_QUERY = "DELETE FROM `user` WHERE `uid` = ?";
    private final String FETCH_EMAIL = "SELECT * FROM `user` WHERE `email` = ?";
    private final String UPDATE_LASTLOGIN = "UPDATE `user` SET `lastlogin` = CURRENT_TIMESTAMP WHERE `uid` = ?";

    private Connection con;

    // Constructor to establish DB connection
    public UserDaoImplementation() {
        try {
            con = MyConnection.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(User newUser) {
        try (PreparedStatement pstmt = con.prepareStatement(INSERT_QUERY)) {
            pstmt.setString(1, newUser.getUsername());
            pstmt.setString(2, newUser.getEmail());
            pstmt.setString(3, newUser.getPassword());
            pstmt.setString(4, newUser.getMobile());
            int status = pstmt.executeUpdate();
            System.out.println(status > 0 ? "Insertion successful" : "Insertion failed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> fetchAll() {
        List<User> userArrayList = new ArrayList<>();
        try (Statement stmt = con.createStatement(); ResultSet resultSet = stmt.executeQuery(FETCHALL_QUERY)) {
            userArrayList = extractUserList(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userArrayList;
    }

    private List<User> extractUserList(ResultSet resultSet) {
        List<User> userList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                userList.add(new User(
                        resultSet.getInt("uid"),
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("mobile"),
                        resultSet.getTimestamp("accCreated").toLocalDateTime(),
                        resultSet.getTimestamp("lastlogin").toLocalDateTime()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public User fetch_ID(int id) {
        User user = null;
        try (PreparedStatement pstmt = con.prepareStatement(FETCH_ID)) {
            pstmt.setInt(1, id);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                List<User> userList = extractUserList(resultSet);
                if (!userList.isEmpty()) {
                    user = userList.get(0);
                } else {
                    System.out.println("No user found for ID: " + id);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getUserEmail(String email) {
        User user = null;
        try (PreparedStatement pstmt = con.prepareStatement(FETCH_EMAIL)) {
            pstmt.setString(1, email);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                List<User> userList = extractUserList(resultSet);
                if (!userList.isEmpty()) {
                    user = userList.get(0);
                } else {
                    System.out.println("No records found for email: " + email);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public int update(User u) {
        int status = 0;
        try (PreparedStatement pstmt = con.prepareStatement(UPDATE_QUERY)) {
            pstmt.setString(1, u.getUsername());
            pstmt.setString(2, u.getEmail());
            pstmt.setString(3, u.getPassword());
            pstmt.setString(4, u.getMobile());
            pstmt.setInt(5, u.getUid());
            status = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public int delete(int id) {
        int status = 0;
        try (PreparedStatement pstmt = con.prepareStatement(DELETE_QUERY)) {
            pstmt.setInt(1, id);
            status = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public void updateLastLogin(int userId) {
        try (PreparedStatement pstmt = con.prepareStatement(UPDATE_LASTLOGIN)) {
            pstmt.setInt(1, userId);
            int status = pstmt.executeUpdate();
            System.out.println(status > 0 ? "Last login updated successfully" : "Failed to update last login");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

 
}
