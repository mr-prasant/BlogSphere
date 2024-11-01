<%-- 
    Document   : create-post
    Created on : 7 Oct, 2024, 3:15:59 AM
    Author     : Prasant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Create Post</title>

        <link rel="stylesheet" href="./css/style.css">
        <link rel="stylesheet" href="./css/create-post.css">
        <script src="https://cdn.ckeditor.com/4.20.1/standard/ckeditor.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
              integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <body>
        
        <%@include file="./elements/header.jsp" %>
        
        <%
            if (userid == null) {
                CookieUtil.build("blogsphere-post", "post-content.jsp");
                response.sendRedirect("signin.jsp");
                return;
            }

            String title = (String) request.getAttribute("title");
            String content = (String) request.getAttribute("content");
            String blogid = (String) request.getAttribute("blogid");
            
            if (title == null || title.equals("null")) title = "";
            if (content == null || content.equals("null")) content = "";
            if (blogid == null || content.equals("null")) blogid = "";
        %>

        <main>
            <div class="card">
                <form id="myForm" action="UploadBlog" method="POST">
                    <input type="hidden" name="blogid" value="<%= blogid %>" readonly>
                    <input type="hidden" name="userid" value="<%= userid %>">
                    <input type="text" name="title" value="<%= title%>" placeholder="Write the title here" required>

                    <textarea name="editor" id="editor" required> <%= content%> </textarea>
                    <button type="submit">Share <i class="fa-solid fa-rocket"></i></button>
                    
                    <div class="errormessage">
                        <%  String signinMessage = (String) request.getAttribute("invalid");

                            if (signinMessage != null) {
                                out.print("Invalid Userid or password!");
                            }
                        %>
                    </div>
                </form>
            </div>
        </main>
    </body>

    <script>
        // Replace the textarea with CKEditor
        CKEDITOR.replace('editor');

        // Function to log CKEditor's content to the console
        // function logContent() {
        //     var editorData = CKEDITOR.instances.editor.getData(); // Get the HTML content
        //     console.log(editorData); // Print the content to the console
        // }
    </script>
</html>
