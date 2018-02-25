<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>管理员管理页面———房屋租赁系统</title>
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="stylesheet" type="text/css" href="css/notice.css">
</head>
<body>
	<div class="maincontent">
		<!-- 头部导航 -->
		<header>
			<nav>
				<div></div>
				<div class="shortCut detailNav">
					<ul id="nav">
						<li class="index"><a href="index.jsp">房屋租赁主页</a></li>
						<li><a href="notice.jsp">消息<span id="countNotice"></span></a></li>
					</ul>		
				</div>
			</nav>
		</header>
		<!-- 个人信息 -->	
		<div class="content">
			<div class="noticeInfos">
				<div class="noticeTitle">相关消息</div>
				<div class="allNotice">
					<!-- <span>暂时没有订单</span> -->
					<p class="chooseNotice" id="chooseNotice"><span id="unread" class="choose">未读消息</span><span id="allnotices">所有消息</span></p>
					<!-- 管理员未审核的订单 -->
					<ul id="allNoticeBg" class="orderInfo">
						<!-- <li><p>亲爱的用户：</p><p class="noticeContent">欢迎你加入大学生租赁网站欢迎你加入大学生租赁网站欢迎你加入大学生租赁网站欢迎你加入大学生租赁网站欢迎你加入大学生租赁网站欢迎你加入大学生租赁网站欢迎你加入大学生租赁网站欢迎你加入大学生租赁网站欢迎你加入大学生租赁网站欢迎你加入大学生租赁网站欢迎你加入大学生租赁网站欢迎你加入大学生租赁网站</p></li> -->
					</ul>
				</div>
			</div>
		</div>
		<!-- 脚部网站信息 -->
		<footer>脚部相关信息</footer>
	</div>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script src="js/notice.js"></script>
</body>
<html>