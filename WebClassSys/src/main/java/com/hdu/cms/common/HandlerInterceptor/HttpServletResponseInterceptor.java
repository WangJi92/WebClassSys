package com.hdu.cms.common.HandlerInterceptor;

import com.hdu.cms.common.RequestResponseContext.RequestResponseContext;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by JetWang on 2016/10/1.
 * 通过拦截器保存当前的请求的对象信息进行处理。
 */
public class HttpServletResponseInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        RequestResponseContext.setResponse(response);
        RequestResponseContext.setRequest(request);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        RequestResponseContext.removeResponse();
        RequestResponseContext.removeRequest();
    }
}
