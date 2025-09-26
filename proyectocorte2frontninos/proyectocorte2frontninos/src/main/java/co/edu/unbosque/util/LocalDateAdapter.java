/**
 * Clase LocalDateAdapter
 * Proyecto: proyectocorte2frontninos
 * Paquete: co.edu.unbosque.util
 *
 * Descripción: Documentación pendiente.
 */
package co.edu.unbosque.util;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.time.LocalDate;

// TODO: Auto-generated Javadoc
/**
 * The Class LocalDateAdapter.
 */
public class LocalDateAdapter extends TypeAdapter<LocalDate> {
    
    /**
     * Write.
     *
     * @param out the out
     * @param value the value
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Override
    public void write(JsonWriter out, LocalDate value) throws IOException {
        out.value(value != null ? value.toString() : null); // yyyy-MM-dd
    }

    /**
     * Read.
     *
     * @param in the in
     * @return the local date
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Override
    public LocalDate read(JsonReader in) throws IOException {
        String str = in.nextString();
        return (str != null && !str.isEmpty()) ? LocalDate.parse(str) : null;
    }
}