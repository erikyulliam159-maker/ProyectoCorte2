package co.edu.unbosque.proyectocorte2back.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.proyectocorte2back.dto.LibroDTO;
import co.edu.unbosque.proyectocorte2back.entity.Libro;
import co.edu.unbosque.proyectocorte2back.repository.LibroRepository;
import co.edu.unbosque.proyectocorte2back.util.ExceptionChecker;

@Service
public class LibroService implements CRUDOperation<LibroDTO> {

	@Autowired
	private LibroRepository libroRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public long count() {
		return libroRepository.count();
	}

	@Override
	public boolean exist(Long id) {
		return libroRepository.existsById(id);
	}

	@Override
	public int create(LibroDTO data) {
		ExceptionChecker.checkNotNullOrEmpty(data.getTitulo(), "Título del Libro");
		ExceptionChecker.checkStringLength(data.getTitulo(), 3, 150, "Título del Libro");
		ExceptionChecker.checkOnlyLetters(data.getTitulo(), "Titulo del libro solo letras");
		ExceptionChecker.checkNotNullOrEmpty(data.getAutor(), "Autor del Libro");
		ExceptionChecker.checkStringLength(data.getAutor(), 3, 100, "Autor del Libro");
		ExceptionChecker.checkOnlyLetters(data.getAutor(), "Autor del libro solo letras");
		ExceptionChecker.checkNotNullOrEmpty(data.getDescripcion(), "Descripción del Libro");
		ExceptionChecker.checkStringLength(data.getDescripcion(), 3, 1000, "Descripción del Libro");
		ExceptionChecker.checkPositiveInt(data.getAnio(), "Año solo numeros positivos");

		Libro entity = modelMapper.map(data, Libro.class);
		if (findTituloAlreadyTaken(entity)) {
			return 1;
		} else {
			libroRepository.save(entity);
			return 0;
		}
	}

	@Override
	public List<LibroDTO> getAll() {
		List<Libro> entityList = libroRepository.findAll();
		List<LibroDTO> dtoList = new ArrayList<>();
		entityList.forEach((entity) -> {
			LibroDTO dto = modelMapper.map(entity, LibroDTO.class);
			dtoList.add(dto);
		});
		return dtoList;
	}

	@Override
	public int deleteById(Long id) {
		Optional<Libro> found = libroRepository.findById(id);
		if (found.isPresent()) {
			libroRepository.delete(found.get());
			return 0;
		} else {
			return 1;
		}
	}

	public int deleteByTitulo(String titulo) {
		Optional<Libro> found = libroRepository.findByTitulo(titulo);
		if (found.isPresent()) {
			libroRepository.delete(found.get());
			return 0;
		} else {
			return 1;
		}
	}

	@Override
	public int updateById(Long id, LibroDTO newData) {
		ExceptionChecker.checkNotNullOrEmpty(newData.getTitulo(), "Título del Libro");
		ExceptionChecker.checkStringLength(newData.getTitulo(), 3, 150, "Título del Libro");
		ExceptionChecker.checkOnlyLetters(newData.getTitulo(), "Titulo del libro solo letras");
		ExceptionChecker.checkNotNullOrEmpty(newData.getAutor(), "Autor del Libro");
		ExceptionChecker.checkStringLength(newData.getAutor(), 3, 100, "Autor del Libro");
		ExceptionChecker.checkOnlyLetters(newData.getAutor(), "Autor del libro solo letras");
		ExceptionChecker.checkNotNullOrEmpty(newData.getDescripcion(), "Descripción del Libro");
		ExceptionChecker.checkStringLength(newData.getDescripcion(), 3, 1000, "Descripción del Libro");
		ExceptionChecker.checkPositiveInt(newData.getAnio(), "Año solo numeros positivos");

		Optional<Libro> found = libroRepository.findById(id);
		Optional<Libro> newFound = libroRepository.findById(newData.getId());

		if (found.isPresent() && !newFound.isPresent()) {
			Libro temp = found.get();
			temp.setDescripcion(newData.getDescripcion());
			temp.setPdf(newData.getPdf());
			temp.setUrlpdf(newData.getUrlpdf());
			temp.setImagenPortada(newData.getImagenPortada());
			temp.setAutor(newData.getAutor());
			temp.setAnio(newData.getAnio());
			libroRepository.save(temp);
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

	public LibroDTO getById(Long id) {
		Optional<Libro> found = libroRepository.findById(id);
		if (found.isPresent()) {
			return modelMapper.map(found.get(), LibroDTO.class);
		} else {
			return null;
		}
	}

	public boolean findTituloAlreadyTaken(Libro newLibro) {
		Optional<Libro> found = libroRepository.findByTitulo(newLibro.getTitulo());
		return found.isPresent();
	}
}
