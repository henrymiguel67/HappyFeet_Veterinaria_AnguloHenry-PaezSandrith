package org.example.model.entities;

public class items_factura {
    public int id;
    public int id_factura;
    public int producto_id;
    public String servicio_descripcion;
    public  int cantidad;
    public double precio_unitario;
    public double subtotal;

    public items_factura(int id, int id_factura, int producto_id, String servicio_descripcion, int cantidad, double precio_unitario, double subtotal) {
        this.id = id;
        this.id_factura = id_factura;
        this.producto_id = producto_id;
        this.servicio_descripcion = servicio_descripcion;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
        this.subtotal = subtotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_factura() {
        return id_factura;
    }

    public void setId_factura(int id_factura) {
        this.id_factura = id_factura;
    }

    public int getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(int producto_id) {
        this.producto_id = producto_id;
    }

    public String getServicio_descripcion() {
        return servicio_descripcion;
    }

    public void setServicio_descripcion(String servicio_descripcion) {
        this.servicio_descripcion = servicio_descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}
