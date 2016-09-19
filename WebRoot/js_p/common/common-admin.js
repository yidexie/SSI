var Grid = {
	deleteManyData : function(url1, url2) {
		if (!checkSelect("ids")) {
			DialogMsg.alert("请至少选择一条记录！");
			return false;
		}
		var ids = "";
		$("input[name='ids']:checked").each(function() {
			ids = ids + $(this).val() + ",";
		});
		if (ids.endWith(",")) {
			ids = ids.substring(0, ids.length - 1);
		}
		var params = "rand=" + new Date().getTime() + "&ids=" + ids;
		var msg = "您确定要删除所选中的记录吗?";
		if(url1.indexOf("key-type") > 0){
			msg="您确定要删除这些类别以及该类别下的所有关键词吗?"
		}
		DialogMsg.confirm(msg, function() {
			$.getJSON(url1, params, function(data) {
				alert(data.tips);
				if(document.getElementById("pageNo")!=null){
					document.getElementById("pageNo").value = 1;
				}
				document.getElementById('searchPage').action = url2;
				document.getElementById('searchPage').submit();
			});
		});
	},
	// wanghc used
	deleteAllSelected : function(url1, url2) {
		if (!checkSelect("ids")) {
			DialogMsg.alert("请至少选择一条记录！");
			return false;
		}
		
		var ids = "";
		$("input[name='ids']:checked").each(function() {
			ids = ids + $(this).val() + ",";
		});
		if (ids.length > 0) {
			ids = ids.substring(0, ids.length - 1);
		}
		var params = "rand=" + new Date().getTime() + "&ids=" + ids;
		DialogMsg.confirm("您确定要删除所选中的记录吗?", function() {
			$.getJSON(url1, params, function(data) {
				alert(data.tips);
				var listForm = document.getElementById('searchPage');
				if (listForm) {
					
					document.getElementById('searchPage').action = url2;
					document.getElementById('searchPage').submit();
				}
			});
		});
	},
	deleteManyDataFrame : function(url1, url2) {
		if (!checkSelect("ids")) {
			DialogMsg.alert("请至少选择一条记录！");
			return false;
		}
		var ids = "";
		$("input[name='ids']:checked").each(function() {
			ids = ids + $(this).val() + ",";
		});
		if (ids.length > 0) {
			ids = ids.substring(0, ids.length - 1);
		}
		var params = "rand=" + new Date().getTime() + "&ids=" + ids;
		DialogMsg.confirm("您确定要删除所选中的记录吗?", function() {
			$.getJSON(url1, params, function(data) {
				alert(data.tips);
				document.getElementById("pageNo").value = 1;
				top.location.href = url2;
			});
		});

	},

	deleteOneData : function(url1, url2) {
		url1 = url1 + "&rand=" + new Date().getTime();
		var msg = "您确定要删除该记录吗?";
		var params = "";
		DialogMsg.confirm(msg,function() {
			$.getJSON(url1,params,function(data) {
				alert(data.tips);
				if(document.getElementById("pageNo")!=null){
					document.getElementById("pageNo").value = 1;
				}
				document.getElementById('searchPage').action = url2;
				document.getElementById('searchPage').submit();
			});
		});
	},

	deleteOneDataFrame : function(url1, url2) {
		url1 = url1 + "&rand=" + new Date().getTime();
		DialogMsg.confirm("您确定要删除该记录吗?", function() {
			$.getJSON(url1, function(data) {
				alert(data.tips);
				document.getElementById("pageNo").value = 1;
				top.location.href = url2;
			});
		});
		
	},

	editData : function(url) {
		window.location.href = url;
	},

	viewData : function(url) {
		window.location.href = url;
	}

}

// 判断某个指定name的值的标签下是否被选中，主要是针对checkbox
function checkSelect(name) {
	var ids = document.getElementsByName(name);
	var length = ids.length;
	var checked = false;
	for (var index = 0;index < length; index++) {
		var obj = ids[index];
		if (obj.checked) {
			checked = true;
			break;
		}
	}
	return checked;
}

// 全选或者反选
function select_all(obj) {
	var all_ids = document.getElementsByName('ids');
	var length = all_ids.length;
	var index = 0;
	for (index = 0;index < length; index++) {
		var sub_obj = all_ids[index];
		if (obj.checked) {
			sub_obj.checked = true;
		} else {
			sub_obj.checked = false;
		}
	}
}

// 判断属性是否为空
function isEmpty(str) {
	if (str == null || str == "") {
		return true;
	}
	str = str.trim();
	return (str == null || str == "");
}

function isNum(num) {
	var reNum = /^\d*$/;
	return (reNum.test(num));
}

// 提示框对象
var DialogMsg = {
	alert : function(msg) {
		alert(msg);
	},

	confirm : function(title, func) {
		if (confirm(title)) {
			func();
		}
	}
}

//导出
function _exportOut(url1,url2){
	var ids="";
	if (!checkSelect("ids")) {
			if(!confirm("您尝未选择需要导出的记录，确定全部导出吗？")){
				return;
			}
		}
		$("input[name='ids']:checked").each(function() {
			ids = ids + $(this).val() + ",";
		});
		if (ids.length > 0) {
			ids = ids.substring(0, ids.length - 1);
		}
		var remark = $("#remark").val();
		var type = $("select[name='type'] option:selected").val();
		var ip = $("#ip").val();
		var timeStart = $("#timeStart").val();
		var timeEnd = $("#timeEnd").val();
		url1=url1+"?ids="+ids;
		if(!isEmpty(remark)){
			url1 = url1+"&remark_="+remark;
		}
		if(!isEmpty(type) && type != "0"){
			url1 = url1 +"&type_="+type;
		}
		if(!isEmpty(ip)){
			url1 = url1 + "&ip_="+ip;
		}
		if(!isEmpty(timeStart)){
			url1 = url1 + "&timeStart_="+timeStart;
		}
		if(!isEmpty(timeEnd)){
			url1 = url1 + "&timeEnd_="+timeEnd;
		}
		window.open(url1);
}

function search(){
	document.getElementById("pageNo").value=1;
	document.searchPage.submit();
}

String.prototype.endWith = function(oString){
	var reg = new RegExp(oString+"$");
	return reg.test(this);
}

//检查输入是否超过字符，如果是，则不允许再进行输入 
function checkLength(object,size) { 
	//检查是否为退格键，则允许进行退格操作 
	if(event.keyCode!=8) { 
		if (object.value.length>=size) { 
			alert("输入的长度不能超过"+size+"个字符！");
			event.returnValue=false 
		} 
	}
} 

function cancelAll(obj){
	var allObj = $("input[type='checkbox'][name='CHECK_ALL']:checked");
	if(allObj){
		$("#CHECK_ALL").removeAttr("checked");
	}
	
}

function checkNum(e){
	var eve = window.event? e.keyCode:e.which
	return (eve>=48&&eve<=57) || eve==8;
}

function checkNumAndComma(e){
	var eve = window.event? e.keyCode:e.which;
	return (eve>=48&&eve<=57) || eve==8 || eve==44;
}

function checkNumAndSeparate(e){
	var eve = window.event? e.keyCode:e.which;
	return (eve>=48&&eve<=57) || eve==8 || eve==32 || eve==44 || eve==45;
}