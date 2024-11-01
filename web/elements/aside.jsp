<%-- 
    Document   : aside
    Created on : 21 Sep, 2024, 8:15:20 PM
    Author     : Prasant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<aside>
    <div class="category">
        <a href="index.jsp">All Blogs</a>
        <a href="index.jsp?cat=re">Recent</a>
        <a href="index.jsp?cat=mv">Most Viewed</a>
        <a href="index.jsp?cat=ml">Most Liked</a>
    </div>
    
    <% String search = String.valueOf(request.getParameter("search")); %>

    <div class="search">
        <form action="index.jsp">
            <input type="text" name="search" class="searchbox" value="<%= (search.equals("null")? "" : search) %>" required>
            <button type="submit"><i class="fa-solid fa-magnifying-glass"></i></button>
        </form>
    </div>
</aside>
