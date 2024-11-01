<%-- 
    Document   : otp-verification
    Created on : 24 Sep, 2024, 1:43:32 AM
    Author     : Prasant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>OTP Verification</title>

        <link rel="stylesheet" href="./css/style.css">
        <link rel="stylesheet" href="./css/signin.css">

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
              integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <body>
        <%@include file="./elements/header.jsp" %>
        <%            String button = (String) request.getAttribute("button");
            if (button == null) {
                button = "c";
            }

            if (request.getSession().getAttribute("useremail") == null) {

                response.sendRedirect("signin.jsp");
                return;
            }
        %>

        <main>

            <div class="main-title">
                Email Verification
            </div>

            <div class="card">
                <form action="OTPVerifyServlet" method="post">
                    <div>OTP</div>
                    <input type="text" name="user-otp" placeholder="Enter the OTP here" required>
                    <input type="hidden" name="button" value="<%= button%>">

                    <button type="submit">Verify OTP</button>

                    <div class="errormessage">
                        <%  String otpstatus = (String) request.getAttribute("otpstatus");

                            if (otpstatus != null) {
                                out.print(otpstatus);
                            }
                        %>
                    </div>
                </form>
            </div>

            <div class="newuser">
                Not got the OTP yet? <a href="EmailVerifyServlet">Resend OTP</a>
            </div>

        </main>
    </body>
</html>
