<%-- 
    Document   : header
    Created on : 21 Sep, 2024, 8:12:51 PM
    Author     : Prasant
--%>

<%@page import="in.blogsphere.dao.UserDAO"%>
<%@page import="in.blogsphere.util.CookieUtil"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>


<%
    String userid = CookieUtil.valueOf("blogsphere-user", request.getCookies());
%>

<header>
    <div class="title">
        <div>BLOG SPHERE</div>
        <p>Unleash Your Voice, Share Your World</p>
    </div>
    <nav>
        <div class="buttons">
            <a href="index.jsp"><i class="fa-solid fa-house"></i></a>
            <a href="post-content.jsp"> <i class="fa-regular fa-square-plus"></i> </a>
            <a href="info.jsp"><i class="fa-solid fa-circle-info"></i></a>

            <a href="#" class="signin"></a>


            <%
                if (userid == null) {
                    out.println("<a class='signin' href='./signin.jsp'>SIGN IN?</a>");
                } else {
                    out.println("<a href='profile.jsp?uid=" + userid + "'> <i class='fa-solid fa-user'></i> : Hi, " + UserDAO.getUsername(userid).split(" ")[0] + "! </a>");
                }
            %>

        </div>
        <div class="icons">
            <a href="https://www.facebook.com/share/hj8pgVm88z9MtuhX/"><i class="fa-brands fa-facebook-f"></i></a>
            <a href="https://x.com/Prasant2507"><i class="fa-brands fa-twitter"></i></a>
            <a href="https://www.linkedin.com/in/prasantcp"><i class="fa-brands fa-linkedin-in"></i></a>
        </div>
    </nav>

    <script>
        $(document).ready(() => {
            $('.signin').on('click', function () {
                document.cookie = "blogsphere-page=index.jsp; path=/";
                window.location.href = "signin.html";
            });
        });
    </script>
</header>
