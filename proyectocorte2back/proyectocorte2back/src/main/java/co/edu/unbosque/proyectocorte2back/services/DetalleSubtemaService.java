package co.edu.unbosque.proyectocorte2back.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.proyectocorte2back.dto.DetalleSubtemaDTO;
import co.edu.unbosque.proyectocorte2back.entity.DetalleSubtema;
import co.edu.unbosque.proyectocorte2back.repository.DetalleSubtemaRepository;

@Service
public class DetalleSubtemaService implements CRUDOperation<DetalleSubtemaDTO> {

    @Autowired
    private DetalleSubtemaRepository detalleSubtemaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public long count() {
        return detalleSubtemaRepository.count();
    }

    @Override
    public boolean exist(Long id) {
        return detalleSubtemaRepository.existsById(id);
    }

    @Override
    public int create(DetalleSubtemaDTO data) {
        DetalleSubtema entity = modelMapper.map(data, DetalleSubtema.class);
        if (findIdAlreadyTaken(entity)) {
            return 1;
        } else {
            detalleSubtemaRepository.save(entity);
            return 0;
        }
    }

    @Override
    public List<DetalleSubtemaDTO> getAll() {
        List<DetalleSubtema> entityList = detalleSubtemaRepository.findAll();
        List<DetalleSubtemaDTO> dtoList = new ArrayList<>();
        entityList.forEach((entity) -> {
            DetalleSubtemaDTO dto = modelMapper.map(entity, DetalleSubtemaDTO.class);
            dtoList.add(dto);
        });

        return dtoList;
    }

    @Override
    public int deleteById(Long id) {
        Optional<DetalleSubtema> found = detalleSubtemaRepository.findById(id);
        if (found.isPresent()) {
            detalleSubtemaRepository.delete(found.get());
            return 0;
        } else {
            return 1;
        }
    }

    public int deleteByNombre(Long id) {
        Optional<DetalleSubtema> found = detalleSubtemaRepository.findById(id);
        if (found.isPresent()) {
            detalleSubtemaRepository.delete(found.get());
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public int updateById(Long id, DetalleSubtemaDTO newData) {
        Optional<DetalleSubtema> found = detalleSubtemaRepository.findById(id);
        Optional<DetalleSubtema> newFound = detalleSubtemaRepository.findById(newData.getId());

        if (found.isPresent() && !newFound.isPresent()) {
            DetalleSubtema temp = found.get();
            temp.setDescripcion(newData.getDescripcion());
            temp.setUrlImagen(newData.getUrlImagen()); 
            detalleSubtemaRepository.save(temp);
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

    public DetalleSubtemaDTO getById(Long id) {
        Optional<DetalleSubtema> found = detalleSubtemaRepository.findById(id);
        if (found.isPresent()) {
            return modelMapper.map(found.get(), DetalleSubtemaDTO.class);
        } else {
            return null;
        }
    }

    public boolean findIdAlreadyTaken(DetalleSubtema newDetalleSubtema) {
        Optional<DetalleSubtema> found = detalleSubtemaRepository.findById(newDetalleSubtema.getId());
        return found.isPresent();
    }
}
