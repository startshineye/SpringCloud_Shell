package com.yxm.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 * C-Cros O-Origin R-Resource S-Sharing
 * 跨域资源共享
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter(){
        /**
         * 跨域配置注册到 source上去
         */
       final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
       final CorsConfiguration config = new CorsConfiguration();
       config.setAllowCredentials(true);
       config.setAllowedOrigins(Arrays.asList("*"));
       config.setAllowedHeaders(Arrays.asList("*"));
       config.setAllowedMethods(Arrays.asList("*"));
       config.setMaxAge(300l);//对于相同跨域请求不再进行检查
       source.registerCorsConfiguration("/**",config);

       return new CorsFilter(source);
    }
}
