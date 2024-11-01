package in.blogsphere.servlet;

import in.blogsphere.dao.UserDAO;
import in.blogsphere.util.CookieUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Prasant
 */
public class SignInServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String useremail = request.getParameter("useremail");
        String password = request.getParameter("password");

        String page = CookieUtil.valueOf("blogsphere-page", request.getCookies());

        if (UserDAO.isMember(useremail, password)) {

            response.addCookie(CookieUtil.build("blogsphere-user", useremail, 2500000));

            if (page == null) {
                page = "index.jsp";
            }
            
            response.sendRedirect(page);
            
        } else {
            request.setAttribute("invalid", "invalid");
            request.setAttribute("useremail", useremail);
            request.setAttribute("password", password);
            request.getRequestDispatcher("signin.jsp").forward(request, response);
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
