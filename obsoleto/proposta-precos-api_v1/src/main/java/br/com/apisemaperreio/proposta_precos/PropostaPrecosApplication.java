package br.com.apisemaperreio.proposta_precos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class PropostaPrecosApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropostaPrecosApplication.class, args);
	}

}
