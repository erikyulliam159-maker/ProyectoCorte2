/**
 * Clase LinkValiosoService
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

import co.edu.unbosque.proyectocorte2back.dto.LinkValiosoDTO;
import co.edu.unbosque.proyectocorte2back.entity.LinkValioso;
import co.edu.unbosque.proyectocorte2back.repository.LinkValiosoRepository;
import co.edu.unbosque.proyectocorte2back.util.ExceptionChecker;

// TODO: Auto-generated Javadoc
/**
 * The Class LinkValiosoService.
 */
@Service
public class LinkValiosoService implements CRUDOperation<LinkValiosoDTO> {

    /** The link valioso repository. */
    @Autowired
    private LinkValiosoRepository linkValiosoRepository;

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
        return linkValiosoRepository.count();
    }

    /**
     * Exist.
     *
     * @param id the id
     * @return true, if successful
     */
    @Override
    public boolean exist(Long id) {
        return linkValiosoRepository.existsById(id);
    }

    /**
     * Crea el.
     *
     * @param data the data
     * @return the int
     */
    @Override
    public int create(LinkValiosoDTO data) {
        ExceptionChecker.checkNotNullOrEmpty(data.getNombre(), "Nombre del Link");
        ExceptionChecker.checkStringLength(data.getNombre(), 3, 150, "Nombre del Link");
        ExceptionChecker.checkNotNullOrEmpty(data.getUrl(), "URL del Link");
        ExceptionChecker.checkStringLength(data.getUrl(), 3, 500, "URL del Link");
        ExceptionChecker.checkNotNullOrEmpty(data.getTipo(), "Tipo link vacio");
        ExceptionChecker.checkStringLength(data.getTipo(), 3, 100, "Tipo link min 3 y max 100");
        ExceptionChecker.checkOnlyLetters(data.getTipo(), "Solo letras");
       

        LinkValioso entity = modelMapper.map(data, LinkValioso.class);
        if (findTituloAlreadyTaken(entity)) {
            return 1; 
        } else {
            linkValiosoRepository.save(entity);
            return 0; 
        }
    }
    
    /**
     * Gets the all.
     *
     * @return the all
     */
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

    /**
     * Delete by id.
     *
     * @param id the id
     * @return the int
     */
    @Override
    public int deleteById(Long id) {
        Optional<LinkValioso> found = linkValiosoRepository.findById(id);
        if (found.isPresent()) {
            linkValiosoRepository.delete(found.get());
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * Delete by titulo.
     *
     * @param nombre the nombre
     * @return the int
     */
    public int deleteByTitulo(String nombre) {
        Optional<LinkValioso> found = linkValiosoRepository.findByNombre(nombre);
        if (found.isPresent()) {
            linkValiosoRepository.delete(found.get());
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
    public int updateById(Long id, LinkValiosoDTO newData) {
    	
        ExceptionChecker.checkNotNullOrEmpty(newData.getNombre(), "Nombre del Link");
        ExceptionChecker.checkStringLength(newData.getNombre(), 3, 150, "Nombre del Link");
        ExceptionChecker.checkNotNullOrEmpty(newData.getUrl(), "URL del Link");
        ExceptionChecker.checkStringLength(newData.getUrl(), 3, 500, "URL del Link");
        ExceptionChecker.checkNotNullOrEmpty(newData.getTipo(), "Tipo link vacio");
        ExceptionChecker.checkStringLength(newData.getTipo(), 3, 100, "Tipo link min 3 y max 100");
        ExceptionChecker.checkOnlyLetters(newData.getTipo(), "Solo letras");
      

        Optional<LinkValioso> found = linkValiosoRepository.findById(id);
        Optional<LinkValioso> newFound = linkValiosoRepository.findByNombre(newData.getNombre());

        if (found.isPresent() && !newFound.isPresent()) {
            LinkValioso temp = found.get();
            temp.setImagen(newData.getImagen());
            temp.setTipo(newData.getTipo());
            temp.setUrl(newData.getUrl());
            linkValiosoRepository.save(temp);
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
    public LinkValiosoDTO getById(Long id) {
        Optional<LinkValioso> found = linkValiosoRepository.findById(id);
        if (found.isPresent()) {
            return modelMapper.map(found.get(), LinkValiosoDTO.class);
        } else {
            return null;
        }
    }

    /**
     * Find titulo already taken.
     *
     * @param newLink the new link
     * @return true, if successful
     */
    public boolean findTituloAlreadyTaken(LinkValioso newLink) {
        Optional<LinkValioso> found = linkValiosoRepository.findByNombre(newLink.getNombre());
        return found.isPresent();
    }
}
