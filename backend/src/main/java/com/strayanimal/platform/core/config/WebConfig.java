package com.strayanimal.platform.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 全局CORS跨域配置
 *
 * @author dong
 * @since 2024-07-25
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry.addMapping("/**") // 对所有接口生效
                .allowedOrigins("http://localhost:5173") // 允许来自Vite开发服务器的访问 (Vue前端的地址)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 允许所有常规方法
                .allowedHeaders("*") // 允许所有请求头
                .allowCredentials(true); // 允许携带凭证（如Cookie）
    }
} 