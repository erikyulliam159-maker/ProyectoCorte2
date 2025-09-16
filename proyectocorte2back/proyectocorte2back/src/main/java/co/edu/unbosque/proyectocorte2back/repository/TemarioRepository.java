package co.edu.unbosque.proyectocorte2back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import co.edu.unbosque.proyectocorte2back.entity.Temario;

public interface TemarioRepository extends JpaRepository<Temario, Long>{
	public Optional<Temario> findByTitulo(String titulo);
	public void deleteByTitulo(String titulo);

}
