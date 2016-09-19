package com.weixin.core.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

/** 
 *Copyright(C):	
 *@n
 *@n File:		  BaseDao.java
 *@n Function:		
 *@n Author:		administrator
 */
@SuppressWarnings("unchecked")
public interface BaseDao {
	
 
	public SqlMapClientTemplate getTemplete();
 
	
 
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
     *             param id 主键的值
     *<br> 返    回： List 列表
     *<br> ************************************************************************
     */
	 
	public List getList(String sqlName, Object id) ;
	
	 
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
     *<br> 功    能：根据sql方法名称和主键值，取回查询结果列表
     *<br> 入口参数：param sqlName sql方法名称
     *             param obj 实体对象
     *<br> 返    回： List 列表
     *<br> ************************************************************************
     */
	public Object getObject(String sqlName, Object obj);

	
	 
	/**
     *<br> ************************************************************************
     *<br> 功    能：根据sql方法名称，取回查询结果对象
     *<br> 入口参数：param sqlName sql方法名称 
     *<br> 返    回： 实体对象
     *<br> ************************************************************************
     */
	public Object getObject(String sqlName);

	/**
	 * @return 获取一个list,同时带参数,主要是为分页提供方便
	 */
	public List<Object> getPage(String sql,Object parameterObject, int skipResults,
			int maxResults) ;
	
	/**
	 * @param primaryKey
	 *            获取记录的属性值
	 * @return
	 */
	public Object getObjectByIbatis(String sql,Object param) ;

}
