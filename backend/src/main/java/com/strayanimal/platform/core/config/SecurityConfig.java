package com.strayanimal.platform.core.config;

import com.strayanimal.platform.core.jwt.JwtRequestFilter;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring Security配置类
 *
 * @author dong
 * @since 2024-07-25
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Resource
    private JwtRequestFilter jwtRequestFilter;

    /**
     * 配置密码编码器，用于密码的加密和验证
     * 我们选用BCrypt强哈希函数
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置AuthenticationManager，用于后续的认证流程
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * 配置核心的安全过滤器链
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. 关闭CSRF保护，因为我们使用JWT，不需要传统的session
                .csrf(csrf -> csrf.disable())

                // 2. 配置URL的授权规则
                .authorizeHttpRequests(auth -> auth
                        // 公开访问: 登录/注册/静态资源/GET请求帖子和评论
                        .requestMatchers("/user/login", "/user/register", "/uploads/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/forum-post/**", "/forum-comment/**").permitAll()
                        
                        // 用户权限: 发表帖子/评论/点赞 (所有POST请求)
                        .requestMatchers(HttpMethod.POST, "/forum-post/**", "/forum-comment/**").hasAnyRole("USER", "ADMIN")

                        // 管理员或用户权限: 修改/删除帖子/评论
                        .requestMatchers(HttpMethod.PUT, "/forum-post/**", "/forum-comment/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/forum-post/**", "/forum-comment/**").hasAnyRole("USER", "ADMIN")

                        // 其他所有请求都需要认证 (例如获取个人信息等)
                        .anyRequest().authenticated()
                )

                // 3. 配置会话管理策略为STATELESS（无状态）
                // Spring Security不会创建或使用任何HttpSession
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        // 4. 将我们自定义的JWT过滤器添加到UsernamePasswordAuthenticationFilter之前
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
} 