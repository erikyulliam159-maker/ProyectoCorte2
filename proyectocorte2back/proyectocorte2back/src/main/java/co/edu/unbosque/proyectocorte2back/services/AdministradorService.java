package co.edu.unbosque.proyectocorte2back.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.proyectocorte2back.dto.AdministradorDTO;
import co.edu.unbosque.proyectocorte2back.entity.Administrador;
import co.edu.unbosque.proyectocorte2back.repository.AdminRepository;

@Service
public class AdministradorService implements CRUDOperation<AdministradorDTO> {

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public long count() {
		return adminRepository.count();
	}

	@Override
	public int create(AdministradorDTO data) {
		Administrador entity = modelMapper.map(data, Administrador.class);
		if (findUsernameAlreadyTaken(entity)) {
			return 1;
		} else {
			adminRepository.save(entity);
			return 0;
		}
	}

	@Override
	public boolean exist(Long id) {
		return adminRepository.existsById(id) ? true : false;
	}

	@Override
	public List<AdministradorDTO> getAll() {
		List<Administrador> entityList = adminRepository.findAll();
		List<AdministradorDTO> dtoList = new ArrayList<>();
		entityList.forEach((entity) -> {
			AdministradorDTO dto = modelMapper.map(entity, AdministradorDTO.class);
			dtoList.add(dto);
		});

		return dtoList;
	}

	@Override
	public int deleteById(Long id) {
		Optional<Administrador> found = adminRepository.findById(id);
		if (found.isPresent()) {
			adminRepository.delete(found.get());
			return 0;
		} else {
			return 1;
		}
	}

	public int deleteByUsername(String nombreCompleto) {
		Optional<Administrador> found = adminRepository.findByNombreCompleto(nombreCompleto);
		if (found.isPresent()) {
			adminRepository.delete(found.get());
			return 0;
		} else {
			return 1;
		}
	}

	@Override
	public int updateById(Long id, AdministradorDTO newData) {
		Optional<Administrador> found = adminRepository.findById(id);
		Optional<Administrador> newFound = adminRepository.findByNombreCompleto(newData.getNombreCompleto());

		if (found.isPresent() && !newFound.isPresent()) {
			Administrador temp = found.get();
			temp.setNombreCompleto(newData.getNombreCompleto());
			temp.setEmail(newData.getEmail());
			temp.setFotoPerfil(newData.getFotoPerfil());
			temp.setPassword(newData.getPassword());
			temp.setUsername(newData.getUsername());
			adminRepository.save(temp);
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

	public AdministradorDTO getById(Long id) {
		Optional<Administrador> found = adminRepository.findById(id);
		if (found.isPresent()) {
			return modelMapper.map(found.get(), AdministradorDTO.class);
		} else {
			return null;
		}
	}

	public boolean findUsernameAlreadyTaken(Administrador newAdmin) {
		Optional<Administrador> found = adminRepository.findByNombreCompleto(newAdmin.getNombreCompleto());
		return found.isPresent();
	}

	public int validateCredentials(String username, String password) {
		for (AdministradorDTO u : getAll()) {
			if (u.getUsername().equals(username)) {
				if (u.getPassword().equals(password)) {
					return 0;
				}
			}
		}
		return 1;
	}
}
