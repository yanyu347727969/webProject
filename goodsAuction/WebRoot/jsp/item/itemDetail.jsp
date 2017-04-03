<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<meta http-equiv=”Content-Type” content=”text/html; charset=gb2312”>
    <title>四川师范大学校园二手物品拍卖网</title>
    <link rel="stylesheet" href="/goodsAuction/css/bootstrap.min.css" type="text/css"></link>
    <link rel="stylesheet" href="/goodsAuction/css/lanrenzhijia.css" type="text/css"></link>
    <script type="text/javascript" src="/goodsAuction/js/jquery-2.1.3.min.js"></script>
  	<script type="text/javascript" src="/goodsAuction/js/bootstrap.min.js"></script>
  	<script type="text/javascript" src="/goodsAuction/js/jquery.jqzoom.js"></script>
    <script type="text/javascript"t src="/goodsAuction/js/lanrenzhijia.js"></script>
  	<script type="text/javascript">
  	
  	</script>
  </head>
  
  <body>
  	<nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="/goodsAuction/d/homepageAction">四川师范大学校园二手物品拍卖网</a>
            </div>

            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
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
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Dropdown <span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="#">Action</a></li>
                            <li><a href="#">Another action</a></li>
                            <li><a href="#">Something else here</a></li>
                            <li class="divider"></li>
                            <li><a href="#">Separated link</a></li>
                        </ul>
                    </li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>
    <!-- 京东插件 -->
    <div class="container col-sm-5" style="margin-top: 30px">
	    <div class="lanrenzhijia">
	        <!-- 大图begin -->
	        <div id="preview" class="spec-preview">
	            <span class="jqzoom"><img jqimg="/goodsAuction/p/itemImageAction?size=bigimage&item_id=${item.item_id}&image_id=0" src="/goodsAuction/p/itemImageAction?size=smallimage&item_id=${item.item_id}&image_id=0" /></span>
	        </div>
	        <!-- 大图end -->
	        <!-- 缩略图begin -->
	        <div class="spec-scroll"> <a class="prev">&lt;</a> <a class="next">&gt;</a>
	            <div class="items">
	                <ul>
	                	<c:forEach begin="0" end="${count-1}" step="1" var="c">
	                		<li><img bimg="/goodsAuction/p/itemImageAction?size=bigimage&item_id=${item.item_id}&image_id=${c}" src="/goodsAuction/p/itemImageAction?size=smallimage&item_id=${item.item_id}&image_id=${c}" onmousemove="preview(this);"></li>
	                	</c:forEach>
	                </ul>
	            </div>
	        </div>
	        <!-- 缩略图end -->
	    </div>
	</div>
	
	<div class="container col-sm-6" style="margin-top:20px;">
		<table class="table">
			<thead>
				<tr>
			         <td class="h3">基本信息</td>
			         <td>
			         	<form class="form-inline" role="form"  method="post" action="/goodsAuction/p/itemAuctionAction">
							<div class="input-group">
						        <span class="input-group-addon">￥</span>
						        <c:if test="${item.max_price==null}">
						        	<input name="bid_price" type="number" class="form-control" value="${item.init_price+1}">
						        </c:if>
						        <c:if test="${item.max_price!=null}">
						        	<input name="bid_price" type="number" class="form-control" value="${item.max_price+1}">
						        </c:if>
						    </div>
						    <input name="item_id" type="hidden" value="${item.item_id}">
						    <c:if test="${item.state.state_name=='正在拍卖中'}">
						    	<button type="submit" class="btn btn-danger">参与竞拍</button>
						    </c:if>
						    <c:if test="${item.state.state_name!='正在拍卖中'}">
						    	<button type="submit" class="btn btn-danger disabled">参与竞拍</button>
						    </c:if>
				   		</form>
			         </td>
			      </tr>
			</thead>
		   <tbody>
		      <tr>
		         <td>物品名称:</td>
		         <td>${item.item_name}</td>
		      </tr>
		      <tr>
		         <td>物品描述:</td>
		         <td>${item.item_desc}</td>
		      </tr>
		      <tr>
		         <td>物品种类:</td>
		         <td>${item.kind.kind_name}</td>
		      </tr>
		      <tr>
		         <td>物品所属:</td>
		         <td>${item.owner_user.username}</td>
		      </tr>
		      <tr>
		         <td>起拍时间:</td>
		         <td>${item.addtime}</td>
		      </tr>
		      <tr>
		         <td>结束时间:</td>
		         <td>${item.endtime}</td>
		      </tr>
		      <tr>
		         <td>起拍价格:</td>
		         <td>${item.init_price}￥</td>
		      </tr>
		      <tr>
		         <td>最高出价:</td>
		         <c:if test="${item.max_price==null}">
		         	<td>无</td>
		         </c:if>
		         <c:if test="${item.max_price!=null}">
		         	<td>${item.max_price}￥</td>
		         </c:if>
		      </tr>
		      <tr>
		         <td>交易价格:</td>
		         <c:if test="${item.second_max_price==null}">
		         	<td>无</td>
		         </c:if>
		         <c:if test="${item.second_max_price!=null}">
		         	<td>${item.second_max_price}￥</td>
		         </c:if>
		      </tr>
		   </tbody>
		</table>
	</div>
  </body>
</html>
