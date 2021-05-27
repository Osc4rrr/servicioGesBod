/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import Modelo.Producto;
import Modelo.ProductoDao;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;

/**
 *
 * @author Oscar Diaz
 */
@WebService(serviceName = "ServicioGesbod")
public class WSgesbod {
    
    
    //Retorna todos los productos
    @WebMethod(operationName ="consultarProductos")
    @WebResult(name="Producto")
    public List<Producto> get_todoProducto(){
        
        ProductoDao prodDao = new ProductoDao(); 
        List<Producto> lista = prodDao.fun_todoProducto(); 
        return lista; 
    }
   
    //Retorna un producto
    @WebMethod(operationName="getProducto")
    @WebResult(name="Producto")
    public Producto getProducto(@WebParam(name="codigoProducto") int codigo_producto){
    
        ProductoDao prodDao = new ProductoDao(); 
        Producto prod = prodDao.fun_producto(codigo_producto); 
        
        return prod; 
       
    }
    
    //actualiza stock
    @WebMethod(operationName="actualizarStock")
    @WebResult(name="resultado")   
    public boolean updateStock( @WebParam(name="codigo_produicto") int codigo_producto, @WebParam(name="cantidadProducto") int stock_producto )
    {
        ProductoDao prodDao = new ProductoDao();
        boolean resultado = prodDao.fun_actualizarStock(codigo_producto, stock_producto); 
        return resultado; 
        
    }
    
    

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "saludo")
    @WebResult(name="saludar")
    public String hello(
            @WebParam(name = "nombre") String txt, 
            @WebParam(name = "apellido_paterno") String apellido
    ) {
        return "Hola " + txt + " " + apellido;
    }
    
    @WebMethod(operationName = "sumar")
    @WebResult(name="resultado")
    public int Suma(
            @WebParam(name = "digito1") int numero1, 
            @WebParam(name = "digito2") int numero2
    ) {
        int sum = numero1+numero2; 
        return sum;
    }
}
