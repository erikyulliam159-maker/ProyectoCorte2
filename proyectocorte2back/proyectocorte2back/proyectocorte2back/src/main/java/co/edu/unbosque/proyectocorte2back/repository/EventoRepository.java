/**
 * Clase EventoRepository
 * Proyecto: proyectocorte2back
 * Paquete: co.edu.unbosque.proyectocorte2back.repository
 *
 * Descripción: Documentación pendiente.
 */
package co.edu.unbosque.proyectocorte2back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import co.edu.unbosque.proyectocorte2back.entity.Evento;

// TODO: Auto-generated Javadoc
/**
 * The Interface EventoRepository.
 */
public interface EventoRepository extends JpaRepository<Evento,Long> {

	/**
	 * Find by titulo.
	 *
	 * @param titulo the titulo
	 * @return the optional
	 */
	public Optional<Evento> findByTitulo(String titulo);
	
	/**
	 * Delete by titulo.
	 *
	 * @param titulo the titulo
	 */
	public void deleteByTitulo(String titulo);
}
