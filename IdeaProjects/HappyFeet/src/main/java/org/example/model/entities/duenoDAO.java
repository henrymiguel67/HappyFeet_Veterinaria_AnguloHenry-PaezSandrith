package org.example.model.entities;

import org.example.ConexionSingleton;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class duenoDAO extends iduenoDAO {
    private final Connection connection;

    public duenoDAO() {
        connection = ConexionSingleton.getInstance().getConnection();
    }

    public void agregarDueno(dueno dueno) {
        String sql = "INSERT INTO dueno VALUES (?,?,?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, dueno.getId());
            pstmt.setString(1, dueno.getNombre());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("ERROR a agregar dueño");

        }
    }

    @Override
    public List<dueno> listarTodos() {
        List<dueno> duenos = new ArrayList<>();
        String sql = "SELECT * FROM dueno";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                dueno dueno = new dueno(rs.getInt("id"),
                        rs.getString("nombre"));

                duenos.add(dueno);
            }
        } catch (SQLException e) {
            throw new RuntimeException("ERROR a listar duenos");
        }
        return duenos;
    }
    @Override
    dueno buscarPorId(Integer  id){
        dueno dueno = null;
        String sql = "SELECT * FROM dueno WHERE id = ?";



        try (PreparedStatement stmt = connection.prepareStatement(sql);){
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                dueno = new dueno(rs.getInt("id"),
                        rs.getString("nombre"));
            }

        }catch (SQLException e){
            throw new RuntimeException("ERROR a buscar dueno" + id);
        }
        return dueno;
    }
    @Override
    public  void  actulizarDueno(dueno dueno){
        String sql = "Update  estudiante set nombre = ?, sexo = ?  where id =?";

        try (PreparedStatement pstmt =connection.prepareStatement(sql)){
            pstmt.setInt(1,dueno.getId());
            pstmt.setString(1,dueno.getNombre());
            pstmt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException("ERROR al Actualizar  Duenño" + dueno);
        }
    }
    @Override
      void  eliminarDueno(Integer id){
        String sql = "Delete  estudiante From estudiante where id =?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setInt(1,id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("ERROR al eliminar dueno ID:" + id);
        }

    }

}