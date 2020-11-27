package com.adri.solaceconsumer;

import com.adri.solaceconsumer.services.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SolaceconsumerApplication implements CommandLineRunner {

	@Autowired
	private ConsumerService consumer;

	public static void main(String[] args) {
		SpringApplication.run(SolaceconsumerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		consumer.retrieveMsg();
	}
}
