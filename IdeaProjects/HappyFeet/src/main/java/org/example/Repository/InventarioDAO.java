
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
            return producto;
        } catch (SQLException e) {
            throw new RuntimeException("Error al agregar producto: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Inventario> listarTodos() {
        List<Inventario> productos = new ArrayList<>();
        String sql = "SELECT * FROM inventario ORDER BY nombre_producto";

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
        String sql = "SELECT * FROM inventario WHERE id = ?";
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
        String sql = "SELECT * FROM inventario WHERE nombre_producto LIKE ? ORDER BY nombre_producto";
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
        List<Inventario> productos = new ArrayList<>();
        String sql = "SELECT * FROM inventario WHERE producto_tipo_id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productoTipoId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    productos.add(mapResultSetToInventario(rs));
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
        String sql = "SELECT * FROM inventario WHERE cantidad_stock <= stock_minimo";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                productos.add(mapResultSetToInventario(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar productos con stock bajo: " + e.getMessage(), e);
        }
        return productos;
    }

    @Override
    public List<Inventario> buscarProximosAVencer(int dias) {
        List<Inventario> productos = new ArrayList<>();
        String sql = "SELECT * FROM inventario WHERE fecha_vencimiento <= DATE_ADD(CURDATE(), INTERVAL ? DAY)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, dias);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    productos.add(mapResultSetToInventario(rs));
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
        String sql = "SELECT * FROM inventario WHERE fecha_vencimiento < CURDATE()";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                productos.add(mapResultSetToInventario(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar productos vencidos: " + e.getMessage(), e);
        }
        return productos;
    }

    @Override
    public void actualizarProducto(Inventario producto) {
        String sql = "UPDATE inventario SET nombre_producto=?, producto_tipo_id=?, descripcion=?, fabricante=?, " +
                "lote=?, cantidad_stock=?, stock_minimo=?, fecha_vencimiento=?, precio_venta=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
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
        String sql = "UPDATE inventario SET cantidad_stock=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
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
        String sql = "UPDATE inventario SET cantidad_stock = cantidad_stock - ? WHERE id=?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cantidad);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al deducir stock: " + e.getMessage(), e);
        }
    }

    @Override
    public void eliminarProducto(Integer id) {
        String sql = "DELETE FROM inventario WHERE id=?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar producto: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean existeProducto(String nombreProducto) {
        String sql = "SELECT COUNT(*) FROM inventario WHERE nombre_producto=?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombreProducto);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al verificar existencia de producto: " + e.getMessage(), e);
        }
        return false;
    }

    @Override
    public boolean hayStockSuficiente(Integer id, Integer cantidadRequerida) {
        String sql = "SELECT cantidad_stock FROM inventario WHERE id=?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("cantidad_stock") >= cantidadRequerida;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al verificar stock suficiente: " + e.getMessage(), e);
        }
        return false;
    }

    @Override
    public List<Inventario> obtenerProductosMasVendidos(int limite) {
        List<Inventario> productos = new ArrayList<>();
        String sql = "SELECT i.* FROM inventario i " +
                "JOIN items_factura itf ON i.id = itf.inventario_id " +
                "GROUP BY i.id " +
                "ORDER BY SUM(itf.cantidad) DESC LIMIT ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, limite);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    productos.add(mapResultSetToInventario(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener productos más vendidos: " + e.getMessage(), e);
        }
        return productos;
    }
}

