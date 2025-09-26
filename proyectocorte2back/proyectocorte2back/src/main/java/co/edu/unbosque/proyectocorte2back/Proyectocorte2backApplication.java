/**
 * Clase Proyectocorte2backApplication
 * Proyecto: proyectocorte2back
 * Paquete: co.edu.unbosque.proyectocorte2back
 *
 * Descripción: Documentación pendiente.
 */
package co.edu.unbosque.proyectocorte2back;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

// TODO: Auto-generated Javadoc
/**
 * The Class Proyectocorte2backApplication.
 */
@SpringBootApplication
public class Proyectocorte2backApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(Proyectocorte2backApplication.class, args);
	}
	
	/**
	 * Gets the model mapper.
	 *
	 * @return the model mapper
	 */
	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
}
