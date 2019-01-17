<%--
  Created by IntelliJ IDEA.
  User: ryan.c.harper
  Date: 12/11/2018
  Time: 3:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-default">
    <div class="container-fluid">

        <div class="navbar-header">
            <a class="navbar-brand" href="/">trainingapp</a>
        </div>

        <ul class="nav navbar-nav navbar-right">

            <c:if test="${sessionScope.user == null}">
                <li><a href="/register">Register</a></li>
                <li><a href="/login">Login</a></li>
            </c:if>
            <c:if test="${sessionScope.user != null}">
                <li><a href="/posts">All posts</a></li>
                <li><a href="/posts/create">Create Post</a></li>
                <li><a href="/profile/">My Profile</a></li>
                <li><a href="/logout">Logout</a></li>
            </c:if>

        </ul>
    </div>
</nav>
