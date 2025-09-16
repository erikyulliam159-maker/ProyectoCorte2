package co.edu.unbosque.proyectocorte2back.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import co.edu.unbosque.proyectocorte2back.entity.Libro;

public interface LibroRepository  extends JpaRepository<Libro, Long> {

	public Optional<Libro> findByTitulo(String titulo);
	public Optional<List<Libro>>findByAutor(String autor);
	
	public void deleteByTitulo(String titulo);
}
