package co.edu.unbosque.proyectocorte2back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import co.edu.unbosque.proyectocorte2back.services.AdministradorService;
import co.edu.unbosque.proyectocorte2back.services.DocenteService;
import co.edu.unbosque.proyectocorte2back.services.EstudianteService;
import co.edu.unbosque.proyectocorte2back.dto.AdministradorDTO;
import co.edu.unbosque.proyectocorte2back.dto.DocenteDTO;
import co.edu.unbosque.proyectocorte2back.dto.EstudianteDTO;

@RestController 
@CrossOrigin(origins = { "*" }) 
@RequestMapping(path = { "/registro" })
public class RegistroController {
 
	@Autowired
	private EstudianteService estudianteService;
	@Autowired 
	private DocenteService docenteService;
	@Autowired
	private AdministradorService administradorService;
	
	@PostMapping(path="/crearEstudiante")
	public ResponseEntity<String> crearEstudiante(@RequestParam String username ,String password ,String nombreCompleto,String email,String fotoPerfil ){
		EstudianteDTO newUser=new EstudianteDTO(username,password,nombreCompleto,email,fotoPerfil);
		int status=estudianteService.create(newUser);
		if (status==0) {
			return new ResponseEntity<>("Estudiante creado con exito",HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>("Error al crear el Estudiante",HttpStatus.NOT_ACCEPTABLE);
		}
		
	}
	
	@PostMapping(path="/crearDocente")
	public ResponseEntity<String> crearDocente(@RequestParam String username ,String password ,String nombreCompleto,String email,String fotoPerfil ){
		DocenteDTO newUser=new DocenteDTO(username,password,nombreCompleto,email,fotoPerfil);
		int status=docenteService.create(newUser);
		if (status==0) {
			return new ResponseEntity<>("Docente creado con exito",HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>("Error al crear el Docente",HttpStatus.NOT_ACCEPTABLE);
		}
		
	}
	
	@PostMapping(path="/crearAdministrador")
	public ResponseEntity<String> crearAdministrador(@RequestParam String username ,String password ,String nombreCompleto,String email,String fotoPerfil ){
		AdministradorDTO newUser=new AdministradorDTO(username,password,nombreCompleto,email,fotoPerfil);
		int status=administradorService.create(newUser);
		if (status==0) {
			return new ResponseEntity<>("Administrador creado con exito",HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>("Error al crear el Administrador",HttpStatus.NOT_ACCEPTABLE);
		}
		
	}
	
	
}
