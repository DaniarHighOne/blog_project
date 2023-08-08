<%@ page import="models.Blog" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 03.08.2023
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="vendor/head.jsp"%>
</head>
<body>
<%@include file="vendor/navbar.jsp"%>
<table class="table table-striped">
    <thead>
    <th>ID</th>
    <th>TITLE</th>
    <th>CONTENT</th>
    <th>POST DATE</th>
    <th>DETAILS</th>
    </thead>
    <tbody>
    <%
        List<Blog> blogs = (List<Blog>) request.getAttribute("novosti");
        for (Blog blog : blogs) {
    %>
    <tr>
        <td><%=blog.getId()%></td>
        <td><%=blog.getTitle()%></td>
        <td><%=blog.getContent()%></td>
        <td><%=blog.getPostDate()%></td>
        <td><a href="/blog-details?id=<%=blog.getId()%>" class="btn btn-dark">DETAILS</a></td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
</body>
</html>