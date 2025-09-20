package co.edu.unbosque.proyectocorte2back.util;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import co.edu.unbosque.proyectocorte2back.dto.LibroDTO;
import co.edu.unbosque.proyectocorte2back.dto.ProblemaDTO;
import co.edu.unbosque.proyectocorte2back.dto.SubtemaDTO;
import co.edu.unbosque.proyectocorte2back.dto.TemarioDTO;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;


import java.io.ByteArrayOutputStream;

import java.util.List;

@Service
public class ArchivoUtil {

    private final String uploadDir = "archivos/pdf/";

    public String guardarArchivo(MultipartFile archivo) throws IOException {
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String nombreArchivo = System.currentTimeMillis() + "_" + archivo.getOriginalFilename();
        Path ruta = Paths.get(uploadDir + nombreArchivo);
        Files.write(ruta, archivo.getBytes());
        return nombreArchivo;
    }

    public File obtenerArchivo(String nombreArchivo) {
        return new File(uploadDir + nombreArchivo);
    }
    
    public byte[] generarPdfTemario(List<TemarioDTO> temarios) throws IOException {
        try (PDDocument document = new PDDocument(); ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            PDPage page = new PDPage();
            document.addPage(page);
            PDPageContentStream content = new PDPageContentStream(document, page);

            content.setFont(PDType1Font.HELVETICA_BOLD, 16);
            content.beginText();
            content.newLineAtOffset(50, 750);
            content.showText("Listado de Temarios");
            content.setFont(PDType1Font.HELVETICA, 12);

            int y = 730;
            for (TemarioDTO temario : temarios) {
                content.newLineAtOffset(0, y - 750);
                content.showText("Temario: " + temario.getTitulo());
                y -= 20;
                if (temario.getSubtemas() != null) {
                    for (SubtemaDTO subtema : temario.getSubtemas()) {
                        content.newLineAtOffset(0, y - 750);
                        content.showText("  Subtema: " + subtema.getNombre());
                        y -= 20;
                        if (subtema.getDetalle() != null) {
                            content.newLineAtOffset(0, y - 750);
                            content.showText("    Detalle: " + subtema.getDetalle().getDescripcion());
                            y -= 20;
                        }
                    }
                }
            }
            content.endText();
            content.close();
            document.save(baos);
            return baos.toByteArray();
        }
    }

    public byte[] generarPdfProblemas(List<ProblemaDTO> problemas) throws IOException {
        try (PDDocument document = new PDDocument(); ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            PDPage page = new PDPage();
            document.addPage(page);
            PDPageContentStream content = new PDPageContentStream(document, page);

            content.setFont(PDType1Font.HELVETICA_BOLD, 16);
            content.beginText();
            content.newLineAtOffset(50, 750);
            content.showText("Listado de Problemas");
            content.setFont(PDType1Font.HELVETICA, 12);

            int y = 730;
            for (ProblemaDTO problema : problemas) {
                content.newLineAtOffset(0, y - 750);
                content.showText("Título: " + problema.getTitulo());
                y -= 20;
                content.newLineAtOffset(0, y - 750);
                content.showText("Tema: " + problema.getTema());
                y -= 20;
                content.newLineAtOffset(0, y - 750);
                content.showText("Juez: " + problema.getJuez());
                y -= 20;
                content.newLineAtOffset(0, y - 750);
                content.showText("URL: " + problema.getUrl());
                y -= 30;
            }
            content.endText();
            content.close();
            document.save(baos);
            return baos.toByteArray();
        }
    }

    public byte[] generarPdfLibros(List<LibroDTO> libros) throws IOException {
        try (PDDocument document = new PDDocument(); ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            PDPage page = new PDPage();
            document.addPage(page);
            PDPageContentStream content = new PDPageContentStream(document, page);

            content.setFont(PDType1Font.HELVETICA_BOLD, 16);
            content.beginText();
            content.newLineAtOffset(50, 750);
            content.showText("Listado de Libros");
            content.setFont(PDType1Font.HELVETICA, 12);

            int y = 730;
            for (LibroDTO libro : libros) {
                content.newLineAtOffset(0, y - 750);
                content.showText("Título: " + libro.getTitulo());
                y -= 20;
                content.newLineAtOffset(0, y - 750);
                content.showText("Autor: " + libro.getAutor());
                y -= 20;
                content.newLineAtOffset(0, y - 750);
                content.showText("Año: " + libro.getAnio());
                y -= 20;
                content.newLineAtOffset(0, y - 750);
                content.showText("Descripción: " + libro.getDescripcion());
                y -= 30;
            }
            content.endText();
            content.close();
            document.save(baos);
            return baos.toByteArray();
        }
    }

    public String guardarArchivoImagen(MultipartFile archivo) throws IOException {
        String uploadDir = "archivos/fotoperfil/";
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String nombreArchivo = System.currentTimeMillis() + "_" + archivo.getOriginalFilename();
        Path ruta = Paths.get(uploadDir + nombreArchivo);
        Files.write(ruta, archivo.getBytes());
        return nombreArchivo;
    }
}