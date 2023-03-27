package kr.co.an.jsd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JsdApplication {

	public static void main(String[] args) {
		SpringApplication.run(JsdApplication.class, args);
	}

}
