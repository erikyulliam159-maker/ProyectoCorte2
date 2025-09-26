/**
 * Clase DetalleSubtemaService
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

import co.edu.unbosque.proyectocorte2back.dto.DetalleSubtemaDTO;
import co.edu.unbosque.proyectocorte2back.entity.DetalleSubtema;
import co.edu.unbosque.proyectocorte2back.repository.DetalleSubtemaRepository;
import co.edu.unbosque.proyectocorte2back.util.ExceptionChecker;

// TODO: Auto-generated Javadoc
/**
 * The Class DetalleSubtemaService.
 */
@Service
public class DetalleSubtemaService implements CRUDOperation<DetalleSubtemaDTO> {

    /** The detalle subtema repository. */
    @Autowired
    private DetalleSubtemaRepository detalleSubtemaRepository;

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
        return detalleSubtemaRepository.count();
    }

    /**
     * Exist.
     *
     * @param id the id
     * @return true, if successful
     */
    @Override
    public boolean exist(Long id) {
        return detalleSubtemaRepository.existsById(id);
    }

    /**
     * Crea el.
     *
     * @param data the data
     * @return the int
     */
    public int create(DetalleSubtemaDTO data) {
        ExceptionChecker.checkNotNullOrEmpty(data.getDescripcion(), "Descripción del Detalle no puede estar");
        ExceptionChecker.checkStringLength(data.getDescripcion(), 3, 1000, "Descripción del Detalle min 3 y 1000");
        ExceptionChecker.checkNotNullOrEmpty(data.getUrlImagen(), "Url Imagen no puede estar vacia");

        
        DetalleSubtema entity = modelMapper.map(data, DetalleSubtema.class);
        if (findIdAlreadyTaken(entity)) {
            return 1;
        } else {
            detalleSubtemaRepository.save(entity);
            return 0;
        }
    }
    
    /**
     * Gets the all.
     *
     * @return the all
     */
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

    /**
     * Delete by id.
     *
     * @param id the id
     * @return the int
     */
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

    /**
     * Delete by nombre.
     *
     * @param id the id
     * @return the int
     */
    public int deleteByNombre(Long id) {
        Optional<DetalleSubtema> found = detalleSubtemaRepository.findById(id);
        if (found.isPresent()) {
            detalleSubtemaRepository.delete(found.get());
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
    public int updateById(Long id, DetalleSubtemaDTO newData) {
        ExceptionChecker.checkNotNullOrEmpty(newData.getDescripcion(), "Descripción del Detalle no puede estar");
        ExceptionChecker.checkStringLength(newData.getDescripcion(), 3, 1000, "Descripción del Detalle min 3 y 1000");
        ExceptionChecker.checkNotNullOrEmpty(newData.getUrlImagen(), "Url Imagen no puede estar vacia");



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

    /**
     * Gets the by id.
     *
     * @param id the id
     * @return the by id
     */
    public DetalleSubtemaDTO getById(Long id) {
        Optional<DetalleSubtema> found = detalleSubtemaRepository.findById(id);
        if (found.isPresent()) {
            return modelMapper.map(found.get(), DetalleSubtemaDTO.class);
        } else {
            return null;
        }
    }

    /**
     * Find id already taken.
     *
     * @param newDetalleSubtema the new detalle subtema
     * @return true, if successful
     */
    public boolean findIdAlreadyTaken(DetalleSubtema newDetalleSubtema) {
        Optional<DetalleSubtema> found = detalleSubtemaRepository.findById(newDetalleSubtema.getId());
        return found.isPresent();
    }
}
