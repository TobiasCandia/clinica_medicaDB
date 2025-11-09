/**
 * Autor: Candia Tobias Julian.
 * Fecha: Octubre 2025.
 * Versión: 1.2.
 */

package clinica.excepciones;

/**
 * Excepción personalizada para manejar errores relacionados con la asignación o gestión de turnos.
 * Puede incluir una causa subyacente (por ejemplo, un error de base de datos).
 */
public class TurnoException extends Exception {

    public TurnoException(String mensaje) {
        super(mensaje);
    }

    public TurnoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
