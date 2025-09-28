package org.example.model.entities; 

import org.example.model.entities.Dueno;  
import org.example.util.DatabaseConnection; 
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DuenoRepositoryImpl implements IDuenoDAO {

    @Override
    public void agregarDueno(Dueno dueno) throws DuenoRepositoryException {
        String sql = "INSERT INTO duenos (nombre_completo, documento_identidad, direccion, telefono, email) " +
                    "VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, dueno.getNombreCompleto());
            stmt.setString(2, dueno.getDocumentoIdentidad());
            stmt.setString(3, dueno.getDireccion());
            stmt.setString(4, dueno.getTelefono());
            stmt.setString(5, dueno.getEmail());
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        dueno.setId(generatedKeys.getInt(1));
                    }
                }
            }
            
        } catch (SQLException e) {
            throw new DuenoRepositoryException("Error al agregar dueño: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Dueno> listarTodos() {
        List<Dueno> duenos = new ArrayList<>();
        String sql = "SELECT id, nombre_completo, documento_identidad, direccion, telefono, email FROM duenos ORDER BY nombre_completo";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Dueno dueno = mapResultSetToDueno(rs);
                duenos.add(dueno);
            }
            
        } catch (SQLException e) {
            throw new DuenoRepositoryException("Error al listar dueños: " + e.getMessage(), e);
        }
        
        return duenos;
    }

    // ... (los demás métodos se mantienen igual, solo verifica los imports)
    
    // Método auxiliar para mapear ResultSet a objeto Dueno
    private Dueno mapResultSetToDueno(ResultSet rs) throws SQLException {
        Dueno dueno = new Dueno();
        dueno.setId(rs.getInt("id"));
        dueno.setNombreCompleto(rs.getString("nombre_completo"));
        dueno.setDocumentoIdentidad(rs.getString("documento_identidad"));
        dueno.setDireccion(rs.getString("direccion"));
        dueno.setTelefono(rs.getString("telefono"));
        dueno.setEmail(rs.getString("email"));
        return dueno;
    }
}