package models;
//Importar las clases JDBC
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Producto {
    //Propiedades
    private int id;
    private String descripcion;
    private String categoria;
    private double precio;

    //ENCAPSULADOS
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    //CONSTRUCTORES
    public Producto() {
    }
    
    public Producto(int id, String descripcion, String categoria, double precio) {
        this.id = id;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.precio = precio;
    }
    
    //Métodos: CRUD
    
    //BUSCAR
    public Producto getbuscarById(int idBuscar){
        //Instanciar el modelo Producto 
        Producto producto = new Producto();
        try{
            //Establecer la conexión
            Connection cnx=Conexion.getConexion();
            //Preparar la instrucción SQL
            PreparedStatement ps = cnx.prepareStatement("select * from producto where id=?");
            //Pasar los valores de los parámetros SQL
            ps.setInt(1, idBuscar);
            //Ejecutar la instrucción SQL
            ResultSet rs=ps.executeQuery(); //SELECT
            //Desplazar el puntero de registros
            if(rs.next()){
                //Leer los valores
                producto.setId(rs.getInt("id"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setCategoria(rs.getString("categoria"));
                producto.setPrecio(rs.getDouble("precio"));
            }else{
                //Establecer valores predeteminados
                producto.setId(0);
                producto.setDescripcion("");
                producto.setCategoria("");
                producto.setPrecio(0.0);
            }
            
        }catch(Exception e){
            //Establecer valores predeteminados
                producto.setId(-1);
                producto.setDescripcion("-Error de conexión-");
                producto.setCategoria("");
                producto.setPrecio(0.0);
                System.out.println(e.getMessage());
        }
        //Retornar el objeto "producto"
        return producto;
    }
    
    
    //INSERTAR
    public int setInsertar(Producto producto){
        int nuevoId;
        try{
            //Establecer la conexión
            //(1) INSERTAR
            Connection cnx=Conexion.getConexion();
            //Preparar la instrucción SQL
            PreparedStatement ps = cnx.prepareStatement("insert into producto (descripcion, categoria, values) values (?,?,?) ");
            //Pasar los valores de los parámetros SQL
            ps.setString(1, producto.getDescripcion());
            ps.setString(2, producto.getCategoria());
            ps.setDouble(3, producto.getPrecio());
            //Ejecutar la instrucción SQL
            ps.executeUpdate(); //INSERTO-UPDATE-DELETE
            
            //********************************************
            //(2) LEER EL NUEVO ID GENERADO
            //Preparar la instrucción SQL
            ps = cnx.prepareStatement("select max(id) as nuevoId from producto");
            //Ejecutar la instrucción SQL
            ResultSet rs= ps.executeQuery();
 
            //Desplazar el puntero de registros
            if(rs.next()){
                //Leer los valores
                nuevoId=rs.getInt("nuevoId");
            }else{
                //Establecer valores predeteminados
                nuevoId=0;
            }
        }catch(Exception e){
            //Establecer valores predeteminados
                nuevoId=-1;
        }
        //Retornar el objeto "producto"
        return nuevoId;
    }
    
    
    //ACTUALIZAR
    public void setActualizar(){
        try{
            //Establecer la conexión
            //(1) INSERTAR
            Connection cnx=Conexion.getConexion();
            //Preparar la instrucción SQL
            PreparedStatement ps = cnx.prepareStatement("update producto set descripion=?, categoria=?, precio=? where id=?");
            //Pasar los valores de los parámetros SQL
            ps.setString(1, producto.getDescripcion());
            ps.setString(2, producto.getCategoria());
            ps.setDouble(3, producto.getPrecio());
            ps.setInt(4,producto.getId);
            //Ejecutar la instrucción SQL
            ps.executeUpdate(); //INSERTO-UPDATE-DELETE
            
        }catch(Exception e){
            //Establecer valores predeteminados
        }
    }
    
    
    //ELIMINAR
    public void setEliminar(int idEliminar){
        try{
            //Establecer la conexión
            //(1) INSERTAR
            Connection cnx=Conexion.getConexion();
            //Preparar la instrucción SQL
            PreparedStatement ps = cnx.prepareStatement("delete from producto where id=?");
            //Pasar los valores de los parámetros SQL
            ps.setInt(1,idEliminar);
            //Ejecutar la instrucción SQL
            ps.executeUpdate(); //INSERTO-UPDATE-DELETE
            
        }catch(Exception e){
            //Establecer valores predeteminados
        }
    }
}
