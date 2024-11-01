/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.blogsphere.dao;

import in.blogsphere.pojo.Like;
import in.blogsphere.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Prasant
 */
public class LikeDAO {
    
    public static void insert(Like like) {
        if (like.getBlogID() == null || like.getUserID() == null) {
            return;
        }
        
        Connection con = DBUtil.getConnection();

        PreparedStatement ps = null;
        boolean flag = false;
        
        try {
            ps = con.prepareStatement("INSERT INTO LIKES (BLOGID, USERID) VALUES (?, ?)");
            ps.setString(1, like.getBlogID());
            ps.setString(2, like.getUserID());
           
            flag = ps.executeUpdate() > 0;
            
        } catch (SQLException ex) {
            System.out.println("Error in LikeDAO: insert()");
            ex.printStackTrace();
        }
        
        System.out.println("insert like " + flag);
        DBUtil.closeStatement(ps);
        
    }
    
    public static void delete(Like like) {
        if (like.getBlogID() == null || like.getUserID() == null) {
            return;
        }
        
        Connection con = DBUtil.getConnection();

        PreparedStatement ps = null;
        boolean flag = false;
        
        try {
            ps = con.prepareStatement("DELETE FROM LIKES WHERE BLOGID = ? AND USERID = ?");
            ps.setString(1, like.getBlogID());
            ps.setString(2, like.getUserID());
           
            flag = ps.executeUpdate() > 0;
            
        } catch (SQLException ex) {
            System.out.println("Error in LikeDAO: delete()");
            ex.printStackTrace();
        }
        
        System.out.println("delete like " + flag);
        DBUtil.closeStatement(ps);
    }
    
    public static boolean isLiked(Like like) {
        if (like.getBlogID() == null || like.getUserID() == null) {
            return false;
        }
        
        Connection con = DBUtil.getConnection();

        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean flag = false;
        
        try {
            ps = con.prepareStatement("SELECT 1 FROM LIKES WHERE BLOGID = ? AND USERID = ?");
            ps.setString(1, like.getBlogID());
            ps.setString(2, like.getUserID());
           
            rs = ps.executeQuery();
            
            flag = rs.next();            
            
        } catch (SQLException ex) {
            System.out.println("Error in commentDAO: insert()");
            ex.printStackTrace();
        }
        
        DBUtil.closeStatement(ps);
        DBUtil.closeResultSet(rs);
        
        return flag;
    }
    
    public static String getClassName(Like like) {
        return isLiked(like)? "fa-solid" : "fa-regular";
    }
    
    public static int getCount(String bid) {
        Connection con = DBUtil.getConnection();

        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        
        try {
            ps = con.prepareStatement("SELECT COUNT(*) AS COUNT FROM LIKES WHERE BLOGID = ?");
            ps.setString(1, bid);
           
            rs = ps.executeQuery();
            
            if (rs.next()) {
                count = rs.getInt("COUNT");
            }        
            
        } catch (SQLException ex) {
            System.out.println("Error in commentDAO: insert()");
            ex.printStackTrace();
        }
        
        DBUtil.closeStatement(ps);
        DBUtil.closeResultSet(rs);
        
        return count;
    }
}
