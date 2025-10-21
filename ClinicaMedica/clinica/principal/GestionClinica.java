/**
 * Autor: Candia Tobias Julian.
 * Fecha: Octubre 2025.
 * Versión: 1.0.
 */

package clinica.principal;

import clinica.modelo.*;
import java.util.*;
import clinica.excepciones.*;
import clinica.excepciones.TurnoException;
import java.text.SimpleDateFormat;
import java.text.ParseException;

// Clase principal del sistema de gestión de turnos médicos de la Clínica San Martín.
public class GestionClinica {

    // Scanner para lectura de datos por consola.
    private static final Scanner scanner = new Scanner(System.in);

    // Instancia principal de la clínica utilizada para gestionar los registros.
    private static final Clinica clinica =
            new Clinica("Clínica San Martín", "Sistema de gestión de turnos médicos");

    
    // Método principal del programa que muestra el menú y gestiona la interacción del usuario con el sistema.
    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");
            System.out.println();

            switch (opcion) {
                case 1 -> registrarPaciente();
                case 2 -> registrarMedico();
                case 3 -> asignarTurno();
                case 4 -> listarTurnos();
                case 5 -> gestionarEstadoTurno();
                case 6 -> generarReporte();
                case 0 -> System.out.println(" Saliendo del sistema...");
                default -> System.out.println("Opción inválida. Intente nuevamente.");
            }

            System.out.println();
        } while (opcion != 0);
    }

    // Métodos del menú principal con la opciones disponibles.
    private static void mostrarMenu() {
        System.out.println("""
                === SISTEMA DE GESTION DE CLINICA SAN MARTIN ===
                1. Registrar Paciente
                2. Registrar Médico
                3. Asignar Turno
                4. Listar Turnos
                5. Gestionar Estado de Turno
                6. Generar Reporte General
                0. Salir
                ===============================================
                """);
    }

    // Permite registrar un nuevo paciente solicitando sus datos por consola.
    private static void registrarPaciente() {
        System.out.println(" Registro de Paciente");
        int id = leerEntero("ID del paciente: ");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("DNI: ");
        String dni = scanner.nextLine();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        Paciente paciente = new Paciente(id, nombre, dni, telefono, email);
        clinica.registrarPaciente(paciente);
        System.out.println(" Paciente registrado correctamente.");
    }

    //Permite registrar un nuevo médico ingresando sus datos básicos y especialidad.
    private static void registrarMedico() {
        System.out.println(" Registro de Médico");
        int id = leerEntero("ID del médico: ");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("DNI: ");
        String dni = scanner.nextLine();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Especialidad: ");
        String especialidad = scanner.nextLine();

        Medico medico = new Medico(id, nombre, dni, telefono, email, especialidad);
        clinica.registrarMedico(medico);
        System.out.println("Médico registrado correctamente.");
    }

    // Asigna un turno a un paciente con un médico determinado, verificando disponibilidad. Si hay conflicto de horarios, lanza una excepción.
    private static void asignarTurno() {
        System.out.println("Asignación de Turno");
    
        if (clinica.getPacientes().isEmpty() || clinica.getMedicos().isEmpty()) {
            System.out.println("Debe haber al menos un paciente y un médico registrados.");
            return;
        }
    
        listarPacientes();
        int idPaciente = leerEntero("Seleccione ID del paciente: ");
        Paciente paciente = clinica.buscarPacientePorId(idPaciente);
        if (paciente == null) {
            System.out.println("Paciente no encontrado.");
            return;
        }
    
        listarMedicos();
        int idMedico = leerEntero("Seleccione ID del médico: ");
        Medico medico = clinica.buscarMedicoPorId(idMedico);
        if (medico == null) {
            System.out.println("Médico no encontrado.");
            return;
        }
    
        // NUEVO: el usuario ingresa la fecha del turno
        System.out.print("Ingrese la fecha del turno (formato dd/MM/yyyy): ");
        String fechaStr = scanner.nextLine();
        Date fecha = null;
    
        try {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            formato.setLenient(false); // evita fechas inválidas como 32/13/2025
            fecha = formato.parse(fechaStr);
        } catch (ParseException e) {
            System.out.println("Error: formato de fecha inválido. Debe ser dd/MM/yyyy.");
            return;
        }
    
        // Hora del turno
        System.out.print("Hora del turno (formato HH:mm): ");
        String hora = scanner.nextLine();
    
        try {
            Turno turno = clinica.asignarTurno(paciente, medico, fecha, hora);
            System.out.println("Turno asignado correctamente:\n" + turno);
        } catch (TurnoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Lista todos los turnos registrados en el sistema.
    private static void listarTurnos() {
        System.out.println("Lista de Turnos Registrados:");
        if (clinica.getTurnos().isEmpty()) {
            System.out.println("No hay turnos registrados.");
        } else {
            for (Turno t : clinica.getTurnos()) {
                System.out.println(t);
            }
        }
    }

    // Permite gestionar el estado de un turno específico:
    private static void gestionarEstadoTurno() {
        if (clinica.getTurnos().isEmpty()) {
            System.out.println("No hay turnos registrados para gestionar.");
            return;
        }

        listarTurnos();
        int idTurno = leerEntero("Ingrese el ID del turno a gestionar: ");
        Turno turnoSeleccionado = clinica.buscarTurnoPorId(idTurno);

        if (turnoSeleccionado == null) {
            System.out.println("No se encontró el turno con ese ID.");
            return;
        }

        System.out.println("""
                --- GESTION DE ESTADO ---
                1. Confirmar turno
                2. Cancelar turno
                3. Reprogramar turno
                0. Volver
                """);

        int opcion = leerEntero("Seleccione una opción: ");
        switch (opcion) {
            case 1 -> {
                turnoSeleccionado.confirmar();
                System.out.println("Turno confirmado exitosamente.");
            }
            case 2 -> {
                turnoSeleccionado.cancelar();
                System.out.println("Turno cancelado.");
            }
            case 3 -> {
                System.out.print("Ingrese nueva hora (HH:mm): ");
                String nuevaHora = scanner.nextLine();
                Date nuevaFecha = new Date();
                turnoSeleccionado.reprogramar(nuevaFecha, nuevaHora);
                System.out.println("Turno reprogramado correctamente.");
            }
            case 0 -> System.out.println("Volviendo al menú principal...");
            default -> System.out.println("Opción inválida.");
        }

        System.out.println("\nEstado actual del turno:");
        System.out.println(turnoSeleccionado);
    }

    // Genera un reporte general de turnos mediante la clase {@link Clinica}.
    private static void generarReporte() {
        System.out.println("Generando reporte general de turnos...");
        clinica.generarReporte();
    }

    // Muestra la lista de pacientes registrados.
    private static void listarPacientes() {
        System.out.println("Pacientes registrados:");
        for (Paciente p : clinica.getPacientes()) {
            System.out.println(p);
        }
    }

    // Muestra la lista de médicos registrados.
    private static void listarMedicos() {
        System.out.println("Médicos registrados:");
        for (Medico m : clinica.getMedicos()) {
            System.out.println(m);
        }
    }

    // Lee un número entero desde la consola, validando que la entrada sea correcta.
    private static int leerEntero(String mensaje) {
        int valor = -1;
        boolean valido = false;
    
        while (!valido) {
            try {
                System.out.print(mensaje);
                valor = scanner.nextInt();
                scanner.nextLine(); // limpia el buffer
                valido = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: debe ingresar un número válido.");
                scanner.nextLine(); // limpia entrada incorrecta
            }
        }
        return valor;
    }
}
