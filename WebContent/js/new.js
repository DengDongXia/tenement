$().ready(function() {
	aboutUser();    //获取用户是否登录信息，进行相关的功能操作
	getNew(); //页面加载后获取所有新闻
	getNoticeNum();  //获取消息数量
});

// 异步请求消息数量
function getNoticeNum() {
	$.ajax({
		// url: 'data/notice.json',
		url: './notice',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify({
			"type":"count",
		}),
	})
	.done(function(obj) {
		if(obj.ret == 'true'){
			$("#countNotice").append("("+obj.data+")");
		}else{
			alert("消息数量获取失败原因："+obj.reason);
		}
	})
	.fail(function() {
		alert("消息数量获取失败");
	})
	.always(function() {
		// console.log("complete");
	});
}


/*以下为判断用户是否登录，从而显示对应的导航功能*/
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
				$('#nav').append("<li><a href='notice.jsp'>消息<span id='countNotice'></span></a></li>");
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

// 获取所有新闻的函数
function getNew() {
	$.ajax({
		// url: 'data/newInfo.json',
		url: './extra',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify({
			"type":"getPublish"
		}),
	})
	.done(function(obj) {
		if(obj.ret == 'true'){
			if(obj.data.length > 0){
				$.each(obj.data, function(index, val) {
					 var content = "<p class='time'>新闻发布时间："+val.updateTime+"</p>";
					 content += "<p class='newTitle'>"+val.title+"</p>";
					 content += "<p class='newContent'>"+val.content+"</p>";
					 $('#allNoticeBg').append("<li>"+content+"</li>");
				});
			}else{
				 $('#allNoticeBg').append("<li><p class='error'>暂时没有任何新闻</p></li>");
			}
		}	
		else{
			$('#allNoticeBg').append("<li><p class='error'>获取新闻出错</p></li>");
		}
	})
	.fail(function() {
		$('#allNoticeBg').append("<li><p class='error'>获取新闻出错</p></li>");
	})
	.always(function() {
		// console.log("complete");
	});
}


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
