package com.hdu.cms.common.BaseAction;

/**
 * Created by JetWang on 2016/10/1.
 */
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hdu.cms.common.RequestResponseContext.RequestResponseContext;
import com.hdu.cms.common.Utils.ActionResult;
import com.hdu.cms.common.Utils.JsonUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 显示层的基类方便操作和处理我们的对象信息
 *Created by JetWang on 2016/10/1.
 */
public class BaseAction {
    /**
     * org.springframework.web.context.request.RequestContextListener 注册的监听器之后 可以直接通过此处理访问Request对象
     * @return
     */
    protected HttpServletRequest getServletRequest() {
        return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
    }

    protected ServletContext getServletContext(){
        HttpServletRequest request = getServletRequest();
        if (request != null){
            return request.getSession().getServletContext();
        }

        return null;
    }
    protected HttpServletResponse getServletResponse(){
        return RequestResponseContext.getResponse();
    }
    protected void writeResponse(ActionResult result) throws IOException {
        HttpServletResponse response = getServletResponse();
        response.setContentType("text/html; charset=utf-8");
        OutputStream stream = response.getOutputStream();
        stream.write(JsonUtils.object2Json(result).getBytes("utf-8"));
        stream.flush();
        stream.close();
    }
}
