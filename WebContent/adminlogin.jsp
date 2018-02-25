<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<title>管理员登录——房屋租赁系统</title>
	<link rel='stylesheet' type='text/css' href="css/bootstrap.css" >
	<link rel="stylesheet" type="text/css" href="css/register-login.css">
	<link rel="stylesheet" type="text/css" href="css/adminlogin.css">
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
						  	  <label>管理员登录</label>
						  </div>
						  <div class="form-group">
						      <input type="text" class="input form-control required" id="input-email" placeholder="管理员账号" />
						       
						  </div>
						  <div class="form-group">
						      <input type="password" class="input form-control required" id="input-password" placeholder="密码" />
						  </div>
						  <div class="input form-group">
							  <input type="button" id="login"  class="btn btn-default send" value="登录" />
						  </div>
						  <!-- <div id="otherWay" class="form-group">
							  <a href="adminlogin.html" target="_blank"><label>管理员登录</label></a>
							  <a href="register.html"><label>立即注册</label></a>
						  </div> -->
					</form>
				</div>
			</div>
		</div>
		<div class="col-md-3"></div>
	</div>
</div>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script src="js/register-login.js"></script>
<script src="js/adminlogin.js"></script>
</body>
</html>