<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://weixin.com.cn/mytag" prefix="mytag"%>
<%@ include file="/common.jsp"%>
<%@ page import="com.weixin.core.util.Constants"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<div class="foots"><%=Constants.copyright%>

</div>

<div class="public_up">
<a href="${ctx}/sys/actions/sys-util!index.action" target="_blank" class="public_up2"></a>
<a href="javascript:;" class="public_up1"></a>
</div>
<script>
$(function(){

		if ($(window).width() > 1024)
		{
			if ($("#div1,.middle,.duties,.public_coat").length > 0)
			{
				$(".public_up").css("right",(($("body").width() - $("#div1,.middle,.duties,.public_coat").width())/2) - $(".public_up").width()-10);
			}
			else
			{
				$(".public_up").css("right",(($("body").width() - $("#div1,.middle,.duties,.public_coat").width())/2) - $(".public_up").width()-10);
			}
		}
		else
		{
			if ($("#div1,.middle,.duties").length > 0)
			{
				$(".public_up").css("right",(($("body").width() - $("#div1,.middle,.duties,.public_coat").width())/2));
			}
			else
			{
				$(".public_up").css("right",(($("body").width() - $("#div1,.middle,.duties,.public_coat").width())/2));
			}
		}
		
		$(window).resize(function(){
			if ($(window).width() > 1024)
			{
				if ($("#div1,.middle,.duties").length > 0)
				{
					$(".public_up").css("right",(($("body").width() - $("#div1,.middle,.duties,.public_coat").width())/2) - $(".public_up").width()-10);
				}
				else
				{
					$(".public_up").css("right",(($("body").width() - $("#div1,.middle,.duties,.public_coat").width())/2) - $(".public_up").width()-10);
				}
			}
			else
			{
				if ($("#div1").length > 0)
				{
					$(".public_up").css("right",(($("body").width() - $("#div1,.middle,.duties,.public_coat").width())/2));
				}
				else
				{
					$(".public_up").css("right",(($("body").width() - $("#div1,.middle,.duties,.public_coat").width())/2));
				}
			}
		});

		$(window).scroll(function(){
			$(this).scrollTop() >= 150 ? $('.public_up1').css("display","block") : $('.public_up1').hide();
		}); 
		$('.public_up1').click(function(){
			$('html, body').animate({scrollTop : 0},$(this).offset().top/7);
		});
});
</script>