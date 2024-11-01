/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.blogsphere.dao;

import in.blogsphere.pojo.User;
import in.blogsphere.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Prasant
 */
public class UserDAO {

    public static boolean isMember(String email, String password) {
        if (email == null || password == null) return false;
        
        Connection con = DBUtil.getConnection();

        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean flag = false;
        
        email = email.trim().toLowerCase();

        try {
            ps = con.prepareStatement("SELECT 1 FROM USERS WHERE USEREMAIL = ? AND PASSWORD = ?");
            ps.setString(1, email);
            ps.setString(2, password);

            rs = ps.executeQuery();

            flag = rs.next();

        } catch (SQLException ex) {
            System.out.println("Error in userDAO: isMember()");
            ex.printStackTrace();
        }

        DBUtil.closeResultSet(rs);
        DBUtil.closeStatement(ps);

        return flag;
    }

    public static boolean isValid(String email) {
        if (email == null) return false;
        
        Connection con = DBUtil.getConnection();

        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean flag = false;
        
        email = email.trim().toLowerCase();

        try {
            ps = con.prepareStatement("SELECT 1 FROM USERS WHERE USEREMAIL = ?");
            ps.setString(1, email);

            rs = ps.executeQuery();

            flag = rs.next();

        } catch (SQLException ex) {
            System.out.println("Error in userDAO: isValid()");
            ex.printStackTrace();
        }

        DBUtil.closeResultSet(rs);
        DBUtil.closeStatement(ps);

        return flag;
    }

    public static String getUsername(String email) {
        Connection con = DBUtil.getConnection();

        PreparedStatement ps = null;
        ResultSet rs = null;
        String username = "Invalid User";
        
        email = email.trim().toLowerCase();

        try {
            ps = con.prepareStatement("SELECT USERNAME FROM USERS WHERE USEREMAIL = ?");
            ps.setString(1, email);

            rs = ps.executeQuery();

            if (rs.next()) {
                username = rs.getString("USERNAME");
            }

        } catch (SQLException ex) {
            System.out.println("Error in userDAO: getUsername()");
            ex.printStackTrace();
        }

        DBUtil.closeResultSet(rs);
        DBUtil.closeStatement(ps);

        return username;
    }

    public static User get(String email) {
        Connection con = DBUtil.getConnection();

        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        
        email = email.trim().toLowerCase();

        try {
            ps = con.prepareStatement("SELECT * FROM USERS WHERE USEREMAIL = ?");
            ps.setString(1, email);

            rs = ps.executeQuery();

            if (rs.next()) {
                user = new User(rs.getString("USEREMAIL"), rs.getString("USERNAME"), rs.getLong("CONTACT"));
                user.setPassword(rs.getString("PASSWORD"));
            }

        } catch (SQLException ex) {
            System.out.println("Error in userDAO: get()");
            ex.printStackTrace();
        }

        DBUtil.closeResultSet(rs);
        DBUtil.closeStatement(ps);

        return user;
    }

    public static boolean insert(User user) {
        if (user == null) {
            return false;
        }

        Connection con = DBUtil.getConnection();

        PreparedStatement ps = null;
        boolean flag = false;
        
        user.setEmail(user.getEmail().trim().toLowerCase());

        try {
            ps = con.prepareStatement("INSERT INTO USERS (USEREMAIL, USERNAME, CONTACT, PASSWORD) VALUES (?, ?, ?, ?)");

            ps.setString(1, user.getEmail());
            ps.setString(2, user.getName());
            ps.setLong(3, user.getContact());
            ps.setString(4, user.getPassword());

            flag = ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println("Error in userDAO: insert()");
            ex.printStackTrace();
        }

        DBUtil.closeStatement(ps);

        return flag;
    }

    public static boolean update(String email, User user) {
        if (user == null || !email.equalsIgnoreCase(user.getEmail())) {
            return false;
        }

        Connection con = DBUtil.getConnection();

        PreparedStatement ps = null;
        boolean flag = false;
        
        user.setEmail(user.getEmail().trim().toLowerCase());

        try {
            ps = con.prepareStatement("UPDATE USERS SET USERNAME = ?, CONTACT = ?, PASSWORD = ? WHERE USEREMAIL = ?");

            ps.setString(1, user.getName());
            ps.setLong(2, user.getContact());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getEmail());

            flag = ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println("Error in userDAO: update()");
            ex.printStackTrace();
        }

        DBUtil.closeStatement(ps);

        return flag;
    }

    public static boolean updatePassword(String email, String password) {
        Connection con = DBUtil.getConnection();

        PreparedStatement ps = null;
        boolean flag = false;

        try {
            ps = con.prepareStatement("UPDATE USERS SET PASSWORD = ? WHERE USEREMAIL = ?");

            ps.setString(1, password);
            ps.setString(2, email);

            flag = ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println("Error in userDAO: updatePassword()");
            ex.printStackTrace();
        }

        DBUtil.closeStatement(ps);

        return flag;
    }

    public static boolean delete(String email) {
        Connection con = DBUtil.getConnection();

        PreparedStatement ps = null;
        boolean flag = false;
        
        email = email.trim().toLowerCase();

        try {
            ps = con.prepareStatement("DELETE FROM users WHERE USEREMAIL = ?");

            ps.setString(1, email);

            flag = ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println("Error in userDAO: delete()");
            ex.printStackTrace();
        }

        DBUtil.closeStatement(ps);

        return flag;
    }

}
