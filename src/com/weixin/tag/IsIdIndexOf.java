package com.weixin.tag;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.weixin.core.util.MyUtil;
import com.weixin.core.util.StringUtil;

public class IsIdIndexOf extends SimpleTagSupport {

	public String idSeq;

	public String id;

	public void doTag() throws JspException, IOException {
		Writer out = getJspContext().getOut();
		if (MyUtil.isIdIndexOf(idSeq, id))
			out.write("checked");
		else
			out.write("");
		out.flush();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdSeq() {
		return idSeq;
	}

	public void setIdSeq(String idSeq) {
		this.idSeq = idSeq;
	}

}