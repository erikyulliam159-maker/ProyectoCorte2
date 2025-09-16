package co.edu.unbosque.proyectocorte2back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import co.edu.unbosque.proyectocorte2back.dto.AdministradorDTO;
import co.edu.unbosque.proyectocorte2back.dto.DocenteDTO;
import co.edu.unbosque.proyectocorte2back.dto.EstudianteDTO;
import co.edu.unbosque.proyectocorte2back.dto.EventoDTO;
import co.edu.unbosque.proyectocorte2back.dto.LinkValiosoDTO;
import co.edu.unbosque.proyectocorte2back.services.AdministradorService;
import co.edu.unbosque.proyectocorte2back.services.DocenteService;
import co.edu.unbosque.proyectocorte2back.services.EstudianteService;
import co.edu.unbosque.proyectocorte2back.services.EventoService;
import co.edu.unbosque.proyectocorte2back.services.LinkValiosoService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins ="*")
@Transactional
public class AdminController {

    @Autowired
    private AdministradorService adminService;
    
    @Autowired
    private EventoService eventoService;
    
    @Autowired
    private LinkValiosoService linkValiosoService;
    
    @Autowired
    private DocenteService docenteService;

    @Autowired
    private EstudianteService estudianteService;

    public AdminController() {}

    @PostMapping(path = "/createjson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createNewWithJSON(@RequestBody AdministradorDTO newAdmin) {
        int status = adminService.create(newAdmin);
        if (status == 0) {
            return new ResponseEntity<>("Administrador creado exitosamente", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Error al crear el administrador, puede que el usuario o correo ya esté en uso", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping(path = "/checklogin", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> checkLogIn(@RequestBody AdministradorDTO adminDTO) {
        int status = adminService.validateCredentials(adminDTO.getUsername(), adminDTO.getPassword());
        if (status == 0) {
            return new ResponseEntity<>("Inicio de sesión exitoso", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("Usuario o contraseña incorrectos", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/getall")
    public ResponseEntity<List<AdministradorDTO>> getAll() {
        List<AdministradorDTO> admins = adminService.getAll();
        if (admins.isEmpty()) {
            return new ResponseEntity<>(admins, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(admins, HttpStatus.OK);
        }
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countAll() {
        Long count = adminService.count();
        if (count == 0) {
            return new ResponseEntity<>(count, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(count, HttpStatus.OK);
        }
    }

    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> exists(@PathVariable Long id) {
        boolean found = adminService.exist(id);
        if (found) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<AdministradorDTO> getById(@PathVariable Long id) {
        AdministradorDTO found = adminService.getById(id);
        if (found != null) {
            return new ResponseEntity<>(found, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "/updatejson", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateNewWithJSON(@RequestParam Long id, @RequestBody AdministradorDTO newAdmin) {
        int status = adminService.updateById(id, newAdmin);
        if (status == 0) {
            return new ResponseEntity<>("Administrador actualizado exitosamente", HttpStatus.ACCEPTED);
        } else if (status == 1) {
            return new ResponseEntity<>("El nuevo usuario o correo ya está en uso", HttpStatus.IM_USED);
        } else if (status == 2) {
            return new ResponseEntity<>("Administrador no encontrado", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>("Error al actualizar", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        int status = adminService.deleteById(id);
        if (status == 0) {
            return new ResponseEntity<>("Administrador eliminado exitosamente", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("Error al eliminar", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deletebyname")
    public ResponseEntity<String> deleteByName(@RequestParam String nombreCompleto) {
        int status = adminService.deleteByUsername(nombreCompleto);
        if (status == 0) {
            return new ResponseEntity<>("Administrador eliminado exitosamente", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("Error al eliminar", HttpStatus.NOT_FOUND);
        }
    }
    


    @PostMapping(path = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(@RequestParam String username,
                                         @RequestParam String password,
                                         @RequestParam String nombreCompleto,
                                         @RequestParam String email,
                                         @RequestParam String fotoPerfil) {
        AdministradorDTO newAdmin = new AdministradorDTO(username, password, nombreCompleto, email, fotoPerfil);
        int status = adminService.create(newAdmin);
        if (status == 0) {
            return new ResponseEntity<>("Administrador creado exitosamente", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Error al crear el administrador, puede que el usuario o correo ya esté en uso", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping(path = "/update")
    public ResponseEntity<String> update(@RequestParam Long id,
                                         @RequestParam String username,
                                         @RequestParam String password,
                                         @RequestParam String nombreCompleto,
                                         @RequestParam String email,
                                         @RequestParam String fotoPerfil) {
        AdministradorDTO newAdmin = new AdministradorDTO(username, password, nombreCompleto, email, fotoPerfil);
        int status = adminService.updateById(id, newAdmin);
        if (status == 0) {
            return new ResponseEntity<>("Administrador actualizado exitosamente", HttpStatus.ACCEPTED);
        } else if (status == 1) {
            return new ResponseEntity<>("El nuevo usuario o correo ya está en uso", HttpStatus.IM_USED);
        } else if (status == 2) {
            return new ResponseEntity<>("Administrador no encontrado", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>("Error al actualizar", HttpStatus.BAD_REQUEST);
        }
    }

//----------------------------------Evento----------------------------------------------------//
  
    @PostMapping(path = "/evento/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createEvento(@RequestBody EventoDTO eventoDTO) {
        int status = eventoService.create(eventoDTO);
        if (status == 0) {
            return new ResponseEntity<>("Evento creado exitosamente", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Error al crear el evento, puede que el título ya esté en uso", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/evento/getall")
    public ResponseEntity<List<EventoDTO>> getAllEventos() {
        List<EventoDTO> eventos = eventoService.getAll();
        if (eventos.isEmpty()) {
            return new ResponseEntity<>(eventos, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(eventos, HttpStatus.OK);
        }
    }

    @GetMapping("/evento/getbyid/{id}")
    public ResponseEntity<EventoDTO> getEventoById(@PathVariable Long id) {
        EventoDTO evento = eventoService.getById(id);
        if (evento != null) {
            return new ResponseEntity<>(evento, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "/evento/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateEvento(@RequestParam Long id, @RequestBody EventoDTO eventoDTO) {
        int status = eventoService.updateById(id, eventoDTO);
        if (status == 0) {
            return new ResponseEntity<>("Evento actualizado exitosamente", HttpStatus.ACCEPTED);
        } else if (status == 1) {
            return new ResponseEntity<>("El nuevo título ya está en uso", HttpStatus.IM_USED);
        } else if (status == 2) {
            return new ResponseEntity<>("Evento no encontrado", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>("Error al actualizar el evento", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/evento/deletebyid/{id}")
    public ResponseEntity<String> deleteEventoById(@PathVariable Long id) {
        int status = eventoService.deleteById(id);
        if (status == 0) {
            return new ResponseEntity<>("Evento eliminado exitosamente", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("Error al eliminar el evento", HttpStatus.NOT_FOUND);
        }
    }
//---------------------------------------------------------------LinkValioso-------------------------------------------------------------------//

    @PostMapping(path = "/linkvalioso/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createLinkValioso(@RequestBody LinkValiosoDTO linkDTO) {
        int status = linkValiosoService.create(linkDTO);
        if (status == 0) {
            return new ResponseEntity<>("Link valioso creado exitosamente", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Error al crear el link valioso, puede que el nombre ya esté en uso", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/linkvalioso/getall")
    public ResponseEntity<List<LinkValiosoDTO>> getAllLinksValiosos() {
        List<LinkValiosoDTO> links = linkValiosoService.getAll();
        if (links.isEmpty()) {
            return new ResponseEntity<>(links, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(links, HttpStatus.OK);
        }
    }

    @GetMapping("/linkvalioso/getbyid/{id}")
    public ResponseEntity<LinkValiosoDTO> getLinkValiosoById(@PathVariable Long id) {
        LinkValiosoDTO link = linkValiosoService.getById(id);
        if (link != null) {
            return new ResponseEntity<>(link, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "/linkvalioso/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateLinkValioso(@RequestParam Long id, @RequestBody LinkValiosoDTO linkDTO) {
        int status = linkValiosoService.updateById(id, linkDTO);
        if (status == 0) {
            return new ResponseEntity<>("Link valioso actualizado exitosamente", HttpStatus.ACCEPTED);
        } else if (status == 1) {
            return new ResponseEntity<>("El nombre ya está en uso", HttpStatus.IM_USED);
        } else if (status == 2) {
            return new ResponseEntity<>("Link valioso no encontrado", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>("Error al actualizar el link valioso", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/linkvalioso/deletebyid/{id}")
    public ResponseEntity<String> deleteLinkValiosoById(@PathVariable Long id) {
        int status = linkValiosoService.deleteById(id);
        if (status == 0) {
            return new ResponseEntity<>("Link valioso eliminado exitosamente", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("Error al eliminar el link valioso", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/linkvalioso/deletebyname")
    public ResponseEntity<String> deleteLinkValiosoByName(@RequestParam String nombre) {
        int status = linkValiosoService.deleteByTitulo(nombre);
        if (status == 0) {
            return new ResponseEntity<>("Link valioso eliminado exitosamente", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("Error al eliminar el link valioso", HttpStatus.NOT_FOUND);
        }
    }
    
  //----------------------------------Docente----------------------------------------------------//

    @GetMapping("/docente/getall")
    public ResponseEntity<List<DocenteDTO>> getAllDocentes() {
        List<DocenteDTO> docentes = docenteService.getAll();
        if (docentes.isEmpty()) {
            return new ResponseEntity<>(docentes, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(docentes, HttpStatus.OK);
        }
    }

    @GetMapping("/docente/getbyid/{id}")
    public ResponseEntity<DocenteDTO> getDocenteById(@PathVariable Long id) {
        DocenteDTO docente = docenteService.getById(id);
        if (docente != null) {
            return new ResponseEntity<>(docente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "/docente/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateDocente(@RequestParam Long id, @RequestBody DocenteDTO docenteDTO) {
        int status = docenteService.updateById(id, docenteDTO);
        if (status == 0) {
            return new ResponseEntity<>("Docente actualizado exitosamente", HttpStatus.ACCEPTED);
        } else if (status == 1) {
            return new ResponseEntity<>("El nuevo usuario o correo ya está en uso", HttpStatus.IM_USED);
        } else if (status == 2) {
            return new ResponseEntity<>("Docente no encontrado", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>("Error al actualizar el docente", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/docente/deletebyid/{id}")
    public ResponseEntity<String> deleteDocenteById(@PathVariable Long id) {
        int status = docenteService.deleteById(id);
        if (status == 0) {
            return new ResponseEntity<>("Docente eliminado exitosamente", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("Error al eliminar el docente", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/docente/deletebyname")
    public ResponseEntity<String> deleteDocenteByName(@RequestParam String nombreCompleto) {
        int status = docenteService.deleteByNombre(nombreCompleto);
        if (status == 0) {
            return new ResponseEntity<>("Docente eliminado exitosamente", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("Error al eliminar el docente", HttpStatus.NOT_FOUND);
        }
    }

    //----------------------------------Estudiante----------------------------------------------------//

    @GetMapping("/estudiante/getall")
    public ResponseEntity<List<EstudianteDTO>> getAllEstudiantes() {
        List<EstudianteDTO> estudiantes = estudianteService.getAll();
        if (estudiantes.isEmpty()) {
            return new ResponseEntity<>(estudiantes, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(estudiantes, HttpStatus.OK);
        }
    }

    @GetMapping("/estudiante/getbyid/{id}")
    public ResponseEntity<EstudianteDTO> getEstudianteById(@PathVariable Long id) {
        EstudianteDTO estudiante = estudianteService.getById(id);
        if (estudiante != null) {
            return new ResponseEntity<>(estudiante, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "/estudiante/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateEstudiante(@RequestParam Long id, @RequestBody EstudianteDTO estudianteDTO) {
        int status = estudianteService.updateById(id, estudianteDTO);
        if (status == 0) {
            return new ResponseEntity<>("Estudiante actualizado exitosamente", HttpStatus.ACCEPTED);
        } else if (status == 1) {
            return new ResponseEntity<>("El nuevo usuario o correo ya está en uso", HttpStatus.IM_USED);
        } else if (status == 2) {
            return new ResponseEntity<>("Estudiante no encontrado", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>("Error al actualizar el estudiante", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/estudiante/deletebyid/{id}")
    public ResponseEntity<String> deleteEstudianteById(@PathVariable Long id) {
        int status = estudianteService.deleteById(id);
        if (status == 0) {
            return new ResponseEntity<>("Estudiante eliminado exitosamente", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("Error al eliminar el estudiante", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/estudiante/deletebyname")
    public ResponseEntity<String> deleteEstudianteByName(@RequestParam String nombreCompleto) {
        int status = estudianteService.deleteByNombre(nombreCompleto);
        if (status == 0) {
            return new ResponseEntity<>("Estudiante eliminado exitosamente", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("Error al eliminar el estudiante", HttpStatus.NOT_FOUND);
        }
    }
}