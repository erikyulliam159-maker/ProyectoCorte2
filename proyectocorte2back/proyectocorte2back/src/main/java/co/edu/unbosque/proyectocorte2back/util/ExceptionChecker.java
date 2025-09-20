	package co.edu.unbosque.proyectocorte2back.util;
	
	import java.time.LocalDate;
	
	
	public class ExceptionChecker {
	
		public static void checkNotNullOrEmpty(String value, String fieldName) {
			if (value == null) {
				throw new IllegalArgumentException("El campo '" + fieldName + "' no puede ser nulo.");
			}
			if (value.trim().isEmpty()) {
				throw new IllegalArgumentException("El campo '" + fieldName + "' no puede estar vacío.");
			}
		}
	
		public static void checkStringLength(String value, int min, int max, String fieldName) {
			checkNotNullOrEmpty(value, fieldName);
			int length = value.trim().length();
			if (length < min || length > max) {
				throw new IllegalArgumentException(
						"El campo '" + fieldName + "' debe tener entre " + min + " y " + max + " caracteres.");
			}
		}
	
		public static void checkOnlyLetters(String value, String fieldName) {
			checkNotNullOrEmpty(value, fieldName);
		
			if (!value.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$")) {
				throw new IllegalArgumentException(
						"El campo '" + fieldName + "' solo puede contener letras y espacios (sin números ni símbolos).");
			}
		}
	
		public static void checkNotNullDate(LocalDate date, String fieldName) {
		    if (date == null) {
		        throw new IllegalArgumentException("El campo '" + fieldName + "' no puede ser nulo.");
		    }
		}
	
		public static void checkNotPastDate(LocalDate date, String fieldName) {
			checkNotNullDate(date, fieldName);
			if (date.isBefore(LocalDate.now())) {
				throw new IllegalArgumentException("El campo '" + fieldName + "' no puede ser una fecha pasada.");
			}
		}
		 public static void checkPositiveInt(int value, String fieldName) {
		        if (value < 0) {
		            throw new IllegalArgumentException("El campo '" + fieldName + "' no puede ser negativo.");
		        }
		    }
	
	}
