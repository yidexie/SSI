
//根据传入iframe的id自动调整iframe的高度
function setIframeHeight(id){
	
	$("#" + id).css("height",$("#" + id).contents().find("form").height());
	/*var FFextraHeight=0;
	var navigator=window.navigator.userAgent;
	//alert(navigator);
	var iframe=document.getElementById(id);
	try{
		if(navigator.indexOf("IE 6.0")>=1 || navigator.indexOf("Safari")>=1){
		iframe.height=iframe.contentWindow.document.documentElement.scrollHeight;
		
		}
		else if(navigator.indexOf("Firefox")>=1 || navigator.indexOf("IE 7.0")>=1 || navigator.indexOf("IE 8.0")>=1 || navigator.indexOf("IE 9.0")>=1 || navigator.indexOf("IE 10.0")>=1)
		//iframe.height=iframe.contentDocument.body.offsetHeight + FFextraHeight+10;}
			
		iframe.height=Math.max(iframe.contentWindow.document.body.scrollHeight, iframe.contentWindow.document.documentElement.scrollHeight);
		
		else
			
		iframe.height=Math.max(iframe.contentWindow.document.body.scrollHeight, iframe.contentWindow.document.documentElement.scrollHeight);
	}catch(e){
		
		iframe.height=Math.max(iframe.contentWindow.document.body.scrollHeight, iframe.contentWindow.document.documentElement.scrollHeight);
	}*/

}
