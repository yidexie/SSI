<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://weixin.com.cn/mytag" prefix="mytag"%>
<%@ include file="/common.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${ctx}/css/base.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/simplemodal2.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script language="javascript" src="${ctx}/js/js.js"></script>
<script language="javascript" src="${ctx}/js/set.js"></script>
<script language="javascript" src="${ctx}/js/simplemodal.js"></script>
<script language="javascript" src="${ctx}/js/jquery.simplemodal.js"></script>
<script language="JavaScript">
		function addNewUser(){
			OpenMyModal("${ctx}/sys/actions/sys-users!preAdd.action",673,370,showFun,showFun);
			$("#simplemodal-container").css({"width":"673px"});
		}
		function showFun(){
			$("select").toggle();
		}
		
		function searchUser(){
			//如果包含特殊符号
			$("#username").attr("value",$("#username").val().trim());
			var user=$("#username");
			if(containsSpecialSign($("#username").val())){
				alert("您输入的查询关键词包含特殊符号，请重新输入！");
				user.focus();
				return;
			}
			document.getElementById("pageNo").value = 1;
			document.searchPage.action="${ctx}/sys/actions/sys-users!list.action";
			document.searchPage.submit();
		}
		function keypress(){
			if(event.keyCode==13){
				searchUser();
			}   
		}
		
		function del(id){
			url="${ctx}/sys/actions/sys-users!delete.action";
			params= "rand="+Math.round()+"&ids="+id;  
			if(confirm("您确定要删除此用户吗？")){
				$.getJSON(url, params,function(result){   
					alert(result.tips);
					document.getElementById("pageNo").value = 1;
					$("#searchPage").submit(); 
				});
			}
		}
		function modify(id){
			var url="${ctx}/sys/actions/sys-users!preEdit.action?rand="+Math.round()+"&id="+id;  
			 OpenMyModal(url,673,370,showFun,showFun);
			 $("#simplemodal-container").css({"width":"673px"});
		}
		
		$(document).ready(function(){
			$("#username").focus();			
			
		})
		
		</script>
</head>
<body> 
<%@ include file="/head.jsp"%> 
<div class="menu"><i class="iconfont"></i>系统管理 &gt; 用户管理</div> 
<form action="${ctx}/sys/actions/sys-users!list.action" name="searchPage" id="searchPage" method="post"> 
  <div class="monitor" onkeydown="keypress()"> 
    <div class="special_input" style="margin:0px 0px 9px 0px"> 用户名称：
      <input name="username" id="username" value="${username}" type="text" class="public_input" /> 
&nbsp;&nbsp;&nbsp;&nbsp; 
      <input name="" type="button" class="special_input1" value=" " onclick="searchUser()"/> 
      <input name="" type="button" class="special_input2" value=" " onclick="addNewUser();"/> 
    </div> 
    <div class="sort_table"> 
      <table width="100%" border="0" cellspacing="0" cellpadding="0"> 
        <tbody> 
          <tr> 
            <th>用户账号</th> 
            <th>姓名</th> 
            <th>用户状态</th> 
            <th>更新时间</th> 
            <th>操作</th> 
          </tr> 
          <c:if test="${fn:length(page.result) == 0}"> 
          <tr> 
            <td class="infor_blue" colspan="5" align="center"> 无符合查询条件的结果！ </td> 
          </tr> 
          </c:if> <c:forEach items="${page.result}" var="temp"> 
          <tr> 
            <td> ${temp.username}</td> 
            <td>${temp.name}</td> 
            <td> <c:choose> <c:when test="${temp.valid eq '1'}"> 有效 </c:when> <c:otherwise>无效 </c:otherwise></c:choose> </td> 
            <td> ${fn:substring(temp.updateTime,0,19)} </td> 
            <td> <c:choose> <c:when test="${(sessionScope.UserSession.privilege == '&S')}"> <a href="javascript:void(0);" onclick="modify('${temp.id}');">修改</a> <span class="public_color4">|</span> <a href="javascript:void(0);" onclick="del(${temp.id});">删除</a> </c:when> <c:otherwise><font color="#666666">修改 | 删除</font></c:otherwise> </c:choose> </td> 
          </tr> 
          </c:forEach> 
        </tbody> 
      </table> 
    </div> 
  </div> 
  <div class="page"> 
    <%@ include file="/page.jsp"%> 
  </div> 
</form> 
</body>
</html>
