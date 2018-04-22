$().ready(function() {
	isadminLogin();   //判断是否是管理员已经登录,并获取相关数据
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

//判断用户是否已经登录，未登录则跳转到登录页面
// 还没进行是否是管理员的判断
function isadminLogin() {
	$.ajax({
		// url: 'data/adminStatus.json',
		url: './admin',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify({
			"type":"statu"
		}),
	})
	.done(function(obj) {
		if(obj.ret == 'true'){
			if(obj.isAdmin != 'true'){
				location.href = './login.jsp';   //当用户还没有登录，跳转到登录界面
			}
			else{
				getAllInfo(); //加载页面信息
			}
		}
		else
			alert("页面获取出错！原因：" + obj.reason);
	})
	.fail(function() {
		alert("页面获取出错");
	})
	.always(function() {
		// console.log("complete");
	});
}

// 获取所有信息
function getAllInfo() {
	getAllAuditInfo("1");  //获取所有待审核的订单信息
	$('#chooseOrder').find('span').click(function(event) {
		$('#chooseOrder').find('span').removeClass('choose');
		$(this).addClass('choose');
		$('#allOrder').find('ul').remove(); //移除原有的信息
		if($(this).is('#auditing')){
			getAllAuditInfo("1");  //获取所有待审核的订单信息
		}
		else if($(this).is('#allOrders')){
			getAllOrder("1"); //获取所有订单
		}
		else if($(this).is("#published")){
			getPublishInfo("1"); //获取所有客房
		}
		else{
			getAllUsers("1");   //获取所有用户
		}
	});
}


/*以下为管理员获取对应信息的函数*/
//获取待审核的订单信息
var nowNoOkPage = "1"; //保存页号数
function getAllAuditInfo(toPage) {
	$.ajax({
		// url: 'data/order.json',
		url: './order',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify({
			"type":"get",
			"filter": "unConfirm",
			"toPage": toPage
		}),
	})
	.done(function(obj) {
		if(obj.ret == 'true'){
			nowNoOkPage = obj.nowPage; //保存页号数
			dealAllAuditInfo(obj);  //处理返回的信息
			if(obj.data.length > 5)
				addButton(nowNoOkPage,obj.sumPage,0); //添加分页按钮
		}
		else
			alert("获取信息出错！原因：" + obj.reason);
	})
	.fail(function() {
		alert("页面获取出错");
	})
	.always(function() {
		// console.log("complete");
	});
}

// 处理获取待审核的订单信息后返回的信息
function dealAllAuditInfo(obj) {
	$('#allOrder').find('ul').remove(); //移除原有的信息
	$('#allOrder').append("<ul id='indeterminateInfo' class='orderInfo'></ul>");  //插入背景
	$.each(obj.data, function(index, val) {
		// 文本
		var contentText = "<p class='orderNumber'>订单编号：<span id='oNumber'>"+val.id+"</span></p>";
			contentText += "<a href='houseDetail.jsp?houseId="+val.houseId+"' target='_blank'><p class='contentTitle'>"+val.houseTitle+"</p></a>";
			contentText += "<p class='contentComment'>"+val.houseComment+"</p>";
		var contentAddress = "<p class='adress'>详细地址：<span>"+val.houseAddress+"</span></p>";
			contentAddress += "<p><label>月租：</label><span class='price'>"+val.housePrice+"元/月</span></p>";
			contentAddress += "<p><span>客户编号："+val.userId+"</span><span>下单客户："+val.userName+"</span><span>手机号："+val.userPhone+"</span></p>";
		var contents = "<div class='text'>"+contentText+contentAddress+"</div>";
		// 其他信息
		var otherInfo = "<p>房东信息</p>";
			otherInfo += "<p><span class='label'>房东编号：</span>"+val.publisherId+"</p>";
			otherInfo += "<p><span class='label'>昵称：</span>"+val.publisherName+"</p>";
			otherInfo += "<p><span class='label'>手机号：</span>"+val.publisherPhone+"</p>";
			otherInfo += "<p class='audit'><span id='pass'>通过</span><span id='noPass'>不通过</span></p>";
		var others = "<div class='otherInfo'>"+otherInfo+"</div>";
		//文本和其他信息合并
		var allContents = "<div class='aboutText'>"+contents+others+"</div>";
		//添加完整的li
		$('#indeterminateInfo').append("<li>"+allContents+"</li>");
	});
	clickAudit();  //调用函数，监听审核的按钮
}

//获取所有订单
var nowAllOrderPage = "1" ;
function getAllOrder(toPage) {
	$.ajax({
		// url: 'data/order.json',
		url: './order',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify({
			"type":"get",
			"filter": "all",
			"toPage": toPage
		}),
	})
	.done(function(obj) {
		if(obj.ret == 'true'){
			nowAllOrderPage = obj.nowPage; //保存页号数
			dealAllOrderInfo(obj);  //处理返回的信息
			if(obj.data.length > 5)
				addButton(nowAllOrderPage,obj.sumPage,1); //添加分页按钮
		}
		else
			alert("获取信息出错！原因：" + obj.reason);
	})
	.fail(function() {
		alert("页面获取出错");
	})
	.always(function() {
		// console.log("complete");
	});
}

//处理获取所有订单后返回的信息
function dealAllOrderInfo(obj){
	$('#allOrder').find('ul').remove(); //移除原有的信息
	$('#allOrder').append("<ul id='allordersInfo' class='orderInfo'></ul>");  //插入背景
	$.each(obj.data, function(index, val) {
		// 文本
		var contentText = "<p class='orderNumber'>订单编号："+val.id+"</p>";
			contentText += "<a href='houseDetail.jsp?houseId="+val.houseId+"' target='_blank'><p class='contentTitle'>"+val.houseTitle+"</p></a>";
			contentText += "<p class='contentComment'>"+val.houseComment+"</p>";
		var contentAddress = "<p class='adress'>详细地址：<span>"+val.houseAddress+"</span></p>";
			contentAddress += "<p><label>月租：</label><span class='price'>"+val.housePrice+"元/月</span></p>";
			contentAddress += "<p><span>客户编号："+val.userId+"</span><span>下单客户："+val.userName+"</span><span>手机号："+val.userPhone+"</span></p>";
		var contents = "<div class='text'>"+contentText+contentAddress+"</div>";
		// 其他信息
		var otherInfo = "<p>房东信息</p>";
			otherInfo += "<p><span class='label'>房东编号：</span>"+val.publisherId+"</p>";
			otherInfo += "<p><span class='label'>昵称：</span>"+val.publisherName+"</p>";
			otherInfo += "<p><span class='label'>手机号：</span>"+val.publisherPhone+"</p>";
			if(val.confirm == 1){
				otherInfo += "<p id='ok'><span class='label'>状态：</span>审核通过</p>";
			}else if(val.confirm == -1){
				otherInfo += "<p id='ok'><span class='label'>状态：</span>审核未通过</p>";
			}else if(val.confirm == 0){
				otherInfo += "<p id='ok'><span class='label'>状态：</span>待房东确认</p>";
			}else{
				otherInfo += "<p id='ok'><span class='label'>状态：</span>待审核</p>";
			}
		var others = "<div class='otherInfo'>"+otherInfo+"</div>";
		//文本和其他信息合并
		var allContents = "<div class='aboutText'>"+contents+others+"</div>";
		//添加完整的li
		$('#allordersInfo').append("<li>"+allContents+"</li>");
	});
}
/*以下为管理员获取对应信息的函数*/
//获取已发布的客房信息
var nowPublishPage = "1"; //保存页号数
function getPublishInfo(toPage) {
	$.ajax({
		// url: 'data/house.json',
		url: './house',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify({
			"type":"all",
			"toPage": toPage,
		}),
	})
	.done(function(obj) {
		if(obj.ret == 'true'){
			nowPublishPage = obj.nowPage; //保存页号数
			dealPublishInfo(obj);  //处理返回的信息
			addButton(nowPublishPage,obj.sumPage,4); //添加分页按钮
		}
		else
			alert("获取信息出错！原因：" + obj.reason);
	})
	.fail(function() {
		alert("页面获取出错");
	})
	.always(function() {
		// console.log("complete");
	});
}

//处理获取已发布的客房信息后返回的信息
function dealPublishInfo(obj) {
	$('#allOrder').find('ul').remove(); //移除原有的信息
	$('#allOrder').append("<ul id='publishedInfo'></ul>");  //插入背景
	$.each(obj.data, function(index, val) {
		// 照片
		var pics = "<div class='housePic'><img src='"+val.pic[0]+"' alt='被出租的房屋图片'></div>";
		// 文本
		var contentText = "<p class='contentTitle'>"+val.title+"</p><p class='contentComment'>"+val.comment+"</p>";
		var contentAddress = "<p class='city'><span><img src='images/address.png'></span><span>"+val.province+">"+val.city+">"+val.region+"</span></p>";
			contentAddress += "<p class='adress'>详细地址：<span>"+val.address+"</span></p>";
		var contents = "<div class='text'>"+contentText+contentAddress+"</div>";
		// 其他信息
		var otherInfo = "<p class='price'>月租：<span>"+val.price+"元/月</span></p>";
			otherInfo += "<p class='count'>剩余套间：<span>"+val.count+"</span></p>";
			//otherInfo += "<p><span class='delete' id='delete'>删除</span>";
			// otherInfo += "<a href='updateHouseInfoManage.jsp?houseId="+val.id+"'><span class='edit' id='edit'>编辑</span></p></a>";
		var others = "<div class='otherInfo'>"+otherInfo+"</div>";
		//文本和其他信息合并
		var allContents = "<div class='aboutText'>"+contents+others+"</div>";
		//添加完整的li
		$('#publishedInfo').append("<li>"+pics+allContents+"</li>");
	});
	//clickDelete();  //对获取客房信息后的删除键进行监听
}

//获取所有用户
var nowAllUserPage = "1" ;
function getAllUsers(toPage) {
	$.ajax({
		// url: 'data/allUsers.json',
		url: './user',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify({
			"type":"all"
		}),
	})
	.done(function(obj) {
		if(obj.ret == 'true'){
			nowAllUserPage = obj.nowPage; //保存页号数
			dealAllUsersInfo(obj);  //处理返回的信息
			/*if(obj.data.length > 5)
				addButton(nowAllUserPage,obj.sumPage,2);*/ //添加分页按钮
		}
		else
			alert("获取信息出错！原因：" + obj.reason);
	})
	.fail(function() {
		alert("页面获取出错");
	})
	.always(function() {
		// console.log("complete");
	});
}

// 处理获取所有用户返回的数据
function dealAllUsersInfo(obj) {
	$('#allOrder').find('ul').remove(); //移除原有的信息
	$('#allOrder').append("<ul id='userInfos' class='orderInfo'></ul>");  //插入背景
	$('#userInfos'). append('<li><span class="number">编号</span><span class="name">用户昵称</span><span class="identity">身份</span><span class="phone">手机号</span><span class="email">邮箱</span><span class="action">操作</span></li>');
	$.each(obj.data, function(index, val) {
		// 文本
		var liText = "<span class='number'>"+val.id+"</span><span class='name'>"+val.userName+"</span>";
			if(val.isLandlord == true){
				liText +="<span class='identity'>房东</span>";
			}else{
				liText +="<span class='identity'>租客</span>";
			}
			liText +="<span class='phone'>"+val.phone+"</span><span class='email'>"+val.email+"</span>";
			liText +="<span class='change' data-id='"+val.id+"' data-phone='"+val.phone+"' data-name='"+val.userName+"'>编辑信息</span>"
		//添加完整的li
		$('#userInfos').append("<li>"+liText+"</li>");
		prepareChange();
	});
}
/*以上为管理员获取对应信息的函数*/
/*修改资料部分*/
function prepareChange(){
	$('.change').each(function(){
		
		$(this).click(function(id, phone, name){
			$('#newName').val($(this).attr("data-name"));
			$('#newPhone').val($(this).attr("data-phone"));
			$('#new-UserId').val($(this).attr("data-id"));
			$('.changedataBg').show();
		});
	});
}

$('#createNew').click(function(){
	$('.createNew').show();
});

$('.createNew').click(function(){
	$('.createNew').hide();
});

$('.changedataBg').click(function(event) {
	$('.changedataBg').hide();
});

$('.mainData').click(function(event) {
		event.stopPropagation();  //停止父元素的点击事件冒泡
	});

// 提交修改事件的参数
$('#okChange').click(function(event) {
	if($('#newName').val() == '' || $('#newPhone').val() == ''){
		alert("请将表单填写完整再提交");
	}else{
		var phoneReg = /^1[3|4|5|7|8][0-9]{9}$/;  //手机匹配的正则表达式
		if(!phoneReg.test($('#newPhone').val())){
			alert("请输入正确的手机号码");
		}else{
			$.ajax({
				url: './user',
				type: 'POST',
				dataType: 'json',
				data: JSON.stringify({
					"type":"edit",
					"id": $('#new-UserId').val(),
					"name":$('#newName').val(),
					"phone":$('#newPhone').val()
				}),
			})
			.done(function(obj) {
				if(obj.ret == 'true'){
					alert("修改成功！");
					window.location.href = "./managePersonal.jsp";
				}
				else
					alert("修改失败！原因：" + obj.reason);
			})
			.fail(function() {
				alert("页面获取出错");
			})
			.always(function() {
				// console.log("complete");
			});
		}
	}
});
/*修改资料部分*/

/*对客房或订单信息进行的相关操作*/
//添加分页按钮
// type表示当前页面信息类型，0：获取发表的客房信息；1：未确定订单；2：房东所有订单；3：租客所有订单  4管理员所有客房
function addButton(nowPage,sumPage,type) {
	$('#switchPage').remove();
	$('#allOrder').find('ul').append("<p id='switchPage'></p>");
	var button = "<span id='first'>首页</span><span id='last'>上一页</span>";
		button += "<span id='next'>下一页</span><span id='end'>尾页</span>";
	$('#switchPage').append(button);
	// 为按钮绑定监听事件
	$('#switchPage').find('span').click(function(event) {
		if($(this).is('#first')){
			nowPage = "1";
		}
		else if($(this).is('#last')){
			if(parseInt(nowPage) > 1){
				nowPage =parseInt(nowPage) - 1;
			}else{
				nowPage = "1";
			}
		}
		else if($(this).is('#next')){
			if(parseInt(nowPage) < parseInt(sumPage)){
				nowPage =parseInt(nowPage) + 1;
			}else{
				nowPage = sumPage;
			}
		}
		else{
			nowPage = sumPage;
		}
		nowPage += ''; //转化为字符串

		// 判断触发，即要获取的是哪一个信息
		if(type == 0){
			getAllAuditInfo(nowPage);
		}
		else if(type == 1){
			getAllOrder(nowPage);
		}else if(type == 4){
			getPublishInfo(nowPage);
		}
		else{
			getAllUsers(nowPage);
		}
	});
}

// 获取要跳转的地址对应的列表的客房id
function getId(link,name) {
	//var link = obj.parent().find('a').prop('href');  //获取要跳转的地址
	var items = link.split('?');     //将地址从?进行分开
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = items[1].match(reg);  //匹配目标参数
    var id = r[2];      //对应的id值  
    return id;
}

//对获取订单信息后的通过与不通过按钮键进行监听
function clickAudit() {
	$('.audit').find('span').click(function(event) {
		var id = $(this).parents('li').find('#oNumber').text();   //获取对应订单id号
		var okObj = $(this);
		if(okObj.is('#pass')){
			var action = "1";
		}else{
			var action = "-1";
		}
		$.ajax({
			// url: 'data/okOrder.json',
			url: './order',
			type: 'POST',
			dataType: 'json',
			data: JSON.stringify({
				"type":"audit",
				"id": id,
				"action":action
			}),
		})
		.done(function(obj) {
			if(obj.ret == 'true'){
				okObj.parents('li').remove();  //找到对应的li，并移除
			}
			else
				alert("审核订单出错！原因：" + obj.reason);
		})
		.fail(function() {
			alert("审核订单出错");
		})
		.always(function() {
			// console.log("complete");
		});
	});
}

/*对客房或订单信息进行的相关操作*/

/*发布新闻*/
$('#publishNew').click(function(){
	if($('#newTitle').val() == '' || $('#newContent').val() == ''){
		alert("请先将表单填写完整");
	}else{
		$.ajax({
			// url:'data/createNew.json',
			url: './extra',
			type: 'POST',
			dataType: 'json',
			data: JSON.stringify({
				"type":"publish",
				"title":$('#newTitle').val(),
				"content":$('#newContent').val()
			}),
		})
		.done(function(obj) {
			if(obj.ret == 'true'){
				alert("发布成功！");
				location.href = "./managePersonal.jsp";
			}
			else
				alert("发布出错！");
		})
		.fail(function() {
			alert("页面出错");
		})
		.always(function() {
			// console.log("complete");
		});
	}
});
/*发布新闻*/


/*以下为点击注销按钮时，触发注销事件*/
$('#logout').click(function(event) {
	$.ajax({
		url: './admin',
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
/*以上为点击注销按钮时，触发注销事件*/