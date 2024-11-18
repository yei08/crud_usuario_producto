package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author JEIFER ALCALA
 */
public class CRUDusuario {

    private ConexionBaseDatos baseDatos;
    private Usuario alguien;

    public void agregarUsuario() throws Exception {
        // Armamos el SQL INSERT de forma dinámica
       String sqlInsert = "INSERT INTO Usuarios "
                + "(password, nombre, apellido, rol, email, telefono, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";


        Connection conexion = null;
        PreparedStatement sentenciaSQL = null;

        try {
            // Obtener la conexión y crear la sentencia
           sentenciaSQL = baseDatos.crearSentencia(sqlInsert);
                    
            sentenciaSQL = conexion.prepareStatement(sqlInsert);

            // Asignar valores a los parámetros
            sentenciaSQL.setString(1, alguien.getPassword());
            sentenciaSQL.setString(2, alguien.getNombre());
            sentenciaSQL.setString(3, alguien.getApellidos());
            sentenciaSQL.setString(4, alguien.getRol());
            sentenciaSQL.setString(5, alguien.getEmail());
            sentenciaSQL.setString(6, alguien.getTelefono());
            sentenciaSQL.setString(7, alguien.getEstado());

            // Ejecutar la actualización
            sentenciaSQL.executeUpdate();

        } catch (Exception error) {
            throw new Exception("Error al agregar usuario " + alguien.getId()
                    + "<br/>Explicación: " + error.getMessage());
        } finally {
            // Cerrar los recursos
            if (sentenciaSQL != null) sentenciaSQL.close();
            if (conexion != null) baseDatos.desconectar();
        }
    }
    
    public void modificarUsuario() throws Exception {
    String sqlUpdate = "UPDATE usuarios "
            + "SET password=?, nombre=?, apellido=?, rol=?, email=?, telefono=?, estado=? "
            + "WHERE id=?";

    try {
        // Preparar la conexión y la sentencia SQL
        PreparedStatement sentenciaSQL = baseDatos.crearSentencia(sqlUpdate);

        // Asignar los valores a los marcadores de posición
        sentenciaSQL.setString(1, alguien.getPassword());
        sentenciaSQL.setString(2, alguien.getNombre());
        sentenciaSQL.setString(3, alguien.getApellidos());
        sentenciaSQL.setString(4, alguien.getRol());
        sentenciaSQL.setString(5, alguien.getEmail());
        sentenciaSQL.setString(6, alguien.getTelefono());
        sentenciaSQL.setString(7, alguien.getEstado());
        sentenciaSQL.setInt(8, alguien.getId()); // Este es el ID para identificar el usuario

            // Ejecutar la actualización
            baseDatos.actualizar(sentenciaSQL);
        } catch (Exception error) {
            // Manejo de excepciones
            throw new Exception("Error al actualizar usuario con ID " + alguien.getId()
                    + ". Explicación: " + error.getMessage());
        } finally {
            // Cerrar la conexión
            baseDatos.desconectar();
        }
    }
    
    public void eliminarUsuario(){
    
    }
    
}
