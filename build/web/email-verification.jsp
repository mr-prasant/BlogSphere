<%-- 
    Document   : email-verification
    Created on : 24 Sep, 2024, 1:41:05 AM
    Author     : Prasant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Email Verification</title>
        
        <link rel="stylesheet" href="./css/style.css">
        <link rel="stylesheet" href="./css/signin.css">

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
        integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <body>
        <%@include file="./elements/header.jsp" %>
        
        <% 
            String button = (String) request.getAttribute("button");
            String useremail = (String) request.getAttribute("useremail");
            
            if (button == null) button = "c";
            if (useremail == null) useremail = "";
        %>
        
        <main>

            <div class="main-title">
                Email Verification
            </div>

            <div class="card">
                <form action="EmailVerifyServlet" method="POST">
                    <div>Email</div>
                    <input type="email" name="useremail" value="<%= useremail%>" placeholder="Enter your email here" required>
                    <input type="hidden" name="button" value="<%= button%>">

                    <button type="submit">Send OTP</button>
                    
                    <div class="errormessage">
                        <%  String emailinvalid = (String) request.getAttribute("invalid");
                        
                            if (emailinvalid != null) {
                                out.print(emailinvalid);
                            }
                        %>
                    </div>
                </form>
            </div>
        </main>
    </body>
</html>
