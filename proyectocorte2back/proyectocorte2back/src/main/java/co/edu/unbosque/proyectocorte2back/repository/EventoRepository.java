package co.edu.unbosque.proyectocorte2back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import co.edu.unbosque.proyectocorte2back.entity.Evento;

public interface EventoRepository extends JpaRepository<Evento,Long> {

	public Optional<Evento> findByTitulo(String titulo);
	public void deleteByTitulo(String titulo);
}
