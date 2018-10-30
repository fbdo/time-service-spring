package com.bmwgroup.timeservice.application;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimeService {

    @Autowired
    private TimeClient client;

    @HystrixCommand(fallbackMethod = "reliableNow")
    public DateTime now() {
        return client.now();
    }

    public DateTime reliableNow() {
        return DateTime.now();
    }
}
