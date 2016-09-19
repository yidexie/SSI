<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<html>
	<head>
		<title></title>
		<META NAME="ROBOTS" CONTENT="NOINDEX,NOFOLLOW">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link rel="STYLESHEET" type="text/css"
			href="${pageContext.request.contextPath}/css/css.css">
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/ui/button/button.css">
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/ui/button/icon.css">
		<script language="javascript"
			src="${pageContext.request.contextPath}/js/jquery-1.3.2.min.js"></script>
		<link href="${pageContext.request.contextPath}/css/styles/index.css"
			rel="stylesheet" type="text/css" />
		<style type="text/css">
		<!--
		.STYLE1 {color: #FF9E05}
		-->
	</style>
	</head>
	<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
		marginheight="0" class="main_bg">
		<form action="" name="form1" id="form1" method="post">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="pane-next">
				<tr class="next_bg">
					<td width="4%" align="center">
					</td>
					<td width="96%" height="27">
						当前位置：系统错误
					</td>
				</tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="96%" height="27"></td>
				</tr>
			</table>
			<table width="95%" cellpadding="0" cellspacing="0" align="center"
				class="table_list1">
				<tbody>
					<tr align="middle">
						<td colSpan="2" height="30" class="col_title" id="title">
							系统错误
						</td>
					</tr>
					<tr class="col_l">
						<td height="50" class="col_h" colspan="2">
							尊敬的用户，
							<br>
							&nbsp;
							<br>
							&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;
							你好，系统目前发生内部错误，错误原因为：${exception.message}
							<br>
							&nbsp;
							<br>
							请重新刷新页面，多次出现该页面，请联系相关客服人员！
							<br>
							&nbsp;
						</td>
					</tr>
					<tr align="middle">
						<td colSpan="2" class="page_bg" valign="middle"
							style="padding-bottom:0px;padding-top: 8px;">
							<input type="button" icon="icon-back" z-type="button" value='返 回'
								onclick="history.back(-1);">

						</td>
					</tr>
				</tbody>
			</table>
	</body>
</html>
