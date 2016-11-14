package com.hdu.cms.common.ConstantParam;

/**
 * Created by JetWang on 2016/11/5.
 * 课程表中的时间的安排
 */
public enum LESSONDAY {
    ONE_TWO(1,"第一二节"),
    THREE_FIVE(2,"第三四五节"),
    FIVE_SEVEN(4,"第六七节"),
    EIGHT_NINE(8,"第八九节"),
    TEN_END(16,"第十到十二节");
    private Integer key;
    private String value;
    LESSONDAY(Integer key, String value) {
        this.key = key;
        this.value = value;
    }
    public static LESSONDAY getString(Integer value){
        if(value !=null) {
            for (LESSONDAY item : LESSONDAY.values()) {
                if (item.getKey() == value) {
                    return item;
                }
            }
        }
        //最笨的方法
        return LESSONDAY.ONE_TWO;
    }
    public Integer getKey() {
        return key;
    }
    public String getValue() {
        return value;
    }


}
