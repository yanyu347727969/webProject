<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
  	<meta charset="UTF-8">
  	<title>账户注册|拍卖网</title>
  	<link rel="stylesheet" href="/goodsAuction/css/bootstrap.min.css" type="text/css"></link>
	<script type="text/javascript" src="/goodsAuction/js/jquery-2.1.3.min.js"></script>
  	<script type="text/javascript" src="/goodsAuction/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$(function(){
			//点击验证码图片则刷新图片
			$("#ccaptchaImage").click(function(){
				//$(this).attr("src","/Ajax/captchaAction?id="+new Date().getTime());
				this.src="/goodsAuction/d/captchaAction?id="+new Date().getTime();
			});
		});
	</script>

  </head>
  
  <body>
  <nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/goodsAuction/d/homepageAction"><span class="text-success">四川师范大学校园二手物品拍卖网</span></a>
        </div>
    </div><!-- /.container-fluid -->
</nav>
<div class="container">
    <div class="container col-sm-9">
        <form action="/goodsAuction/d/registerAction" class="form-horizontal" role="form"  method="post" id="form">
            <legend class="h1">注册</legend>
            <div class="form-group">
                <div class="col-sm-6">
                    <input name="username" id="username" type="text" class="form-control" placeholder="用户名">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-6">
                    <input  name="userpass" id="password" type="password" class="form-control" placeholder="密码">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-6">
                    <input  name="userpass2" id="password2" type="password" class="form-control" placeholder="密码确认">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-6">
                    <input  name="mobile" id="mobile" type="tel" class="form-control" placeholder="电话号码">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-6">
                    <input  name="email" id="email" type="email" class="form-control" placeholder="电子邮箱">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-3">
                    <input  name="captcha" id="captcha" type="text" class="form-control" placeholder="验证码">
                </div>
                <div class="col-sm-3">
                    <img  src="/goodsAuction/d/captchaAction" id="ccaptchaImage" height="40" width="100">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-6">
                    <button type="submit" class="btn btn-block btn-primary">注册</button>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-6" style="text-align: right">
                    <a href="/goodsAuction/jsp/user/login.jsp">已有账号,直接登录</a>
                </div>
            </div>
            <legend class="h1"></legend>
        </form>
        <c:if test="${!empty error}">
            <div class="alert alert-danger alert-dismissable">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">
                    &times;
                </button>
              	  错误！${error}
            </div>
        </c:if>
    </div>
</div>
  </body>
</html>
