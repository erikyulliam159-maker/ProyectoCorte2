package co.edu.unbosque.proyectocorte2back.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.proyectocorte2back.dto.LinkValiosoDTO;
import co.edu.unbosque.proyectocorte2back.entity.LinkValioso;
import co.edu.unbosque.proyectocorte2back.repository.LinkValiosoRepository;

@Service
public class LinkValiosoService implements CRUDOperation<LinkValiosoDTO> {

    @Autowired
    private LinkValiosoRepository linkValiosoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public long count() {
        return linkValiosoRepository.count();
    }

    @Override
    public boolean exist(Long id) {
        return linkValiosoRepository.existsById(id);
    }

    @Override
    public int create(LinkValiosoDTO data) {
        LinkValioso entity = modelMapper.map(data, LinkValioso.class);
        if (findTituloAlreadyTaken(entity)) {
            return 1; // título ya existe
        } else {
            linkValiosoRepository.save(entity);
            return 0; // creado
        }
    }

    @Override
    public List<LinkValiosoDTO> getAll() {
        List<LinkValioso> entityList = linkValiosoRepository.findAll();
        List<LinkValiosoDTO> dtoList = new ArrayList<>();
        entityList.forEach((entity) -> {
            LinkValiosoDTO dto = modelMapper.map(entity, LinkValiosoDTO.class);
            dtoList.add(dto);
        });
        return dtoList;
    }

    @Override
    public int deleteById(Long id) {
        Optional<LinkValioso> found = linkValiosoRepository.findById(id);
        if (found.isPresent()) {
            linkValiosoRepository.delete(found.get());
            return 0;
        } else {
            return 1; // no encontrado
        }
    }

    public int deleteByTitulo(String nombre) {
        Optional<LinkValioso> found = linkValiosoRepository.findByNombre(nombre);
        if (found.isPresent()) {
            linkValiosoRepository.delete(found.get());
            return 0;
        } else {
            return 1; // no encontrado
        }
    }

    @Override
    public int updateById(Long id, LinkValiosoDTO newData) {
        Optional<LinkValioso> found = linkValiosoRepository.findById(id);
        Optional<LinkValioso> newFound = linkValiosoRepository.findByNombre(newData.getNombre());

        if (found.isPresent() && !newFound.isPresent()) {
            LinkValioso temp = found.get();
            temp.setImagen(newData.getImagen());
            temp.setTipo(newData.getTipo());
            temp.setUrl(newData.getUrl()); // Ajusta según los atributos reales de tu entidad
            linkValiosoRepository.save(temp);
            return 0;
        }
        if (found.isPresent() && newFound.isPresent()) {
            return 1; // título ya existe
        }
        if (!found.isPresent()) {
            return 2; // no encontrado
        } else {
            return 3; // error genérico
        }
    }

    public LinkValiosoDTO getById(Long id) {
        Optional<LinkValioso> found = linkValiosoRepository.findById(id);
        if (found.isPresent()) {
            return modelMapper.map(found.get(), LinkValiosoDTO.class);
        } else {
            return null;
        }
    }

    public boolean findTituloAlreadyTaken(LinkValioso newLink) {
        Optional<LinkValioso> found = linkValiosoRepository.findByNombre(newLink.getNombre());
        return found.isPresent();
    }
}
