package sprBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	
	public static void main (String args[]) throws Exception {
		SpringApplication.run(Application.class, args);
		System.out.println(".... Spring Boot Init ....");
		
	}
}
