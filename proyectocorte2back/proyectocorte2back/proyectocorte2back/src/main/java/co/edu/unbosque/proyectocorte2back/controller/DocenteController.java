/**
 * Clase DocenteController
 * Proyecto: proyectocorte2back
 * Paquete: co.edu.unbosque.proyectocorte2back.controller
 *
 * Descripción: Documentación pendiente.
 */
package co.edu.unbosque.proyectocorte2back.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import co.edu.unbosque.proyectocorte2back.dto.TemarioDTO;

import co.edu.unbosque.proyectocorte2back.dto.ProblemaDTO;

import co.edu.unbosque.proyectocorte2back.services.TemarioService;

import co.edu.unbosque.proyectocorte2back.services.ProblemaService;

import co.edu.unbosque.proyectocorte2back.dto.LibroDTO;
import co.edu.unbosque.proyectocorte2back.util.ArchivoUtil;
import co.edu.unbosque.proyectocorte2back.services.LibroService;

// TODO: Auto-generated Javadoc
/**
 * The Class DocenteController.
 */
@RestController
@RequestMapping("/docente")
@CrossOrigin(origins = { "*" })
public class DocenteController {

	/** The temario service. */
	@Autowired
	private TemarioService temarioService;

	/** The problema service. */
	@Autowired
	private ProblemaService problemaService;

	/** The libro service. */
	@Autowired
	private LibroService libroService;

	/** The archivo util. */
	@Autowired
	private ArchivoUtil archivoUtil;

	// ------------------- TEMARIO -------------------

	/**
	 * Crea el temario.
	 *
	 * @param temarioDTO the temario DTO
	 * @return the response entity
	 */
	@PostMapping(path = "/temario/createjson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createTemario(@RequestBody TemarioDTO temarioDTO) {
		try {

			if (temarioDTO.getTitulo() == null || temarioDTO.getTitulo().trim().isEmpty()) {
				return new ResponseEntity<>("El título del evento es obligatorio", HttpStatus.BAD_REQUEST);
			}

			int status = temarioService.create(temarioDTO);
			if (status == 0) {
				return new ResponseEntity<>("Temario creado exitosamente", HttpStatus.CREATED);
			} else if (status == 1) {
				return new ResponseEntity<>("Ya existe un temario con ese título", HttpStatus.CONFLICT);
			} else {
				return new ResponseEntity<>("Error desconocido al crear el temario", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>("Error de validación: " + e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			
			return new ResponseEntity<>("Error inesperado en el servidor: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Update temario.
	 *
	 * @param id the id
	 * @param temarioDTO the temario DTO
	 * @return the response entity
	 */
	@PutMapping(path = "/temario/updatejson", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateTemario(@RequestParam Long id, @RequestBody TemarioDTO temarioDTO) {
	    System.out.println(">>> updateTemario invocado con id: " + id);
		try {
			int status = temarioService.updateById(id, temarioDTO);
			if (status == 0) {
				return new ResponseEntity<>("Temario actualizado exitosamente", HttpStatus.ACCEPTED);
			} else if (status == 1) {
				return new ResponseEntity<>("El nuevo título ya está en uso", HttpStatus.IM_USED);
			} else if (status == 2) {
				return new ResponseEntity<>("Temario no encontrado", HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>("Error al actualizar el temario", HttpStatus.BAD_REQUEST);
			}
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {

		    return new ResponseEntity<>("Error interno al actualizar el temario", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Gets the all temarios.
	 *
	 * @return the all temarios
	 */
	@GetMapping("/temario/getall")
	public ResponseEntity<List<TemarioDTO>> getAllTemarios() {
		List<TemarioDTO> temarios = temarioService.getAll();
		if (temarios.isEmpty()) {
			return new ResponseEntity<>(temarios, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(temarios, HttpStatus.OK);
		}
	}

	/**
	 * Gets the temario by id.
	 *
	 * @param id the id
	 * @return the temario by id
	 */
	@GetMapping("/temario/getbyid/{id}")
	public ResponseEntity<TemarioDTO> getTemarioById(@PathVariable Long id) {
		TemarioDTO temario = temarioService.getById(id);
		if (temario != null) {
			return new ResponseEntity<>(temario, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Delete temario by id.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@DeleteMapping("/temario/deletebyid/{id}")
	public ResponseEntity<String> deleteTemarioById(@PathVariable Long id) {
		int status = temarioService.deleteById(id);
		if (status == 0) {
			return new ResponseEntity<>("Temario eliminado exitosamente", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>("Error al eliminar el temario, no encontrado", HttpStatus.NOT_FOUND);
		}
	}

	// ------------------- PROBLEMA -------------------//

	/**
	 * Crea el problema.
	 *
	 * @param problemaDTO the problema DTO
	 * @return the response entity
	 */
	@PostMapping(path = "/problema/createjson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createProblema(@RequestBody ProblemaDTO problemaDTO) {

		try {

			if (problemaDTO.getTitulo() == null || problemaDTO.getTitulo().trim().isEmpty()) {
				return new ResponseEntity<>("El título del problema es obligatorio", HttpStatus.BAD_REQUEST);
			} else if (problemaDTO.getJuez() == null || problemaDTO.getJuez().trim().isEmpty()) {
				return new ResponseEntity<>("El juez del problema es obligatorio", HttpStatus.BAD_REQUEST);
			} else if (problemaDTO.getTema() == null || problemaDTO.getTema().trim().isEmpty()) {
				return new ResponseEntity<>("El Tema del problema es obligatorio", HttpStatus.BAD_REQUEST);
			}

			int status = problemaService.create(problemaDTO);
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

	

	/**
	 * Update problema.
	 *
	 * @param id the id
	 * @param problemaDTO the problema DTO
	 * @return the response entity
	 */
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

	/**
	 * Gets the all problemas.
	 *
	 * @return the all problemas
	 */
	@GetMapping("/problema/getall")
	public ResponseEntity<List<ProblemaDTO>> getAllProblemas() {
		List<ProblemaDTO> problemas = problemaService.getAll();
		if (problemas.isEmpty()) {
			return new ResponseEntity<>(problemas, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(problemas, HttpStatus.OK);
		}
	}

	/**
	 * Gets the problema by id.
	 *
	 * @param id the id
	 * @return the problema by id
	 */
	@GetMapping("/problema/getbyid/{id}")
	public ResponseEntity<ProblemaDTO> getProblemaById(@PathVariable Long id) {
		ProblemaDTO problema = problemaService.getById(id);
		if (problema != null) {
			return new ResponseEntity<>(problema, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Delete problema by id.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@DeleteMapping("/problema/deletebyid/{id}")
	public ResponseEntity<String> deleteProblemaById(@PathVariable Long id) {
		int status = problemaService.deleteById(id);
		if (status == 0) {
			return new ResponseEntity<>("Problema eliminado exitosamente", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>("Error al eliminar el problema", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Delete problema by title.
	 *
	 * @param titulo the titulo
	 * @return the response entity
	 */
	@DeleteMapping("/problema/deletebytitle")
	public ResponseEntity<String> deleteProblemaByTitle(@RequestParam String titulo) {
		int status = problemaService.deleteByTitulo(titulo);
		if (status == 0) {
			return new ResponseEntity<>("Problema eliminado exitosamente", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>("Error al eliminar el problema", HttpStatus.NOT_FOUND);
		}
	}
	// ------------------- LIBRO -------------------//

	/**
	 * Crea el libro.
	 *
	 * @param titulo the titulo
	 * @param autor the autor
	 * @param anio the anio
	 * @param descripcion the descripcion
	 * @param imagenPortada the imagen portada
	 * @param file the file
	 * @param urlPdf the url pdf
	 * @return the response entity
	 */
	@PostMapping(path = "/libro/createjson", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> createLibro(@RequestParam("titulo") String titulo,
	        @RequestParam("autor") String autor, @RequestParam("anio") int anio,
	        @RequestParam("descripcion") String descripcion,
	        @RequestParam(value = "imagenPortada", required = false) String imagenPortada,
	        @RequestParam(value = "file", required = false) MultipartFile file, // ← AHORA ES OPCIONAL
	        @RequestParam(value = "urlPdf", required = false) String urlPdf) {
	    try {

	        if (titulo == null || titulo.trim().isEmpty()) {
	            return new ResponseEntity<>("El título del libro es obligatorio", HttpStatus.BAD_REQUEST);
	        } else if(autor == null || autor.trim().isEmpty()) {
	            return new ResponseEntity<>("El autor del libro es obligatorio", HttpStatus.BAD_REQUEST);
	        } else if(descripcion == null || descripcion.trim().isEmpty()) {
	            return new ResponseEntity<>("La descripcion del libro es obligatorio", HttpStatus.BAD_REQUEST);
	        }
	        
	     
	        if ((file == null || file.isEmpty()) && (urlPdf == null || urlPdf.trim().isEmpty())) {
	            return new ResponseEntity<>("Debe proporcionar un archivo PDF o una URL de PDF", HttpStatus.BAD_REQUEST);
	        }
	        
	        String nombreArchivo = null;
	        if (file != null && !file.isEmpty()) {
	            nombreArchivo = archivoUtil.guardarArchivo(file);
	        }
	        
	
	        if (urlPdf == null) {
	            urlPdf = "";
	        }

	        LibroDTO libroDTO = new LibroDTO(titulo, autor, anio, descripcion, imagenPortada, nombreArchivo, urlPdf);

	        int status = libroService.create(libroDTO);
	        if (status == 0) {
	            return new ResponseEntity<>("Libro creado exitosamente", HttpStatus.CREATED);
	        } else if (status == 1) {
	            return new ResponseEntity<>("Ya existe un libro con ese título", HttpStatus.CONFLICT);
	        } else {
	            return new ResponseEntity<>("Error desconocido al crear el libro", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    } catch (IllegalArgumentException e) {
	        return new ResponseEntity<>("Error de validación: " + e.getMessage(), HttpStatus.BAD_REQUEST);
	    } catch (Exception e) {
	        return new ResponseEntity<>("Error inesperado en el servidor: " + e.getMessage(),
	                HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	/**
	 * Update libro.
	 *
	 * @param id the id
	 * @param titulo the titulo
	 * @param autor the autor
	 * @param anio the anio
	 * @param descripcion the descripcion
	 * @param imagenPortada the imagen portada
	 * @param file the file
	 * @param urlPdf the url pdf
	 * @return the response entity
	 */
	@PutMapping(path = "/libro/updatejson", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> updateLibro(@RequestParam Long id, @RequestParam("titulo") String titulo,
			@RequestParam("autor") String autor, @RequestParam("anio") int anio,
			@RequestParam("descripcion") String descripcion,
			@RequestParam(value = "imagenPortada", required = false) String imagenPortada,
			@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(value = "urlPdf", required = false) String urlPdf) {
		if (urlPdf == null) {
		    urlPdf = "";
		}
		try {
			LibroDTO libroDTO = libroService.getById(id);
			 if (titulo == null || titulo.trim().isEmpty()) {
		            return new ResponseEntity<>("El título del libro es obligatorio", HttpStatus.BAD_REQUEST);
		        }else if(autor==null|| autor.trim().isEmpty()) {
		        	 return new ResponseEntity<>("El autor del libro es obligatorio", HttpStatus.BAD_REQUEST);
		        }else if(descripcion==null|| descripcion.trim().isEmpty()) {
		        	 return new ResponseEntity<>("La descripcion del libro es obligatorio", HttpStatus.BAD_REQUEST);
		        }else if (libroDTO == null) {
				return new ResponseEntity<>("Libro no encontrado", HttpStatus.NOT_FOUND);
			}

			if (file != null && !file.isEmpty()) {
				String nombreArchivo = archivoUtil.guardarArchivo(file);
				libroDTO.setPdf(nombreArchivo);
			}
			libroDTO.setTitulo(titulo);
			libroDTO.setAutor(autor);
			libroDTO.setAnio(anio);
			libroDTO.setDescripcion(descripcion);
			libroDTO.setImagenPortada(imagenPortada);
			libroDTO.setUrlpdf(urlPdf);

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
		} catch (IOException e) {
			return new ResponseEntity<>("Error al subir el archivo", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Gets the all libros.
	 *
	 * @return the all libros
	 */
	@GetMapping("/libro/getall")
	public ResponseEntity<List<LibroDTO>> getAllLibros() {
		List<LibroDTO> libros = libroService.getAll();
		if (libros.isEmpty()) {
			return new ResponseEntity<>(libros, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(libros, HttpStatus.OK);
		}
	}

	/**
	 * Gets the libro by id.
	 *
	 * @param id the id
	 * @return the libro by id
	 */
	@GetMapping("/libro/getbyid/{id}")
	public ResponseEntity<LibroDTO> getLibroById(@PathVariable Long id) {
		LibroDTO libro = libroService.getById(id);
		if (libro != null) {
			return new ResponseEntity<>(libro, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Delete libro by id.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@DeleteMapping("/libro/deletebyid/{id}")
	public ResponseEntity<String> deleteLibroById(@PathVariable Long id) {
		int status = libroService.deleteById(id);
		if (status == 0) {
			return new ResponseEntity<>("Libro eliminado exitosamente", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>("Error al eliminar el libro", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Delete libro by title.
	 *
	 * @param titulo the titulo
	 * @return the response entity
	 */
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