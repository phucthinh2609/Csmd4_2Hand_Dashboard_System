<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: macbookair
  Date: 22/06/2022
  Time: 09:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
    <!-- App favicon -->
    <%@include file="/cp/head/app-favicon.html" %>
    <!-- Main -->
    <%@include file="/cp/head/main.html" %>
</head>
<body>
<div class="account-pages my-5 pt-sm-5">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8 col-lg-6 col-xl-5">
                <div class="card overflow-hidden">
                    <div class="bg-soft-primary">
                        <div class="row">
                            <div class="col-7">
                                <div class="text-primary p-4">
                                    <h5 class="text-primary">Welcome Back !</h5>
                                    <p>Sign in to continue to Skote.</p>
                                </div>
                            </div>
                            <div class="col-5 align-self-end">
                                <img src="assets\images\profile-img.png" alt="" class="img-fluid">
                            </div>
                        </div>
                    </div>
                    <div class="card-body pt-0">
                        <div>
                            <a href="index.html">
                                <div class="avatar-md profile-user-wid mb-4">
                                              <span class="avatar-title rounded-circle bg-light">
                                                  <img src="assets\images\logo.svg" alt="" class="rounded-circle"
                                                       height="34">
                                              </span>
                                </div>
                            </a>
                        </div>
                        <div class="p-2">
                            <form class="form-horizontal" method="post">

                                <div class="form-group">
                                    <label for="username">Email user</label>
                                    <input type="text" class="form-control" id="username" name="username"
                                           value="${username}" placeholder="Enter username">
                                </div>

                                <div class="form-group">
                                    <div class="form-group">
                                        <label for="password">Password</label>
                                        <div class="input-group">
                                            <input type="password" name="password" id="password"
                                                   class="form-control pwd" value="${password}">
                                            <span class="input-group-append">
                                                <button class="btn btn-default reveal" type="button">
                                                    <i class="fa fa-eye"></i>
                                                </button>
                                            </span>
                                        </div>

                                    </div>
                                </div>

                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="custom-control-input" id="customControlInline">
                                    <label class="custom-control-label" for="customControlInline">Remember me</label>
                                </div>

                                <div class="mt-3 mb-3">
                                    <button class="btn btn-primary btn-block waves-effect waves-light" type="submit">Log
                                        In
                                    </button>
                                </div>

                                <!-- start alert fail -->
                                <div>
                                    <c:if test="${requestScope['success'] == false}">
                                        <c:forEach items="${requestScope['errors']}" var="error">
                                            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                                                <i class="mdi mdi-block-helper mr-2"></i>
                                                    ${error}
                                                <button type="button" class="close" data-dismiss="alert"
                                                        aria-label="Close">
                                                    <span aria-hidden="true">×</span>
                                                </button>
                                            </div>
                                        </c:forEach>
                                    </c:if>
                                </div>
                                <!-- end alert fail -->


                                <div class="mt-4 text-center">
                                    <h5 class="font-size-14 mb-3">Sign in with</h5>

                                    <ul class="list-inline">
                                        <li class="list-inline-item">
                                            <a href="javascript::void()"
                                               class="social-list-item bg-primary text-white border-primary">
                                                <i class="mdi mdi-facebook"></i>
                                            </a>
                                        </li>
                                        <li class="list-inline-item">
                                            <a href="javascript::void()"
                                               class="social-list-item bg-info text-white border-info">
                                                <i class="mdi mdi-twitter"></i>
                                            </a>
                                        </li>
                                        <li class="list-inline-item">
                                            <a href="javascript::void()"
                                               class="social-list-item bg-danger text-white border-danger">
                                                <i class="mdi mdi-google"></i>
                                            </a>
                                        </li>
                                    </ul>
                                </div>

                                <div class="mt-4 text-center">
                                    <a href="auth-recoverpw.html" class="text-muted"><i class="mdi mdi-lock mr-1"></i>
                                        Forgot your password?</a>
                                </div>
                            </form>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<!-- JAVASCRIPT -->
<%@include file="/cp/script/javascript.html" %>

<script src="/assets/js/pages/password-hide-show.js"></script>

<!-- App js -->
<%@include file="/cp/script/app-js.html" %>


</body>
</html>
