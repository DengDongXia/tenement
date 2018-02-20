<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<title>登录——房屋租赁系统</title>
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
						  	  <label>登录</label>
						  </div>
						  <div class="form-group">
						      <input type="text" class="input form-control required" id="input-email" placeholder="邮箱" />
						       
						  </div>
						  <div class="form-group">
						      <input type="password" class="input form-control required" id="input-password" placeholder="密码" />
						  </div>
						  <div class="form-group" id="checked">
							<label class="radio-inline">
							  <input type="radio" name="inlineRadioOptions" id="inlineRadio1" value="1" checked> 用户登录
							</label>
							<label class="radio-inline">
							  <input type="radio" name="inlineRadioOptions" id="inlineRadio2" value="2">房东登录
							</label>
						  </div>
						  <div class="input form-group">
							  <input type="button" id="login"  class="btn btn-default send" value="登录" />
						  </div>
						  <div id="otherWay" class="form-group">
							  <a href="adminlogin.jsp" target="_blank"><label>管理员登录</label></a>
							  <a href="register.jsp"><label>立即注册</label></a>
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
<script src="js/login.js"></script>
</body>
</html>