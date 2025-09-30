package org.example.Repository;

import org.example.DatabaseConnection;
import org.example.model.entities.ItemsFactura;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ItemsFacturaDAO {

    private final Connection connection;
    private static final Logger logger = Logger.getLogger(ItemsFacturaDAO.class.getName());

    public ItemsFacturaDAO() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public void agregarItemFactura(ItemsFactura item) throws ItemsFacturaException {
        String sql = "INSERT INTO items_factura (id_factura, producto_id, servicio_descripcion, cantidad, precio_unitario, subtotal) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, item.getIdFactura());
            pstmt.setInt(2, item.getProductoId());
            pstmt.setString(3, item.getServicioDescripcion());
            pstmt.setInt(4, item.getCantidad());
            pstmt.setDouble(5, item.getPrecioUnitario());
            pstmt.setDouble(6, item.getSubtotal());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet keys = pstmt.getGeneratedKeys()) {
                    if (keys.next()) {
                        item.setId(keys.getInt(1));
                    }
                }
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al agregar item de factura", e);
            throw new ItemsFacturaException("Error al agregar item de factura", e);
        }
    }

    public List<ItemsFactura> listarTodos() throws ItemsFacturaException {
        List<ItemsFactura> lista = new ArrayList<>();
        String sql = "SELECT * FROM items_factura";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ItemsFactura item = new ItemsFactura(
                        rs.getInt("id"),
                        rs.getInt("id_factura"),
                        rs.getInt("producto_id"),
                        rs.getString("servicio_descripcion"),
                        rs.getInt("cantidad"),
                        rs.getDouble("precio_unitario"),
                        rs.getDouble("subtotal")
                );
                lista.add(item);
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al listar items de factura", e);
            throw new ItemsFacturaException("Error al listar items de factura", e);
        }

        return lista;
    }

    // Otros métodos potencialmente útiles:
    
    public List<ItemsFactura> buscarPorFactura(int idFactura) throws ItemsFacturaException {
        List<ItemsFactura> items = new ArrayList<>();
        String sql = "SELECT * FROM items_factura WHERE id_factura = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, idFactura);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    ItemsFactura item = new ItemsFactura(
                            rs.getInt("id"),
                            rs.getInt("id_factura"),
                            rs.getInt("producto_id"),
                            rs.getString("servicio_descripcion"),
                            rs.getInt("cantidad"),
                            rs.getDouble("precio_unitario"),
                            rs.getDouble("subtotal")
                    );
                    items.add(item);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al buscar items por factura", e);
            throw new ItemsFacturaException("Error al buscar items por factura", e);
        }
        
        return items;
    }
}