<%--
  Created by IntelliJ IDEA.
  User: ryan.c.harper
  Date: 12/12/2018
  Time: 12:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View 2</title>
</head>
<body>
<div class="container">

    <h2>View 2</h2>

    City:
    <input type="text" data-ng-model="filter.name"/>

    <ul>
        <li data-ng-repeat="cust in customers | filter:city | orderBy:'city'"> {{ cust.name | uppercase }} - {{ cust.city | lowercase }}</li>
    </ul>

</div>
</body>
</html>
