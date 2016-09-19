package com.weixin.core.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.weixin.core.pojo.Page;

public class PageUtil {

	public final static int DEFAULT_PAGE_SIZE = 10;

	public static final int DEFAULT_PAGE_NO = 1;

	@SuppressWarnings("unchecked")
	public static String pageTag(String action, Page page, Map params) {

		// int pageSize = page.getPageSize();
		long pageCount = page.getTotalPages();
		long totalCount = page.getTotalCount();
		long pageNo = page.getPageNo();
		StringBuffer sb = new StringBuffer();

		sb.append("<table width='98%'  border='0' align='center' cellpadding='0' cellspacing='0'>\n");
		sb.append("<tr>");
		sb.append("<td width='54%' height='25'>共有 ");
		sb.append(totalCount);
		sb.append(" 条记录，当前第 ");
		sb.append(pageNo);
		sb.append(" 页，共 ");
		sb.append(pageCount);
		sb.append(" 页</td>\n<td width='46%' align='right'>");
		if (pageNo > 1) {
			sb.append("<input name='Submit' type='submit' onclick=\"goPage('1')\" class='pag_btn' value='首页'>\n");
		}

		if (pageNo > 1) {
			sb.append("<input name='Submit' type='submit' onclick=\"goPage('"
					+ String.valueOf(pageNo - 1)
					+ "')\" class='pag_btn' value='上一页'>\n");
		}
		if (pageNo < pageCount) {
			sb.append("<input name='Submit' type='submit' onclick=\"goPage('"
					+ String.valueOf(pageNo + 1)
					+ "')\" class='pag_btn' value='下一页'>\n");
		}

		if (pageNo < pageCount) {
			sb.append("<input name='Submit' type='submit' onclick=\"goPage('"
					+ pageCount + "')\" class='pag_btn' value='尾页'>\n");
		}
		sb.append("转到<input name='textfield' id='go_page' type='text' size='3'>页\n");
		sb.append("<input name='Submit' type='submit' class='go_btn' onclick='jumpPage()' value='GO'>");
		sb.append("</td></tr></table>\n");
		sb.append("<form  style='display:none' id='paged_form' method='post' action='");
		sb.append(action);
		sb.append("'>");
		// 构造参数
		if (params == null) {
			params = new HashMap();
		}
//		Iterator it = params.keySet().iterator();
//		while (it.hasNext()) {
//			String key = it.next().toString();
//			if (params.get(key) == null)
//				continue;
//			sb.append("<input type=hidden name='" + key + "' value='"
//					+ params.get(key) + "' />\n");
//		}
		sb.append("<input type=hidden name='pageNo' id=pageNo value='");
		sb.append(pageNo);
		sb.append("'/>\n");
		if (page.getOrderBy() != null) {
			sb
					.append("<input type=hidden name='orderBy' id=pageorderBy value='");
			sb.append(page.getOrderBy());
			sb.append("'/>\n");
		}
		if (page.getOrder() != null) {
			sb.append("<input type=hidden name='order' id=pageorder value='");
			sb.append(page.getOrder());
			sb.append("'/>\n");
		}
		sb.append("</form>\n");
		sb.append("<script  language='javascript'>\n");
		sb.append("function goPage(pageNo){\n");
		sb.append("document.getElementById('pageNo').value=pageNo;\n");
		sb.append("document.getElementById('paged_form').submit();\n");
		sb.append("}\n");

		sb.append("function jumpPage(){\n");
		sb.append("var jmpPage=document.getElementById('go_page').value;\n");
		sb.append("if(jmpPage==''||jmpPage==null) return false;");
		sb.append("if((jmpPage.replace(/\\d/g, '').length != 0))return false;");
		sb.append("document.getElementById('pageNo').value=jmpPage;\n");
		sb.append("document.getElementById('paged_form').submit();\n");
		sb.append("}\n");
		sb.append("</script>");
		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	public static String pageMultTag(String action, Page page, Map params){
		long pageCount = page.getTotalPages();
		long totalCount = page.getTotalCount();
		long pageNo = page.getPageNo();
		int pageSize = page.getPageSize();
		StringBuffer sb = new StringBuffer();
		sb.append("<div class='pages-nav'><span class='text'>");
		sb.append("共");
		sb.append(totalCount);
		sb.append("条记录 ");
		sb.append(pageSize+"条/页 ");
		sb.append(pageNo+"/"+pageCount+" 页</span>");
		sb.append("<a class='next' onclick='goPage(1);' href='#nogo'>&laquo;首页</a>");
		
		long prePage = pageNo - 1;
		if(prePage < 1)
			prePage = 1;
		sb.append("<a class='next' onclick='goPage(");
		sb.append(prePage);
		sb.append(");' href='#nogo'>&#8249;上一页</a>");

		long tempCount = 7;
		boolean isMore = true;
		if(tempCount > pageCount){
			tempCount = pageCount;
			isMore = false;
		}
		long index = 1;
		long indexCount = tempCount;
		if(isMore && pageNo > tempCount - 1 && pageNo - tempCount < 4 && pageNo - tempCount > -1){
			indexCount = pageNo + 1;
		}
		for(index = 1;index <= indexCount;index++){
			if(index != pageNo){
				sb.append("<a onclick='goPage(");
				sb.append(index);
				sb.append(");' ");
				sb.append("href='#nogo'>");
				sb.append(index);
				sb.append("</a>");
			}else{
				sb.append("<span class='currentPage'>" + index + "</span>");
			}
		}
		if(isMore){
			if(pageNo > tempCount-1 && pageNo - tempCount > 3 && pageNo < pageCount - 3){
				sb.append("<span>......</span>");
				index = pageNo - 2;
				for(int i = 0; i < 5; ++i){
					if(index != pageNo){
						sb.append("<a onclick='goPage(");
						sb.append(index);
						sb.append(");' ");
						sb.append("href='#nogo'>");
						sb.append(index);
						sb.append("</a>");
					}else{
						sb.append("<span class='currentPage'>" + index + "</span>");
					}
					index++;
					if(index > pageCount){
						break;
					}
				}
			}
			if(pageCount - index > 3){
				sb.append("<span>......</span>");
				index = pageCount - 3;
			}
			for(; index <= pageCount; ++index){
				if(index != pageNo){
					sb.append("<a onclick='goPage(");
					sb.append(index);
					sb.append(");' ");
					sb.append("href='#nogo'>");
					sb.append(index);
					sb.append("</a>");
				}else{
					sb.append("<span class='currentPage'>" + index + "</span>");
				}
			}
		}
		
		long nextPage = pageNo + 1;
		if(nextPage > pageCount)
			nextPage = pageCount;
		sb.append("<a class='next' onclick='goPage(");
		sb.append(nextPage);
		sb.append(");' href='#nogo'>下一页&#8250;</a>");
			
		sb.append("<a class='next' onclick='goPage(");
		sb.append(pageCount);
		sb.append(");' href='#nogo'>尾页&raquo;</a><span class='goto'>");
		sb.append("<input type='text' id='gotopage' size='3' onkeypress='respKeyEvent(event);'/></span><a href='#nogo' class='text' onclick='jumpPage();'>GO</a>");
		sb.append("<span>&nbsp;</span></div>");
		sb.append("<form  style='display:none' id='paged_form' method='post' action='");
		sb.append(action);
		sb.append("'>");
		// 构造参数
		if (params == null) {
			params = new HashMap();
		}
		Iterator it = params.keySet().iterator();
		while (it.hasNext()) {
			Object key = it.next();
			Object value = params.get(key);
			if(key.toString().equals("pageNo"))
				continue;
			if (value == null)
				continue;
			Object result = null;
			if(value.getClass().isArray()){
				Object[] vs = (Object[]) value;
				result = vs[0];
			}else
				result = value;
			sb.append("<input type=hidden name='" + key + "' value='"
					+ result + "' />\n");
		}
		sb.append("<input type=hidden name='pageNo' id='pageNo' value='");
		sb.append(pageNo);
		sb.append("'/>\n");
		
		sb.append("</form>\n");
		sb.append("<script  language='javascript'>\n");
		sb.append("function goPage(pageNo){\n");
		sb.append("document.getElementById('pageNo').value=pageNo;\n");
		sb.append("document.getElementById('paged_form').submit();\n");
		sb.append("}\n");

		sb.append("function jumpPage(){\n");
		sb.append("var jmpPage=document.getElementById('gotopage').value;\n");
		sb.append("if(jmpPage==''||jmpPage==null) return false;");
		sb.append("if((jmpPage.replace(/\\d/g, '').length != 0))return false;");
		sb.append("if(jmpPage > "+pageCount+"||jmpPage<1)return false;");
		sb.append("document.getElementById('pageNo').value=jmpPage;\n");
		sb.append("document.getElementById('paged_form').submit();\n");
		sb.append("}\n");
		sb.append("function respKeyEvent(e){var key = window.event ? e.keyCode : e.which; if(key == 13){jumpPage();}}");
		sb.append("</script>");
		return sb.toString();
	}
	

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Page page = new Page();
		page.setPageNo(5);
		page.setPageSize(10);
		page.setTotalCount(1000);
		 System.out.println(PageUtil.pageMultTag("", page, null));
	}
}
