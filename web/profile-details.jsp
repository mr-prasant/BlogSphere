<%-- 
    Document   : profile-details
    Created on : 24 Sep, 2024, 1:46:14 AM
    Author     : Prasant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Create Profile</title>

        <link rel="stylesheet" href="./css/style.css">
        <link rel="stylesheet" href="./css/signin.css">

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
              integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>

    <body>
        <%@include file="./elements/header.jsp" %>

        <%            userid = (String) request.getAttribute("useremail");
            String name = (String) request.getAttribute("username");
            String phone = (String) request.getAttribute("userphone");
            String password1 = (String) request.getAttribute("password1");
            String password2 = (String) request.getAttribute("password2");

            String button = (String) request.getAttribute("button-name");

            if (name == null) {
                name = "";
            }
            if (phone == null) {
                phone = "";
            }
            if (password1 == null) {
                password1 = "";
            }
            if (password2 == null) {
                password2 = "";
            }
            if (userid == null) {
                userid = (String) request.getSession().getAttribute("useremail");
            }

            if (button == null) {
                button = "Create";
            }
            
            if (userid == null) {
                response.sendRedirect("signin.jsp");
                return;
            }

        %>

        <main>
            <div class="card">
                <form action="CreateProfile" method="post">
                    <div>Name</div>
                    <input type="text" name="name" placeholder="Enter your name here" value="<%= name%>" required> <br>

                    <div>Phone</div>
                    <input type="number" name="phone" placeholder="Enter your phone number here" value="<%= phone%>" required> <br>

                    <div>Create Password</div>
                    <input type="password" name="password1" placeholder="Create your password" value="<%= password1%>" required> <br>

                    <div>Verify Password</div>
                    <input type="text" name="password2" placeholder="Re-enter your password" value="<%= password2%>" required> <br>

                    <input type="text" style="outline: none; opacity: 0.5" name="useremail" value="<%= userid%>" readonly>
                    <input type="hidden" name="button-name" value="<%= button%>"> 
                    <div class="errormessage">
                        <%  String pwdstatus = (String) request.getAttribute("pwdstatus");

                            if (pwdstatus != null) {
                                out.print(pwdstatus);
                            }
                        %>
                    </div>

                    <button type="submit"><%= button%> Profile</button>
                </form>
            </div>
        </main>
    </body>
</html>
