package co.edu.unbosque.proyectocorte2back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import co.edu.unbosque.proyectocorte2back.entity.Subtema;

public interface SubtemaRepository extends JpaRepository<Subtema, Long> {

	public Optional<Subtema> findByNombre(String nombre);
	public void deleteByNombre(String nombre);
	
}
