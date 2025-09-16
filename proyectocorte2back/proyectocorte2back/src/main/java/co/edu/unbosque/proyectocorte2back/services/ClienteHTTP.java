package co.edu.unbosque.proyectocorte2back.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient.Version;
import java.time.Duration;

public class ClienteHTTP {
	private static final HttpClient CLIENT = HttpClient.newBuilder().version(Version.HTTP_2)
			.connectTimeout(Duration.ofSeconds(200)).build();

	public static String doGet(String url) {
		HttpRequest solicitud = HttpRequest.newBuilder().GET().uri(URI.create(url))
				.header("Content-Type", "aplication/json").build();
		HttpResponse<String> respuesta = null;

		try {
			respuesta = CLIENT.send(solicitud, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {

			System.out.println("Error al solicitar los datos");
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.out.println("Error de interrupcion de la comunicacion");
			e.printStackTrace();
		}
		return respuesta.statusCode() + "\t" + respuesta.body();
	}
}
