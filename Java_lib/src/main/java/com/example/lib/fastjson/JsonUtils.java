package com.example.lib.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.JSONToken;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {


    //默认日期格式（年月日时分秒）
    public static final String default_dateFormat = "yyyy-MM-dd HH:mm:ss";
    //存在时间格式（年月日）
    public static final String dateFormat = "yyyy-MM-dd";

    /**
     * json字符串转对象
     *
     * @param str   字符串
     * @param clazz 需要转成想要的对象
     * @param <T>   返回相应对象
     * @return
     */
    public static <T> T jsonToObject(String str, Class<T> clazz) {
        return JSON.parseObject(str, clazz);
    }

    /**
     * 对象转json字符串，默认不执行进行日期转换
     *
     * @param obj 对象
     * @return
     */
    public static String ObjectTojson(Object obj) {


        return ObjectTojson(obj, false);
    }

    /**
     * 对象转json字符串，使用默认日期转换
     *
     * @param obj           对象
     * @param useDateFormat 自定义时间格式
     * @return
     */
    public static String ObjectTojson(Object obj, boolean useDateFormat) {

        return ObjectTojson(obj, useDateFormat, default_dateFormat);
    }

    /**
     * 自定义日期格式
     *
     * @param obj
     * @param dateFormat
     * @return
     */
    public static String ObjectTojson(Object obj, String dateFormat) {

        return ObjectTojson(obj, true, dateFormat);

    }

    /**
     * 对象转字符串，总处理方法，不对外开放
     *
     * @param obj           javabean对象
     * @param useDateFormat
     * @param dateFormat
     * @return
     */
    private static String ObjectTojson(Object obj, boolean useDateFormat, String dateFormat) {
        if (useDateFormat) {
            return JSON.toJSONStringWithDateFormat(obj, dateFormat);
        }
        return JSON.toJSONString(obj);

    }

    /**
     * json格式解析为List集合，不解决格式时间问题
     *
     * @param str   json字符串
     * @param clazz 要转换的对象
     * @param <T>
     * @return
     */
    public static <T> List<T> jsonTolist(String str, Class<T> clazz) {

        return JSON.parseArray(str, clazz);
    }




/*    public static <T> T pare(String jsonStr, Class<T> clazz){


        Object object = JSON.parse(jsonStr);
        if (object instanceof JSONObject) {
            return JSON.parseObject(jsonStr, clazz);
        } else if (object instanceof JSONArray) {
            return JSON.parseArray(jsonStr, clazz);

        } else {

        }

    }*/


    public static void main(String[] agrs) {

        List<UserTestBean> userTestBeans = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            UserTestBean bean = new UserTestBean();
            bean.setAge(i);
            bean.setName("test-" + i);
            userTestBeans.add(bean);
        }


        String jsonStr =  JSON.toJSON(userTestBeans).toString();
        System.out.println("jsonStr:"+jsonStr);
        List<UserTestBean> userTestBeans1= (List<UserTestBean>) JSON.parse(jsonStr);
        System.out.println("userTestBeans1:"+userTestBeans1.size());




    }

}