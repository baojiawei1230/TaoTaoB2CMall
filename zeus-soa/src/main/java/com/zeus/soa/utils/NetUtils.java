package com.zeus.soa.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.regex.Pattern;

/**
 * 网络工具类
 *
 * @Author Alex_Bao
 * @create 2017-06-14
 * create by IntelliJ IDEA
 */
public class NetUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(NetUtils.class);

    private static final Pattern IP_PATTERN = Pattern.compile("\\d{1,3}(\\.\\d{1,3}){3,5}$");

    /**
     * 获取本机IP
     *
     * @return
     */
    public static String getLocalIP() {
        InetAddress localAddress = getLocalAddress0();
        return localAddress == null ? "127.0.0.1" : localAddress.getHostAddress();
    }

    /**
     * 本机网络信息
     *
     * @return
     */
    private static InetAddress getLocalAddress0() {

        // host文件信息
        InetAddress localAddress = null;
        try {
            localAddress = InetAddress.getLocalHost();
            if (isValidAddress(localAddress)) {

                return localAddress;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

        //第一块网卡信息
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            if (interfaces != null) {
                while (interfaces.hasMoreElements()) {

                    NetworkInterface network = interfaces.nextElement();
                    Enumeration<InetAddress> addresses = network.getInetAddresses();
                    if (addresses != null) {
                        while (addresses.hasMoreElements()) {
                            InetAddress address = addresses.nextElement();
                            if (isValidAddress(address)) {
                                return address;
                            }
                        }
                    }
                }

            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

        return localAddress;
    }

    /**
     * 有效IP校验
     *
     * @param localAddress
     * @return
     */
    private static boolean isValidAddress(InetAddress localAddress) {
        //如果ip为空或者是外网地址.
        if (localAddress == null || localAddress.isLoopbackAddress()) {
            return false;
        }
        String ip = localAddress.getHostAddress();

        if (ip != null && !ip.startsWith("0.0") && !"127.0.0.1".equals(ip) && IP_PATTERN.matcher(ip).matches()) {
            return true;
        }
        return false;
    }

}