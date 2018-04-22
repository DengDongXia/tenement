$().ready(function() {
	isLogin();   //判断用户是否已经登录
	getData();
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
function isLogin() {
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
			if(obj.isLogin != 'true'){
				location.href = './login.jsp';   //当用户还没有登录，跳转到登录界面
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

// 获取当前用户信息,同时调用函数获取订单信息
var publisherId; //用于保存房东的id
var renterId; //用于保存租客的id
function getData() {
	$.ajax({
		// url: 'data/user.json',
		url: './user',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify({
			"type":"info"
		}),
	})
	.done(function(obj) {
		if(obj.ret == 'true'){
			var userInfo = obj.data[0]; 
			isLandlord = userInfo.isLandlord;
			if(userInfo.isLandlord == true){
				publisherId = userInfo.id; //保存房东的id
				$('#owner').append('房东');
				$('#userInfo').append("<a href='createHouseInfo.jsp'><p id='create'><span>发布新客房信息</span></p></a>");
			}else{
				$('#owner').append('租客');
				renterId = userInfo.id; //保存租客的id
			}
			$('#userName').append(userInfo.userName);
			$('#email').append(userInfo.email);
			$('#phone').append(userInfo.phone);
			getAllInfo(isLandlord);
		}
		else
			alert("获取用户信息出错！原因：" + obj.reason);
	})
	.fail(function() {
		alert("页面获取出错");
	})
	.always(function() {
		// console.log("complete");
	});
}

// 获取所有信息
function getAllInfo(isLandlord) {
	if(isLandlord == true){
		getPublishInfo("1");  //获取已发布的客房信息
		$('#chooseOrder').find('span').click(function(event) {
			$('#chooseOrder').find('span').removeClass('choose');
			$(this).addClass('choose');
			$('#allOrder').find('ul').remove(); //移除原有的信息
			if($(this).is('#published')){
				getPublishInfo("1");  //获取已发布的客房信息
			}
			else if($(this).is('#indeterminate')){
				getIndeterminateInfo("1"); //获取待确认订单
			}
			else if($(this).is('#allorders')){
				getAllOrder("1");   //获取所有订单
			}
			else{
				getAllCollect();   //获取所有收藏
			}
		});
	}else{
		$('#chooseOrder').remove();  //移除房东才拥有的功能键
		getRenterOrder("1");    //租客查看其所有订单
	}
}

/*以下为房东获取对应信息的函数*/
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
			"filter": "publisherId",
			"id": publisherId,
			"sort":"timeDown"
		}),
	})
	.done(function(obj) {
		if(obj.ret == 'true'){
			nowPublishPage = obj.nowPage; //保存页号数
			dealPublishInfo(obj);  //处理返回的信息
			addButton(nowPublishPage,obj.sumPage,0); //添加分页按钮
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

// 处理获取已发布的客房信息后返回的信息
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
		var contents = "<a href='houseDetail.jsp?houseId="+val.id+"' target='_blank'><div class='text'>"+contentText+contentAddress+"</div></a>";
		// 其他信息
		var otherInfo = "<p class='price'>月租：<span>"+val.price+"元/月</span></p>";
			otherInfo += "<p class='count'>剩余套间：<span>"+val.count+"</span></p>";
			otherInfo += "<p><span class='delete' id='delete'>删除</span>";
			otherInfo += "<a href='updateHouseInfo.jsp?houseId="+val.id+"'><span class='edit' id='edit'>编辑</span></p></a>";
		var others = "<div class='otherInfo'>"+otherInfo+"</div>";
		//文本和其他信息合并
		var allContents = "<div class='aboutText'>"+contents+others+"</div>";
		//添加完整的li
		$('#publishedInfo').append("<li>"+pics+allContents+"</li>");
	});
	clickDelete();  //对获取客房信息后的删除键进行监听
}

//获取待确认订单
var nowNoOkPage = "1" ;
function getIndeterminateInfo(toPage) {
	$.ajax({
		// url: 'data/order.json',
		url: './order',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify({
			"type":"get",
			"filter": "publisherIdUnconfirm",
			"toPage": toPage,
			"id": publisherId
		}),
	})
	.done(function(obj) {
		if(obj.ret == 'true'){
			nowNoOkPage = obj.nowPage; //保存页号数
			dealIndeterminateInfo(obj);  //处理返回的信息
			addButton(nowNoOkPage,obj.sumPage,1); //添加分页按钮
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

//处理获取未确定订单后返回的信息
function dealIndeterminateInfo(obj){
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
			otherInfo += "<p><span id='ok' class='ok'>确认订单</span></p>";
		var others = "<div class='otherInfo'>"+otherInfo+"</div>";
		//文本和其他信息合并
		var allContents = "<div class='aboutText'>"+contents+others+"</div>";
		//添加完整的li
		$('#indeterminateInfo').append("<li>"+allContents+"</li>");
	});
		okOrder();  //对获取订单信息后的确定订单键进行监听
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
			"filter": "publisherId",
			"toPage": toPage,
			"id": publisherId
		}),
	})
	.done(function(obj) {
		if(obj.ret == 'true'){
			nowAllOrderPage = obj.nowPage; //保存页号数
			dealAllOrderInfo(obj);  //处理返回的信息
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

// 处理获取所有订单返回的数据
function dealAllOrderInfo(obj) {
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
/*以上为房东获取对应信息的函数*/

/*对客房或订单信息进行的相关操作*/
//添加分页按钮
// type表示当前页面信息类型，0：获取发表的客房信息；1：未确定订单；2：房东所有订单；3：租客所有订单
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
			getPublishInfo(nowPage);
		}
		else if(type == 1){
			getIndeterminateInfo(nowPage);
		} 
		else if(type == 2){
			getAllOrder(nowPage);
		}
		else{
			 getRenterOrder(nowPage);
		}
	});
}

// 对获取客房信息后的删除键进行监听
function clickDelete() {
	$('.delete').click(function(event) {
		var link = $(this).parent().find('a').prop('href');  //获取要跳转的地址
		var id = getId(link,'houseId');   //获取对应客房id号
		var deleteObj = $(this);
		$.ajax({
			// url: 'data/delete.json',
			url: './house',
			type: 'POST',
			dataType: 'json',
			data: JSON.stringify({
				"type":"delete",
				"id": id
			}),
		})
		.done(function(obj) {
			if(obj.ret == 'true'){
				deleteObj.parents('li').remove();  //找到对应的li，并移除
			}
			else
				alert("删除出错！原因：" + obj.reason);
		})
		.fail(function() {
			alert("删除出错");
		})
		.always(function() {
			// console.log("complete");
		});
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

//对获取订单信息后的确定订单键进行监听
function okOrder() {
	$('.ok').click(function(event) {
		var id = $(this).parents('li').find('#oNumber').text();   //获取对应订单id号
		var okObj = $(this);
		$.ajax({
			// url: 'data/okOrder.json',
			url: './order',
			type: 'POST',
			dataType: 'json',
			data: JSON.stringify({
				"type":"confirm",
				"id": id
			}),
		})
		.done(function(obj) {
			if(obj.ret == 'true'){
				okObj.parents('li').remove();  //找到对应的li，并移除
			}
			else
				alert("确定订单失败！原因：" + obj.reason);
		})
		.fail(function() {
			alert("确定订单失败");
		})
		.always(function() {
			// console.log("complete");
		});
	});
}

/*对客房或订单信息进行的相关操作*/

/*以下为租客获取对应信息的函数*/
var nowOrderPage = "1" ;
function getRenterOrder(toPage) {
	$('#allOrder').find('ul').remove(); //移除原有的信息
	$('#allOrder').append('<p class="chooseOrder" id="chooseOrder"><span id="allRenterOrder" class="choose">所有订单</span><span id="allCollect">查看收藏</span></p>');  //插入背景
	// 为两个切换按钮绑定事件
	$('#chooseOrder').find('span').click(function(event) {
		$('#chooseOrder').find('span').removeClass('choose');
		$(this).addClass('choose');
		$('#allOrder').find('ul').remove(); //移除原有的信息
		if($(this).is('#allRenterOrder')){
			getAllrenterOrder("1");   //获取所有订单
		}
		else{
			getAllCollect();   //获取所有收藏
		}
	});
	getAllrenterOrder(toPage);
}

// 获取所有订单
function getAllrenterOrder(toPage){
	$.ajax({
		// url: 'data/order.json',
		url: './order',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify({
			"type":"get",
			"filter": "userId",
			"toPage": toPage,
			"id": renterId
		}),
	})
	.done(function(obj) {
		if(obj.ret == 'true'){
			nowOrderPage = obj.nowPage; //保存页号数
			dealRenterOrderInfo(obj);  //处理返回的信息
			addButton(nowOrderPage,obj.sumPage,3);
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

// 处理获取所有订单返回的数据
function dealRenterOrderInfo(obj) {
	// $('#allOrder').find('ul').remove(); //移除原有的信息
	$('#allOrder').append("<ul id='allReordersInfo' class='orderInfo'></ul>");  //插入背景
	$.each(obj.data, function(index, val) {
		// 文本
		var contentText = "<p class='orderNumber'>订单编号："+val.id+"</p>";
			contentText += "<a href='houseDetail.jsp?houseId="+val.houseId+"' target='_blank'><p class='contentTitle'>"+val.houseTitle+"</p></a>";
			contentText += "<p class='contentComment'>"+val.houseComment+"</p>";
		var contentAddress = "<p class='adress'>详细地址：<span>"+val.houseAddress+"</span></p>";
			contentAddress += "<p><label>月租：</label><span class='price'>"+val.housePrice+"元/月</span></p>";
			contentAddress += "<p><span>房东编号："+val.publisherId+"</span><span>房东昵称："+val.publisherName+"</span><span>手机号："+val.publisherPhone+"</span></p>";
		var contents = "<div class='text'>"+contentText+contentAddress+"</div>";
		// 其他信息
		var otherInfo = "<p>租客信息</p>";
			otherInfo += "<p><span class='label'>租客编号：</span>"+val.userId+"</p>";
			otherInfo += "<p><span class='label'>租客昵称：</span>"+val.userName+"</p>";
			otherInfo += "<p><span class='label'>手机号：</span>"+val.userPhone+"</p>";
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
		$('#allReordersInfo').append("<li>"+allContents+"</li>");
	});
}
/*以上为租客获取对应信息的函数*/

/*以下为点击注销按钮时，触发注销事件*/
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
/*以上为点击注销按钮时，触发注销事件*/

// 向管理员发送信息
$('#contact').click(function(event) {
	$('.contactMange').show();
});

$('.contactMange').click(function(event) {
	$('.contactMange').hide();
});

/*修改资料部分*/
$('#changeData').click(function(event) {
	$('.changedataBg').show();
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
					"name":$('#newName').val(),
					"phone":$('#newPhone').val()
				}),
			})
			.done(function(obj) {
				if(obj.ret == 'true'){
					window.location.href = "./personal.jsp";
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

// 公共部分
 //获取所有收藏
function getAllCollect(){
	$.ajax({
		// url: 'data/house.json',
		url: './extra',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify({
			"type":"getCollect"
		}),
	})
	.done(function(obj) {
		if(obj.ret == 'true'){
			// 将收藏的房子信息渲染出来
			setCollectdata(obj.data);
		}
		else
			alert("获取收藏信息出错！原因：" + obj.reason);
	})
	.fail(function() {
		alert("页面获取出错");
	})
	.always(function() {
		// console.log("complete");
	});
}  

function setCollectdata(data){
	$('#allOrder').find('ul').remove(); //移除原有的信息
	$('#allOrder').append("<ul id='publishedInfo'></ul>");  //插入背景
	$.each(data, function(index, val) {
		// 照片
		var pics = "<div class='housePic'><img src='"+val.pic[0]+"' alt='被出租的房屋图片'></div>";
		// 文本
		var contentText = "<p class='contentTitle'>"+val.title+"</p><p class='contentComment'>"+val.comment+"</p>";
		var contentAddress = "<p class='city'><span><img src='images/address.png'></span><span>"+val.province+">"+val.city+">"+val.region+"</span></p>";
			contentAddress += "<p class='adress'>详细地址：<span>"+val.address+"</span></p>";
		var contents = "<a href='houseDetail.jsp?houseId="+val.id+"' target='_blank'><div class='text'>"+contentText+contentAddress+"</div></a>";
		// 其他信息
		var otherInfo = "<p class='price'>月租：<span>"+val.price+"元/月</span></p>";
			otherInfo += "<p class='count'>剩余套间：<span>"+val.count+"</span></p>";
		var others = "<div class='otherInfo'>"+otherInfo+"</div>";
		//文本和其他信息合并
		var allContents = "<div class='aboutText'>"+contents+others+"</div>";
		//添加完整的li
		$('#publishedInfo').append("<li>"+pics+allContents+"</li>");
	});
}

// 向管理员发送信息
$('#sendMessage').click(function(){
	if($("#message").val() == ''){
		alert("请先填写要发送的内容");
	}else{
		$.ajax({
			// url: 'data/sendMessage.json',
			url: './extra',
			type: 'POST',
			dataType: 'json',
			data: JSON.stringify({
				"type":"sendMessage",
				"comment":$("#message").val()
			}),
		})
		.done(function(obj) {
			if(obj.ret == 'true'){
				alert("发送成功");
				window.location.href = "./personal.jsp";
			}
			else
				alert("发送出错");
		})
		.fail(function() {
			alert("发送出错");
		})
		.always(function() {
			// console.log("complete");
		});
	}
});