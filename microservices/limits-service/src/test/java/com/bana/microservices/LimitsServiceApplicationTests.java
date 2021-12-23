package com.bana.microservices;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest( properties = { "spring.cloud.config.enabled=false" })
class LimitsServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
