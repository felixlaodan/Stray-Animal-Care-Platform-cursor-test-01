package com.strayanimal.platform.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
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

    /**
     * 配置静态资源映射
     * @param registry 资源处理器注册表
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 获取项目根路径
        String projectRoot = System.getProperty("user.dir");
        // 配置/uploads/**的URL路径，使其映射到项目根目录下的uploads文件夹
        // file: 前缀表示这是一个本地文件系统路径
        String uploadPath = "file:" + projectRoot + "/uploads/";

        registry.addResourceHandler("/uploads/**")
                .addResourceLocations(uploadPath);
    }
} 