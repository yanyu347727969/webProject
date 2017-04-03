<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<meta http-equiv=”Content-Type” content=”text/html; charset=utf-8>
    <title>四川师范大学校园二手物品拍卖网</title>
    <link rel="stylesheet" href="/goodsAuction/css/bootstrap.min.css" type="text/css"></link>
    <link rel="stylesheet" href="/goodsAuction/css/10010_com.css" type="text/css"></link>
    <script type="text/javascript" src="/goodsAuction/js/jquery-2.1.3.min.js"></script>
  	<script type="text/javascript" src="/goodsAuction/js/bootstrap.min.js"></script>
  </head>
  
  <body style="position: absolute;top: 0;left: 0;">
  	<nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="/goodsAuction/d/homepageAction">四川师范大学校园二手物品拍卖网</a>
            </div>

            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li><a id="addedItem" href="/goodsAuction/jsp/item/addedItem.jsp">发布拍卖物品</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
		            <c:if test="${!empty user}">
		  				<li><a href="/goodsAuction/jsp/user/individualCenter.jsp">欢迎您:${user.username}</a></li>
		  				<li><a href="/goodsAuction/d/safeExitAction">安全退出</a></li>
		  			</c:if>
		  			<c:if test="${empty user}">
		  				<li><a id="a_login" href="/goodsAuction/jsp/user/login.jsp">亲,请登录</a></li>
                    	<li><a id="a_register" href="/goodsAuction/jsp/user/register.jsp">免费注册</a></li>
		  			</c:if>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">管理员 <span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="/goodsAuction/jsp/kind/addItemKind.jsp">发布物品类别</a></li>
                            <li><a href="/goodsAuction/jsp/state/addItemState.jsp">发布物品状态</a></li>
                            <li><a href="#">Something else here</a></li>
                            <li class="divider"></li>
                            <li><a href="#">Separated link</a></li>
                        </ul>
                    </li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>
    <div class="container">
	    <div class="col-sm-6" style="margin-left: 25%">
	        <form role="form">
	            <div class="form-group">
	                <div class="input-group">
	                    <input id="keyword" type="text" class="form-control">
	                    <a href="javascript:void(0)" class="input-group-addon" id="search">搜索</a>
	                </div>
	            </div>
	        </form>
	    </div>
    </div>
    
    <div class="selectNumberScreen">
		<div id="selectList" class="screenBox screenBackground">
			<dl class=" listIndex" attr="terminal_activity_s">
			  <dt>物品类别:</dt>
				  <dd>
				  	<c:forEach items="${kinds}" var="kind">
				  		<a href="javascript:void(0)" name="${kind.kind_id}">${kind.kind_name}</a>
				  	</c:forEach>
				  </dd>
			</dl>
			<dl class="noBorder listIndex" attr="terminal_sellFeature_s">
			  <dt>物品状态:</dt>
			  <dd>
				  	<c:forEach items="${states}" var="state">
				  		<a href="javascript:void(0)" name="${state.state_id}">${state.state_name}</a>
				  	</c:forEach>
				  </dd>
			</dl>
		</div>
		<div class="hasBeenSelected">
			<dl>
			  <dt>您已选择:</dt>
			  <dd style="DISPLAY: none" class=clearDd>
			  <div class=clearList></div>
			  <div style="DISPLAY: none" class="eliminateCriteria">清除筛选条件</div>
			</dl>
		</div>
	</div>
    <div class="container">
        <div id="item" class="row show-grid">
        </div>
    </div>
    <div class="container" align="center">
    	<table>
    		<tr>
    			<td>
    				<ul class="pagination pagination-sm">
					</ul>
    			</td>
    			<td style="color: gray;padding-left: 15px;font-size:small;">
    				<span id=totalPage></span>
    			</td>
    			<td style="color: gray;padding-left: 10px;font-size:small;padding-top: 3px">
    				<span>到第&nbsp</span><input id="pageNo" type="text" style="width: 50px;height: 25px" onkeyup="this.value=this.value.replace(/[^\d]/g,'')" onafterpaste="this.value=this.value.replace(/[^\d]/g,'')">
    				<span>页&nbsp</span><button id="jumpPage" class="btn btn-default btn-sm">确定</button>
    			</td>
    		</tr>
    	</table>
     </div>
     
     <!-- 登录的模态框 -->
     <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close"
	                        data-dismiss="modal" aria-hidden="true">
	                    &times;
	                </button>
	                <h4 class="modal-title" id="myModalLabel">
	                    	你尚未登录
	                </h4>
	            </div>
	            <div class="modal-body">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <div class="col-sm-10">
                                <span class="h1">登录</span>
                            </div>
                            <div class="col-sm-2">
                                <a href="/goodsAuction/jsp/user/register.jsp" style="color: red">免费注册</a>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <input name="username" id="username" type="text" class="form-control" placeholder="用户名">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <input  name="userpass" id="password" type="password" class="form-control" placeholder="密码">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-8">
                                <input  name="captcha" id="captcha" type="text" class="form-control" placeholder="验证码">
                            </div>
                            <div class="col-sm-4">
                                <img  src="/goodsAuction/d/captchaAction" id="ccaptchaImage" height="35" width="100">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <a id="button_login" class="btn btn-block btn-primary">登录</a>
                            </div>
                        </div>
                    </form>
	            </div>
	        </div><!-- /.modal-content -->
	    </div><!-- /.modal -->
     
  </body>
  <script type="text/javascript">
    var dlNum  =$("#selectList").find("dl");
    for (var i = 0; i < dlNum.length; i++) {
        $(".hasBeenSelected .clearList").append("<div class=\"selectedInfor selectedShow\" style=\"display:none\"><span></span><label></label><em></em></div>");
    }
    var pageNo = 1;//默认当前页
    var pageSize = 10;//默认每页数据条
    var pageCount = 0;//总页数
    var refresh = "true";
    $(".listIndex a ").on("click",function(){
        var text =$(this).text();
        var selectedShow = $(".selectedShow");
        var textTypeIndex =$(this).parents("dl").index();
        var textType =$(this).parent("dd").siblings("dt").text();
        index = textTypeIndex -(2);
        $(".clearDd").show();
        $(".selectedShow").eq(index).show();
        $(this).addClass("selected").siblings().removeClass("selected");
        selectedShow.eq(index).find("span").text(textType);
        selectedShow.eq(index).find("label").text(text);
        var show = $(".selectedShow").length - $(".selectedShow:hidden").length;
        if (show > 1) {
            $(".eliminateCriteria").show();
        }
        ajaxsend(pageNo,pageSize);
    });
    $(".selectedShow em").on("click",function(){
        $(this).parents(".selectedShow").hide();
        var textTypeIndex =$(this).parents(".selectedShow").index();
        index = textTypeIndex;
        $(".listIndex").eq(index).find("a").removeClass("selected");
        
        if($(".listIndex .selected").length < 2){
            $(".eliminateCriteria").hide();
        }
        ajaxsend(pageNo,pageSize);
    });
    
    $(".eliminateCriteria").on("click",function(){
        $(".selectedShow").hide();
        $(this).hide();
        $(".listIndex a ").removeClass("selected");
        ajaxsend(pageNo,pageSize);
    }); 
    
    function ajaxsend(pageNo,pageSize){
    	var kind_id = "";
        var state_id = "";
        var keyword = $("#keyword").val();
		$.each($("#selectList dl"),function(i,dl){
			var dtText = $(dl).children("dt").html();
			if(dtText=="物品类别:"){
				kind_id = $(dl).find("a.selected").attr("name")==undefined?"":$(dl).find("a.selected").attr("name");
				kind_name = $(dl).find("a.selected").html()==undefined?"":$(dl).find("a.selected").html();
			}
			if(dtText=="物品状态:"){
				state_id = $(dl).find("a.selected").attr("name")==undefined?"":$(dl).find("a.selected").attr("name");
				state_name = $(dl).find("a.selected").html()==undefined?"":$(dl).find("a.selected").html();
			}
		});
		//发送ajax请求,请求过滤数据
		$.get("/goodsAuction/s/searchItemsAction",{kind_id:kind_id,state_id:state_id,keyword:keyword,pageNo:pageNo,pageSize:pageSize},function(data){
			$("#item").empty();
			var data = eval("("+data+")");
			var html = "";
			$.each(data[1],function(index,item){
				var max_price = null;
				var second_max_price = null;
				if(item.max_price==null){
					max_price = "无";
				}else{
					max_price = item.max_price;
				}
				if(item.second_max_price==null){
					second_max_price = item.init_price;
				}else{
					second_max_price = item.second_max_price;
				}
				
				
				html+="<div class='col-sm-6 col-md-3'>"+
						"	<a href='/goodsAuction/p/itemDetailAction?item_id="+item.item_id+"' class='thumbnail'>"+
						"		<img src='/goodsAuction/p/itemImageAction?size=smallimage&item_id="+item.item_id+"&image_id=0' alt='通用的占位符缩略图'>"+
						"		<div class='caption'>"+
						"			<p><span>最高价格:"+max_price+"￥</span><span class='pull-right'>"+data[0][item.item_id]+"人参与</span></p>"+
						"			<span>交易价格:"+second_max_price+"￥</span>"+
						" 			<p class='small' style='width: 253px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;'>"+
						"				<span class='h4'>"+item.item_name+"</span>"+
						"				<span class='small'>  "+item.item_desc+"</span>"+
						"			</p>"+
						"			<p></p>"+
						"			<span>"+item.state.state_name+"</span><span class='pull-right'>物主:"+item.owner_user.username+"</span><p></p>"+
						"		</div>"+
						"	</a>"+
						"</div>";
			});
			if(html=="null"||html==""){
				html="<h3>对不起没有物品显示!</h3>";
			}
			$("#item").html(html);
			$("#totalPage").text("共"+data[2	]+"页");
			pageCount = data[2];
			setCommentPage(pageNo,data[2]);
		});
    }
    
    //分页跳转
    function setCommentPage(pageNo,pageCount){
        var temp="";
        if (pageCount != 1)
        {
            if(pageNo!=1)
            {
                //上一页不启用
                var k=pageNo-1;
                temp +="<li><a id='comment"+ k+ "'href='javascript:void(0)' onclick='ajaxsend("+ k+ ","+ pageSize+")'>";
                temp +="上一页</a></li>";
            }
            for (i = 1; i <= pageCount;i++)
            {
                if (i == pageNo)//当前页
                {
                    temp +="<li class='active'><a id='comment"+ i+ "' href='javascript:void(0)' onclick='ajaxsend("+ i+ ","+ pageSize+")'>";
                    temp +=i+"</a></li>";
                }
                else
                {
                    if(pageNo-i>=4&&i!=1)  //只显示当前页前三个页码
                    {
                        temp+="<li class='disabled'><span>...</span></li>";
                        i=pageNo-4;//将页码跳到没有省略的页码
                    }
                    else
                    {
                        if(i>=pageNo+3&&i!=pageCount)  //只显示当前页的后两个页码
                        {
                            temp+="<li class='disabled'><span>...</span></li>";
                            i=pageCount;  //将页码跳到最后一页
                        }
                        temp +="<li><a id='comment"+ i+ "' href='javascript:void(0)' onclick='ajaxsend("+ i+ ","+ pageSize+")'>";
                        temp +=i+"</a></li>";
                    }
                }
            }
            if(pageNo!=pageCount&&pageCount!=0)
            {
                //下一页不启用
                var k=pageNo+1;
                temp +="<li><a id='comment"+ k + "'href='javascript:void(0)' onclick='ajaxsend("+ k+ ","+ pageSize+")'>";
                temp +="下一页</a></li>";
            }
        }
        $(".pagination").html(temp);
        $("#pageNo").val(pageNo);
    }
    //判断obj对象是否是json对象
    function isjson(obj){
		var isjson = typeof(obj) == "object" && Object.prototype.toString.call(obj).toLowerCase() == "[object object]" && !obj.length;    
		return isjson;
	}
    $(function(){
    	//页面初始化时加载拍卖物品
    	ajaxsend(pageNo,pageSize);
    	
    	$("body").css("width",screen.width);
    	
    	//查询按钮的点击事件
    	$("#search").click(function(){
    		$(".eliminateCriteria").click();
			ajaxsend(pageNo,pageSize);
		});
		//输入数字,跳转页数
        $("#jumpPage").click(function(){
        	if(($("#pageNo").val()-0)>pageCount){
        		alert("过大");
        		return;
        	}
            ajaxsend($("#pageNo").val()-0,pageSize);
        });
        
        $("#addedItem").click(function(){
        	if("${empty user}"=="true"){
        		$('#myModal').modal('show');
                return false;
        	}
        });
        
        //ajax登录
         $("#button_login").click(function(){
        	var username=$("#username").val();
        	var userpass=$("#password").val();
        	var captcha=$("#captcha").val();
        	$.post("/goodsAuction/s/ajaxLoginAction",{username:username,userpass:userpass,captcha:captcha},function(data){
        		var data = eval("("+data+")");
        		if(isjson(data)){
        			$('#myModal').modal('hide');
        			$("#a_login").attr("herf","javascript:void(0)");
        			$("#a_login").text("欢迎您:"+data.username);
        			$("#a_register").attr("herf","/goodsAuction/d/safeExitAction");
        			$("#a_register").text("安全退出");
        			window.location.href="/goodsAuction/jsp/item/addedItem.jsp"; 
        		}else{
        			$("div.alert-danger").remove();
        			$("div.modal-body").find("form").after(
        			"<div class='alert alert-danger alert-dismissable'>"+
					"	<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>"+
					"		&times;"+
					"	</button>"+
					"	错误! "+data+
					"</div>");
        		}
        	});
        }); 
    });
</script>
</html>