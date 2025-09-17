package co.edu.unbosque.proyectocorte2back.configure;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.edu.unbosque.proyectocorte2back.entity.Administrador;
import co.edu.unbosque.proyectocorte2back.repository.AdminRepository;


@Configuration
public class LoadDatabase {
	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
	CommandLineRunner initDatabase(AdminRepository adminRepo) {

		return args -> {
			// Optional<User> found = userRepo.findByUsername(AESUtil.encrypt("admin"));
			Optional<Administrador> found = adminRepo.findByNombreCompleto("Juanito Perez");
			if (found.isPresent()) {
				log.info("Admin already exists,  skipping admin creating  ...");
			} else {
				// userRepo.save(new User(AESUtil.encrypt("admin"),
				// AESUtil.encrypt("1234567890")));
				adminRepo.save(new Administrador("admin", "1234567890","Juanito Perez","erikyulliam159@gmail.com","https://files.bo3.gg/uploads/image/25959/image/webp-4006a414667dbe11908cb635bf5cee0b.webp"));
				log.info("Preloading admin user");
			}
		};
	}

}
