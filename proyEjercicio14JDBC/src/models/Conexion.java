package models;

//Importar las clases JDBC
import java.sql.DriverManager;
import java.sql.Connection;

public class Conexion {
    //Método estático para establecer la conexión a la BD
    public static Connection getConexion(){
        //Declarar una variable del tipo Connection
        Connection conexion;
        
        //Controlador de excepciones
        try{
            //Conexión JDBC 
            //Cargar el controlador 
            Class.forName("com.mysql.jdbc.Driver");
            
            //Declarar las variables parámetros de conexión
            String cadena="jdbc:mysql://127.0.0.1:3306/bdpiad303";
            String usuario="root";
            String clave="";
            
            //Instanciar y abrir la conexión
            conexion=DriverManager.getConnection(cadena, usuario, clave);
        }catch(Exception e){
            //Asignar a la conexión el valor null
            conexion=null;
            System.out.println("Error !");
            System.out.println(e.getMessage());
        }
        
        return conexion;
    }
}
