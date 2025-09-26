/**
 * Clase DocenteService
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

import co.edu.unbosque.proyectocorte2back.dto.DocenteDTO;
import co.edu.unbosque.proyectocorte2back.entity.Docente;
import co.edu.unbosque.proyectocorte2back.repository.DocenteRepository;
import co.edu.unbosque.proyectocorte2back.util.ExceptionChecker;

// TODO: Auto-generated Javadoc
/**
 * The Class DocenteService.
 */
@Service
public class DocenteService implements CRUDOperation<DocenteDTO> {

    /** The docente repository. */
    @Autowired
    private DocenteRepository docenteRepository;

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
        return docenteRepository.count();
    }

    /**
     * Exist.
     *
     * @param id the id
     * @return true, if successful
     */
    @Override
    public boolean exist(Long id) {
        return docenteRepository.existsById(id);
    }

    /**
     * Crea el.
     *
     * @param data the data
     * @return the int
     */
    @Override
    public int create(DocenteDTO data) {
		ExceptionChecker.checkNotNullOrEmpty(data.getUsername(), "Username no puede estar vacio");
		ExceptionChecker.checkStringLength(data.getUsername(), 3, 12, "Username min 3 y max 12");
		ExceptionChecker.checkNotNullOrEmpty(data.getPassword(), "Contraseña no puede estar vacio");
		ExceptionChecker.checkStringLength(data.getPassword(), 3, 12, "Contraseña min 3 y max 12");
		ExceptionChecker.checkNotNullOrEmpty(data.getNombreCompleto(), "El nombre no puede estar en vacio");
		ExceptionChecker.checkStringLength(data.getNombreCompleto(), 3, 50, "El nombre Min 3 letras max 50");
		ExceptionChecker.checkOnlyLetters(data.getNombreCompleto(), "Solo letras en el nombre");
		ExceptionChecker.checkNotNullOrEmpty(data.getEmail(), "Email no puede estar vacio");
		ExceptionChecker.checkStringLength(data.getEmail(), 5, 100, "Email min 5 y max 100");
		
        Docente entity = modelMapper.map(data, Docente.class);
        if (findNombreAlreadyTaken(entity)) {
            return 1;
        } else {
            docenteRepository.save(entity);
            return 0;
        }
    }

    /**
     * Gets the all.
     *
     * @return the all
     */
    @Override
    public List<DocenteDTO> getAll() {
        List<Docente> entityList = docenteRepository.findAll();
        List<DocenteDTO> dtoList = new ArrayList<>();
        entityList.forEach((entity) -> {
            DocenteDTO dto = modelMapper.map(entity, DocenteDTO.class);
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
        Optional<Docente> found = docenteRepository.findById(id);
        if (found.isPresent()) {
            docenteRepository.delete(found.get());
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * Delete by nombre.
     *
     * @param nombreCompleto the nombre completo
     * @return the int
     */
    public int deleteByNombre(String nombreCompleto) {
        Optional<Docente> found = docenteRepository.findByNombreCompleto(nombreCompleto);
        if (found.isPresent()) {
            docenteRepository.delete(found.get());
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
    public int updateById(Long id, DocenteDTO newData) {
        ExceptionChecker.checkNotNullOrEmpty(newData.getUsername(), "Username no puede estar vacio");
        ExceptionChecker.checkStringLength(newData.getUsername(), 3, 12, "Username min 3 y max 12");
        ExceptionChecker.checkNotNullOrEmpty(newData.getPassword(), "Contraseña no puede estar vacio");
        ExceptionChecker.checkStringLength(newData.getPassword(), 3, 12, "Contraseña min 3 y max 12");
        ExceptionChecker.checkOnlyLetters(newData.getNombreCompleto(), "Solo letras en el nombre");
        ExceptionChecker.checkNotNullOrEmpty(newData.getEmail(), "Email no puede estar vacio");
        ExceptionChecker.checkStringLength(newData.getEmail(), 5, 100, "Email min 5 y max 100");

        Optional<Docente> found = docenteRepository.findById(id);

        if (found.isPresent()) {
        	Docente temp = found.get();
            // temp.setNombreCompleto(newData.getNombreCompleto()); // NO actualizar nombre
            temp.setEmail(newData.getEmail());
            temp.setFotoPerfil(newData.getFotoPerfil());
            temp.setPassword(newData.getPassword());
            temp.setUsername(newData.getUsername());
            docenteRepository.save(temp);
            return 0;
        }
        return 2;
    }

    /**
     * Gets the by id.
     *
     * @param id the id
     * @return the by id
     */
    public DocenteDTO getById(Long id) {
        Optional<Docente> found = docenteRepository.findById(id);
        if (found.isPresent()) {
            return modelMapper.map(found.get(), DocenteDTO.class);
        } else {
            return null;
        }
    }

    /**
     * Find nombre already taken.
     *
     * @param newDocente the new docente
     * @return true, if successful
     */
    public boolean findNombreAlreadyTaken(Docente newDocente) {
        Optional<Docente> found = docenteRepository.findByNombreCompleto(newDocente.getNombreCompleto());
        return found.isPresent();
    }
    
    /**
     * Validate credentials.
     *
     * @param username the username
     * @param password the password
     * @return the int
     */
    public int validateCredentials(String username, String password) {
		// encriptado del front
		// username = AESUtil.decrypt("keyfrontfirstenc", "iviviviviviviviv", username);
		// password = AESUtil.decrypt("keyfrontfirstenc", "iviviviviviviviv", password);
		// a encriptrado del back
		// username = AESUtil.encrypt(username);
		// password = AESUtil.encrypt(password);
		for (DocenteDTO u : getAll()) {
			if (u.getUsername().equals(username)) {
				if (u.getPassword().equals(password)) {
					return 0;
				}
			}
		}
		return 1;
	}
}
