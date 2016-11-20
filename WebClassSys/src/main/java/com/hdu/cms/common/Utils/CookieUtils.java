package com.hdu.cms.common.Utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JetWang on 2016/10/29.
 * 处理cookie 的基础操作类
 */
public class CookieUtils {

    /**
     * 添加cookie在返回的对象中
     * @param response
     * @param key
     * @param value
     * @param maxAge
     */
    public  static void addCookie(HttpServletResponse response,String key,String value,int maxAge)
    {
       if(StringUtils.isNotEmpty(key)){
           Cookie cookie = new Cookie(key,value);
           cookie.setPath("/");
           if(maxAge>=0){
               cookie.setMaxAge(maxAge);
           }
           response.addCookie(cookie);
       }
    }

    /**
     *
     * 通过key 去寻找我们的name 的值
     * @param request
     * @param name
     * @return
     */
    public static Cookie getCookieByName(HttpServletRequest request,String name){
        Map<String,Cookie> cookieMap = ReadCookieMap(request);
        if(cookieMap.containsKey(name)){
            Cookie cookie = (Cookie)cookieMap.get(name);
            return cookie;
        }else{
            return null;
        }
    }
    /**
     * 根据cookie 的名字获取 信息
     * @param request
     * @return
     */
    private static Map<String,Cookie> ReadCookieMap(HttpServletRequest request){
        Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
        Cookie[] cookies = request.getCookies();
        if(null!=cookies){
            for(Cookie cookie : cookies){
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }



}
