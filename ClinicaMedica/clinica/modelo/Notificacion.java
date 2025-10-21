package clinica.modelo;

import java.util.Date;

public class Notificacion {
    private int idNotificacion;
    private String mensaje;
    private String canal; // Atributos
    private Date fechaEnvio;
    private EstadoNotificacion estado;
    private Turno turno;

    public Notificacion(int idNotificacion, String mensaje, String canal, Turno turno) {
        this.idNotificacion = idNotificacion;
        this.mensaje = mensaje;
        this.canal = canal;         // Constructor
        this.turno = turno;
        this.estado = EstadoNotificacion.PENDIENTE;
        this.fechaEnvio = new Date();
    }

    // public Notificacion(int idNotificacion, String mensaje, String canal, Turno turno) // Ejemplo temporal de uso

    // Simula el proceso de envío del mensaje al paciente.
    // Cambia el estado de la notificación a ENVIADA.
    public void enviar() {
        System.out.println("Enviando notificación al paciente " + turno.getPaciente().getNombre());
        this.estado = EstadoNotificacion.ENVIADA;
    }

    // Permite registrar la confirmación de lectura o recepción de la notificación
    // por parte del paciente, cambiando el estado a RECIBIDA.
    public void confirmarRecepcion() {
        this.estado = EstadoNotificacion.RECIBIDA;
    }

    // Devuelve una representación textual simplificada de la notificación:
    @Override
    public String toString() {
        return "Notificación #" + idNotificacion + " | Canal: " + canal + " | Estado: " + estado;
    }
}
