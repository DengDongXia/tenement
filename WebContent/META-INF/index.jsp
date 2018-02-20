<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>房屋租赁系统主页</title>
	<link rel="stylesheet" type="text/css" href="css/index.css">
</head>
<body>
	<!-- 头部导航 -->
	<header>
		<nav>
			<div></div>
			<div class="shortCut">	
				<ul>
					<li><a href="personal.jsp">个人中心</a></li>
					<li><a href="notice.jsp">消息<span id="countNotice"></span></a></li>
					<li><a href="login.jsp">登录</a></li>
					<li><a href="register.jsp" target="_blank">注册</a></li> 
					<li id="lgout">注销</li> 
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
				<li>
					<a href="houseDetail.html" target="_blank">
					<div class="housePic"><img src="images/house_1.jpg" alt="被出租的房屋图片"></div>
					<div class="aboutText">
						<div class="text">
							<p class="contentTitle">邻近交通大学南门 交大东路西直门长河湾可长租可月付照片真实(个人)  </p>
							<p class="contentComment">房源标题(租):保利花园电梯三房带主套看房方便装修描述(租):此单位住家装修，保养还不错。雪白的墙身，显示得房屋光亮，户型方正。楼下环境好，温馨舒适。核心卖点(租):通风采光好生活便利户型实用配套:布局合理。采光以及通风好，旺中带静。房屋视野开阔.通风对流，让人感觉舒服。住起来比较温馨。小区介绍:属于中型社区，楼下环境可以，出入方便，配套成熟。交通方便，有多条线路直达广州任何一个区域我司房源编号为：NS98818</p>
							<p class="city"><span><img src="images/address.png"></span><span>广东省>广州市>天河区</span></p>
							<p class="adress">详细地址：<span>龙洞迎福路广东金融学院</span></p>
						</div>
						<div class="otherInfo">
							<p class="price">月租：<span>3000/月</span></p>
							<p class="count">剩余套间：<span>2</span></p>
						</div>
					</div>
					</a>
				</li>
				<li>
					<a href="houseDetail.html" target="_blank">
					<div class="housePic"><img src="images/house_1.jpg" alt="被出租的房屋图片"></div>
					<div class="aboutText">
						<div class="text">
							<p class="contentTitle">邻近交通大学南门 交大东路西直门长河湾可长租可月付照片真实(个人)  </p>
							<p class="contentComment">房源标题(租):保利花园电梯三房带主套看房方便装修描述(租):此单位住家装修，保养还不错。雪白的墙身，显示得房屋光亮，户型方正。楼下环境好，温馨舒适。核心卖点(租):通风采光好生活便利户型实用配套:布局合理。采光以及通风好，旺中带静。房屋视野开阔.通风对流，让人感觉舒服。住起来比较温馨。小区介绍:属于中型社区，楼下环境可以，出入方便，配套成熟。交通方便，有多条线路直达广州任何一个区域我司房源编号为：NS98818</p>
							<p class="city"><span><img src="images/address.png"></span><span>广东省>广州市>天河区</span></p>
							<p class="adress">详细地址：<span>龙洞迎福路广东金融学院</span></p>
						</div>
						<div class="otherInfo">
							<p class="price">月租：<span>3000/月</span></p>
							<p class="count">剩余套间：<span>2</span></p>
						</div>
					</div>
					</a>
				</li>
			</ul>
			
		</div>
		
		<!-- 切换页面 -->
		<div id="switchPage">
			<!-- <span class="page" id="lastPage">上一页</span>
			<span class="page" id="choosePage">1</span>
			<span class="page">2</span>
			<span class="page">3</span>
			<span id="morePage">...</span>
			<span class="page">10</span>
			<span class="page" id="nextPage">下一页</span> -->
		</div>
	</div>
	<!-- 脚部网站信息 -->
	<footer>脚部相关信息</footer>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script src="js/index.js"></script>
</body>
<html>