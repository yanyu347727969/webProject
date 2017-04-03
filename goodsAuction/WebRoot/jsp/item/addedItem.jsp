<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link rel="stylesheet" href="/goodsAuction/css/bootstrap.min.css"
	type="text/css"></link>
<script type="text/javascript"
	src="/goodsAuction/js/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="/goodsAuction/js/bootstrap.min.js"></script>
<script type="text/javascript">
		$(function(){
			//点击验证码图片则刷新图片
			$("#ccaptchaImage").click(function(){
				//$(this).attr("src","/Ajax/captchaAction?id="+new Date().getTime());
				this.src="/goodsAuction/d/captchaAction?id="+new Date().getTime();
			});
			
			//加载物品类别
			$.post("/goodsAuction/p/loadKindAction",function(data){
				for(var i = 0;i<data.length;i++){
					$("<option>").html(data[i].kind_name).val(data[i].kind_id).appendTo($("#kind_id"));
				}
			},"json");
		});
	
	</script>


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
			<form action="/goodsAuction/p/addedItemAction"
				class="form-horizontal" role="form" method="post" id="form">
				<legend class="h1">发布拍卖物品</legend>
				<div class="form-group">
					<label for="item_name" class="col-sm-2 control-label">物品名称:</label>
					<div class="col-sm-6">
						<input name="item_name" id="item_name" type="text"
							class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label for="item_desc" class="col-sm-2 control-label">物品描述:</label>
					<div class="col-sm-6">
						<input name="item_desc" id="item_desc" type="text"
							class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label for="addtime" class="col-sm-2 control-label">起拍日期:</label>
					<div class="col-sm-6">
						<input name="addtime" id="addtime" type="date"
							class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label for="endtime" class="col-sm-2 control-label">结束日期:</label>
					<div class="col-sm-6">
						<input name="endtime" id="endtime" type="date"
							class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label for="init_price" class="col-sm-2 control-label">起拍价格:</label>
					<div class="col-sm-6">
						<input name="init_price" id="init_price" type="number" min="0.1"
							step="0.1" value="1" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label for="kind_id" class="col-sm-2 control-label">物品类别:</label>
					<div class="col-sm-6">
						<select class="form-control col-sm-6" id="kind_id" name="kind_id">
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="address" class="col-sm-2 control-label">交易地址:</label>
					<div class="col-sm-6">
						<input name="address" id="address" type="text"
							class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label for="captcha" class="col-sm-2 control-label">验证码:</label>
					<div class="col-sm-3">
						<input name="captcha" id="captcha" type="text"
							class="form-control">
					</div>
					<div class="col-sm-3">
						<img src="/goodsAuction/d/captchaAction" id="ccaptchaImage"
							height="40" width="100">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2"></div>
					<div class="col-sm-6">
						<button type="submit" class="btn btn-block btn-primary">提交</button>
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
