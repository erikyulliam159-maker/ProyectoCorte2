/**
 * Clase AdminControllerTest
 * Proyecto: proyectocorte2back
 * Paquete: co.edu.unbosque.proyectocorte2back
 *
 * Descripción: Documentación pendiente.
 */
package co.edu.unbosque.proyectocorte2back;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import co.edu.unbosque.proyectocorte2back.controller.AdminController;
import co.edu.unbosque.proyectocorte2back.dto.DocenteDTO;
import co.edu.unbosque.proyectocorte2back.dto.EstudianteDTO;
import co.edu.unbosque.proyectocorte2back.dto.EventoDTO;
import co.edu.unbosque.proyectocorte2back.dto.LinkValiosoDTO;
import co.edu.unbosque.proyectocorte2back.services.DocenteService;
import co.edu.unbosque.proyectocorte2back.services.EstudianteService;
import co.edu.unbosque.proyectocorte2back.services.EventoService;
import co.edu.unbosque.proyectocorte2back.services.LinkValiosoService;
import co.edu.unbosque.proyectocorte2back.util.ArchivoUtil;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// TODO: Auto-generated Javadoc
/**
 * The Class AdminControllerTest.
 */
@WebMvcTest(AdminController.class)
class AdminControllerTest {

	/** The mock mvc. */
	@Autowired
	private MockMvc mockMvc;

	/** The evento service. */
	@MockBean
	private EventoService eventoService;

	/** The link valioso service. */
	@MockBean
	private LinkValiosoService linkValiosoService;

	/** The docente service. */
	@MockBean
	private DocenteService docenteService;

	/** The estudiante service. */
	@MockBean
	private EstudianteService estudianteService;

	/** The archivo util. */
	@MockBean
	private ArchivoUtil archivoUtil;

	/**
	 * Cuando create evento con datos validos entonces retornar created.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@DisplayName("POST /admin/evento/create - Evento creado exitosamente")
	void cuandoCreateEventoConDatosValidos_entoncesRetornarCreated() throws Exception {
		String eventoJson = "{\"titulo\":\"Evento Test\",\"fecha\":\"2024-12-31\",\"descripcion\":\"Descripción test\"}";
		when(eventoService.create(any(EventoDTO.class))).thenReturn(0);

		mockMvc.perform(MockMvcRequestBuilders.post("/admin/evento/create").contentType(MediaType.APPLICATION_JSON)
				.content(eventoJson)).andExpect(status().isCreated())
				.andExpect(content().string("Evento creado exitosamente"));
	}

	/**
	 * Cuando create evento con titulo vacio entonces retornar bad request.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@DisplayName("POST /admin/evento/create - Título vacío")
	void cuandoCreateEventoConTituloVacio_entoncesRetornarBadRequest() throws Exception {
		String eventoJson = "{\"titulo\":\"\",\"fecha\":\"2024-12-31\"}";

		mockMvc.perform(MockMvcRequestBuilders.post("/admin/evento/create").contentType(MediaType.APPLICATION_JSON)
				.content(eventoJson)).andExpect(status().isBadRequest())
				.andExpect(content().string("El título del evento es obligatorio"));
	}

	/**
	 * Cuando create evento con fecha nula entonces retornar bad request.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@DisplayName("POST /admin/evento/create - Fecha nula")
	void cuandoCreateEventoConFechaNula_entoncesRetornarBadRequest() throws Exception {
		String eventoJson = "{\"titulo\":\"Evento Test\",\"fecha\":null}";

		mockMvc.perform(MockMvcRequestBuilders.post("/admin/evento/create").contentType(MediaType.APPLICATION_JSON)
				.content(eventoJson)).andExpect(status().isBadRequest())
				.andExpect(content().string("La fecha del evento es obligatoria"));
	}

	/**
	 * Cuando create evento que ya existe entonces retornar conflict.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@DisplayName("POST /admin/evento/create - Evento ya existe")
	void cuandoCreateEventoQueYaExiste_entoncesRetornarConflict() throws Exception {
		String eventoJson = "{\"titulo\":\"Evento Existente\",\"fecha\":\"2024-12-31\"}";
		when(eventoService.create(any(EventoDTO.class))).thenReturn(1);

		mockMvc.perform(MockMvcRequestBuilders.post("/admin/evento/create").contentType(MediaType.APPLICATION_JSON)
				.content(eventoJson)).andExpect(status().isConflict())
				.andExpect(content().string("Ya existe un evento con ese título"));
	}

	/**
	 * Cuando get all eventos con eventos entonces retornar lista.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@DisplayName("GET /admin/evento/getall - Con eventos existentes")
	void cuandoGetAllEventosConEventos_entoncesRetornarLista() throws Exception {
		EventoDTO evento1 = new EventoDTO();
		evento1.setId(1L);
		evento1.setTitulo("Evento 1");

		EventoDTO evento2 = new EventoDTO();
		evento2.setId(2L);
		evento2.setTitulo("Evento 2");

		List<EventoDTO> eventos = Arrays.asList(evento1, evento2);
		when(eventoService.getAll()).thenReturn(eventos);

		mockMvc.perform(MockMvcRequestBuilders.get("/admin/evento/getall")).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(2));
	}

	/**
	 * Cuando get all eventos sin eventos entonces retornar no content.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@DisplayName("GET /admin/evento/getall - Sin eventos")
	void cuandoGetAllEventosSinEventos_entoncesRetornarNoContent() throws Exception {
		when(eventoService.getAll()).thenReturn(Arrays.asList());

		mockMvc.perform(MockMvcRequestBuilders.get("/admin/evento/getall")).andExpect(status().isNoContent());
	}

	/**
	 * Cuando get evento by id existente entonces retornar evento.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@DisplayName("GET /admin/evento/getbyid/{id} - Evento encontrado")
	void cuandoGetEventoByIdExistente_entoncesRetornarEvento() throws Exception {
		EventoDTO evento = new EventoDTO();
		evento.setId(1L);
		evento.setTitulo("Evento Test");
		when(eventoService.getById(1L)).thenReturn(evento);

		mockMvc.perform(MockMvcRequestBuilders.get("/admin/evento/getbyid/1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.titulo").value("Evento Test"));
	}

	/**
	 * Cuando get evento by id no existente entonces retornar not found.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@DisplayName("GET /admin/evento/getbyid/{id} - Evento no encontrado")
	void cuandoGetEventoByIdNoExistente_entoncesRetornarNotFound() throws Exception {
		when(eventoService.getById(999L)).thenReturn(null);

		mockMvc.perform(MockMvcRequestBuilders.get("/admin/evento/getbyid/999")).andExpect(status().isNotFound());
	}

	/**
	 * Cuando update evento con datos validos entonces retornar accepted.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@DisplayName("PUT /admin/evento/update - Actualización exitosa")
	void cuandoUpdateEventoConDatosValidos_entoncesRetornarAccepted() throws Exception {
		String eventoJson = "{\"titulo\":\"Evento Actualizado\",\"fecha\":\"2024-12-31\"}";
		when(eventoService.updateById(eq(1L), any(EventoDTO.class))).thenReturn(0);

		mockMvc.perform(MockMvcRequestBuilders.put("/admin/evento/update?id=1").contentType(MediaType.APPLICATION_JSON)
				.content(eventoJson)).andExpect(status().isAccepted())
				.andExpect(content().string("Evento actualizado exitosamente"));
	}

	/**
	 * Cuando delete evento by id existente entonces retornar accepted.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@DisplayName("DELETE /admin/evento/deletebyid/{id} - Eliminación exitosa")
	void cuandoDeleteEventoByIdExistente_entoncesRetornarAccepted() throws Exception {
		when(eventoService.deleteById(1L)).thenReturn(0);

		mockMvc.perform(MockMvcRequestBuilders.delete("/admin/evento/deletebyid/1")).andExpect(status().isAccepted())
				.andExpect(content().string("Evento eliminado exitosamente"));
	}

	/**
	 * Cuando create link valioso con datos validos entonces retornar created.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@DisplayName("POST /admin/linkvalioso/create - Link creado exitosamente")
	void cuandoCreateLinkValiosoConDatosValidos_entoncesRetornarCreated() throws Exception {
		String linkJson = "{\"nombre\":\"Link Test\",\"url\":\"https://example.com\",\"descripcion\":\"Descripción test\"}";
		when(linkValiosoService.create(any(LinkValiosoDTO.class))).thenReturn(0);

		mockMvc.perform(MockMvcRequestBuilders.post("/admin/linkvalioso/create").contentType(MediaType.APPLICATION_JSON)
				.content(linkJson)).andExpect(status().isCreated())
				.andExpect(content().string("Link valioso creado exitosamente"));
	}

	/**
	 * Cuando create link valioso con nombre vacio entonces retornar bad request.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@DisplayName("POST /admin/linkvalioso/create - Nombre vacío")
	void cuandoCreateLinkValiosoConNombreVacio_entoncesRetornarBadRequest() throws Exception {
		String linkJson = "{\"nombre\":\"\",\"url\":\"https://example.com\"}";

		mockMvc.perform(MockMvcRequestBuilders.post("/admin/linkvalioso/create").contentType(MediaType.APPLICATION_JSON)
				.content(linkJson)).andExpect(status().isBadRequest())
				.andExpect(content().string("El título es obligatorio"));
	}

	/**
	 * Cuando create link valioso con URL vacia entonces retornar bad request.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@DisplayName("POST /admin/linkvalioso/create - URL vacía")
	void cuandoCreateLinkValiosoConURLVacia_entoncesRetornarBadRequest() throws Exception {
		String linkJson = "{\"nombre\":\"Link Test\",\"url\":\"\"}";

		mockMvc.perform(MockMvcRequestBuilders.post("/admin/linkvalioso/create").contentType(MediaType.APPLICATION_JSON)
				.content(linkJson)).andExpect(status().isBadRequest())
				.andExpect(content().string("La URL es obligatoria"));
	}

	/**
	 * Cuando create link valioso con URL invalida entonces retornar bad request.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@DisplayName("POST /admin/linkvalioso/create - URL inválida")
	void cuandoCreateLinkValiosoConURLInvalida_entoncesRetornarBadRequest() throws Exception {
		String linkJson = "{\"nombre\":\"Link Test\",\"url\":\"example.com\"}";

		mockMvc.perform(MockMvcRequestBuilders.post("/admin/linkvalioso/create").contentType(MediaType.APPLICATION_JSON)
				.content(linkJson)).andExpect(status().isBadRequest())
				.andExpect(content().string("La URL debe comenzar con http o https"));
	}

	/**
	 * Cuando get all links valiosos con links entonces retornar lista.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@DisplayName("GET /admin/linkvalioso/getall - Con links existentes")
	void cuandoGetAllLinksValiososConLinks_entoncesRetornarLista() throws Exception {
		LinkValiosoDTO link1 = new LinkValiosoDTO();
		link1.setId(1L);
		link1.setNombre("Link 1");

		LinkValiosoDTO link2 = new LinkValiosoDTO();
		link2.setId(2L);
		link2.setNombre("Link 2");

		List<LinkValiosoDTO> links = Arrays.asList(link1, link2);
		when(linkValiosoService.getAll()).thenReturn(links);

		mockMvc.perform(MockMvcRequestBuilders.get("/admin/linkvalioso/getall")).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(2));
	}

	/**
	 * Cuando delete link valioso by id existente entonces retornar ok.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@DisplayName("DELETE /admin/linkvalioso/deletebyid/{id} - Eliminación exitosa")
	void cuandoDeleteLinkValiosoByIdExistente_entoncesRetornarOk() throws Exception {
		when(linkValiosoService.deleteById(1L)).thenReturn(0);

		mockMvc.perform(MockMvcRequestBuilders.delete("/admin/linkvalioso/deletebyid/1")).andExpect(status().isOk())
				.andExpect(content().string("Link valioso eliminado exitosamente"));
	}

	/**
	 * Cuando get all docentes con docentes entonces retornar lista.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@DisplayName("GET /admin/docente/getall - Con docentes existentes")
	void cuandoGetAllDocentesConDocentes_entoncesRetornarLista() throws Exception {
		DocenteDTO docente1 = new DocenteDTO();
		docente1.setId(1L);
		docente1.setNombreCompleto("Docente 1");

		DocenteDTO docente2 = new DocenteDTO();
		docente2.setId(2L);
		docente2.setNombreCompleto("Docente 2");

		List<DocenteDTO> docentes = Arrays.asList(docente1, docente2);
		when(docenteService.getAll()).thenReturn(docentes);

		mockMvc.perform(MockMvcRequestBuilders.get("/admin/docente/getall")).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(2));
	}

	/**
	 * Cuando get docente by id existente entonces retornar docente.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@DisplayName("GET /admin/docente/getbyid/{id} - Docente encontrado")
	void cuandoGetDocenteByIdExistente_entoncesRetornarDocente() throws Exception {
		DocenteDTO docente = new DocenteDTO();
		docente.setId(1L);
		docente.setNombreCompleto("Docente Test");
		when(docenteService.getById(1L)).thenReturn(docente);

		mockMvc.perform(MockMvcRequestBuilders.get("/admin/docente/getbyid/1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.nombreCompleto").value("Docente Test"));
	}

	/**
	 * Cuando update docente sin archivo entonces retornar ok.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@DisplayName("PUT /admin/docente/update - Actualización exitosa sin archivo")
	void cuandoUpdateDocenteSinArchivo_entoncesRetornarOk() throws Exception {
		DocenteDTO docenteExistente = new DocenteDTO();
		docenteExistente.setId(1L);
		docenteExistente.setUsername("docente1");
		docenteExistente.setNombreCompleto("Docente Existente");
		docenteExistente.setEmail("docente@test.com");

		when(docenteService.getById(1L)).thenReturn(docenteExistente);
		when(docenteService.updateById(eq(1L), any(DocenteDTO.class))).thenReturn(0);

		mockMvc.perform(MockMvcRequestBuilders.multipart("/admin/docente/update").param("id", "1")
				.param("username", "docente_actualizado").param("password", "nueva_password")
				.param("nombreCompleto", "Docente Actualizado").param("email", "docente@actualizado.com")
				.with(request -> {
					request.setMethod("PUT");
					return request;
				})).andExpect(status().isOk()).andExpect(content().string("Docente actualizado exitosamente"));
	}

	/**
	 * Cuando update docente con username vacio entonces retornar bad request.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@DisplayName("PUT /admin/docente/update - Username vacío")
	void cuandoUpdateDocenteConUsernameVacio_entoncesRetornarBadRequest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.multipart("/admin/docente/update").param("id", "1").param("username", "")
				.param("password", "password").param("nombreCompleto", "Docente Test")
				.param("email", "docente@test.com").with(request -> {
					request.setMethod("PUT");
					return request;
				})).andExpect(status().isBadRequest()).andExpect(content().string("El username es obligatorio"));
	}

	/**
	 * Cuando update docente con email invalido entonces retornar bad request.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@DisplayName("PUT /admin/docente/update - Email inválido")
	void cuandoUpdateDocenteConEmailInvalido_entoncesRetornarBadRequest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.multipart("/admin/docente/update").param("id", "1")
				.param("username", "docente1").param("password", "password").param("nombreCompleto", "Docente Test")
				.param("email", "emailinvalido").with(request -> {
					request.setMethod("PUT");
					return request;
				})).andExpect(status().isBadRequest()).andExpect(content().string("El email no es válido"));
	}

	/**
	 * Cuando delete docente by id existente entonces retornar ok.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@DisplayName("DELETE /admin/docente/deletebyid/{id} - Eliminación exitosa")
	void cuandoDeleteDocenteByIdExistente_entoncesRetornarOk() throws Exception {
		when(docenteService.deleteById(1L)).thenReturn(0);

		mockMvc.perform(MockMvcRequestBuilders.delete("/admin/docente/deletebyid/1")).andExpect(status().isOk())
				.andExpect(content().string("Docente eliminado exitosamente"));
	}

	/**
	 * Cuando get all estudiantes con estudiantes entonces retornar lista.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@DisplayName("GET /admin/estudiante/getall - Con estudiantes existentes")
	void cuandoGetAllEstudiantesConEstudiantes_entoncesRetornarLista() throws Exception {
		EstudianteDTO estudiante1 = new EstudianteDTO();
		estudiante1.setId(1L);
		estudiante1.setNombreCompleto("Estudiante 1");

		EstudianteDTO estudiante2 = new EstudianteDTO();
		estudiante2.setId(2L);
		estudiante2.setNombreCompleto("Estudiante 2");

		List<EstudianteDTO> estudiantes = Arrays.asList(estudiante1, estudiante2);
		when(estudianteService.getAll()).thenReturn(estudiantes);

		mockMvc.perform(MockMvcRequestBuilders.get("/admin/estudiante/getall")).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(2));
	}

	/**
	 * Cuando get estudiante by id existente entonces retornar estudiante.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@DisplayName("GET /admin/estudiante/getbyid/{id} - Estudiante encontrado")
	void cuandoGetEstudianteByIdExistente_entoncesRetornarEstudiante() throws Exception {
		EstudianteDTO estudiante = new EstudianteDTO();
		estudiante.setId(1L);
		estudiante.setNombreCompleto("Estudiante Test");
		when(estudianteService.getById(1L)).thenReturn(estudiante);

		mockMvc.perform(MockMvcRequestBuilders.get("/admin/estudiante/getbyid/1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.nombreCompleto").value("Estudiante Test"));
	}

	/**
	 * Cuando update estudiante sin archivo entonces retornar ok.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@DisplayName("PUT /admin/estudiante/update - Actualización exitosa sin archivo")
	void cuandoUpdateEstudianteSinArchivo_entoncesRetornarOk() throws Exception {
		EstudianteDTO estudianteExistente = new EstudianteDTO();
		estudianteExistente.setId(1L);
		estudianteExistente.setUsername("estudiante1");
		estudianteExistente.setNombreCompleto("Estudiante Existente");
		estudianteExistente.setEmail("estudiante@test.com");

		when(estudianteService.getById(1L)).thenReturn(estudianteExistente);
		when(estudianteService.updateById(eq(1L), any(EstudianteDTO.class))).thenReturn(0);

		mockMvc.perform(MockMvcRequestBuilders.multipart("/admin/estudiante/update").param("id", "1")
				.param("username", "estudiante_actualizado").param("password", "nueva_password")
				.param("nombreCompleto", "Estudiante Actualizado").param("email", "estudiante@actualizado.com")
				.with(request -> {
					request.setMethod("PUT");
					return request;
				})).andExpect(status().isOk()).andExpect(content().string("Estudiante actualizado exitosamente"));
	}

	/**
	 * Cuando update estudiante con password vacia entonces retornar bad request.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@DisplayName("PUT /admin/estudiante/update - Password vacía")
	void cuandoUpdateEstudianteConPasswordVacia_entoncesRetornarBadRequest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.multipart("/admin/estudiante/update").param("id", "1")
				.param("username", "estudiante1").param("password", "").param("nombreCompleto", "Estudiante Test")
				.param("email", "estudiante@test.com").with(request -> {
					request.setMethod("PUT");
					return request;
				})).andExpect(status().isBadRequest()).andExpect(content().string("La contraseña es obligatoria"));
	}

	/**
	 * Cuando delete estudiante by id existente entonces retornar ok.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@DisplayName("DELETE /admin/estudiante/deletebyid/{id} - Eliminación exitosa")
	void cuandoDeleteEstudianteByIdExistente_entoncesRetornarOk() throws Exception {
		when(estudianteService.deleteById(1L)).thenReturn(0);

		mockMvc.perform(MockMvcRequestBuilders.delete("/admin/estudiante/deletebyid/1")).andExpect(status().isOk())
				.andExpect(content().string("Estudiante eliminado exitosamente"));
	}

	/**
	 * Cuando delete estudiante by name con nombre vacio entonces retornar bad request.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@DisplayName("DELETE /admin/estudiante/deletebyname - Nombre vacío")
	void cuandoDeleteEstudianteByNameConNombreVacio_entoncesRetornarBadRequest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/admin/estudiante/deletebyname").param("nombreCompleto", ""))
				.andExpect(status().isBadRequest()).andExpect(content().string("El nombre no puede estar vacío"));
	}
}