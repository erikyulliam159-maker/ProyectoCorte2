package co.edu.unbosque.proyectocorte2back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import co.edu.unbosque.proyectocorte2back.dto.TemarioDTO;
import co.edu.unbosque.proyectocorte2back.dto.SubtemaDTO;
import co.edu.unbosque.proyectocorte2back.dto.ProblemaDTO;

import co.edu.unbosque.proyectocorte2back.dto.DetalleSubtemaDTO;
import co.edu.unbosque.proyectocorte2back.services.TemarioService;
import co.edu.unbosque.proyectocorte2back.services.SubtemaService;
import co.edu.unbosque.proyectocorte2back.services.DetalleSubtemaService;
import co.edu.unbosque.proyectocorte2back.services.ProblemaService;

import co.edu.unbosque.proyectocorte2back.dto.LibroDTO;
import co.edu.unbosque.proyectocorte2back.services.LibroService;

@RestController
@RequestMapping("/docente")
@CrossOrigin(origins = { "*" })
@Transactional
public class DocenteController {

	@Autowired
	private TemarioService temarioService;

	@Autowired
	private SubtemaService subtemaService;

	@Autowired
	private DetalleSubtemaService detalleSubtemaService;

	@Autowired
	private ProblemaService problemaService;

	@Autowired
	private LibroService libroService;

	// ------------------- TEMARIO -------------------

	@PostMapping(path = "/temario/createjson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createTemario(@RequestBody TemarioDTO temarioDTO) {
		int status = temarioService.create(temarioDTO);
		if (status == 0) {
			return new ResponseEntity<>("Temario creado exitosamente", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Error al crear el temario", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@PutMapping(path = "/temario/updatejson", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateTemario(@RequestParam Long id, @RequestBody TemarioDTO temarioDTO) {
		int status = temarioService.updateById(id, temarioDTO);
		if (status == 0) {
			return new ResponseEntity<>("Temario actualizado exitosamente", HttpStatus.ACCEPTED);
		} else if (status == 1) {
			return new ResponseEntity<>("Temario no encontrado", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>("Error al actualizar el temario", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/temario/getall")
	public ResponseEntity<List<TemarioDTO>> getAllTemarios() {
		List<TemarioDTO> temarios = temarioService.getAll();
		if (temarios.isEmpty()) {
			return new ResponseEntity<>(temarios, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(temarios, HttpStatus.OK);
		}
	}

	@GetMapping("/temario/getbyid/{id}")
	public ResponseEntity<TemarioDTO> getTemarioById(@PathVariable Long id) {
		TemarioDTO temario = temarioService.getById(id);
		if (temario != null) {
			return new ResponseEntity<>(temario, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/temario/deletebyid/{id}")
	public ResponseEntity<String> deleteTemarioById(@PathVariable Long id) {
		int status = temarioService.deleteById(id);
		if (status == 0) {
			return new ResponseEntity<>("Temario eliminado exitosamente", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>("Error al eliminar el temario", HttpStatus.NOT_FOUND);
		}
	}

	// ------------------- SUBTEMA -------------------

	@PostMapping(path = "/subtema/createjson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createSubtema(@RequestBody SubtemaDTO subtemaDTO) {
		int status = subtemaService.create(subtemaDTO);
		if (status == 0) {
			return new ResponseEntity<>("Subtema creado exitosamente", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Error al crear el subtema", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@PutMapping(path = "/subtema/updatejson", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateSubtema(@RequestParam Long id, @RequestBody SubtemaDTO subtemaDTO) {
		int status = subtemaService.updateById(id, subtemaDTO);
		if (status == 0) {
			return new ResponseEntity<>("Subtema actualizado exitosamente", HttpStatus.ACCEPTED);
		} else if (status == 1) {
			return new ResponseEntity<>("Subtema no encontrado", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>("Error al actualizar el subtema", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/subtema/getall")
	public ResponseEntity<List<SubtemaDTO>> getAllSubtemas() {
		List<SubtemaDTO> subtemas = subtemaService.getAll();
		if (subtemas.isEmpty()) {
			return new ResponseEntity<>(subtemas, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(subtemas, HttpStatus.OK);
		}
	}

	@GetMapping("/subtema/getbyid/{id}")
	public ResponseEntity<SubtemaDTO> getSubtemaById(@PathVariable Long id) {
		SubtemaDTO subtema = subtemaService.getById(id);
		if (subtema != null) {
			return new ResponseEntity<>(subtema, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/subtema/deletebyid/{id}")
	public ResponseEntity<String> deleteSubtemaById(@PathVariable Long id) {
		int status = subtemaService.deleteById(id);
		if (status == 0) {
			return new ResponseEntity<>("Subtema eliminado exitosamente", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>("Error al eliminar el subtema", HttpStatus.NOT_FOUND);
		}
	}

	// ------------------- DETALLE SUBTEMA -------------------

	@PostMapping(path = "/detallesubtema/createjson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createDetalleSubtema(@RequestBody DetalleSubtemaDTO detalleDTO) {
		int status = detalleSubtemaService.create(detalleDTO);
		if (status == 0) {
			return new ResponseEntity<>("Detalle de subtema creado exitosamente", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Error al crear el detalle de subtema", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@PutMapping(path = "/detallesubtema/updatejson", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateDetalleSubtema(@RequestParam Long id,
			@RequestBody DetalleSubtemaDTO detalleDTO) {
		int status = detalleSubtemaService.updateById(id, detalleDTO);
		if (status == 0) {
			return new ResponseEntity<>("Detalle de subtema actualizado exitosamente", HttpStatus.ACCEPTED);
		} else if (status == 1) {
			return new ResponseEntity<>("Detalle de subtema ya existe", HttpStatus.IM_USED);
		} else if (status == 2) {
			return new ResponseEntity<>("Detalle de subtema no encontrado", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>("Error al actualizar el detalle de subtema", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/detallesubtema/getall")
	public ResponseEntity<List<DetalleSubtemaDTO>> getAllDetallesSubtema() {
		List<DetalleSubtemaDTO> detalles = detalleSubtemaService.getAll();
		if (detalles.isEmpty()) {
			return new ResponseEntity<>(detalles, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(detalles, HttpStatus.OK);
		}
	}

	@GetMapping("/detallesubtema/getbyid/{id}")
	public ResponseEntity<DetalleSubtemaDTO> getDetalleSubtemaById(@PathVariable Long id) {
		DetalleSubtemaDTO detalle = detalleSubtemaService.getById(id);
		if (detalle != null) {
			return new ResponseEntity<>(detalle, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/detallesubtema/deletebyid/{id}")
	public ResponseEntity<String> deleteDetalleSubtemaById(@PathVariable Long id) {
		int status = detalleSubtemaService.deleteById(id);
		if (status == 0) {
			return new ResponseEntity<>("Detalle de subtema eliminado exitosamente", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>("Error al eliminar el detalle de subtema", HttpStatus.NOT_FOUND);
		}
	}

	// ------------------- PROBLEMA -------------------//

	@PostMapping(path = "/problema/createjson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createProblema(@RequestBody ProblemaDTO problemaDTO) {
		int status = problemaService.create(problemaDTO);
		if (status == 0) {
			return new ResponseEntity<>("Problema creado exitosamente", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Error al crear el problema, puede que el título ya esté en uso",
					HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@PutMapping(path = "/problema/updatejson", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateProblema(@RequestParam Long id, @RequestBody ProblemaDTO problemaDTO) {
		int status = problemaService.updateById(id, problemaDTO);
		if (status == 0) {
			return new ResponseEntity<>("Problema actualizado exitosamente", HttpStatus.ACCEPTED);
		} else if (status == 1) {
			return new ResponseEntity<>("El nuevo título ya está en uso", HttpStatus.IM_USED);
		} else if (status == 2) {
			return new ResponseEntity<>("Problema no encontrado", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>("Error al actualizar el problema", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/problema/getall")
	public ResponseEntity<List<ProblemaDTO>> getAllProblemas() {
		List<ProblemaDTO> problemas = problemaService.getAll();
		if (problemas.isEmpty()) {
			return new ResponseEntity<>(problemas, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(problemas, HttpStatus.OK);
		}
	}

	@GetMapping("/problema/getbyid/{id}")
	public ResponseEntity<ProblemaDTO> getProblemaById(@PathVariable Long id) {
		ProblemaDTO problema = problemaService.getById(id);
		if (problema != null) {
			return new ResponseEntity<>(problema, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/problema/deletebyid/{id}")
	public ResponseEntity<String> deleteProblemaById(@PathVariable Long id) {
		int status = problemaService.deleteById(id);
		if (status == 0) {
			return new ResponseEntity<>("Problema eliminado exitosamente", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>("Error al eliminar el problema", HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/problema/deletebytitle")
	public ResponseEntity<String> deleteProblemaByTitle(@RequestParam String titulo) {
		int status = problemaService.deleteByTitulo(titulo);
		if (status == 0) {
			return new ResponseEntity<>("Problema eliminado exitosamente", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>("Error al eliminar el problema", HttpStatus.NOT_FOUND);
		}
	}
	//------------------- LIBRO -------------------//

	@PostMapping(path = "/libro/createjson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createLibro(@RequestBody LibroDTO libroDTO) {
	    int status = libroService.create(libroDTO);
	    if (status == 0) {
	        return new ResponseEntity<>("Libro creado exitosamente", HttpStatus.CREATED);
	    } else {
	        return new ResponseEntity<>("Error al crear el libro, puede que el título ya esté en uso", HttpStatus.NOT_ACCEPTABLE);
	    }
	}

	@PutMapping(path = "/libro/updatejson", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateLibro(@RequestParam Long id, @RequestBody LibroDTO libroDTO) {
	    int status = libroService.updateById(id, libroDTO);
	    if (status == 0) {
	        return new ResponseEntity<>("Libro actualizado exitosamente", HttpStatus.ACCEPTED);
	    } else if (status == 1) {
	        return new ResponseEntity<>("El nuevo título ya está en uso", HttpStatus.IM_USED);
	    } else if (status == 2) {
	        return new ResponseEntity<>("Libro no encontrado", HttpStatus.NOT_FOUND);
	    } else {
	        return new ResponseEntity<>("Error al actualizar el libro", HttpStatus.BAD_REQUEST);
	    }
	}

	@GetMapping("/libro/getall")
	public ResponseEntity<List<LibroDTO>> getAllLibros() {
	    List<LibroDTO> libros = libroService.getAll();
	    if (libros.isEmpty()) {
	        return new ResponseEntity<>(libros, HttpStatus.NO_CONTENT);
	    } else {
	        return new ResponseEntity<>(libros, HttpStatus.OK);
	    }
	}

	@GetMapping("/libro/getbyid/{id}")
	public ResponseEntity<LibroDTO> getLibroById(@PathVariable Long id) {
	    LibroDTO libro = libroService.getById(id);
	    if (libro != null) {
	        return new ResponseEntity<>(libro, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	    }
	}

	@DeleteMapping("/libro/deletebyid/{id}")
	public ResponseEntity<String> deleteLibroById(@PathVariable Long id) {
	    int status = libroService.deleteById(id);
	    if (status == 0) {
	        return new ResponseEntity<>("Libro eliminado exitosamente", HttpStatus.ACCEPTED);
	    } else {
	        return new ResponseEntity<>("Error al eliminar el libro", HttpStatus.NOT_FOUND);
	    }
	}

	@DeleteMapping("/libro/deletebytitle")
	public ResponseEntity<String> deleteLibroByTitle(@RequestParam String titulo) {
	    int status = libroService.deleteByTitulo(titulo);
	    if (status == 0) {
	        return new ResponseEntity<>("Libro eliminado exitosamente", HttpStatus.ACCEPTED);
	    } else {
	        return new ResponseEntity<>("Error al eliminar el libro", HttpStatus.NOT_FOUND);
	    }
	}
}