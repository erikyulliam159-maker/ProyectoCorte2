/**
 * Clase LoginControllerTest
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import co.edu.unbosque.proyectocorte2back.controller.LoginController;
import co.edu.unbosque.proyectocorte2back.services.AdministradorService;
import co.edu.unbosque.proyectocorte2back.services.DocenteService;
import co.edu.unbosque.proyectocorte2back.services.EstudianteService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginControllerTest.
 */
@WebMvcTest(LoginController.class)
class LoginControllerTest {

	/** The mock mvc. */
	@Autowired
	private MockMvc mockMvc;

	/** The estudiante service. */
	@MockBean
	private EstudianteService estudianteService;

	/** The administrador service. */
	@MockBean
	private AdministradorService administradorService;

	/** The docente service. */
	@MockBean
	private DocenteService docenteService;

	/**
	 * Cuando check login estudiante con credenciales correctas entonces retornar accepted.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@DisplayName("POST /login/checkloginEstudiante - Credenciales correctas")
	void cuandoCheckLoginEstudianteConCredencialesCorrectas_entoncesRetornarAccepted() throws Exception {
		String username = "estudiante123";
		String password = "password123";
		when(estudianteService.validateCredentials(username, password)).thenReturn(0);

		mockMvc.perform(MockMvcRequestBuilders.post("/login/checkloginEstudiante").param("username", username)
				.param("password", password).contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isAccepted()).andExpect(content().string("Credenciales correctas"));
	}

	/**
	 * Cuando check login estudiante con usuario no existente entonces retornar not found.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@DisplayName("POST /login/checkloginEstudiante - Usuario no existe")
	void cuandoCheckLoginEstudianteConUsuarioNoExistente_entoncesRetornarNotFound() throws Exception {
		String username = "usuario_inexistente";
		String password = "password123";
		when(estudianteService.validateCredentials(username, password)).thenReturn(1);

		mockMvc.perform(MockMvcRequestBuilders.post("/login/checkloginEstudiante").param("username", username)
				.param("password", password).contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isNotFound()).andExpect(content().string("El usuario no existe"));
	}

	/**
	 * Cuando check login estudiante con password incorrecto entonces retornar unauthorized.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@DisplayName("POST /login/checkloginEstudiante - Contraseña incorrecta")
	void cuandoCheckLoginEstudianteConPasswordIncorrecto_entoncesRetornarUnauthorized() throws Exception {
		String username = "estudiante123";
		String password = "password_incorrecto";
		when(estudianteService.validateCredentials(username, password)).thenReturn(2);

		mockMvc.perform(MockMvcRequestBuilders.post("/login/checkloginEstudiante").param("username", username)
				.param("password", password).contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isUnauthorized()).andExpect(content().string("Contraseña incorrecta"));
	}

	/**
	 * Cuando check login estudiante con error interno entonces retornar internal server error.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@DisplayName("POST /login/checkloginEstudiante - Error interno del servicio")
	void cuandoCheckLoginEstudianteConErrorInterno_entoncesRetornarInternalServerError() throws Exception {
		String username = "estudiante123";
		String password = "password123";
		when(estudianteService.validateCredentials(username, password)).thenReturn(999);

		mockMvc.perform(MockMvcRequestBuilders.post("/login/checkloginEstudiante").param("username", username)
				.param("password", password).contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isInternalServerError())
				.andExpect(content().string("Error interno al validar credenciales"));
	}

	/**
	 * Cuando check login estudiante con username vacio entonces retornar bad request.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@DisplayName("POST /login/checkloginEstudiante - Username vacío")
	void cuandoCheckLoginEstudianteConUsernameVacio_entoncesRetornarBadRequest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/login/checkloginEstudiante").param("username", "")
				.param("password", "password123").contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isBadRequest()).andExpect(content().string("El username no puede estar vacío"));
	}

	/**
	 * Cuando check login estudiante con password vacio entonces retornar bad request.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@DisplayName("POST /login/checkloginEstudiante - Password vacío")
	void cuandoCheckLoginEstudianteConPasswordVacio_entoncesRetornarBadRequest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/login/checkloginEstudiante").param("username", "estudiante123")
				.param("password", "").contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isBadRequest()).andExpect(content().string("La contraseña no puede estar vacía"));
	}

	/**
	 * Cuando check login estudiante con username con espacios entonces retornar bad request.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@DisplayName("POST /login/checkloginEstudiante - Username con espacios en blanco")
	void cuandoCheckLoginEstudianteConUsernameConEspacios_entoncesRetornarBadRequest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/login/checkloginEstudiante").param("username", "   ")
				.param("password", "password123").contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isBadRequest()).andExpect(content().string("El username no puede estar vacío"));
	}

	/**
	 * Cuando check login docente con credenciales correctas entonces retornar accepted.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@DisplayName("POST /login/checkloginDocente - Credenciales correctas")
	void cuandoCheckLoginDocenteConCredencialesCorrectas_entoncesRetornarAccepted() throws Exception {
		String username = "docente456";
		String password = "password456";
		when(docenteService.validateCredentials(username, password)).thenReturn(0);

		mockMvc.perform(MockMvcRequestBuilders.post("/login/checkloginDocente").param("username", username)
				.param("password", password).contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isAccepted()).andExpect(content().string("Credenciales correctas"));
	}

	/**
	 * Cuando check login docente con usuario no existente entonces retornar not found.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@DisplayName("POST /login/checkloginDocente - Usuario no existe")
	void cuandoCheckLoginDocenteConUsuarioNoExistente_entoncesRetornarNotFound() throws Exception {
		String username = "docente_inexistente";
		String password = "password456";
		when(docenteService.validateCredentials(username, password)).thenReturn(1);

		mockMvc.perform(MockMvcRequestBuilders.post("/login/checkloginDocente").param("username", username)
				.param("password", password).contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isNotFound()).andExpect(content().string("El usuario no existe"));
	}

	/**
	 * Cuando check login docente con password incorrecto entonces retornar unauthorized.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@DisplayName("POST /login/checkloginDocente - Contraseña incorrecta")
	void cuandoCheckLoginDocenteConPasswordIncorrecto_entoncesRetornarUnauthorized() throws Exception {
		String username = "docente456";
		String password = "password_incorrecto";
		when(docenteService.validateCredentials(username, password)).thenReturn(2);

		mockMvc.perform(MockMvcRequestBuilders.post("/login/checkloginDocente").param("username", username)
				.param("password", password).contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isUnauthorized()).andExpect(content().string("Contraseña incorrecta"));
	}

	/**
	 * Cuando check login docente con error interno entonces retornar internal server error.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@DisplayName("POST /login/checkloginDocente - Error interno del servicio")
	void cuandoCheckLoginDocenteConErrorInterno_entoncesRetornarInternalServerError() throws Exception {
		String username = "docente456";
		String password = "password456";
		when(docenteService.validateCredentials(username, password)).thenReturn(999);

		mockMvc.perform(MockMvcRequestBuilders.post("/login/checkloginDocente").param("username", username)
				.param("password", password).contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isInternalServerError())
				.andExpect(content().string("Error interno al validar credenciales"));
	}

	/**
	 * Cuando check login docente con username vacio entonces retornar bad request.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@DisplayName("POST /login/checkloginDocente - Username vacío")
	void cuandoCheckLoginDocenteConUsernameVacio_entoncesRetornarBadRequest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/login/checkloginDocente").param("username", "")
				.param("password", "password456").contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isBadRequest()).andExpect(content().string("El username no puede estar vacío"));
	}

	/**
	 * Cuando check login administrador con credenciales correctas entonces retornar accepted.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@DisplayName("POST /login/checkloginAdministrador - Credenciales correctas")
	void cuandoCheckLoginAdministradorConCredencialesCorrectas_entoncesRetornarAccepted() throws Exception {
		String username = "admin789";
		String password = "admin123";
		when(administradorService.validateCredentials(username, password)).thenReturn(0);

		mockMvc.perform(MockMvcRequestBuilders.post("/login/checkloginAdministrador").param("username", username)
				.param("password", password).contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isAccepted()).andExpect(content().string("Credenciales correctas"));
	}

	/**
	 * Cuando check login administrador con usuario no existente entonces retornar not found.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@DisplayName("POST /login/checkloginAdministrador - Usuario no existe")
	void cuandoCheckLoginAdministradorConUsuarioNoExistente_entoncesRetornarNotFound() throws Exception {
		String username = "admin_inexistente";
		String password = "admin123";
		when(administradorService.validateCredentials(username, password)).thenReturn(1);

		mockMvc.perform(MockMvcRequestBuilders.post("/login/checkloginAdministrador").param("username", username)
				.param("password", password).contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isNotFound()).andExpect(content().string("El usuario no existe"));
	}

	/**
	 * Cuando check login administrador con password incorrecto entonces retornar unauthorized.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@DisplayName("POST /login/checkloginAdministrador - Contraseña incorrecta")
	void cuandoCheckLoginAdministradorConPasswordIncorrecto_entoncesRetornarUnauthorized() throws Exception {
		String username = "admin789";
		String password = "password_incorrecto";
		when(administradorService.validateCredentials(username, password)).thenReturn(2);

		mockMvc.perform(MockMvcRequestBuilders.post("/login/checkloginAdministrador").param("username", username)
				.param("password", password).contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isUnauthorized()).andExpect(content().string("Contraseña incorrecta"));
	}

	/**
	 * Cuando check login administrador con error interno entonces retornar internal server error.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@DisplayName("POST /login/checkloginAdministrador - Error interno del servicio")
	void cuandoCheckLoginAdministradorConErrorInterno_entoncesRetornarInternalServerError() throws Exception {
		String username = "admin789";
		String password = "admin123";
		when(administradorService.validateCredentials(username, password)).thenReturn(999);

		mockMvc.perform(MockMvcRequestBuilders.post("/login/checkloginAdministrador").param("username", username)
				.param("password", password).contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isInternalServerError())
				.andExpect(content().string("Error interno al validar credenciales"));
	}

	/**
	 * Cuando check login administrador con password vacio entonces retornar bad request.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@DisplayName("POST /login/checkloginAdministrador - Password vacío")
	void cuandoCheckLoginAdministradorConPasswordVacio_entoncesRetornarBadRequest() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.post("/login/checkloginAdministrador").param("username", "admin789")
				.param("password", "").contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isBadRequest()).andExpect(content().string("La contraseña no puede estar vacía"));
	}

	/**
	 * Cuando check login administrador con ambos campos vacios entonces retornar bad request.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@DisplayName("POST /login/checkloginAdministrador - Ambos campos vacíos")
	void cuandoCheckLoginAdministradorConAmbosCamposVacios_entoncesRetornarBadRequest() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.post("/login/checkloginAdministrador").param("username", "")
				.param("password", "").contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isBadRequest()).andExpect(content().string("El username no puede estar vacío"));
	}
}