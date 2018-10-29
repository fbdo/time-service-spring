package com.bmwgroup.timeservice.adapters.worldtime;

import com.bmwgroup.timeservice.application.TimeClient;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WorldTimeClient implements TimeClient {
    @Override
    public DateTime now() {
        RestTemplate restTemplate = new RestTemplate();

        WorldTime wt = restTemplate.getForObject("http://worldtimeapi.org/api/ip", WorldTime.class);

        return DateTime.parse(wt.getDatetime());
    }
}
