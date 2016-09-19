<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<div class="page">
	共
							<span id="totalNum">0</span> 条，第
							<span id="pageNum">0</span>/<span id="pageNumTotal">0</span> 页 
	<input type="hidden" name="page.pageSize" id="page.pageSize" value="${page.pageSize }"/>
	<input type="button" id="firstPage" class="pag_btn"
		src="${pageContext.request.contextPath}/images/turn.gif" value="首页" disabled="disabled"
		onclick="goToPage('first')"   />
	<input type="button" id="prePage" class="pag_btn" value="上一页"
		disabled="disabled" onclick="goToPage('pre')" />
	<input type="button" id="nextPage" class="pag_btn" value="下一页"
		disabled="disabled" onclick="goToPage('next')" />
	<input type="button" id="lastPage" class="pag_btn" value="末页"
		disabled="disabled" onclick="goToPage('last')" />
	<font color="#5480A5">转到</font>
	<input type="text" id="pageText" value=""  onkeydown="goToCurrPage();" onkeypress="return checkNum(event);"/>
	<font color="#5480A5">页</font>
	<a href="javascript:goToPage('goToPage')" class="page_go" >
	<img src="${pageContext.request.contextPath}/images/go.gif" width="21" height="15"  border="0"></a>
	<input type="hidden" name="page.pageNo" id="pageNo"
		value="${page.pageNo}" />
	<input type="hidden" name="page.orderBy" id="pageorderBy"
		value="${page.orderBy}" />
	<input type="hidden" name="page.order" id="pageorder"
		value="${page.order}" />
	<input type="hidden" name="page.totalPages" id="totalPages"
		value="${page.totalPages}" />
</div>
<script type="text/javascript">
   
  setPageNum(${page.pageSize},${page.pageNo},${page.totalPages},${page.totalCount});
   
     //新查询的清空操作  
  function newSearchClearUp(){ 
    if(document.getElementById("pageNo") != null){
      document.getElementById("pageNo").value = 1;
    }
  if(document.getElementById("orderType") != null){
    document.getElementById("orderType").value = "";
 }
}
  //新查询    
  function doSearch(){  
   
   
    newSearchClearUp(); //新查询的清空操作
     
    doPageSearch(); 
  }
 
  function valueTrim(){
	  var count = searchPage.elements.length;  
		for(var i=0;i<count;i++){  
			with(searchPage.elements[i]){
			value = value.trim(); 
			}
				
		} 
  }
  //页面查询  
  function doPageSearch(){  
    searchPage.submit(); 
  }
 /*
  * 新增
  */ 
  function doAdd(path){
   
  }
  //跳转到首页、上一页、下一页、末页、刷新该页或指定页  
  function goToPage(page){ 
   
    var pageNum = document.getElementById("pageNo");  
    var tmpPage = document.getElementById("pageText").value; 
    var goPageObj = document.getElementById("pageText");
    var totalPage = $("#totalPages").val();
    if(page == 'goToPage'){
	  	if(!checkRate(tmpPage)){
	  		alert("请输入正确的页码!");
	  		return;
	  	}
	  	if(pageNum == 0 || pageNum == 1){
	  		if(tmpPage < pageNum || tmpPage == 0){
	  			alert("输入的数字小于最小页码!");
	  			return;
	  		}
	  	}
	  	
	  	var totalPage = $("#totalPages").val();
	  	if(eval(tmpPage) > eval(totalPage)){
	  		alert("输入的数字大于最大页码!");
	  		return;
	  	}
  	}
	searchPage.reset(); 
    if(page=="first"){
      pageNum.value = 1;
    }
    else if(page=="pre"){
      pageNum.value = eval(pageNum.value)-1;
    }
    else if(page=="next"){
    
      pageNum.value = eval(pageNum.value)+1;
    }
    else if(page=="last"){
      pageNum.value = document.getElementById("pageNumTotal").innerText;
    }
    else if(page=="goToPage"){
        pageNum.value = tmpPage;
    }
    
    page.value = "";
    doPageSearch();
  }
  
  function checkRate(input){
     var re = /^[1-9]+[0-9]*]*$/;   //判断字符串是否为数字     //判断正整数 /^[1-9]+[0-9]*]*$/   
     if (!re.test(input))
    {  
        return false;
     }
     return true;
}


function goToCurrPage(){
  	document.onkeypress=function(e){//用document.onkeypress不会出现多次触发按键事件  
  	//$("#pageText").keydown(function(e)这种用法会导致多次触发按键事件
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
      		var pNo = $("#pageText").val();
		  	if(!checkRate(pNo)){
		  		alert("请输入正确的页码！");
		  		return;
		  	}
		  	
		  	var fristPage = $("#pageNo").val();
		  	if(fristPage == 0 || fristPage == 1){
		  		if(pNo < fristPage || pNo == 0){
		  			alert("输入的数字小于最小页码！");
		  			return;
		  		}
		  	}
		  	
		  	var totalPage = $("#totalPages").val();
		  	if(eval(pNo) > eval(totalPage)){
		  		alert("输入的数字大于最大页码！");
		  		return;
		  	}
      		goToPage('goToPage');
		}
      }
  	
  }
  
  //设置当前页和总页数、记录旧的查询条件并设置图片按钮禁用或启用  
  function setPageNum(pageSize,pageNum,pageNumTotal,totalNum){ 
	if(document.all){
	 document.getElementById("pageNum").innerText=pageNum;
     document.getElementById("pageNumTotal").innerText=pageNumTotal;
	 document.getElementById("totalNum").innerText=totalNum;
	}else{
	 document.getElementById("pageNum").textContent=pageNum;
     document.getElementById("pageNumTotal").textContent=pageNumTotal;
	 document.getElementById("totalNum").textContent=totalNum;
	}
	
    if(pageNumTotal==0 || pageNumTotal==1){
      disableImg("firstPage");
      disableImg("prePage");
      disableImg("nextPage");
      disableImg("lastPage");
    }else if(pageNum==1){
      disableImg("firstPage");
      disableImg("prePage");
      enableImg("nextPage");
      enableImg("lastPage");
    }
    else if(pageNum==pageNumTotal){
      enableImg("firstPage");
      enableImg("prePage");
      disableImg("nextPage");
      disableImg("lastPage");
    }else{
      enableImg("firstPage");
      enableImg("prePage");
      enableImg("nextPage");
      enableImg("lastPage");
    }
    
  }
  
//禁用图片按钮功能
  function disableImg(imgId){
    document.getElementById(imgId).style.cursor=""; 
   document.getElementById(imgId).disabled=true;
  }
  
//启用图片按钮功能  
  function enableImg(imgId){
    document.getElementById(imgId).style.cursor="hand"; 
    document.getElementById(imgId).disabled=false;
  }

  
  function setSelectValueByValue(id,value){
	var sel = document.getElementById(id);
	if(sel==undefined)
		return false;
	for(var i=0;i<sel.options.length;i++)
	{
		if(sel.options[i].value==value)
		{
			sel.options[i].selected=true;
			break;
		}
	}
	return true;
}
 
</script>
