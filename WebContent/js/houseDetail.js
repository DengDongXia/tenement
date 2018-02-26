$().ready(function() {
	aboutUser();    //获取用户是否登录信息，进行相关的功能操作
	getDetailInfo();  //获取该房子的详细信息
});

/*以下为判断用户是否登录，从而显示对应的导航功能*/
var isLogin = 'false'; //标记用户是否登录
var isLandlord = 'true'; //标记着用户是否是房东
function aboutUser() {
	$.ajax({
		// url: 'data/statu.json',
		url: './user',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify({
			"type":"statu"
		}),
	})
	.done(function(obj) {
		if(obj.ret == 'true'){
			if(obj.isLogin == 'true'){
				isLogin = 'true'; //用户状态为已登录
				isLandlord = obj.data.isLandlord;  //用户是否为房东
				$('#nav').append("<li><a href='personal.jsp'>个人中心</a></li>");
				$('#nav').append("<li id='logout'>注销</li> ");
				setLogout();   //绑定注销事件
			}else{
				$('#nav').append("<li><a href='login.jsp'>登录</a></li>");
				$('#nav').append("<li><a href='register.jsp' target='_blank'>注册</a>");
			}
		}
		else
			console("获取用户信息失败！原因：" + obj.reason);
	})
	.fail(function() {
		alert("页面获取出错");
	})
	.always(function() {
		// console.log("complete");
	});
}
/*以上为判断用户是否登录，从而显示对应的导航功能*/

//获取该房子的详细信息
function getDetailInfo() {
	var houseId = getUrlId('houseId');  //获取url中的客房id
	$.ajax({
		// url: 'data/updateHouse.json',
		url: './house',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify({
			"type":"info",
			"id":houseId
		}),
	})
	.done(function(obj) {
		if(obj.ret == 'true'){
			$.each(obj.data.pic, function(index, val) {
				 $('#picBg').append("<li><img alt='房子详情图片' src='"+val+"'></li>")
			});
			$('#title').append(obj.data.title);  //添加标题
			$('#address').append("<span>"+obj.data.province+"</span>><span>"+obj.data.city+"</span>><span>"+obj.data.region+"</span><span class='moreAddress'>详细地址："+obj.data.address+"</span>");
			$('#comment').append(obj.data.comment);  //添加详细内容
			$('#price').append(obj.data.price+"元/月");  //添加月租
			$('#count').append(obj.data.count);  //添加剩余數量
		}
		else
			alert("获取客房信息失败！原因：" + obj.reason);
	})
	.fail(function() {
		alert("页面获取出错");
	})
	.always(function() {
		// console.log("complete");
	});
}

//获取url中的参数，客房的id
function getUrlId(name) {
   var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
   var r = window.location.search.substr(1).match(reg);  //匹配目标参数
   if (r != null) return unescape(r[2]); return null; //返回参数值,即questionId的值
}

/*以下为轮播图切换图片代码*/
var picObj = $('#picBg>li:first-child'); //this保存当前显示的图片
$('#detailPic').find('span').click(function(event) {
	picObj.hide();
	if($(this).is('#last')){
		if(picObj.is('#picBg>li:first-child')){
			picObj = $('#picBg>li:last-child');
		}else{
			picObj = picObj.prev(); 
		}
		
	}else{
		if(picObj.is('#picBg>li:last-child')){
			picObj = $('#picBg>li:first-child');
		}else{
			picObj = picObj.next(); 
		}
	}
	picObj.show();
});
/*以上为轮播图切换图片代码*/


/*点击下单按钮，同时判断是否有下单的权利*/
$('#order').click(function(event) {
	if(isLogin == 'false'){
		alert("你尚未登录，无法进行下单");
		location.href = "./login.jsp";
	}else{
		if(isLandlord == 'true'){
			alert("很抱歉，只有租客有权利下单");
		}else{
			var houseId = getUrlId('houseId');  //获取url中的客房id
			$.ajax({
				url: './order',
				type: 'POST',
				dataType: 'json',
				data: JSON.stringify({
					"type":"publish",
					"houseId":houseId
				}),
			})
			.done(function(obj) {
				if(obj.ret == 'true'){
					alert("下单成功");
					location.href = "./personal.jsp";
				}
				else
					alert("下单失败！原因：" + obj.reason);
			})
			.fail(function() {
				alert("页面出错");
			})
			.always(function() {
				// console.log("complete");
			});
		}
	}	
});
/*点击下单按钮*/


/*以下为点击注销按钮时，触发注销事件*/
function setLogout(){
	$('#logout').click(function(event) {
	$.ajax({
		url: './user',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify({
			"type":"logout"
		}),
	})
	.done(function(obj) {
		if(obj.ret == 'true'){
			alert("注销成功");
			location.href = "./login.jsp";
		}
		else
			alert("注销失败！原因：" + obj.reason);
	})
	.fail(function() {
		alert("页面获取出错");
	})
	.always(function() {
		// console.log("complete");
	});
});
}
/*以上为点击注销按钮时，触发注销事件*/
