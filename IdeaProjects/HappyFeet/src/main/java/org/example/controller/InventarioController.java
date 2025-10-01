package org.example.controller;

import org.example.model.entities.Inventario;
import org.example.Service.InventarioService;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.logging.Level;

public class InventarioController {
    private static final Logger logger = Logger.getLogger(InventarioController.class.getName());
    private final InventarioService inventarioService;

    public InventarioController() {
        this.inventarioService = new InventarioService();
    }

    // CREATE - Agregar nuevo producto
    public boolean agregarProducto(String nombreProducto, Integer productoTipoId, String descripcion,
    String fabricante, String lote, Integer cantidadStock, 
    Integer stockMinimo, java.util.Date fechaVencimiento, Double precioVenta) {
try {
// Validar datos antes (puedes usar tu método validarDatosProducto)
if (!validarDatosProducto(nombreProducto, productoTipoId, cantidadStock, stockMinimo, precioVenta)) {
logger.log(Level.WARNING, "\u274c Datos inv\u00e1lidos para el producto: {0}", nombreProducto);
return false;
}

Inventario producto = new Inventario.Builder()
.setNombreProducto(nombreProducto)
.setProductoTipoId(productoTipoId)
.setDescripcion(descripcion)
.setFabricante(fabricante)
.setLote(lote)
.setCantidadStock(cantidadStock)
.setStockMinimo(stockMinimo)
.setFechaVencimiento(fechaVencimiento)
.setPrecioVenta(precioVenta)
.build();

inventarioService.agregarProducto(producto);
logger.log(Level.INFO, "\u2705 Producto agregado exitosamente: {0}", nombreProducto);
return true;

} catch (IllegalArgumentException e) {
logger.log(Level.WARNING, "\u274c Validaci\u00f3n fallida al agregar producto: {0}", e.getMessage());
return false;

} catch (Exception e) {
logger.log(Level.SEVERE, "❌ Error al agregar producto: " + e.getMessage(), e);
return false;
}
}
    // READ - Listar todos los productos
    public List<Inventario> listarTodosLosProductos() {
        try {
            List<Inventario> productos = inventarioService.listarTodosLosProductos();
            logger.log(Level.INFO, "\ud83d\udccb Se listaron {0} productos", productos.size());
            return productos;
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "❌ Error al listar productos: " + e.getMessage(), e);
            return List.of();
        }
    }

    // READ - Buscar producto por ID
    public Optional<Inventario> buscarProductoPorId(Integer id) {
        try {
            Optional<Inventario> producto = inventarioService.buscarProductoPorId(id);
            if (producto.isPresent()) {
                logger.log(Level.INFO, "\ud83d\udd0d Producto encontrado: {0}", producto.get().getNombreProducto());
            } else {
                logger.log(Level.INFO, "\ud83d\udd0d No se encontr\u00f3 producto con ID: {0}", id);
            }
            return producto;
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "❌ Error al buscar producto por ID: " + e.getMessage(), e);
            return Optional.empty();
        }
    }

    // READ - Buscar productos por nombre
    public List<Inventario> buscarProductosPorNombre(String nombre) {
        try {
            List<Inventario> productos = inventarioService.buscarProductosPorNombre(nombre);
            logger.log(Level.INFO, "\ud83d\udd0e Se encontraron {0} productos con nombre: {1}", new Object[]{productos.size(), nombre});
            return productos;
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "❌ Error al buscar productos por nombre: " + e.getMessage(), e);
            return List.of();
        }
    }

    // READ - Buscar productos con stock bajo
    public List<Inventario> buscarProductosConStockBajo() {
        try {
            List<Inventario> productos = inventarioService.buscarProductosConStockBajo();
            logger.log(Level.INFO, "\u26a0\ufe0f  Se encontraron {0} productos con stock bajo", productos.size());
            return productos;
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "❌ Error al buscar productos con stock bajo: " + e.getMessage(), e);
            return List.of();
        }
    }

    // READ - Buscar productos próximos a vencer
    public List<Inventario> buscarProductosProximosAVencer(int dias) {
        try {
            List<Inventario> productos = inventarioService.buscarProductosProximosAVencer(dias);
            logger.log(Level.INFO, "\ud83d\udcc5 Se encontraron {0} productos pr\u00f3ximos a vencer en {1} d\u00edas", new Object[]{productos.size(), dias});
            return productos;
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "❌ Error al buscar productos próximos a vencer: " + e.getMessage(), e);
            return List.of();
        }
    }

    // READ - Buscar productos vencidos
    public List<Inventario> buscarProductosVencidos() {
        try {
            List<Inventario> productos = inventarioService.buscarProductosVencidos();
            logger.log(Level.INFO, "\ud83d\udeab Se encontraron {0} productos vencidos", productos.size());
            return productos;
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "❌ Error al buscar productos vencidos: " + e.getMessage(), e);
            return List.of();
        }
    }

    // UPDATE - Actualizar stock
    public boolean actualizarStock(Integer id, Integer nuevaCantidad) {
        try {
            inventarioService.actualizarStock(id, nuevaCantidad);
            logger.log(Level.INFO, "\ud83d\udce6 Stock actualizado - Producto ID: {0} - Nuevo stock: {1}", new Object[]{id, nuevaCantidad});
            return true;
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "❌ Error al actualizar stock: " + e.getMessage(), e);
            return false;
        }
    }

    // UPDATE - Deducir stock
    public boolean deducirStock(Integer id, Integer cantidad) {
        try {
            inventarioService.deducirStock(id, cantidad);
            logger.log(Level.INFO, "\u2796 Stock deducido - Producto ID: {0} - Cantidad: {1}", new Object[]{id, cantidad});
            return true;
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "❌ Error al deducir stock: " + e.getMessage(), e);
            return false;
        }
    }

    // UPDATE - Reponer stock
    public boolean reponerStock(Integer id, Integer cantidad) {
        try {
            inventarioService.reponerStock(id, cantidad);
            logger.log(Level.INFO, "\u2795 Stock repuesto - Producto ID: {0} - Cantidad: {1}", new Object[]{id, cantidad});
            return true;
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "❌ Error al reponer stock: " + e.getMessage(), e);
            return false;
        }
    }

    // ALERTAS - Generar alertas
    public void generarAlertasInventario() {
        try {
            inventarioService.generarAlertas();
            logger.info("🔔 Alertas de inventario generadas");
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "❌ Error al generar alertas: " + e.getMessage(), e);
        }
    }

    // REPORTES - Resumen de inventario
    public void generarResumenInventario() {
        try {
            inventarioService.generarResumenInventario();
            logger.info("📊 Resumen de inventario generado");
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "❌ Error al generar resumen: " + e.getMessage(), e);
        }
    }

    // REPORTES - Valor total del inventario
    public double obtenerValorTotalInventario() {
        try {
            double valorTotal = inventarioService.obtenerValorTotalInventario();
            logger.log(Level.INFO, "\ud83d\udcb0 Valor total del inventario: ${0}", valorTotal);
            return valorTotal;
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "❌ Error al obtener valor total: " + e.getMessage(), e);
            return 0.0;
        }
    }

    // VALIDACIÓN - Verificar stock suficiente
    public boolean verificarStockSuficiente(Integer id, Integer cantidadRequerida) {
        try {
            boolean suficiente = inventarioService.verificarStockSuficiente(id, cantidadRequerida);
            logger.log(Level.INFO, "\ud83d\udce6 Verificaci\u00f3n de stock - Producto ID: {0} - Suficiente: {1}", new Object[]{id, suficiente});
            return suficiente;
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "❌ Error al verificar stock: " + e.getMessage(), e);
            return false;
        }
    }

    // Validación de datos antes de operaciones
    public boolean validarDatosProducto(String nombreProducto, Integer productoTipoId, 
                                       Integer cantidadStock, Integer stockMinimo, Double precioVenta) {
        try {
            if (nombreProducto == null || nombreProducto.trim().isEmpty()) {
                return false;
            }
            if (productoTipoId == null || productoTipoId <= 0) {
                return false;
            }
            if (cantidadStock == null || cantidadStock < 0) {
                return false;
            }
            if (stockMinimo == null || stockMinimo < 0) {
                return false;
            }
            return !(precioVenta == null || precioVenta < 0);
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "❌ Error en validación de datos de producto: " + e.getMessage(), e);
            return false;
        }
    }
}