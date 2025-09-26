/**
 * Clase DetalleSubtemaRepository
 * Proyecto: proyectocorte2back
 * Paquete: co.edu.unbosque.proyectocorte2back.repository
 *
 * Descripción: Documentación pendiente.
 */
package co.edu.unbosque.proyectocorte2back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.proyectocorte2back.entity.DetalleSubtema;


// TODO: Auto-generated Javadoc
/**
 * The Interface DetalleSubtemaRepository.
 */
public interface DetalleSubtemaRepository extends JpaRepository<DetalleSubtema, Long> {

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the optional
	 */
	public Optional<DetalleSubtema> findById(Long id);
	
	/**
	 * Delete by id.
	 *
	 * @param id the id
	 */
	public void deleteById(Long id);
}
