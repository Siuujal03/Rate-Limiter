package com.example.ratelimiter.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.ratelimiter.Services.RateLimiterService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RateLimiterInterceptor implements HandlerInterceptor
{
    @Autowired
    RateLimiterService rateLimitService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
    {
        String ipAddress = request.getRemoteAddr();
        boolean isAccepted = rateLimitService.registerRequest(ipAddress);

        if(isAccepted)
        {
            response.setStatus(200);
            return true;
        }

        response.setStatus(429);
        return false;
    }
}
