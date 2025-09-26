/**
 * Clase ProblemaRepository
 * Proyecto: proyectocorte2back
 * Paquete: co.edu.unbosque.proyectocorte2back.repository
 *
 * Descripción: Documentación pendiente.
 */
package co.edu.unbosque.proyectocorte2back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import co.edu.unbosque.proyectocorte2back.entity.Problema;

// TODO: Auto-generated Javadoc
/**
 * The Interface ProblemaRepository.
 */
public interface ProblemaRepository extends JpaRepository<Problema, Long>{
	
	/**
	 * Find by titulo.
	 *
	 * @param titulo the titulo
	 * @return the optional
	 */
	public Optional<Problema> findByTitulo(String titulo);
	
	/**
	 * Delete by titulo.
	 *
	 * @param titulo the titulo
	 */
	public void deleteByTitulo(String titulo);
	
}