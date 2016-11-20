package com.hdu.cms.common.Utils;

import com.hdu.cms.common.RequestResponseContext.RequestResponseContext;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * Created by JetWang on 2016/10/1.
 */
public class HttpRequestUtils {
    public static String EMPTY_STRING="";

    /**
     * 得到上下文环境的对象
     * @return
     */
    public static ServletContext getServletContext(){
        HttpServletRequest request = RequestResponseContext.getRequest();
        if (request != null){
            return request.getSession().getServletContext();
        }
        return null;
    }

    /**
     * 获得web目录的根路径信息
     * @return
     */
   public static String getWebContextRootPath(){
       ServletContext servletContext = getServletContext();
       if (servletContext != null){
           return servletContext.getRealPath(File.separator);
       }
       return EMPTY_STRING;
   }
    public  static  String  getBasePath(){
        HttpServletRequest request = RequestResponseContext.getRequest();
        if(request !=null){
            String path = request.getContextPath();
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
            return basePath;
        }
        return EMPTY_STRING;

    }
}
