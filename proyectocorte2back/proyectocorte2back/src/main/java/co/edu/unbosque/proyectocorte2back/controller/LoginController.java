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
	public ResponseEntity<String> checkLogEstudiante(@RequestParam String username, @RequestParam String password) {

	    if (username == null || username.trim().isEmpty()) {
	        return new ResponseEntity<>("El username no puede estar vacío", HttpStatus.BAD_REQUEST);
	    }
	    if (password == null || password.trim().isEmpty()) {
	        return new ResponseEntity<>("La contraseña no puede estar vacía", HttpStatus.BAD_REQUEST);
	    }

	    int status = estudianteService.validateCredentials(username, password);

	    if (status == 0) {
	        return new ResponseEntity<>("Credenciales correctas", HttpStatus.ACCEPTED);
	    } else if (status == 1) {
	        return new ResponseEntity<>("El usuario no existe", HttpStatus.NOT_FOUND);
	    } else if (status == 2) {
	        return new ResponseEntity<>("Contraseña incorrecta", HttpStatus.UNAUTHORIZED);
	    } else {
	        return new ResponseEntity<>("Error interno al validar credenciales", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	@PostMapping(path = "/checkloginDocente")
	public ResponseEntity<String> checkLogDocente(@RequestParam String username, @RequestParam String password) {

	    if (username == null || username.trim().isEmpty()) {
	        return new ResponseEntity<>("El username no puede estar vacío", HttpStatus.BAD_REQUEST);
	    }
	    if (password == null || password.trim().isEmpty()) {
	        return new ResponseEntity<>("La contraseña no puede estar vacía", HttpStatus.BAD_REQUEST);
	    }

	    int status = docenteService.validateCredentials(username, password);

	    if (status == 0) {
	        return new ResponseEntity<>("Credenciales correctas", HttpStatus.ACCEPTED);
	    } else if (status == 1) {
	        return new ResponseEntity<>("El usuario no existe", HttpStatus.NOT_FOUND);
	    } else if (status == 2) {
	        return new ResponseEntity<>("Contraseña incorrecta", HttpStatus.UNAUTHORIZED);
	    } else {
	        return new ResponseEntity<>("Error interno al validar credenciales", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	@PostMapping(path = "/checkloginAdministrador")
	public ResponseEntity<String> checkLogAdministrador(@RequestParam String username, @RequestParam String password) {

	    if (username == null || username.trim().isEmpty()) {
	        return new ResponseEntity<>("El username no puede estar vacío", HttpStatus.BAD_REQUEST);
	    }
	    if (password == null || password.trim().isEmpty()) {
	        return new ResponseEntity<>("La contraseña no puede estar vacía", HttpStatus.BAD_REQUEST);
	    }

	    int status = administradorService.validateCredentials(username, password);

	    if (status == 0) {
	        return new ResponseEntity<>("Credenciales correctas", HttpStatus.ACCEPTED);
	    } else if (status == 1) {
	        return new ResponseEntity<>("El usuario no existe", HttpStatus.NOT_FOUND);
	    } else if (status == 2) {
	        return new ResponseEntity<>("Contraseña incorrecta", HttpStatus.UNAUTHORIZED);
	    } else {
	        return new ResponseEntity<>("Error interno al validar credenciales", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}


}
