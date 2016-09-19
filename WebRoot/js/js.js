$(function(){
	$(".head_right_bot_list > li").bind({
		mouseover:function(){
			
			$(this).children(".head_right_bot_empty").show();
		},
		mouseout:function(){
			
			$(this).children(".head_right_bot_empty").hide();
		}
	});
	$(".special_left_mat_title").click(function(){
		$(".special_left_mat_list").hide();
		$(this).parent().children(".special_left_mat_list").show();
	});
	$(".shopshow_table_plan_mat").mouseover(function(){
		$(this).children(".shopshow_table_pe").css("z-index","80");
		$(this).find(".shopshow_table_win").slideDown(100);
	});
	$(".shopshow_table_plan_mat").bind("mouseleave", function(){
  		$(this).find(".shopshow_table_win").slideUp(100);
		$(this).children(".shopshow_table_pe").css("z-index","10");
  	});
	$(".shopshow_table_plan_mat:last").find(".shopshow_table_win").css("top","-90px");
	$(".shopshow_table_plan_mat:last").find(".shopshow_table_win_ico").css("top","108px");
	
	$(".middle_select_input").focus(function(){  
        if($(this).val()==$(this).attr("inputTip")){  
            $(this).val("");  
        }  
        $(this).addClass("inputFocus");  
    }).blur(function(){  
        if($.trim($(this).val()).length == 0){  
            $(this).val($(this).attr("inputTip"));  
            $(this).removeClass("inputFocus");  
        }  
    }).blur();
});

