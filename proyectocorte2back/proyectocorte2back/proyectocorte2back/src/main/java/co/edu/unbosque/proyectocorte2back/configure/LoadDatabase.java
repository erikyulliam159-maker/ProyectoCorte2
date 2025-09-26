/**
 * Clase LoadDatabase
 * Proyecto: proyectocorte2back
 * Paquete: co.edu.unbosque.proyectocorte2back.configure
 *
 * Descripción: Documentación pendiente.
 */
package co.edu.unbosque.proyectocorte2back.configure;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.edu.unbosque.proyectocorte2back.entity.Administrador;
import co.edu.unbosque.proyectocorte2back.repository.AdminRepository;


// TODO: Auto-generated Javadoc
/**
 * The Class LoadDatabase.
 */
@Configuration
public class LoadDatabase {
	
	/** The Constant log. */
	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	/**
	 * Inicializa el database.
	 *
	 * @param adminRepo the admin repo
	 * @return the command line runner
	 */
	@Bean
	CommandLineRunner initDatabase(AdminRepository adminRepo) {

		return args -> {
		
			Optional<Administrador> found = adminRepo.findByNombreCompleto("Juanito Perez");
			if (found.isPresent()) {
				log.info("Admin already exists,  skipping admin creating  ...");
			} else {
		
				adminRepo.save(new Administrador("admin", "1234567890","Juanito Perez","erikyulliam159@gmail.com","https://files.bo3.gg/uploads/image/25959/image/webp-4006a414667dbe11908cb635bf5cee0b.webp"));
				log.info("Preloading admin user");
			}
		};
	}

}
