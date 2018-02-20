/*注册部分*/	
	
  //提交，最终验证。
   $('#register').click(function(){
    $("form :input.required").trigger('blur');    //含有类名为required的文本框都触发验证事件
    var numError = $('form .error').length;
    if(numError){
     return false;
    } 
    // 异步提交表单数据
    $.ajax({
    	url: './user',
    	type: 'POST',
    	dataType: 'json',
    	data: JSON.stringify({
    		"type":"regist",
			"email": $("#input-email").val(),
			"name": $("#input-name").val(),
			"psw": $("#input-password").val(),
			"phone": $("#input-phone").val(),
			"t": $("input[type='radio']:checked").val()
		}),
    })
    .done(function() {
    	if(data.ret == 'true'){
				alert("注册成功！");
				location.href = "../login.jsp";   //当后端注册成功后,返回true,跳转到登录界面
		}else{
				$("#tag").find(".error").remove();	
			    var text = "<div class='error form-group'><span>"+data.reason+"</span></div>" ;
				$("#tag").append(text);
		}
    })
    .fail(function() {
    	// console.log("error");
    })
    .always(function() {
    	// console.log("complete");
    });
    
    
    
   });
