/*管理员登录部分*/
  //提交，最终验证。
   $('#login').click(function(){
    $("form :input.required").trigger('blur');    //含有类名为required的文本框都触发验证事件
    var numError = $('form .error').length;
    if(numError){
     return false;
    } 
    // 异步提交表单数据
    $.ajax({
    	url: './admin',
    	type: 'POST',
    	dataType: 'json',
    	data: JSON.stringify({
    		"type":"login",
			"userName": $("#input-email").val(),
			"psw": $("#input-password").val()
		}),
    })
    .done(function(data) {
    	 if(data.ret == 'true')
				location.href = "./managePersonal.jsp";   //当后端登录成功后,返回true,跳转到管理员管理界面
			else{
				$("#input-password").find(".error").remove();	
			    var text = "<div class='error form-group'><span>"+data.reason+"</span></div>" ;
				$("#input-password").append(text);
			}
    })
    .fail(function() {
    	// console.log("error");
    })
    .always(function() {
    	// console.log("complete");
    });
});
