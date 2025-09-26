/**
 * Clase LinkValiosoRepository
 * Proyecto: proyectocorte2back
 * Paquete: co.edu.unbosque.proyectocorte2back.repository
 *
 * Descripción: Documentación pendiente.
 */
package co.edu.unbosque.proyectocorte2back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.proyectocorte2back.entity.LinkValioso;

// TODO: Auto-generated Javadoc
/**
 * The Interface LinkValiosoRepository.
 */
public interface LinkValiosoRepository extends JpaRepository<LinkValioso, Long> {

	/**
	 * Find by nombre.
	 *
	 * @param nombre the nombre
	 * @return the optional
	 */
	public Optional<LinkValioso> findByNombre(String nombre);

	/**
	 * Delete by nombre.
	 *
	 * @param nombre the nombre
	 */
	public void deleteByNombre(String nombre);
}
