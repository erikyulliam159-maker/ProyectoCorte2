package co.edu.unbosque.proyectocorte2back.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.proyectocorte2back.dto.ProblemaDTO;
import co.edu.unbosque.proyectocorte2back.entity.Problema;
import co.edu.unbosque.proyectocorte2back.repository.ProblemaRepository;

@Service
public class ProblemaService implements CRUDOperation<ProblemaDTO> {

    @Autowired
    private ProblemaRepository problemaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public long count() {
        return problemaRepository.count();
    }

    @Override
    public boolean exist(Long id) {
        return problemaRepository.existsById(id);
    }

    @Override
    public int create(ProblemaDTO data) {
        Problema entity = modelMapper.map(data, Problema.class);
        if (findTituloAlreadyTaken(entity)) {
            return 1;
        } else {
            problemaRepository.save(entity);
            return 0;
        }
    }

    @Override
    public List<ProblemaDTO> getAll() {
        List<Problema> entityList = problemaRepository.findAll();
        List<ProblemaDTO> dtoList = new ArrayList<>();
        entityList.forEach((entity) -> {
            ProblemaDTO dto = modelMapper.map(entity, ProblemaDTO.class);
            dtoList.add(dto);
        });
        return dtoList;
    }

    @Override
    public int deleteById(Long id) {
        Optional<Problema> found = problemaRepository.findById(id);
        if (found.isPresent()) {
            problemaRepository.delete(found.get());
            return 0;
        } else {
            return 1;
        }
    }

    public int deleteByTitulo(String titulo) {
        Optional<Problema> found = problemaRepository.findByTitulo(titulo);
        if (found.isPresent()) {
            problemaRepository.delete(found.get());
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public int updateById(Long id, ProblemaDTO newData) {
        Optional<Problema> found = problemaRepository.findById(id);
        Optional<Problema> newFound = problemaRepository.findByTitulo(newData.getTitulo());

        if (found.isPresent() && !newFound.isPresent()) {
            Problema temp = found.get();
            temp.setDificultad(newData.getDificultad());
            temp.setJuez(newData.getJuez());
            temp.setTema(newData.getTema());
            temp.setUrl(newData.getUrl());
            problemaRepository.save(temp);
            return 0;
        }
        if (found.isPresent() && newFound.isPresent()) {
            return 1; 
        }
        if (!found.isPresent()) {
            return 2; // no encontrado
        } else {
            return 3; // error genérico
        }
    }

    public ProblemaDTO getById(Long id) {
        Optional<Problema> found = problemaRepository.findById(id);
        if (found.isPresent()) {
            return modelMapper.map(found.get(), ProblemaDTO.class);
        } else {
            return null;
        }
    }

    public boolean findTituloAlreadyTaken(Problema newProblema) {
        Optional<Problema> found = problemaRepository.findByTitulo(newProblema.getTitulo());
        return found.isPresent();
    }
}
