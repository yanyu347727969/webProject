<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<meta charset="UTF-8">
<title>发布物品状态|拍卖网</title>
<link rel="stylesheet" href="/goodsAuction/css/bootstrap.min.css"
	type="text/css"></link>
<script type="text/javascript"
	src="/goodsAuction/js/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="/goodsAuction/js/bootstrap.min.js"></script>
</head>

<body>
	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="/goodsAuction/d/homepageAction"><span
				class="text-success">四川师范大学校园二手物品拍卖网</span></a>
		</div>
	</div>
	<!-- /.container-fluid --> </nav>
	<div class="container">
		<div class="container col-sm-9">
			<form action="/goodsAuction/admin/addItemStateAction"
				class="form-horizontal" role="form" method="post" id="form">
				<legend class="h1">发布物品状态</legend>
				<div class="form-group">
					<div class="col-sm-6">
						<input name="state_name" id="state_name" type="text"
							class="form-control" placeholder="状态名称">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-3">
						<input name="captcha" id="captcha" type="text"
							class="form-control" placeholder="验证码">
					</div>
					<div class="col-sm-3">
						<img src="/goodsAuction/d/captchaAction" id="ccaptchaImage"
							height="40" width="100">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-6">
						<button type="submit" class="btn btn-block btn-primary">发布</button>
					</div>
				</div>
				<legend class="h1"></legend>
			</form>
			<c:if test="${!empty error}">
				<div class="alert alert-danger alert-dismissable">
					<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">&times;</button>
					错误！${error}
				</div>
			</c:if>
		</div>
	</div>
</body>
</html>
