package clinica.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import clinica.excepciones.TurnoException;

public class Clinica {
    private String nombre;
    private String descripcion;
    private List<Paciente> pacientes; // Atributos
    private List<Medico> medicos;
    private List<Turno> turnos;

    public Clinica(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.pacientes = new ArrayList<>(); // Constructor
        this.medicos = new ArrayList<>();
        this.turnos = new ArrayList<>();
    }

    //Clinica clinica = new Clinica("Clínica San Martín", "Sistema de gestión de turnos médicos"); // Ejemplo temporal de uso.

    public void registrarPaciente(Paciente paciente) {
        pacientes.add(paciente);
        System.out.println("Paciente registrado: " + paciente.getNombre()); // Método para registrar un nuevo paciente a la clinica.
    }

    public void registrarMedico(Medico medico) {
        medicos.add(medico);
        System.out.println("Médico registrado: " + medico.getNombre()); // Método para registrar un nuevo medico a la clinica.
    }

    // Asigna un turno a un paciente con un medico en una fecha y hora determinadas
    public Turno asignarTurno(Paciente paciente, Medico medico, Date fecha, String hora) throws TurnoException {

        // Validar si el médico ya tiene turno ese día a esa hora
        // Validar si el paciente ya tiene turno ese día a esa hora
        for (Turno t : turnos) {
            if (t.getMedico().equals(medico) &&
                t.getFecha().equals(fecha) &&
                t.getHora().equals(hora)) {
                throw new TurnoException("El médico ya tiene un turno asignado en ese horario.");
            }
        }

        // Validar si el paciente ya tiene turno ese día a esa hora
        for (Turno t : turnos) {
            if (t.getPaciente().equals(paciente) &&
                t.getFecha().equals(fecha) &&
                t.getHora().equals(hora)) {
                throw new TurnoException("El paciente ya tiene un turno asignado en ese horario.");
            }
        }

        // Si pasa las validaciones, crear el turno, si no lanza los mensajes de excepciones
        Turno turno = new Turno(turnos.size() + 1, fecha, hora, EstadoTurno.PENDIENTE, paciente, medico);
        turnos.add(turno);
        System.out.println("Turno asignado a " + paciente.getNombre() + " con el Dr. " + medico.getNombre());
        return turno;
    }

    // Devuelve la lista completa de turnos registrados en la clínica
    public List<Turno> obtenerTurnos() {
        return turnos;
    }

    // Muestra un listado con todos los turnos registrados, incluyendo paciente, médico, fecha, hora y estado.
    public void generarReporte() {
        System.out.println("\n Reporte de Turnos");
        for (Turno turno : turnos) {
            System.out.println(turno);
        }
    }

    public Paciente buscarPacientePorId(int id) {
        for (Paciente p : pacientes) {
            if (p.getIdPaciente() == id) return p;
        }
        return null;
    }
    
    public Medico buscarMedicoPorId(int id) {
        for (Medico m : medicos) {
            if (m.getIdMedico() == id) return m;
        }
        return null;
    }
    
    public Turno buscarTurnoPorId(int id) {
        for (Turno t : turnos) {
            if (t.getIdTurno() == id) return t;
        }
        return null;
    }
    
    public List<Paciente> getPacientes() {
        return pacientes;
    }
    
    public List<Medico> getMedicos() {
        return medicos;
    }
    
    public List<Turno> getTurnos() {
        return turnos;
    }    
}
