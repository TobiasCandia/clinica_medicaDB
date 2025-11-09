package clinica.conexionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySQL {

    private static Connection conexion = null;

    private ConexionMySQL() { }

    public static Connection getConexion() throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            String url = "jdbc:mysql://localhost:3306/clinica_medica";
            String user = "root";
            String password = "41842028";

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println("Error: No se encontro el driver JDBC de MySQL.");
                e.printStackTrace();
            }

            conexion = DriverManager.getConnection(url, user, password);
            System.out.println("Conexion exitosa con la base de datos MySQL.");
        }
        return conexion;
    }

    public static void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("Conexion cerrada correctamente.");
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexion: " + e.getMessage());
        }
    }
}