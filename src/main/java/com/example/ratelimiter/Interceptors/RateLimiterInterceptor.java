package com.example.ratelimiter.Interceptors;

import java.io.PrintWriter;

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
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        String ipAddress = request.getRemoteAddr();
        boolean isAccepted = rateLimitService.registerRequest(ipAddress);

        if(isAccepted)
        {
            return true;
        }

        response.setStatus(429);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.write("{\"error\" : \"Too Many Requests\"}");
        return false;
    }
}
