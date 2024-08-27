package com.ntq.configs;

import com.ntq.filters.CustomAccessDeniedHandler;
import com.ntq.filters.JwtAuthenticationTokenFilter;
import com.ntq.filters.RestAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.ntq.controllers",
    "com.ntq.repositories",
    "com.ntq.services",
    "com.ntq.components"
})
@Order(1)
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {

    //Đăng ký bộ lọc JWT để xử lý xác thực
    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() throws Exception {
        JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter = new JwtAuthenticationTokenFilter();
        jwtAuthenticationTokenFilter.setAuthenticationManager(authenticationManager());
        return jwtAuthenticationTokenFilter;
    }

    //Đăng ký xử lý lỗi xác thực đầu vào
    @Bean
    public RestAuthenticationEntryPoint restServicesEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    //Đăng ký xử lý lỗi truy cập bị từ chối
    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    //Đăng ký AuthenticationManager
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 1. Tắt bảo vệ CSRF cho các endpoint API
        http.csrf().ignoringAntMatchers("/api/**");

        // 2. Cho phép truy cập công khai vào các endpoint cụ thể
        http.authorizeRequests().antMatchers("/api/login/").permitAll();
        http.authorizeRequests().antMatchers("/api/users/").permitAll();

        // 3. Cho phép truy cập công khai vào tất cả các yêu cầu GET tới /api/**
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/**").permitAll();

        // 4. Yêu cầu xác thực cho các yêu cầu POST tới /api/**
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/**").authenticated();

        // 5. Yêu cầu xác thực cho các yêu cầu DELETE tới /api/**
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/**").authenticated();

        // 6. Cấu hình quản lý session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // 7. Thêm bộ lọc JWT trước UsernamePasswordAuthenticationFilter
        http.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        // 8. Xử lý các trường hợp truy cập bị từ chối
        http.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());

        // 9. Cấu hình để sử dụng HTTP Basic Authentication cho các endpoint API
        http.antMatcher("/api/**").httpBasic().authenticationEntryPoint(restServicesEntryPoint());
    }

}
