package com.strayanimal.platform.core.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 安全工具类，用于获取当前登录用户信息
 */
public class SecurityUtil {

    /**
     * 获取当前登录用户的用户名
     * @return 当前用户名，如果未登录则返回 null
     */
    public static String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return userDetails.getUsername();
        }
        return null;
    }

    /**
     * 检查当前用户是否已认证
     * @return true 如果用户已认证，否则 false
     */
    public static boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated() 
               && !(authentication.getPrincipal() instanceof String); // 排除 "anonymousUser"
    }
} 