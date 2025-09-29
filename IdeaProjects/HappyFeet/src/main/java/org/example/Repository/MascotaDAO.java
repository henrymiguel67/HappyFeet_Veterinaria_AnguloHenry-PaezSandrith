package org.example.Repository;

import org.example.model.entities.Mascota;
import org.example.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MascotaDAO implements IMascotaDAO {

    @Override
    public void agregarMascota(Mascota mascota) {
        String sql = "INSERT INTO mascotas (dueno_id, nombre, raza_id, fecha_nacimiento, sexo, url_foto) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setInt(1, mascota.getDuenoId());
            stmt.setString(2, mascota.getNombre());
            stmt.setInt(3, mascota.getRazaId());
            stmt.setDate(4, new java.sql.Date(mascota.getFechaNacimiento().getTime()));
            stmt.setString(5, mascota.getSexo());
            stmt.setString(6, mascota.getUrlFoto());
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        mascota.setId(generatedKeys.getInt(1));
                    }
                }
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al agregar mascota: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Mascota> listarTodas() {
        List<Mascota> mascotas = new ArrayList<>();
        String sql = "SELECT id, dueno_id, nombre, raza_id, fecha_nacimiento, sexo, url_foto " +
                    "FROM mascotas ORDER BY nombre";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Mascota mascota = mapResultSetToMascota(rs);
                mascotas.add(mascota);
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar mascotas: " + e.getMessage(), e);
        }
        
        return mascotas;
    }

    @Override
    public Optional<Mascota> buscarPorId(Integer id) {
        String sql = "SELECT id, dueno_id, nombre, raza_id, fecha_nacimiento, sexo, url_foto " +
                    "FROM mascotas WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToMascota(rs));
                }
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar mascota por ID: " + e.getMessage(), e);
        }
        
        return Optional.empty();
    }

    @Override
    public List<Mascota> buscarPorDuenoId(Integer duenoId) {
        List<Mascota> mascotas = new ArrayList<>();
        String sql = "SELECT id, dueno_id, nombre, raza_id, fecha_nacimiento, sexo, url_foto " +
                    "FROM mascotas WHERE dueno_id = ? ORDER BY nombre";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, duenoId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Mascota mascota = mapResultSetToMascota(rs);
                    mascotas.add(mascota);
                }
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar mascotas por dueño: " + e.getMessage(), e);
        }
        
        return mascotas;
    }

    @Override
    public List<Mascota> buscarPorNombre(String nombre) {
        List<Mascota> mascotas = new ArrayList<>();
        String sql = "SELECT id, dueno_id, nombre, raza_id, fecha_nacimiento, sexo, url_foto " +
                    "FROM mascotas WHERE nombre LIKE ? ORDER BY nombre";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, "%" + nombre + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Mascota mascota = mapResultSetToMascota(rs);
                    mascotas.add(mascota);
                }
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar mascotas por nombre: " + e.getMessage(), e);
        }
        
        return mascotas;
    }

    @Override
    public void actualizarMascota(Mascota mascota) {
        String sql = "UPDATE mascotas SET dueno_id = ?, nombre = ?, raza_id = ?, " +
                    "fecha_nacimiento = ?, sexo = ?, url_foto = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, mascota.getDuenoId());
            stmt.setString(2, mascota.getNombre());
            stmt.setInt(3, mascota.getRazaId());
            stmt.setDate(4, new java.sql.Date(mascota.getFechaNacimiento().getTime()));
            stmt.setString(5, mascota.getSexo());
            stmt.setString(6, mascota.getUrlFoto());
            stmt.setInt(7, mascota.getId());
            
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar mascota: " + e.getMessage(), e);
        }
    }

    @Override
    public void eliminarMascota(Integer id) {
        String sql = "DELETE FROM mascotas WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar mascota: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean existeMascota(String nombre, Integer duenoId) {
        String sql = "SELECT COUNT(*) FROM mascotas WHERE nombre = ? AND dueno_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, nombre);
            stmt.setInt(2, duenoId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al verificar mascota: " + e.getMessage(), e);
        }
        
        return false;
    }

    // Método auxiliar para mapear ResultSet a objeto Mascota
    private Mascota mapResultSetToMascota(ResultSet rs) throws SQLException {
        Mascota mascota = new Mascota();
        mascota.setId(rs.getInt("id"));
        mascota.setDuenoId(rs.getInt("dueno_id"));
        mascota.setNombre(rs.getString("nombre"));
        mascota.setRazaId(rs.getInt("raza_id"));
        mascota.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
        mascota.setSexo(rs.getString("sexo"));
        mascota.setUrlFoto(rs.getString("url_foto"));
        return mascota;
    }
}