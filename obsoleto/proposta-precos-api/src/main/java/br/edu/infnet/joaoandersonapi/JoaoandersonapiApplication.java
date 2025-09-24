package br.edu.infnet.joaoandersonapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class JoaoandersonapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(JoaoandersonapiApplication.class, args);
	}

}
