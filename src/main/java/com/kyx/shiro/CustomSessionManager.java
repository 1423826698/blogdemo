package com.kyx.shiro;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

public class CustomSessionManager extends DefaultWebSessionManager {
    public CustomSessionManager(){
        super();
    }
    @Override
    protected Serializable getSessionId(ServletRequest request,
                                        ServletResponse response){
    String sessionId = WebUtils.toHttp(request).getHeader("token");
    if (sessionId!=null){
        request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE,
                ShiroHttpServletRequest.COOKIE_SESSION_ID_SOURCE);
        request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID,sessionId);
        request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID,
                Boolean.TRUE);
        return sessionId;
    }
    return super.getSessionId(request,response);
    }
}
