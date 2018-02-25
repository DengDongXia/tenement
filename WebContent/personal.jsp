<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>个人中心———房屋租赁系统</title>
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="stylesheet" type="text/css" href="css/index.css">
	<link rel="stylesheet" type="text/css" href="css/personal.css">
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
						<li id="logout">注销</li> 
					</ul>		
				</div>
			</nav>
		</header>
		<!-- 个人信息 -->	
		<div class="content">
			<div class="userInfo" id="userInfo">
				<div ><span id="imgBg"><img src="images/house.jpg" alt="用户头像"></span></div>
				<p class="owner" id="owner"><!-- 房东 --></p>
				<p><span class="label">昵称：</span><span id="userName"><!-- LuLuLa --></span></p>
				<p><span class="label">邮箱：</span><span id="email"><!-- 1111@qq.com --></span></p>
				<p><span class="label">手机号码：</span><span id="phone"><!-- 13661192011 --></span></p>
				<!-- <a href="createHouseInfo.jsp"><p id="create"><span>发布新客房信息</span></p></a> -->
			</div>
			<div class="orderInfos">
				<div class="orderTitle">所有订单</div>
				<div class="allOrder" id="allOrder">
					<!-- <span>暂时没有订单</span> -->
					<p class="chooseOrder" id="chooseOrder"><span id="published" class="choose">已发布的客房信息</span><span id="indeterminate">待确认订单</span><span id="allorders">所有订单</span></p>
					<!-- 房东已发布的所有信息 -->
					<!-- <ul id="publishedInfo">
						<li>
							<div class="housePic"><img src="images/house_1.jpg" alt="被出租的房屋图片"></div>
							<div class="aboutText">
								<a href="houseDetail.jsp" target="_blank">
									<div class="text">
										<p class="contentTitle">邻近交通大学南门 交大东路西直门长河湾可长租可月付照片真实(个人)  </p>
										<p class="contentComment">房源标题(租):保利花园电梯三房带主套看房方便装修描述(租):此单位住家装修，保养还不错。雪白的墙身，显示得房屋光亮，户型方正。楼下环境好，温馨舒适。核心卖点(租):通风采光好生活便利户型实用配套:布局合理。采光以及通风好，旺中带静。房屋视野开阔.通风对流，让人感觉舒服。住起来比较温馨。小区介绍:属于中型社区，楼下环境可以，出入方便，配套成熟。交通方便，有多条线路直达广州任何一个区域我司房源编号为：NS98818</p>
										<p class="city"><span><img src="images/address.png"></span><span>广东省>广州市>天河区</span></p>
										<p class="adress">详细地址：<span>龙洞迎福路广东金融学院</span></p>
									</div>
								</a>
								<div class="otherInfo">
									<p class="price">月租：<span>3000/月</span></p>
									<p class="count">剩余套间：<span>2</span></p>
									<p><span class="delete" id="delete">删除</span><a href="updateHouseInfo.jsp?houseId=1"><span class="edit" id="edit">编辑</span></p></a>
								</div>
							</div>
							
						</li>
					</ul> -->

					<!-- 该房东未确定的订单 -->
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
									<p><span id="ok" class='ok'>确认订单</span></p>
								</div>
							</div>
							
						</li>
					</ul> -->

					<!-- 房东的所有订单 -->
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
									<p id="ok"><span class="label">状态：</span>待确定</p>
								</div>
							</div>
						</li>
					</ul> -->

					<!-- 租客的所有订单 -->
					<!-- <ul id="allReordersInfo" class="orderInfo">
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
									<p><span>房东编号：1525327</span><span>房东名称：萨拉拉</span><span>手机号：1199111111</span></p>
								</div>
								
								<div class="otherInfo">
									<p>租客信息</p>
									<p><span class="label">客户编号：</span>1113323</p>
									<p><span class="label">昵称：</span>LuLuLa</p>
									<p><span class="label">手机号：</span>14785236978</p>
									<p id="ok"><span class="label">状态：</span>待审核</p>
									<p id="ok"><span class="label">状态：</span>已确定</p>
								</div>
							</div>
							
						</li>
					</ul> -->

				</div>
			</div>
		</div>
		<!-- 脚部网站信息 -->
		<footer>脚部相关信息</footer>
	</div>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script src="js/personal.js"></script>
</body>
<html>