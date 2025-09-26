package co.edu.unbosque.util;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.time.LocalDate;

public class LocalDateAdapter extends TypeAdapter<LocalDate> {
    @Override
    public void write(JsonWriter out, LocalDate value) throws IOException {
        out.value(value != null ? value.toString() : null); // yyyy-MM-dd
    }

    @Override
    public LocalDate read(JsonReader in) throws IOException {
        String str = in.nextString();
        return (str != null && !str.isEmpty()) ? LocalDate.parse(str) : null;
    }
}