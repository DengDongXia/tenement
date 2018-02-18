/*登录部分*/
  //提交，最终验证。
   $('#login').click(function(){
    $("form :input.required").trigger('blur');    //含有类名为required的文本框都触发验证事件
    var numError = $('form .error').length;
    if(numError){
     return false;
    } 
    // 异步提交表单数据
    $.post('/user', {
	    	type:"login",
			email: $("#input-email").val(),
			psw: $("#input-password").val(),
			t: $("input[type='radio']:checked").val()

		}, function(data, textStatus) {
		    if(data[0].ret == 'true')
				location.href = "../index.jsp";   //当后端登录成功后,返回true,跳转到登录界面
			else{
				$("#input-password").find(".error").remove();	
			    var text = "<div class='error form-group'><span>"+data[0].reason+"</span></div>" ;
				$("#input-password").append(text);
			}
		});
   });
