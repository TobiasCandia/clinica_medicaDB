package clinica.gestionClinicaMedica;

import clinica.conexionBD.AsignacionTurnoModelo;
import clinica.excepciones.TurnoException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GestionClinica {

    private static final Scanner scanner = new Scanner(System.in);
    private static final AsignacionTurnoModelo modelo = new AsignacionTurnoModelo();

    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE GESTION DE CLINICA CENTRAL ===");

        int opcion = -1;
        do {
            mostrarMenu();

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

        System.out.println("=== Fin del programa ===");
    }

    private static void mostrarMenu() {
        System.out.println("""
                \n=== MENU PRINCIPAL ===
                1. Listar medicos disponibles
                2. Listar pacientes registrados
                3. Asignar turno
                4. Listar turnos
                5. Registrar nuevo paciente
                6. Registrar nuevo medico
                0. Salir
                =====================
                """);
    }

    private static void listarMedicos() {
        var medicos = modelo.obtenerMedicosDisponibles();
        System.out.println("=== MEDICOS DISPONIBLES ===");
        for (String m : medicos) {
            System.out.println(m);
        }
    }

    private static void listarPacientes() {
        var pacientes = modelo.obtenerPacientes();
        System.out.println("=== PACIENTES REGISTRADOS ===");
        for (String p : pacientes) {
            System.out.println(p);
        }
    }

    private static void asignarTurno() {
        try {
            listarPacientes();
            System.out.print("Ingrese ID del paciente: ");
            int idPaciente = scanner.nextInt();

            listarMedicos();
            System.out.print("Ingrese ID del medico: ");
            int idMedico = scanner.nextInt();

            scanner.nextLine();
            System.out.print("Ingrese fecha (YYYY-MM-DD): ");
            String fechaStr = scanner.nextLine();
            System.out.print("Ingrese hora (HH:mm): ");
            String horaStr = scanner.nextLine();

            int idEstadoTurno = 6;

            int idGenerado = modelo.insertarTurnoConRetorno(idPaciente, idMedico, fechaStr, horaStr, idEstadoTurno);
            if (idGenerado > 0) {
                System.out.println("Turno registrado con ID: " + idGenerado);
            } else {
                System.out.println("No se pudo registrar el turno.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: debe ingresar un número válido para ID.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void listarTurnos() {
        var turnos = modelo.listarTurnos();
        System.out.println("=== TURNOS REGISTRADOS ===");
        for (String t : turnos) {
            System.out.println(t);
        }
    }

    private static void registrarPaciente() {
        try {
            System.out.println("=== REGISTRO DE NUEVO PACIENTE ===");
            System.out.print("Nombre completo: ");
            String nombre = scanner.nextLine();
            System.out.print("DNI: ");
            String dni = scanner.nextLine();
            System.out.print("Telefono: ");
            String telefono = scanner.nextLine();
            System.out.print("Correo electronico: ");
            String correo = scanner.nextLine();

            int idGenerado = modelo.insertarPaciente(nombre, correo, telefono, dni);
            if (idGenerado > 0) {
                System.out.println("Paciente registrado con ID: " + idGenerado);
            } else {
                System.out.println("No se pudo registrar el paciente.");
            }
        } catch (SQLException e) {
            System.out.println("Error al registrar paciente: " + e.getMessage());
        }
    }

    private static void registrarMedico() {
        try {
            System.out.println("=== REGISTRO DE NUEVO MEDICO ===");
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Especialidad: ");
            String especialidad = scanner.nextLine();
            System.out.print("Matricula: ");
            String matricula = scanner.nextLine();
            System.out.print("Telefono: ");
            String telefono = scanner.nextLine();
            System.out.print("Horario de atencion: ");
            String horario = scanner.nextLine();

            int idGenerado = modelo.insertarMedico(nombre, email, especialidad, matricula, telefono, horario);
            if (idGenerado > 0) {
                System.out.println("Medico registrado con ID: " + idGenerado);
            } else {
                System.out.println("No se pudo registrar el medico.");
            }
        } catch (SQLException e) {
            System.out.println("Error al registrar medico: " + e.getMessage());
        }
    }
}
