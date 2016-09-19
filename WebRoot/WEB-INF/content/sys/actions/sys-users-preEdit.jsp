<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common.jsp"%>
<%@ page import="com.weixin.core.util.Constants"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>编辑用户信息</title>
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
			function save() {
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
			
				var valid_Time = $("#valid_Time").val();
				var password = $("#password").val();
				if (password.length >0) {
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
				}		
				document.form1.submit();
		}
		
		 $(document).ready(function(){
				$("#password").val("");
		})
	</script>
 
	</head>
	<body  class="public_bg" >
		<div class="window_title"><h3>编辑用户信息</h3></div>
		<form action="${ctx}/sys/actions/sys-users!editUser.action" name="form1" method="post">
		<input type="hidden" name="id" value="${adminUser.id}" />
		
		<div class="window_table">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
			  <tr>
			    <th>用户名：<span class="public_color">*</span></th>
			    <td style="color: #002D49">${adminUser.username}
					<input name="username" type="hidden" class="public_input4" readonly="true" value="${adminUser.username}"/></td>
			  </tr>
			  <tr>
			    <th>姓      名：<span class="public_color">*</span></th>
			    <td><input  name="name" id="name" type="text" class="window_input" maxlength="25" autocomplete="off" value="${adminUser.name}"/></td>
			  </tr>
			  <!--
			  <tr>
			    <th>有效期：</th>
			    <td><s:textfield name="valid_Time" id="valid_Time" cssClass="Wdate" size="15"
								onfocus="WdatePicker({skin:'whyGreen'})"
								onkeydown="return false;"  value="%{adminUser.valid_Time}"/><font color="red">(为空则永久有效)</font>
								
								
				<s:textfield name="valid_Timex" id="valid_Timex" cssClass="Wdate" size="15"
								onfocus="WdatePicker({skin:'whyGreen'})"
								onkeydown="return false;"  cssStyle="display: none;"/>
								</td>
			  </tr>
			  -->
			  
			   <tr>
			    <th>密      码：<span class="public_color">*</span></th>
			    <td><input name="password" id="password" value="" type="password" class="window_input2"/><font color="red">(为空则不修改)</font></td>
			  </tr>
			  
			   <tr>
			    <th>确认密码：<span class="public_color">*</span></th>
			    <td><input name="password2" id="password2" type="password" class="window_input2"/></td>
			  </tr>
			  
			  <tr>
			    <th>&nbsp;</th>
			    <td>
			      <input type="button" name="button" id="button" value=" " class="window_table_but1"  onclick="save();"/>
			      <input type="button" name="button" id="button" value=" " class="window_table_but2" onclick="parent.CloseMyModal();"/>
				  <input type="hidden" name="valid" value="${adminUser.valid}"/>
			    </td>
			  </tr>
			</table>
		</div>
		</form>
	</body>
</html>