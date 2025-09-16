package co.edu.unbosque.proyectocorte2back;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Proyectocorte2backApplication {

	public static void main(String[] args) {
		SpringApplication.run(Proyectocorte2backApplication.class, args);
	}
	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
}
