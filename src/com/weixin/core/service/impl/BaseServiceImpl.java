package com.weixin.core.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.weixin.core.dao.BaseDao;
import com.weixin.core.pojo.Entity;
import com.weixin.core.pojo.Page;
import com.weixin.core.service.BaseService;

/**
 *Copyright(C):
 * 
 * @n
 *@n File: BaseServiceImpl.java
 *@n Function:
 *@n Author: administrator
 */

@SuppressWarnings("unchecked")
public class BaseServiceImpl implements BaseService {

	private BaseDao baseDao;

	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	/**
	 *<br>
	 * ************************************************************************ <br>
	 * 功 能：根据sql方法名称和对象插入数据库 <br>
	 * 入口参数：param sqlName sql方法名称 param obj 对象 <br>
	 * 返 回：Object 对象 <br>
	 * ************************************************************************
	 */
	public Object saveObject(String sqlName, Object obj) {
		return getBaseDao().saveObject(sqlName, obj);
	}

	/**
	 *<br>
	 * ************************************************************************ <br>
	 * 功 能：根据sql方法名称和对象修改数据库 <br>
	 * 入口参数：param sqlName sql方法名称 param obj 对象 <br>
	 * 返 回： <br>
	 * ************************************************************************
	 */
	public void updateObject(String sqlName, Object obj) {
		getBaseDao().updateObject(sqlName, obj);
	}

	/**
	 *<br>
	 * ************************************************************************ <br>
	 * 功 能：保存或更新数据库对象 <br>
	 * 入口参数：param sqlName4Add 增加sql名称 param sqlName4Edit 修改sql名称 param entity
	 * 参数实体类 <br>
	 * 返 回： <br>
	 * ************************************************************************
	 */
	public void saveOrUpdateObject(String sqlName4Add, String sqlName4Edit,
			Entity entity) {

		if (entity.getId() != null && entity.getId() > 0)
			updateObject(sqlName4Edit, entity);
		else
			saveObject(sqlName4Add, entity);

	}

	/**
	 *<br>
	 * ************************************************************************ <br>
	 * 功 能：保存或更新数据库对象 <br>
	 * 入口参数：param sqlName4Add 增加sql名称 param entity 参数实体类 <br>
	 * 返 回： <br>
	 * ************************************************************************
	 */
	public Object saveObjectAndGetId(String sqlName4Add, Entity entity) {

		return saveObject(sqlName4Add, entity);

	}

	/**
	 *<br>
	 * ************************************************************************ <br>
	 * 功 能：将批量数据插入数据库中 <br>
	 * 入口参数：param sqlName 执行插入的sql方法名称 param list 对象 <br>
	 * 返 回： void <br>
	 * ************************************************************************
	 */
	public void insertList(String sqlName, List list)
			throws DataAccessException {
		getBaseDao().insertList(sqlName, list);

	}

	/**
	 *<br>
	 * ************************************************************************ <br>
	 * 功 能：将批量数据更新到数据库中 <br>
	 * 入口参数：param sqlName 执行插入的sql方法名称 param list 对象 <br>
	 * 返 回： void <br>
	 * ************************************************************************
	 */
	public void updateList(String sqlName, List list)
			throws DataAccessException {
		getBaseDao().updateList(sqlName, list);

	}

	/**
	 *<br>
	 * ************************************************************************ <br>
	 * 功 能：根据sql方法名称和对象id <br>
	 * 入口参数：param sqlName sql方法名称 param id id对象 <br>
	 * 返 回： void <br>
	 * ************************************************************************
	 */
	public void removeObject(String sqlName, Object id) {
		getBaseDao().removeObject(sqlName, id);
	}

	/**
	 *<br>
	 * ************************************************************************ <br>
	 * 功 能：根据sql方法名称和对象id <br>
	 * 入口参数：param sqlName sql方法名称 param ids ids对象数组 <br>
	 * 返 回： void <br>
	 * ************************************************************************
	 */
	public void removeObjects(String sqlName, Object[] ids) {
		getBaseDao().removeObjects(sqlName, ids);
	}

	/**
	 *<br>
	 * ************************************************************************ <br>
	 * 功 能：根据sql方法名称和条件参数（HashMap）取回查询结果列表 <br>
	 * 入口参数：param sqlName sql方法名称 param hm 条件参数的值 <br>
	 * 返 回： List 列表 <br>
	 * ************************************************************************
	 */
	public List getList(String sqlName, HashMap hm) {
		return getBaseDao().getList(sqlName, hm);
	}

	/**
	 *<br>
	 * ************************************************************************ <br>
	 * 功 能：根据sql方法名称和主键值，取回查询结果列表 <br>
	 * 入口参数：param sqlName sql方法名称 param obj 主键的值 <br>
	 * 返 回： List 列表 <br>
	 * ************************************************************************
	 */
	public List getList(String sqlName, Object obj) {
		return getBaseDao().getList(sqlName, obj);
	}

	/**
	 *<br>
	 * ************************************************************************ <br>
	 * 功 能：根据sql方法名称取回查询结果列表 <br>
	 * 入口参数：param sqlName sql方法名称 <br>
	 * 返 回： List 列表 <br>
	 * ************************************************************************
	 */
	public List getList(String sqlName) {
		return getBaseDao().getList(sqlName);
	}

	/**
	 *<br>
	 * ************************************************************************ <br>
	 * 功 能：获取HASHMAP <br>
	 * 入口参数：param sqlName sql方法名称 param obj 参数列表 param keyFiled 主键 param
	 * valueField 值 <br>
	 * 返 回： Map <br>
	 * ************************************************************************
	 */
	public Map getMap(String sqlName, Object obj, String keyFiled,
			String valueField) {
		return getBaseDao().getMap(sqlName, obj, keyFiled, valueField);
	}

	/**
	 *<br>
	 * ************************************************************************ <br>
	 * 功 能：根据sql方法名称和条件参数（HashMap）取回查询结果对象 <br>
	 * 入口参数：param sqlName sql方法名称 param hm 条件参数的值 <br>
	 * 返 回： 实体对象 <br>
	 * ************************************************************************
	 */
	public Object getObject(String sqlName, HashMap hm) {
		return getBaseDao().getObject(sqlName, hm);
	}

	/**
	 *<br>
	 * ************************************************************************ <br>
	 * 功 能：根据sql方法名称和主键值，取回查询结果对象 <br>
	 * 入口参数：param sqlName sql方法名称 param obj 主键的值 <br>
	 * 返 回： 实体对象 <br>
	 * ************************************************************************
	 */
	public Object getObject(String sqlName, Object obj) {
		return getBaseDao().getObject(sqlName, obj);
	}

	/**
	 *<br>
	 * ************************************************************************ <br>
	 * 功 能：根据sql方法名称，取回查询结果对象 <br>
	 * 入口参数：param sqlName sql方法名称 <br>
	 * 返 回： 实体对象 <br>
	 * ************************************************************************
	 */
	public Object getObject(String sqlName) {
		return getBaseDao().getObject(sqlName);
	}

	/**
	 *<br>
	 * ************************************************************************ <br>
	 * 功 能：根据sql方法名称，查询条件Map，分页参数查询数据 <br>
	 * 入口参数：param sqlName4Query sql查询方法名称 param sqlName4Count sql统计方法名称 param
	 * queryCondition 查询条件 param page 分页参数 <br>
	 * 返 回： 查询数据 <br>
	 * ************************************************************************
	 */
	public Page getPageList(String sql,String sqlForCount,Page page,Object param){
		int nowPage = page.getPageNo();
		int maxResults = page.getPageSize();//单页显示多少
		int skipResults = (nowPage-1)*maxResults;
		List results = (List)baseDao.getPage(sql,param, skipResults, maxResults);
		int count = (Integer)getObject(sqlForCount, param);
		
		page.setResult(results);
		page.setTotalCount(count);
		
		return page;
	}


	/*@SuppressWarnings("unchecked")
	public Page getPageList(String GpsQuery, String ZoneQuery, String GpsCount,
			String ZoneCount, String uin, Page page) {

		int GpstotalRecord = (Integer) getObject(GpsCount, uin);
		int ZonetotalRecord = (Integer) getObject(ZoneCount, uin);
		List<IpInfo> list1 = getList(GpsQuery, uin);
		List<IpInfo> list2 = getList(ZoneQuery, uin);
		List<IpInfo> list = new ArrayList<IpInfo>();
		list.addAll(list1);
		list.addAll(list2);
		page.setTotalCount(GpstotalRecord + ZonetotalRecord);
		int pageSize = page.getPageSize();
		int pageNo = page.getPageNo();
		int startNo = (pageNo - 1) * pageSize; // 设置开始条数
		int endNo = pageNo * pageSize; // 设置结束条数

		List<IpInfo> result = list.subList(startNo, endNo);
		page.setResult(result);
		return page;
	}*/

}
