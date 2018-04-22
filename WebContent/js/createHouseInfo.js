
/*发布房屋信息*/
$('#publish').click(function(event) {
	// 上传图片模块
	$.ajaxFileUpload({
			url: './upload', //用于文件上传的服务器端请求地址
			secureuri: false, //是否需要安全协议，一般设置为false
			fileElementId: 'pic', //文件上传域的ID
			dataType: 'json', //返回值类型 一般设置为json
			success: function (data, status)  //服务器成功响应处理函数
			{
				if(data.ret=="true"){
					submitForm(data.path);   //提交表单
				}else{
					alert("图片上传失败，表单无法成功提交");
				}
			},
			error: function (data, status, e)//服务器响应失败处理函数
			{
				alert("图片上传失败，表单无法成功提交");
			}
		}
	);
});

//提交表单
function submitForm(path) {
	// if(path.length < 0 || path.length >5)
	if(judgeForm() == false) return;
	var houseId = getUrlId('houseId');  //获取url中的客房id
	$.ajax({
		// url: 'data/updateHouse.json',
		url: './house',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify({
			"type":"publish",
			"title":$('#infoTitle').val(),
			"province":$('#province').val(),
			"city":$('#city').val(),
			"region":$('#region').val(),
			"address":$('#address').val(),
			"price":$('#price').val(),
			"comment":$('#comment').val(),
			"pic":path,
			"count":$('#count').val()
		}),
	})
	.done(function(obj) {
		if(obj.ret == 'true'){
			alert("发布成功");
			location.href = './personal.jsp';   //跳转到个人中心页面
		}
		else
			alert("发布客房信息失败！原因：" + obj.reason);
	})
	.fail(function() {
		alert("发布客房信息出错");
	})
	.always(function() {
		// console.log("complete");
	});
}

//判断表单是否已经填完整
function judgeForm(){
	if($('#infoTitle').val() == '' ||  $('#province').val() == '' || $('#city').val() == '' ||
		$('#region').val() == '' || $('#address').val() == '' || $('#price').val() == '' ||
		$('#comment').val() == '' || $('#count').val() == '' || /*$('#pic').val() == '' */){
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

