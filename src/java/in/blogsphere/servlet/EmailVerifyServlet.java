/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.blogsphere.servlet;

import in.blogsphere.dao.UserDAO;
import in.blogsphere.util.GenerateUtil;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.io.IOException;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Prasant
 */
public class EmailVerifyServlet extends HttpServlet {

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
        String button = request.getParameter("button");
        RequestDispatcher rd = request.getRequestDispatcher("email-verification.jsp");
        
        if (userid == null) {
            request.setAttribute("invalid", "Something wrong, please try again!");
            request.getRequestDispatcher("email-verification.jsp").forward(request, response);
            return;
        }
        
        boolean isPresent = UserDAO.isValid(userid);
        request.setAttribute("useremail", userid);
        request.setAttribute("button", button);

        if (button.equals("f")) {
            if (!isPresent){
                
                request.setAttribute("invalid", "Email doesn't exist! Wanna Register?");
                rd.forward(request, response);
                return;
            }
        } else {
            if (isPresent){
                
                request.setAttribute("invalid", "Email exist, wanna sign in?");
                rd.forward(request, response);
                return;
            }
        }

        String otp = GenerateUtil.generateOTP();
        userid = userid.trim().toLowerCase();

        final String fromEmail = "prasantcp7@gmail.com"; // your email
        final String password = "ippz mbsx agkz imwk"; // your email password

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            // Creating the email message

            String subject = "Your BlogSphere OTP Code";
            String content = "Dear User,\n\n"
                    + "Your one-time password (OTP) for BlogSphere is: " + otp + "\n\n"
                    + "Please use this OTP to complete your action. The OTP is valid for the next 10 minutes.\n\n"
                    + "If you did not request this, please ignore this email.\n\n"
                    + "Thank you for using BlogSphere!\n"
                    + "Best regards,\n"
                    + "The BlogSphere Team";

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userid));
            message.setSubject(subject);
            message.setText(content);

            try {
                Transport.send(message);
                System.out.println("Email sent successfully.");
                
                request.getSession().setAttribute("otp", otp);
                request.getSession().setAttribute("useremail", userid);
                request.setAttribute("otpstatus", "OTP sent to your email!");
                request.getRequestDispatcher("otp-verification.jsp").forward(request, response);
                
            } catch (Exception e) {
                System.out.println("Error in sending the mail: " + e.getMessage());
                e.printStackTrace();
                
                request.setAttribute("invalid", "Not able to process your request, please try again!");
                rd.forward(request, response);
            }

        } catch (MessagingException e) {
            System.out.println("Error in sending the mail");
            e.printStackTrace();
            
            request.setAttribute("invalid", "Something wrong, please try again!");
            rd.forward(request, response);
        }

        System.out.println("valid: " + userid);

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
