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
						<li><a href="new.jsp">查看新闻</a></li>
						<li><a href="notice.jsp">消息<span id="countNotice"></span></a></li>
						<li><a href="personal.jsp">个人中心</a></li>
						<li id="contact">联系管理员</li>
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
				<p class='change' id="changeDataP"><span id='changeData'>修改资料</span></p>
				<!-- <a href="createHouseInfo.jsp"><p id="create"><span>发布新客房信息</span></p></a> -->
			</div>
			<div class="orderInfos">
				<div class="orderTitle">所有信息</div>
				<div class="allOrder" id="allOrder">
					<!-- <span>暂时没有订单</span> -->
					<p class="chooseOrder" id="chooseOrder"><span id="published" class="choose">已发布的客房信息</span><span id="indeterminate">待确认订单</span><span id="allorders">所有订单</span><span id="allCollect">查看收藏</span></p>
				</div>
			</div>
		</div>
		<!-- 脚部网站信息 -->
		<footer>脚部相关信息</footer>

		<!-- 遮罩层修改资料部分 -->
		<div class="changedataBg">
			<div class="mainData">
				<p><span>修改个人资料</span></p>
				<p><input type="text" name="" id='newName' placeholder='新的昵称'></p>
				<p><input type="text" name="" id='newPhone' placeholder='新的手机号'></p>
				<p><input type="button" name="" id='okChange' value='确定修改'></p>
			</div>
		</div>

		<!-- 向管理员发送消息 -->
		<div class="contactMange">
				<div class="mainData">
					<p><span>向管理员发送消息</span></p>
					<p><textarea name="" id="message" cols="45" rows="7" placeholder="发送的内容"></textarea></p>
					<p><input type="button" name="" id='sendMessage' value='发送'></p>
				</div>
		</div>
	</div>
	<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
	<script src="js/personal.js"></script>
</body>
</html>