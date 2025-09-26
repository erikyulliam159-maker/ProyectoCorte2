/**
 * Clase EstudianteControllerTest
 * Proyecto: proyectocorte2back
 * Paquete: co.edu.unbosque.proyectocorte2back
 *
 * Descripción: Documentación pendiente.
 */
package co.edu.unbosque.proyectocorte2back;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import co.edu.unbosque.proyectocorte2back.controller.EstudianteController;
import co.edu.unbosque.proyectocorte2back.dto.*;
import co.edu.unbosque.proyectocorte2back.services.*;
import co.edu.unbosque.proyectocorte2back.util.ArchivoUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class EstudianteControllerTest.
 */
@ExtendWith(MockitoExtension.class)
class EstudianteControllerTest {

	/** The temario service. */
	@Mock
	private TemarioService temarioService;

	/** The subtema service. */
	@Mock
	private SubtemaService subtemaService;

	/** The detalle subtema service. */
	@Mock
	private DetalleSubtemaService detalleSubtemaService;

	/** The problema service. */
	@Mock
	private ProblemaService problemaService;

	/** The libro service. */
	@Mock
	private LibroService libroService;

	/** The evento service. */
	@Mock
	private EventoService eventoService;

	/** The link valioso service. */
	@Mock
	private LinkValiosoService linkValiosoService;

	/** The archivo util. */
	@Mock
	private ArchivoUtil archivoUtil;

	/** The estudiante controller. */
	@InjectMocks
	private EstudianteController estudianteController;

	/** The temario. */
	private TemarioDTO temario;
	
	/** The subtema. */
	private SubtemaDTO subtema;
	
	/** The detalle subtema. */
	private DetalleSubtemaDTO detalleSubtema;
	
	/** The problema. */
	private ProblemaDTO problema;
	
	/** The libro. */
	private LibroDTO libro;
	
	/** The evento. */
	private EventoDTO evento;
	
	/** The link valioso. */
	private LinkValiosoDTO linkValioso;

	/**
	 * Establece el up.
	 */
	@BeforeEach
	void setUp() {
		// Configurar datos de prueba con los nombres de campos correctos
		temario = new TemarioDTO();
		temario.setId(1L);
		temario.setTitulo("Temario de Prueba");

		subtema = new SubtemaDTO();
		subtema.setId(1L);
		subtema.setNombre("Subtema de Prueba");

		detalleSubtema = new DetalleSubtemaDTO();
		detalleSubtema.setId(1L);
		detalleSubtema.setDescripcion("Detalle de prueba");

		problema = new ProblemaDTO();
		problema.setId(1L);
		problema.setTitulo("Problema de prueba");
		problema.setDificultad(1);
		problema.setTema("Matemáticas");
		problema.setJuez("Codeforces");
		problema.setUrl("https://codeforces.com");

		libro = new LibroDTO();
		libro.setId(1L);
		libro.setTitulo("Libro de Prueba");
		libro.setPdf("ruta/archivo.pdf");

		evento = new EventoDTO();
		evento.setId(1L);
		evento.setTitulo("Evento de Prueba");
		evento.setDescripcion("Descripción del evento");
		evento.setFecha(LocalDate.now());
		evento.setUrl("https://evento.com");

		linkValioso = new LinkValiosoDTO();
		linkValioso.setId(1L);
		linkValioso.setUrl("https://ejemplo.com");
	}

	// ... (otros tests permanecen igual)

	/**
	 * Test download pdf success.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	void testDownloadPdf_Success() throws IOException {
		// Arrange
		when(libroService.getById(1L)).thenReturn(libro);

		// Crear un archivo temporal real en lugar de un mock
		File archivoReal = File.createTempFile("test", ".pdf");
		archivoReal.deleteOnExit(); // Limpiar después del test

		when(archivoUtil.obtenerArchivo(libro.getPdf())).thenReturn(archivoReal);

		// Act
		ResponseEntity<InputStreamResource> response = estudianteController.downloadPdf(1L);

		// Assert
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		verify(libroService, times(1)).getById(1L);
		verify(archivoUtil, times(1)).obtenerArchivo(libro.getPdf());

		// Limpiar
		archivoReal.delete();
	}

	/**
	 * Test download pdf libro not found.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	void testDownloadPdf_LibroNotFound() throws IOException {
		// Arrange
		when(libroService.getById(1L)).thenReturn(null);

		// Act
		ResponseEntity<InputStreamResource> response = estudianteController.downloadPdf(1L);

		// Assert
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		verify(libroService, times(1)).getById(1L);
		verify(archivoUtil, never()).obtenerArchivo(anyString());
	}

	/**
	 * Test download pdf archivo no existe.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	void testDownloadPdf_ArchivoNoExiste() throws IOException {
		// Arrange
		when(libroService.getById(1L)).thenReturn(libro);

		File archivoSimulado = mock(File.class);
		when(archivoUtil.obtenerArchivo(libro.getPdf())).thenReturn(archivoSimulado);
		when(archivoSimulado.exists()).thenReturn(false);

		// Act
		ResponseEntity<InputStreamResource> response = estudianteController.downloadPdf(1L);

		// Assert
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		verify(libroService, times(1)).getById(1L);
		verify(archivoUtil, times(1)).obtenerArchivo(libro.getPdf());
	}

}