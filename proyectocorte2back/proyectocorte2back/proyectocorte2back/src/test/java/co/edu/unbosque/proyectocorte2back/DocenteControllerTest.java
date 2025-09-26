/**
 * Clase DocenteControllerTest
 * Proyecto: proyectocorte2back
 * Paquete: co.edu.unbosque.proyectocorte2back
 *
 * Descripción: Documentación pendiente.
 */
package co.edu.unbosque.proyectocorte2back;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import co.edu.unbosque.proyectocorte2back.controller.DocenteController;
import co.edu.unbosque.proyectocorte2back.dto.*;
import co.edu.unbosque.proyectocorte2back.services.*;
import co.edu.unbosque.proyectocorte2back.util.ArchivoUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class DocenteControllerTest.
 */
@ExtendWith(MockitoExtension.class)
class DocenteControllerTest {

    /** The temario service. */
    @Mock
    private TemarioService temarioService;

    /** The problema service. */
    @Mock
    private ProblemaService problemaService;

    /** The libro service. */
    @Mock
    private LibroService libroService;

    /** The archivo util. */
    @Mock
    private ArchivoUtil archivoUtil;

    /** The multipart file. */
    @Mock
    private MultipartFile multipartFile;

    /** The docente controller. */
    @InjectMocks
    private DocenteController docenteController;

    /** The temario. */
    private TemarioDTO temario;
    
    /** The problema. */
    private ProblemaDTO problema;
    
    /** The libro. */
    private LibroDTO libro;

    /**
     * Establece el up.
     */
    @BeforeEach
    void setUp() {
        // Configurar datos de prueba con valores válidos
        temario = new TemarioDTO();
        temario.setId(1L);
        temario.setTitulo("Temario de Prueba");

        problema = new ProblemaDTO();
        problema.setId(1L);
        problema.setTitulo("Problema de Prueba");
        problema.setDificultad(1);
        problema.setTema("Matemáticas");
        problema.setJuez("Codeforces");
        problema.setUrl("https://codeforces.com");

        libro = new LibroDTO();
        libro.setId(1L);
        libro.setTitulo("Libro de Prueba");
        libro.setAutor("Autor de Prueba");
        libro.setAnio(2024);
        libro.setDescripcion("Descripción de prueba");
        libro.setImagenPortada("imagen.jpg");
        libro.setPdf("archivo.pdf");
        libro.setUrlpdf("https://libro.com");
    }

    // ------------------- TESTS PARA TEMARIO -------------------

    /**
     * Test create temario success.
     */
    @Test
    void testCreateTemario_Success() {
        // Arrange
        when(temarioService.create(temario)).thenReturn(0);

        // Act
        ResponseEntity<String> response = docenteController.createTemario(temario);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Temario creado exitosamente", response.getBody());
        verify(temarioService, times(1)).create(temario);
    }

    /**
     * Test create temario conflict.
     */
    @Test
    void testCreateTemario_Conflict() {
        // Arrange
        when(temarioService.create(temario)).thenReturn(1);

        // Act
        ResponseEntity<String> response = docenteController.createTemario(temario);

        // Assert
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Ya existe un temario con ese título", response.getBody());
        verify(temarioService, times(1)).create(temario);
    }

    /**
     * Test create temario internal error.
     */
    @Test
    void testCreateTemario_InternalError() {
        // Arrange
        when(temarioService.create(temario)).thenReturn(2);

        // Act
        ResponseEntity<String> response = docenteController.createTemario(temario);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error desconocido al crear el temario", response.getBody());
        verify(temarioService, times(1)).create(temario);
    }

    /**
     * Test update temario success.
     */
    @Test
    void testUpdateTemario_Success() {
        // Arrange
        when(temarioService.updateById(1L, temario)).thenReturn(0);

        // Act
        ResponseEntity<String> response = docenteController.updateTemario(1L, temario);

        // Assert
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals("Temario actualizado exitosamente", response.getBody());
        verify(temarioService, times(1)).updateById(1L, temario);
    }

    /**
     * Test update temario title in use.
     */
    @Test
    void testUpdateTemario_TitleInUse() {
        // Arrange
        when(temarioService.updateById(1L, temario)).thenReturn(1);

        // Act
        ResponseEntity<String> response = docenteController.updateTemario(1L, temario);

        // Assert
        assertEquals(HttpStatus.IM_USED, response.getStatusCode());
        assertEquals("El nuevo título ya está en uso", response.getBody());
        verify(temarioService, times(1)).updateById(1L, temario);
    }

    /**
     * Test update temario not found.
     */
    @Test
    void testUpdateTemario_NotFound() {
        // Arrange
        when(temarioService.updateById(1L, temario)).thenReturn(2);

        // Act
        ResponseEntity<String> response = docenteController.updateTemario(1L, temario);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Temario no encontrado", response.getBody());
        verify(temarioService, times(1)).updateById(1L, temario);
    }

    /**
     * Test update temario bad request.
     */
    @Test
    void testUpdateTemario_BadRequest() {
        // Arrange
        when(temarioService.updateById(1L, temario)).thenReturn(3);

        // Act
        ResponseEntity<String> response = docenteController.updateTemario(1L, temario);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error al actualizar el temario", response.getBody());
        verify(temarioService, times(1)).updateById(1L, temario);
    }

    /**
     * Test get all temarios success.
     */
    @Test
    void testGetAllTemarios_Success() {
        // Arrange
        List<TemarioDTO> temarios = Arrays.asList(temario);
        when(temarioService.getAll()).thenReturn(temarios);

        // Act
        ResponseEntity<List<TemarioDTO>> response = docenteController.getAllTemarios();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(temario, response.getBody().get(0));
        verify(temarioService, times(1)).getAll();
    }

    /**
     * Test get all temarios no content.
     */
    @Test
    void testGetAllTemarios_NoContent() {
        // Arrange
        when(temarioService.getAll()).thenReturn(Arrays.asList());

        // Act
        ResponseEntity<List<TemarioDTO>> response = docenteController.getAllTemarios();

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());
        verify(temarioService, times(1)).getAll();
    }

    /**
     * Test get temario by id success.
     */
    @Test
    void testGetTemarioById_Success() {
        // Arrange
        when(temarioService.getById(1L)).thenReturn(temario);

        // Act
        ResponseEntity<TemarioDTO> response = docenteController.getTemarioById(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(temario, response.getBody());
        verify(temarioService, times(1)).getById(1L);
    }

    /**
     * Test get temario by id not found.
     */
    @Test
    void testGetTemarioById_NotFound() {
        // Arrange
        when(temarioService.getById(1L)).thenReturn(null);

        // Act
        ResponseEntity<TemarioDTO> response = docenteController.getTemarioById(1L);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(temarioService, times(1)).getById(1L);
    }

    /**
     * Test delete temario by id success.
     */
    @Test
    void testDeleteTemarioById_Success() {
        // Arrange
        when(temarioService.deleteById(1L)).thenReturn(0);

        // Act
        ResponseEntity<String> response = docenteController.deleteTemarioById(1L);

        // Assert
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals("Temario eliminado exitosamente", response.getBody());
        verify(temarioService, times(1)).deleteById(1L);
    }

    /**
     * Test delete temario by id not found.
     */
    @Test
    void testDeleteTemarioById_NotFound() {
        // Arrange
        when(temarioService.deleteById(1L)).thenReturn(1);

        // Act
        ResponseEntity<String> response = docenteController.deleteTemarioById(1L);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Error al eliminar el temario, no encontrado", response.getBody());
        verify(temarioService, times(1)).deleteById(1L);
    }

    // ------------------- TESTS PARA PROBLEMA -------------------

    /**
     * Test create problema success.
     */
    @Test
    void testCreateProblema_Success() {
        // Arrange
        when(problemaService.create(problema)).thenReturn(0);

        // Act
        ResponseEntity<String> response = docenteController.createProblema(problema);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Evento creado exitosamente", response.getBody());
        verify(problemaService, times(1)).create(problema);
    }

    /**
     * Test create problema conflict.
     */
    @Test
    void testCreateProblema_Conflict() {
        // Arrange
        when(problemaService.create(problema)).thenReturn(1);

        // Act
        ResponseEntity<String> response = docenteController.createProblema(problema);

        // Assert
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Ya existe un evento con ese título", response.getBody());
        verify(problemaService, times(1)).create(problema);
    }

    /**
     * Test update problema success.
     */
    @Test
    void testUpdateProblema_Success() {
        // Arrange
        when(problemaService.updateById(1L, problema)).thenReturn(0);

        // Act
        ResponseEntity<String> response = docenteController.updateProblema(1L, problema);

        // Assert
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals("Problema actualizado exitosamente", response.getBody());
        verify(problemaService, times(1)).updateById(1L, problema);
    }

    /**
     * Test update problema title in use.
     */
    @Test
    void testUpdateProblema_TitleInUse() {
        // Arrange
        when(problemaService.updateById(1L, problema)).thenReturn(1);

        // Act
        ResponseEntity<String> response = docenteController.updateProblema(1L, problema);

        // Assert
        assertEquals(HttpStatus.IM_USED, response.getStatusCode());
        assertEquals("El nuevo título ya está en uso", response.getBody());
        verify(problemaService, times(1)).updateById(1L, problema);
    }

    /**
     * Test update problema not found.
     */
    @Test
    void testUpdateProblema_NotFound() {
        // Arrange
        when(problemaService.updateById(1L, problema)).thenReturn(2);

        // Act
        ResponseEntity<String> response = docenteController.updateProblema(1L, problema);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Problema no encontrado", response.getBody());
        verify(problemaService, times(1)).updateById(1L, problema);
    }

    /**
     * Test get all problemas success.
     */
    @Test
    void testGetAllProblemas_Success() {
        // Arrange
        List<ProblemaDTO> problemas = Arrays.asList(problema);
        when(problemaService.getAll()).thenReturn(problemas);

        // Act
        ResponseEntity<List<ProblemaDTO>> response = docenteController.getAllProblemas();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(problema, response.getBody().get(0));
        verify(problemaService, times(1)).getAll();
    }

    /**
     * Test get problema by id success.
     */
    @Test
    void testGetProblemaById_Success() {
        // Arrange
        when(problemaService.getById(1L)).thenReturn(problema);

        // Act
        ResponseEntity<ProblemaDTO> response = docenteController.getProblemaById(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(problema, response.getBody());
        verify(problemaService, times(1)).getById(1L);
    }

    /**
     * Test delete problema by id success.
     */
    @Test
    void testDeleteProblemaById_Success() {
        // Arrange
        when(problemaService.deleteById(1L)).thenReturn(0);

        // Act
        ResponseEntity<String> response = docenteController.deleteProblemaById(1L);

        // Assert
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals("Problema eliminado exitosamente", response.getBody());
        verify(problemaService, times(1)).deleteById(1L);
    }

    /**
     * Test delete problema by title success.
     */
    @Test
    void testDeleteProblemaByTitle_Success() {
        // Arrange
        when(problemaService.deleteByTitulo("Problema de Prueba")).thenReturn(0);

        // Act
        ResponseEntity<String> response = docenteController.deleteProblemaByTitle("Problema de Prueba");

        // Assert
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals("Problema eliminado exitosamente", response.getBody());
        verify(problemaService, times(1)).deleteByTitulo("Problema de Prueba");
    }

    // ------------------- TESTS PARA LIBRO -------------------

    /**
     * Test create libro success.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    void testCreateLibro_Success() throws IOException {
        // Arrange
        when(archivoUtil.guardarArchivo(multipartFile)).thenReturn("archivo.pdf");
        when(libroService.create(any(LibroDTO.class))).thenReturn(0);

        // Act
        ResponseEntity<String> response = docenteController.createLibro(
            "Libro de Prueba", "Autor de Prueba", 2024, "Descripción", 
            "imagen.jpg", multipartFile, "https://libro.com");

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Evento creado exitosamente", response.getBody());
        verify(archivoUtil, times(1)).guardarArchivo(multipartFile);
        verify(libroService, times(1)).create(any(LibroDTO.class));
    }

    /**
     * Test create libro conflict.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    void testCreateLibro_Conflict() throws IOException {
        // Arrange
        when(archivoUtil.guardarArchivo(multipartFile)).thenReturn("archivo.pdf");
        when(libroService.create(any(LibroDTO.class))).thenReturn(1);

        // Act
        ResponseEntity<String> response = docenteController.createLibro(
            "Libro de Prueba", "Autor de Prueba", 2024, "Descripción", 
            "imagen.jpg", multipartFile, "https://libro.com");

        // Assert
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Ya existe un evento con ese título", response.getBody());
        verify(archivoUtil, times(1)).guardarArchivo(multipartFile);
        verify(libroService, times(1)).create(any(LibroDTO.class));
    }

    /**
     * Test update libro success.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    void testUpdateLibro_Success() throws IOException {
        // Arrange
        when(libroService.getById(1L)).thenReturn(libro);
        when(archivoUtil.guardarArchivo(multipartFile)).thenReturn("nuevo_archivo.pdf");
        when(libroService.updateById(1L, libro)).thenReturn(0);

        // Act
        ResponseEntity<String> response = docenteController.updateLibro(
            1L, "Nuevo Título", "Nuevo Autor", 2025, "Nueva Descripción", 
            "nueva_imagen.jpg", multipartFile, "https://nuevolibro.com");

        // Assert
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals("Libro actualizado exitosamente", response.getBody());
        verify(libroService, times(1)).getById(1L);
        verify(archivoUtil, times(1)).guardarArchivo(multipartFile);
        verify(libroService, times(1)).updateById(1L, libro);
    }

    /**
     * Test update libro without file.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    void testUpdateLibro_WithoutFile() throws IOException {
        // Arrange
        when(libroService.getById(1L)).thenReturn(libro);
        when(libroService.updateById(1L, libro)).thenReturn(0);

        // Act
        ResponseEntity<String> response = docenteController.updateLibro(
            1L, "Nuevo Título", "Nuevo Autor", 2025, "Nueva Descripción", 
            "nueva_imagen.jpg", null, "https://nuevolibro.com");

        // Assert
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals("Libro actualizado exitosamente", response.getBody());
        verify(libroService, times(1)).getById(1L);
        verify(archivoUtil, never()).guardarArchivo(any());
        verify(libroService, times(1)).updateById(1L, libro);
    }

    /**
     * Test update libro not found.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    void testUpdateLibro_NotFound() throws IOException {
        // Arrange
        when(libroService.getById(1L)).thenReturn(null);

        // Act
        ResponseEntity<String> response = docenteController.updateLibro(
            1L, "Nuevo Título", "Nuevo Autor", 2025, "Nueva Descripción", 
            "nueva_imagen.jpg", multipartFile, "https://nuevolibro.com");

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Libro no encontrado", response.getBody());
        verify(libroService, times(1)).getById(1L);
        verify(archivoUtil, never()).guardarArchivo(any());
        verify(libroService, never()).updateById(anyLong(), any(LibroDTO.class));
    }

    /**
     * Test get all libros success.
     */
    @Test
    void testGetAllLibros_Success() {
        // Arrange
        List<LibroDTO> libros = Arrays.asList(libro);
        when(libroService.getAll()).thenReturn(libros);

        // Act
        ResponseEntity<List<LibroDTO>> response = docenteController.getAllLibros();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(libro, response.getBody().get(0));
        verify(libroService, times(1)).getAll();
    }

    /**
     * Test get libro by id success.
     */
    @Test
    void testGetLibroById_Success() {
        // Arrange
        when(libroService.getById(1L)).thenReturn(libro);

        // Act
        ResponseEntity<LibroDTO> response = docenteController.getLibroById(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(libro, response.getBody());
        verify(libroService, times(1)).getById(1L);
    }

    /**
     * Test delete libro by id success.
     */
    @Test
    void testDeleteLibroById_Success() {
        // Arrange
        when(libroService.deleteById(1L)).thenReturn(0);

        // Act
        ResponseEntity<String> response = docenteController.deleteLibroById(1L);

        // Assert
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals("Libro eliminado exitosamente", response.getBody());
        verify(libroService, times(1)).deleteById(1L);
    }

    /**
     * Test delete libro by title success.
     */
    @Test
    void testDeleteLibroByTitle_Success() {
        // Arrange
        when(libroService.deleteByTitulo("Libro de Prueba")).thenReturn(0);

        // Act
        ResponseEntity<String> response = docenteController.deleteLibroByTitle("Libro de Prueba");

        // Assert
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals("Libro eliminado exitosamente", response.getBody());
        verify(libroService, times(1)).deleteByTitulo("Libro de Prueba");
    }
}