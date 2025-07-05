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
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

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
                // 1. 开启CORS并应用默认配置
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                // 2. 使用最新的推荐方式关闭CSRF保护
                .csrf(AbstractHttpConfigurer::disable)

                // 3. 配置URL的授权规则
                .authorizeHttpRequests(auth -> auth
                        // 明确放行所有的OPTIONS预检请求
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        // 公开访问: 登录/注册/静态资源
                        .requestMatchers("/api/user/login", "/api/user/register", "/uploads/**").permitAll()
                        // 公开访问: 论坛的GET请求
                        .requestMatchers(HttpMethod.GET, "/api/forum-post/**", "/api/forum-comment/**").permitAll()
                        // 公开访问: 领养信息的GET请求
                        .requestMatchers(HttpMethod.GET, "/api/adoption/infos/**").permitAll()
                        
                        // 登录用户访问: 救助模块的GET请求
                        .requestMatchers(HttpMethod.GET, "/api/rescue/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                        
                        // 用户权限: 发表帖子/评论/点赞 (所有POST请求)
                        .requestMatchers(HttpMethod.POST, "/api/forum-post/**", "/api/forum-comment/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")

                        // 管理员或用户权限: 修改/删除帖子/评论
                        .requestMatchers(HttpMethod.PUT, "/api/forum-post/**", "/api/forum-comment/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/forum-post/**", "/api/forum-comment/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")

                        // 新增：要求rescue模块的写操作需要认证
                        .requestMatchers(HttpMethod.POST, "/api/rescue/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/rescue/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/rescue/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")

                        // 新增：文件上传需要认证
                        .requestMatchers(HttpMethod.POST, "/api/files/upload").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")

                        // 新增：所有/api/admin/下的请求都需要ADMIN角色
                        .requestMatchers("/api/admin/**").hasAuthority("ROLE_ADMIN")

                        // 其他所有请求都需要认证 (例如获取个人信息等)
                        .anyRequest().authenticated()
                )

                // 4. 配置会话管理策略为STATELESS（无状态）
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        // 5. 将我们自定义的JWT过滤器添加到UsernamePasswordAuthenticationFilter之前
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * 配置CORS（跨源资源共享）
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // 允许的前端源地址
        configuration.setAllowedOrigins(List.of("http://localhost:5173"));
        // 允许的请求方法
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        // 允许的请求头
        configuration.setAllowedHeaders(List.of("*"));
        // 允许携带凭证
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 对所有URL应用这个CORS配置
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
} 