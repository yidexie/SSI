package com.weixin.sys.actions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.mytools.util.StringUtils;
import com.opensymphony.xwork2.ModelDriven;
import com.weixin.core.actions.BaseAction;
import com.weixin.core.pojo.UserSession;
import com.weixin.sys.pojo.SysLog;
import com.weixin.sys.service.SysService;

public class SysLogAction extends BaseAction implements ModelDriven<SysLog> {

	private static Logger logger = Logger.getLogger(SysLogAction.class);

	private SysLog sysLog = new SysLog();

	private SysService sysService;

	private String tips = "";

	private String ids;

	public String delete() {
		UserSession userSession = this.getUser();
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			ids = request.getParameter("ids");
			String[] idStr = new String[] {};
			if (!StringUtils.isEmpty(ids)) {
				if (ids.indexOf(",") > 0) {
					idStr = ids.split(",");
				} else {
					idStr = new String[] { ids };
				}
				sysService.removeObjects("SysLog.deleteByID", idStr);
				// 记录登录日志
				SysLog sysLog = new SysLog();
				sysLog.setType(3);// 1表示登录2表示任务3表示管理
				sysLog.setUserid(userSession.getId());
				String remark = "用户" + userSession.getUsername() + "删除了："
						+ idStr.length + "条日志";
				sysLog.setRemark(remark);
				sysLog.setIp(request.getRemoteAddr());
				sysService.saveObject("SysLog.addSysLog", sysLog);
				tips = "日志删除成功";
			}
		} catch (Exception e) {
			tips = "日志删除失败";
			logger.error(e);
		}

		return JSON;
	}

	public String list() {
		try {
			String time = " 23:59:59";
			if (!StringUtils.isEmpty(sysLog.getTimeEnd())) {
				time = sysLog.getTimeEnd() + time;
				sysLog.setTimeEnd(time);
			}
			page = sysService.getPageList("SysLog.getPageList",
					"SysLog.getTotalCount", page, sysLog);
			sysLog.setTimeEnd(time.replace(" 23:59:59", ""));
		} catch (Exception e) {
			logger.error("日志列表异常", e);
		}
		return "list";
	}

	public SysLog getModel() {
		return sysLog;
	}

	public SysLog getSysLog() {
		return sysLog;
	}

	@com.googlecode.jsonplugin.annotations.JSON(serialize = false)
	public SysService getSysService() {
		return sysService;
	}

	public String getTips() {
		return tips;
	}

	public void setSysLog(SysLog sysLog) {
		this.sysLog = sysLog;
	}

	@com.googlecode.jsonplugin.annotations.JSON(serialize = false)
	public void setSysService(SysService sysService) {
		this.sysService = sysService;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

}
