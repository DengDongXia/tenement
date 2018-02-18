$().ready(function() {
	getUser();
	getEssay();
	getComments();
});

// 发起请求，判断当前用户是否登录
function getUser(argument) {
	$.ajax({
		url: 'data/userdata.json',
		type: 'post',
		dataType: 'json',
	})
	.done(function(data) {
		dealingUserData(data);
	})
	.fail(function() {
		console.log("error");
	})
	.always(function() {
		console.log("complete");
	});
	
}

// 对后台返回的用户登录信息进行处理
function dealingUserData(data) {
	if(data[0].userId == null || data[0].userId == ''){
		var login = "<li><a href='login.html'><i class='fa fa-meh-o'> </i>登录</a></li>";
		var register = "<li><a target='_blank' href='register.html'><i class='fa fa-plus'> </i>注册</a></li>";
		$('#top-menu').append(login+register);
	}else{
		if(data[0].userName == null || data[0].userName == ''){
			var personal = "<li><a href='personal.html'><i class='fa fa-meh-o'> </i>个人中心</a></li>";
		}else{
			var personal = "<li><a href='personal.html'><i class='fa fa-meh-o'> </i>"+data[0].userName+"</a></li>";
		}
		$('#top-menu').append(personal);
	}
}

// 页面加载后进行异步请求文章数据
function getEssay(argument) {
	/*$.ajax({
		url: 'essay/detail.json',
		type: 'get',
		dataType: 'json',
		data: {essayId: getUrlEssayId('essayId')},
	})
	.done(function() {
		dealingEssayData(data);
	})
	.fail(function() {
		console.log("error");
	})
	.always(function() {
		console.log("complete");
	});

	//获取url中的参数
       function getUrlEssayId(name) {
           var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
           var r = window.location.search.substr(1).match(reg);  //匹配目标参数
           // alert(r[1]);
           if (r != null) return unescape(r[2]); return null; //返回参数值,即questionId的值
       }*/
	
	// 未连接后台时
	$.getJSON('data/detail.json', function(data) {
		dealingEssayData(data);
	});
	// $.getJSON('essay/show', function(data) {
	// });
}

// 对请求返回的数据进行处理
function dealingEssayData(data) {
	// 插入文章作者信息
	$("#authorPicture").append("<img src='"+data[0].eassayPersonPictrue+"'>");
	$("#authorPicture").parent().append("<span>"+data[0].eassayPersonName+"</span>");
	// 插入文章具体信息
	$("#eassayTag").append("<span class='tag'>"+data[0].eassaycountry+"</span><span>"+data[0].eassayTime+"</span>");
	$("#eassayContent").append("<h3>"+data[0].eassayHeader+"</h3><h4>"+data[0].eassayContent+"</h4><img src='"+data[0].eassayPicture+"' alt='攻略所示图片'>");
	// 插入评论信息

}

// 页面加载后进行异步请求文章评论数据
function getComments() {
	$.getJSON('data/eassayComment.json', function(data) {
		dealingCommentData(data);
	});
}

//处理返回的全部评论信息
function dealingCommentData(data) {
	$.each(data, function(index, val) {
		 $("#comments").append("<li><span>"+val.commentUser+"</span><span>"+val.commentContent+"</span></li>");
	});
	
}

$('#comment-input').focus(function(event) {
	$.ajax({
		url: 'data/userdata.json',
		type: 'post',
		dataType: 'json',
	})
	.done(function(data) {
		if(data[0].userId == null || data[0].userId == ''){
			window.location.href = "login.html";
		}else{
		// 对提交的评论进行处理
		$("#send").click(function() {
			$.get('comments/picture/submit',{
												//将对应评论的用户id传过去
				comment : $("#comment").val(),   //将对应评论的内容传过去
				userId : data[0].userId          //将进行评论的用户的id传过去
			}, function(data) {  //回调函数对返回的值进行插入
			    $("#comments").append("<li><span>"+data[0].commentUser+"</span><span>"+data[0].commentContent+"</span></li>");
			});
		});
		}
	})
	.fail(function() {
		console.log("error");
	})
	.always(function() {
		console.log("complete");
	});
	
});



