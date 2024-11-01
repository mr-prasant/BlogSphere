<%-- 
    Document   : profile
    Created on : 24 Sep, 2024, 2:00:59 AM
    Author     : Prasant
--%>

<%@page import="in.blogsphere.dao.LikeDAO"%>
<%@page import="in.blogsphere.pojo.Like"%>
<%@page import="in.blogsphere.util.FormatUtil"%>
<%@page import="in.blogsphere.pojo.Blog"%>
<%@page import="java.util.List"%>
<%@page import="in.blogsphere.pojo.User"%>
<%@page import="in.blogsphere.dao.ProfileDAO"%>
<%@page import="in.blogsphere.pojo.Profile"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Profile</title>

        <link rel="stylesheet" href="./css/style.css">
        <link rel="stylesheet" href="./css/profile.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
              integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />


    </head>

    <body>

        <%@include file="./elements/header.jsp" %>

        <%            userid = request.getParameter("uid");
            String cUserId = String.valueOf(CookieUtil.valueOf("blogsphere-user", request.getCookies()));

            Profile profile = ProfileDAO.get(userid);

            if (profile == null) {
                response.sendRedirect("error.jsp");
                return;
            }
            
            response.addCookie(CookieUtil.build("blogsphere-page", "profile.jsp?uid=" + userid));

            User user = profile.getUser();
            List<Blog> blogs = profile.getBlogs();
            int bsize = blogs.size();
            String suffix = (bsize > 1) ? "s" : "";
        %>

        <div class="userinfo">
            <div class="info-header">
                <div class="userimage"><i class="fa-solid fa-user"></i></div>
                <div class="username"> <%= user.getName()%> </div>
                <div class="userblogcount"><span style="font-weight: 500"> <%= bsize%> </span> Blog<%= suffix%> posted till now</div>
            </div>

            <div class="card userdetails">
                <div class="phone">Phone: <%= user.getContact()%></div>
                <div class="useremail"> <%= userid%> </div>

                 <% 
                     if (userid.equals(cUserId)) { %>

                <style>
                    .useremail {
/*                        margin-left: auto !important;
                        margin-right: 0 !important;*/
                        margin: 0 auto 0 50px;
                    }
                </style>
                <div class='editprofile'><a href='EditProfileServlet'> Edit Profile </a></div>
                <div class='signout'><a href='SignOutServlet'>Sign Out <i class='fa-solid fa-right-from-bracket'></i></a></div>

                <% } %> 
            </div>
        </div>

        <main>

            <%
                for (Blog blog : blogs) {
                    out.println("<div class='card' id='" + blog.getId() + "'>");

                    out.println("<div class='card-title'> " + blog.getTitle() + " </div>");
                    out.println("<div class='card-content'>");
                    out.println(FormatUtil.paragraph(blog.getContent()));
                    out.println("</div>");

                    out.println("<div class='card-footer'>");
                    out.println("<div class='card-footer-container'>");
                    out.println("<div class='card-views-comments'> <span> " + FormatUtil.count(blog.getViews()) + " views </span> <span> <a href='post.jsp?bid=" + blog.getId() + "#comments' class='admin'> " + FormatUtil.count(blog.getComments()) + " comments </a></span> </div>");
                    out.println("<div class='card-views likeButton' data-blogid='" + blog.getId() + "'> <span class='likecount'> " + FormatUtil.count(blog.getLikes()) + " </span> <i class='" + LikeDAO.getClassName(new Like(blog.getId(), userid)) + " fa-heart'></i></div></div>");
                    out.println("</div>");

                    out.println("</div>");
                }

            %>

        </main>
    </body>

    <script>

        Array.prototype.slice.call(Array.from(document.getElementsByClassName("card")), 1).forEach(element => {
            element.addEventListener("click", () => {
                var id = element.id;
                console.log("post: " + id);
                window.open('post.jsp?bid=' + id, '_self');
            });
        });

    </script>
    
    <script src="./js/script.js"></script>
</html>
