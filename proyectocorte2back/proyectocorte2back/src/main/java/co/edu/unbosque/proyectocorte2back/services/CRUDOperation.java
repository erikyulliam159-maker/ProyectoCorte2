/**
 * Clase CRUDOperation
 * Proyecto: proyectocorte2back
 * Paquete: co.edu.unbosque.proyectocorte2back.services
 *
 * Descripción: Documentación pendiente.
 */
package co.edu.unbosque.proyectocorte2back.services;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Interface CRUDOperation.
 *
 * @param <D> the generic type
 */
public interface CRUDOperation <D>{

	/**
	 * Crea el.
	 *
	 * @param newDato the new dato
	 * @return the int
	 */
	public int create(D newDato);
	
	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	public List<D>getAll();
	
	/**
	 * Delete by id.
	 *
	 * @param id the id
	 * @return the int
	 */
	public int deleteById(Long id);
	
	/**
	 * Update by id.
	 *
	 * @param id the id
	 * @param newDato the new dato
	 * @return the int
	 */
	public int updateById(Long id,D newDato);
	
	/**
	 * Count.
	 *
	 * @return the long
	 */
	public long count();
	
	/**
	 * Exist.
	 *
	 * @param Id the id
	 * @return true, if successful
	 */
	public boolean exist(Long Id);
}
