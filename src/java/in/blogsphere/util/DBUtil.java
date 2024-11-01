/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.blogsphere.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Prasant
 */
public class DBUtil {
    private static Connection con;
    
    public static void openConnection(String url, String user, String password) {
        if (con != null) return;
        
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("BlogSphere Connection Build");
        } catch (SQLException ex) {
            System.out.println("Error in opening Connection!");
            ex.printStackTrace();
        }
    }
    
    public static void closeConnection() {
        if (con == null) return;
        
        try {
            con.close();
        } catch (SQLException ex) {
            System.out.println("Error in closing Connection!");
            ex.printStackTrace();
        }
    }
    
    public static Connection getConnection() {
        return con;
    }
    
    public static void closeResultSet(ResultSet rs) {
        if (rs == null) return;
        
        try {
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error in closing ResultSet!");
            ex.printStackTrace();
        }
    }
    
    public static void closeStatement(Statement st) {
        if (st == null) return;
        
        try {
            st.close();
        } catch (SQLException ex) {
            System.out.println("Error in closing Statement!");
            ex.printStackTrace();
        }
    }
}
