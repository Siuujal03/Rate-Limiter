package com.example.ratelimiter.Models;
import java.time.Duration;
import java.util.Queue;
import java.time.Instant;
import java.util.ArrayDeque;

public class UserRequestInfo 
{
    private final Queue<Instant> timestamps;

    public UserRequestInfo()
    {
        this.timestamps = new ArrayDeque<>();
    }

    private void cleanup(Instant currentTime, Duration window)
    {
        Instant startTime = currentTime.minus(window);

        while(!timestamps.isEmpty())
        {
            Instant timestamp = timestamps.peek();
            
            if(timestamp.isBefore(startTime))
            {
                timestamps.poll();
            }
            else
            {
                return;
            }
        }
    }

    public synchronized boolean tryAndRequest(Instant currentTime, int limit, Duration window)
    {
        if(!timestamps.isEmpty())
        {
            cleanup(currentTime, window);
        }

        if(timestamps.size() >= limit)
        {
            return false;
        }

        timestamps.offer(currentTime);
        return true;

    }
}
