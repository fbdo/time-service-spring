package com.bmwgroup.timeservice.integration;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.joda.time.DateTime;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.hamcrest.Matchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {
		"worldtime.url=http://localhost:8080/api/ip",
})
public class TimeServiceIntegrationTests {

	@Autowired
	private MockMvc mvc;

	@Rule
	public WireMockRule wireMockRule = new WireMockRule(options().port(8080).notifier(new ConsoleNotifier(true)));


	@Test
	public void testGetNow() throws Exception {

		stubFor(WireMock.get(urlEqualTo("/api/ip"))
				.withHeader("Accept", containing("application/json"))
				.willReturn(aResponse()
						.withHeader("Content-Type", "application/json")
						.withBodyFile("wiremock/worldtime_by_ip_response.json")));



		mvc.perform(get("/now"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.seconds", is(45)))
				.andExpect(jsonPath("$.minutes", is(20)))
				.andExpect(jsonPath("$.hours", is(20)))
				.andExpect(jsonPath("$.day", is(4)));

	}

}
