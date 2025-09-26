/**
 * Clase LibroBean
 * Proyecto: proyectocorte2frontninos
 * Paquete: co.edu.unbosque.beans
 *
 * Descripción: Documentación pendiente.
 */
package co.edu.unbosque.beans;

import jakarta.inject.Named;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;

import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

import co.edu.unbosque.model.LibroDTO;
import co.edu.unbosque.service.LibroService;
import com.google.gson.Gson;
import jakarta.servlet.http.Part;

// TODO: Auto-generated Javadoc
/**
 * The Class LibroBean.
 */
@Named("libroBean")
@ViewScoped
public class LibroBean implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The libros. */
    private List<LibroDTO> libros;
    
    /** The libro seleccionado. */
    private LibroDTO libroSeleccionado;
    
    /** The archivo pdf. */
    private Part archivoPdf;

    /**
     * Inicializa el.
     */
    @PostConstruct
    public void init() {
        try {
            libroSeleccionado = new LibroDTO();
            cargarLibros();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Cargar libros.
     */
    public void cargarLibros() {
        libros = LibroService.doGetAll("http://localhost:8081/docente/libro/getall");
    }

    /**
     * Crear libro.
     */
    public void crearLibro() {
        try {
            boolean tieneArchivo = archivoPdf != null && archivoPdf.getSize() > 0;
            boolean tieneUrl = libroSeleccionado.getUrlpdf() != null && !libroSeleccionado.getUrlpdf().trim().isEmpty();
            
            if (!tieneArchivo) {
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", 
                      "Debe proporcionar un archivo PDF"));
                return;
            }else if (!tieneUrl) {
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", 
                          "Debe proporcionar una URL de PDF"));
                    return;
            }
            
            String boundary = "----WebKitFormBoundary" + System.currentTimeMillis();
            String urlStr = "http://localhost:8081/docente/libro/createjson";
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

            StringBuilder sb = new StringBuilder();
            sb.append("--").append(boundary).append("\r\n");
            sb.append("Content-Disposition: form-data; name=\"titulo\"\r\n\r\n")
              .append(libroSeleccionado.getTitulo()).append("\r\n");
            sb.append("--").append(boundary).append("\r\n");
            sb.append("Content-Disposition: form-data; name=\"autor\"\r\n\r\n")
              .append(libroSeleccionado.getAutor()).append("\r\n");
            sb.append("--").append(boundary).append("\r\n");
            sb.append("Content-Disposition: form-data; name=\"anio\"\r\n\r\n")
              .append(libroSeleccionado.getAnio()).append("\r\n");
            sb.append("--").append(boundary).append("\r\n");
            sb.append("Content-Disposition: form-data; name=\"descripcion\"\r\n\r\n")
              .append(libroSeleccionado.getDescripcion()).append("\r\n");
            sb.append("--").append(boundary).append("\r\n");
            sb.append("Content-Disposition: form-data; name=\"imagenPortada\"\r\n\r\n")
              .append(libroSeleccionado.getImagenPortada() != null ? libroSeleccionado.getImagenPortada() : "").append("\r\n");
            sb.append("--").append(boundary).append("\r\n");
            sb.append("Content-Disposition: form-data; name=\"urlPdf\"\r\n\r\n")
              .append(libroSeleccionado.getUrlpdf() != null ? libroSeleccionado.getUrlpdf() : "").append("\r\n");

            
            sb.append("--").append(boundary).append("\r\n");
            if (tieneArchivo) {
                sb.append("Content-Disposition: form-data; name=\"file\"; filename=\"")
                  .append(archivoPdf.getSubmittedFileName()).append("\"\r\n");
                sb.append("Content-Type: application/pdf\r\n\r\n");
            } else {
                sb.append("Content-Disposition: form-data; name=\"file\"; filename=\"\"\r\n");
                sb.append("Content-Type: application/octet-stream\r\n\r\n");
            }

            byte[] inicio = sb.toString().getBytes(StandardCharsets.UTF_8);
            byte[] fin = ("\r\n--" + boundary + "--\r\n").getBytes(StandardCharsets.UTF_8);
            try (var os = conn.getOutputStream()) {
                os.write(inicio);
                
                if (tieneArchivo) {
                    try (InputStream is = archivoPdf.getInputStream()) {
                        byte[] buffer = new byte[4096];
                        int bytesRead;
                        while ((bytesRead = is.read(buffer)) != -1) {
                            os.write(buffer, 0, bytesRead);
                        }
                    }
                }
                
                os.write(fin);
            }

            int responseCode = conn.getResponseCode();
            
            if (responseCode == 201) {
                libroSeleccionado = new LibroDTO();
                archivoPdf = null;
                cargarLibros();
            } else {
                try (var errorStream = conn.getErrorStream()) {
                    if (errorStream != null) {
                        String errorMessage = new String(errorStream.readAllBytes(), StandardCharsets.UTF_8);
                        System.err.println("Error del servidor: " + errorMessage);
                    }
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

 /**
  * Editar libro.
  *
  * @param libro the libro
  */
 public void editarLibro(LibroDTO libro) {
     this.libroSeleccionado = new LibroDTO(
         libro.getTitulo(),
         libro.getAutor(),
         libro.getAnio(),
         libro.getDescripcion(),
         libro.getImagenPortada(),
         libro.getPdf(),
         libro.getUrlpdf()
     );
     this.libroSeleccionado.setId(libro.getId());
     this.archivoPdf = null; 
 }


 /**
  * Guardar edicion.
  */
 public void guardarEdicion() {
     try {
    	 System.out.println("ID a editar: " + libroSeleccionado.getId());
    	 System.out.println("Título: " + libroSeleccionado.getTitulo());
    	 System.out.println("Autor: " + libroSeleccionado.getAutor());
    	 System.out.println("Año: " + libroSeleccionado.getAnio());
    	 System.out.println("URL PDF: " + libroSeleccionado.getUrlpdf());
         String url = "http://localhost:8081/docente/libro/updatejson?id=" + libroSeleccionado.getId();
         String boundary = "----WebKitFormBoundary" + System.currentTimeMillis();
         URL endpoint = new URL(url);
         HttpURLConnection conn = (HttpURLConnection) endpoint.openConnection();
         conn.setDoOutput(true);
         conn.setRequestMethod("PUT");
         conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

         StringBuilder sb = new StringBuilder();
         sb.append("--").append(boundary).append("\r\n");
         sb.append("Content-Disposition: form-data; name=\"titulo\"\r\n\r\n")
           .append(libroSeleccionado.getTitulo()).append("\r\n");
         sb.append("--").append(boundary).append("\r\n");
         sb.append("Content-Disposition: form-data; name=\"autor\"\r\n\r\n")
           .append(libroSeleccionado.getAutor()).append("\r\n");
         sb.append("--").append(boundary).append("\r\n");
         sb.append("Content-Disposition: form-data; name=\"anio\"\r\n\r\n")
           .append(libroSeleccionado.getAnio()).append("\r\n");
         sb.append("--").append(boundary).append("\r\n");
         sb.append("Content-Disposition: form-data; name=\"descripcion\"\r\n\r\n")
           .append(libroSeleccionado.getDescripcion()).append("\r\n");
         sb.append("--").append(boundary).append("\r\n");
         sb.append("Content-Disposition: form-data; name=\"imagenPortada\"\r\n\r\n")
           .append(libroSeleccionado.getImagenPortada() != null ? libroSeleccionado.getImagenPortada() : "").append("\r\n");
         sb.append("--").append(boundary).append("\r\n");
         sb.append("Content-Disposition: form-data; name=\"urlPdf\"\r\n\r\n")
           .append(libroSeleccionado.getUrlpdf() != null ? libroSeleccionado.getUrlpdf() : "").append("\r\n");

       
         sb.append("--").append(boundary).append("\r\n");
         
         if (archivoPdf != null && archivoPdf.getSize() > 0) {
             sb.append("Content-Disposition: form-data; name=\"file\"; filename=\"")
               .append(archivoPdf.getSubmittedFileName()).append("\"\r\n");
             sb.append("Content-Type: application/pdf\r\n\r\n");
         } else {
             sb.append("Content-Disposition: form-data; name=\"file\"; filename=\"\"\r\n");
             sb.append("Content-Type: application/octet-stream\r\n\r\n");
         }

         byte[] inicio = sb.toString().getBytes(StandardCharsets.UTF_8);
         byte[] fin = ("\r\n--" + boundary + "--\r\n").getBytes(StandardCharsets.UTF_8);

         try (var os = conn.getOutputStream()) {
             os.write(inicio);
             if (archivoPdf != null && archivoPdf.getSize() > 0) {
                 try (InputStream is = archivoPdf.getInputStream()) {
                     byte[] buffer = new byte[4096];
                     int bytesRead;
                     while ((bytesRead = is.read(buffer)) != -1) {
                         os.write(buffer, 0, bytesRead);
                     }
                 }
             }
             os.write(fin);
         }

         int responseCode = conn.getResponseCode();
         if (responseCode == 202) {
             libroSeleccionado = new LibroDTO();
             archivoPdf = null;
             cargarLibros();
             FacesContext.getCurrentInstance().addMessage(null,
                 new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Libro actualizado correctamente"));
         } else {
             try (var errorStream = conn.getErrorStream()) {
                 String errorMessage = errorStream != null ? new String(errorStream.readAllBytes(), StandardCharsets.UTF_8) : "Error desconocido";
                 FacesContext.getCurrentInstance().addMessage(null,
                     new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", errorMessage));
                 System.err.println("Error al actualizar: " + errorMessage);
             }
         }
     } catch (Exception e) {
         e.printStackTrace();
     }
 }

   
 /**
  * Eliminar libro.
  *
  * @param libro the libro
  */
 public void eliminarLibro(LibroDTO libro) {
	    String url = "http://localhost:8081/docente/libro/deletebyid/" + libro.getId();
	    LibroService.doDelete(url);
	    cargarLibros();
	}
   

    /**
     * Gets the libros.
     *
     * @return the libros
     */
    public List<LibroDTO> getLibros() {
        return libros;
    }

    /**
     * Sets the libros.
     *
     * @param libros the new libros
     */
    public void setLibros(List<LibroDTO> libros) {
        this.libros = libros;
    }

    /**
     * Gets the libro seleccionado.
     *
     * @return the libro seleccionado
     */
    public LibroDTO getLibroSeleccionado() {
        return libroSeleccionado;
    }

    /**
     * Sets the libro seleccionado.
     *
     * @param libroSeleccionado the new libro seleccionado
     */
    public void setLibroSeleccionado(LibroDTO libroSeleccionado) {
        this.libroSeleccionado = libroSeleccionado;
    }

	/**
	 * Gets the archivo pdf.
	 *
	 * @return the archivo pdf
	 */
	public Part getArchivoPdf() {
		return archivoPdf;
	}

	/**
	 * Sets the archivo pdf.
	 *
	 * @param archivoPdf the new archivo pdf
	 */
	public void setArchivoPdf(Part archivoPdf) {
		this.archivoPdf = archivoPdf;
	}
    
}