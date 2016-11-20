package com.hdu.cms.common.ConstantParam;

import org.omg.PortableInterceptor.INACTIVE;

import java.io.Serializable;

/**
 * Created by JetWang on 2016/10/6.
 */
public class ConstantParam  implements Serializable{
    /**
     * 默认的分页的大小
     */
    public static Integer DEFAULT_PAGE_SIZE = 15;
    public static Integer DEFAULT_PAGE_NO = 1;

    //图片的保存路径
    public  static String DEFAULT_IMAGE_PATH ="image\\";

    /**
     * excel 导出目录
     */
    public static  String DEFAULT_EXCEL_PATH ="excel\\";

    public static String  EXPORT_USER ="user.xls";

    public static  String EXPORT_EQUIPMENT="equipment.xls";

    public static String EXPORT_DIC = "dictionary.xls";

    public static String EXPORT_MAINTAIN ="maintain.xls";

    public static String EXPORT_APPLYCLASSOOM="applyclassroom.xls";

    public static String DEFAULT_IMAGE_TYPE =".jpg";
    /**
     * 保存在serssion 中的登录信息 通过base64进行加密然后在加盐操作
     */
    public  static  String SESSION_USERNAME ="CMS_USER";
    public  static  String  COOKIE_NAME ="COOKIE_NAME_CMS";
    public static String COOKIE_PASSWORD="COOKIE_VALUE_CMS";
    public  static String ADD_SALT="SALT_I_LOVE_HANGDIAN";
    /**
     * 默认初始化的星期的数量
     */
    public  static Integer DEFAULT_MAX_WEEK = 20;


}
