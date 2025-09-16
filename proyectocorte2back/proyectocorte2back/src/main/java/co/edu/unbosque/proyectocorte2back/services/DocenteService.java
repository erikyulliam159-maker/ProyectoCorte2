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

@Service
public class DocenteService implements CRUDOperation<DocenteDTO> {

    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public long count() {
        return docenteRepository.count();
    }

    @Override
    public boolean exist(Long id) {
        return docenteRepository.existsById(id);
    }

    @Override
    public int create(DocenteDTO data) {
        Docente entity = modelMapper.map(data, Docente.class);
        if (findNombreAlreadyTaken(entity)) {
            return 1;
        } else {
            docenteRepository.save(entity);
            return 0;
        }
    }

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

    public int deleteByNombre(String nombreCompleto) {
        Optional<Docente> found = docenteRepository.findByNombreCompleto(nombreCompleto);
        if (found.isPresent()) {
            docenteRepository.delete(found.get());
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public int updateById(Long id, DocenteDTO newData) {
        Optional<Docente> found = docenteRepository.findById(id);
        Optional<Docente> newFound = docenteRepository.findByNombreCompleto(newData.getNombreCompleto());

        if (found.isPresent() && !newFound.isPresent()) {
            Docente temp = found.get();
            temp.setNombreCompleto(newData.getNombreCompleto());
            temp.setEmail(newData.getEmail());
            temp.setFotoPerfil(newData.getFotoPerfil());
            temp.setPassword(newData.getPassword());
            temp.setUsername(newData.getUsername());
            docenteRepository.save(temp);
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

    public DocenteDTO getById(Long id) {
        Optional<Docente> found = docenteRepository.findById(id);
        if (found.isPresent()) {
            return modelMapper.map(found.get(), DocenteDTO.class);
        } else {
            return null;
        }
    }

    public boolean findNombreAlreadyTaken(Docente newDocente) {
        Optional<Docente> found = docenteRepository.findByNombreCompleto(newDocente.getNombreCompleto());
        return found.isPresent();
    }
    
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
