<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common.jsp"%>
<%----%>
<%--<script language="javascript" src="${ctx}/js/jquery.js"></script>--%>
<script language="javascript" src="${ctx}/js/simplemodal.js"></script>
<script language="javascript" src="${ctx}/js/jquery.simplemodal.js"></script>
<script language="javascript">
	var menuId = "${menuId}";
	function loginOut(){
		if(window.confirm("是否退出系统?")){
			location.href = "${ctx}/sys/actions/sys-users!logout.action";
		}
	}
	function showFun(){
		$("select").toggle();
	}
	function resetPass(id){
		url="${ctx}/sys/actions/sys-users!preEditPass.action?rand="+Math.round();
		OpenMyModal(url,500,250,showFun,showFun);
		$("#simplemodal-container").css({"width":"500px","height":"250px"});
	}
	
	$(document).ready(function(){
		if(menuId != null && menuId != "")
			$("#"+menuId).addClass("press");
	})
	
</script>
 <div class="public_wrap"> 
  <div class="head"> 
     <div class="head_left"> 
      <!--这里放logo图片
			<img src="${ctx}/images/logo.png">
			--> 
    </div> 
     <div class="head_right"> 
      <div class="head_right_top"> <i class="iconfont"></i><span class="public_color">欢迎您，${sessionScope.UserSession.username}</span>&nbsp;&nbsp; <c:if test="${sessionScope.UserSession.username eq 'admin'}"> <a href="${ctx}/sys/actions/sys-users!list.action">系统管理</a>| </c:if> <a href="#" onClick="resetPass();">修改密码</a>| <a href="#" onclick="loginOut();">退出系统</a> </div> 
      <div class="head_right_bot"> 
         <ul class="head_right_bot_list"> 
          <li id="1"> <a href="${ctx}/sys/actions/sys-users!index.action"> 
            <h3> <i class="iconfont"></i> </h3> 
            <h4> 系统首页 </h4> 
            </a> </li> 
          <li id="4"> <a href="#"> 
            <h3> <i class="iconfont"></i> </h3> 
            <h4> 菜单 </h4> 
            </a> 
             <dl class="head_right_bot_empty"> 
              <dt><a href="#">子菜单一</a></dt> 
              <dt><a href="#">子菜单二</a></dt> 
            </dl> 
           </li> 
          <%--					<li id="4">--%> 
          <%--						<a href="#"><h3>--%> 
          <%--								<i class="iconfont"></i>--%> 
          <%--							</h3>--%> 
          <%--							<h4>--%> 
          <%--								行业分布--%> 
          <%--							</h4>--%> 
          <%--						</a>--%> 
          <%--					</li>--%> 
        </ul> 
       </div> 
    </div> 
   </div> 
</div> 
