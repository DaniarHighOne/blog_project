<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 08.08.2023
  Time: 9:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <%@include file="vendor/head.jsp"%>
</head>
<body>
  <%@include file="vendor/navbar.jsp"%>

  <div class="container col-6 mx-auto">
    <%
      String error = request.getParameter("error");
      if (error != null && error.equals("1")) {
    %>
    <div class="alert alert-danger alert-dismissible fade show" role="alert">
      <strong>Incorrect email or password! Try again!</strong>
      <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
    <%
      }
    %>
    <form action="/auth" method="post">
      <div class="mb-3">
        <label for="exampleInputEmail1" class="form-label">Email address</label>
        <input name="email" type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
        <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
      </div>
      <div class="mb-3">
        <label for="exampleInputPassword1" class="form-label">Password</label>
        <input name="password" type="password" class="form-control" id="exampleInputPassword1">
      </div>
      <button type="submit" class="btn btn-primary">SIGN IN</button>
      <a href="/sign-up" class="btn btn-success">SIGN UP</a>
    </form>
  </div>
</body>
</html>