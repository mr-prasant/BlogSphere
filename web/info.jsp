<%-- 
    Document   : info.jsp
    Created on : 1 Nov, 2024, 6:32:14 PM
    Author     : Prasant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Post Desciption</title>

        <link rel="stylesheet" href="./css/style.css">

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
              integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />

    </head>

    <body>

        <%@include file="./elements/header.jsp" %>
        <%@include file="./elements/aside.jsp" %>

        <div class="card" style="margin: 28px 18vw;">
            <p>BlogSphere is a feature-rich blogging platform designed for users to seamlessly create, edit, and interact
                with blogs. Built with a blend of cutting-edge technologies, it allows users to sign in using both passwords
                and OTP verification, ensuring enhanced security through email validation powered by Jakarta Mail and
                Activation libraries.</p>

            <p>&nbsp;</p>

            <p><strong>Frontend:</strong><br />
                HTML/CSS/JS are the core for the user interface.<br />
                CKEditor enables rich-text blog creation, offering an easy-to-use editor.<br />
                Google Fonts and FontAwesome add visual flair to enhance user experience.</p>

            <p><br />
                <strong>Backend:</strong><br />
                Servlets and JSP handle the business logic, allowing users to add, edit blogs, manage profiles, and interact
                through comments.<br />
                Oracle Database stores data, with seamless integration via Apache as the server.
            </p>

            <p><br />
                <strong>Key Features:</strong><br />
                Like, comment, and view other user profiles and blogs.<br />
                Real-time updates like tracking blog views, likes, and comments.
            </p>

            <p><br />
                Developed using&nbsp;<strong>NetBeans IDE</strong>, BlogSphere ensures a robust and scalable blogging
                experience.</p>

            <p>&nbsp;</p>

            <p>Developed by <a href="http://www.linkedin.com/in/prasantcp"><strong>Prasant Chandra Poddar</strong></a></p>
        </div>

        <%@include file="./elements/footer.jsp" %>

    </body>

</html>
