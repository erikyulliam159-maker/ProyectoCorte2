package co.edu.unbosque.proyectocorte2back.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.proyectocorte2back.dto.DetalleSubtemaDTO;
import co.edu.unbosque.proyectocorte2back.dto.SubtemaDTO;
import co.edu.unbosque.proyectocorte2back.entity.DetalleSubtema;
import co.edu.unbosque.proyectocorte2back.entity.Subtema;
import co.edu.unbosque.proyectocorte2back.entity.Temario;
import co.edu.unbosque.proyectocorte2back.repository.SubtemaRepository;
import co.edu.unbosque.proyectocorte2back.repository.TemarioRepository;
import co.edu.unbosque.proyectocorte2back.util.ExceptionChecker;


@Service
public class SubtemaService implements CRUDOperation<SubtemaDTO> {

    @Autowired
    private SubtemaRepository subtemaRepository;

    @Autowired
    private TemarioRepository temarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public long count() {
        return subtemaRepository.count();
    }

    @Override
    public boolean exist(Long id) {
        return subtemaRepository.existsById(id);
    }

    @Override
    public int create(SubtemaDTO data) {
       
        ExceptionChecker.checkNotNullOrEmpty(data.getNombre(), "Nombre del Subtema no puede estar vacio ");
        ExceptionChecker.checkOnlyLetters(data.getNombre(), "Nombre del Subtema solo letras");
        ExceptionChecker.checkStringLength(data.getNombre(), 3, 100, "Nombre del Subtema min 3 y max 100");
      

        if (data.getTipo() != null) {
            ExceptionChecker.checkOnlyLetters(data.getTipo(), "Tipo del Subtema");
        }

        
        if (data.getTemarioTitulo() != null) {
            temarioRepository.findByTitulo(data.getTemarioTitulo())
                    .orElseThrow(() -> new IllegalArgumentException("El temario con título '" + data.getTemarioTitulo() + "' no existe."));
        }

        Subtema entity = modelMapper.map(data, Subtema.class);

        if (data.getDetalle() != null) {
        	
            DetalleSubtema detalleEntity = modelMapper.map(data.getDetalle(), DetalleSubtema.class);
            detalleEntity.setSubtema(entity);
            entity.setDetalle(detalleEntity);
        }

        subtemaRepository.save(entity);
        return 0;
    }

    @Override
    public List<SubtemaDTO> getAll() {
        List<Subtema> entityList = subtemaRepository.findAll();
        List<SubtemaDTO> dtoList = new ArrayList<>();
        entityList.forEach((entity) -> {
            SubtemaDTO dto = modelMapper.map(entity, SubtemaDTO.class);
            dtoList.add(dto);
        });
        return dtoList;
    }

    @Override
    public int deleteById(Long id) {
        Optional<Subtema> found = subtemaRepository.findById(id);
        if (found.isPresent()) {
            subtemaRepository.delete(found.get());
            return 0;
        } else {
            return 1;
        }
    }

    public int deleteByTitulo(String nombre) {
        Optional<Subtema> found = subtemaRepository.findByNombre(nombre);
        if (found.isPresent()) {
            subtemaRepository.delete(found.get());
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public int updateById(Long id, SubtemaDTO newData) {
       
        ExceptionChecker.checkNotNullOrEmpty(newData.getNombre(), "Nombre del Subtema no puede estar vacio");
        ExceptionChecker.checkOnlyLetters(newData.getNombre(), "Nombre del Subtema solo letras");
        ExceptionChecker.checkStringLength(newData.getNombre(), 3, 100, "Nombre del Subtema min 3 y max 100");

        if (newData.getTipo() != null) {
            ExceptionChecker.checkOnlyLetters(newData.getTipo(), "Tipo del Subtema solo letras");
        }

        Optional<Subtema> found = subtemaRepository.findById(id);

        if (found.isPresent()) {
            Subtema existing = found.get();

            existing.setNombre(newData.getNombre());
            existing.setTipo(newData.getTipo());

         
            Optional<Temario> temarioOpt = temarioRepository.findByTitulo(newData.getTemarioTitulo());
            if (temarioOpt.isPresent()) {
                existing.setTemario(temarioOpt.get());
            } else {
                throw new IllegalArgumentException("El temario con título '" + newData.getTemarioTitulo() + "' no existe.");
            }

            if (newData.getDetalle() != null) {
                DetalleSubtema detalleEntity = modelMapper.map(newData.getDetalle(), DetalleSubtema.class);
                detalleEntity.setSubtema(existing);
                existing.setDetalle(detalleEntity);
            } else {
                existing.setDetalle(null);
            }

            subtemaRepository.save(existing);
            return 0;
        } else {
            return 1; // Subtema no encontrado
        }
    }

    public SubtemaDTO getById(Long id) {
        Optional<Subtema> found = subtemaRepository.findById(id);
        if (found.isPresent()) {
            SubtemaDTO dto = modelMapper.map(found.get(), SubtemaDTO.class);

            if (found.get().getDetalle() != null) {
                DetalleSubtemaDTO detalleDTO = modelMapper.map(found.get().getDetalle(), DetalleSubtemaDTO.class);
                dto.setDetalle(detalleDTO);
            }

            return dto;
        } else {
            return null;
        }
    }
}

