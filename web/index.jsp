<%-- 
    Document   : index.jsp
    Created on : 21 Sep, 2024, 7:49:04 PM
    Author     : Prasant
--%>

<%@page import="in.blogsphere.pojo.Like"%>
<%@page import="in.blogsphere.dao.LikeDAO"%>
<%@page import="in.blogsphere.util.CookieUtil"%>
<%@page import="in.blogsphere.util.FormatUtil"%>
<%@page import="in.blogsphere.dao.BlogDAO"%>
<%@page import="in.blogsphere.pojo.Blog"%>
<%@page import="in.blogsphere.util.DBUtil"%>
<%@page import="java.util.List, java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>

        <link rel="stylesheet" href="./css/style.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>
    <body>

        <%@include file="./elements/header.jsp" %>
        <%@include file="./elements/aside.jsp" %>

        <main>

            <%
                response.addCookie(CookieUtil.build("blogsphere-page", "index.jsp"));
                
                List<Blog> blogs;
                
                if (!search.equals("null")) {
                    blogs = BlogDAO.getBlogsContain(search);
                } else {
                    String cat = String.valueOf(request.getParameter("cat")).trim().toLowerCase();
                    if (cat.equals("mv")) {
                        blogs = BlogDAO.getAllByFilter("VIEWS");
                    } else if (cat.equals("ml")) {
                        blogs = BlogDAO.getAllByFilter("LIKE_COUNT");
                    } else if (cat.equals("re")) {
                        blogs = BlogDAO.getAllByFilter("TIME");
                    } else {
                        blogs = BlogDAO.getAll();
                    }
                }

                if (blogs == null || blogs.isEmpty()) {
                    out.println("<h2> No data found related to '" + search + "'</h2>");
                } else {
                    for (Blog blog : blogs) {
                        out.println("<div class='card' id='" + blog.getId() + "'>");

                        out.println("<a class='admin' href='./profile.jsp?uid=" + blog.getEmail() + "'>");
                        out.println("<div class='card-header'>");
                        out.println("<div class='userimage'><i class='fa-solid fa-user'></i></div>");
                        out.println("<div class='user-details'> " + blog.getUsername() + " <br /> " + FormatUtil.date(blog.getDate()) + " <span>•</span> <span> " + FormatUtil.time(blog.getMillisecond()) + " </span></div>");
                        out.println("</div></a>");

                        out.println("<div class='card-title'> " + blog.getTitle() + " </div>");
                        out.println("<div class='card-content'>");
                        out.println(FormatUtil.paragraph(blog.getContent()));
                        out.println("</div>");

                        out.println("<div class='card-footer'>");
                        out.println("<div class='card-footer-container'>");
                        out.println("<div class='card-views-comments'> <span> " + FormatUtil.count(blog.getViews()) + " views </span> <span> <a href='post.jsp?bid=" + blog.getId() + "#comments' class='admin'> " + FormatUtil.count(blog.getComments()) + " comments </a></span> </div>");
                        out.println("<div class='card-views likeButton' data-blogid='" + blog.getId() + "'> <span class='likecount'> " + FormatUtil.count(blog.getLikes()) + " </span> <i class='" + LikeDAO.getClassName(new Like(blog.getId(), userid)) + " fa-heart'></i></div></div>");
                        out.println("</div>");
//                        out.println("<h1> " + blog.getId() + " " + userid + " </h1>");
                        out.println("</div>");
                    }
                }

            %>

        </main>

        <%@include file="./elements/footer.jsp" %>

    </body>

    <script>
        Array.from(document.getElementsByClassName("card")).forEach(element => {
            element.addEventListener("click", () => {
                var id = element.id;
                console.log("post: " + id);
                window.open('post.jsp?bid=' + id, '_self');
                document.cookie = "blogsphere-page=post.jsp?bid=" + id + "; path=/";
            });
        });
    </script>

    <script src="./js/script.js"></script>

</html>
