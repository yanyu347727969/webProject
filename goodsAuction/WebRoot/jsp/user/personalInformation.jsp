<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>个人信息</title>
	<link rel="stylesheet" href="/goodsAuction/css/bootstrap.min.css" type="text/css"></link>
	<script type="text/javascript" src="/goodsAuction/js/jquery-2.1.3.min.js"></script>
  	<script type="text/javascript" src="/goodsAuction/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		function ModifyPassowrd(tag){
			if($(tag).text()=="密码修改"){
				$(tag).text("取消密码修改");
				$("#span_modifyPassowrd").html(
				"<div class='form-group'>"+
				"	<div class='col-sm-6'>"+
				"		<input id='password' type='password' class='form-control' placeholder='旧密码'>"+
				"	</div>"+
				"</div>"+
				"<div class='form-group'>"+
				"	<div class='col-sm-6'>"+
				"		<input id='newPassword' type='password' class='form-control' placeholder='新密码'>"+
				"	</div>"+
				"</div>"+
				"<div class='form-group'>"+
				"	<div class='col-sm-6'>"+
				"		<input id='newPassword2' type='password' class='form-control' placeholder='新密码确认'>"+
				"	</div>"+
				"</div>"
				);
				return;
			}
			if($(tag).text()=="取消密码修改"){
				$(tag).text("密码修改");
				$("#span_modifyPassowrd").html("");
			}
			
		}
		$(function(){
			//点击验证码图片则刷新图片
			$("#ccaptchaImage").click(function(){
				//$(this).attr("src","/Ajax/captchaAction?id="+new Date().getTime());
				this.src="/goodsAuction/d/captchaAction?id="+new Date().getTime();
			});
			
			//个人资料修改提交
			$("a.btn-primary").click(function(){
				var user_id = $("#user_id").val();
				var username = $("#username").val();
				var oldPassword = $("#password").val();
				var newPassword = $("#newPassword").val();
				var newPassword2 = $("#newPassword2").val();
				var mobile = $("#mobile").val();
				var email = $("#email").val();
				var captcha = $("#captcha").val();
				$.post("/goodsAuction/owner/personalInformationUpdateAction",{user_id:user_id,username:username,userpass:oldPassword,newPassword:newPassword,newPassword2:newPassword2,mobile:mobile,email:email,captcha:captcha},function(data){
					alert(data);
					if(data=="已成功修改个人信息!"){
						$('#iframe', parent.document).attr("src","/goodsAuction/jsp/user/personalInformation.jsp?id="+new Date().getTime());
					}
				});
			});
			
		});
	</script>
  </head>
  
  <body>
	  <div class="container col-sm-9" style="margin-left: 50px">
        <form class="form-horizontal" role="form" id="form">
        	<input id="user_id" type="hidden" value="${user.user_id}">
            <div class="form-group">
                <div class="col-sm-6">
                    <input value="${user.username}" id="username" type="text" class="form-control" disabled="disabled" placeholder="用户名">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-6">
                	<a href="javascript:void(0)" onclick=ModifyPassowrd(this)>密码修改</a>
                </div>
            </div>
            <span id="span_modifyPassowrd">
            </span>
            <div class="form-group">
                <div class="col-sm-6">
                    <input  name="mobile" value="${user.mobile}" id="mobile" type="tel" class="form-control" placeholder="电话号码">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-6">
                    <input value="${user.email}" id="email" type="email" class="form-control" placeholder="电子邮箱">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-3">
                    <input id="captcha" type="text" class="form-control" placeholder="验证码">
                </div>
                <div class="col-sm-3">
                    <img  src="/goodsAuction/d/captchaAction" id="ccaptchaImage" height="40" width="100">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-6">
                    <a class="btn btn-block btn-primary">确认修改</a>
                </div>
            </div>
            <legend class="h1"></legend>
        </form>
    </div>
  </body>
</html>
