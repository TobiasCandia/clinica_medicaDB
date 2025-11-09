package clinica.conexionBD;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AsignacionTurnoControlador {

    private static final AsignacionTurnoModelo modelo = new AsignacionTurnoModelo();
    private static final AsignacionTurnoVista vista = new AsignacionTurnoVista();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE GESTION DE CLINICA CENTRAL ===");
        ejecutar();
    }

    public static void ejecutar() {
        int opcion = -1;
        do {
            try {
                System.out.print("Seleccione una opcion: ");
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1 -> listarMedicos();
                    case 2 -> listarPacientes();
                    case 3 -> asignarTurno();
                    case 4 -> listarTurnos();
                    case 5 -> registrarPaciente();
                    case 6 -> registrarMedico();
                    case 0 -> System.out.println("Saliendo del sistema...");
                    default -> System.out.println("Opcion invalida. Intente nuevamente.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Error: debe ingresar un numero válido.");
                scanner.nextLine();
            }

        } while (opcion != 0);

        vista.mostrarMensaje("=== Fin del programa ===");
    }

    private static void listarMedicos() {
        vista.mostrarMensaje("=== MEDICOS DISPONIBLES ===");
        List<String> medicos = modelo.obtenerMedicosDisponibles();
        if (medicos.isEmpty()) {
            vista.mostrarMensaje("No hay medicos registrados.");
        } else {
            vista.mostrarMedicos(medicos);
        }
    }

    private static void listarPacientes() {
        vista.mostrarMensaje("=== PACIENTES REGISTRADOS ===");
        List<String> pacientes = modelo.obtenerPacientes();
        if (pacientes.isEmpty()) {
            vista.mostrarMensaje("No hay pacientes registrados.");
        } else {
            vista.mostrarPacientes(pacientes);
        }
    }

    private static void asignarTurno() {
        vista.mostrarMensaje("=== ASIGNACION DE TURNO ===");

        var pacientes = modelo.obtenerPacientes();
        var medicos = modelo.obtenerMedicosDisponibles();

        if (pacientes.isEmpty() || medicos.isEmpty()) {
            vista.mostrarMensaje("Debe haber al menos un medico y un paciente registrados.");
            return;
        }

        vista.mostrarPacientes(pacientes);
        int idPaciente = vista.pedirEntero("Ingrese ID del paciente: ");

        vista.mostrarMedicos(medicos);
        int idMedico = vista.pedirEntero("Ingrese ID del medico: ");

        String fecha = vista.pedirFecha("Ingrese fecha (YYYY-MM-DD): ");
        String hora = vista.pedirTexto("Ingrese hora (HH:mm): ");
        int idEstadoTurno = 6;

        int idGenerado = modelo.insertarTurnoConRetorno(idPaciente, idMedico, fecha, hora, idEstadoTurno);
        if (idGenerado > 0) {
            vista.mostrarMensaje("Turno asignado correctamente (ID: " + idGenerado + ").");
        } else {
            vista.mostrarMensaje("No se pudo asignar el turno.");
        }
    }

    private static void listarTurnos() {
        vista.mostrarMensaje("=== TURNOS REGISTRADOS ===");
        List<String> turnos = modelo.listarTurnos();
        if (turnos.isEmpty()) {
            vista.mostrarMensaje("No hay turnos registrados.");
        } else {
            vista.mostrarTurnos(turnos);
        }
    }

    private static void registrarPaciente() {
        vista.mostrarMensaje("=== REGISTRO DE NUEVO PACIENTE ===");
        String nombre = vista.pedirTexto("Nombre completo: ");
        String dni = vista.pedirTexto("DNI: ");
        String telefono = vista.pedirTexto("Telefono: ");
        String email = vista.pedirTexto("Correo electronico: ");

        try {
            int idGenerado = modelo.insertarPaciente(nombre, email, telefono, dni);
            if (idGenerado > 0) {
                vista.mostrarMensaje("Paciente registrado correctamente (ID: " + idGenerado + ").");
            } else {
                vista.mostrarMensaje("No se pudo registrar el paciente.");
            }
        } catch (SQLException e) {
            vista.mostrarMensaje("Error al registrar paciente: " + e.getMessage());
        }
    }

    private static void registrarMedico() {
        vista.mostrarMensaje("=== REGISTRO DE NUEVO MEDICO ===");
        String nombre = vista.pedirTexto("Nombre: ");
        String email = vista.pedirTexto("Email: ");
        String especialidad = vista.pedirTexto("Especialidad: ");
        String matricula = vista.pedirTexto("Matricula: ");
        String telefono = vista.pedirTexto("Telefono: ");
        String horario = vista.pedirTexto("Horario de atencion: ");

        try {
            int idGenerado = modelo.insertarMedico(nombre, email, especialidad, matricula, telefono, horario);
            if (idGenerado > 0) {
                vista.mostrarMensaje("Medico registrado correctamente (ID: " + idGenerado + ").");
            } else {
                vista.mostrarMensaje("No se pudo registrar el medico.");
            }
        } catch (SQLException e) {
            vista.mostrarMensaje("Error al registrar medico: " + e.getMessage());
        }
    }
}