function quickAdd(){
	if(document.searchForm.query.value=='请输入您要搜索的关键词' || document.searchForm.query.value.length<1){
   		alert("请输入查询的关键词！");
		document.searchForm.query.focus();
		return;
    }
	else{
		if (document.searchForm.query.value.length>40) {
			alert("搜索关键词的长度不能超过40！");
			document.searchForm.query.focus();
			return;
		}
		if(document.searchForm.actName.value.length>0){
			if(document.searchForm.actName.value.length>25){
				alert("专项名称长度不能超过25个汉字！");
				document.searchForm.actName.focus();
				return;
			}
			if(containsSpecialSign(document.searchForm.actName.value)){
				alert("您输入的专项名称包含特殊符号，请重输！");
				document.searchForm.actName.focus();
				return;
			}
		}
		//如果包含特殊符号
		if(containsSpecialSign(document.searchForm.query.value)){
			alert("您输入的关键词包含特殊符号，请重新输入！");
			document.searchForm.query.focus();
			return false;
		}
		if(isEmpty(document.searchForm.query.value)){
			alert("关键词为空，请重输！");
			document.searchForm.query.focus();
			return false;
		}
		//document.searchForm.action=root+"/clue/actions/tasks!quickAdd.action";
		document.searchForm.submit();
	}
}

function containsSpecialSign(s)   
{   
	var patrn=/^.*[\\;'<>?{}].*$/;
	//var patrn=/^[^`~!@#$%^&*()+=|\\\][\]\{\}:;'\,.<>/?]{1}$/;
	if (!patrn.exec(s))
		return false;
	else
		return true;
}

function isValidUrl(s){
	var patrn=/^https?:\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/;
	if (!patrn.exec(s))
		return false;
	else
		return true;
}

String.prototype.Trim=function(){return this.replace(/(^\s*)|(\s*$)/g,"");}

function keyChose(){
	try{
		var ostore = Store_getAll();
		if(ostore != null){
				document.getElementById("ukeySerialNo").value= ostore.SerialNumber;				
		}
	}catch(e){}
}

function clearActName(){
	if(document.searchForm.actName.className=='' || document.searchForm.actName.className=='public_input1'){
		document.searchForm.actName.className ="public_input1";
		document.searchForm.actName.value = "";
	}
}