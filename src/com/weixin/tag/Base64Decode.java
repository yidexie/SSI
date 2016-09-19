package com.weixin.tag;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.weixin.core.util.Base64Utils;
import com.weixin.core.util.MyUtil;
import com.weixin.core.util.StringUtil;

public class Base64Decode extends SimpleTagSupport {

	public String string;

	public void doTag() throws JspException, IOException {
		Writer out = getJspContext().getOut();
		String result = Base64Utils.decode(string);
		if (result.startsWith(","))
			result = result.substring(1);
		out.write(result);
		out.flush();
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

}