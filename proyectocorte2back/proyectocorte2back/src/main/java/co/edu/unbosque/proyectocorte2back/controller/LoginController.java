package co.edu.unbosque.proyectocorte2back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.proyectocorte2back.services.AdministradorService;
import co.edu.unbosque.proyectocorte2back.services.DocenteService;
import co.edu.unbosque.proyectocorte2back.services.EstudianteService;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = {"*"})
@Transactional
public class LoginController {
	
	
	@Autowired
	private EstudianteService estudianteService;
	
	@Autowired
	private AdministradorService administradorService;
	
	@Autowired
	private DocenteService docenteService;
	
	@PostMapping(path = "/checkloginEstudiante")
	ResponseEntity<String> checkLogEstudiante(@RequestParam String username, @RequestParam String password) {
		
		
		int status = estudianteService.validateCredentials(username,password);

		if (status == 0) {
			return new ResponseEntity<>("Credenciales correctas", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>("Username o contraseña incorrectos",
					HttpStatus.UNAUTHORIZED);
		}
	}
	
	@PostMapping(path = "/checkloginDocente")
	ResponseEntity<String> checkLogDocente(@RequestParam String username, @RequestParam String password) {
		
		
		int status = docenteService.validateCredentials(username,password);

		if (status == 0) {
			return new ResponseEntity<>("Credenciales correctas", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>("Username o contraseña incorrectos",
					HttpStatus.UNAUTHORIZED);
		}
	}
	
	@PostMapping(path = "/checkloginAdministrador")
	ResponseEntity<String> checkLogAdministrador(@RequestParam String username, @RequestParam String password) {
		
		
		int status = administradorService.validateCredentials(username,password);

		if (status == 0) {
			return new ResponseEntity<>("Credenciales correctas", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>("Username o contraseña incorrectos",
					HttpStatus.UNAUTHORIZED);
		}
	}

}
