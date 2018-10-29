package com.bmwgroup.timeservice.application;


import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimeService {

    @Autowired
    private TimeClient client;

    public DateTime now() {
        return client.now();
    }
}
