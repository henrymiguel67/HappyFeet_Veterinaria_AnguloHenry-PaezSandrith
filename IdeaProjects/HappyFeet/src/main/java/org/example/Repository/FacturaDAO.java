package org.example.Repository;

import org.example.model.entities.Factura;
import org.example.DatabaseConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FacturaDAO implements IFacturaDAO {

    @Override
    public void generarFactura(Factura factura) {
        String sql = "INSERT INTO facturas (dueno_id, fecha_emision, total) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, factura.getDuenoId());
            stmt.setTimestamp(2, Timestamp.valueOf(factura.getFechaEmision()));
            stmt.setDouble(3, factura.getTotal());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        factura.setId(generatedKeys.getInt(1));
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al generar factura: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Factura> listarTodasLasFacturas() {
        List<Factura> facturas = new ArrayList<>();
        String sql = "SELECT id, dueno_id, fecha_emision, total FROM facturas ORDER BY fecha_emision DESC";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Factura factura = mapResultSetToFactura(rs);
                facturas.add(factura);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al listar facturas: " + e.getMessage(), e);
        }

        return facturas;
    }

    @Override
    public Optional<Factura> buscarPorId(Integer id) {
        String sql = "SELECT id, dueno_id, fecha_emision, total FROM facturas WHERE id = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToFactura(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar factura por ID: " + e.getMessage(), e);
        }

        return Optional.empty();
    }

    @Override
    public List<Factura> buscarPorDuenoId(Integer duenoId) {
        List<Factura> facturas = new ArrayList<>();
        String sql = "SELECT id, dueno_id, fecha_emision, total FROM facturas WHERE dueno_id = ? ORDER BY fecha_emision DESC";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, duenoId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Factura factura = mapResultSetToFactura(rs);
                    facturas.add(factura);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar facturas por dueño: " + e.getMessage(), e);
        }

        return facturas;
    }

    @Override
    public List<Factura> buscarPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        List<Factura> facturas = new ArrayList<>();
        String sql = "SELECT id, dueno_id, fecha_emision, total FROM facturas " +
                     "WHERE fecha_emision BETWEEN ? AND ? ORDER BY fecha_emision DESC";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setTimestamp(1, Timestamp.valueOf(fechaInicio));
            stmt.setTimestamp(2, Timestamp.valueOf(fechaFin));
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Factura factura = mapResultSetToFactura(rs);
                    facturas.add(factura);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar facturas por rango de fechas: " + e.getMessage(), e);
        }

        return facturas;
    }

    @Override
    public Optional<Factura> obtenerUltimaFactura() {
        String sql = "SELECT id, dueno_id, fecha_emision, total FROM facturas ORDER BY id DESC LIMIT 1";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return Optional.of(mapResultSetToFactura(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener última factura: " + e.getMessage(), e);
        }

        return Optional.empty();
    }

    @Override
    public void actualizarFactura(Factura factura) {
        String sql = "UPDATE facturas SET dueno_id = ?, fecha_emision = ?, total = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, factura.getDuenoId());
            stmt.setTimestamp(2, Timestamp.valueOf(factura.getFechaEmision()));
            stmt.setDouble(3, factura.getTotal());
            stmt.setInt(4, factura.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar factura: " + e.getMessage(), e);
        }
    }

    @Override
    public void eliminarFactura(Integer id) {
        String sql = "DELETE FROM facturas WHERE id = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar factura: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean existeFactura(Integer id) {
        String sql = "SELECT COUNT(*) FROM facturas WHERE id = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al verificar existencia de factura: " + e.getMessage(), e);
        }

        return false;
    }

    @Override
    public Double obtenerTotalFacturadoPorPeriodo(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        String sql = "SELECT SUM(total) FROM facturas WHERE fecha_emision BETWEEN ? AND ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setTimestamp(1, Timestamp.valueOf(fechaInicio));
            stmt.setTimestamp(2, Timestamp.valueOf(fechaFin));
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble(1);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener total facturado: " + e.getMessage(), e);
        }

        return 0.0;
    }

 @Override
public Integer contarFacturasPorPeriodo(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
    String sql = "SELECT COUNT(*) FROM facturas WHERE fecha_emision BETWEEN ? AND ?";

    try (Connection conn = DatabaseConnection.getInstance().getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setTimestamp(1, Timestamp.valueOf(fechaInicio));
        stmt.setTimestamp(2, Timestamp.valueOf(fechaFin));
        
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0; 
        }
    } catch (SQLException e) {
       
        return null; 
    }
}
            // Método auxiliar para mapear un ResultSet a un objeto Factura
private Factura mapResultSetToFactura(ResultSet rs) throws SQLException {
    Factura factura = new Factura();
    factura.setId(rs.getInt("id"));
    factura.setDuenoId(rs.getInt("dueno_id"));
    factura.setFechaEmision(rs.getTimestamp("fecha_emision").toLocalDateTime());
    factura.setTotal(rs.getDouble("total"));
    return factura;
}


        }
