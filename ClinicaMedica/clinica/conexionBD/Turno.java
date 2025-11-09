package clinica.conexionBD;

import clinica.gestionClinicaMedica.EstadoTurno;
import java.time.LocalDate;
import java.time.LocalTime;

public class Turno {

    private int idTurno;
    private int idPaciente;
    private int idMedico;
    private LocalDate fechaTurno;
    private LocalTime horaTurno;
    private EstadoTurno estadoTurno;

    public Turno(int idTurno, int idPaciente, int idMedico, LocalDate fechaTurno, LocalTime horaTurno, EstadoTurno estadoTurno) {
        this.idTurno = idTurno;
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
        this.fechaTurno = fechaTurno;
        this.horaTurno = horaTurno;
        this.estadoTurno = estadoTurno;
    }

    public int getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(int idTurno) {
        this.idTurno = idTurno;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public LocalDate getFechaTurno() {
        return fechaTurno;
    }

    public void setFechaTurno(LocalDate fechaTurno) {
        this.fechaTurno = fechaTurno;
    }

    public LocalTime getHoraTurno() {
        return horaTurno;
    }

    public void setHoraTurno(LocalTime horaTurno) {
        this.horaTurno = horaTurno;
    }

    public EstadoTurno getEstadoTurno() {
        return estadoTurno;
    }

    public void setEstadoTurno(EstadoTurno estadoTurno) {
        this.estadoTurno = estadoTurno;
    }

    public int getIdEstadoTurno() {
        return estadoTurno.ordinal();
    }

    @Override
    public String toString() {
        return "Turno #" + idTurno +
               " | Médico ID: " + idMedico +
               " | Paciente ID: " + idPaciente +
               " | Fecha: " + fechaTurno +
               " | Hora: " + horaTurno +
               " | Estado: " + estadoTurno;
    }
}
