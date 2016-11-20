package com.hdu.cms.common.RequestResponseContext;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by JetWang on 2016/11/20.
 */
public class GlobalContext implements ApplicationContextAware {
    private static ApplicationContext context;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
         context  = applicationContext;
    }
    public static ApplicationContext getContext() {
        return context;
    }
    public final static Object getBean(String beanName) {
         return context.getBean(beanName);
    }
}
