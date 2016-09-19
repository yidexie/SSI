<%@ page contentType="text/html;charset=UTF-8"%>  
<%@ include file="../common.jsp"%>
<html>
<head>
<META NAME="ROBOTS" CONTENT="NOINDEX,NOFOLLOW">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>微博专搜系统</title>
<link rel="stylesheet" type="text/css" href="${URL}/powercharts/css/Style.css"></link>
<script type="text/javascript" src="${URL}/powercharts/js/FusionCharts.js"></script>
</head>  
<body>
<table width="98%" border="0" cellspacing="0" cellpadding="3" align="center">
  <tr> 
    <td valign="top" class="text" align="center">
    <div id="chartdiv" align="center" style="padding-top: 15px;"></div>
      	<script type="text/javascript">
		   var chart = new FusionCharts("${URL}/powercharts/powercharts/Radar.swf", "ChartId", "600", "400", "0", "0");
		   chart.setDataURL("${URL}/powercharts/data/${filename}.xml");		   
		   chart.render("chartdiv");
		</script> 
	</td>
  </tr>
</table>
</body> 
</html>

