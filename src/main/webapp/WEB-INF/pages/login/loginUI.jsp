<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>loginUI</title>
	<script src="${pageContext.request.contextPath}/script/bootstrap.js"></script>
	<script src="${pageContext.request.contextPath}/script/jquery-1.4.2.js"></script>
	<link href="${pageContext.request.contextPath}/style/bootstrap.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/login.css">
	<style type="text/css">
		.error{
			color:red ;	
		}
	</style>
	<script type="text/javascript">
		function check(e,name){
			$("#msg").removeClass("error") ;
			$("#msg").html("");
			if($(e).val()==""){
				$("#msg").html(name+"不能为空") ;
				$("#msg").addClass("error") ;
			}
		}
		function checkSubmit(){
			if($("input[name='username']").val()!='' && $("input[name='password']").val()!=''){
				return true ;
			}else{
				alert("请填写用户名或密码") ;
				return false ;
			}
		}
	</script>
</head>
<body>
	<div>
		<div class="CenterArea row">
			<form class="col-md-4 col-md-offset-4" method="post" action="${pageContext.request.contextPath}/login/login.action" onsubmit="return checkSubmit();">
				<h3>欢迎登录</h3>
				<div class="form-group">
					<label>用户名：</label>
					<input type="text" class="form-control" name="username" placeholder="loginName" onblur="check(this,'用户名')">
				</div>
				<div class="form-group">
					<label for="exampleInputPassword1">密码：</label>
					<input type="password" class="form-control" id="exampleInputPassword1" name="password" placeholder="Password" onblur="check(this,'密码')">
				</div>
				<button type="submit" class="btn btn-info">登录</button><label id="msg" class="error">${msg}</label>
			</form>
		</div>
	</div>
</body>
</html>

