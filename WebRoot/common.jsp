<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/fn.tld" prefix="fn"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
	var GB_ROOT_DIR = "${ctx}/js/greybox/";
　　String.prototype.trim=function(){
　　 return this.replace(/(^\s*)|(\s*$)/g, "");
　　}
　　String.prototype.ltrim=function(){
　　 return this.replace(/(^\s*)/g,"");
　　}
　　String.prototype.rtrim=function(){
　　 return this.replace(/(\s*$)/g,"");
　　}
</script>
