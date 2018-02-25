<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>管理员管理页面———房屋租赁系统</title>
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="stylesheet" type="text/css" href="css/index.css">
	<link rel="stylesheet" type="text/css" href="css/managePersonal.css">
</head>
<body>
	<div class="maincontent">
		<!-- 头部导航 -->
		<header>
			<nav>
				<div></div>
				<div class="shortCut detailNav">
					<ul>
						<li class="index"><a href="">房屋租赁系统管理页面</a></li>
						<li><a href="managePersonal.jsp">个人中心</a></li>
						<!-- <li><a href="notice.jsp">消息<span id="countNotice"></span></a></li>
						<li><a href="login.jsp">登录</a></li>
						<li><a href="register.jsp" target="_blank">注册</a></li>  -->
						<li id="lgout">注销</li> 
					</ul>		
				</div>
			</nav>
		</header>
		<!-- 个人信息 -->	
		<div class="content">
			<div class="userInfo">
				<div ><span id="imgBg"><img src="images/house.jpg" alt="用户头像"></span></div>
				<p class="owner">管理员</p>
			</div>
			<div class="orderInfos">
				<div class="allOrder" id='allOrder'>
					<!-- <span>暂时没有订单</span> -->
					<p class="chooseOrder" id='chooseOrder'><span id="auditing" class="choose">待审核订单</span><span id="allOrders">所有订单</span><span id='allUser'>所有用户</span></p>
					<!-- 管理员未审核的订单 -->
					<!-- <ul id="indeterminateInfo" class="orderInfo">
						<li>
							<div class="aboutText">
								<div class="text">
									<p class="orderNumber">订单编号：<span id='oNumber'>111122313</span></p>
									<a href="houseDetail.jsp" target="_blank">
									<p class="contentTitle">邻近交通大学南门 交大东路西直门长河湾可长租可月付照片真实(个人)  </p>
									</a>
									<p class="contentComment">房源标题(租):保利花园电梯三房带主套看房方便装修描述(租):此单位住家装修，保养还不错。雪白的墙身，显示得房屋光亮，户型方正。楼下环境好，温馨舒适。核心卖点(租):通风采光好生活便利户型实用配套:布局合理。采光以及通风好，旺中带静。房屋视野开阔.通风对流，让人感觉舒服。住起来比较温馨。小区介绍:属于中型社区，楼下环境可以，出入方便，配套成熟。交通方便，有多条线路直达广州任何一个区域我司房源编号为：NS98818</p>
									<p class="adress">详细地址：<span>龙洞迎福路广东金融学院</span></p>
									<p><label>月租：</label><span class="price">3000元/月</span></p>
									<p><span>客户编号：1525327</span><span>下单客户：萨拉拉</span><span>手机号：1199111111</span></p>
								</div>
								
								<div class="otherInfo">
									<p>房东信息</p>
									<p><span class="label">房东编号：</span>1113323</p>
									<p><span class="label">昵称：</span>LuLuLa</p>
									<p><span class="label">手机号：</span>14785236978</p>
									<p class="audit" id='audit'><span id="pass">通过</span><span id="noPass">不通过</span></p>
								</div>
							</div>
							
						</li>
					</ul>
 -->
					<!-- 管理员查询所有订单 -->
					<!-- <ul id="allordersInfo" class="orderInfo">
						<li>
							<div class="aboutText">
								<div class="text">
									<p class="orderNumber">订单编号：111122313</p>
									<a href="houseDetail.jsp" target="_blank">
									<p class="contentTitle">邻近交通大学南门 交大东路西直门长河湾可长租可月付照片真实(个人)  </p>
									</a>
									<p class="contentComment">房源标题(租):保利花园电梯三房带主套看房方便装修描述(租):此单位住家装修，保养还不错。雪白的墙身，显示得房屋光亮，户型方正。楼下环境好，温馨舒适。核心卖点(租):通风采光好生活便利户型实用配套:布局合理。采光以及通风好，旺中带静。房屋视野开阔.通风对流，让人感觉舒服。住起来比较温馨。小区介绍:属于中型社区，楼下环境可以，出入方便，配套成熟。交通方便，有多条线路直达广州任何一个区域我司房源编号为：NS98818</p>
									<p class="adress">详细地址：<span>龙洞迎福路广东金融学院</span></p>
									<p><label>月租：</label><span class="price">3000元/月</span></p>
									<p><span>客户编号：1525327</span><span>下单客户：萨拉拉</span><span>手机号：1199111111</span></p>
								</div>
								
								<div class="otherInfo">
									<p>房东信息</p>
									<p><span class="label">房东编号：</span>1113323</p>
									<p><span class="label">昵称：</span>LuLuLa</p>
									<p><span class="label">手机号：</span>14785236978</p>
									<p id="ok"><span class="label">状态：</span>已审核</p>
								</div>
							</div>
							
						</li>
					</ul> -->

					<!-- 管理员查询所有用户 -->
						<!-- <ul id="userInfos" class="">
							<li><span class="number">编号</span><span class="name">用户昵称</span><span class="identity">身份</span><span class="phone">手机号</span><span class="email">邮箱</span></li>
							<li><span class="number">123456</span><span class="name">升水的</span><span class="identity">租客</span><span class="phone">12365487901</span><span class="email">12@qq.com</span></li>
						</ul> -->

				</div>
			</div>
		</div>
		<!-- 脚部网站信息 -->
		<footer>脚部相关信息</footer>
	</div>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script src="js/managePersonal.js"></script>
</body>
<html>