/**
 * Clase HttpClientSynchronous
 * Proyecto: proyectocorte2frontadulto
 * Paquete: co.edu.unbosque.controller
 *
 * Descripción: Documentación pendiente.
 */
package co.edu.unbosque.controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

// TODO: Auto-generated Javadoc
/**
 * The Class HttpClientSynchronous.
 */
public class HttpClientSynchronous {

	/** The Constant httpClient. */
	private static final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1)
			.connectTimeout(Duration.ofSeconds(10)).build();

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InterruptedException the interrupted exception
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println("//// GET ////");
		String geolocation="https://ipgeolocation.abstractapi.com/v1/?";
		geolocation+="api_key=d8706a8e487c4b608c26e64aa216e16a";
		System.out.println(doGetAndParse(geolocation));
//		System.out.println("//// GET ////");
//		System.out.println(doGetWithHeaders("https://www.virustotal.com/api/v3/analyses/YTc4NmY3MzVjYzE5MTk2NjhmOGNkMWI1OGU1OTRmNDE6MTcxMzc4OTM3Ng=="));
//		
		//System.out.println("//// POST ////");
		//System.out.println(doPost("https://httpbin.org/post", "{\"name\":\"mkyong\",\"notes\":\"hello\"}"));
//		System.out.println("//// GET ////");
//		System.out.println(doGet("http://localhost:8082/user/getall"));
		//System.out.println("//// PUT ////");
		//System.out.println(doPut("https://httpbin.org/put", "{\"name\":\"mkyong\",\"notes\":\"hello\"}"));
		//System.out.println("//// DELETE ////");
		//System.out.println(doDelete("https://httpbin.org/delete"));
	}
	
	/**
	 * Do get and parse.
	 *
	 * @param url the url
	 * @return the string
	 */
	public static String doGetAndParse(String url) {
		HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url))
				.setHeader("User-Agent", "Java 11 HttpClient Bot").build();

		HttpResponse<String> response = null;
		try {
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("status code -> " + response.statusCode());

		String uglyJson = response.body();
		return prettyPrintUsingGson(uglyJson);
	}
	
	/**
	 * Do get with headers.
	 *
	 * @param url the url
	 * @return the string
	 */
	public static String doGetWithHeaders(String url) {
		HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url))
				.setHeader("X-Apikey", "b022b08d83bbbfd856d2f1e6080a08cc93a24c38db6537639d2c0b5bd84c0701")
				.build();

		HttpResponse<String> response = null;
		try {
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("status code -> " + response.statusCode());

		String uglyJson = response.body();
		Gson json = new Gson();
		return prettyPrintUsingGson(uglyJson);
	}
	
	/**
	 * Do get.
	 *
	 * @param url the url
	 * @return the string
	 */
	public static String doGet(String url) {
		HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url))
				.setHeader("User-Agent", "Java 11 HttpClient Bot").build();

		HttpResponse<String> response = null;
		try {
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("status code -> " + response.statusCode());

		String uglyJson = response.body();
		return prettyPrintUsingGson(uglyJson);
	}

	/**
	 * Do post.
	 *
	 * @param url the url
	 * @param json the json
	 * @return the string
	 */
	public static String doPost(String url, String json) {

		// add json header
		HttpRequest request = HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.ofString(json))
				.uri(URI.create(url)).setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
				.header("Content-Type", "application/json").build();

		HttpResponse<String> response = null;
		try {
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("status code -> " + response.statusCode());

		return prettyPrintUsingGson(response.body());
	}

	/**
	 * Do put.
	 *
	 * @param url the url
	 * @param json the json
	 * @return the string
	 */
	public static String doPut(String url, String json) {

		// add json header
		HttpRequest request = HttpRequest.newBuilder().PUT(HttpRequest.BodyPublishers.ofString(json))
				.uri(URI.create(url)).setHeader("User-Agent", "Java 11 HttpClient Bot")
				.header("Content-Type", "application/json").build();

		HttpResponse<String> response = null;
		try {
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("status code -> " + response.statusCode());
		System.out.println(response.body());
		return prettyPrintUsingGson(response.body());
	}

	/**
	 * Do delete.
	 *
	 * @param url the url
	 * @return the string
	 */
	public static String doDelete(String url) {

		// add json header
		HttpRequest request = HttpRequest.newBuilder().DELETE().uri(URI.create(url))
				.setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
				.header("Content-Type", "application/json").build();

		HttpResponse<String> response = null;
		try {
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("status code -> " + response.statusCode());

		return prettyPrintUsingGson(response.body());
	}

	/**
	 * Pretty print using gson.
	 *
	 * @param uglyJson the ugly json
	 * @return the string
	 */
	@SuppressWarnings("deprecation")
	public static String prettyPrintUsingGson(String uglyJson) {
	Gson gson = new GsonBuilder().setLenient().setPrettyPrinting().create();
		JsonElement jsonElement = JsonParser.parseString(uglyJson);
		String prettyJsonString = gson.toJson(jsonElement);
		return prettyJsonString;
	}

}