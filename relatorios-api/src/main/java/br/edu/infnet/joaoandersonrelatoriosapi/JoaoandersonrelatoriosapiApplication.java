package br.edu.infnet.joaoandersonrelatoriosapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class JoaoandersonrelatoriosapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(JoaoandersonrelatoriosapiApplication.class, args);
	}

}
