package com.example.ratelimiter.Services;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.example.ratelimiter.Models.UserRequestInfo;

@Service
public class RateLimiterService 
{
    Map<String, UserRequestInfo> requests;

    public RateLimiterService()
    {
        this.requests = new ConcurrentHashMap<>();
    }

    
}
