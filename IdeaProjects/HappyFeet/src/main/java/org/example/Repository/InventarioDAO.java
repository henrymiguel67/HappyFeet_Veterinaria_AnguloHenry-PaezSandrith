package org.example.Repository;

import org.example.model.entities.Inventario;
import org.example.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InventarioDAO implements IInventarioDAO {

    @Override
    public void agregarProducto(Inventario producto) {
        String sql = "INSERT INTO inventario (nombre_producto, producto_tipo_id, descripcion, " +
                    "fabricante, lote, cantidad_stock, stock_minimo, fecha_vencimiento, precio_venta) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, producto.getNombreProducto());
            stmt.setInt(2, producto.getProductoTipoId());
            stmt.setString(3, producto.getDescripcion());
            stmt.setString(4, producto.getFabricante());
            stmt.setString(5, producto.getLote());
            stmt.setInt(6, producto.getCantidadStock());
            stmt.setInt(7, producto.getStockMinimo());
            
            if (producto.getFechaVencimiento() != null) {
                stmt.setDate(8, new java.sql.Date(producto.getFechaVencimiento().getTime()));
            } else {
                stmt.setNull(8, Types.DATE);
            }
            
            stmt.setDouble(9, producto.getPrecioVenta());
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        producto.setId(generatedKeys.getInt(1));
                    }
                }
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al agregar producto: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Inventario> listarTodos() {
        List<Inventario> productos = new ArrayList<>();
        String sql = "SELECT id, nombre_producto, producto_tipo_id, descripcion, fabricante, " +
                    "lote, cantidad_stock, stock_minimo, fecha_vencimiento, precio_venta " +
                    "FROM inventario ORDER BY nombre_producto";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Inventario producto = mapResultSetToInventario(rs);
                productos.add(producto);
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar productos: " + e.getMessage(), e);
        }
        
        return productos;
    }

    @Override
    public Optional<Inventario> buscarPorId(Integer id) {
        String sql = "SELECT id, nombre_producto, producto_tipo_id, descripcion, fabricante, " +
                    "lote, cantidad_stock, stock_minimo, fecha_vencimiento, precio_venta " +
                    "FROM inventario WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToInventario(rs));
                }
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar producto por ID: " + e.getMessage(), e);
        }
        
        return Optional.empty();
    }

    @Override
    public List<Inventario> buscarPorNombre(String nombre) {
        List<Inventario> productos = new ArrayList<>();
        String sql = "SELECT id, nombre_producto, producto_tipo_id, descripcion, fabricante, " +
                    "lote, cantidad_stock, stock_minimo, fecha_vencimiento, precio_venta " +
                    "FROM inventario WHERE nombre_producto LIKE ? ORDER BY nombre_producto";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, "%" + nombre + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Inventario producto = mapResultSetToInventario(rs);
                    productos.add(producto);
                }
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar productos por nombre: " + e.getMessage(), e);
        }
        
        return productos;
    }

    @Override
    public List<Inventario> buscarPorTipo(Integer productoTipoId) {
        List<Inventario> productos = new ArrayList<>();
        String sql = "SELECT id, nombre_producto, producto_tipo_id, descripcion, fabricante, " +
                    "lote, cantidad_stock, stock_minimo, fecha_vencimiento, precio_venta " +
                    "FROM inventario WHERE producto_tipo_id = ? ORDER BY nombre_producto";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, productoTipoId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Inventario producto = mapResultSetToInventario(rs);
                    productos.add(producto);
                }
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar productos por tipo: " + e.getMessage(), e);
        }
        
        return productos;
    }

    @Override
    public List<Inventario> buscarStockBajo() {
        List<Inventario> productos = new ArrayList<>();
        String sql = "SELECT id, nombre_producto, producto_tipo_id, descripcion, fabricante, " +
                    "lote, cantidad_stock, stock_minimo, fecha_vencimiento, precio_venta " +
                    "FROM inventario WHERE cantidad_stock <= stock_minimo ORDER BY cantidad_stock ASC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Inventario producto = mapResultSetToInventario(rs);
                productos.add(producto);
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar productos con stock bajo: " + e.getMessage(), e);
        }
        
        return productos;
    }

    @Override
    public List<Inventario> buscarProximosAVencer(int dias) {
        List<Inventario> productos = new ArrayList<>();
        String sql = "SELECT id, nombre_producto, producto_tipo_id, descripcion, fabricante, " +
                    "lote, cantidad_stock, stock_minimo, fecha_vencimiento, precio_venta " +
                    "FROM inventario WHERE fecha_vencimiento BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL ? DAY) " +
                    "ORDER BY fecha_vencimiento ASC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, dias);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Inventario producto = mapResultSetToInventario(rs);
                    productos.add(producto);
                }
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar productos próximos a vencer: " + e.getMessage(), e);
        }
        
        return productos;
    }

    @Override
    public List<Inventario> buscarVencidos() {
        List<Inventario> productos = new ArrayList<>();
        String sql = "SELECT id, nombre_producto, producto_tipo_id, descripcion, fabricante, " +
                    "lote, cantidad_stock, stock_minimo, fecha_vencimiento, precio_venta " +
                    "FROM inventario WHERE fecha_vencimiento < CURDATE() ORDER BY fecha_vencimiento ASC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Inventario producto = mapResultSetToInventario(rs);
                productos.add(producto);
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar productos vencidos: " + e.getMessage(), e);
        }
        
        return productos;
    }

    @Override
    public void actualizarProducto(Inventario producto) {
        String sql = "UPDATE inventario SET nombre_producto = ?, producto_tipo_id = ?, descripcion = ?, " +
                    "fabricante = ?, lote = ?, cantidad_stock = ?, stock_minimo = ?, " +
                    "fecha_vencimiento = ?, precio_venta = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, producto.getNombreProducto());
            stmt.setInt(2, producto.getProductoTipoId());
            stmt.setString(3, producto.getDescripcion());
            stmt.setString(4, producto.getFabricante());
            stmt.setString(5, producto.getLote());
            stmt.setInt(6, producto.getCantidadStock());
            stmt.setInt(7, producto.getStockMinimo());
            
            if (producto.getFechaVencimiento() != null) {
                stmt.setDate(8, new java.sql.Date(producto.getFechaVencimiento().getTime()));
            } else {
                stmt.setNull(8, Types.DATE);
            }
            
            stmt.setDouble(9, producto.getPrecioVenta());
            stmt.setInt(10, producto.getId());
            
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar producto: " + e.getMessage(), e);
        }
    }

    @Override
    public void actualizarStock(Integer id, Integer nuevaCantidad) {
        String sql = "UPDATE inventario SET cantidad_stock = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, nuevaCantidad);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar stock: " + e.getMessage(), e);
        }
    }

    @Override
    public void deducirStock(Integer id, Integer cantidad) {
        String sql = "UPDATE inventario SET cantidad_stock = cantidad_stock - ? WHERE id = ? AND cantidad_stock >= ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, cantidad);
            stmt.setInt(2, id);
            stmt.setInt(3, cantidad);
            
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("Stock insuficiente para el producto ID: " + id);
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al deducir stock: " + e.getMessage(), e);
        }
    }

    @Override
    public void eliminarProducto(Integer id) {
        String sql = "DELETE FROM inventario WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar producto: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean existeProducto(String nombreProducto) {
        String sql = "SELECT COUNT(*) FROM inventario WHERE nombre_producto = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, nombreProducto);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al verificar producto: " + e.getMessage(), e);
        }
        
        return false;
    }

    @Override
    public boolean hayStockSuficiente(Integer id, Integer cantidadRequerida) {
        String sql = "SELECT cantidad_stock FROM inventario WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("cantidad_stock") >= cantidadRequerida;
                }
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al verificar stock: " + e.getMessage(), e);
        }
        
        return false;
    }

    @Override
    public List<Inventario> obtenerProductosMasVendidos(int limite) {
        // Esta implementación se completará cuando tengamos el módulo de ventas
        List<Inventario> productos = new ArrayList<>();
        // Por ahora retornamos productos con mayor stock como placeholder
        String sql = "SELECT id, nombre_producto, producto_tipo_id, descripcion, fabricante, " +
                    "lote, cantidad_stock, stock_minimo, fecha_vencimiento, precio_venta " +
                    "FROM inventario ORDER BY cantidad_stock DESC LIMIT ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, limite);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Inventario producto = mapResultSetToInventario(rs);
                    productos.add(producto);
                }
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener productos más vendidos: " + e.getMessage(), e);
        }
        
        return productos;
    }

    // Método auxiliar para mapear ResultSet a objeto Inventario
    private Inventario mapResultSetToInventario(ResultSet rs) throws SQLException {
        Inventario producto = new Inventario();
        producto.setId(rs.getInt("id"));
        producto.setNombreProducto(rs.getString("nombre_producto"));
        producto.setProductoTipoId(rs.getInt("producto_tipo_id"));
        producto.setDescripcion(rs.getString("descripcion"));
        producto.setFabricante(rs.getString("fabricante"));
        producto.setLote(rs.getString("lote"));
        producto.setCantidadStock(rs.getInt("cantidad_stock"));
        producto.setStockMinimo(rs.getInt("stock_minimo"));
        producto.setFechaVencimiento(rs.getDate("fecha_vencimiento"));
        producto.setPrecioVenta(rs.getDouble("precio_venta"));
        return producto;
    }
}