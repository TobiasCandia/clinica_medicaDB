package clinica.modelo;

import java.util.ArrayList;
import java.util.List;

public class Paciente {
    private int idPaciente;
    private String nombre;
    private String dni;         // Atributos
    private String telefono;
    private String email;
    private List<Turno> historial;

    public Paciente(int idPaciente, String nombre, String dni, String telefono, String email) {
        this.idPaciente = idPaciente;
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;       // Constructor
        this.email = email;
        this.historial = new ArrayList<>();
    }

    //Paciente p1 = new Paciente(1, "Laura Pérez", "40999888", "3794001111", "laura@gmail.com"); // Ejemplo temporal de uso

    public int getIdPaciente() { return idPaciente; }
    public String getNombre() { return nombre; } // Devuelve el nombre completo del paciente.
    public String getDni() { return dni; }
    public String getTelefono() { return telefono; }
    public String getEmail() { return email; }

    // Devuelve la lista de turnos asociados al paciente.
    // Permite visualizar su historial médico o de consultas.
    public List<Turno> consultarHistorial() {
        return historial;
    }

    // Agrega un nuevo turno al historial del paciente.
    // Se utiliza internamente por la clase Clinica cuando se asigna un turno.
    public void agregarTurno(Turno turno) {
        historial.add(turno);
    }

    // Devuelve una representación en texto del paciente, útil para mostrarlo en listados o reportes.
    @Override
    public String toString() {
        return nombre + " (DNI: " + dni + ")";
    }
}
