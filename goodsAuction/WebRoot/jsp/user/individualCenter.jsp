<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv=”Content-Type” content=”text/html; charset=utf-8>
<title>个人中心</title>
<link rel="stylesheet" href="/goodsAuction/css/bootstrap.min.css"
	type="text/css"></link>
<link rel="stylesheet" href="/goodsAuction/css/10010_com.css"
	type="text/css"></link>
<script type="text/javascript"
	src="/goodsAuction/js/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="/goodsAuction/js/bootstrap.min.js"></script>
</head>

<body>
	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="/goodsAuction/d/homepageAction">四川师范大学校园二手物品拍卖网</a>
		</div>

		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a id="addedItem"
					href="/goodsAuction/jsp/item/addedItem.jsp">发布拍卖物品</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<c:if test="${!empty user}">
					<li><a href="/goodsAuction/s/individualCenterAction">欢迎您:${user.username}</a></li>
					<li><a href="/goodsAuction/d/safeExitAction">安全退出</a></li>
				</c:if>
				<c:if test="${empty user}">
					<li><a id="a_login" href="/goodsAuction/jsp/user/login.jsp">亲,请登录</a></li>
					<li><a id="a_register"
						href="/goodsAuction/jsp/user/register.jsp">免费注册</a></li>
				</c:if>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-expanded="false">管理员
						<span class="caret"></span>
				</a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="/goodsAuction/jsp/kind/addItemKind.jsp">发布物品类别</a></li>
						<li><a href="/goodsAuction/jsp/state/addItemState.jsp">发布物品状态</a></li>
						<li><a href="#">Something else here</a></li>
						<li class="divider"></li>
						<li><a href="#">Separated link</a></li>
					</ul></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>
	<ul class="nav nav-tabs nav-justified">
		<li class="active"><a id="a1" href="javascript:void(0)">修改个人资料</a></li>
		<li><a id="a2" href="javascript:void(0)">我参与的拍卖</a></li>
		<li><a id="a3" href="javascript:void(0)">我发布的拍卖物品</a></li>
		<li><a id="a4" href="javascript:void(0)">我竞拍成功的物品</a></li>
	</ul>
	<iframe style="margin-top: 50px; border: 0;" id="iframe"
		src="/goodsAuction/jsp/user/personalInformation.jsp" width="100%"
		height="100%"> </iframe>
	<script type="text/javascript">
		function fuc(tagA,url){
			tagA.click(function(){
				$("ul.nav-justified").find("li").removeClass();
				tagA.parent("li").attr("class","active");
				$("#iframe").attr("src",url);
			});
		}
		
		$(function(){
			fuc($("#a1"),"/goodsAuction/jsp/user/personalInformation.jsp");
			fuc($("#a2"),"/goodsAuction/jsp/user/OwnerAuction.jsp");
			fuc($("#a3"),"/goodsAuction/jsp/user/OwnerAddedItem.jsp");
			fuc($("#a4"),"/goodsAuction/jsp/user/OwnerAuctionSuccess.jsp");
		});
	</script>
</body>
</html>
