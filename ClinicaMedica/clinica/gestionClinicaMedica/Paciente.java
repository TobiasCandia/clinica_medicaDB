package clinica.gestionClinicaMedica;

import java.time.LocalDateTime;

/**
 * Clase Paciente
 * Representa a un paciente registrado en el sistema de gestión clínica.
 * Contiene los datos personales y la fecha de registro en el sistema.
 */
public class Paciente {

    private int idPaciente;
    private String nombre;
    private String dni;
    private String telefono;
    private String correo;
    private LocalDateTime fechaRegistro;

    public Paciente() { }

    public Paciente(int idPaciente, String nombre, String dni, String telefono, String correo, LocalDateTime fechaRegistro) {
        this.idPaciente = idPaciente;
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.correo = correo;
        this.fechaRegistro = fechaRegistro;
    }

    // Getters y Setters
    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public String toString() {
        return idPaciente + " - " + nombre + " (DNI: " + dni + ")";
    }
}
