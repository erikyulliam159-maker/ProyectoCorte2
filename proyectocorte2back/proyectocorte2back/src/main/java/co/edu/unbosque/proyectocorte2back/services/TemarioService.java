package co.edu.unbosque.proyectocorte2back.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.proyectocorte2back.dto.SubtemaDTO;
import co.edu.unbosque.proyectocorte2back.dto.TemarioDTO;
import co.edu.unbosque.proyectocorte2back.entity.Subtema;
import co.edu.unbosque.proyectocorte2back.entity.Temario;
import co.edu.unbosque.proyectocorte2back.repository.TemarioRepository;

@Service
public class TemarioService implements CRUDOperation<TemarioDTO> {

	@Autowired
	private TemarioRepository temarioRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public long count() {
		return temarioRepository.count();
	}

	@Override
	public boolean exist(Long id) {
		return temarioRepository.existsById(id);
	}

	@Override
	public int create(TemarioDTO data) {
		Temario entity = modelMapper.map(data, Temario.class);

		if (entity.getSubtemas() != null) {
			entity.getSubtemas().forEach(s -> {
				s.setTemario(entity);
				if (s.getDetalle() != null) {
					s.getDetalle().setSubtema(s);
				}
			});
		}

		if (data.getSubtemas() != null && !data.getSubtemas().isEmpty()) {
			List<Subtema> subtemasEntity = new ArrayList<>();
			for (SubtemaDTO subtemaDTO : data.getSubtemas()) {
				Subtema subtema = modelMapper.map(subtemaDTO, Subtema.class);
				subtema.setTemario(entity);
				subtemasEntity.add(subtema);
			}
			entity.setSubtemas(subtemasEntity);
		}

		temarioRepository.save(entity);
		return 0;
	}

	@Override
	public List<TemarioDTO> getAll() {
		List<Temario> entityList = temarioRepository.findAll();
		List<TemarioDTO> dtoList = new ArrayList<>();
		entityList.forEach((entity) -> {
			TemarioDTO dto = modelMapper.map(entity, TemarioDTO.class);
			dtoList.add(dto);
		});
		return dtoList;
	}

	@Override
	public int deleteById(Long id) {
		Optional<Temario> found = temarioRepository.findById(id);
		if (found.isPresent()) {
			temarioRepository.delete(found.get());
			return 0;
		} else {
			return 1;
		}
	}

	@Override
	public int updateById(Long id, TemarioDTO newData) {
		Optional<Temario> found = temarioRepository.findById(id);

		if (found.isPresent()) {
			Temario existing = found.get();

			existing.setTitulo(newData.getTitulo());

			if (newData.getSubtemas() != null && !newData.getSubtemas().isEmpty()) {
				List<Subtema> subtemasEntity = new ArrayList<>();
				for (SubtemaDTO subtemaDTO : newData.getSubtemas()) {
					Subtema subtema = modelMapper.map(subtemaDTO, Subtema.class);
					subtema.setTemario(existing);
					subtemasEntity.add(subtema);
				}
				existing.setSubtemas(subtemasEntity);
			} else {
				existing.setSubtemas(null);
			}

			temarioRepository.save(existing);
			return 0;
		} else {
			return 1;
		}
	}

	public TemarioDTO getById(Long id) {
		Optional<Temario> found = temarioRepository.findById(id);
		if (found.isPresent()) {
			TemarioDTO dto = modelMapper.map(found.get(), TemarioDTO.class);

			if (found.get().getSubtemas() != null && !found.get().getSubtemas().isEmpty()) {
				List<SubtemaDTO> subtemasDTO = new ArrayList<>();
				for (Subtema subtema : found.get().getSubtemas()) {
					SubtemaDTO subtemaDTO = modelMapper.map(subtema, SubtemaDTO.class);
					subtemasDTO.add(subtemaDTO);
				}
				dto.setSubtemas(subtemasDTO);
			}

			return dto;
		} else {
			return null;
		}
	}

	public int create(String titulo, String subtemas) {
		// TODO Auto-generated method stub
		return 0;
	}
}
