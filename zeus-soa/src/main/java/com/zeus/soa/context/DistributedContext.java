package com.zeus.soa.context;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Alex_Bao
 * @create 2017-06-14
 * create by IntelliJ IDEA
 */
public class DistributedContext {

    private static final ThreadLocal<Map<String,Serializable>>  context = new ThreadLocal<>();
    private static final String CONTEXT_KEY_DBWRITERECORD = "CONTEXT_KEY_DBWRITERECORD";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void putToContext(Map<String,Serializable> map){
        if(map == null || map.isEmpty()){
            return ;
        }
        getContext().putAll(map);
    }

    public static Map<String,Serializable> getContext() {
        if(context.get() != null){
            init();

        }
        return null;
    }

    private static void init() {
        HashMap<String,Serializable> map = new HashMap<>();
        //map.put(CONTEXT_KEY_DBWRITERECORD,new DBWriteRecord());
        context.set(map);
    }


    public static Map<String,Serializable> toObject(String jsonString){
        if(jsonString == null || jsonString.trim().length()==0){
            return new HashMap<>();
        }

        try{
            Map<String, Class> childMap=new HashMap<String, Class>();
            //childMap.put(DistributedContextConstant.CONTEXT_KEY_DBWRITERECORD, DBWriteRecord.class);
            //childMap.put("writeRecords", TableWriteRecord.class);
            Map<String,Serializable> pojo = (Map<String,Serializable>)objectMapper.readValue(jsonString ,Map.class);
            return pojo;
        }catch(Exception e){
            return new HashMap<String,Serializable>();
        }
    }

    public static void remove(){
        context.remove();
    }

    public static Serializable get(String key) {
        return getContext().get(key);
    }

    public static Serializable remove(String key) {
        return getContext().remove(key);
    }

    /**
     * 设置map集合
     *
     * @param key
     * @param value
     */
    public static void put(String key, String value) {
        getContext().put(key,value);
    }
}
