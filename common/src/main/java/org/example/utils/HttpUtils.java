package org.example.utils;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

/**
 * HTTP 工具类
 * 处理 Http Request
 * */
public class HttpUtils {

    private HttpUtils() {}

    /**
     * 获取客户端IP
     * @return IP地址
     */
    public static String getIpAddr() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        return getIpAddr(request);
    }

    /**
     * 获取客户端IP
     * @param request 请求对象
     * @return IP地址
     */
    public static String getIpAddr(HttpServletRequest request) {
        if (request == null) {
            return "unknown";
        }
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : getMultistageReverseProxyIp(ip);
    }

    /**
     * 从多级反向代理中获得第一个非unknown IP地址
     *
     * @param ip 获得的IP地址，例如 "192.168.0.1, 192.168.0.1, 192.168.0.1"
     * @return 第一个非unknown IP地址
     */
    public static String getMultistageReverseProxyIp(String ip) {
        if (ip == null) {
            return "";
        }
        // 多级反向代理检测
        if (ip.contains(",")) {
            final String[] ips = ip.trim().split(",");
            for (String subIp : ips) {
                if (StringUtils.hasText(subIp) && !subIp.equalsIgnoreCase("unknown")) {
                    ip = subIp;
                    break;
                }
            }
        }
        return ip.substring(0, 255);
    }

    /**
     * 获取请求中的请求体参数
     * ⚠️ 注：request.getInputStream()、request.getReader() 只能被调用使用一次，否则报 IOException
     * @return String
     */
    public static String getRequestBody(ServletRequest request) {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = request.getReader();
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            System.out.println("getRequestBody 获取异常" + e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("getRequestBody 流关闭异常" + e);
                }
            }
        }
        return sb.toString();
    }
}
