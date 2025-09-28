package org.example.model.entities;

import org.example.ConnectionSingleton;
import org.example.model.entities.Repository.DuenoRepositoryException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del DAO de Dueno usando ConnectionSingleton
 */
public class DuenoDAO implements IDuenoDAO {

    private final Connection connection;

    public DuenoDAO() {
        // Obtenemos la conexión singleton
        this.connection = ConnectionSingleton.getInstance().getConnection();
    }

    @Override
    public void agregarDueno(Dueno dueno) throws DuenoRepositoryException {
        String sql = "INSERT INTO dueno (nombre, telefono, email, documento_identidad) VALUES (?,?,?,?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, dueno.getNombre());
            pstmt.setString(2, dueno.getTelefono());
            pstmt.setString(3, dueno.getEmail());
            pstmt.setString(4, dueno.getDocumentoIdentidad());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        dueno.setId(generatedKeys.getInt(1)); // Asignamos ID generado por MySQL
                    }
                }
            }

        } catch (SQLException e) {
            throw new DuenoRepositoryException("Error al agregar dueño: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Dueno> listarTodos() throws DuenoRepositoryException {
        List<Dueno> lista = new ArrayList<>();
        String sql = "SELECT id, nombre, telefono, email, documento_identidad FROM dueno";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Dueno dueno = new Dueno(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getString("documento_identidad")
                );
                lista.add(dueno);
            }

        } catch (SQLException e) {
            throw new DuenoRepositoryException("Error al listar dueños: " + e.getMessage(), e);
        }

        return lista;
    }
}
