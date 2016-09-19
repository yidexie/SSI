package com.weixin.core.pojo;

import java.io.Serializable;

/**
 * Copyright(C):
 * 
 * @n
 * @n File: Entity.java
 * @n Function: 实体信息相关类
 * @n Author: administrator 
 */

public class Entity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOrder() {
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

}
