<%--
  Created by IntelliJ IDEA.
  User: ryan.c.harper
  Date: 12/12/2018
  Time: 11:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html data-ng-app="">
<head>
    <title>Login</title>
</head>
<body>
        <!--before SimpleContoller:

        data-ng-init="
        customers=[{name: 'Ryan Harper', city: 'San Antonio'},
        {name: 'Gideon Rogers', city: 'Seoul'},
        {name: 'Tyler Hinton', city: 'Austin'}]">-->

<h3>${message}</h3>

        <div data-ng-controller="SimpleController">
            Name:
            <input type="text" data-ng-model="name"/> <!-- declares a model object to be used in the view -->

            <ul>
                <li data-ng-repeat="cust in customers | filter:name | orderBy:'city'"> {{ cust.name | uppercase }} - {{ cust.city | lowercase }}</li>
            </ul>

        </div>
<!--{{ name }}-->

<!--====================== ng-repeat =============================-->

<!--<body data-ng-init="names=['Ryan', 'Grant', 'Lynne', 'Lauren']">

    <h3>looping through an array with the ng-repeat Directive:</h3>
    <ul>
    <li data-ng-repeat="personName in names">{{ personName }}</li>

</ul>-->

<!--========================== using | filter, orderBy ===============================-->

<!--<ul>
    <li data-ng-repeat="cust in customers | filter:name | orderBy:'city'"> {{ cust.name | uppercase }} - {{ cust.city | lowercase }}</li>
     // or ... {{ cust.name + ' ' + cust.city }}
                {{cust.name}} - {{cust.city}} //
</ul> -->


<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.5/angular.min.js"></script>

<script>

    function SimpleController($scope) {

        $scope.customers=[
            {name: 'Ryan Harper', city: 'San Antonio'},
            {name: 'Gideon Rogers', city: 'Seoul'},
            {name: 'Tyler Hinton', city: 'Austin'}];

    }

</script>
</body>
</html>