var ctx = null;
function check()
{ 	
	if(document.form1.username.value.length<1){
		alert("用户名不能为空！");
		return;
	}
	if(document.form1.password.value.length<1){
		alert("密码不能为空！");
		return;
	}
	document.form1.action=ctx+"/sys/actions/sys-users!login.action";
	document.form1.submit();
}

function keypress()
  {
  	document.onkeypress=function(e){
  		var code;
  		if(!e){
  			var e = window.event;
  		}
  		if(e.keyCode){
  			code = e.keyCode;
  		}else if(e.which){
  			code = e.which;
  		}
  		
  		if(code == 13){
  			check();
  		}
  	}
}
   