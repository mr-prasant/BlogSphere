/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.blogsphere.servlet;

import in.blogsphere.dao.UserDAO;
import in.blogsphere.pojo.User;
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
public class EditProfileServlet extends HttpServlet {

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

        String userid = (String) CookieUtil.valueOf("blogsphere-user", request.getCookies());
        
        if (userid == null) {
            response.sendRedirect("signin.jsp");
            return;
        }
        
        User user = UserDAO.get(userid);

        if (user == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        request.setAttribute("button-name", "Update");

        request.setAttribute("useremail", userid);
        request.setAttribute("username", user.getName());
        request.setAttribute("password1", user.getPassword());
        request.setAttribute("password2", user.getPassword());
        request.setAttribute("userphone", String.valueOf(user.getContact()));
        request.getRequestDispatcher("profile-details.jsp").forward(request, response);

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