package com.zeus.soa.filter;

import com.zeus.soa.context.DistributedContext;
import com.zeus.soa.context.LogTrackContext;
import com.zeus.soa.utils.NetUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * 线程过滤器
 *
 * @Author Alex_Bao
 * @create 2017-06-14
 * create by IntelliJ IDEA
 */
public class ThreadFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadFilter.class);

    private String[] excludeUrls;

    public ThreadFilter(){

    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.excludeUrls = filterConfig.getInitParameter("excludeUrls").split(",");
    }

    @Override
    /**
     * 拦截操作
     */
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse) res;
        String uri = request.getRequestURI().toLowerCase();
        String[] trackNum = this.excludeUrls;
        int contextStr = trackNum.length;

        for(int webIpObj = 0 ; webIpObj < contextStr ; webIpObj++){
            String broserIpObj = trackNum[webIpObj];
            if(uri.matches(broserIpObj.toLowerCase().trim())){
                chain.doFilter(req,res);
                return;
            }
        }

        try{
            String reqTrackNum = req.getParameter("trackNum");
            String parentAppName;
            if(reqTrackNum == null){
                reqTrackNum = UUID.randomUUID().toString();
                LogTrackContext.setTrackNumber(reqTrackNum);
            }else{
                LogTrackContext.setTrackNumber(reqTrackNum);
                parentAppName = req.getParameter("parentAppName");
                LogTrackContext.setParentAppNumber(parentAppName);
            }

            String distributedContext = req.getParameter("DistributedContext");
            if(StringUtils.isNotBlank(distributedContext)){
                DistributedContext.putToContext(DistributedContext.toObject(distributedContext));
            }

            Object webIpObj = DistributedContext.getContext().get("webIp");
            Object broserIpObj = DistributedContext.getContext().get("broserIp");
            if(webIpObj == null ||broserIpObj == null){
                String broserIp = getRemoteIpAddress(request);
                String webIp = NetUtils.getLocalIP();
                LOGGER.info("webIp : {} , broserIp : {} ",webIp,broserIp);
                DistributedContext.put("webIp",webIp);
                DistributedContext.put("broserIp",broserIp);
            }else{
                LOGGER.info("webIp : {} , broserIp : {} ",webIpObj,broserIpObj);
            }
            LOGGER.info(request.getRequestURL() + " ------------------ start");
            chain.doFilter(req,res);

        }finally {
            LOGGER.info(request.getRequestURL() + " ------------------  end");
            LogTrackContext.remove();
            DistributedContext.remove();
        }
    }

    /**
     * 获取真实的ip地址
     *
     * @param request
     * @return
     */
    private String getRemoteIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();

        }
        ip = transferIP(ip);

        return ip;
    }

    /**
     * 转换成本地ip
     *
     * @param ip
     * @return
     */
    private String transferIP(String ip) {
        if (ip != null && (ip.equals("127.0.0.1") || ip.startsWith("0:0:0:0") || ip.equals("localhost"))) {
            ip = NetUtils.getLocalIP();
        }

        return ip;
    }

    @Override
    public void destroy() {

    }
}
