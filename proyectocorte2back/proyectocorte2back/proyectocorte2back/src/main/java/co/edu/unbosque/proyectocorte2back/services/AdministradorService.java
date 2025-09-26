/**
 * Clase AdministradorService
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

import co.edu.unbosque.proyectocorte2back.dto.AdministradorDTO;
import co.edu.unbosque.proyectocorte2back.entity.Administrador;
import co.edu.unbosque.proyectocorte2back.repository.AdminRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class AdministradorService.
 */
@Service
public class AdministradorService implements CRUDOperation<AdministradorDTO> {

	/** The admin repository. */
	@Autowired
	private AdminRepository adminRepository;

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
		return adminRepository.count();
	}

	/**
	 * Crea el.
	 *
	 * @param data the data
	 * @return the int
	 */
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

	/**
	 * Exist.
	 *
	 * @param id the id
	 * @return true, if successful
	 */
	@Override
	public boolean exist(Long id) {
		return adminRepository.existsById(id) ? true : false;
	}

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
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

	/**
	 * Delete by id.
	 *
	 * @param id the id
	 * @return the int
	 */
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

	/**
	 * Delete by username.
	 *
	 * @param nombreCompleto the nombre completo
	 * @return the int
	 */
	public int deleteByUsername(String nombreCompleto) {
		Optional<Administrador> found = adminRepository.findByNombreCompleto(nombreCompleto);
		if (found.isPresent()) {
			adminRepository.delete(found.get());
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

	/**
	 * Gets the by id.
	 *
	 * @param id the id
	 * @return the by id
	 */
	public AdministradorDTO getById(Long id) {
		Optional<Administrador> found = adminRepository.findById(id);
		if (found.isPresent()) {
			return modelMapper.map(found.get(), AdministradorDTO.class);
		} else {
			return null;
		}
	}

	/**
	 * Find username already taken.
	 *
	 * @param newAdmin the new admin
	 * @return true, if successful
	 */
	public boolean findUsernameAlreadyTaken(Administrador newAdmin) {
		Optional<Administrador> found = adminRepository.findByNombreCompleto(newAdmin.getNombreCompleto());
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
