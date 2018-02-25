// 页面加载后调用以下函数
$().ready(function() {
	getHouseInfo();   //获取该客房信息
});

// 获取客房信息
function getHouseInfo(argument) {
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
			$('#infoTitle').val(obj.data.title);  //信息的标题
			$('#province').val(obj.data.province); //省
			$('#city').val(obj.data.city); //城市
			$('#region').val(obj.data.region); //区
			$('#address').val(obj.data.address); //详细地址
			$('#price').val(obj.data.price); //每月租金
			$('#count').val(obj.data.count); //剩余数量
			$('#comment').val(obj.data.comment); //具体信息
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

/*重新发布房屋信息*/
$('#publish').click(function(event) {
	if(judgeForm() == false) return;
	var houseId = getUrlId('houseId');  //获取url中的客房id
	$.ajax({
		// url: 'data/updateHouse.json',
		url: './house',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify({
			"type":"edit",
			"id" :houseId,
			"title":$('#infoTitle').val(),
			"province":$('#province').val(),
			"city":$('#city').val(),
			"region":$('#region').val(),
			"address":$('#address').val(),
			"price":$('#price').val(),
			"comment":$('#comment').val(),
			"count":$('#count').val()
		}),
	})
	.done(function(obj) {
		if(obj.ret == 'true'){
			alert("修改成功");
			location.href = './personal.jsp';   //跳转到个人中心页面
		}
		else
			alert("修改客房信息失败！原因：" + obj.reason);
	})
	.fail(function() {
		alert("修改客房信息出错");
	})
	.always(function() {
		// console.log("complete");
	});
});

//判断表单是否已经填完整
function judgeForm(){
	if($('#infoTitle').val() == '' ||  $('#province').val() == '' || $('#city').val() == '' ||
		$('#region').val() == '' || $('#address').val() == '' || $('#price').val() == '' ||
		$('#comment').val() == '' || $('#count').val() == ''){
		alert("请将表单填完整，再进行发布");
		return false;
	}
	return true;
}
/*重新发布房屋信息*/

/*以下为点击注销按钮时，触发注销事件*/
$('#lgout').click(function(event) {
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

