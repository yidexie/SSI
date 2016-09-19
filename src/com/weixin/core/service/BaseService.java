package com.weixin.core.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.weixin.core.pojo.Entity;
import com.weixin.core.pojo.Page;

/** 
 *Copyright(C):	
 *@n
 *@n File:		  BaseService.java
 *@n Function:		
 *@n Author:		administrator
 */
@SuppressWarnings("unchecked")
public interface BaseService {
	
		 
	 
	 
	 
	/**
     *<br> ************************************************************************
     *<br> 功    能：根据sql方法名称和对象插入数据库
     *<br> 入口参数：param sqlName sql方法名称
     *             param obj 对象
     *<br> 返    回：Object 对象
     *<br> ************************************************************************
     */
	public Object saveObject(String sqlName, Object obj);
	
	 
	/**
     *<br> ************************************************************************
     *<br> 功    能：根据sql方法名称和对象修改数据库
     *<br> 入口参数：param sqlName sql方法名称
     *             param obj 对象
     *<br> 返    回： 
     *<br> ************************************************************************
     */
	public void updateObject(String sqlName, Object obj) ;
	

	/**
     *<br> ************************************************************************
     *<br> 功    能：保存或更新数据库对象
     *<br> 入口参数：param sqlName4Add 增加sql名称
     *			   param sqlName4Edit 修改sql名称
     *             param entity 参数实体类
     *<br> 返    回： 
     *<br> ************************************************************************
     */
	
	public void saveOrUpdateObject(String sqlName4Add, String sqlName4Edit, Entity entity);
	
	/**
     *<br> ************************************************************************
     *<br> 功    能：保存或更新数据库对象
     *<br> 入口参数：param sqlName4Add 增加sql名称 
     *             param entity 参数实体类
     *<br> 返    回： 
     *<br> ************************************************************************
     */
	public Object saveObjectAndGetId(String sqlName4Add, Entity entity);
	/**
     *<br> ************************************************************************
     *<br> 功    能：在同一事务中将批量数据插入数据库中
     *<br> 入口参数：param sqlName 执行插入的sql方法名称
     *             param list 对象
     *<br> 返    回： void
     *<br> ************************************************************************
     */
	public void insertList(String sqlName,List list)throws DataAccessException;
	
	/**
     *<br> ************************************************************************
     *<br> 功    能：在同一事务中将批量数据更新到数据库中
     *<br> 入口参数：param sqlName 执行插入的sql方法名称
     *             param list 对象
     *<br> 返    回： void
     *<br> ************************************************************************
     */
	public void updateList(String sqlName,List list)throws DataAccessException;
	
	 
	/**
     *<br> ************************************************************************
     *<br> 功    能：根据sql方法名称和对象id
     *<br> 入口参数：param sqlName sql方法名称
     *             param id id对象
     *<br> 返    回： void
     *<br> ************************************************************************
     */
	public void removeObject(String sqlName, Object id) ;
	
	 
	/**
     *<br> ************************************************************************
     *<br> 功    能：根据sql方法名称和对象id
     *<br> 入口参数：param sqlName sql方法名称
     *             param id id对象
     *<br> 返    回： void
     *<br> ************************************************************************
     */
	public void removeObjects(String sqlName, Object[] ids);
	
	 
	/**
     *<br> ************************************************************************
     *<br> 功    能：根据sql方法名称和条件参数（HashMap）取回查询结果列表
     *<br> 入口参数：param sqlName sql方法名称
     *             param hm 条件参数的值
     *<br> 返    回： List 列表
     *<br> ************************************************************************
     */
	public List getList(String sqlName, HashMap hm);
	 
	 
	/**
     *<br> ************************************************************************
     *<br> 功    能：根据sql方法名称和主键值，取回查询结果列表
     *<br> 入口参数：param sqlName sql方法名称
     *             param obj 实体对象
     *<br> 返    回： List 列表
     *<br> ************************************************************************
     */
	 
	public List getList(String sqlName, Object obj) ;
	
	 
	/**
     *<br> ************************************************************************
     *<br> 功    能：根据sql方法名称取回查询结果列表
     *<br> 入口参数：param sqlName sql方法名称 
     *<br> 返    回： List 列表
     *<br> ************************************************************************
     */
	public List getList(String sqlName) ;

	/**
     *<br> ************************************************************************
     *<br> 功    能：获取HASHMAP
     *<br> 入口参数：param sqlName sql方法名称 
     *			   param obj 参数列表
     *			   param keyFiled 主键
     *			   param valueField 值
     *<br> 返    回： Map
     *<br> ************************************************************************
     */
	public Map getMap(String sqlName,Object obj,String keyFiled,String valueField);
	/**
     *<br> ************************************************************************
     *<br> 功    能：根据sql方法名称和条件参数（HashMap）取回查询结果对象
     *<br> 入口参数：param sqlName sql方法名称
     *             param hm 条件参数的值
     *<br> 返    回： 实体对象
     *<br> ************************************************************************
     */
	public Object getObject(String sqlName, HashMap hm);

	 
	/**
     *<br> ************************************************************************
     *<br> 功    能：根据sql方法名称和主键值，取回查询结果对象
     *<br> 入口参数：param sqlName sql方法名称
     *             param id 主键的值
     *<br> 返    回： 实体对象
     *<br> ************************************************************************
     */
	public Object getObject(String sqlName, Object id);

	
	 
	/**
     *<br> ************************************************************************
     *<br> 功    能：根据sql方法名称，取回查询结果对象
     *<br> 入口参数：param sqlName sql方法名称 
     *<br> 返    回： 实体对象
     *<br> ************************************************************************
     */
	public Object getObject(String sqlName);

	/**
     *<br> ************************************************************************
     *<br> 功    能：根据sql方法名称，查询条件Map，分页参数查询数据
     *<br> 入口参数：param sqlName4Query sql查询方法名称 
     *			   param sqlName4Count sql统计方法名称 
     *			   param entity 查询条件实体类
     *			   param page 分页参数
     *<br> 返    回： 查询数据
     *<br> ************************************************************************
     */
	public Page getPageList(String sql,String sqlForCount,Page page,Object param);
	
}
