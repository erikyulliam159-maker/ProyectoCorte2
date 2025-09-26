/**
 * Clase TemarioService
 * Proyecto: proyectocorte2back
 * Paquete: co.edu.unbosque.proyectocorte2back.services
 *
 * Descripción: Documentación pendiente.
 */
package co.edu.unbosque.proyectocorte2back.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.proyectocorte2back.dto.SubtemaDTO;
import co.edu.unbosque.proyectocorte2back.dto.TemarioDTO;
import co.edu.unbosque.proyectocorte2back.entity.DetalleSubtema;
import co.edu.unbosque.proyectocorte2back.entity.Subtema;
import co.edu.unbosque.proyectocorte2back.entity.Temario;
import co.edu.unbosque.proyectocorte2back.repository.TemarioRepository;
import co.edu.unbosque.proyectocorte2back.util.ExceptionChecker;

// TODO: Auto-generated Javadoc
/**
 * The Class TemarioService.
 */
@Service
public class TemarioService implements CRUDOperation<TemarioDTO> {

	/** The temario repository. */
	@Autowired
	private TemarioRepository temarioRepository;

	/** The model mapper. */
	@Autowired
	private ModelMapper modelMapper;

	/**
	 * Count.
	 *
	 * @return the long
	 */
	@Override
	public long count() {
		return temarioRepository.count();
	}

	/**
	 * Exist.
	 *
	 * @param id the id
	 * @return true, if successful
	 */
	@Override
	public boolean exist(Long id) {
		return temarioRepository.existsById(id);
	}


	/**
	 * Crea el.
	 *
	 * @param data the data
	 * @return the int
	 */
	@Override
	public int create(TemarioDTO data) {
	    ExceptionChecker.checkNotNullOrEmpty(data.getTitulo(), "Título del Temario no puede estar vacio");
	    ExceptionChecker.checkOnlyLetters(data.getTitulo(), "Título del Temario solo letras");
	    ExceptionChecker.checkStringLength(data.getTitulo(), 3, 100, "Título del Temario min 3  y max 100");

	    Temario entity = modelMapper.map(data, Temario.class);


	    List<Subtema> subtemasEntity = new ArrayList<>();
	    if (data.getSubtemas() != null && !data.getSubtemas().isEmpty()) {
	        for (SubtemaDTO subtemaDTO : data.getSubtemas()) {
	            Subtema subtema = modelMapper.map(subtemaDTO, Subtema.class);
	            subtema.setId(null); 
	            subtema.setTemario(entity);
	            if (subtema.getDetalle() != null) {
	                subtema.getDetalle().setId(null);
	                subtema.getDetalle().setSubtema(subtema);
	            }
	            subtemasEntity.add(subtema);
	        }
	    }
	    entity.setSubtemas(subtemasEntity); 

	    temarioRepository.save(entity);
	    return 0;
	}
	
	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	@Override
	public List<TemarioDTO> getAll() {
		List<Temario> entityList = temarioRepository.findAll();
		List<TemarioDTO> dtoList = new ArrayList<>();
		entityList.forEach((entity) -> {
			TemarioDTO dto = modelMapper.map(entity, TemarioDTO.class);
			dtoList.add(dto);
		});
		return dtoList;
	}

	/**
	 * Delete by id.
	 *
	 * @param id the id
	 * @return the int
	 */
	@Override
	public int deleteById(Long id) {
		Optional<Temario> found = temarioRepository.findById(id);
		if (found.isPresent()) {
			temarioRepository.delete(found.get());
			return 0;
		} else {
			return 1;
		}
	}
	
	/**
	 * Update by id.
	 *
	 * @param id the id
	 * @param newData the new data
	 * @return the int
	 */
	@Override
	public int updateById(Long id, TemarioDTO newData) {
	    ExceptionChecker.checkNotNullOrEmpty(newData.getTitulo(), "Título del Temario no puede estar vacio");
	    ExceptionChecker.checkOnlyLetters(newData.getTitulo(), "Título del Temario solo letras");
	    ExceptionChecker.checkStringLength(newData.getTitulo(), 3, 100, "Título del Temario min 3 y max 100");

	    Optional<Temario> temarioConTitulo = temarioRepository.findByTitulo(newData.getTitulo());
	    if (temarioConTitulo.isPresent() && !temarioConTitulo.get().getId().equals(id)) {
	        return 1; // Título en uso
	    }

	    Optional<Temario> found = temarioRepository.findById(id);

	    if (found.isPresent()) {
	        Temario existing = found.get();
	        existing.setTitulo(newData.getTitulo());

	        // GUARDA LOS SUBTEMAS EXISTENTES ANTES DE LIMPIAR
	        List<Subtema> subtemasExistentes = new ArrayList<>(existing.getSubtemas());
	        existing.getSubtemas().clear();

	        if (newData.getSubtemas() != null && !newData.getSubtemas().isEmpty()) {
	            for (SubtemaDTO subtemaDTO : newData.getSubtemas()) {
	                Subtema subtema;
	                
	                if (subtemaDTO.getId() != null) {
	                    // Busca en los subtemas existentes ANTES de limpiar
	                    Optional<Subtema> subtemaExistenteOpt = subtemasExistentes
	                        .stream()
	                        .filter(s -> s.getId().equals(subtemaDTO.getId()))
	                        .findFirst();
	                    
	                    if (subtemaExistenteOpt.isPresent()) {
	                        // REUTILIZA la instancia existente
	                        subtema = subtemaExistenteOpt.get();
	                        subtema.setNombre(subtemaDTO.getNombre());
	                        subtema.setTipo(subtemaDTO.getTipo());
	                        
	                        // Actualiza el detalle
	                        if (subtemaDTO.getDetalle() != null) {
	                            if (subtema.getDetalle() != null) {
	                                subtema.getDetalle().setDescripcion(subtemaDTO.getDetalle().getDescripcion());
	                                subtema.getDetalle().setUrlImagen(subtemaDTO.getDetalle().getUrlImagen());
	                            } else {
	                                DetalleSubtema detalle = new DetalleSubtema();
	                                detalle.setDescripcion(subtemaDTO.getDetalle().getDescripcion());
	                                detalle.setUrlImagen(subtemaDTO.getDetalle().getUrlImagen());
	                                detalle.setSubtema(subtema);
	                                subtema.setDetalle(detalle);
	                            }
	                        } else {
	                            subtema.setDetalle(null);
	                        }
	                    } else {
	                        // Nuevo subtema con ID específico (caso raro)
	                        subtema = new Subtema();
	                        subtema.setId(subtemaDTO.getId());
	                        subtema.setNombre(subtemaDTO.getNombre());
	                        subtema.setTipo(subtemaDTO.getTipo());
	                        subtema.setTemario(existing);
	                        
	                        if (subtemaDTO.getDetalle() != null) {
	                            DetalleSubtema detalle = new DetalleSubtema();
	                            detalle.setDescripcion(subtemaDTO.getDetalle().getDescripcion());
	                            detalle.setUrlImagen(subtemaDTO.getDetalle().getUrlImagen());
	                            detalle.setSubtema(subtema);
	                            subtema.setDetalle(detalle);
	                        }
	                    }
	                } else {
	                    // Nuevo subtema sin ID
	                    subtema = new Subtema();
	                    subtema.setNombre(subtemaDTO.getNombre());
	                    subtema.setTipo(subtemaDTO.getTipo());
	                    subtema.setTemario(existing);
	                    
	                    if (subtemaDTO.getDetalle() != null) {
	                        DetalleSubtema detalle = new DetalleSubtema();
	                        detalle.setDescripcion(subtemaDTO.getDetalle().getDescripcion());
	                        detalle.setUrlImagen(subtemaDTO.getDetalle().getUrlImagen());
	                        detalle.setSubtema(subtema);
	                        subtema.setDetalle(detalle);
	                    }
	                }
	                
	                existing.getSubtemas().add(subtema);
	            }
	        }

	        temarioRepository.save(existing);
	        return 0;
	    } else {
	        return 2; // Temario no encontrado
	    }
	}
	

	/**
	 * Gets the by id.
	 *
	 * @param id the id
	 * @return the by id
	 */
	public TemarioDTO getById(Long id) {
		Optional<Temario> found = temarioRepository.findById(id);
		if (found.isPresent()) {
			TemarioDTO dto = modelMapper.map(found.get(), TemarioDTO.class);

			if (found.get().getSubtemas() != null && !found.get().getSubtemas().isEmpty()) {
				List<SubtemaDTO> subtemasDTO = new ArrayList<>();
				for (Subtema subtema : found.get().getSubtemas()) {
					SubtemaDTO subtemaDTO = modelMapper.map(subtema, SubtemaDTO.class);
					subtemasDTO.add(subtemaDTO);
				}
				dto.setSubtemas(subtemasDTO);
			}

			return dto;
		} else {
			return null;
		}
	}

	
}
