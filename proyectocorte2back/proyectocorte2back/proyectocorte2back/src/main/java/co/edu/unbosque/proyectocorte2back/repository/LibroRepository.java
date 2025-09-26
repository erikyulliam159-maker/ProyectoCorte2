/**
 * Clase LibroRepository
 * Proyecto: proyectocorte2back
 * Paquete: co.edu.unbosque.proyectocorte2back.repository
 *
 * Descripción: Documentación pendiente.
 */
package co.edu.unbosque.proyectocorte2back.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import co.edu.unbosque.proyectocorte2back.entity.Libro;

// TODO: Auto-generated Javadoc
/**
 * The Interface LibroRepository.
 */
public interface LibroRepository  extends JpaRepository<Libro, Long> {

	/**
	 * Find by titulo.
	 *
	 * @param titulo the titulo
	 * @return the optional
	 */
	public Optional<Libro> findByTitulo(String titulo);
	
	/**
	 * Find by autor.
	 *
	 * @param autor the autor
	 * @return the optional
	 */
	public Optional<List<Libro>>findByAutor(String autor);
	
	/**
	 * Delete by titulo.
	 *
	 * @param titulo the titulo
	 */
	public void deleteByTitulo(String titulo);
}
