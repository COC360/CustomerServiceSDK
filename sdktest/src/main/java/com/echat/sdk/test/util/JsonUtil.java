package com.yichat.util;

import com.echat.sdk.test.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.PropertyFilter;

import java.util.*;

/**
 * Created by qjl on 2015/6/11.
 */
public class JsonUtil {
    private static JsonConfig jsonConfig = new JsonConfig();

    static {
        jsonConfig.registerJsonValueProcessor(java.util.Date.class, new JsonValueProcessor() {
            @Override
            public Object processArrayValue(Object o, JsonConfig jsonConfig) {
                return process(o);
            }

            @Override
            public Object processObjectValue(String s, Object o, JsonConfig jsonConfig) {
                return process(o);
            }

            Object process(Object o) {
                if (o != null) {
                    return ((Date) o).getTime();
                }
                return null;
            }
        });
        jsonConfig.registerJsonValueProcessor(java.sql.Date.class, new JsonValueProcessor() {
            @Override
            public Object processArrayValue(Object o, JsonConfig jsonConfig) {
                return process(o);
            }

            @Override
            public Object processObjectValue(String s, Object o, JsonConfig jsonConfig) {
                return process(o);
            }

            Object process(Object o) {
                if (o != null) {
                    return ((Date) o).getTime();
                }
                return null;
            }
        });
        PropertyFilter propertyFilter = new PropertyFilter() {
            @Override
            public boolean apply(Object source, String name, Object value) {
                if (value == null || StringUtil.isEmpty(value.toString())) {
                    return true;
                }
                return false;
            }
        };
        jsonConfig.setJsonPropertyFilter(propertyFilter);
    }

    public static String toJSON(Object object) {

        // 测试转换到json字符串 10W次 大概耗时1.8s
        JSONObject object1 = JSONObject.fromObject(object, jsonConfig);
        return object1.toString();
    }

    public static Object toBean(String json, Class classes) {
        JSONObject object = JSONObject.fromObject(json);
        return JSONObject.toBean(object, classes);
    }

    public static Object toBean(Map jsonMap, Class classes) {
        JSONObject object = JSONObject.fromObject(jsonMap);
        return JSONObject.toBean(object, classes);
    }

    public static Object toBean(String json, Class classes, Map<String, Class> classMap) {
        JSONObject object = JSONObject.fromObject(json);
        return JSONObject.toBean(object, classes, classMap);
    }

    public static String listToJSON(List list) {
        JSONArray array = JSONArray.fromObject(list, jsonConfig);
        return array.toString();
    }

    public static String mapToJSON(Map map) {
        JSONObject object1 = JSONObject.fromObject(map, jsonConfig);
        return object1.toString();
    }

    public static Map<String, Object> objectToMap(Object object) {
        if (object == null) {
            return null;
        }
        JSONObject object1 = JSONObject.fromObject(object, jsonConfig);
        return object1;
    }

    public static Map<String, String> toMap(String json) {
        Map<String, String> map = new HashMap<>();
        JSONObject jsonObj = JSONObject.fromObject(json);
        Iterator<String> it = jsonObj.keys();
        while (it.hasNext()) {
            String key = it.next();
            map.put(key, (String) jsonObj.get(key));
        }
        return map;
    }

    public static String getAttributFromJson(String json, String attr) {
        JSONObject jsonObj = JSONObject.fromObject(json);
        return jsonObj.getString(attr);
    }

    public static List toList(String json, Class classes) {
        List list = new ArrayList();
        JSONArray jsonArray = JSONArray.fromObject(json);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObj = jsonArray.getJSONObject(i);
            list.add(JSONObject.toBean(jsonObj, classes));
        }
        return list;
    }

    public static List toList(String json, Class classes, Map<String, Class> classMap) {
        List list = new ArrayList();
        JSONArray jsonArray = JSONArray.fromObject(json);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObj = jsonArray.getJSONObject(i);
            list.add(JSONObject.toBean(jsonObj, classes, classMap));
        }
        return list;
    }


}
