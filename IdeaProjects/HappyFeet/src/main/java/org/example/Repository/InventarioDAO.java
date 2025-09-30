package org.example.Repository;

import org.example.model.entities.Inventario;
import org.example.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class InventarioDAO implements IInventarioDAO {
    
@Override
public Inventario agregarProducto(Inventario producto) {
    String sql = "INSERT INTO inventario (nombre_producto, producto_tipo_id, descripcion, " +
                 "fabricante, lote, cantidad_stock, stock_minimo, fecha_vencimiento, precio_venta) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try (Connection conn = DatabaseConnection.getInstance().getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        // ... (configurar parámetros igual que antes)

        int affectedRows = stmt.executeUpdate();
        if (affectedRows > 0) {
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    // Crear y devolver una nueva instancia con el ID actualizado
                    return new Inventario.Builder()
                            .setId(generatedKeys.getInt(1))
                            .setNombreProducto(producto.getNombreProducto())
                            .setProductoTipoId(producto.getProductoTipoId())
                            .setDescripcion(producto.getDescripcion())
                            .setFabricante(producto.getFabricante())
                            .setLote(producto.getLote())
                            .setCantidadStock(producto.getCantidadStock())
                            .setStockMinimo(producto.getStockMinimo())
                            .setFechaVencimiento(producto.getFechaVencimiento())
                            .setPrecioVenta(producto.getPrecioVenta())
                            .build();
                }
            }
        }
        return producto; // Si no se generó ID, devolver el original
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

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                productos.add(mapResultSetToInventario(rs));
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

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
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

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + nombre + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    productos.add(mapResultSetToInventario(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar productos por nombre: " + e.getMessage(), e);
        }
        return productos;
    }

    // Otros métodos de la interfaz IInventarioDAO se implementan de manera similar...

    private Inventario mapResultSetToInventario(ResultSet rs) throws SQLException {
        java.sql.Date fechaVencimientoSqlDate = rs.getDate("fecha_vencimiento");
        Date fechaVencimiento = null;
        if (fechaVencimientoSqlDate != null) {
            fechaVencimiento = new Date(fechaVencimientoSqlDate.getTime());
        }

        return new Inventario.Builder()
                .setId(rs.getInt("id"))
                .setNombreProducto(rs.getString("nombre_producto"))
                .setProductoTipoId(rs.getInt("producto_tipo_id"))
                .setDescripcion(rs.getString("descripcion"))
                .setFabricante(rs.getString("fabricante"))
                .setLote(rs.getString("lote"))
                .setCantidadStock(rs.getInt("cantidad_stock"))
                .setStockMinimo(rs.getInt("stock_minimo"))
                .setFechaVencimiento(fechaVencimiento)
                .setPrecioVenta(rs.getDouble("precio_venta"))
                .build();
    }

    @Override
    public List<Inventario> buscarPorTipo(Integer productoTipoId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorTipo'");
    }

    @Override
    public List<Inventario> buscarStockBajo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarStockBajo'");
    }

    @Override
    public List<Inventario> buscarProximosAVencer(int dias) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarProximosAVencer'");
    }

    @Override
    public List<Inventario> buscarVencidos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarVencidos'");
    }

    @Override
    public void actualizarProducto(Inventario producto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actualizarProducto'");
    }

    @Override
    public void actualizarStock(Integer id, Integer nuevaCantidad) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actualizarStock'");
    }

    @Override
    public void deducirStock(Integer id, Integer cantidad) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deducirStock'");
    }

    @Override
    public void eliminarProducto(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarProducto'");
    }

    @Override
    public boolean existeProducto(String nombreProducto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'existeProducto'");
    }

    @Override
    public boolean hayStockSuficiente(Integer id, Integer cantidadRequerida) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hayStockSuficiente'");
    }

    @Override
    public List<Inventario> obtenerProductosMasVendidos(int limite) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerProductosMasVendidos'");
    }
}
