package co.edu.unbosque.proyectocorte2back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import co.edu.unbosque.proyectocorte2back.entity.Problema;

public interface ProblemaRepository extends JpaRepository<Problema, Long>{
	
	public Optional<Problema> findByTitulo(String titulo);
	
	public void deleteByTitulo(String titulo);
	
}