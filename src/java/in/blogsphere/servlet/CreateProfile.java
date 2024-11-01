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
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Prasant
 */
public class CreateProfile extends HttpServlet {

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

        String userid = request.getParameter("useremail");
        String button = request.getParameter("button-name");

        if (userid == null) {
            userid = (String) request.getSession().getAttribute("useremail");
        }

        if (userid == null) {
            request.setAttribute("invalid", "Something wrong, please try again!");
            request.getRequestDispatcher("signin.jsp").forward(request, response);
            return;
        }

        request.setAttribute("button-name", button);

        request.setAttribute("useremail", userid);
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");

        request.setAttribute("useremail", userid);
        request.setAttribute("username", name);
        request.setAttribute("userphone", phone);
        request.setAttribute("password1", password1);
        request.setAttribute("password2", password2);
        request.setAttribute("button-name", button);
        
        RequestDispatcher rd = request.getRequestDispatcher("profile-details.jsp");

        if (password1.length() < 4) {
            
            request.setAttribute("pwdstatus", "Password must be atleast 4 characters!");
            rd.forward(request, response);
            return;

        } else if (!password1.equals(password2)) {
            
            request.setAttribute("pwdstatus", "Password not matched!");
            rd.forward(request, response);
            return;

        } else if (phone.length() < 10 || phone.length() > 12) {
            
            request.setAttribute("pwdstatus", "Invaid Phone number!");
            rd.forward(request, response);
            return;
        }

        User user = new User(userid, name, Long.parseLong(phone));
        user.setPassword(password1);

        if (button.equals("Create")) {

            if (!UserDAO.insert(user)) {
                
                request.setAttribute("pwdstatus", "Sorry, our server is currently busy! Please try later");
                rd.forward(request, response);
                return;
            }

            // false ensures it doesn't create a new session if one doesn't exist
            HttpSession session = request.getSession(false);
            if (session != null) {
                // Invalidate the session to clear it
                session.invalidate();
            }

            response.sendRedirect("signin.jsp");
            
        } else {
            if (!UserDAO.update(userid, user)) {
                
                request.setAttribute("pwdstatus", "Sorry, there is some issue! Please try later");
                rd.forward(request, response);
                return;
            }

            response.sendRedirect("profile.jsp?uid=" + userid);
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
