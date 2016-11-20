package com.hdu.cms.common.HandlerInterceptor;

import com.hdu.cms.common.ConstantParam.ConstantParam;
import com.hdu.cms.common.ConstantParam.USERTYPE;
import com.hdu.cms.common.RequestResponseContext.GlobalContext;
import com.hdu.cms.common.Utils.CookieUtils;
import com.hdu.cms.common.Utils.HttpRequestUtils;
import com.hdu.cms.modules.UserInfo.entity.UserInfo;
import com.hdu.cms.modules.UserInfo.service.IUserInfoService;
import com.hdu.cms.modules.UserInfo.service.impl.UserInfoService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by JetWang on 2016/11/20.
 */
public class FilterRequest implements Filter {

    private static  String cookieNameKey = DigestUtils.md5Hex(ConstantParam.COOKIE_NAME + ConstantParam.ADD_SALT);
    private static   String cookiePasswordKey =DigestUtils.md5Hex(ConstantParam.COOKIE_PASSWORD + ConstantParam.ADD_SALT);
    public void destroy() {
    }
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String uri = request.getRequestURI();
        if(!uri.endsWith("jsp")){//非jsp文件
            chain.doFilter(req, resp);
        }else if(uri.indexOf("cms/views/login")>-1){//登录不进行拦截
            chain.doFilter(req, resp);
        }else{//其他的东西 jsp
            AuthorizedInterceptor(request,response,chain,uri);
        }

    }
    private void AuthorizedInterceptor( HttpServletRequest request, HttpServletResponse response ,FilterChain chain,String  reuquesURI)throws ServletException, IOException{
        UserInfo info =(UserInfo) request.getSession().getAttribute(ConstantParam.SESSION_USERNAME);
        //session 存在 和 不存在 session存在，查看！ 不存在找cookie！
        if(info == null){
            //session  不存在 ，只要看cookie了
            getCookie(request,response,chain,reuquesURI);
        }else if(info.getUserType() !=null && !info.getUserType().equals(USERTYPE.ADM.getIndex()) && (reuquesURI.indexOf("cms/views")> -1)){
            //不是管理员，想访问管理员的账号
            clearCookieAndSession(request,response);
            response.sendRedirect(request.getContextPath()+"/views/login/login.jsp");
        }else if(info.getUserType() !=null && info.getUserType().equals(USERTYPE.ADM.getIndex()) && (reuquesURI.indexOf("cms/user")> -1)){
            //是管理员，想访一般用户的网页
            clearCookieAndSession(request,response);
            response.sendRedirect(request.getContextPath()+"/views/login/login.jsp");
        }else{
            chain.doFilter(request, response);
        }
    }

    private void  clearCookieAndSession(HttpServletRequest request, HttpServletResponse response){
        CookieUtils.addCookie(response, cookieNameKey, null, 0); // 清除Cookie
        CookieUtils.addCookie(response,cookiePasswordKey,null, 0); // 清除Cookie
        request.getSession().setAttribute(ConstantParam.SESSION_USERNAME,null);
    }
    private void getCookie( HttpServletRequest request, HttpServletResponse response ,FilterChain chain,String  reuquesURI)throws ServletException, IOException{
        Cookie cookieLoginName = CookieUtils.getCookieByName(request,cookieNameKey);
        Cookie cookiePassword = CookieUtils.getCookieByName(request,cookiePasswordKey);
        if(cookieLoginName !=null &&cookiePassword != null && StringUtils.isNotEmpty(cookieLoginName.getValue()) && StringUtils.isNotEmpty(cookiePassword.getValue())){
            String cookieNameeValue = cookieLoginName.getValue();
            UserInfoService userInfoService =(UserInfoService)GlobalContext.getBean("userInfoService");
            UserInfo userInfo = userInfoService.getUserByLgoinAccount(cookieNameeValue);
            if(userInfo !=null && DigestUtils.md5Hex(userInfo.getPassword()+ ConstantParam.ADD_SALT).equals(cookiePassword.getValue())){
                 if(userInfo.getUserType() !=null && !userInfo.getUserType().equals(USERTYPE.ADM.getIndex()) && (reuquesURI.indexOf("cms/views")> -1)){
                    //不是管理员，想访问管理员的账号
                     clearCookieAndSession(request,response);
                    response.sendRedirect(request.getContextPath()+"/views/login/login.jsp");
                }else if(userInfo.getUserType() !=null && userInfo.getUserType().equals(USERTYPE.ADM.getIndex()) && (reuquesURI.indexOf("cms/user")> -1)){
                    //是管理员，想访一般用户的网页
                     clearCookieAndSession(request, response);
                    response.sendRedirect(request.getContextPath()+"/views/login/login.jsp");
                }else{
                     // cookie 存在了，加入session中去
                     request.getSession().setAttribute(ConstantParam.SESSION_USERNAME,userInfo);
                    chain.doFilter(request, response);
                }
            }else {// cookie 被修改了，密码不正确！
                clearCookieAndSession(request,response);
                response.sendRedirect(request.getContextPath()+"/views/login/login.jsp");
            }
        }else{//cookie 为空
            clearCookieAndSession(request,response);
            response.sendRedirect(request.getContextPath()+"/views/login/login.jsp");
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
