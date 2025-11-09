package clinica.gestionClinicaMedica;

import clinica.conexionBD.AsignacionTurnoModelo;
import clinica.conexionBD.Turno;
import clinica.excepciones.TurnoException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clase Clinica
 * Representa el núcleo del sistema de gestión clínica.
 * Administra pacientes, médicos y turnos dentro de la aplicación.
 */
public class Clinica {

    private AsignacionTurnoModelo turnoModelo;

    private String nombre;
    private String descripcion;

    private List<Paciente> pacientes;
    private List<Medico> medicos;
    private List<Turno> turnos;

    public Clinica(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.pacientes = new ArrayList<>();
        this.medicos = new ArrayList<>();
        this.turnos = new ArrayList<>();
        this.turnoModelo = new AsignacionTurnoModelo();
    }

    // -------------------------
    // REGISTRO DE ENTIDADES
    // -------------------------

    public void registrarPaciente(Paciente paciente) {
        pacientes.add(paciente);
        System.out.println("Paciente registrado: " + paciente.getNombre());
    }

    public void registrarMedico(Medico medico) {
        medicos.add(medico);
        System.out.println("Médico registrado: " + medico.getNombre());
    }

    // -------------------------
    // ASIGNACIÓN DE TURNOS POR CONSOLA
    // -------------------------

    public void asignarTurnoPorConsola() {
        Scanner scanner = new Scanner(System.in);

        // Seleccionar paciente
        System.out.println("Lista de Pacientes:");
        for (Paciente p : pacientes) {
            System.out.println(p.getIdPaciente() + " - " + p.getNombre());
        }
        System.out.print("Ingrese el ID del paciente: ");
        int idPaciente = scanner.nextInt();

        // Seleccionar médico
        System.out.println("Lista de Médicos:");
        for (Medico m : medicos) {
            System.out.println(m.getIdMedico() + " - " + m.getNombre());
        }
        System.out.print("Ingrese el ID del médico: ");
        int idMedico = scanner.nextInt();

        scanner.nextLine(); // limpiar buffer

        // Fecha y hora
        System.out.print("Ingrese la fecha del turno (YYYY-MM-DD): ");
        String fechaStr = scanner.nextLine();
        LocalDate fecha = LocalDate.parse(fechaStr);

        System.out.print("Ingrese la hora del turno (HH:mm:ss): ");
        String horaStr = scanner.nextLine();
        LocalTime hora = LocalTime.parse(horaStr);

        // Estado del turno
        System.out.print("Ingrese el ID del estado del turno: ");
        int idEstadoTurno = scanner.nextInt();

        try {
            Paciente paciente = buscarPacientePorId(idPaciente);
            Medico medico = buscarMedicoPorId(idMedico);

            if (paciente == null) {
                System.out.println("Paciente no encontrado.");
                return;
            }
            if (medico == null) {
                System.out.println("Medico no encontrado.");
                return;
            }

            // Validar choques de turnos
            for (Turno t : turnos) {
                if (t.getIdMedico() == idMedico
                        && t.getFechaTurno().equals(fecha)
                        && t.getHoraTurno().equals(hora)) {
                    System.out.println("El medico ya tiene un turno asignado en ese horario.");
                    return;
                }
                if (t.getIdPaciente() == idPaciente
                        && t.getFechaTurno().equals(fecha)
                        && t.getHoraTurno().equals(hora)) {
                    System.out.println("El paciente ya tiene un turno asignado en ese horario.");
                    return;
                }
            }

            // Guardar en BD
            int idTurnoGenerado = turnoModelo.insertarTurnoConRetorno(
                    idPaciente,
                    idMedico,
                    fecha.toString(),
                    hora.toString(),
                    idEstadoTurno
            );

            if (idTurnoGenerado == -1) {
                System.out.println("Error al guardar el turno en la base de datos.");
                return;
            }

            EstadoTurno estado = EstadoTurno.fromId(idEstadoTurno);
            Turno nuevoTurno = new Turno(
                idTurnoGenerado,
                idPaciente,
                idMedico,
                fecha,
                hora,
                estado
            );

            turnos.add(nuevoTurno);
            System.out.println("Turno asignado correctamente con ID " + idTurnoGenerado);

        } catch (Exception e) {
            System.out.println("Error al asignar turno: " + e.getMessage());
        }
    }

    // -------------------------
    // BÚSQUEDAS
    // -------------------------

    public Paciente buscarPacientePorId(int id) {
        return pacientes.stream()
                .filter(p -> p.getIdPaciente() == id)
                .findFirst()
                .orElse(null);
    }

    public Medico buscarMedicoPorId(int id) {
        return medicos.stream()
                .filter(m -> m.getIdMedico() == id)
                .findFirst()
                .orElse(null);
    }

    public Turno buscarTurnoPorId(int id) {
        return turnos.stream()
                .filter(t -> t.getIdTurno() == id)
                .findFirst()
                .orElse(null);
    }

    // -------------------------
    // REPORTES
    // -------------------------

    public void generarReporte() {
        System.out.println("\n--- Reporte General de Turnos ---");
        if (turnos.isEmpty()) {
            System.out.println("No hay turnos registrados.");
        } else {
            turnos.forEach(System.out::println);
        }
    }

    // -------------------------
    // GETTERS
    // -------------------------

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