/**
 * Clase RegistroControllerTest
 * Proyecto: proyectocorte2back
 * Paquete: co.edu.unbosque.proyectocorte2back
 *
 * Descripción: Documentación pendiente.
 */
package co.edu.unbosque.proyectocorte2back;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import co.edu.unbosque.proyectocorte2back.controller.RegistroController;
import co.edu.unbosque.proyectocorte2back.dto.DocenteDTO;
import co.edu.unbosque.proyectocorte2back.dto.EstudianteDTO;
import co.edu.unbosque.proyectocorte2back.services.DocenteService;
import co.edu.unbosque.proyectocorte2back.services.EstudianteService;
import co.edu.unbosque.proyectocorte2back.util.ArchivoUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

// TODO: Auto-generated Javadoc
/**
 * The Class RegistroControllerTest.
 */
@ExtendWith(MockitoExtension.class)
class RegistroControllerTest {

	/** The estudiante service. */
	@Mock
	private EstudianteService estudianteService;

	/** The docente service. */
	@Mock
	private DocenteService docenteService;

	/** The archivo util. */
	@Mock
	private ArchivoUtil archivoUtil;

	/** The multipart file. */
	@Mock
	private MultipartFile multipartFile;

	/** The registro controller. */
	@InjectMocks
	private RegistroController registroController;

	// ------------------- TESTS PARA ESTUDIANTE -------------------

	/**
	 * Test create estudiante success.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	void testCreateEstudiante_Success() throws IOException {
		// Arrange
		when(multipartFile.isEmpty()).thenReturn(false);
		when(archivoUtil.guardarArchivoImagen(multipartFile)).thenReturn("foto_perfil.jpg");
		when(estudianteService.create(any(EstudianteDTO.class))).thenReturn(0);

		// Act
		ResponseEntity<String> response = registroController.createEstudiante("estudiante123", "password123",
				"Juan Pérez", "juan@email.com", multipartFile);

		// Assert
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals("Estudiante creado exitosamente", response.getBody());
		verify(archivoUtil, times(1)).guardarArchivoImagen(multipartFile);
		verify(estudianteService, times(1)).create(any(EstudianteDTO.class));
	}

	/**
	 * Test create estudiante success without photo.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	void testCreateEstudiante_SuccessWithoutPhoto() throws IOException {
		// Arrange
		when(estudianteService.create(any(EstudianteDTO.class))).thenReturn(0);

		// Act
		ResponseEntity<String> response = registroController.createEstudiante("estudiante123", "password123",
				"Juan Pérez", "juan@email.com", null);

		// Assert
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals("Estudiante creado exitosamente", response.getBody());
		verify(archivoUtil, never()).guardarArchivoImagen(any());
		verify(estudianteService, times(1)).create(any(EstudianteDTO.class));
	}

	/**
	 * Test create estudiante username conflict.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	void testCreateEstudiante_UsernameConflict() throws IOException {
		// Arrange
		when(multipartFile.isEmpty()).thenReturn(false);
		when(archivoUtil.guardarArchivoImagen(multipartFile)).thenReturn("foto_perfil.jpg");
		when(estudianteService.create(any(EstudianteDTO.class))).thenReturn(1);

		// Act
		ResponseEntity<String> response = registroController.createEstudiante("estudiante123", "password123",
				"Juan Pérez", "juan@email.com", multipartFile);

		// Assert
		assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
		assertEquals("El username ya está en uso", response.getBody());
		verify(archivoUtil, times(1)).guardarArchivoImagen(multipartFile);
		verify(estudianteService, times(1)).create(any(EstudianteDTO.class));
	}

	/**
	 * Test create estudiante email conflict.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	void testCreateEstudiante_EmailConflict() throws IOException {
		// Arrange
		when(multipartFile.isEmpty()).thenReturn(false);
		when(archivoUtil.guardarArchivoImagen(multipartFile)).thenReturn("foto_perfil.jpg");
		when(estudianteService.create(any(EstudianteDTO.class))).thenReturn(2);

		// Act
		ResponseEntity<String> response = registroController.createEstudiante("estudiante123", "password123",
				"Juan Pérez", "juan@email.com", multipartFile);

		// Assert
		assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
		assertEquals("El correo ya está registrado", response.getBody());
		verify(archivoUtil, times(1)).guardarArchivoImagen(multipartFile);
		verify(estudianteService, times(1)).create(any(EstudianteDTO.class));
	}

	/**
	 * Test create estudiante internal error.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	void testCreateEstudiante_InternalError() throws IOException {
		// Arrange
		when(multipartFile.isEmpty()).thenReturn(false);
		when(archivoUtil.guardarArchivoImagen(multipartFile)).thenReturn("foto_perfil.jpg");
		when(estudianteService.create(any(EstudianteDTO.class))).thenReturn(3);

		// Act
		ResponseEntity<String> response = registroController.createEstudiante("estudiante123", "password123",
				"Juan Pérez", "juan@email.com", multipartFile);

		// Assert
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertEquals("Error desconocido al crear el estudiante", response.getBody());
		verify(archivoUtil, times(1)).guardarArchivoImagen(multipartFile);
		verify(estudianteService, times(1)).create(any(EstudianteDTO.class));
	}

	/**
	 * Test create estudiante IO exception.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	void testCreateEstudiante_IOException() throws IOException {
		// Arrange
		when(multipartFile.isEmpty()).thenReturn(false);
		when(archivoUtil.guardarArchivoImagen(multipartFile)).thenThrow(new IOException("Error al guardar archivo"));

		// Act
		ResponseEntity<String> response = registroController.createEstudiante("estudiante123", "password123",
				"Juan Pérez", "juan@email.com", multipartFile);

		// Assert
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertEquals("Error al subir la foto", response.getBody());
		verify(archivoUtil, times(1)).guardarArchivoImagen(multipartFile);
		verify(estudianteService, never()).create(any(EstudianteDTO.class));
	}

	/**
	 * Test create estudiante general exception.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	void testCreateEstudiante_GeneralException() throws IOException {
		// Arrange
		when(multipartFile.isEmpty()).thenReturn(false);
		when(archivoUtil.guardarArchivoImagen(multipartFile)).thenReturn("foto_perfil.jpg");
		when(estudianteService.create(any(EstudianteDTO.class))).thenThrow(new RuntimeException("Error general"));

		// Act
		ResponseEntity<String> response = registroController.createEstudiante("estudiante123", "password123",
				"Juan Pérez", "juan@email.com", multipartFile);

		// Assert
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertTrue(response.getBody().contains("Error inesperado: Error general"));
		verify(archivoUtil, times(1)).guardarArchivoImagen(multipartFile);
		verify(estudianteService, times(1)).create(any(EstudianteDTO.class));
	}

	// ------------------- TESTS PARA DOCENTE -------------------

	/**
	 * Test create docente success.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	void testCreateDocente_Success() throws IOException {
		// Arrange
		when(multipartFile.isEmpty()).thenReturn(false);
		when(archivoUtil.guardarArchivoImagen(multipartFile)).thenReturn("foto_perfil.jpg");
		when(docenteService.create(any(DocenteDTO.class))).thenReturn(0);

		// Act
		ResponseEntity<String> response = registroController.createDocente("docente123", "password123", "María García",
				"maria@email.com", multipartFile);

		// Assert
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals("Docente creado exitosamente", response.getBody());
		verify(archivoUtil, times(1)).guardarArchivoImagen(multipartFile);
		verify(docenteService, times(1)).create(any(DocenteDTO.class));
	}

	/**
	 * Test create docente success without photo.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	void testCreateDocente_SuccessWithoutPhoto() throws IOException {
		// Arrange
		when(docenteService.create(any(DocenteDTO.class))).thenReturn(0);

		// Act
		ResponseEntity<String> response = registroController.createDocente("docente123", "password123", "María García",
				"maria@email.com", null);

		// Assert
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals("Docente creado exitosamente", response.getBody());
		verify(archivoUtil, never()).guardarArchivoImagen(any());
		verify(docenteService, times(1)).create(any(DocenteDTO.class));
	}

	/**
	 * Test create docente username conflict.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	void testCreateDocente_UsernameConflict() throws IOException {
		// Arrange
		when(multipartFile.isEmpty()).thenReturn(false);
		when(archivoUtil.guardarArchivoImagen(multipartFile)).thenReturn("foto_perfil.jpg");
		when(docenteService.create(any(DocenteDTO.class))).thenReturn(1);

		// Act
		ResponseEntity<String> response = registroController.createDocente("docente123", "password123", "María García",
				"maria@email.com", multipartFile);

		// Assert
		assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
		assertEquals("El username ya está en uso", response.getBody());
		verify(archivoUtil, times(1)).guardarArchivoImagen(multipartFile);
		verify(docenteService, times(1)).create(any(DocenteDTO.class));
	}

	/**
	 * Test create docente email conflict.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	void testCreateDocente_EmailConflict() throws IOException {
		// Arrange
		when(multipartFile.isEmpty()).thenReturn(false);
		when(archivoUtil.guardarArchivoImagen(multipartFile)).thenReturn("foto_perfil.jpg");
		when(docenteService.create(any(DocenteDTO.class))).thenReturn(2);

		// Act
		ResponseEntity<String> response = registroController.createDocente("docente123", "password123", "María García",
				"maria@email.com", multipartFile);

		// Assert
		assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
		assertEquals("El correo ya está registrado", response.getBody());
		verify(archivoUtil, times(1)).guardarArchivoImagen(multipartFile);
		verify(docenteService, times(1)).create(any(DocenteDTO.class));
	}

	/**
	 * Test create docente internal error.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	void testCreateDocente_InternalError() throws IOException {
		// Arrange
		when(multipartFile.isEmpty()).thenReturn(false);
		when(archivoUtil.guardarArchivoImagen(multipartFile)).thenReturn("foto_perfil.jpg");
		when(docenteService.create(any(DocenteDTO.class))).thenReturn(3);

		// Act
		ResponseEntity<String> response = registroController.createDocente("docente123", "password123", "María García",
				"maria@email.com", multipartFile);

		// Assert
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertEquals("Error desconocido al crear el docente", response.getBody());
		verify(archivoUtil, times(1)).guardarArchivoImagen(multipartFile);
		verify(docenteService, times(1)).create(any(DocenteDTO.class));
	}

	/**
	 * Test create docente IO exception.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	void testCreateDocente_IOException() throws IOException {
		// Arrange
		when(multipartFile.isEmpty()).thenReturn(false);
		when(archivoUtil.guardarArchivoImagen(multipartFile)).thenThrow(new IOException("Error al guardar archivo"));

		// Act
		ResponseEntity<String> response = registroController.createDocente("docente123", "password123", "María García",
				"maria@email.com", multipartFile);

		// Assert
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertEquals("Error al subir la foto", response.getBody());
		verify(archivoUtil, times(1)).guardarArchivoImagen(multipartFile);
		verify(docenteService, never()).create(any(DocenteDTO.class));
	}

	/**
	 * Test create docente general exception.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	void testCreateDocente_GeneralException() throws IOException {
		// Arrange
		when(multipartFile.isEmpty()).thenReturn(false);
		when(archivoUtil.guardarArchivoImagen(multipartFile)).thenReturn("foto_perfil.jpg");
		when(docenteService.create(any(DocenteDTO.class))).thenThrow(new RuntimeException("Error general"));

		// Act
		ResponseEntity<String> response = registroController.createDocente("docente123", "password123", "María García",
				"maria@email.com", multipartFile);

		// Assert
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertTrue(response.getBody().contains("Error inesperado: Error general"));
		verify(archivoUtil, times(1)).guardarArchivoImagen(multipartFile);
		verify(docenteService, times(1)).create(any(DocenteDTO.class));
	}
}