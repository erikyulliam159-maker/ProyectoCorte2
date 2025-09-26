/**
 * Clase SubtemaRepository
 * Proyecto: proyectocorte2back
 * Paquete: co.edu.unbosque.proyectocorte2back.repository
 *
 * Descripción: Documentación pendiente.
 */
package co.edu.unbosque.proyectocorte2back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import co.edu.unbosque.proyectocorte2back.entity.Subtema;

// TODO: Auto-generated Javadoc
/**
 * The Interface SubtemaRepository.
 */
public interface SubtemaRepository extends JpaRepository<Subtema, Long> {

	/**
	 * Find by nombre.
	 *
	 * @param nombre the nombre
	 * @return the optional
	 */
	public Optional<Subtema> findByNombre(String nombre);
	
	/**
	 * Delete by nombre.
	 *
	 * @param nombre the nombre
	 */
	public void deleteByNombre(String nombre);
	
}
