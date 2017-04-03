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
        function choose(tagA){
            var file = $(tagA).parents("tr").find("input[type='file']");
            file.click();
        }
        //图片预览
        function preSendSeeView(file,image)
        {
            image.attr("src",URL.createObjectURL(file));
            URL.revokeObjectURL(file);
        }
        //给table加上序列
        function sumTr(){
            var spans = $("tr").find("span:first");
            $.each(spans, function(n,span){
                $(span).text(n+1);
            });
        }
        //增加图片选择栏
        function addImage(){
            var tr=$("<tr>");
            var td=$("<td style='vertical-align: middle'><span></span></td>"+
            "<td style='vertical-align: middle'>" +
            "<span style='display:none'>" +
            "<input type='file' id='file' name='image'>" +
            "</span>" +
            "<div class='input-group col-sm-8' id='filenamediv'>" +
            "<input type='text' id='fileName' class='form-control' readonly>" +
            "<span class='input-group-addon'>" +
            "<a href='javascript:void(0)'>选择</a>" +
            "</span></div></td>" +
            "<td><img src='/goodsAuction/image/non_existent.jpg' id='image' alt='' width='200' height='200'></td>" +
            "<td style='vertical-align: middle'><a href='javascript:void(0)' id='close' class='btn'><img src='/goodsAuction/image/close.gif' alt=''></a></td>");
            tr.append(td);
            $("#lasttr").before(tr);
            sumTr();
        }
        $(function(){
            $("tbody").on("change","#file",function(e){
            	var tt = $(this).parents("tr").find("#fileName");
                $(this).parents("tr").find("#fileName").val($(this).val());
                var file = e.target.files[0];
                objectURL = window.URL.createObjectURL(file);
                var image = $(this).parents("tr").find("#image");
                image.attr("src",objectURL);
            });
            $("tbody").on("click","#close",function(){
                $(this).parents("tr").remove();
                sumTr();
            });
            $("tbody").on("click","#filenamediv",function(){
                choose(this);
            });
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
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="/goodsAuction/jsp/item/addedItem.jsp">发布拍卖物品</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<c:if test="${!empty user}">
					<li><a href="javascript:void(0)">欢迎您:${user.username}</a></li>
					<li><a href="/goodsAuction/d/safeExitAction">安全退出</a></li>
				</c:if>
				<c:if test="${empty user}">
					<li><a href="/goodsAuction/jsp/user/login.jsp">亲,请登录</a></li>
					<li><a href="/goodsAuction/jsp/user/register.jsp">免费注册</a></li>
				</c:if>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-expanded="false">Dropdown
						<span class="caret"></span>
				</a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="#">Action</a></li>
						<li><a href="#">Another action</a></li>
						<li><a href="#">Something else here</a></li>
						<li class="divider"></li>
						<li><a href="#">Separated link</a></li>
					</ul></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>
	<div class="container">
		<div class="container col-sm-9">
			<form action="/goodsAuction/p/saveImageAction"
				class="form-horizontal" role="form" method="post"
				enctype="multipart/form-data" id="formId">
				<input type="hidden" name="item_id" value="${param.item_id}">
				<table class="table">
					<thead>
						<th>
						<td class="h1">上传拍卖物品图片</td>
						<td colspan="2"><input class="pull-right btn btn-primary"
							type="button" id="add" value="增加图片" onclick="addImage()" /></td>
						</th>
					</thead>
					<tbody>
						<tr id="lasttr">
							<td colspan="3"><input
								class="btn btn-block btn-primary pull-right" type="submit"
								value="提交"></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
