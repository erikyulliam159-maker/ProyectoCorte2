package co.edu.unbosque.proyectocorte2back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.proyectocorte2back.entity.Docente;


public interface DocenteRepository extends JpaRepository<Docente, Long>{

	public Optional<Docente> findByNombreCompleto(String nombreCompleto);
	public Optional<Docente>findByEmail(String email);
	public Optional<Docente>findByUsername(String username);
	
	public void deleteByNombreCompleto(String nombre);
	public void deleteByEmail(String email);
	public void deleteByUsername(String username);
}
