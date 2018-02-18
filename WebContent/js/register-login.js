$(function(){
	tag = true;  //用于记录邮箱是否存在或正确

// 文本框判断部分
  //文本框失去焦点后
  $('form :input').blur(function(){
    var $parent = $(this).parent();
    $parent.find(".error").remove();
    var element = this.value;  //获取元素的值	
    var text_pic = "<div class='error form-group'>&nbsp;" ;

    //验证邮件
    if( $(this).is('#input-email') ){
		// var phoneReg = /^1[3|4|5|7|8][0-9]{9}$/;  //手机匹配的正则表达式，第一位是【1】开头，第二位则则有【3,4,5,7,8】，第三位则是【0-9】，第三位之后则是数字【0-9】
		var emailReg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;  //邮箱匹配的正则表达式
		if (element == "" || element == null) {
			tag = false;
			text = text_pic+"<span>账号不能为空！</span>";
			$parent.append(text+"</div>");   //插入html语句
		}else if(!emailReg.test(element)){
			tag = false;
			text = text_pic+"<span>请输入正确的邮箱！</span>";
			$parent.append(text+"</div>");   //插入html语句
		}else{
			tag = true;			
			$parent.find(".error").remove();    //输入正确后，删除提示语
		}
    }

    //验证昵称，判断是否为空
    if( $(this).is('#input-name') ){	
		if (element == "" || element == null) {
			text = text_pic+"<span>昵称不能为空！</span>";
			$parent.append(text+"</div>");   //插入html语句
		}else{
			$parent.find(".error").remove();
		}
    }


    //验证密码
    if( $(this).is('#input-password') ){	
		if (element == "" || element == null) {
			text = text_pic+"<span>密码不能为空！</span>";
			$parent.append(text+"</div>");   //插入html语句
		}else if (element.length<6) {
			text = text_pic+"<span>密码长度不能小于6！</span>";
			$parent.append(text+"</div>");   //插入html语句
		}else{
			$parent.find(".error").remove();
		}
    }

 	//验证手机号码
    if( $(this).is('#input-phone') ){
		var phoneReg = /^1[3|4|5|7|8][0-9]{9}$/;  //手机匹配的正则表达式，第一位是【1】开头，第二位则则有【3,4,5,7,8】，第三位则是【0-9】，第三位之后则是数字【0-9】
		if (element == "" || element == null) {
			text = text_pic+"<span>手机号码不能为空！</span>";
			$parent.append(text+"</div>");   //插入html语句
		}else if(!phoneReg.test(element)){
			text = text_pic+"<span>请输入正确的手机号码！</span>";
			$parent.append(text+"</div>");   //插入html语句
		}else{
			$parent.find(".error").remove();    //输入正确后，删除提示语
		}
    }


  }).keyup(function(){ 
   $(this).triggerHandler("blur");    //键下内容时触发事件
  }).focus(function(){
    $(this).triggerHandler("blur");   //获得焦点时触发事件
  });//end blur

  

	/*登录部分*/
	// 登录异步获取图片验证码
	$('#getPicVerification').click(function(){
		getPicCode();
	});

	//异步请求图片验证码
	function getPicCode(argument) {
		$.ajax({
			url: 'login/identifyCode.jsp',
			type: 'POST',
			dataType: 'json',
			// data: {param1: 'value1'},
		})
		// .done(function() {
		// 	console.log("success");
		// })
		// .fail(function() {
		// 	console.log("error");
		// })
		// .always(function() {
		// 	console.log("complete");
		// });
		
	}

})

