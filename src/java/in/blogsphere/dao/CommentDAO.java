/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.blogsphere.dao;

import in.blogsphere.pojo.Comment;
import in.blogsphere.util.DBUtil;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Prasant
 */
public class CommentDAO {

    public static List<Comment> getAll(String bid) {
        Connection con = DBUtil.getConnection();

        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Comment> comments =  new ArrayList<Comment>();

        try {
            ps = con.prepareStatement("SELECT * FROM COMMENTS WHERE BLOGID = ? ORDER BY TIME DESC");
            ps.setString(1, bid);
            rs = ps.executeQuery();

            while (rs.next()) {
                Comment comment = new Comment();
                comment.setBlogID(rs.getString("BLOGID"));
                comment.setUserID(rs.getString("USERID"));
                comment.setContent(rs.getString("CONTENT"));
                comment.setMillisecond(rs.getLong("MILLISECOND"));

                Date time = rs.getDate("TIME");
                if (time != null) {
                    comment.setDate(new java.util.Date(time.getTime()));
                }
                
                comment.setUsername(UserDAO.getUsername(comment.getUserID()));
                
                comments.add(comment);
            }

        } catch (SQLException ex) {
            System.out.println("Error in commentDAO: getAll()");
            ex.printStackTrace();
        }

        DBUtil.closeResultSet(rs);
        DBUtil.closeStatement(ps);

        return comments;
    }
    
    public static boolean insert(Comment comment) {
        if (comment == null) return false;
        
        Connection con = DBUtil.getConnection();

        PreparedStatement ps = null;
        boolean flag = false;

        try {
            ps = con.prepareStatement("INSERT INTO COMMENTS (BLOGID, USERID, CONTENT, TIME, MILLISECOND) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, comment.getBlogID());
            ps.setString(2, comment.getUserID());
            ps.setString(3, comment.getContent());
            
            if (comment.getDate() != null) {
                ps.setDate(4, new java.sql.Date(comment.getDate().getTime()));
                ps.setLong(5, comment.getMillisecond());
            } else {
                ps.setDate(4, new java.sql.Date(System.currentTimeMillis()));
                ps.setLong(5, System.currentTimeMillis());
            }
            
            flag = ps.executeUpdate() > 0;
            
        } catch (SQLException ex) {
            System.out.println("Error in commentDAO: insert()");
            ex.printStackTrace();
        }

        DBUtil.closeStatement(ps);

        return flag;
    }

}
