<%-- 
    Document   : post
    Created on : 24 Sep, 2024, 1:49:40 AM
    Author     : Prasant
--%>

<%@page import="in.blogsphere.pojo.Like"%>
<%@page import="in.blogsphere.dao.LikeDAO"%>
<%@page import="in.blogsphere.dao.BlogDetailDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="in.blogsphere.pojo.Comment"%>
<%@page import="java.util.List"%>
<%@page import="in.blogsphere.pojo.BlogDetail"%>
<%@page import="in.blogsphere.util.FormatUtil"%>
<%@page import="in.blogsphere.pojo.Blog"%>
<%@page import="in.blogsphere.dao.BlogDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Post Desciption</title>

        <link rel="stylesheet" href="./css/style.css">
        <link rel="stylesheet" href="./css/post.css">

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
              integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>

    <body>

        <%@include file="./elements/header.jsp" %>
        <%@include file="./elements/aside.jsp" %>

        <%
            String bid = String.valueOf(request.getParameter("bid"));

            System.out.println("bid: " + bid);

            if (bid == null || bid.equals("null")) {
                response.sendRedirect("error.jsp");
                return;
            }

            BlogDetail bd = BlogDetailDAO.get(bid);

            if (bd == null) {
                response.sendRedirect("error.jsp");
                return;
            }

            Blog blog = bd.getBlog();
            List<Comment> comments = bd.getComments();

            BlogDAO.viewed(bid);
            blog.setViews(blog.getViews() + 1);


        %>

        <main class="pc-main">
            <div class="card">
                <a class="admin" href="profile.jsp?uid=<%= blog.getEmail()%>">
                    <div class="card-header">
                        <div class="userimage"><i class="fa-solid fa-user"></i></div>
                        <div class="user-details">
                            <%= blog.getUsername()%> <span>•</span> <%= FormatUtil.date(blog.getDate())%> <span>•</span> <span> <%= FormatUtil.time(blog.getMillisecond())%> </span> <span>read</span>
                        </div>

                    </div>
                </a>

                <% if (userid != null && userid.equals(blog.getEmail())) {%>

                <div class="options">
                    <a class="editbtn" href="EditBlogServlet?bid=<%= bid%>"><i class="fa-solid fa-pen"></i> Edit</a>
                    <a class="deletebtn" href="DeleteBlogServlet?bid=<%= bid%>"><i class="fa-solid fa-trash"></i> Delete</a>
                </div>

                <% }%>

                <div class="card-title">
                    <%= blog.getTitle()%>
                </div>

                <div class="card-content">
                    <%= blog.getContent()%>
                </div>

                <% System.out.println(blog.getContent());%>

                <div class="card-footer">
                    <div class="card-footer-container">
                        <div class="card-views-comments">
                            <span> <%= blog.getViews()%> views</span>
                            <span> <%= blog.getComments()%> comments</span>
                        </div>
                        <div class="card-views likeButton" data-blogid="<%= blog.getId()%>"> <span class="likecount"> <%= blog.getLikes()%> </span> <i class="<%= LikeDAO.getClassName(new Like(blog.getId(), userid))%> fa-heart"></i></div>
                    </div>
                </div>
            </div>

            <div class="card" id="comments">
                <div class="comment-title">
                    Comments
                </div>

                <div class="comment-write">
                    <form action="CommentServlet" method="POST">
                        <input type="hidden" name="userid" value="<%= userid%>" >
                        <input type="hidden" name="blogid" value="<%= blog.getId()%>" >
                        <textarea name="comment" placeholder="Write your comment here" required></textarea>
                        <button type="submit">Publish</button>
                    </form>
                </div>
            </div>

            <div class="card">

                <%
                    if (comments.isEmpty()) {
                        out.println("<h4> Be the first person to comment! </h4>");
                    } else {
                        for (Comment comment : comments) {
                            out.println("<div class='comment'>");
                            out.println("<a class='admin' href='profile.jsp?uid=" + comment.getUserID() + "'>");
                            out.println("<div class='card-header'>");
                            out.println("<div class='userimage'><i class='fa-solid fa-user'></i></div>");
                            out.println("<div class='user-details'>");
                            out.println(comment.getUsername() + "<span>•</span> " + FormatUtil.date(comment.getDate()) + " <span>•</span> <span> " + FormatUtil.time(comment.getMillisecond()) + " </span>");
                            out.println("</div> </div> </a>");
                            out.println("<div class='comment-content'>");
                            out.println(comment.getContent());
                            out.println("</div>");
                            out.println("</div>");
                        }
                    }
                %>

            </div>
        </main>

        <%@include file="./elements/footer.jsp" %>

    </body>

    <script src="./js/script.js"></script>
</html>
