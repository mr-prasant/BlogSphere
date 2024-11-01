/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.blogsphere.dao;

import in.blogsphere.pojo.Blog;
import in.blogsphere.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Prasant
 */
public class BlogDAO {

    public static Blog get(String id) {
        if (id == null) return null;
        
        Connection con = DBUtil.getConnection();

        PreparedStatement ps = null;
        ResultSet rs = null;
        Blog blog = null;

        try {
            ps = con.prepareStatement("SELECT * FROM BLOGS WHERE BLOGID = ?");
            ps.setString(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                
                blog = new Blog();
                
                blog.setId(rs.getString("BLOGID"));
                blog.setEmail(rs.getString("USEREMAIL"));
                blog.setTitle(rs.getString("TITLE"));
                blog.setContent(rs.getString("CONTENT"));
                blog.setCategory(rs.getString("CATEGORY"));
                blog.setUsername(rs.getString("USERNAME"));

                blog.setMillisecond(rs.getLong("MILLISECOND"));
                blog.setLikes(rs.getInt("LIKE_COUNT"));
                blog.setComments(rs.getInt("COMMENT_COUNT"));
                blog.setViews(rs.getInt("VIEWS"));

                java.sql.Date sqlDate = rs.getDate("TIME");
                blog.setDate(new java.util.Date(sqlDate.getTime()));

            }

        } catch (SQLException ex) {
            System.out.println("Error in BlogDAO: get()");
            ex.printStackTrace();
        }

        DBUtil.closeResultSet(rs);
        DBUtil.closeStatement(ps);

        return blog;
    }

    public static List<Blog> getAll() {
        Connection con = DBUtil.getConnection();

        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Blog> blogs = new ArrayList<Blog>();

        try {
            ps = con.prepareStatement("SELECT * FROM BLOGS ORDER BY TIME DESC");
            rs = ps.executeQuery();
            

            while (rs.next()) {
                Blog blog = new Blog();

                blog.setId(rs.getString("BLOGID"));
                blog.setEmail(rs.getString("USEREMAIL"));
                blog.setTitle(rs.getString("TITLE"));
                blog.setContent(rs.getString("CONTENT"));
                blog.setCategory(rs.getString("CATEGORY"));
                blog.setUsername(rs.getString("USERNAME"));

                blog.setMillisecond(rs.getLong("MILLISECOND"));
                blog.setLikes(rs.getInt("LIKE_COUNT"));
                blog.setComments(rs.getInt("COMMENT_COUNT"));
                blog.setViews(rs.getInt("VIEWS"));

                java.sql.Date sqlDate = rs.getDate("TIME");
                blog.setDate(new java.util.Date(sqlDate.getTime()));

                blogs.add(blog);
            }

        } catch (SQLException ex) {
            System.out.println("Error in BlogDAO: getAll()");
            ex.printStackTrace();
        }

        DBUtil.closeResultSet(rs);
        DBUtil.closeStatement(ps);

        return blogs;
    }
    
    public static List<Blog> getAllByFilter(String filter) {
        Connection con = DBUtil.getConnection();
        
        if (filter == null) filter = "TIME";

        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Blog> blogs = new ArrayList<Blog>();
        
        filter = filter.trim().toUpperCase();

        try {
            String query = "SELECT * FROM BLOGS ORDER BY " + filter + " DESC";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            

            while (rs.next()) {
                Blog blog = new Blog();

                blog.setId(rs.getString("BLOGID"));
                blog.setEmail(rs.getString("USEREMAIL"));
                blog.setTitle(rs.getString("TITLE"));
                blog.setContent(rs.getString("CONTENT"));
                blog.setCategory(rs.getString("CATEGORY"));
                blog.setUsername(rs.getString("USERNAME"));

                blog.setMillisecond(rs.getLong("MILLISECOND"));
                blog.setLikes(rs.getInt("LIKE_COUNT"));
                blog.setComments(rs.getInt("COMMENT_COUNT"));
                blog.setViews(rs.getInt("VIEWS"));

                java.sql.Date sqlDate = rs.getDate("TIME");
                blog.setDate(new java.util.Date(sqlDate.getTime()));

                blogs.add(blog);
            }

        } catch (SQLException ex) {
            System.out.println("Error in BlogDAO: getAll()");
            ex.printStackTrace();
        }

        DBUtil.closeResultSet(rs);
        DBUtil.closeStatement(ps);

        return blogs;
    }
    
    public static List<Blog> getBlogsContain(String target) {
        Connection con = DBUtil.getConnection();

        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Blog> blogs = new ArrayList<Blog>();;

        try {
            ps = con.prepareStatement("SELECT * FROM BLOGS WHERE LOWER(TITLE) LIKE LOWER(?) OR LOWER(CONTENT) LIKE LOWER(?)");
            ps.setString(1, "%" + target + "%");
            ps.setString(2, "%" + target + "%");
            
            rs = ps.executeQuery();
            
            while (rs.next()) {
                
                Blog blog = new Blog();

                blog.setId(rs.getString("BLOGID"));
                blog.setEmail(rs.getString("USEREMAIL"));
                blog.setTitle(rs.getString("TITLE"));
                blog.setContent(rs.getString("CONTENT"));
                blog.setCategory(rs.getString("CATEGORY"));
                blog.setUsername(rs.getString("USERNAME"));

                blog.setMillisecond(rs.getLong("MILLISECOND"));
                blog.setLikes(rs.getInt("LIKE_COUNT"));
                blog.setComments(rs.getInt("COMMENT_COUNT"));
                blog.setViews(rs.getInt("VIEWS"));

                java.sql.Date sqlDate = rs.getDate("TIME");
                blog.setDate(new java.util.Date(sqlDate.getTime()));

                blogs.add(blog);
            }

        } catch (SQLException ex) {
            System.out.println("Error in BlogDAO: getAll()");
            ex.printStackTrace();
        }

        DBUtil.closeResultSet(rs);
        DBUtil.closeStatement(ps);

        return blogs;
    }
    
    public static List<Blog> getBlogsOf(String uid) {
        Connection con = DBUtil.getConnection();

        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Blog> blogs = new ArrayList<>();

        try {
            ps = con.prepareStatement("SELECT * FROM BLOGS WHERE USEREMAIL = ? ORDER BY MILLISECOND DESC");
            ps.setString(1, uid);
            
            rs = ps.executeQuery();

            while (rs.next()) {
                Blog blog = new Blog();

                blog.setId(rs.getString("BLOGID"));
                blog.setEmail(rs.getString("USEREMAIL"));
                blog.setTitle(rs.getString("TITLE"));
                blog.setContent(rs.getString("CONTENT"));
                blog.setCategory(rs.getString("CATEGORY"));
                blog.setUsername(rs.getString("USERNAME"));

                blog.setMillisecond(rs.getLong("MILLISECOND"));
                blog.setLikes(rs.getInt("LIKE_COUNT"));
                blog.setComments(rs.getInt("COMMENT_COUNT"));
                blog.setViews(rs.getInt("VIEWS"));

                java.sql.Date sqlDate = rs.getDate("TIME");
                blog.setDate(new java.util.Date(sqlDate.getTime()));

                blogs.add(blog);
            }

        } catch (SQLException ex) {
            System.out.println("Error in BlogDAO: getAll()");
            ex.printStackTrace();
        }

        DBUtil.closeResultSet(rs);
        DBUtil.closeStatement(ps);

        return blogs;
    }

    public static boolean insert(Blog blog) {
        if (blog == null) {
            return false;
        }

        Connection con = DBUtil.getConnection();

        PreparedStatement ps = null;
        boolean flag = false;

        try {

            ps = con.prepareStatement("INSERT INTO BLOGS (BLOGID, USEREMAIL, TITLE, CONTENT, CATEGORY, USERNAME, LIKE_COUNT, COMMENT_COUNT, VIEWS, TIME, MILLISECOND) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            ps.setString(1, blog.getId());
            ps.setString(2, blog.getEmail());
            ps.setString(3, blog.getTitle());
            ps.setString(4, blog.getContent());
            ps.setString(5, "no category");
            ps.setString(6, blog.getUsername());
            
            ps.setInt(7, 0);
            ps.setInt(8, 0);
            ps.setInt(9, 0);

            if (blog.getDate() != null) {
                ps.setDate(10, new java.sql.Date(blog.getDate().getTime()));
            } else {
                ps.setDate(10, new java.sql.Date(System.currentTimeMillis()));
            }
            
            ps.setLong(11, blog.getMillisecond());

            flag = ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println("Error in BlogDAO: insert()");
            ex.printStackTrace();
        }

        DBUtil.closeStatement(ps);

        return flag;
    }

    public static boolean update(String id, Blog blog) {
        id = id.trim();
        
        if (blog == null || !id.equals(blog.getId())) {
            return false;
        }

        Connection con = DBUtil.getConnection();

        PreparedStatement ps = null;
        boolean flag = false;

        try {
            ps = con.prepareStatement("UPDATE BLOGS SET TITLE = ?, CONTENT = ? WHERE BLOGID = ?");

            ps.setString(1, blog.getTitle());
            ps.setString(2, blog.getContent());
            ps.setString(3, id);

            flag = ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println("Error in BlogDAO: update()");
            ex.printStackTrace();
        }

        DBUtil.closeStatement(ps);

        return flag;
    }

    public static boolean delete(String id) {
        Connection con = DBUtil.getConnection();

        PreparedStatement pscomments = null, pslikes = null, psblog = null;
        boolean flag = false;

        try {
            con.setAutoCommit(false);

            // delete comments
            pscomments = con.prepareStatement("DELETE FROM COMMENTS WHERE BLOGID = ?");
            pscomments.setString(1, id);

            pscomments.executeUpdate();

            // delete likes
            pslikes = con.prepareStatement("DELETE FROM LIKES WHERE BLOGID = ?");
            pslikes.setString(1, id);

            pslikes.executeUpdate();

            // delete blog
            psblog = con.prepareStatement("DELETE FROM BLOGS WHERE BLOGID = ?");
            psblog.setString(1, id);

            psblog.executeUpdate();

            con.commit();
            flag = true;

        } catch (SQLException ex) {
            System.out.println("Error in BlogDAO: delete()");
            ex.printStackTrace();
            flag = false;

            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        
        try {
            con.setAutoCommit(true);
        } catch (SQLException ex) {
            System.out.println("error in auto commit");
            ex.printStackTrace();
        }
        
        

        DBUtil.closeStatement(pscomments);
        DBUtil.closeStatement(pslikes);
        DBUtil.closeStatement(psblog);

        return flag;
    }
    
    public static void viewed(String bid) {        
        Connection con = DBUtil.getConnection();

        PreparedStatement ps = null;
        boolean flag = false;
        
        try {
            ps = con.prepareStatement("UPDATE BLOGS SET VIEWS = VIEWS + 1 WHERE BLOGID = ?");

            ps.setString(1, bid);

            flag = ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println("Error in BlogDAO: viewed()");
            ex.printStackTrace();
        }
        
        System.out.println(flag);

        DBUtil.closeStatement(ps);
    }
}
