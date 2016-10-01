package com.hdu.cms.common.RequestResponseContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by JetWang on 2016/10/1.
 * 根据拦截器进行加载当前线程的HttpResponse 和 HttpRequest 对象
 * 在非Controller层获取Http对象信息
 */
public class RequestResponseContext {

    private static ThreadLocal<HttpServletRequest> REQUEST_CHREADLOCAL = new ThreadLocal<HttpServletRequest>();

    private static ThreadLocal<HttpServletResponse> REPONSE_THREADLOCAL = new ThreadLocal<HttpServletResponse>();

    public static void setRequest(HttpServletRequest request) {
        REQUEST_CHREADLOCAL.set(request);
    }

    public static HttpServletRequest getRequest() {
        return REQUEST_CHREADLOCAL.get();
    }

    public static void removeRequest() {
        REQUEST_CHREADLOCAL.remove();
    }

    public static void setResponse(HttpServletResponse response) {
        REPONSE_THREADLOCAL.set(response);
    }

    public static HttpServletResponse getResponse() {
        return REPONSE_THREADLOCAL.get();
    }

    public static void removeResponse() {
        REPONSE_THREADLOCAL.remove();
    }
}
