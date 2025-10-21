package clinica.modelo;

import java.util.Date;

public class Turno {
    private int idTurno;
    private Date fecha;
    private String hora;            // Atributos
    private EstadoTurno estado;
    private Paciente paciente;
    private Medico medico;

    public Turno(int idTurno, Date fecha, String hora, EstadoTurno estado, Paciente paciente, Medico medico) {
        this.idTurno = idTurno;
        this.fecha = fecha;
        this.hora = hora;           // Constructor
        this.estado = estado;
        this.paciente = paciente;
        this.medico = medico;
    }

    // Turno t1 = new Turno(1, new Date(), "09:00", EstadoTurno.PENDIENTE, p1, m1); // Ejemplo temporal de uso

    public int getIdTurno() { return idTurno; }
    public Date getFecha() { return fecha; }
    public String getHora() { return hora; }
    public Paciente getPaciente() { return paciente; }
    public Medico getMedico() { return medico; }

    // Cambia el estado del turno a CONFIRMADO.
    // Este método se ejecuta cuando el paciente confirma su asistencia.
    public void confirmar() {
        estado = EstadoTurno.CONFIRMADO;
    }

    // Cambia el estado del turno a CANCELADO.
    // Se utiliza cuando el paciente o el médico anulan la cita previamente asignada.
    public void cancelar() {
        estado = EstadoTurno.CANCELADO;
    }

    // Permite modificar la fecha y la hora del turno, y cambia su estado a REPROGRAMADO.
    public void reprogramar(Date nuevaFecha, String nuevaHora) {
        this.fecha = nuevaFecha;
        this.hora = nuevaHora;
        estado = EstadoTurno.REPROGRAMADO;
    }

    // Devuelve una representación textual detallada del turno,
    // mostrando la información del paciente, médico, fecha, hora y estado actual.
    @Override
    public String toString() {
        return "Turno #" + idTurno + " | Paciente: " + paciente.getNombre() +
                " | Médico: " + medico.getNombre() +
                " | Fecha: " + fecha + " | Hora: " + hora +
                " | Estado: " + estado;
    }
}
