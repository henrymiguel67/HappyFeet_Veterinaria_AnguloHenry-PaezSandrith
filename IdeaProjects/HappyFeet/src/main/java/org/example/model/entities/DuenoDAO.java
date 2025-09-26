package org.example.model.entities;

import org.example.ConnexionSingleton;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DuenoDAO extends iduenoDAO {
    private final Connection connection;

    public DuenoDAO() {
        connection = ConnexionSingleton.getInstance().getConnection();
    }

    public void agregarDueno(Dueno dueno) {
        String sql = "INSERT INTO dueno (id, nombre, telefono, email, documento_identidad) VALUES (?,?,?,?,?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, dueno.getId());
            pstmt.setString(2, dueno.getNombre());
            pstmt.setString(3,dueno.getTelefono());
            pstmt.setString(4,dueno.getEmail());
            pstmt.setString(5,dueno.getDocumento_identidad());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("ERROR a agregar dueño");

        }
    }

    @Override
    public List<Dueno> listarTodos() {
        List<Dueno> Lista= new ArrayList<>();
        String sql = "SELECT * FROM dueno";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Dueno dueno = new Dueno(rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("telefono"),
                        rs.getString("Email"),
                        rs.getString("documento_identidad"));

                Lista.add(dueno);
            }
        } catch (SQLException e) {
            throw new RuntimeException("ERROR a listar duenos");
        }
        return Lista;
    }
    @Override
    Dueno buscarPorId(Integer  id){
        Dueno dueno = null;
        String sql = "SELECT * FROM dueno WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql);){
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                dueno = new Dueno(rs.getInt("id"),
                        rs.getString("nombre"));
            }

        }catch (SQLException e){
            throw new RuntimeException("ERROR a buscar dueno" + id);
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
            pstmt.setString(4,dueno.getTelefono());
            pstmt.setString(5,dueno.getEmail());
            pstmt.executeUpdate();
            System.out.println("Dueño agregado:" + dueno);


        } catch (SQLException e) {
            throw new RuntimeException("ERROR al Actualizar  Dueño" + dueno);
        }
    }
    @Override
      void  eliminarDueno(Integer id){
        String sql = "Delete  dueno From dueno where id =?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setInt(1,id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("ERROR al eliminar dueno ID:" + id);
        }

    }

}

