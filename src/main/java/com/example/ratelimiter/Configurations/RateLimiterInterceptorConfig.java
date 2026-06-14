package com.example.ratelimiter.Configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.ratelimiter.Interceptors.RateLimiterInterceptor;

@Configuration
public class RateLimiterInterceptorConfig implements WebMvcConfigurer
{
    @Autowired
    private RateLimiterInterceptor rateLimiterReceptor;

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry)
    {
        interceptorRegistry.addInterceptor(rateLimiterReceptor).addPathPatterns("/request");
    }
}
