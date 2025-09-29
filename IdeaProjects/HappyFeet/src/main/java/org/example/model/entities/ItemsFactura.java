package org.example.model.entities;

/**
 * Clase que representa un item de factura en la veterinaria Happy Feet
 */
public class ItemsFactura {

    private int id;
    private int idFactura;
    private int productoId;
    private String servicioDescripcion;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;

    // Constructor
    public ItemsFactura(int id, int idFactura, int productoId, String servicioDescripcion,
                        int cantidad, double precioUnitario, double subtotal) {
        this.id = id;
        this.idFactura = idFactura;
        this.productoId = productoId;
        this.servicioDescripcion = servicioDescripcion;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public String getServicioDescripcion() {
        return servicioDescripcion;
    }

    public void setServicioDescripcion(String servicioDescripcion) {
        this.servicioDescripcion = servicioDescripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "ItemsFactura{" +
                "id=" + id +
                ", idFactura=" + idFactura +
                ", productoId=" + productoId +
                ", servicioDescripcion='" + servicioDescripcion + '\'' +
                ", cantidad=" + cantidad +
                ", precioUnitario=" + precioUnitario +
                ", subtotal=" + subtotal +
                '}';
    }
}
