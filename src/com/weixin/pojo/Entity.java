package com.weixin.pojo;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

/**
 * Copyright(C):
 * 
 * @n
 * @n File: Entity.java
 * @n Function:
 */

public class Entity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String l_id;

	private int startNo = 0; // 开始条数

	private int pageSize = 10; // 每页显示数量

	private String orderBy = "id"; // 排序 字段名

	private String order = "desc"; // 排序方式

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getOrderBy() {
		if (StringUtils.isEmpty(orderBy))
			return "id";
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOrder() {
		if (StringUtils.isEmpty(order))
			return "desc";
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public int getStartNo() {
		return startNo;
	}

	public void setStartNo(int startNo) {
		this.startNo = startNo;
	}

	public String getL_id() {
		return l_id;
	}

	public void setL_id(String l_id) {
		this.l_id = l_id;
	}

}
