package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
    
    public void eliminarUsuario() throws Exception{
        String sqlDelete = "DELETE FROM usuarios "
                + "WHERE id =?";
       
        try{
             //crear una sentencia jdbc mediante la sentencia sql anterior
            PreparedStatement sentenciaSQL = baseDatos.crearSentencia(sqlDelete);
            //pasarle los datos del usuarioa la sentencia sql
            sentenciaSQL.setInt(1,alguien.getId());
            //actualizar la base de datos usando la sentenciaSQL con datos de usuario
            baseDatos.actualizar(sentenciaSQL);
        
            
                
        } 
        catch (Exception error){
            throw new Exception("error al eliminar el usuario"+alguien.getId()
                +"<br/> explicacion: "+ error. getMessage());
            
                    
        } finally{
            baseDatos.desconectar();
        }
    }
 
    public static Usuario iniciarSesion(String id, String password)throws Exception{
        if(id == null || id.isEmpty() || password == null || password.isEmpty()){
            throw new Exception ("el id y la contraseña del usuario son necesarios");
        }
        Usuario alguien; ConexionBaseDatos baseDatos = null;
        //armar el sql select de forma dinamica
        String sqlSelect = "SELECT * FROM usuarios WHERE id =? and password=?";
        try{
            // crear una sentencia jdbc mediante la sentencia sql anterior
            baseDatos = new ConexionBaseDatos();
            PreparedStatement sentenciaSQL = baseDatos.crearSentencia(sqlSelect);
            //pasamos los datos del usuario a la sentencia sql
            sentenciaSQL.setString(1, id);
            sentenciaSQL.setString(2,password );
            //verificar el resultado de la consulta
            ResultSet resultado = baseDatos.consultar(sentenciaSQL);
            if (resultado.next() == true){
                alguien = new Usuario();
                alguien.setPassword(resultado.getString("password"));
                alguien.setNombre(resultado.getString("nombre"));
                alguien.setApellidos(resultado.getString("apellidos"));
                alguien.setRol(resultado.getString("rol"));
                alguien.setEmail(resultado.getString("email"));
                alguien.setTelefono(resultado.getString("telefono"));
                alguien.setEstado(resultado.getString("estado"));
                return alguien;
                
            }else{
                throw new Exception("error a consultar el usuario "+id+" <br/>explicacion: ");
            }
    
                    
        }catch (Exception error){
            throw new Exception(error.getMessage()+"error en el id o la contraseña estan errados");
            
        }finally{
            if (baseDatos != null){
                  baseDatos.desconectar();
            }
        }
    }

    public static Usuario consultarUsuario(String id) throws Exception{
        if (id == null || id.isEmpty()){
            throw new Exception("el id del usuario es necesario");
        }
        Usuario alguien; ConexionBaseDatos baseDatos = null;
        //armar el sql select de forma dinamica
        String sqlSelect = "SELECT * FROM usuarios WHERE id =?";
        
        try{
            //creamos una sentencia jdbc mediante la sentencia sql anterior 
            baseDatos = new ConexionBaseDatos();
            PreparedStatement sentenciaSQL = baseDatos.crearSentencia(sqlSelect);
            //pasamos los datos del usuario a la sentencia sql
            sentenciaSQL.setString(1, id);
            //verificamos el resultado de la consulta
            ResultSet resultado = baseDatos.consultar(sentenciaSQL);
            if (resultado.next() == true){
                alguien = new Usuario();
                alguien.setPassword(resultado.getString("password"));
                alguien.setNombre(resultado.getString("nombre"));
                alguien.setApellidos(resultado.getString("apellidos"));
                alguien.setRol(resultado.getString("rol"));
                alguien.setEmail(resultado.getString("email"));
                alguien.setTelefono(resultado.getString("telefono"));
                alguien.setEstado(resultado.getString("estado"));
                alguien.setFechaRegistro(resultado.getDate("fecha_registro"));
                
                return alguien;
            }else{
                throw new Exception("error al consultar usuario"+id+"\n explicacion:");
            }        
        }catch (Exception error){
            throw new Exception(error.getMessage()+" el usuario no existe en la base de datos");
        }finally{
            if (baseDatos != null){
                baseDatos.desconectar();
            }
        }
    }
}
