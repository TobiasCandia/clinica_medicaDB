package clinica.modelo;

import java.util.ArrayList;
import java.util.List;

public class Medico {
    private int idMedico;
    private String nombre;
    private String dni;
    private String telefono;    // Atributos
    private String email;
    private String especialidad;
    private List<Turno> historial;

    public Medico(int idMedico, String nombre, String dni, String telefono, String email, String especialidad) {
        this.idMedico = idMedico;
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;       // Constructor
        this.email = email;
        this.especialidad = especialidad;
        this.historial = new ArrayList<>();
    }

    //Medico m1 = new Medico(1, "Juan López", "32111222", "3794003333", "jlopez@clinica.com", "Cardiología"); // Ejemplo temporal de uso.

    public int getIdMedico() { return idMedico; }
    public String getNombre() { return nombre; } // Devuelve el nombre completo del médico.
    public String getEspecialidad() { return especialidad; } // Retorna la especialidad médica del profesional.
    public String getDni() { return dni; }
    public String getTelefono() { return telefono; }
    public String getEmail() { return email; }

    // Devuelve la lista de turnos asignados al médico.
    // Este método permite consultar su agenda o historial de atención.
    public List<Turno> consultarHistorial() {
        return historial;
    }

    // Devuelve una representación textual del médico, incluyendo su título y especialidad.
    // Esto se utiliza principalmente para listados y reportes.
    @Override
    public String toString() {
        return "Dr. " + nombre + " (" + especialidad + ")";
    }
}
