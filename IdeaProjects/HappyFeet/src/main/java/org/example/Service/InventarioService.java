package org.example.Service;

import org.example.model.entities.Inventario;
import org.example.repository.InventarioDAO;
import java.util.List;
import java.util.Optional;

public class InventarioService {
    private final InventarioDAO inventarioDAO;

    public InventarioService() {
        this.inventarioDAO = new InventarioDAO();
    }

    // CREATE - Agregar nuevo producto con validaciones
    public void agregarProducto(Inventario producto) {
        validarProducto(producto);
        
        // Verificar si ya existe un producto con el mismo nombre
        if (inventarioDAO.existeProducto(producto.getNombreProducto())) {
            throw new IllegalArgumentException("Ya existe un producto con el nombre: " + producto.getNombreProducto());
        }
        
        inventarioDAO.agregarProducto(producto);
    }

    // READ - Listar todos los productos
    public List<Inventario> listarTodosLosProductos() {
        return inventarioDAO.listarTodos();
    }

    // READ - Buscar producto por ID
    public Optional<Inventario> buscarProductoPorId(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID de producto inválido");
        }
        return inventarioDAO.buscarPorId(id);
    }

    // READ - Buscar productos por nombre
    public List<Inventario> buscarProductosPorNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Nombre de producto no puede estar vacío");
        }
        return inventarioDAO.buscarPorNombre(nombre.trim());
    }

    // READ - Buscar productos por tipo
    public List<Inventario> buscarProductosPorTipo(Integer productoTipoId) {
        if (productoTipoId == null || productoTipoId <= 0) {
            throw new IllegalArgumentException("ID de tipo de producto inválido");
        }
        return inventarioDAO.buscarPorTipo(productoTipoId);
    }

    // READ - Buscar productos con stock bajo
    public List<Inventario> buscarProductosConStockBajo() {
        return inventarioDAO.buscarStockBajo();
    }

    // READ - Buscar productos próximos a vencer
    public List<Inventario> buscarProductosProximosAVencer(int dias) {
        if (dias <= 0) {
            throw new IllegalArgumentException("El número de días debe ser positivo");
        }
        return inventarioDAO.buscarProximosAVencer(dias);
    }

    // READ - Buscar productos vencidos
    public List<Inventario> buscarProductosVencidos() {
        return inventarioDAO.buscarVencidos();
    }

    // UPDATE - Actualizar producto
    public void actualizarProducto(Inventario producto) {
        validarProducto(producto);
        
        if (producto.getId() == null || producto.getId() <= 0) {
            throw new IllegalArgumentException("ID de producto inválido para actualizar");
        }
        
        // Verificar que el producto existe
        Optional<Inventario> productoExistente = inventarioDAO.buscarPorId(producto.getId());
        if (productoExistente.isEmpty()) {
            throw new IllegalArgumentException("No se encontró el producto con ID: " + producto.getId());
        }
        
        inventarioDAO.actualizarProducto(producto);
    }

    // UPDATE - Actualizar stock
    public void actualizarStock(Integer id, Integer nuevaCantidad) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID de producto inválido");
        }
        if (nuevaCantidad == null || nuevaCantidad < 0) {
            throw new IllegalArgumentException("La cantidad de stock no puede ser negativa");
        }
        
        // Verificar que el producto existe
        Optional<Inventario> producto = inventarioDAO.buscarPorId(id);
        if (producto.isEmpty()) {
            throw new IllegalArgumentException("No se encontró el producto con ID: " + id);
        }
        
        inventarioDAO.actualizarStock(id, nuevaCantidad);
    }

    // UPDATE - Deducir stock (para ventas/uso)
    public void deducirStock(Integer id, Integer cantidad) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID de producto inválido");
        }
        if (cantidad == null || cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad a deducir debe ser positiva");
        }
        
        // Verificar stock suficiente
        if (!inventarioDAO.hayStockSuficiente(id, cantidad)) {
            throw new IllegalArgumentException("Stock insuficiente para el producto ID: " + id);
        }
        
        inventarioDAO.deducirStock(id, cantidad);
    }

    // DELETE - Eliminar producto
    public void eliminarProducto(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID de producto inválido");
        }
        
        // Verificar que el producto existe antes de eliminar
        Optional<Inventario> producto = inventarioDAO.buscarPorId(id);
        if (producto.isEmpty()) {
            throw new IllegalArgumentException("No se encontró el producto con ID: " + id);
        }
        
        inventarioDAO.eliminarProducto(id);
    }

    // VALIDACIÓN - Verificar stock suficiente
    public boolean verificarStockSuficiente(Integer id, Integer cantidadRequerida) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID de producto inválido");
        }
        if (cantidadRequerida == null || cantidadRequerida <= 0) {
            throw new IllegalArgumentException("La cantidad requerida debe ser positiva");
        }
        
        return inventarioDAO.hayStockSuficiente(id, cantidadRequerida);
    }

    // REPORTES - Productos más vendidos
    public List<Inventario> obtenerProductosMasVendidos(int limite) {
        if (limite <= 0) {
            throw new IllegalArgumentException("El límite debe ser positivo");
        }
        return inventarioDAO.obtenerProductosMasVendidos(limite);
    }

    // REPORTES - Resumen de inventario
    public void generarResumenInventario() {
        List<Inventario> todosProductos = inventarioDAO.listarTodos();
        List<Inventario> stockBajo = inventarioDAO.buscarStockBajo();
        List<Inventario> proximosVencer = inventarioDAO.buscarProximosAVencer(30); // 30 días
        List<Inventario> vencidos = inventarioDAO.buscarVencidos();
        
        double valorTotalInventario = todosProductos.stream()
                .mapToDouble(p -> p.getPrecioVenta() * p.getCantidadStock())
                .sum();
        
        System.out.println("\n=== 📊 RESUMEN DE INVENTARIO ===");
        System.out.println("Total de Productos: " + todosProductos.size());
        System.out.println("Productos con Stock Bajo: " + stockBajo.size());
        System.out.println("Productos Próximos a Vencer: " + proximosVencer.size());
        System.out.println("Productos Vencidos: " + vencidos.size());
        System.out.println("Valor Total del Inventario: $" + valorTotalInventario);
    }

    // ALERTAS - Generar alertas de inventario
    public void generarAlertas() {
        List<Inventario> stockBajo = inventarioDAO.buscarStockBajo();
        List<Inventario> proximosVencer = inventarioDAO.buscarProximosAVencer(15); // 15 días
        List<Inventario> vencidos = inventarioDAO.buscarVencidos();
        
        if (!stockBajo.isEmpty()) {
            System.out.println("\n⚠️  ALERTAS DE STOCK BAJO:");
            stockBajo.forEach(p -> 
                System.out.println("   📦 " + p.getNombreProducto() + " - Stock: " + 
                                 p.getCantidadStock() + " (Mínimo: " + p.getStockMinimo() + ")")
            );
        }
        
        if (!proximosVencer.isEmpty()) {
            System.out.println("\n⚠️  ALERTAS DE PRÓXIMO VENCIMIENTO (15 días):");
            proximosVencer.forEach(p -> 
                System.out.println("   📅 " + p.getNombreProducto() + " - Vence: " + p.getFechaVencimiento())
            );
        }
        
        if (!vencidos.isEmpty()) {
            System.out.println("\n❌ ALERTAS DE PRODUCTOS VENCIDOS:");
            vencidos.forEach(p -> 
                System.out.println("   🚫 " + p.getNombreProducto() + " - Vencido: " + p.getFechaVencimiento())
            );
        }
        
        if (stockBajo.isEmpty() && proximosVencer.isEmpty() && vencidos.isEmpty()) {
            System.out.println("\n✅ No hay alertas de inventario pendientes.");
        }
    }

    // Validación de datos del producto
    private void validarProducto(Inventario producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo");
        }
        
        if (producto.getNombreProducto() == null || producto.getNombreProducto().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto es requerido");
        }
        
        if (producto.getProductoTipoId() == null || producto.getProductoTipoId() <= 0) {
            throw new IllegalArgumentException("ID de tipo de producto inválido");
        }
        
        if (producto.getCantidadStock() == null || producto.getCantidadStock() < 0) {
            throw new IllegalArgumentException("La cantidad de stock no puede ser negativa");
        }
        
        if (producto.getStockMinimo() == null || producto.getStockMinimo() < 0) {
            throw new IllegalArgumentException("El stock mínimo no puede ser negativo");
        }
        
        if (producto.getPrecioVenta() == null || producto.getPrecioVenta() < 0) {
            throw new IllegalArgumentException("El precio de venta no puede ser negativo");
        }
        
        // Validar que si tiene fecha de vencimiento, no sea pasada
        if (producto.getFechaVencimiento() != null && 
            producto.getFechaVencimiento().before(new java.util.Date())) {
            throw new IllegalArgumentException("La fecha de vencimiento no puede ser pasada");
        }
    }

    // Método para reponer stock
    public void reponerStock(Integer id, Integer cantidad) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID de producto inválido");
        }
        if (cantidad == null || cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad a reponer debe ser positiva");
        }
        
        Optional<Inventario> productoOpt = inventarioDAO.buscarPorId(id);
        if (productoOpt.isEmpty()) {
            throw new IllegalArgumentException("No se encontró el producto con ID: " + id);
        }
        
        Inventario producto = productoOpt.get();
        int nuevoStock = producto.getCantidadStock() + cantidad;
        inventarioDAO.actualizarStock(id, nuevoStock);
    }

    // Método para obtener valor total del inventario
    public double obtenerValorTotalInventario() {
        List<Inventario> productos = inventarioDAO.listarTodos();
        return productos.stream()
                .mapToDouble(p -> p.getPrecioVenta() * p.getCantidadStock())
                .sum();
    }
}