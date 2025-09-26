/**
 * Clase ProblemaService
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

import co.edu.unbosque.proyectocorte2back.dto.ProblemaDTO;
import co.edu.unbosque.proyectocorte2back.entity.Problema;
import co.edu.unbosque.proyectocorte2back.repository.ProblemaRepository;
import co.edu.unbosque.proyectocorte2back.util.ExceptionChecker;

// TODO: Auto-generated Javadoc
/**
 * The Class ProblemaService.
 */
@Service
public class ProblemaService implements CRUDOperation<ProblemaDTO> {

    /** The problema repository. */
    @Autowired
    private ProblemaRepository problemaRepository;

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
        return problemaRepository.count();
    }

    /**
     * Exist.
     *
     * @param id the id
     * @return true, if successful
     */
    @Override
    public boolean exist(Long id) {
        return problemaRepository.existsById(id);
    }

    /**
     * Crea el.
     *
     * @param data the data
     * @return the int
     */
    @Override
    public int create(ProblemaDTO data) {

       
        ExceptionChecker.checkNotNullOrEmpty(data.getTitulo(), "Título del Problema no puede estar vacio");
        ExceptionChecker.checkStringLength(data.getTitulo(), 3, 150, "Título del Problema min 3 y max 100");
        ExceptionChecker.checkOnlyLetters(data.getTitulo(),"Título del Problema solo letras");
        ExceptionChecker.checkNotNullOrEmpty(data.getTema(), "Tema del Problema no puede estar vacio");
        ExceptionChecker.checkOnlyLetters(data.getTema(), "Tema del Problema solo letras");
        ExceptionChecker.checkStringLength(data.getTema(), 3, 100, "Tema del Problema min 3 y max 100");
        ExceptionChecker.checkNotNullOrEmpty(data.getJuez(), "Juez del Problema no puede estar vacio");
        ExceptionChecker.checkStringLength(data.getJuez(), 3, 100, "Juez del Problema min 3 y max 100");
        ExceptionChecker.checkOnlyLetters(data.getJuez(),"Juez del Problema solo letras");
        ExceptionChecker.checkNotNullOrEmpty(data.getUrl(), "URL del Problema no puede estar vacio");

        Problema entity = modelMapper.map(data, Problema.class);

        if (findTituloAlreadyTaken(entity)) {
            return 1;
        } else {
            problemaRepository.save(entity);
            return 0;
        }
    }

    /**
     * Gets the all.
     *
     * @return the all
     */
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

    /**
     * Delete by id.
     *
     * @param id the id
     * @return the int
     */
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

    /**
     * Delete by titulo.
     *
     * @param titulo the titulo
     * @return the int
     */
    public int deleteByTitulo(String titulo) {
        Optional<Problema> found = problemaRepository.findByTitulo(titulo);
        if (found.isPresent()) {
            problemaRepository.delete(found.get());
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
    public int updateById(Long id, ProblemaDTO newData) {


    	 ExceptionChecker.checkNotNullOrEmpty(newData.getTitulo(), "Título del Problema no puede estar vacio");
         ExceptionChecker.checkStringLength(newData.getTitulo(), 3, 150, "Título del Problema min 3 y max 100");
         ExceptionChecker.checkOnlyLetters(newData.getTitulo(),"Título del Problema solo letras");
         ExceptionChecker.checkNotNullOrEmpty(newData.getTema(), "Tema del Problema no puede estar vacio");
         ExceptionChecker.checkOnlyLetters(newData.getTema(), "Tema del Problema solo letras");
         ExceptionChecker.checkStringLength(newData.getTema(), 3, 100, "Tema del Problema min 3 y max 100");
         ExceptionChecker.checkNotNullOrEmpty(newData.getJuez(), "Juez del Problema no puede estar vacio");
         ExceptionChecker.checkStringLength(newData.getJuez(), 3, 100, "Juez del Problema min 3 y max 100");
         ExceptionChecker.checkOnlyLetters(newData.getJuez(),"Juez del Problema solo letras");
         ExceptionChecker.checkNotNullOrEmpty(newData.getUrl(), "URL del Problema no puede estar vacio");

        Optional<Problema> found = problemaRepository.findById(id);
        Optional<Problema> newFound = problemaRepository.findByTitulo(newData.getTitulo());

        if (found.isPresent() && !newFound.isPresent()) {
            Problema temp = found.get();
            temp.setDificultad(newData.getDificultad());
            temp.setJuez(newData.getJuez());
            temp.setTema(newData.getTema());
            temp.setUrl(newData.getUrl());
            temp.setTitulo(newData.getTitulo());
            problemaRepository.save(temp);
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
    public ProblemaDTO getById(Long id) {
        Optional<Problema> found = problemaRepository.findById(id);
        if (found.isPresent()) {
            return modelMapper.map(found.get(), ProblemaDTO.class);
        } else {
            return null;
        }
    }

    /**
     * Find titulo already taken.
     *
     * @param newProblema the new problema
     * @return true, if successful
     */
    public boolean findTituloAlreadyTaken(Problema newProblema) {
        Optional<Problema> found = problemaRepository.findByTitulo(newProblema.getTitulo());
        return found.isPresent();
    }
}
