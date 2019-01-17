<%--
  Created by IntelliJ IDEA.
  User: ryan.c.harper
  Date: 12/11/2018
  Time: 3:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <jsp:include page="/WEB-INF/views/partials/header.jsp">
        <jsp:param name="title" value="Please Log In" />
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/views/partials/navbar.jsp" />

<div class="container">
    <h1>Please Log In</h1>
    <form:form action="/login" method="POST" modelAttribute="user">

        <div class="form-group">
            <label for="username">Username</label>
            <input id="username" name="username" class="form-control" type="text" value="${user.username}">
        </div>
        <c:if test="${usernameIsEmpty}">
            <div class="alert alert-danger alert-dismissible" role="alert">
                <strong>Please enter a username</strong>
            </div>
        </c:if>
        <c:if test="${userNotExist}">
            <div class="alert alert-danger alert-dismissible" role="alert">
                <strong>That username doesn't exist. Have you <a href="/register">registered?</a></strong>
            </div>
        </c:if>

        <div class="form-group">
            <label for="password">Password</label>
            <input id="password" name="password" class="form-control" type="password" value="${user.password}">
        </div>
        <c:if test="${passwordIsEmpty}">
            <div class="alert alert-danger alert-dismissible" role="alert">
                <strong>Please enter a password</strong>
            </div>
        </c:if>
        <c:if test="${passwordIsIncorrect}">
            <div class="alert alert-danger alert-dismissible" role="alert">
                <strong>Incorrect password</strong>
            </div>
        </c:if>

        <input type="submit" class="btn btn-primary btn-block" value="Log In">
    </form:form>
</div>
<jsp:include page="/WEB-INF/views/partials/footer.jsp" />
</body>
</html>