/**
 * Clase EventoService
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

import co.edu.unbosque.proyectocorte2back.dto.EventoDTO;
import co.edu.unbosque.proyectocorte2back.entity.Evento;
import co.edu.unbosque.proyectocorte2back.repository.EventoRepository;
import co.edu.unbosque.proyectocorte2back.util.ExceptionChecker;

// TODO: Auto-generated Javadoc
/**
 * The Class EventoService.
 */
@Service
public class EventoService implements CRUDOperation<EventoDTO> {
    
    /** The evento repository. */
    @Autowired
    private EventoRepository eventoRepository;

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
        return eventoRepository.count();
    }

    /**
     * Exist.
     *
     * @param id the id
     * @return true, if successful
     */
    @Override
    public boolean exist(Long id) {
        return eventoRepository.existsById(id);
    }

    /**
     * Crea el.
     *
     * @param data the data
     * @return the int
     */
    @Override
    public int create(EventoDTO data) {
    	ExceptionChecker.checkNotNullOrEmpty(data.getTitulo(), "Titulo del Evento no puede estar vacia ");
        ExceptionChecker.checkStringLength(data.getTitulo(), 3, 100, "Titulo del Evento min 3 y max 100");
        ExceptionChecker.checkNotNullOrEmpty(data.getDescripcion(), "Descripción del Evento no puede estar vacia ");
        ExceptionChecker.checkStringLength(data.getDescripcion(), 3, 500, "Descripción del Evento min 3 y max 500");
        ExceptionChecker.checkNotNullOrEmpty(data.getUrl(), "URL del Evento no puede estar vacia");
        ExceptionChecker.checkNotNullDate(data.getFecha(), "Fecha del Evento no puede estar vacio");
        ExceptionChecker.checkNotPastDate(data.getFecha(), "No se pueden poner fechas en meses anteriores");
        System.out.println("Creando evento: " + data);
        Evento entity = modelMapper.map(data, Evento.class);
        System.out.println("Entidad a guardar: " + entity);
        if (findNombreAlreadyTaken(entity)){
            return 1;
        } else {
            eventoRepository.save(entity);
            return 0;
        }
    }
    
    /**
     * Gets the all.
     *
     * @return the all
     */
    @Override
    public List<EventoDTO> getAll() {
        List<Evento> entityList = eventoRepository.findAll();
        List<EventoDTO> dtoList = new ArrayList<>();
        entityList.forEach((entity) -> {
            EventoDTO dto = modelMapper.map(entity, EventoDTO.class);
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
        Optional<Evento> found = eventoRepository.findById(id);
        if (found.isPresent()) {
            eventoRepository.delete(found.get());
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * Delete by nombre.
     *
     * @param id the id
     * @return the int
     */
    public int deleteByNombre(Long id) {
        Optional<Evento> found = eventoRepository.findById(id);
        if (found.isPresent()) {
            eventoRepository.delete(found.get());
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
    public int updateById(Long id, EventoDTO newData) {
     	ExceptionChecker.checkNotNullOrEmpty(newData.getTitulo(), "Titulo del Evento no puede estar vacia ");
        ExceptionChecker.checkStringLength(newData.getTitulo(), 3, 100, "Titulo del Evento min 3 y max 100");
        ExceptionChecker.checkNotNullOrEmpty(newData.getDescripcion(), "Descripción del Evento no puede estar vacia ");
        ExceptionChecker.checkStringLength(newData.getDescripcion(), 3, 500, "Descripción del Evento min 3 y max 500");
        ExceptionChecker.checkNotNullOrEmpty(newData.getUrl(), "URL del Evento no puede estar vacia");
        ExceptionChecker.checkNotNullDate(newData.getFecha(), "Fecha del Evento no puede estar vacio");
        ExceptionChecker.checkNotPastDate(newData.getFecha(), "No se pueden poner fechas en meses anteriores");

        Optional<Evento> found = eventoRepository.findById(id);
        Optional<Evento> newFound = eventoRepository.findById(newData.getId());

        if (found.isPresent() && !newFound.isPresent()) {
            Evento temp = found.get();
            temp.setDescripcion(newData.getDescripcion());
            temp.setFecha(newData.getFecha());
            temp.setUrl(newData.getUrl());
            eventoRepository.save(temp);
            return 0;
        }
        if (found.isPresent() && newFound.isPresent()) {
            return 1;
        }
        if (!found.isPresent()) {
            return 2;
        } else {
            return 3;
        }
    }

    /**
     * Gets the by id.
     *
     * @param id the id
     * @return the by id
     */
    public EventoDTO getById(Long id) {
        Optional<Evento> found = eventoRepository.findById(id);
        if (found.isPresent()) {
            return modelMapper.map(found.get(), EventoDTO.class);
        } else {
            return null;
        }
    }

    /**
     * Find nombre already taken.
     *
     * @param newEvento the new evento
     * @return true, if successful
     */
    public boolean findNombreAlreadyTaken(Evento newEvento) {
        Optional<Evento> found = eventoRepository.findByTitulo(newEvento.getTitulo());
        return found.isPresent();
    }
}
