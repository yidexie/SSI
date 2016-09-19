package com.weixin.tag;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.weixin.core.util.StringUtil;

public class GetNoHtmlValidString extends SimpleTagSupport {

	public String string;

	public int length;

	public void doTag() throws JspException, IOException {
		Writer out = getJspContext().getOut();
		out.write(StringUtil.getNoHtmlValidString(string, length));
		out.flush();
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

}