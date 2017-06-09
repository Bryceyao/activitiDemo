package com.welab.common.util;


import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


/**
 * 对象工具，实体bean，map，jsonObject之间互转.
 * 
 * <pre>
 * 修改日期        修改人    修改原因
 * 2016-3-23    姚玉龙    新建
 * </pre>
 */
public class BeanUtil {
    private final static Logger log = LoggerFactory.getLogger(BeanUtil.class);
    
    /**
     * 将JSONObject的旧Key值替换为新Key值.
     *
     * @param obj
     * @param oldKeys
     * @param newKeys
     * @return
     *
     * <pre>
     * 修改日期        修改人    修改原因
     * 2015-11-5    姚玉龙    新建
     * </pre>
     */
    public static JSONObject replaceKey(JSONObject obj,String[] oldKeys,String[] newKeys){
        JSONObject rtn= new JSONObject();
        if(oldKeys.length==newKeys.length){
            for (int i = 0; i < oldKeys.length; i++) {
                rtn.put(newKeys[i], obj.get(oldKeys[i]));
            }
        }
        else{
            return null;
        }
        return rtn;
    }
    
    /**
     * 将map对象的旧Key值替换为新Key值.
     *
     * @param map
     * @param oldKeys
     * @param newKeys
     * @return
     *
     * <pre>
     * 修改日期        修改人    修改原因
     * 2015-11-5    姚玉龙    新建
     * </pre>
     */
    public static Map<String,Object> replaceKey(Map<String,Object> map,String[] oldKeys,String[] newKeys){
        Map<String,Object> rtn= new HashMap<String,Object>();
        if(!Objects.isNull(map)){
            if(oldKeys.length==newKeys.length){
                for (int i = 0; i < oldKeys.length; i++) {
                    rtn.put(newKeys[i], map.get(oldKeys[i]));
                }
            }
            else{
                log.error("转换key为中文失败，新旧key值长度不匹配！");
            }
        }
        return rtn;
    }
    
    /**
     * 将json对象转化为bean对象.
     *
     * @param <T>
     * @param clazz
     * @param obj
     * @return
     *
     * <pre>
     * 修改日期        修改人    修改原因
     * 2015-12-31    姚玉龙    新建
     * </pre>
     */
    public static <T> T convertJsonToBean(Class<T> clazz,JSONObject obj){
        if(obj==null){
            return null;
        }
        T bean = (T) JSONObject.toJavaObject(obj, clazz);
        return bean;
    }

    /**
     * 将json对象数组转化为bean对象.
     *
     * @param <T>
     * @param clazz
     * @param obj
     * @return
     *
     * <pre>
     * 修改日期        修改人    修改原因
     * 2015-12-31    姚玉龙    新建
     * </pre>
     */
    public static <T>List<T> convertJsonToBean(Class<T> clazz,JSONArray obj){
        List<T> beans= new ArrayList<T>();
        if(obj==null){
            return null;
        }
        for (int i = 0; i < obj.size(); i++) {
            beans.add((T) JSONObject.toJavaObject(obj.getJSONObject(i), clazz));
        }
        return beans;
    }

    
    
    /**
     * 
     * 此方法描述的是：将bean对象转化为json对象.
     * @param bean
     * @return
     * JSONObject
     * @exception
     * @since  1.0.0
     */
    public static JSONObject convertBeanToJson(Object bean){
        if(bean==null){
            return null;
        }
        JSONObject obj = (JSONObject) JSONObject.toJSON(bean);
        return obj;
    }
    
    /**
     * 将bean对象转化为jsonArray对象.
     *
     * @param List
     * @param JSONArray
     * @return
     *
     * <pre>
     * 修改日期        修改人    修改原因
     * 2015-12-31    姚玉龙    新建
     * </pre>
     */
    @SuppressWarnings("rawtypes")
    public static JSONArray convertListToJsonArr(List beans){
        JSONArray arr = new JSONArray();
        for (int i = 0; i < beans.size(); i++) {
            arr.add(convertBeanToJson(beans.get(i)));
        }
        return arr;
    }
    
    /**
     * JSONObject转Map.
     *
     * @param json
     * @return
     *
     * <pre>
     * 修改日期        修改人    修改原因
     * 2016-3-23    姚玉龙    新建
     * </pre>
     */
     public static Map<String, Object> convertJsonToMap(JSONObject json){
         Map<String,Object> columnValMap = new HashMap<String,Object>();
         Set<String> jsonKeys = json.keySet();
         for (String key : jsonKeys) {
             Object JsonValObj = json.get(key);
             columnValMap.put(key,  JsonValObj);
        }
         return columnValMap;
     }
     
     /**
      * Map转JSONObject.
      *
      * @param map
      * @return
      *
      * <pre>
      * 修改日期        修改人    修改原因
      * 2016-3-23    姚玉龙    新建
      * </pre>
      */
     public static JSONObject convertMapToJson(Map<String, Object> map){
         JSONObject json = new JSONObject();
         Set<String> mapKeys = map.keySet();
         for (String key : mapKeys) {
             Object mapValObj = map.get(key);
             json.put(key,  mapValObj);
        }
         return json;
     }
    
    
    /**
     * 将一个 Map 对象转化为一个 JavaBean
     * @param type 要转化的类型
     * @param map 包含属性值的 map
     * @return 转化出来的 JavaBean 对象
     * @throws IntrospectionException
     *             如果分析类属性失败
     * @throws IllegalAccessException
     *             如果实例化 JavaBean 失败
     * @throws InstantiationException
     *             如果实例化 JavaBean 失败
     * @throws InvocationTargetException
     *             如果调用属性的 setter 方法失败
     */
    @SuppressWarnings("rawtypes")
    public static Object convertMapToBean(Class type, Map map)
            throws IntrospectionException, IllegalAccessException,
            InstantiationException, InvocationTargetException {
        BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
        Object obj = type.newInstance(); // 创建 JavaBean 对象
 
        // 给 JavaBean 对象的属性赋值
        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
        for (int i = 0; i< propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
 
            if (map.containsKey(propertyName)) {
                // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
                Object value = map.get(propertyName);
 
                Object[] args = new Object[1];
                args[0] = value;
 
                descriptor.getWriteMethod().invoke(obj, args);
            }
        }
        return obj;
    }
 
    /**
     * 将一个 JavaBean 对象转化为一个  Map
     * @param bean 要转化的JavaBean 对象
     * @return 转化出来的  Map 对象
     * @throws IntrospectionException 如果分析类属性失败
     * @throws IllegalAccessException 如果实例化 JavaBean 失败
     * @throws InvocationTargetException 如果调用属性的 setter 方法失败
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static Map convertBeanToMap(Object bean)
            throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        Class type = bean.getClass();
        Map returnMap = new HashMap();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);
 
        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
        for (int i = 0; i< propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean, new Object[0]);
                if (result != null) {
                    returnMap.put(propertyName, result);
                } else {
                    returnMap.put(propertyName, "");
                }
            }
        }
        return returnMap;
    }
    
    
}