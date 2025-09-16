package co.edu.unbosque.proyectocorte2back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.unbosque.proyectocorte2back.dto.TemarioDTO;
import co.edu.unbosque.proyectocorte2back.dto.SubtemaDTO;
import co.edu.unbosque.proyectocorte2back.dto.DetalleSubtemaDTO;
import co.edu.unbosque.proyectocorte2back.dto.ProblemaDTO;
import co.edu.unbosque.proyectocorte2back.dto.LibroDTO;
import co.edu.unbosque.proyectocorte2back.dto.EventoDTO;
import co.edu.unbosque.proyectocorte2back.dto.LinkValiosoDTO;
import co.edu.unbosque.proyectocorte2back.services.TemarioService;
import co.edu.unbosque.proyectocorte2back.services.SubtemaService;
import co.edu.unbosque.proyectocorte2back.services.DetalleSubtemaService;
import co.edu.unbosque.proyectocorte2back.services.ProblemaService;
import co.edu.unbosque.proyectocorte2back.services.LibroService;
import co.edu.unbosque.proyectocorte2back.services.EventoService;
import co.edu.unbosque.proyectocorte2back.services.LinkValiosoService;

@RestController
@RequestMapping("/estudiante")
@CrossOrigin(origins = { "*" })
public class EstudianteController {

    @Autowired
    private TemarioService temarioService;

    @Autowired
    private SubtemaService subtemaService;

    @Autowired
    private DetalleSubtemaService detalleSubtemaService;

    @Autowired
    private ProblemaService problemaService;

    @Autowired
    private LibroService libroService;

    @Autowired
    private EventoService eventoService;

    @Autowired
    private LinkValiosoService linkValiosoService;

    // Ver temarios
    @GetMapping("/temario/getall")
    public ResponseEntity<List<TemarioDTO>> getAllTemarios() {
        List<TemarioDTO> temarios = temarioService.getAll();
        if (temarios.isEmpty()) {
            return new ResponseEntity<>(temarios, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(temarios, HttpStatus.OK);
        }
    }

    @GetMapping("/temario/getbyid/{id}")
    public ResponseEntity<TemarioDTO> getTemarioById(@PathVariable Long id) {
        TemarioDTO temario = temarioService.getById(id);
        if (temario != null) {
            return new ResponseEntity<>(temario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Ver subtemas
    @GetMapping("/subtema/getall")
    public ResponseEntity<List<SubtemaDTO>> getAllSubtemas() {
        List<SubtemaDTO> subtemas = subtemaService.getAll();
        if (subtemas.isEmpty()) {
            return new ResponseEntity<>(subtemas, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(subtemas, HttpStatus.OK);
        }
    }

    @GetMapping("/subtema/getbyid/{id}")
    public ResponseEntity<SubtemaDTO> getSubtemaById(@PathVariable Long id) {
        SubtemaDTO subtema = subtemaService.getById(id);
        if (subtema != null) {
            return new ResponseEntity<>(subtema, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Ver detalle subtema
    @GetMapping("/detallesubtema/getall")
    public ResponseEntity<List<DetalleSubtemaDTO>> getAllDetallesSubtema() {
        List<DetalleSubtemaDTO> detalles = detalleSubtemaService.getAll();
        if (detalles.isEmpty()) {
            return new ResponseEntity<>(detalles, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(detalles, HttpStatus.OK);
        }
    }

    @GetMapping("/detallesubtema/getbyid/{id}")
    public ResponseEntity<DetalleSubtemaDTO> getDetalleSubtemaById(@PathVariable Long id) {
        DetalleSubtemaDTO detalle = detalleSubtemaService.getById(id);
        if (detalle != null) {
            return new ResponseEntity<>(detalle, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Ver problemas
    @GetMapping("/problema/getall")
    public ResponseEntity<List<ProblemaDTO>> getAllProblemas() {
        List<ProblemaDTO> problemas = problemaService.getAll();
        if (problemas.isEmpty()) {
            return new ResponseEntity<>(problemas, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(problemas, HttpStatus.OK);
        }
    }

    @GetMapping("/problema/getbyid/{id}")
    public ResponseEntity<ProblemaDTO> getProblemaById(@PathVariable Long id) {
        ProblemaDTO problema = problemaService.getById(id);
        if (problema != null) {
            return new ResponseEntity<>(problema, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Ver libros
    @GetMapping("/libro/getall")
    public ResponseEntity<List<LibroDTO>> getAllLibros() {
        List<LibroDTO> libros = libroService.getAll();
        if (libros.isEmpty()) {
            return new ResponseEntity<>(libros, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(libros, HttpStatus.OK);
        }
    }

    @GetMapping("/libro/getbyid/{id}")
    public ResponseEntity<LibroDTO> getLibroById(@PathVariable Long id) {
        LibroDTO libro = libroService.getById(id);
        if (libro != null) {
            return new ResponseEntity<>(libro, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Ver eventos
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

    // Ver links valiosos
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
}