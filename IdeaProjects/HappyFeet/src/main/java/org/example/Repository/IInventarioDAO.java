package org.example.Repository;

import org.example.model.entities.Inventario;
import java.util.List;
import java.util.Optional;

/**

* DAO (Data Access Object) para la entidad Inventario.
* Define operaciones CRUD, búsquedas, validaciones y reportes.
  */
  public interface IInventarioDAO {

  // CREATE - Agregar nuevo producto al inventario
  Inventario agregarProducto(Inventario producto);

  // READ - Listar todos los productos
  List<Inventario> listarTodos();

  // READ - Buscar producto por ID
  Optional<Inventario> buscarPorId(Integer id);

  // READ - Buscar productos por nombre (LIKE %nombre%)
  List<Inventario> buscarPorNombre(String nombre);

  // READ - Buscar productos por tipo
  List<Inventario> buscarPorTipo(Integer productoTipoId);

  // READ - Buscar productos con stock bajo (stock < mínimo)
  List<Inventario> buscarStockBajo();

  // READ - Buscar productos próximos a vencer (en X días)
  List<Inventario> buscarProximosAVencer(int dias);

  // READ - Buscar productos ya vencidos
  List<Inventario> buscarVencidos();

  // UPDATE - Actualizar todos los datos del producto
  void actualizarProducto(Inventario producto);

  // UPDATE - Actualizar cantidad en stock
  void actualizarStock(Integer id, Integer nuevaCantidad);

  // UPDATE - Deducir cantidad de stock (por ventas o consumo)
  void deducirStock(Integer id, Integer cantidad);

  // DELETE - Eliminar producto por ID
  void eliminarProducto(Integer id);

  // VERIFICAR - Si existe un producto con ese nombre
  boolean existeProducto(String nombreProducto);

  // VERIFICAR - Si hay stock suficiente para una operación
  boolean hayStockSuficiente(Integer id, Integer cantidadRequerida);

  // REPORTES - Obtener los productos más vendidos (requiere integración con ventas)
  List<Inventario> obtenerProductosMasVendidos(int limite);
  }
