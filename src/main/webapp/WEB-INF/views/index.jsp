<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ryan.c.harper
  Date: 12/12/2018
  Time: 3:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html ng-app="app"> <!-- an angular directive, defines that this is an angularjs application, give it a name-->
<head>
    <jsp:include page="/WEB-INF/views/partials/header.jsp">
        <jsp:param name="title" value="trainingapp" />
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/views/partials/navbar.jsp" />

<div ng-controller="indexController">
    <div class="container" ng-init="fetchPosts()">

        <div ng-repeat="post in posts | orderBy:'$index':true">
            <a href="/posts/{{post.id}}/{{post.title}}"><%-- use ng-bind-html for parsing the markdown to html--%>
                <h3 ng-bind-html="post.htmlTitle">{{post.title}}</h3>
            </a>
            <h4 ng-bind-html="post.htmlSubtitle">{{post.subtitle}}</h4>

            By: <a href="/profile/{{post.user.id}}/{{post.user.username}}" class="margin-right">{{post.user.username}}</a>
            <span class="margin-right-lt">{{post.hoursMinutes}}</span><span class="margin-right">{{post.formatDate}}</span>
            <i class="fas fa-thumbs-up margin-right-lt"></i>{{post.voteCount}}
            <a href="/posts/{{post.id}}/{{post.title}}"><div ng-bind-html="post.htmlLeadImage" id="index-post-image">{{post.leadImage}}</div></a>
        </div>

    </div>
</div>
<jsp:include page="/WEB-INF/views/partials/footer.jsp" />
<script>

    let app = angular.module('app', ['ngSanitize']);

    app.controller('indexController', function($scope, $http) { //$http will be used for accessing the server side data

        $scope.posts = {};

        $scope.fetchPosts = function() {
                $http({
                    method: 'GET',
                    url: '/fetchPosts'
                }).then(function (response) {
                    console.log("success");
                    console.log(response.data);
                    $scope.posts = response.data;
                    // $scope.postLimit = 3;  | limitTo:postLimit"
                }, function (error) {
                    console.log("Get posts error: " + error);
                });
            };


    });




</script>
</body>
</html>
