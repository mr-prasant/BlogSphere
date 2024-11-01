/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.blogsphere.servlet;

import in.blogsphere.dao.BlogDAO;
import in.blogsphere.dao.UserDAO;
import in.blogsphere.pojo.Blog;
import in.blogsphere.util.CookieUtil;
import in.blogsphere.util.GenerateUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Prasant
 */
public class UploadBlog extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String title = String.valueOf(request.getParameter("title"));
        String content = request.getParameter("editor");
        content = content.trim();

        String userid = request.getParameter("userid");
        String blogid = request.getParameter("blogid");
        
        // checking for null values
        if (blogid == null || String.valueOf(blogid).equals("null") || blogid.isEmpty()) {
            blogid = null;
        }
        
        if (userid == null || String.valueOf(userid).equals("null") || userid.isEmpty()) {
            userid = null;
        }

        if (userid == null || !UserDAO.isValid(userid)) {
            System.out.println("not valid: " + userid);
            CookieUtil.build("blogsphere-post", "post-content.jsp");

            request.setAttribute("title", title);
            request.setAttribute("content", content);
            request.getRequestDispatcher("post-content.jsp").forward(request, response);
            return;
        }
        
        // getting the blog
        Blog blog = BlogDAO.get(blogid);

        // checking for create/update
        if (blog == null) { // create new blog
            blog = new Blog();
            blog.setId(GenerateUtil.generate('B'));
            blog.setEmail(userid);
            blog.setUsername(UserDAO.getUsername(userid));
            blog.setDate(new java.util.Date());
            blog.setMillisecond(blog.getDate().getTime());
        }

        blog.setTitle(title);
        blog.setContent(content);

        if (blogid != null) {
            if (BlogDAO.update(blog.getId(), blog)) {
                response.sendRedirect("post.jsp?bid=" + blog.getId());
                return;
            } else {

                request.setAttribute("title", title);
                request.setAttribute("content", content);
                request.setAttribute("postcontentstatus", "There is a problem occurred, please try again.");
                request.getRequestDispatcher("post-content.jsp").forward(request, response);
                return;
            }
        }

        if (BlogDAO.insert(blog)) {
            response.sendRedirect("post.jsp?bid=" + blog.getId());
        } else {
            request.setAttribute("title", title);
            request.setAttribute("content", content);
            request.setAttribute("postcontentstatus", "There is a problem occurred, please try again.");
            request.getRequestDispatcher("post-content.jsp").forward(request, response);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
