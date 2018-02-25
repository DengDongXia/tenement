<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<title>注册——房屋租赁系统</title>
	<link rel='stylesheet' type='text/css' href="css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="css/register-login.css">
</head>
<body>
<div class="container-fluid">
	<div class="row">
		 <div class="col-md-3">
		 </div>
		 <div class="col-md-6 registerForm">
			 <div class="panel panel-default">
				<div class="loginMain panel-body">
					<form action="" method="post">
						  <div class="mainLabel form-group">
						  	  <label>注册</label>
						  </div>
						  <div class="form-group">
						      <input type="text" class="input form-control required" id="input-email" placeholder="邮箱" />
						       
						  </div>
						  <div class="form-group">
						      <input type="text" class="input form-control required" id="input-name" placeholder="昵称" />
						       
						  </div>
						  <div class="form-group">
						      <input type="password" class="input form-control required" id="input-password" placeholder="密码" />
						  </div>
						  <div class="form-group" id="tag">
						      <input type="text" class="input form-control required" id="input-phone" placeholder="手机号码" />
						       
						  </div>
						  <div class="form-group" id="checked">
							<label class="radio-inline">
							  <input type="radio" name="inlineRadioOptions" id="inlineRadio1" value="1" checked> 用户注册
							</label>
							<label class="radio-inline">
							  <input type="radio" name="inlineRadioOptions" id="inlineRadio2" value="2">房东注册
							</label>
						  </div>
						  <div class="input form-group">
							  <input type="button" id="register"  class="btn btn-default send" value="注册" />
						  </div>
						  <div id="otherWay" class="form-group">
							  <a href="login.jsp"><label>立即登录</label></a>
						  </div>
					</form>
				</div>
			</div>
		</div>
		<div class="col-md-3"></div>
	</div>
</div>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script src="js/register-login.js"></script>
<script src="js/register.js"></script>
</body>
</html>