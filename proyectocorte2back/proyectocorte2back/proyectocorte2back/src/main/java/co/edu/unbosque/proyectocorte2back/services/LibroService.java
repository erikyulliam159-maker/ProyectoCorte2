/**
 * Clase LibroService
 * Proyecto: proyectocorte2back
 * Paquete: co.edu.unbosque.proyectocorte2back.services
 *
 * Descripción: Documentación pendiente.
 */
package co.edu.unbosque.proyectocorte2back.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.proyectocorte2back.dto.LibroDTO;
import co.edu.unbosque.proyectocorte2back.entity.Libro;
import co.edu.unbosque.proyectocorte2back.repository.LibroRepository;
import co.edu.unbosque.proyectocorte2back.util.ExceptionChecker;

// TODO: Auto-generated Javadoc
/**
 * The Class LibroService.
 */
@Service
public class LibroService implements CRUDOperation<LibroDTO> {

	/** The libro repository. */
	@Autowired
	private LibroRepository libroRepository;

	/** The model mapper. */
	@Autowired
	private ModelMapper modelMapper;

	/**
	 * Count.
	 *
	 * @return the long
	 */
	@Override
	public long count() {
		return libroRepository.count();
	}

	/**
	 * Exist.
	 *
	 * @param id the id
	 * @return true, if successful
	 */
	@Override
	public boolean exist(Long id) {
		return libroRepository.existsById(id);
	}

	/**
	 * Crea el.
	 *
	 * @param data the data
	 * @return the int
	 */
	@Override
	public int create(LibroDTO data) {
	
	    ExceptionChecker.checkNotNullOrEmpty(data.getTitulo(), "Título del Libro no puede estar vacio");
	    ExceptionChecker.checkOnlyLetters(data.getTitulo(), "Título del Libro solo letras");
	    ExceptionChecker.checkStringLength(data.getTitulo(), 3, 100, "Título del Libro min 3 y max 100");
	    
	    ExceptionChecker.checkNotNullOrEmpty(data.getAutor(), "Autor del Libro no puede estar vacio");
	    ExceptionChecker.checkOnlyLetters(data.getAutor(), "Autor del Libro solo letras");
	    ExceptionChecker.checkStringLength(data.getAutor(), 3, 50, "Autor del Libro min 3 y max 50");
	    
	    ExceptionChecker.checkNotNullOrEmpty(data.getDescripcion(), "Descripción del Libro no puede estar vacia");
	    ExceptionChecker.checkStringLength(data.getDescripcion(), 10, 1000, "Descripción del Libro min 10 y max 1000");
	    

	    Optional<Libro> libroExistente = libroRepository.findByTitulo(data.getTitulo());
	    if (libroExistente.isPresent()) {
	        return 1;
	    }
	    
	 
	    boolean tienePdf = data.getPdf() != null && !data.getPdf().trim().isEmpty();
	    boolean tieneUrlPdf = data.getUrlpdf() != null && !data.getUrlpdf().trim().isEmpty();
	    
	    if (!tienePdf && !tieneUrlPdf) {
	        throw new IllegalArgumentException("Debe proporcionar un archivo PDF o una URL de PDF");
	    }
	    

	    Libro libro = new Libro();
	    libro.setTitulo(data.getTitulo());
	    libro.setAutor(data.getAutor());
	    libro.setAnio(data.getAnio());
	    libro.setDescripcion(data.getDescripcion());
	    libro.setImagenPortada(data.getImagenPortada()); // Puede ser null
	    
	    // Manejar PDF - puede ser null
	    libro.setPdf(data.getPdf());
	    
	    // Manejar URL PDF - puede ser null
	    libro.setUrlpdf(data.getUrlpdf());
	    
	    try {
	        libroRepository.save(libro);
	        return 0; // Éxito
	    } catch (Exception e) {
	        e.printStackTrace();
	        return 2; // Error al guardar
	    }
	}
	
	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	@Override
	public List<LibroDTO> getAll() {
		List<Libro> entityList = libroRepository.findAll();
		List<LibroDTO> dtoList = new ArrayList<>();
		entityList.forEach((entity) -> {
			LibroDTO dto = modelMapper.map(entity, LibroDTO.class);
			dtoList.add(dto);
		});
		return dtoList;
	}

	/**
	 * Delete by id.
	 *
	 * @param id the id
	 * @return the int
	 */
	@Override
	public int deleteById(Long id) {
		Optional<Libro> found = libroRepository.findById(id);
		if (found.isPresent()) {
			libroRepository.delete(found.get());
			return 0;
		} else {
			return 1;
		}
	}

	/**
	 * Delete by titulo.
	 *
	 * @param titulo the titulo
	 * @return the int
	 */
	public int deleteByTitulo(String titulo) {
		Optional<Libro> found = libroRepository.findByTitulo(titulo);
		if (found.isPresent()) {
			libroRepository.delete(found.get());
			return 0;
		} else {
			return 1;
		}
	}

	/**
	 * Update by id.
	 *
	 * @param id the id
	 * @param newData the new data
	 * @return the int
	 */
	@Override
	public int updateById(Long id, LibroDTO newData) {
	    ExceptionChecker.checkNotNullOrEmpty(newData.getTitulo(), "Título del Libro");
	    ExceptionChecker.checkStringLength(newData.getTitulo(), 3, 150, "Título del Libro");
	    ExceptionChecker.checkOnlyLetters(newData.getTitulo(), "Titulo del libro solo letras");
	    ExceptionChecker.checkNotNullOrEmpty(newData.getAutor(), "Autor del Libro");
	    ExceptionChecker.checkStringLength(newData.getAutor(), 3, 100, "Autor del Libro");
	    ExceptionChecker.checkOnlyLetters(newData.getAutor(), "Autor del libro solo letras");
	    ExceptionChecker.checkNotNullOrEmpty(newData.getDescripcion(), "Descripción del Libro");
	    ExceptionChecker.checkStringLength(newData.getDescripcion(), 3, 1000, "Descripción del Libro");
	    ExceptionChecker.checkPositiveInt(newData.getAnio(), "Año solo numeros positivos");

	    Optional<Libro> found = libroRepository.findById(id);
	    if (!found.isPresent()) {
	        return 2; // Libro no encontrado
	    }

	    // Verifica si el título ya está en uso por otro libro
	    Optional<Libro> libroConTitulo = libroRepository.findByTitulo(newData.getTitulo());
	    if (libroConTitulo.isPresent() && !libroConTitulo.get().getId().equals(id)) {
	        return 1; // El nuevo título ya está en uso por otro libro
	    }

	    Libro temp = found.get();
	    temp.setDescripcion(newData.getDescripcion());
	    temp.setPdf(newData.getPdf());
	    temp.setUrlpdf(newData.getUrlpdf());
	    temp.setImagenPortada(newData.getImagenPortada());
	    temp.setAutor(newData.getAutor());
	    temp.setAnio(newData.getAnio());
	    temp.setTitulo(newData.getTitulo());
	    libroRepository.save(temp);
	    return 0; // Éxito
	}

	/**
	 * Gets the by id.
	 *
	 * @param id the id
	 * @return the by id
	 */
	public LibroDTO getById(Long id) {
		Optional<Libro> found = libroRepository.findById(id);
		if (found.isPresent()) {
			return modelMapper.map(found.get(), LibroDTO.class);
		} else {
			return null;
		}
	}

	/**
	 * Find titulo already taken.
	 *
	 * @param newLibro the new libro
	 * @return true, if successful
	 */
	public boolean findTituloAlreadyTaken(Libro newLibro) {
		Optional<Libro> found = libroRepository.findByTitulo(newLibro.getTitulo());
		return found.isPresent();
	}
}
