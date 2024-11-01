<%-- 
    Document   : error
    Created on : 15 Oct, 2024, 5:04:22 AM
    Author     : Prasant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign In</title>

        <link rel="stylesheet" href="./css/style.css">
        <link rel="stylesheet" href="./css/error.css">

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
              integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <body>

        <%@include file="./elements/header.jsp" %>
        <%@include file="./elements/aside.jsp" %>

        <div class="imgcontainer">
            <img class="errorimg" src="./img/datanotfound.jpg" alt="Ops, No data found!">
            <h3> No Data Found :( </h3>
        </div>
    </body>
</html>
