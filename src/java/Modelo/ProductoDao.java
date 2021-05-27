/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author SEPULS PC'S
 */

import java.sql.*; 
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleTypes; 

public class ProductoDao {
    Conexion conn; 

    public ProductoDao() {
        conn = new Conexion(); 
    }
    
    public List<Producto> fun_todoProducto(){
    
        Connection acceso = conn.getCnn(); 
        Producto prod = null;
        List<Producto> lista = new ArrayList(); 
        
        try { 
            CallableStatement cs = acceso.prepareCall("{ call PRODUCTO_PKG.proc_mostrarProducto(?) }");
            cs.registerOutParameter(1, OracleTypes.CURSOR); 
            cs.execute(); 
            
            ResultSet rs = (ResultSet) cs.getObject(1); 
            
            while (rs.next()){
                prod = new Producto(); 
                prod.setCodigo_producto(rs.getInt("ID_PRODUCTO"));
                prod.setNombre_producto(rs.getString("NOMBRE"));
                prod.setPrecio(rs.getInt("PRECIO"));
                prod.setStock(rs.getInt("STOCK"));
                prod.setAutor(rs.getString("AUTOR"));
                prod.setVigencia(rs.getString("VIGENCIA"));
                
                lista.add(prod); 
            }
        } catch (SQLException ex) {
            //Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
            
            System.out.println("Error" + ex.getMessage());
        }
        
        return lista;

    }
    
    
    public Producto fun_producto(int codigo_producto){
        Connection acceso = conn.getCnn();
        Producto prod = new Producto(); 

        try {
            CallableStatement cs = acceso.prepareCall("{ call PRODUCTO_PKG.proc_prodDetalle(?, ?) }");
            cs.setInt(1, codigo_producto);
            cs.registerOutParameter(2, OracleTypes.CURSOR); 
            cs.execute(); 
            
            ResultSet rs = (ResultSet) cs.getObject(2);
            
            while(rs.next()){
                prod.setCodigo_producto(rs.getInt("ID_PRODUCTO"));
                prod.setNombre_producto(rs.getString("NOMBRE"));
                prod.setPrecio(rs.getInt("PRECIO"));
                prod.setStock(rs.getInt("STOCK"));
                prod.setAutor(rs.getString("AUTOR"));
                prod.setVigencia(rs.getString("VIGENCIA"));
            }


        } catch (Exception ex) {
            System.out.println("Error" + ex.getMessage());

        }
        
        return prod; 
    
    }
    
    
    public boolean fun_actualizarStock(int codigo_producto, int stock){
        Connection acceso = conn.getCnn();
        boolean resultado = false; 
        
        
        try {
            CallableStatement cs;
            cs = acceso.prepareCall("{ call PRODUCTO_PKG.upd_descuentaStock(?, ?) }");
            cs.setInt(1, codigo_producto);            
            cs.setInt(2, stock);
            
            if(!cs.execute())
            {
                resultado = true; 
            }


        } catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());

        }
        
        return resultado; 
        
    }
    
    
}
