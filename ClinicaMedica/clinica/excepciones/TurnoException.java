/**
 * Autor: Candia Tobias Julian.
 * Fecha: Octubre 2025.
 * Versión: 1.0.
 */

package clinica.excepciones;

// Clase de excepción personalizada utilizada para manejar errores relacionados con la asignación o gestión de turnos en la clínica.
public class TurnoException extends Exception {
    public TurnoException(String mensaje) {
        super(mensaje);
    }
}
