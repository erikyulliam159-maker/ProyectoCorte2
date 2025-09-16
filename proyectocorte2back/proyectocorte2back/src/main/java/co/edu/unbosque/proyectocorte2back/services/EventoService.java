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

@Service
public class EventoService implements CRUDOperation<EventoDTO> {
    
    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public long count() {
        return eventoRepository.count();
    }

    @Override
    public boolean exist(Long id) {
        return eventoRepository.existsById(id);
    }

    @Override
    public int create(EventoDTO data) {
        Evento entity = modelMapper.map(data, Evento.class);
        if (findNombreAlreadyTaken(entity)) {
            return 1;
        } else {
            eventoRepository.save(entity);
            return 0;
        }
    }

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

    public int deleteByNombre(Long id) {
        Optional<Evento> found = eventoRepository.findById(id);
        if (found.isPresent()) {
            eventoRepository.delete(found.get());
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public int updateById(Long id, EventoDTO newData) {
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

    public EventoDTO getById(Long id) {
        Optional<Evento> found = eventoRepository.findById(id);
        if (found.isPresent()) {
            return modelMapper.map(found.get(), EventoDTO.class);
        } else {
            return null;
        }
    }

    public boolean findNombreAlreadyTaken(Evento newEvento) {
        Optional<Evento> found = eventoRepository.findById(newEvento.getId());
        return found.isPresent();
    }
}
