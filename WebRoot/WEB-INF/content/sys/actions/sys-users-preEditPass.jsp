<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${ctx}/css/base.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="${ctx}/js/jquery.js"></script>
	<title>重置密码</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script language="Javascript" >
	<!--
		function save(){
			var password = document.form1.password.value;
			var newPassword = document.form1.newPassword.value;
			var newPassword2 = document.form1.newPassword2.value;
			newPassword = newPassword.trim();
			newPassword2 = newPassword2.trim();

			if (password.length<1) {
				alert("请输入旧密码！");
				document.form1.password.focus();
				return;
			}
			
			if (newPassword.length<1) {
				alert("请输入新密码！");
				document.form1.newPassword.focus();
				return;
			}
			if(newPassword.length < 6){
				alert("输入的密码不能小于6位！");
				document.form1.newPassword.focus();
				return false;
			}
			if (newPassword2.length<1) {
				alert("请输入确认密码！");
				document.form1.newPassword2.focus();
				return;
			}
			if(newPassword!=newPassword2){
				alert("两次输入的新密码不一致！");
				document.form1.newPassword.focus();
				return;
			}
			var param = {password:password, newPassword:newPassword};
			$.post("${ctx}/sys/actions/sys-users!editPass.action",param,function(data){
				if(data[0].tips.indexOf("成功")>0){
					alert(data[0].tips);
					parent.CloseMyModal();
				}else{
					alert(data[0].tips);
				}
				
			},'json');
		}
	//-->
	</script>
</head>
<body class="public_bg">

	<div class="window_title"><h3>重置密码</h3><h4></h4></div>
		<form action="${ctx}/sys/actions/sys-users!editPass.action" name="form1" method="post">	
		<div class="window_table">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		  <tr>
		    <th>旧密码：<span class="public_color">*</span></th>
		    <td><input name="password" type="password" class="window_input"/></td>
		  </tr>
		  <tr>
		    <th>新密码：<span class="public_color">*</span></th>
		    <td><input name="newPassword" type="password" class="window_input"/></td>
		  </tr>
		   <tr>
		    <th>新密码：<span class="public_color">*</span></th>
		    <td><input name="newPassword2" type="password" class="window_input"/></td>
		  </tr>
		  <tr>
		    <th>&nbsp;</th>
		    <td>
		      <input type="button" name="button" id="button" value=" " class="window_table_but1" onclick="save();"/><input type="button" name="button" id="button" value=" " class="window_table_but2" onclick="parent.CloseMyModal();"/>
		    </td>
		  </tr>
		</table>
	</div>
		</form>
</body>
</html>