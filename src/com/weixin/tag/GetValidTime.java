package com.weixin.tag;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class GetValidTime extends SimpleTagSupport {

	public String time;

	public void doTag() throws JspException, IOException {
		Writer out = getJspContext().getOut();
		if (time.length() > 19)
			time = time.substring(0, 19);
		out.write(time.replace(" 00:00:00", "").replace(" 00:00:01", ""));
		out.flush();
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}