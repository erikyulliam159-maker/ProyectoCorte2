package co.edu.unbosque.proyectocorte2back.services;

import java.util.List;

public interface CRUDOperation <D>{

	public int create(D newDato);
	public List<D>getAll();
	public int deleteById(Long id);
	public int updateById(Long id,D newDato);
	public long count();
	public boolean exist(Long Id);
}
