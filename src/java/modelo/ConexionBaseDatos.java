/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author JEIFER ALCALA
 */
public class ConexionBaseDatos {
        protected String driver ="com.mysql.jdbc.Driver";
        protected String NombreIPServidorBD ="localhost";
        protected String url = "jdbc:mysql://";
        protected int puertoServidorBD = 3306;
        protected String usuarioBD = "root";
        protected String passwordUsuarioBD ="";
        protected String nombreBD = "crud_usuario_producto";
        private Connection conexion;
        private PreparedStatement sentencia;
        private ResultSet filasConsulta;
        
        public void conectar() throws Exception{
            try{
                    Class.forName(driver);
            }
            catch (ClassNotFoundException ex){
                throw new Exception ("error de driver"+ex.getMessage());
            }
            try{
                    conexion = DriverManager.getConnection(url, usuarioBD, passwordUsuarioBD);
            }
            catch(SQLException ex){
                    throw new Exception("Error de Conexion, Codigo: "
                                        + ex.getErrorCode()+ "explicacion: "+ex.getMessage());
            }
        }
        
    public ConexionBaseDatos() throws Exception{
        url = url + NombreIPServidorBD+":"+puertoServidorBD+"/"+nombreBD;
        this.conectar();
    }
    public ConexionBaseDatos(String driver,String servidor, String url, 
                String usuarioBD, String passwordUsuarioBD, String nombreBD) 
                throws Exception
    {
        this.driver = driver;
        this.NombreIPServidorBD =servidor;
        this.url = url;
        this.usuarioBD = usuarioBD;
        this.passwordUsuarioBD= passwordUsuarioBD;
        this.nombreBD = nombreBD;
        this.conectar();
    }
    
    public int actualizar(PreparedStatement sentencia)throws Exception{
        try{
                int res = sentencia.executeUpdate();
                return res;
        }
        catch (SQLException ex){
                throw new SQLException("error al ejecutar sentencia BD conexion, codigo: "
                            + ex.getErrorCode()+"explicacion: "+ex.getMessage());
        }
    }
    
    public ResultSet consultar(PreparedStatement sentencia)throws Exception{
                try{
                        ResultSet filasBD = sentencia.executeQuery();
                        return filasBD;
                }
                catch (SQLException ex){
                        throw new SQLException("error al ejecutar la sentencia BD conexion"
                                                                            + ex.getMessage());
                }
    }   
    
    public void desconectar(){
            try{
                    conexion.close();
            }catch (SQLException ex){
                    conexion = null;
            }
    }
    
    public PreparedStatement crearSentencia (String sql)throws Exception{
                try{
                        PreparedStatement sentencia = conexion.prepareStatement(sql);
                                return sentencia;
                }
                catch (SQLException ex){
                            throw new SQLException("ERROR DE SENTENCIA BD \n codigo: "
                                    + ex.getErrorCode()+"explicacion: "+ ex.getMessage());
                }
    }

    public String getDriver() {
        return driver;
    }
    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getNombreIPServidorBD() {
        return NombreIPServidorBD;
    }
    public void setNombreIPServidorBD(String NombreIPServidorBD) {
        this.NombreIPServidorBD = NombreIPServidorBD;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public int getPuertoServidorBD() {
        return puertoServidorBD;
    }
    public void setPuertoServidorBD(int puertoServidorBD) {
        this.puertoServidorBD = puertoServidorBD;
    }

    public String getUsuarioBD() {
        return usuarioBD;
    }
    public void setUsuarioBD(String usuarioBD) {
        this.usuarioBD = usuarioBD;
    }

    public String getPasswordUsuarioBD() {
        return passwordUsuarioBD;
    }
    public void setPasswordUsuarioBD(String passwordUsuarioBD) {
        this.passwordUsuarioBD = passwordUsuarioBD;
    }

    public String getNombreBD() {
        return nombreBD;
    }
    public void setNombreBD(String nombreBD) {
        this.nombreBD = nombreBD;
    }

    public Connection getConexion() {
        return conexion;
    }
    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public PreparedStatement getSentencia() {
        return sentencia;
    }
    public void setSentencia(PreparedStatement sentencia) {
        this.sentencia = sentencia;
    }

    public ResultSet getFilasConsulta() {
        return filasConsulta;
    }
    public void setFilasConsulta(ResultSet filasConsulta) {
        this.filasConsulta = filasConsulta;
    }
    
    
}

