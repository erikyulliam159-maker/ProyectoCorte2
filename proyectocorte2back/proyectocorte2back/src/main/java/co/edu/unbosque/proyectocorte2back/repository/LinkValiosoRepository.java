package co.edu.unbosque.proyectocorte2back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.proyectocorte2back.entity.LinkValioso;

public interface LinkValiosoRepository extends JpaRepository<LinkValioso, Long> {

	public Optional<LinkValioso> findByNombre(String nombre);

	public void deleteByNombre(String nombre);
}
