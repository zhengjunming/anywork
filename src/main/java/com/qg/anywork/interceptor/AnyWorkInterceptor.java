package com.qg.anywork.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Create by ming on 18-8-14 下午1:48
 *
 * @author ming
 * I'm the one to ignite the darkened skies.
 */
//@Configuration
public class AnyWorkInterceptor extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AnyWorkLoginHandlerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/anywork/utils/valcode", "/anywork/user/forget/new",
                        "/anywork/utils/reset", "/anywork/user/forget",
                        "/anywork/user/login", "/anywork/user/register",
                        "/anywork/html/**",
                        "/anywork/static/**", "/anywork/picture/**", "/anywork/excel/**",
                        "/anywork/favicon.ico"
                );
        super.addInterceptors(registry);
    }
}
