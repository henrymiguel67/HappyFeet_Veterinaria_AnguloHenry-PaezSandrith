package org.example.Repository;

import org.example.model.entities.Factura;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IFacturaDAO {

    void generarFactura(Factura factura);

    List<Factura> listarTodas();

    Optional<Factura> buscarPorId(Integer id);

    List<Factura> buscarPorDuenoId(Integer duenoId);

    List<Factura> buscarPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    Optional<Factura> obtenerUltimaFactura();

    void actualizarFactura(Factura factura);

    void eliminarFactura(Integer id);

    boolean existeFactura(Integer id);

    Double obtenerTotalFacturadoPorPeriodo(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    Integer contarFacturasPorPeriodo(LocalDateTime fechaInicio, LocalDateTime fechaFin);
}
