package org.example.repository;

import org.example.model.entities.Inventario;
import java.util.List;
import java.util.Optional;

public interface IInventarioDAO {
    
    // CREATE - Agregar nuevo producto al inventario
    void agregarProducto(Inventario producto);
    
    // READ - Listar todos los productos
    List<Inventario> listarTodos();
    
    // READ - Buscar producto por ID
    Optional<Inventario> buscarPorId(Integer id);
    
    // READ - Buscar productos por nombre
    List<Inventario> buscarPorNombre(String nombre);
    
    // READ - Buscar productos por tipo
    List<Inventario> buscarPorTipo(Integer productoTipoId);
    
    // READ - Buscar productos con stock bajo
    List<Inventario> buscarStockBajo();
    
    // READ - Buscar productos próximos a vencer
    List<Inventario> buscarProximosAVencer(int dias);
    
    // READ - Buscar productos vencidos
    List<Inventario> buscarVencidos();
    
    // UPDATE - Actualizar producto
    void actualizarProducto(Inventario producto);
    
    // UPDATE - Actualizar stock
    void actualizarStock(Integer id, Integer nuevaCantidad);
    
    // UPDATE - Deducir stock
    void deducirStock(Integer id, Integer cantidad);
    
    // DELETE - Eliminar producto
    void eliminarProducto(Integer id);
    
    // VERIFICAR - Si existe producto con nombre
    boolean existeProducto(String nombreProducto);
    
    // VERIFICAR - Stock disponible
    boolean hayStockSuficiente(Integer id, Integer cantidadRequerida);
    
    // REPORTES - Productos más vendidos (se integrará con ventas)
    List<Inventario> obtenerProductosMasVendidos(int limite);
}