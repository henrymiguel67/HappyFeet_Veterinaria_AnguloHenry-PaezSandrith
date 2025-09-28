package org.example.model.entities;

import java.util.Date;

public class Inventario {
    private int id;
    private String nombreProducto;
    public int producto_tipo_id;
    public String descripcion;
    public String fabricante;
    public String lote;
    public int cantidad_stock;
    public int stock_minimo;
    public Date fecha_Vencimiento;
    public double precio_venta;

    // Builder pattern to avoid too many constructor parameters
    public static class Builder {
        private int id;
        private String nombreProducto;
        private int producto_tipo_id;
        private String descripcion;
        private String fabricante;
        private String lote;
        private int cantidad_stock;
        private int stock_minimo;
        private Date fecha_Vencimiento;
        private double precio_venta;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setNombreProducto(String nombreProducto) {
            this.nombreProducto = nombreProducto;
            return this;
        }

        public Builder setProducto_tipo_id(int producto_tipo_id) {
            this.producto_tipo_id = producto_tipo_id;
            return this;
        }

        public Builder setDescripcion(String descripcion) {
            this.descripcion = descripcion;
            return this;
        }

        public Builder setFabricante(String fabricante) {
            this.fabricante = fabricante;
            return this;
        }

        public Builder setLote(String lote) {
            this.lote = lote;
            return this;
        }

        public Builder setCantidad_stock(int cantidad_stock) {
            this.cantidad_stock = cantidad_stock;
            return this;
        }

        public Builder setStock_minimo(int stock_minimo) {
            this.stock_minimo = stock_minimo;
            return this;
        }

        public Builder setFecha_Vencimiento(Date fecha_Vencimiento) {
            this.fecha_Vencimiento = fecha_Vencimiento;
            return this;
        }

        public Builder setPrecio_venta(double precio_venta) {
            this.precio_venta = precio_venta;
            return this;
        }

        public Inventario build() {
            return new Inventario(this);
        }
    }

    private Inventario(Builder builder) {
        this.id = builder.id;
        this.nombreProducto = builder.nombreProducto;
        this.producto_tipo_id = builder.producto_tipo_id;
        this.descripcion = builder.descripcion;
        this.fabricante = builder.fabricante;
        this.lote = builder.lote;
        this.cantidad_stock = builder.cantidad_stock;
        this.stock_minimo = builder.stock_minimo;
        this.fecha_Vencimiento = builder.fecha_Vencimiento;
        this.precio_venta = builder.precio_venta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getProducto_tipo_id() {
        return producto_tipo_id;
    }

    public void setProducto_tipo_id(int producto_tipo_id) {
        this.producto_tipo_id = producto_tipo_id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public int getCantidad_stock() {
        return cantidad_stock;
    }

    public void setCantidad_stock(int cantidad_stock) {
        this.cantidad_stock = cantidad_stock;
    }

    public int getStock_minimo() {
        return stock_minimo;
    }

    public void setStock_minimo(int stock_minimo) {
        this.stock_minimo = stock_minimo;
    }

    public Date getFecha_Vencimiento() {
        return fecha_Vencimiento;
    }

    public void setFecha_Vencimiento(Date fecha_Vencimiento) {
        this.fecha_Vencimiento = fecha_Vencimiento;
    }

    public double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(double precio_venta) {
        this.precio_venta = precio_venta;
    }
}
