/**
 * Clase ExceptionChecker
 * Proyecto: proyectocorte2back
 * Paquete: co.edu.unbosque.proyectocorte2back.util
 *
 * Descripción: Documentación pendiente.
 */
	package co.edu.unbosque.proyectocorte2back.util;
	
	import java.time.LocalDate;
	
	
	// TODO: Auto-generated Javadoc
/**
	 * The Class ExceptionChecker.
	 */
	public class ExceptionChecker {
	
		/**
		 * Check not null or empty.
		 *
		 * @param value the value
		 * @param fieldName the field name
		 */
		public static void checkNotNullOrEmpty(String value, String fieldName) {
			if (value == null) {
				throw new IllegalArgumentException("El campo '" + fieldName + "' no puede ser nulo.");
			}
			if (value.trim().isEmpty()) {
				throw new IllegalArgumentException("El campo '" + fieldName + "' no puede estar vacío.");
			}
		}
	
		/**
		 * Check string length.
		 *
		 * @param value the value
		 * @param min the min
		 * @param max the max
		 * @param fieldName the field name
		 */
		public static void checkStringLength(String value, int min, int max, String fieldName) {
			checkNotNullOrEmpty(value, fieldName);
			int length = value.trim().length();
			if (length < min || length > max) {
				throw new IllegalArgumentException(
						"El campo '" + fieldName + "' debe tener entre " + min + " y " + max + " caracteres.");
			}
		}
	
		/**
		 * Check only letters.
		 *
		 * @param value the value
		 * @param fieldName the field name
		 */
		public static void checkOnlyLetters(String value, String fieldName) {
		    checkNotNullOrEmpty(value, fieldName);
		
		    if (!value.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ¿?¡!.,;\\-]+$")) {
		        throw new IllegalArgumentException(
		            "El campo '" + fieldName + "' solo puede contener letras, espacios y signos de puntuación básicos.");
		    }
		}
	
		/**
		 * Check not null date.
		 *
		 * @param date the date
		 * @param fieldName the field name
		 */
		public static void checkNotNullDate(LocalDate date, String fieldName) {
		    if (date == null) {
		        throw new IllegalArgumentException("El campo '" + fieldName + "' no puede ser nulo.");
		    }
		}
	
		/**
		 * Check not past date.
		 *
		 * @param date the date
		 * @param fieldName the field name
		 */
		public static void checkNotPastDate(LocalDate date, String fieldName) {
			checkNotNullDate(date, fieldName);
			if (date.isBefore(LocalDate.now())) {
				throw new IllegalArgumentException("El campo '" + fieldName + "' no puede ser una fecha pasada.");
			}
		}
		 
 		/**
 		 * Check positive int.
 		 *
 		 * @param value the value
 		 * @param fieldName the field name
 		 */
 		public static void checkPositiveInt(int value, String fieldName) {
		        if (value < 0) {
		            throw new IllegalArgumentException("El campo '" + fieldName + "' no puede ser negativo.");
		        }
		    }
	
	}
