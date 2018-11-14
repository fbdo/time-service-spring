package com.bmwgroup.timeservice.adapters.worldtime;

import com.bmwgroup.timeservice.application.TimeClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Qualifier("reliable")
@Primary
public class ReliableTimeClient implements TimeClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReliableTimeClient.class);

    private DateTime now;

    @Autowired
    @Qualifier("worldtime")
    private TimeClient decorated;

    @Override
    @HystrixCommand(fallbackMethod = "reliableNow")
    public DateTime now() {
        now = decorated.now();
        return now;
    }

    public DateTime reliableNow() {
        LOGGER.debug("Access to time service failed, using reliable intra-VM source instead");

        if (now == null) {
            now = DateTime.now();
        }

        return now;
    }
}
