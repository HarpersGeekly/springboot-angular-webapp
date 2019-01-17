<%--
  Created by IntelliJ IDEA.
  User: ryan.c.harper
  Date: 12/12/2018
  Time: 12:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View1</title>
</head>
<body>
<div class="container">

    <h2>View 1</h2>

    Name:
    <input type="text" data-ng-model="filter.name"/>

    <ul>
        <li data-ng-repeat="cust in customers | filter:filter.name | orderBy:'city'"> {{ cust.name | uppercase }} - {{ cust.city | lowercase }}</li>
    </ul>

    Customer Name:
    <input type="text" data-ng-model="newCustomer.name" />
    <br/>
    Customer City:
    <input type="text" data-ng-model="newCustomer.city" />
    <br/>
    <button data-ng-click="addCustomer()">Add Customer</button>
    <a href="#!/view2">Go to View 2</a>

</div>
</body>
</html>
