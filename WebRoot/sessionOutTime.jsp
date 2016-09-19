<%@ page contentType="text/html;charset=UTF-8"%>  
<%@ taglib prefix="s" uri="/struts-tags" %>
<script type="text/javascript">
	alert("当前你没有权限访问该页面！可能原因：你未登录或登录session失效！");
	if(window.parent)window.parent.location="${pageContext.request.contextPath}/login.jsp";
	else window.location="${pageContext.request.contextPath}/login.jsp";
</script>
