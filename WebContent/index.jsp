<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>房屋租赁系统主页</title>
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="stylesheet" type="text/css" href="css/index.css">
</head>
<body>
	<!-- 头部导航 -->
	<header>
		<nav>
			<div></div>
			<div class="shortCut">	
				<ul id="nav">
					<li><a href="new.jsp">查看新闻</a></li>
					<li><a href="notice.jsp">消息<span id="countNotice"></span></a></li>
				</ul>		
			</div>
		</nav>
		<div>
			<div id="logo"><a href="index.jsp"><img src="images/logo.png" alt="logo" /></a></div>
			<div class="makeup">
				<div id="search">
					<ul id="filterBg">
						<span>筛选条件：</span>
						<li id="priceFilter">按价格筛选</li>
						<li id="keywordFilter">按关键字筛选</li>
						<li id="houseIdFilter">按房东id号筛选</li>
					</ul>
					<div class="filter price" id="price">
						<span>价格范围：</span>
						<input type="text" name="minprice" id="minPrice" placeholder="最小价格">--
						<input type="text" name="maxprice" id="maxPrice" placeholder="最高价格">
						<button id="sendPrice">查询</button>
					</div>
					<div class="filter" id="keyword">
						<span>关键字搜索：</span>
						<input type="text" placeholder="请输入要搜索的房子信息关键字"/>
						<button id="sendKeyword">查询</button>
					</div>
					<div class="filter" id="houseId">
						<span>房主id号搜索：</span>
						<input type="text" placeholder="请输入要搜索的房主id号"/>
						<button id="sendHouseId">查询</button>
					</div>
					
				</div>
			</div>
		</div>
	</header>
	<!-- 主体租赁信息内容 -->	
	<div id="main">
		<div class="content">
			<div id="houseTitle"><span>房源信息</span><span class="sort" id="timeUp">按时间升序</span><span class="sort chooseSort" id="timeDown">按时间降序</span><span class="sort" id="priceUp">按价格升序</span><span class="sort" id="priceDown">按价格降序</span></div>
			<ul id="houseInfo">
			</ul>
			
		</div>
		
		<!-- 切换页面 -->
		<div id="switchPage"></div>
	</div>
	<!-- 脚部网站信息 -->
	<footer>脚部相关信息</footer>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script src="js/index.js"></script>
</body>
<html>