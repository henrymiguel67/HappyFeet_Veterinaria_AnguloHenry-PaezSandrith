package org.example.Repository;

import org.example.DatabaseConnection;

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
        this.connection = DatabaseConnection.getInstance().getConnection();
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

    /**
     *
     * @param criterio
     * @return
     * @throws DuenoRepositoryException
     */
    @Override
public List<Dueno> buscarPorNombreOTelefono(String criterio) throws DuenoRepositoryException {
    List<Dueno> lista = new ArrayList<>();
    String sql = "SELECT id, nombre_completo, telefono, email, documento_identidad " +
                 "FROM duenos WHERE nombre_completo LIKE ? OR telefono LIKE ?";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        String criterioLike = "%" + criterio + "%";
        stmt.setString(1, criterioLike);
        stmt.setString(2, criterioLike);

        try (ResultSet rs = stmt.executeQuery()) {
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
        }
    } catch (SQLException e) {
        logger.log(Level.SEVERE, "Error al buscar dueño por criterio: " + criterio, e);
        throw new DuenoRepositoryException("Error al buscar dueño por criterio", e);
    }
    return lista;
}

    /**
     *
     * @param id
     * @throws DuenoRepositoryException
     */
    @Override
public void eliminarDueno(int id) throws DuenoRepositoryException {
    String sql = "DELETE FROM duenos WHERE id = ?";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setInt(1, id);
        int affectedRows = stmt.executeUpdate();

        if (affectedRows == 0) {
            throw new DuenoRepositoryException("No se encontró un dueño con ID: " + id);
        }
    } catch (SQLException e) {
        logger.log(Level.SEVERE, "Error al eliminar dueño con ID: " + id, e);
        throw new DuenoRepositoryException("Error al eliminar dueño", e);
    }
}

    /**
     *
     * @param id
     * @return
     * @throws DuenoRepositoryException
     */
    @Override
public Dueno buscarPorId(int id) throws DuenoRepositoryException {
    String sql = "SELECT id, nombre_completo, telefono, email, documento_identidad FROM duenos WHERE id = ?";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setInt(1, id);

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return new Dueno(
                    rs.getInt("id"),
                    rs.getString("nombre_completo"),
                    rs.getString("telefono"),
                    rs.getString("email"),
                    rs.getString("documento_identidad")
                );
            } else {
                return null; // o lanzar excepción si prefieres
            }
        }
    } catch (SQLException e) {
        logger.log(Level.SEVERE, "Error al buscar dueño por ID: " + id, e);
        throw new DuenoRepositoryException("Error al buscar dueño por ID", e);
    }
}

    /**
     *
     * @param dueno
     * @throws DuenoRepositoryException
     */
    @Override
public void actualizarDueno(Dueno dueno) throws DuenoRepositoryException {
    String sql = "UPDATE duenos SET nombre_completo = ?, telefono = ?, email = ?, documento_identidad = ? WHERE id = ?";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setString(1, dueno.getNombre());
        stmt.setString(2, dueno.getTelefono());
        stmt.setString(3, dueno.getEmail());
        stmt.setString(4, dueno.getDocumentoIdentidad());
        stmt.setInt(5, dueno.getId());

        int affectedRows = stmt.executeUpdate();

        if (affectedRows == 0) {
            throw new DuenoRepositoryException("No se encontró un dueño con ID: " + dueno.getId());
        }
    } catch (SQLException e) {
        logger.log(Level.SEVERE, "Error al actualizar dueño: " + dueno, e);
        throw new DuenoRepositoryException("Error al actualizar dueño", e);
    }
}
}

