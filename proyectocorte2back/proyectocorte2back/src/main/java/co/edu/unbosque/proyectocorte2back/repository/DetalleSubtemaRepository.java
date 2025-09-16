package co.edu.unbosque.proyectocorte2back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.proyectocorte2back.entity.DetalleSubtema;
import co.edu.unbosque.proyectocorte2back.entity.Docente;

public interface DetalleSubtemaRepository extends JpaRepository<DetalleSubtema, Long> {

	public Optional<DetalleSubtema> findById(Long id);
	
	public void deleteById(Long id);
}
