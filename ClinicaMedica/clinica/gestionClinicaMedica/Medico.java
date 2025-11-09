package clinica.gestionClinicaMedica;

/**
 * Clase Medico
 * Representa a un médico dentro del sistema de gestión clínica.
 * Contiene la información básica y profesional del médico.
 */
public class Medico {

    private int idMedico;
    private String nombre;
    private String email;
    private String especialidad;
    private String matricula;
    private String telefono;
    private String horarioAtencion;

    public Medico() { }

    public Medico(int idMedico, String nombre, String email, String especialidad,
                  String matricula, String telefono, String horarioAtencion) {
        this.idMedico = idMedico;
        this.nombre = nombre;
        this.email = email;
        this.especialidad = especialidad;
        this.matricula = matricula;
        this.telefono = telefono;
        this.horarioAtencion = horarioAtencion;
    }

    // Getters y setters
    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getHorarioAtencion() {
        return horarioAtencion;
    }

    public void setHorarioAtencion(String horarioAtencion) {
        this.horarioAtencion = horarioAtencion;
    }

    @Override
    public String toString() {
        return idMedico + " - " + nombre + " (" + especialidad + ") | Matrícula: " + matricula;
    }
}
