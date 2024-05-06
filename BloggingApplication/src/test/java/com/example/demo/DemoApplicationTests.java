package com.example.demo;

import com.example.demo.services.serviceImpl.CustomUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
@Slf4j
class DemoApplicationTests {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	CustomUserDetailsService customUserDetailsService;

	@Test
	void contextLoads() {
		log.info(passwordEncoder.encode("myPassword"));
	}

}
