package co.edu.unbosque.proyectocorte2back.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import co.edu.unbosque.proyectocorte2back.dto.DocenteDTO;
import co.edu.unbosque.proyectocorte2back.dto.EstudianteDTO;
import co.edu.unbosque.proyectocorte2back.dto.EventoDTO;
import co.edu.unbosque.proyectocorte2back.dto.LinkValiosoDTO;
	
import co.edu.unbosque.proyectocorte2back.services.DocenteService;
import co.edu.unbosque.proyectocorte2back.services.EstudianteService;
import co.edu.unbosque.proyectocorte2back.services.EventoService;
import co.edu.unbosque.proyectocorte2back.services.LinkValiosoService;
import co.edu.unbosque.proyectocorte2back.util.ArchivoUtil;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
@Transactional
public class AdminController {


	@Autowired
	private EventoService eventoService;

	@Autowired
	private LinkValiosoService linkValiosoService;

	@Autowired
	private DocenteService docenteService;

	@Autowired
	private EstudianteService estudianteService;
	@Autowired
	private ArchivoUtil archivoUtil;

	public AdminController() {
	}


//----------------------------------Evento----------------------------------------------------//

	@PostMapping(path = "/evento/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createEvento(@RequestBody EventoDTO eventoDTO) {
	    try {
	       
	        if (eventoDTO.getTitulo() == null || eventoDTO.getTitulo().trim().isEmpty()) {
	            return new ResponseEntity<>("El título del evento es obligatorio", HttpStatus.BAD_REQUEST);
	        }else if (eventoDTO.getFecha() == null) {
	            return new ResponseEntity<>("La fecha del evento es obligatoria", HttpStatus.BAD_REQUEST);
	        }

	        int status = eventoService.create(eventoDTO);
	        if (status == 0) {
	            return new ResponseEntity<>("Evento creado exitosamente", HttpStatus.CREATED);
	        } else if (status == 1) {
	            return new ResponseEntity<>("Ya existe un evento con ese título", HttpStatus.CONFLICT);
	        } else {
	            return new ResponseEntity<>("Error desconocido al crear el evento", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    } catch (IllegalArgumentException e) {
	        return new ResponseEntity<>("Error de validación: " + e.getMessage(), HttpStatus.BAD_REQUEST);
	    } catch (Exception e) {
	        return new ResponseEntity<>("Error inesperado en el servidor: " + e.getMessage(),
	                HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	@GetMapping("/evento/getall")
	public ResponseEntity<List<EventoDTO>> getAllEventos() {
		List<EventoDTO> eventos = eventoService.getAll();
		if (eventos.isEmpty()) {
			return new ResponseEntity<>(eventos, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(eventos, HttpStatus.OK);
		}
	}

	@GetMapping("/evento/getbyid/{id}")
	public ResponseEntity<EventoDTO> getEventoById(@PathVariable Long id) {
		EventoDTO evento = eventoService.getById(id);
		if (evento != null) {
			return new ResponseEntity<>(evento, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(path = "/evento/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateEvento(@RequestParam Long id, @RequestBody EventoDTO eventoDTO) {
		int status = eventoService.updateById(id, eventoDTO);
		if (status == 0) {
			return new ResponseEntity<>("Evento actualizado exitosamente", HttpStatus.ACCEPTED);
		} else if (status == 1) {
			return new ResponseEntity<>("El nuevo título ya está en uso", HttpStatus.IM_USED);
		} else if (status == 2) {
			return new ResponseEntity<>("Evento no encontrado", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>("Error al actualizar el evento", HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/evento/deletebyid/{id}")
	public ResponseEntity<String> deleteEventoById(@PathVariable Long id) {
		int status = eventoService.deleteById(id);
		if (status == 0) {
			return new ResponseEntity<>("Evento eliminado exitosamente", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>("Error al eliminar el evento no se encontro", HttpStatus.NOT_FOUND);
		}
	}
//---------------------------------------------------------------LinkValioso-------------------------------------------------------------------//

	@PostMapping(path = "/linkvalioso/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createLinkValioso(@RequestBody LinkValiosoDTO linkDTO) {
	    try {
	       
	        if (linkDTO.getNombre() == null || linkDTO.getNombre().trim().isEmpty()) {
	            return new ResponseEntity<>("El título es obligatorio", HttpStatus.BAD_REQUEST);
	        }
	        if (linkDTO.getUrl() == null || linkDTO.getUrl().trim().isEmpty()) {
	            return new ResponseEntity<>("La URL es obligatoria", HttpStatus.BAD_REQUEST);
	        }
	        if (!linkDTO.getUrl().startsWith("http")) {
	            return new ResponseEntity<>("La URL debe comenzar con http o https", HttpStatus.BAD_REQUEST);
	        }

	        int status = linkValiosoService.create(linkDTO);
	        if (status == 0) {
	            return new ResponseEntity<>("Link valioso creado exitosamente", HttpStatus.CREATED);
	        } else if (status == 1) {
	            return new ResponseEntity<>("Ya existe un link con ese título", HttpStatus.CONFLICT);
	        } else {
	            return new ResponseEntity<>("Error desconocido al crear el link valioso", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    } catch (Exception e) {
	        return new ResponseEntity<>("Error inesperado: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	@GetMapping("/linkvalioso/getall")
	public ResponseEntity<List<LinkValiosoDTO>> getAllLinksValiosos() {
	    try {
	        List<LinkValiosoDTO> links = linkValiosoService.getAll();
	        if (links.isEmpty()) {
	            return new ResponseEntity<>(links, HttpStatus.NO_CONTENT);
	        } else {
	            return new ResponseEntity<>(links, HttpStatus.OK);
	        }
	    } catch (Exception e) {
	        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	@GetMapping("/linkvalioso/getbyid/{id}")
	public ResponseEntity<LinkValiosoDTO> getLinkValiosoById(@PathVariable Long id) {
	    try {
	        if (id <= 0) {
	            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	        }
	        LinkValiosoDTO link = linkValiosoService.getById(id);
	        if (link != null) {
	            return new ResponseEntity<>(link, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	        }
	    } catch (Exception e) {
	        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	@PutMapping(path = "/linkvalioso/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateLinkValioso(@RequestParam Long id, @RequestBody LinkValiosoDTO linkDTO) {
	    try {
	        if (id <= 0) {
	            return new ResponseEntity<>("ID inválido", HttpStatus.BAD_REQUEST);
	        }
	        if (linkDTO.getNombre() == null || linkDTO.getNombre().trim().isEmpty()) {
	            return new ResponseEntity<>("El título no puede estar vacío", HttpStatus.BAD_REQUEST);
	        }

	        int status = linkValiosoService.updateById(id, linkDTO);
	        if (status == 0) {
	            return new ResponseEntity<>("Link valioso actualizado exitosamente", HttpStatus.OK);
	        } else if (status == 1) {
	            return new ResponseEntity<>("Ya existe un link con ese título", HttpStatus.CONFLICT);
	        } else if (status == 2) {
	            return new ResponseEntity<>("Link valioso no encontrado", HttpStatus.NOT_FOUND);
	        } else {
	            return new ResponseEntity<>("Error desconocido al actualizar el link valioso", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    } catch (Exception e) {
	        return new ResponseEntity<>("Error inesperado: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	@DeleteMapping("/linkvalioso/deletebyid/{id}")
	public ResponseEntity<String> deleteLinkValiosoById(@PathVariable Long id) {
	    try {
	        if (id <= 0) {
	            return new ResponseEntity<>("ID inválido", HttpStatus.BAD_REQUEST);
	        }
	        int status = linkValiosoService.deleteById(id);
	        if (status == 0) {
	            return new ResponseEntity<>("Link valioso eliminado exitosamente", HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>("Link valioso no encontrado", HttpStatus.NOT_FOUND);
	        }
	    } catch (Exception e) {
	        return new ResponseEntity<>("Error inesperado: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	@DeleteMapping("/linkvalioso/deletebyname")
	public ResponseEntity<String> deleteLinkValiosoByName(@RequestParam String nombre) {
	    try {
	        if (nombre == null || nombre.trim().isEmpty()) {
	            return new ResponseEntity<>("El nombre no puede estar vacío", HttpStatus.BAD_REQUEST);
	        }
	        int status = linkValiosoService.deleteByTitulo(nombre);
	        if (status == 0) {
	            return new ResponseEntity<>("Link valioso eliminado exitosamente", HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>("Link valioso no encontrado", HttpStatus.NOT_FOUND);
	        }
	    } catch (Exception e) {
	        return new ResponseEntity<>("Error inesperado: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	// ----------------------------------Docente----------------------------------------------------//

	@GetMapping("/docente/getall")
	public ResponseEntity<List<DocenteDTO>> getAllDocentes() {
	    try {
	        List<DocenteDTO> docentes = docenteService.getAll();
	        if (docentes.isEmpty()) {
	            return new ResponseEntity<>(docentes, HttpStatus.NO_CONTENT);
	        } else {
	            return new ResponseEntity<>(docentes, HttpStatus.OK);
	        }
	    } catch (Exception e) {
	        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	@GetMapping("/docente/getbyid/{id}")
	public ResponseEntity<DocenteDTO> getDocenteById(@PathVariable Long id) {
	    try {
	        if (id == null || id <= 0) {
	            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	        }
	        DocenteDTO docente = docenteService.getById(id);
	        if (docente != null) {
	            return new ResponseEntity<>(docente, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	        }
	    } catch (Exception e) {
	        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	@PutMapping(path = "/docente/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> updateDocente(
	    @RequestParam("id") Long id,
	    @RequestParam("username") String username,
	    @RequestParam("password") String password,
	    @RequestParam("nombreCompleto") String nombreCompleto,
	    @RequestParam("email") String email,
	    @RequestParam(value = "file", required = false) MultipartFile file
	) {
	    try {
	        if (id == null || id <= 0) {
	            return new ResponseEntity<>("ID inválido", HttpStatus.BAD_REQUEST);
	        }
	        if (username == null || username.trim().isEmpty()) {
	            return new ResponseEntity<>("El username es obligatorio", HttpStatus.BAD_REQUEST);
	        }
	        if (password == null || password.trim().isEmpty()) {
	            return new ResponseEntity<>("La contraseña es obligatoria", HttpStatus.BAD_REQUEST);
	        }
	        if (nombreCompleto == null || nombreCompleto.trim().isEmpty()) {
	            return new ResponseEntity<>("El nombre completo es obligatorio", HttpStatus.BAD_REQUEST);
	        }
	        if (email == null || !email.contains("@")) {
	            return new ResponseEntity<>("El email no es válido", HttpStatus.BAD_REQUEST);
	        }

	        DocenteDTO docenteDTO = docenteService.getById(id);
	        if (docenteDTO == null) {
	            return new ResponseEntity<>("Docente no encontrado", HttpStatus.NOT_FOUND);
	        }

	        if (file != null && !file.isEmpty()) {
	            try {
	                String fotoPerfil = archivoUtil.guardarArchivoImagen(file);
	                docenteDTO.setFotoPerfil(fotoPerfil);
	            } catch (IOException e) {
	                return new ResponseEntity<>("Error al guardar la foto de perfil", HttpStatus.INTERNAL_SERVER_ERROR);
	            }
	        }

	        docenteDTO.setUsername(username);
	        docenteDTO.setPassword(password);
	        docenteDTO.setNombreCompleto(nombreCompleto);
	        docenteDTO.setEmail(email);

	        int status = docenteService.updateById(id, docenteDTO);
	        if (status == 0) {
	            return new ResponseEntity<>("Docente actualizado exitosamente", HttpStatus.OK);
	        } else if (status == 1) {
	            return new ResponseEntity<>("Ya existe un docente con ese username", HttpStatus.CONFLICT);
	        } else {
	            return new ResponseEntity<>("Error desconocido al actualizar el docente", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    } catch (Exception e) {
	        return new ResponseEntity<>("Error inesperado: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	@DeleteMapping("/docente/deletebyid/{id}")
	public ResponseEntity<String> deleteDocenteById(@PathVariable Long id) {
	    try {
	        if (id == null || id <= 0) {
	            return new ResponseEntity<>("ID inválido", HttpStatus.BAD_REQUEST);
	        }
	        int status = docenteService.deleteById(id);
	        if (status == 0) {
	            return new ResponseEntity<>("Docente eliminado exitosamente", HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>("Docente no encontrado", HttpStatus.NOT_FOUND);
	        }
	    } catch (Exception e) {
	        return new ResponseEntity<>("Error inesperado: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	@DeleteMapping("/docente/deletebyname")
	public ResponseEntity<String> deleteDocenteByName(@RequestParam String nombreCompleto) {
	    try {
	        if (nombreCompleto == null || nombreCompleto.trim().isEmpty()) {
	            return new ResponseEntity<>("El nombre no puede estar vacío", HttpStatus.BAD_REQUEST);
	        }
	        int status = docenteService.deleteByNombre(nombreCompleto);
	        if (status == 0) {
	            return new ResponseEntity<>("Docente eliminado exitosamente", HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>("Docente no encontrado", HttpStatus.NOT_FOUND);
	        }
	    } catch (Exception e) {
	        return new ResponseEntity<>("Error inesperado: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	// ----------------------------------Estudiante----------------------------------------------------//

	@GetMapping("/estudiante/getall")
	public ResponseEntity<List<EstudianteDTO>> getAllEstudiantes() {
	    try {
	        List<EstudianteDTO> estudiantes = estudianteService.getAll();
	        if (estudiantes.isEmpty()) {
	            return new ResponseEntity<>(estudiantes, HttpStatus.NO_CONTENT);
	        } else {
	            return new ResponseEntity<>(estudiantes, HttpStatus.OK);
	        }
	    } catch (Exception e) {
	        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	@GetMapping("/estudiante/getbyid/{id}")
	public ResponseEntity<EstudianteDTO> getEstudianteById(@PathVariable Long id) {
	    try {
	        if (id == null || id <= 0) {
	            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	        }
	        EstudianteDTO estudiante = estudianteService.getById(id);
	        if (estudiante != null) {
	            return new ResponseEntity<>(estudiante, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	        }
	    } catch (Exception e) {
	        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	@PutMapping(path = "/estudiante/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> updateEstudiante(
	    @RequestParam("id") Long id,
	    @RequestParam("username") String username,
	    @RequestParam("password") String password,
	    @RequestParam("nombreCompleto") String nombreCompleto,
	    @RequestParam("email") String email,
	    @RequestParam(value = "file", required = false) MultipartFile file
	) {
	    try {
	        if (id == null || id <= 0) {
	            return new ResponseEntity<>("ID inválido", HttpStatus.BAD_REQUEST);
	        }
	        if (username == null || username.trim().isEmpty()) {
	            return new ResponseEntity<>("El username es obligatorio", HttpStatus.BAD_REQUEST);
	        }
	        if (password == null || password.trim().isEmpty()) {
	            return new ResponseEntity<>("La contraseña es obligatoria", HttpStatus.BAD_REQUEST);
	        }
	        if (nombreCompleto == null || nombreCompleto.trim().isEmpty()) {
	            return new ResponseEntity<>("El nombre completo es obligatorio", HttpStatus.BAD_REQUEST);
	        }
	        if (email == null || !email.contains("@")) {
	            return new ResponseEntity<>("El email no es válido", HttpStatus.BAD_REQUEST);
	        }

	        EstudianteDTO estudianteDTO = estudianteService.getById(id);
	        if (estudianteDTO == null) {
	            return new ResponseEntity<>("Estudiante no encontrado", HttpStatus.NOT_FOUND);
	        }

	        if (file != null && !file.isEmpty()) {
	            try {
	                String fotoPerfil = archivoUtil.guardarArchivoImagen(file);
	                estudianteDTO.setFotoPerfil(fotoPerfil);
	            } catch (IOException e) {
	                return new ResponseEntity<>("Error al guardar la foto de perfil", HttpStatus.INTERNAL_SERVER_ERROR);
	            }
	        }

	        estudianteDTO.setUsername(username);
	        estudianteDTO.setPassword(password);
	        estudianteDTO.setNombreCompleto(nombreCompleto);
	        estudianteDTO.setEmail(email);

	        int status = estudianteService.updateById(id, estudianteDTO);
	        if (status == 0) {
	            return new ResponseEntity<>("Estudiante actualizado exitosamente", HttpStatus.OK);
	        } else if (status == 1) {
	            return new ResponseEntity<>("Ya existe un estudiante con ese username", HttpStatus.CONFLICT);
	        } else {
	            return new ResponseEntity<>("Error desconocido al actualizar el estudiante", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    } catch (Exception e) {
	        return new ResponseEntity<>("Error inesperado: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	@DeleteMapping("/estudiante/deletebyid/{id}")
	public ResponseEntity<String> deleteEstudianteById(@PathVariable Long id) {
	    try {
	        if (id == null || id <= 0) {
	            return new ResponseEntity<>("ID inválido", HttpStatus.BAD_REQUEST);
	        }
	        int status = estudianteService.deleteById(id);
	        if (status == 0) {
	            return new ResponseEntity<>("Estudiante eliminado exitosamente", HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>("Estudiante no encontrado", HttpStatus.NOT_FOUND);
	        }
	    } catch (Exception e) {
	        return new ResponseEntity<>("Error inesperado: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	@DeleteMapping("/estudiante/deletebyname")
	public ResponseEntity<String> deleteEstudianteByName(@RequestParam String nombreCompleto) {
	    try {
	        if (nombreCompleto == null || nombreCompleto.trim().isEmpty()) {
	            return new ResponseEntity<>("El nombre no puede estar vacío", HttpStatus.BAD_REQUEST);
	        }
	        int status = estudianteService.deleteByNombre(nombreCompleto);
	        if (status == 0) {
	            return new ResponseEntity<>("Estudiante eliminado exitosamente", HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>("Estudiante no encontrado", HttpStatus.NOT_FOUND);
	        }
	    } catch (Exception e) {
	        return new ResponseEntity<>("Error inesperado: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
}