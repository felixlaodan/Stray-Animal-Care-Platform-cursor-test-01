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
                        // 允许对/user/login, /user/register以及/uploads/**的匿名访问
                        .requestMatchers("/user/login", "/user/register", "/uploads/**").permitAll()
                        // 允许游客匿名访问帖子列表和帖子详情
                        .requestMatchers(HttpMethod.GET, "/forum-post/**", "/forum-comment/**").permitAll()
                        // 除上述路径外，所有其他请求都需要认证
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