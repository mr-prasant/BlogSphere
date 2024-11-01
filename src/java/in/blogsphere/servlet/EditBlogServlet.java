/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.blogsphere.servlet;

import in.blogsphere.dao.BlogDAO;
import in.blogsphere.pojo.Blog;
import in.blogsphere.util.CookieUtil;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Prasant
 */
public class EditBlogServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String blogid = request.getParameter("bid");
        String userid = CookieUtil.valueOf("blogsphere-user", request.getCookies());
        
        if (blogid == null) {
            response.sendRedirect("index.jsp");
            return;
        }
        
        if (userid == null) {
            response.sendRedirect("post.jsp?bid=" + blogid);
            return;
        } 
        
        Blog blog = BlogDAO.get(blogid);
        if (blog == null || !blog.getEmail().equals(userid)) {
            response.sendRedirect("post.jsp?bid=" + blogid);
            return;
        }
        
        request.setAttribute("title", blog.getTitle());
        request.setAttribute("content", blog.getContent());
        request.setAttribute("blogid", blogid);
        request.getRequestDispatcher("post-content.jsp").forward(request, response);
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
