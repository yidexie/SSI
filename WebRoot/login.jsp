<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common.jsp"%>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>-</title>
		<link href="${ctx}/css/base.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
		<script language="javascript" src="${ctx}/js/jquery.js"></script>
		<script language="javascript" src="${ctx}/js/js.js"></script>
		<script language="JavaScript" type="text/javascript" src="${ctx}/js_p/sys/user/login1.js"></script>
		<script type="text/javascript">
			ctx = "${ctx}";
		</script>
	</head>

	<body class="public_login" onLoad="document.form1.username.focus();" onkeydown="if(event.keyCode==13)check();">
	<form name="form1" method="post" action="${ctx}/sys/actions/sys-users!login.action">
		<input name="codeImg" type="hidden" value="noCodeImage"/>
		<div class="login_head">
			<img src="${ctx}/images/login_logo.png" />
		</div>
		<div class="login_mat">
			<div class="login">
				<div class="login_wrap">
					<ul class="login_list">
						<li>
							<input name="username" id="username" type="text" placeholder="请输入用户名" />
						</li>
						<li>
							<input name="password" id="password" type="password" placeholder="请输入密码" />
						</li>
					</ul>
					<div class="login_but"><input name="button" type="button"  onClick="check()" tabindex="4"/></div>
				</div>
			</div>
		</div>
		</form>
	</body>
</html>

