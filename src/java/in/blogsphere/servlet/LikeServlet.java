/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.blogsphere.servlet;

import in.blogsphere.dao.LikeDAO;
import in.blogsphere.dao.UserDAO;
import in.blogsphere.pojo.Like;
import in.blogsphere.util.CookieUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Prasant
 */
public class LikeServlet extends HttpServlet {

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

        String blogid = request.getParameter("blogID");
        String userid = request.getParameter("userID");
        String classname;

        if (userid == null || !UserDAO.isValid(userid)) {
            response.addCookie(CookieUtil.build("blogsphere-page", "post.jsp?bid=" + blogid));

            response.sendRedirect("signin.jsp");
            return;
        }
        
        Like like = new Like(blogid, userid);
        if (LikeDAO.isLiked(like)) {
            LikeDAO.delete(like);
            classname = "fa-regular";
        } else {
            LikeDAO.insert(like);
            classname = "fa-solid";
        }

        String jsonResponse = String.format("{\"likecount\": %d, \"classname\": \"%s\"}", LikeDAO.getCount(blogid), classname);

        response.setContentType("application/json");
        response.getWriter().write(jsonResponse);
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
