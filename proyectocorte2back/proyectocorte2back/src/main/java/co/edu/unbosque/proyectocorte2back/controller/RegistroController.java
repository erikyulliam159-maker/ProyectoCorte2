package co.edu.unbosque.proyectocorte2back.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import co.edu.unbosque.proyectocorte2back.services.DocenteService;
import co.edu.unbosque.proyectocorte2back.services.EstudianteService;
import co.edu.unbosque.proyectocorte2back.util.ArchivoUtil;

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
	private ArchivoUtil archivoUtil;

	@PostMapping(path = "/estudiante/crear")
	public ResponseEntity<String> createEstudiante(@RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("nombreCompleto") String nombreCompleto,
			@RequestParam("email") String email, @RequestParam(value = "file", required = false) MultipartFile file) {
		try {
			// Validaciones simples antes de crear
			if (username == null || username.trim().isEmpty()) {
				return new ResponseEntity<>("El username no puede estar vacío", HttpStatus.BAD_REQUEST);
			}
			if (password == null || password.trim().isEmpty()) {
				return new ResponseEntity<>("La contraseña no puede estar vacía", HttpStatus.BAD_REQUEST);
			}
			if (nombreCompleto == null || nombreCompleto.trim().isEmpty()) {
				return new ResponseEntity<>("El nombre completo es obligatorio", HttpStatus.BAD_REQUEST);
			}
			if (email == null || !email.contains("@")) {
				return new ResponseEntity<>("El correo electrónico no es válido", HttpStatus.BAD_REQUEST);
			}

			String fotoPerfil = null;
			if (file != null && !file.isEmpty()) {
				fotoPerfil = archivoUtil.guardarArchivoImagen(file);
			}

			EstudianteDTO estudianteDTO = new EstudianteDTO(username, password, nombreCompleto, email, fotoPerfil);
			int status = estudianteService.create(estudianteDTO);

			if (status == 0) {
				return new ResponseEntity<>("Estudiante creado exitosamente", HttpStatus.CREATED);
			} else if (status == 1) {
				return new ResponseEntity<>("El username ya está en uso", HttpStatus.CONFLICT);
			} else if (status == 2) {
				return new ResponseEntity<>("El correo ya está registrado", HttpStatus.CONFLICT);
			} else {
				return new ResponseEntity<>("Error desconocido al crear el estudiante",
						HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} catch (IOException e) {
			return new ResponseEntity<>("Error al subir la foto", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<>("Error inesperado: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(path = "/docente/crear")
	public ResponseEntity<String> createDocente(@RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("nombreCompleto") String nombreCompleto,
			@RequestParam("email") String email, @RequestParam(value = "file", required = false) MultipartFile file) {
		try {
			if (username == null || username.trim().isEmpty()) {
				return new ResponseEntity<>("El username no puede estar vacío", HttpStatus.BAD_REQUEST);
			}
			if (password == null || password.trim().isEmpty()) {
				return new ResponseEntity<>("La contraseña no puede estar vacía", HttpStatus.BAD_REQUEST);
			}
			if (nombreCompleto == null || nombreCompleto.trim().isEmpty()) {
				return new ResponseEntity<>("El nombre completo es obligatorio", HttpStatus.BAD_REQUEST);
			}
			if (email == null || !email.contains("@")) {
				return new ResponseEntity<>("El correo electrónico no es válido", HttpStatus.BAD_REQUEST);
			}

			String fotoPerfil = null;
			if (file != null && !file.isEmpty()) {
				fotoPerfil = archivoUtil.guardarArchivoImagen(file);
			}

			DocenteDTO docenteDTO = new DocenteDTO(username, password, nombreCompleto, email, fotoPerfil);
			int status = docenteService.create(docenteDTO);

			if (status == 0) {
				return new ResponseEntity<>("Docente creado exitosamente", HttpStatus.CREATED);
			} else if (status == 1) {
				return new ResponseEntity<>("El username ya está en uso", HttpStatus.CONFLICT);
			} else if (status == 2) {
				return new ResponseEntity<>("El correo ya está registrado", HttpStatus.CONFLICT);
			} else {
				return new ResponseEntity<>("Error desconocido al crear el docente", HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} catch (IOException e) {
			return new ResponseEntity<>("Error al subir la foto", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<>("Error inesperado: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
