package co.edu.unbosque.proyectocorte2back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.proyectocorte2back.entity.Administrador;


public interface AdminRepository extends JpaRepository<Administrador, Long> {

	public Optional<Administrador> findByNombreCompleto(String nombreCompleto);
	public Optional<Administrador>findByEmail(String email);
	public Optional<Administrador>findByUsername(String username);
	
	public void deleteByNombreCompleto(String nombre);
	public void deleteByEmail(String email);
	public void deleteByUsername(String username);
}
