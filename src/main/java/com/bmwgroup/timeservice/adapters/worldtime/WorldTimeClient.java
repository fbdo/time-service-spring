package com.bmwgroup.timeservice.adapters.worldtime;

import com.bmwgroup.timeservice.application.TimeClient;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WorldTimeClient implements TimeClient {

    @Value("${worldtime.url}")
    private String wordTimeURL;

    @Override
    public DateTime now() {
        RestTemplate restTemplate = new RestTemplate();

        WorldTime wt = restTemplate.getForObject(wordTimeURL, WorldTime.class);

        return DateTime.parse(wt.getDatetime());
    }
}
