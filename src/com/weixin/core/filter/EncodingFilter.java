package com.weixin.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
 
/** 
 *Copyright(C):	
 *@n
 *@n File:			EncodingFilter.java
 *@n Function:		编码过滤器
 *@n Author:		administrator
 */
public class EncodingFilter implements Filter {

    private String defaultEncoding = "UTF-8";
 
    public void init(FilterConfig config) throws ServletException {
    	
        if(config.getInitParameter("defaultEncoding") != null){
        	
            this.defaultEncoding = config.getInitParameter("defaultEncoding");
        }
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
            ServletException {

    	
    	 
        req.setCharacterEncoding(getEncoding());
        
        res.setContentType(getContentType()); 
        
        chain.doFilter(req, res);
    }

  

	private String getEncoding() {
    	
        return defaultEncoding;
    }

    protected String getContentType() {
    	
        return "text/html; charset=" + getEncoding();
    }

    public void destroy() {

    }
 
}
