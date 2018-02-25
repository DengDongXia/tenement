<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>更新页面———房屋租赁系统</title>
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="stylesheet" type="text/css" href="css/createHouseInfo.css">
</head>
<body>

<div class="maincontent">
		<!-- 头部导航 -->
		<header>
			<nav>
				<div></div>
				<div class="shortCut detailNav">
					<ul>
						<li class="index"><a href="index.jsp">房屋租赁主页</a></li>
						<li><a href="personal.jsp">个人中心</a></li>
						<li><a href="notice.jsp">消息<span id="countNotice"></span></a></li>
						<li id="lgout">注销</li> 
					</ul>		
				</div>
			</nav>
		</header>
		<!-- 创建订单部分 -->
		<div class="ceateOrder">
			<p class="tagTitle">更新已发布的客房信息</p>
			<div class="orderContent">
				<p><span class="label">客房标题：</span><input type="text" name="" id="infoTitle" placeholder="请输入客房信息的标题" required ></p>
				<p class="area"><span class="label">所在地区：</span><input type="text" name="" id="province" placeholder="所在省份" required >--<input type="text" name="" id="city" placeholder="所在市" required >--<input type="text" name="" id="region" placeholder="所在城区" required ></p>
				<p><span class="label">详细地址：</span><input type="text" name="" id="address" placeholder="请输入客房所在详细地址" required ></p>
				<p><span class="label">每月租金：</span><input type="text" name="" id="price" placeholder="请输入客房的月租" required ></p>
				<p><span class="label">剩余数量：</span><input type="text" name="" id="count" placeholder="请输入客房的剩余数量" required ></p>
				<!-- <p><span class="label">上传图片：</span><input type="file" name="" title="最多上传5张图片" required multiple></p> -->
				<p class="commentBg"><span class="label">详细内容：</span><textarea id="comment"></textarea></p>
				<p><button id="publish">重新发布</button></p>
			</div>
		</div>

		<!-- 脚部网站信息 -->
		<footer>脚部相关信息</footer>
</div>

<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script src="js/updateHouseInfo.js"></script>
</body>
<html>