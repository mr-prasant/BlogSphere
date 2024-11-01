<%-- 
    Document   : signin
    Created on : 24 Sep, 2024, 1:30:14 AM
    Author     : Prasant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign In</title>

        <link rel="stylesheet" href="./css/style.css">
        <link rel="stylesheet" href="./css/signin.css">

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
              integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <body>
        <%@include file="./elements/header.jsp" %>
        <%            String useremail = (String) request.getAttribute("useremail");
            String password = (String) request.getAttribute("password");

            if (useremail == null) {
                useremail = "";
            }
            if (password == null) {
                password = "";
            }
        %>

        <main>
            <div class="main-title">
                Sign In
            </div>

            <div class="card">
                <form action="SignInServlet" method="post">
                    <div>Email</div>
                    <input type="email" name="useremail" placeholder="Enter your email here" value="<%= useremail%>" required>

                    <div class="pwd">Password</div>
                    <input type="password" name="password" placeholder="Enter your password here" value="<%= useremail%>" required>

                    <button type="submit">Sign In</button>

                    <div class="errormessage">
                        <%  String signinMessage = (String) request.getAttribute("invalid");

                            if (signinMessage != null) {
                                out.print("Invalid Userid or password!");
                            }
                        %>
                    </div>
                </form>
            </div>

            <div class="newuser">
                Are you a new user? <a href="./email-verification.jsp">Register here</a>
            </div>

            <div class="newuser">
                Sign in with OTP? <a href="FgtPasswordServlet">Click here</a>
            </div>
        </main>
    </body>
</html>
