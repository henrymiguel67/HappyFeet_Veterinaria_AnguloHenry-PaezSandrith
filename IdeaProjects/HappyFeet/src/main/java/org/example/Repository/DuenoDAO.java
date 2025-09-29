package org.example.Repository;

import org.example.ConnectionSingleton;
import org.example.model.entities.Dueno;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DuenoDAO implements IDuenoDAO {

    private final Connection connection;
    private static final Logger logger = Logger.getLogger(DuenoDAO.class.getName());

    public DuenoDAO() {
        this.connection = ConnectionSingleton.getInstance().getConnection();
    }

    @Override
    public void agregarDueno(Dueno dueno) throws DuenoRepositoryException {
        String sql = "INSERT INTO duenos (nombre_completo, telefono, email, documento_identidad) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, dueno.getNombre());
            stmt.setString(2, dueno.getTelefono());
            stmt.setString(3, dueno.getEmail());
            stmt.setString(4, dueno.getDocumentoIdentidad());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet keys = stmt.getGeneratedKeys()) {
                    if (keys.next()) dueno.setId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al agregar dueño: " + dueno, e);
            throw new DuenoRepositoryException("Error al agregar dueño", e);
        }
    }

    @Override
    public List<Dueno> listarTodos() throws DuenoRepositoryException {
        List<Dueno> lista = new ArrayList<>();
        String sql = "SELECT id, nombre_completo, telefono, email, documento_identidad FROM duenos";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Dueno dueno = new Dueno(
                        rs.getInt("id"),
                        rs.getString("nombre_completo"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getString("documento_identidad")
                );
                lista.add(dueno);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al listar dueños", e);
            throw new DuenoRepositoryException("Error al listar dueños", e);
        }
        return lista;
    }
}
