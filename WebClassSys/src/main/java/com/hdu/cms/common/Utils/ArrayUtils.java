package com.hdu.cms.common.Utils;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JetWang on 2016/10/10.
 */
public class ArrayUtils {

    /**
     * [ 1, 2] list 转为 1,2,3,4,5
     * @param ids
     * @return
     */
    public static  String listToDotString(List ids){
        if(CollectionUtils.isNotEmpty(ids)){
            return ids.toString().replace("[","").replace("]"," ").trim();
        }
        return " ";
    }

    /**
     * 1,2,3,4 转化为 [1,2,3,4]
     * @param dotStr
     * @return
     */
    public static List<Integer> StringDotToList(String dotStr){
        if(StringUtils.isNotBlank(dotStr)){
            String[] arry = dotStr.split(",");
            List<Integer> list = Lists.newArrayList();
            for(String item :arry){
                list.add(Integer.valueOf(item));
            }
            return list;
        }
        return Lists.newArrayList();
    }
}
