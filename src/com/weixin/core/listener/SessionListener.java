package com.weixin.core.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class SessionListener implements HttpSessionListener {

    private static final Log logger = LogFactory.getLog(SessionListener.class);

    /**
     * session创建时触发的事件
     */
    public void sessionCreated(HttpSessionEvent se) {
        logger.debug("new session created");
    }

    /**
     * session失效时触发的事件
     */
    public void sessionDestroyed(HttpSessionEvent se) {
        try {
            logger.debug("用户关闭会话或session超时....");
//            HttpSession session = se.getSession();
//            @SuppressWarnings("unused")
//            UserSession usersession = (UserSession) session.getAttribute(Constants.USERSESSION);
//            session.removeAttribute(Constants.USERSESSION);
//            session.invalidate();
//            logger.debug("用户远程连接会话已经关闭.");
        } catch (Exception e) {
            logger.error("关闭session异常！", e);
        }
    }

}
