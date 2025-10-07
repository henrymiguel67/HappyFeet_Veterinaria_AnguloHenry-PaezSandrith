/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.example.DatabaseConnection;
import org.example.model.entities.HistorialMedico;

/**
 *
 * @author camper
 */
public class ExportarHistorialMedicoDAO extends IExportarHistorialMedico{
    
    @Override
     public void Exportar(HistorialMedico historial){
        String sql = "INSERT INTO historial_medico (id, mascota_id, fecha_evento, descripcion, diagnostico,tratamiento_recomendado) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1,historial.getId());
            stmt.setInt(1, historial.getMascotaId());
            stmt.setDate(1, (Date) historial.getFechaEvento());
            stmt.setString(1, historial.getDescripcion());
            
            

           

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        historial.setId(generatedKeys.getInt(1));
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al generar factura: " + e.getMessage(), e);
        }
    }
}
