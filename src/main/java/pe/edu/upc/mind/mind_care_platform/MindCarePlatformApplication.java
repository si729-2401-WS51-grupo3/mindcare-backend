package pe.edu.upc.mind.mind_care_platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MindCarePlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(MindCarePlatformApplication.class, args);
	}

}
