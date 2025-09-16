package co.edu.unbosque.proyectocorte2back.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.proyectocorte2back.dto.EstudianteDTO;
import co.edu.unbosque.proyectocorte2back.entity.Estudiante;
import co.edu.unbosque.proyectocorte2back.repository.EstudianteRepository;
@Service
public class EstudianteService implements CRUDOperation<EstudianteDTO> {

	@Autowired
	private EstudianteRepository estudianteRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public long count() {
		return estudianteRepo.count();
	}

	@Override
	public boolean exist(Long id) {
		return estudianteRepo.existsById(id);
	}

	@Override
	public int create(EstudianteDTO data) {
		Estudiante entity = modelMapper.map(data, Estudiante.class);
		if (findNombreAlreadyTaken(entity)) {
			return 1;
		} else {
			estudianteRepo.save(entity);
			return 0;
		}
	}

	@Override
	public List<EstudianteDTO> getAll() {
		List<Estudiante> entityList = estudianteRepo.findAll();
		List<EstudianteDTO> dtoList = new ArrayList<>();
		entityList.forEach((entity) -> {
			EstudianteDTO dto = modelMapper.map(entity, EstudianteDTO.class);
			dtoList.add(dto);
		});

		return dtoList;
	}

	@Override
	public int deleteById(Long id) {
		Optional<Estudiante> found = estudianteRepo.findById(id);
		if (found.isPresent()) {
			estudianteRepo.delete(found.get());
			return 0;
		} else {
			return 1;
		}
	}

	public int deleteByNombre(String nombreCompleto) {
		Optional<Estudiante> found = estudianteRepo.findByNombreCompleto(nombreCompleto);
		if (found.isPresent()) {
			estudianteRepo.delete(found.get());
			return 0;
		} else {
			return 1;
		}
	}

	@Override
	public int updateById(Long id, EstudianteDTO newData) {
		Optional<Estudiante> found = estudianteRepo.findById(id);
		Optional<Estudiante> newFound = estudianteRepo.findByNombreCompleto(newData.getNombreCompleto());

		if (found.isPresent() && !newFound.isPresent()) {
			Estudiante temp = found.get();
			temp.setNombreCompleto(newData.getNombreCompleto());
			temp.setEmail(newData.getEmail());
			temp.setFotoPerfil(newData.getFotoPerfil());
			temp.setPassword(newData.getPassword());
			temp.setUsername(newData.getUsername());
			estudianteRepo.save(temp);
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

	public EstudianteDTO getById(Long id) {
		Optional<Estudiante> found = estudianteRepo.findById(id);
		if (found.isPresent()) {
			return modelMapper.map(found.get(), EstudianteDTO.class);
		} else {
			return null;
		}
	}

	public boolean findNombreAlreadyTaken(Estudiante newEstudiante) {
		Optional<Estudiante> found = estudianteRepo.findByNombreCompleto(newEstudiante.getNombreCompleto());
		return found.isPresent();
	}

	public int validateCredentials(String username, String password) {
		// encriptado del front
		// username = AESUtil.decrypt("keyfrontfirstenc", "iviviviviviviviv", username);
		// password = AESUtil.decrypt("keyfrontfirstenc", "iviviviviviviviv", password);
		// a encriptrado del back
		// username = AESUtil.encrypt(username);
		// password = AESUtil.encrypt(password);
		for (EstudianteDTO u : getAll()) {
			if (u.getUsername().equals(username)) {
				if (u.getPassword().equals(password)) {
					return 0;
				}
			}
		}
		return 1;
	}
}
