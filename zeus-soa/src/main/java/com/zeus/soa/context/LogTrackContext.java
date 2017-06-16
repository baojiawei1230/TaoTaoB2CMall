package com.zeus.soa.context;

/**
 * 日志记录容器
 *
 * @Author Alex_Bao
 * @create 2017-06-14
 * create by IntelliJ IDEA
 */
public class LogTrackContext {

    private static final ThreadLocal<String> trackNumContext = new ThreadLocal<>();
    private static final ThreadLocal<String> parentAppNameContext = new ThreadLocal<>();

    public LogTrackContext(){}

    public static void setTrackNumber(String trackNum){
        trackNumContext.set(trackNum);
    }

    public static void setParentAppNumber(String parentAppName){
        parentAppNameContext.set(parentAppName);
    }

    public static String getParentAppName(){
        return parentAppNameContext.get();
    }

    /**
     * 移除操作
     */
    public static void remove(){
        try{
            trackNumContext.remove();
        }catch (Exception e){}

        try{
            parentAppNameContext.remove();
        }catch (Exception e2){}
    }

}
