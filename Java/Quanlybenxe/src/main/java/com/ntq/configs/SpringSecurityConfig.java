    package com.ntq.configs;

    import com.cloudinary.Cloudinary;
    import com.cloudinary.utils.ObjectUtils;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.ComponentScan;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.core.annotation.Order;
    import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;

    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
    import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

    import org.springframework.security.core.userdetails.UserDetailsService;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
    import org.springframework.transaction.annotation.EnableTransactionManagement;
    import org.springframework.web.cors.CorsConfiguration;
    import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
    import org.springframework.web.filter.CorsFilter;

    @Configuration
    @EnableWebSecurity
    @EnableTransactionManagement
    @ComponentScan(basePackages = {
        "com.ntq.controllers",
        "com.ntq.repositories",
        "com.ntq.services"
    })
    @Order(2)
    public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        private UserDetailsService userDetailsService;

        @Bean
        public BCryptPasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public Cloudinary cloudinary() {
            Cloudinary cloudinary
                    = new Cloudinary(ObjectUtils.asMap(
                            "cloud_name", "dnqxawhjq",
                            "api_key", "747232433638318",
                            "api_secret", "uyixwZrKZNwZqiGHdnR1RmMcJcw",
                            "secure", true));
            return cloudinary;
        }

    
        @Bean
        public BasicAuthenticationEntryPoint swaggerAuthenticationEntryPoint() {
            BasicAuthenticationEntryPoint entryPoint = new BasicAuthenticationEntryPoint();
            entryPoint.setRealmName("Swagger Realm");
            return entryPoint;
        }

        @Bean
        public CorsFilter corsFilter() {
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            CorsConfiguration config = new CorsConfiguration();
            config.setAllowCredentials(true);
            config.addAllowedOriginPattern("*");
            config.addAllowedHeader("*");
            config.addAllowedMethod("*");
            source.registerCorsConfiguration("/**", config);
            return new CorsFilter(source);
        }

        //Cấu hình UserDetailsService và mã hóa mật khẩu
        @Override
        protected void configure(AuthenticationManagerBuilder auth)
                throws Exception {
            auth.userDetailsService(userDetailsService)
                    .passwordEncoder(passwordEncoder());
        }

        //Cấu hình đăng nhập và đăng xuất, xử lý lỗi và bảo mật cơ bản
        @Override
        protected void configure(HttpSecurity http)
                throws Exception {
            http.formLogin().usernameParameter("username").passwordParameter("password");
    
            http.formLogin().defaultSuccessUrl("/")
                    .failureUrl("/login?error");
            http.logout().logoutSuccessUrl("/login");
    
            http.exceptionHandling().accessDeniedPage("/login?accessDenied");
            http.authorizeRequests().antMatchers("/").access("hasRole('ROLE_ADMIN')");
            http.csrf().disable();
        }
        

    }
