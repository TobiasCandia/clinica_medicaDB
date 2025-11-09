package clinica.conexionBD;

import java.util.List;
import java.util.Scanner;

public class AsignacionTurnoVista {

    private final Scanner scanner = new Scanner(System.in);

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarLinea() {
        System.out.println("----------------------------------------------------");
    }

    public void mostrarMenu() {
        System.out.println("""
                
                === SISTEMA DE GESTION DE CLINICA MEDICA ===
                1. Registrar paciente
                2. Registrar medico
                3. Asignar turno
                4. Listar turnos
                5. Gestionar estado de turno
                6. Generar reporte general
                0. Salir
                """);
    }

    public String pedirTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    public int pedirEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida. Por favor, ingrese un numero entero.");
            }
        }
    }

    public String pedirFecha(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    public void mostrarMedicos(List<String> medicos) {
        System.out.println("\n=== MEDICOS DISPONIBLES ===");
        if (medicos.isEmpty()) {
            System.out.println("No hay medicos registrados.");
        } else {
            for (String m : medicos) {
                System.out.println(" • " + m);
            }
        }
        System.out.println();
    }

    public void mostrarPacientes(List<String> pacientes) {
        System.out.println("\n=== PACIENTES REGISTRADOS ===");
        if (pacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
        } else {
            for (String p : pacientes) {
                System.out.println(" • " + p);
            }
        }
        System.out.println();
    }

    public void mostrarTurnos(List<String> turnos) {
        System.out.println("\n=== TURNOS REGISTRADOS ===");
        if (turnos.isEmpty()) {
            System.out.println("No hay turnos disponibles.");
        } else {
            for (String t : turnos) {
                System.out.println(" • " + t);
            }
        }
        System.out.println();
    }
}