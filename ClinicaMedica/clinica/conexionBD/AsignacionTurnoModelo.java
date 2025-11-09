package clinica.conexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AsignacionTurnoModelo {

    private Connection conexion;

    public AsignacionTurnoModelo() {
        try {
            conexion = ConexionMySQL.getConexion();
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    public List<String> obtenerMedicosDisponibles() {
        List<String> medicos = new ArrayList<>();
        String sql = "SELECT id_medico, nombre FROM medicos";
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                medicos.add(rs.getInt("id_medico") + " - " + rs.getString("nombre"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener medicos: " + e.getMessage());
        }
        return medicos;
    }

    public boolean registrarMedico(String nombre, String dni, String telefono, String email, String especialidad) {
        String sql = "INSERT INTO medicos (nombre, dni, telefono, email, especialidad) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, dni);
            ps.setString(3, telefono);
            ps.setString(4, email);
            ps.setString(5, especialidad);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al registrar médico: " + e.getMessage());
            return false;
        }
    }

    public List<String> obtenerPacientes() {
        List<String> pacientes = new ArrayList<>();
        String sql = "SELECT id_paciente, nombre FROM pacientes";
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                pacientes.add(rs.getInt("id_paciente") + " - " + rs.getString("nombre"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener pacientes: " + e.getMessage());
        }
        return pacientes;
    }

    public boolean registrarPaciente(String nombre, String dni, String telefono, String email) {
        String sql = "INSERT INTO pacientes (nombre, dni, telefono, correo) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, dni);
            ps.setString(3, telefono);
            ps.setString(4, email);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al registrar paciente: " + e.getMessage());
            return false;
        }
    }

    public int insertarTurnoConRetorno(int idPaciente, int idMedico, String fecha, String hora, int idEstadoTurno) {
        String sql = """
            INSERT INTO turnos (id_paciente, id_medico, fecha_turno, hora_turno, id_estado_turno)
            VALUES (?, ?, ?, ?, ?)
        """;
        try (PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, idPaciente);
            ps.setInt(2, idMedico);
            ps.setString(3, fecha);
            ps.setString(4, hora);
            ps.setInt(5, idEstadoTurno); // ahora sí existe
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error al registrar el turno: " + e.getMessage());
        }
        return -1;
    }


    // Listar turnos (mantener como estaba)
    public List<String> listarTurnos() {
        List<String> turnos = new ArrayList<>();
        String sql = """
            SELECT t.id_turno, p.nombre AS paciente, m.nombre AS medico, t.fecha_turno, t.hora_turno, e.descripcion AS estado
            FROM turnos t
            JOIN pacientes p ON t.id_paciente = p.id_paciente
            JOIN medicos m ON t.id_medico = m.id_medico
            JOIN estado_turno e ON t.id_estado_turno = e.id_estado_turno
        """;
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                turnos.add(String.format(
                    "%d | Paciente: %s | Médico: %s | Fecha: %s | Hora: %s | Estado: %s",
                    rs.getInt("id_turno"),
                    rs.getString("paciente"),
                    rs.getString("medico"),
                    rs.getString("fecha_turno"),
                    rs.getString("hora_turno"),
                    rs.getString("estado")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar turnos: " + e.getMessage());
        }
        return turnos;
    }

    public boolean actualizarEstadoTurno(int idTurno, int nuevoEstado) {
        String sql = "UPDATE turnos SET id_estado_turno = ? WHERE id_turno = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, nuevoEstado);
            ps.setInt(2, idTurno);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al actualizar estado del turno: " + e.getMessage());
            return false;
        }
    }

    public int insertarPaciente(String nombre, String correo, String telefono, String dni) throws SQLException {
        String sql = "INSERT INTO pacientes (nombre, dni, telefono, correo, fecha_registro) VALUES (?, ?, ?, ?, NOW())";
        try (PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, nombre);
            ps.setString(2, dni);
            ps.setString(3, telefono);
            ps.setString(4, correo);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);
        }
        return -1;
    }

    public int insertarMedico(String nombre, String email, String especialidad, String matricula, String telefono, String horario) throws SQLException {
        String sql = """
            INSERT INTO medicos (nombre, email, especialidad, matricula, telefono, horario_atencion)
            VALUES (?, ?, ?, ?, ?, ?)
        """;
        try (PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, nombre);
            ps.setString(2, email);
            ps.setString(3, especialidad);
            ps.setString(4, matricula);
            ps.setString(5, telefono);
            ps.setString(6, horario);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);
        }
        return -1;
    }
}