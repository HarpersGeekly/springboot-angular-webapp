<%--
  Created by IntelliJ IDEA.
  User: ryan.c.harper
  Date: 12/12/2018
  Time: 12:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html data-ng-app="demoApp">
<head>
    <title>Angular Modules</title>
</head>
<body>

    <h3>${message}</h3>

    <%--Placeholder for our views! (Our views will be dynamically injected into this div. This is a SPA concept, Single-Page App)--%>
    <div>
        <div data-ng-view=""></div>
    </div>

<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.5/angular.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.5/angular-route.js"></script>
<script>

    let demoApp = angular.module('demoApp', ['ngRoute']);

    demoApp.config(['$routeProvider', function($routeProvider) {

        $routeProvider
            .when('/view1', {
                controller: 'SimpleController',
                templateUrl: '/views/partials/angular/view1.jsp'
            })
            .when('/view2', {
                controller: 'SimpleController',
                templateUrl: '/views/partials/angular/view2.jsp'
            })
            .otherwise({redirectTo: '/view1'}
            );
    }]);

    // let controllers = {};
    // demoApp.controller(controllers);

    demoApp.controller('SimpleController', function($scope) { // ($scope, simpleFactory)

        $scope.customers = [
            {name: 'Ryan Harper', city: 'San Antonio'},
            {name: 'Gideon Rogers', city: 'Seoul'},
            {name: 'Tyler Hinton', city: 'Austin'}
        ];

        // view1.jsp calls this function addCustomer() on a button click, to be used here.
        // view1.jsp does not know about the controller
        $scope.addCustomer = function () {
            $scope.customers.push(
                {
                    name: $scope.newCustomer.name,
                    city: $scope.newCustomer.city
                });
        }
    });

    // $scope.customers = [];
    // init();

    // function init() {
    // $scope.customers = simpleFactory.getCustomers();
    // }

    // demoApp.factory('simpleFactory', function( //$http could be an ajax call $http.get etc) {
    //     let factory = {}; //object
    //     let customers = [];
    //     factory.getCustomers = function() {
    //         //could be an ajax call to get all customers..
    //         return customers;
    //     };
    //     factory.postCustomer = function(customer) {
    //
    //     };
    //     return factory;
    // })
    // demoApp.controller('SimpleController', function($scope, simpleFactory) {
    //     $scope.customers = simpleFactory.getCustomers();
    // });


</script>
</body>
</html>
