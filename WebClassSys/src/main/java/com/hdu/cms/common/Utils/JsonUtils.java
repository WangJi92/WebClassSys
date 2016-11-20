package com.hdu.cms.common.Utils;

/**
 * Created by JetWang on 2016/10/1.
 */
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

    public static String object2Json(Object obj){
        String result = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            result = objectMapper.writeValueAsString(obj);
        } catch (IOException e) {
            e.printStackTrace();
            LogUtils.logException(e);
        }
        return result;
    }

    public static Map<?, ?> jsonToMap(String json){
        return json2Object(json, Map.class);
    }
    public static List<?> jsonToList(String json){
        return json2Object(json,List.class);
    }

    public static <T> T json2Object(String json, Class<T> cls){
        T result = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            result =  objectMapper.readValue(json, cls);
        } catch (IOException e) {
            e.printStackTrace();
            LogUtils.logException(e);
        }

        return result;
    }

    public static <T> T conveterObject(Object srcObject, Class<T> destObjectType) {
        String jsonContent = object2Json(srcObject);
        return json2Object(jsonContent, destObjectType);
    }
}
