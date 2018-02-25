<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>房屋详情页</title>
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="stylesheet" type="text/css" href="css/houseDetail.css">
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
		<!-- 房子详情轮播图 -->	
		<div class="content">
			<div id="detailPic">
				<span id="last"></span>
				<span id="next"></span>
				<ul id="picBg">
					<!-- <li><img alt="房子详情图片" src="images/house_1.jpg"></li>
					<li><img alt="房子详情图片" src="images/house_2.jpg"></li> -->
				</ul>
			</div>
			<div class="mainInfo">
				<div class="houseInfo">
					<p class="mainTitle">房源信息</p>
					<div class="textInfo">
						<p class="title" id="title"><!-- 2邻近交通大学南门 交大东路西直门长河湾可长租可月付照片真实(个人)  --></p>
						<p class="address" id="address"><img src="images/address.png" alt="定位的logo"><!-- <span>广东省</span>><span>广州市</span>><span>天河区</span><span class="moreAddress">详细地址：龙洞迎福路广东金融学院</span> --></p>
						<p class="commentTitle">详细描述:</p>
						<p class="comment" id="comment"><!-- <房源标题(租):保利花园电梯三房带主套看房方便装修描述(租):此单位住家装修，保养还不错。雪白的墙身，显示得房屋光亮，户型方正。楼下环境好，温馨舒适。核心卖点(租):通风采光好生活便利户型实用配套:布局合理。采光以及通风好，旺中带静。房屋视野开阔.通风对流，让人感觉舒服。住起来比较温馨。小区介绍:属于中型社区，楼下环境可以，出入方便，配套成熟。交通方便，有多条线路直达广州任何一个区域我司房源编号为：NS98818 --></p>
					</div>
					<div class="otherInfo">
						<p>月租：<span class="price" id="price"></span></p>
						<p><span id="count">剩余套间：</span></p>
						<p class="order" id="order"></a>下单</p>
					</div>
				</div>
			</div>
		</div>
		<!-- 脚部网站信息 -->
		<footer>脚部相关信息</footer>
	
	<!-- 遮罩层部分，创建订单 -->
		<!-- <div id="custom-made-question" class="custom-made-question">
			<div class="form-bg" id="form-bg">
				<div class="form-horizontal">
				<h4>进行特色定制</h4>
				  <div class="form-group">
				    <label for="title" class="col-sm-2 control-label">标题</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" id="questionTitle" placeholder="请输入定制问题的标题">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="content" class="col-sm-2 control-label">内容</label>
				    <div class="col-sm-10">
				    	<textarea class="form-control" id="questionContent" rows="15"></textarea>
				    </div>
				  </div>
				  <div class="form-group">
				    <div class="col-sm-offset-2 col-sm-10 publish">
				      <button type="submit" class="btn btn-default" id="submitQuestion">进行特色定制</button>
				    </div>
				  </div>
				</div>
			</div>
		</div> -->
	</div>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script src="js/houseDetail.js"></script>
</body>
<html>