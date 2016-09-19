<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common.jsp"%>
<%@ page import="com.weixin.core.util.Constants"%>
<%@ taglib uri="http://weixin.com.cn/mytag" prefix="mytag"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="${ctx}/css/base.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/js/datepicker/skin/WdatePicker.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
		<script language="javascript" src="${ctx}/js/datepicker/WdatePicker.js"></script>
		<script language="javascript" src="${ctx}/js/js.js"></script>
		<script language="javascript" src="${ctx}/js/set.js"></script>
		<script language="javascript" src="${ctx}/js_p/common/common-admin.js"></script>
		<script language="Javascript">
		function close1(){
			parent.CloseMyModal();
		}
	
		function add() {
		var username = $("#username").val();
		if (username.length < 1) {
			alert("请输入用户名！");
			document.form1.username.focus();
			return false;
		}
		//如果包含特殊符号
		if (containsSpecialSign(username)) {
			alert("您输入的用户名包含特殊符号，请重新输入！");
			document.form1.username.focus();
			return false;
		}
		
		var name = $("#name").val();
		if (name.length < 1) {
			alert("请输入姓名！");
			document.form1.name.focus();
			return false;
		}
		//如果包含特殊符号
		if (containsSpecialSign(name)) {
			alert("您输入的姓名包含特殊符号，请重新输入！");
			document.form1.name.focus();
			return false;
		}
		var password = $("#password").val();
		if (password.length < 1) {
			alert("请输入密码！");
			document.form1.password.focus();
			return false;
		}
		if(password.length < 6){
			alert("输入的密码不能小于6位！");
			document.form1.password.focus();
			return false;
		}
		var password2 = $("#password2").val();
		if (password != password2) {
			alert("两次输入的密码不一致！");
			document.form1.password.focus();
			return false;
		}
		var valid_Time = $("#valid_Time").val();
		var params = "rand="+new Date()+"&username="+username+"&name="+name+"&valid_Time="+valid_Time+"&password="+password;
		$.post('${ctx}/sys/actions/sys-users!add.action',params,function(data){
			if(data.tips.indexOf("恢复")> -1){
				if(confirm(data.tips)){
					params = "rand="+Math.round()+"&adminUser.username="+encodeURI(username);
					$.getJSON('${ctx}/sys/actions/sys-users!recoverUser.action',params,function(bdata){
						alert(bdata.tips);
						parent.document.searchPage.submit();
						close1();
					});
				}else{
					close1();
				}
			}else if(data.tips.indexOf("存在")> -1){
				alert(data.tips);
				$("#username").focus();
				return;
			}else{
				alert(data.tips);
				parent.document.searchPage.submit();
				//close1();
			}
		});
		
	}
		$(document).ready(function(){
			$("#valid_Time").val("");
			$("#password").val("");
		})
</script>
	</head>
	<body class="public_bg" onload="document.form1.username.focus();">
	<form action="${ctx}/sys/actions/sys-users!add.action" name="form1" method="post">	
		<div class="window_title"><h3>添加新用户</h3></div>
		<div class="window_table">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
			  <tr>
			    <th>用户名：<span class="public_color">*</span></th>
			    <td><input name="username" id="username" type="text" class="window_input" maxlength="25" autocomplete="off"/></td>
			  </tr>
			  <tr>
			    <th>姓      名：<span class="public_color">*</span></th>
			    <td><input  name="name" id="name" type="text" class="window_input" maxlength="25" autocomplete="off"/></td>
			  </tr>
			  <!--
			  <tr>
			    <th>有效期：</th>
			    <td><s:textfield name="valid_Time" id="valid_Time" cssClass="Wdate" size="15"
								onfocus="WdatePicker({skin:'whyGreen'})"
								onkeydown="return false;"  /><font color="red">(为空则永久有效)</font></td>
								
				<s:textfield name="valid_Timex" id="valid_Timex" cssClass="Wdate" size="15"
								onfocus="WdatePicker({skin:'whyGreen'})"
								onkeydown="return false;"  cssStyle="display: none;"/>
			  </tr>
			  -->
			   <tr>
			    <th>密      码：<span class="public_color">*</span></th>
			    <td><input name="password" id="password" type="password" class="window_input2"/></td>
			  </tr>
			  
			   <tr>
			    <th>确认密码：<span class="public_color">*</span></th>
			    <td><input name="password2" id="password2" type="password" class="window_input2"/></td>
			  </tr>
			  
			  <tr>
			    <th>&nbsp;</th>
			    <td>
			      <input type="button" name="button" id="button" value=" " class="window_table_but1"  onclick="add();"/>
			      <input type="button" name="button" id="button" value=" " class="window_table_but2" onclick="parent.CloseMyModal();"/>
			    </td>
			  </tr>
			</table>
		</div>
		</form>

	</body>
</html>