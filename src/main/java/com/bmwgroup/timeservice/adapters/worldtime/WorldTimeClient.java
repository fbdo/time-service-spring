package com.bmwgroup.timeservice.adapters.worldtime;

import com.bmwgroup.timeservice.application.TimeClient;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
@Qualifier("worldtime")
public class WorldTimeClient implements TimeClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorldTimeClient.class);

    @Value("${worldtime.url}")
    private String wordTimeURL;

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public DateTime now() {
        LOGGER.debug("Retrieving time from WorldTime service");

        try {
            WorldTime wt = restTemplate.getForObject(wordTimeURL, WorldTime.class);

            return DateTime.parse(wt.getDatetime());
        } catch (RestClientException rce) {
            LOGGER.warn("Remote acess error:", rce);
            throw rce;
        }
    }
}
