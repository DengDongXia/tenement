$().ready(function() {
	aboutUser();    //获取用户是否登录信息，进行相关的功能操作
	getUnread(); //页面加载后获取所有未读信息
});

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

// 获取未读信息的函数
function getUnread() {
	$.ajax({
		// url: 'data/noticeInfo.json',
		url: './notice',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify({
			"type":"unread"
		}),
	})
	.done(function(obj) {
		if(obj.ret == 'true'){
			if(obj.data.length > 0){
				$.each(obj.data, function(index, val) {
					 var content = "<p>亲爱的用户：</p>";
					 content += "<p class='noticeContent'>"+val.comment+"</p>";
					 $('#allNoticeBg').append("<li>"+content+"</li>");
				});
			}else{
				 $('#allNoticeBg').append("<li><p class='error'>暂时没有任何未读消息</p></li>");
			}
		}	
		else{
			$('#allNoticeBg').append("<li><p class='error'>获取未读消息出错</p></li>");
		}
	})
	.fail(function() {
		$('#allNoticeBg').append("<li><p class='error'>获取消息出错</p></li>");
	})
	.always(function() {
		// console.log("complete");
	});
}

// 获取所有消息的函数
function getAllNotice() {
	$.ajax({
		// url: 'data/noticeInfo.json',
		url: './notice',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify({
			"type":"all"
		}),
	})
	.done(function(obj) {
		if(obj.ret == 'true'){
			if(obj.data.length > 0){
				$.each(obj.data, function(index, val) {
					 var content = "<p>亲爱的用户：</p>";
					 content += "<p class='noticeContent'>"+val.comment+"</p>";
					 $('#allNoticeBg').append("<li>"+content+"</li>");
				});
			}else{
				 $('#allNoticeBg').append("<li><p class='error'>暂时没有任何消息</p></li>");
			}
		}	
		else{
			$('#allNoticeBg').append("<li><p class='error'>获取消息出错</p></li>");
		}
	})
	.fail(function() {
		$('#allNoticeBg').append("<li><p class='error'>获取消息出错</p></li>");
	})
	.always(function() {
		// console.log("complete");
	});
}


/*以下为对获取相关消息的按钮进行监听，并做出相应的响应*/
$('#chooseNotice').find('span').click(function(event) {
	$('#chooseNotice').find('span').removeClass('choose');  //移除原有的选中类
	$(this).addClass('choose');   //给当前点击的对象添加choose类
	$('#allNoticeBg').children().remove();  //移除原有的消息内容
	if($(this).is('#unread')){
		getUnread();
	}else{
		getAllNotice();
	}
});

/*以上为对获取相关消息的按钮进行监听，并做出相应的响应*/



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
