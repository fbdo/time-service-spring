package com.bmwgroup.timeservice.ports.restapi;

import com.bmwgroup.timeservice.application.TimeService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimeController {

    @Autowired
    private TimeService service;

    @RequestMapping("/now")
    public TimeResource now() {

        DateTime now = service.now();
        TimeResource time = new TimeResource(now.getSecondOfMinute(), now.getMinuteOfHour(), now.getHourOfDay(), now.getDayOfMonth(), now.getMonthOfYear(), now.getYear());
        return time;
    }
}
