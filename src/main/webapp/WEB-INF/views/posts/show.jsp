<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ryan.c.harper
  Date: 1/3/2019
  Time: 4:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="app">
<head>
    <jsp:include page="/WEB-INF/views/partials/header.jsp">
        <jsp:param name="title" value="${post.title}" />
    </jsp:include>
</head>
<jsp:include page="/WEB-INF/views/partials/navbar.jsp" />
<body>

<div ng-controller="postController">
    <div class="container" id="post-wrapper-show-page" ng-init="fetchPost(${post.id})">

        <h3 ng-bind-html="post.htmlTitle">{{post.title}}</h3> <%-- use ng-bind-html for parsing the markdown to html--%>
        <h4 ng-bind-html="post.htmlSubtitle">{{post.subtitle}}</h4>

        By: <a href="/profile/{{post.user.id}}/{{post.user.username}}" class="margin-right">{{post.user.username}}</a>

        <span class="margin-right">{{post.hoursMinutes}}</span><span class="margin-right">{{post.formatDate}}</span>

        <i class="fas fa-thumbs-up margin-right-lt"></i><span>{{post.voteCount}}</span>

        <div ng-bind-html="post.htmlLeadImage" id="index-post-image">{{post.leadImage}}</div>

        <div ng-bind-html="post.htmlBody">{{post.body}}</div>

        <c:if test="${sessionScope.user == null}">
            <a href="/login" class="margin-right-lt"><i class="fas fa-2x fa-thumbs-up"></i></a>
            <span class="margin-right-lt">{{post.voteCount}}</span>
            <a href="/login"><i class="fas fa-2x fa-thumbs-down"></i></a>
        </c:if>

         <div class="vote-options-row">
            <c:if test="${sessionScope.user != null}">
                <div class="thumbs-wrapper">
                    <i class="fas fa-2x fa-thumbs-up upvoteIcon margin-right-lt" ng-click="upVote(post.id)" ng-class="{true:'upvoteActive', false:'upvoteIcon'}"></i>
                    <span class="margin-right-lt">{{post.voteCount}}</span>
                    <i class="fas fa-2x fa-thumbs-down downvoteIcon" ng-click="downVote(post.id)" ng-class="{true:'downvoteActive', false:'downvoteIcon'}"></i>
                </div>
            </c:if>

            <%--<i title="Up Votes" ng-click="upVote()" class="fa fa-arrow-circle-up fa-2x" ng-class="{true:'upvoteActive', false:''}"></i>--%>
            <%--<br>--%>
            <%--<i title="Down Votes" ng-click="downVote()" class="fa fa-arrow-circle-down fa-2x"  ng-class="{true:'downvoteActive', false:''}"></i>--%>
            <%--<br>Vote: {{post.voteCount}}--%>

            <c:if test="${sessionScope.user.id == post.user.id}">
                <div class="dropdown-container">
                    <i class="fas fa-ellipsis-h fa-lg options-ellipsis dropdown-toggle"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    </i>
                    <div class="dropdown-menu edit-delete-dropdown" aria-labelledby="dropdownMenu">
                        <div>
                            <a href="/posts/{{post.id}}/edit" class="dropdown-edit-btn">
                                <i class="far fa-edit"></i> edit
                            </a>
                        </div>
                        <div>
                            <a ng-click="deletePost(post)" class="dropdown-delete-btn">
                                <i class="fas fa-trash-alt"></i> delete
                            </a>
                        </div>
                    </div>
                </div>
            </c:if>
        </div>

    </div>
</div>

<jsp:include page="/WEB-INF/views/partials/footer.jsp" />
<script>

    let app = angular.module('app', ['ngSanitize']);

    app.controller('postController', function($scope, $http, $window) {

        $scope.post = {};

        $scope.fetchPost = function(postId) {
            console.log(postId);
            $http({
                method: 'GET',
                // url: 'http://localhost:8888/posts/fetch/' + postId,
                url: 'http://localhost:8888/api/post/id/' + postId,
            }).then(function (response) {
                console.log("success");
                console.log(response.data);
                $scope.post = response.data;
                // $scope.postLimit = 3;  | limitTo:postLimit"
            }, function (error) {
                console.log("Get posts error: " + error);
            });
        };

        $scope.upVote = function (postId) {
            $http({
                method: 'POST',
                url: 'http://localhost:8888/posts/' + postId + '/upvote',
            }).then(function (response) {
                console.log(response.data);
                $scope.post.voteCount = response.data.voteCount;
            }, function (error) {
                console.log("Get posts error: " + error);
            });
        };

        $scope.downVote = function (postId) {
            $http({
                method: 'POST',
                url: 'http://localhost:8888/posts/' + postId + '/downvote',
            }).then(function (response) {
                console.log(response.data);
                $scope.post.voteCount = response.data.voteCount;
            }, function (error) {
                console.log("Get posts error: " + error);
            });
        };

        $scope.deletePost = function(post) {
            $http({
                method: 'POST',
                url: 'http://localhost:8888/deletePost/' + post.id + '/redirect',
            }).then(function () {
                $window.location.href = "/profile"
            }, function (error) {
                console.log("Delete post error: " + error);
            })

        };









    })

</script>




</body>
</html>
