package com.yosef.moontrackJpa;

import com.yosef.moontrackJpa.config.RsaKeyConfigProperties;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyConfigProperties.class)
@SpringBootApplication
@Log
public class MoontrackJpaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MoontrackJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Moontrack Backend App started successfully");
	}
}
