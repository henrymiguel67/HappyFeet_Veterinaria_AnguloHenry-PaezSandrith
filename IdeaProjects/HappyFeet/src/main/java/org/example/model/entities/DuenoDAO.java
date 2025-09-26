package org.example.model.entities;

import org.example.ConnexionSingleton;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import java.util.logging.Logger;

public class DuenoDAO extends IDuenoDAO {
    private final Connection connection;
    private static final Logger logger = Logger.getLogger(DuenoDAO.class.getName());

    public DuenoDAO() {
        connection = ConnexionSingleton.getInstance().getConnection();
    }

    public void agregarDueno(Dueno dueno) throws DuenoAddException {
        String sql = "INSERT INTO dueno (id, nombre, telefono, email, documento_identidad) VALUES (?,?,?,?,?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, dueno.getId());
            pstmt.setString(2, dueno.getNombre());
            pstmt.setString(3,dueno.getTelefono());
            pstmt.setString(4,dueno.getEmail());
            pstmt.setString(5,dueno.getDocumento_identidad());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DuenoAddException("ERROR a agregar dueño", e);

        }
    }

    @Override
    public List<Dueno> listarTodos() throws DuenoListException {
        List<Dueno> lista = new ArrayList<>();
        String sql = "SELECT id, nombre, telefono, email, documento_identidad FROM dueno";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Dueno dueno = new Dueno(rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("telefono"),
                        rs.getString("Email"),
                        rs.getString("documento_identidad"));

                lista.add(dueno);
            }
        } catch (SQLException e) {
            throw new DuenoListException("ERROR a listar duenos", e);
        }
        return lista;
    }
    @Override
    Dueno buscarPorId(Integer  id){
        Dueno dueno = null;
        String sql = "SELECT id, nombre, telefono, email, documento_identidad FROM dueno WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql);){
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                dueno = new Dueno(rs.getInt("id"),
                        rs.getString("nombre"));
            }

        }catch (SQLException e){
            throw new DuenoSearchException("ERROR al buscar dueno con ID: " + id, e);
        }
        return dueno;
    }
    @Override
    public  void  actulizarDueno(Dueno dueno){
        String sql = "Update dueno set nombre = ?, telefono = ?,email = ?, documento_identidad = ?, where id =?";

        try (PreparedStatement pstmt =connection.prepareStatement(sql)){
            pstmt.setInt(1,dueno.getId());
            pstmt.setString(2,dueno.getNombre());
            pstmt.setString(3,dueno.getDocumento_identidad());
            logger.info("Dueño agregado: " + dueno);
            pstmt.setString(5,dueno.getEmail());
            pstmt.executeUpdate();
            logger.info("Dueño agregado: " + dueno);


        } catch (SQLException e) {
            throw new DuenoUpdateException("ERROR al Actualizar Dueño: " + dueno, e);
        }
    }
    @Override
      void  eliminarDueno(Integer id){
        String sql = "Delete  dueno From dueno where id =?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setInt(1,id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new DuenoDeleteException("ERROR al eliminar dueno ID:" + id, e);
        }

    }

}

