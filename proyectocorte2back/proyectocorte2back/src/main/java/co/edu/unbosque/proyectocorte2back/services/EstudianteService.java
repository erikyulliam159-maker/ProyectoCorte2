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
import co.edu.unbosque.proyectocorte2back.util.ExceptionChecker;

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
		ExceptionChecker.checkNotNullOrEmpty(data.getUsername(), "Username no puede estar vacio");
		ExceptionChecker.checkStringLength(data.getUsername(), 3, 12, "Username min 3 y max 12");
		ExceptionChecker.checkNotNullOrEmpty(data.getPassword(), "Contraseña no puede estar vacio");
		ExceptionChecker.checkStringLength(data.getPassword(), 3, 12, "Contraseña min 3 y max 12");
		ExceptionChecker.checkNotNullOrEmpty(data.getNombreCompleto(), "El nombre no puede estar en vacio");
		ExceptionChecker.checkStringLength(data.getNombreCompleto(), 3, 50, "El nombre Min 3 letras max 50");
		ExceptionChecker.checkOnlyLetters(data.getNombreCompleto(), "Solo letras en el nombre");
		ExceptionChecker.checkNotNullOrEmpty(data.getEmail(), "Email no puede estar vacio");
		ExceptionChecker.checkStringLength(data.getEmail(), 5, 100, "Email min 5 y max 100");

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

		ExceptionChecker.checkNotNullOrEmpty(newData.getUsername(), "Username no puede estar vacio");
		ExceptionChecker.checkStringLength(newData.getUsername(), 3, 12, "Username min 3 y max 12");
		ExceptionChecker.checkNotNullOrEmpty(newData.getPassword(), "Contraseña no puede estar vacio");
		ExceptionChecker.checkStringLength(newData.getPassword(), 3, 12, "Contraseña min 3 y max 12");
		ExceptionChecker.checkOnlyLetters(newData.getNombreCompleto(), "Solo letras en el nombre");
		ExceptionChecker.checkNotNullOrEmpty(newData.getEmail(), "Email no puede estar vacio");
		ExceptionChecker.checkStringLength(newData.getEmail(), 5, 100, "Email min 5 y max 100");

		Optional<Estudiante> found = estudianteRepo.findById(id);
		Optional<Estudiante> newFound = estudianteRepo.findByNombreCompleto(newData.getNombreCompleto());

		if (found.isPresent() && !newFound.isPresent()) {
			Estudiante temp = found.get();
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
