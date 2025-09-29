package org.example.model.entities;

import java.util.Date;

public class Inventario {
    private final int id;
    private final String nombreProducto;
    private final int productoTipoId;
    private final String descripcion;
    private final String fabricante;
    private final String lote;
    private final int cantidadStock;
    private final int stockMinimo;
    private final Date fechaVencimiento;
    private final double precioVenta;

    private Inventario(Builder builder) {
        this.id = builder.id;
        this.nombreProducto = builder.nombreProducto;
        this.productoTipoId = builder.productoTipoId;
        this.descripcion = builder.descripcion;
        this.fabricante = builder.fabricante;
        this.lote = builder.lote;
        this.cantidadStock = builder.cantidadStock;
        this.stockMinimo = builder.stockMinimo;
        this.fechaVencimiento = builder.fechaVencimiento;
        this.precioVenta = builder.precioVenta;
    }

    public static class Builder {
        private int id;
        private String nombreProducto;
        private int productoTipoId;
        private String descripcion;
        private String fabricante;
        private String lote;
        private int cantidadStock;
        private int stockMinimo;
        private Date fechaVencimiento;
        private double precioVenta;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setNombreProducto(String nombreProducto) {
            this.nombreProducto = nombreProducto;
            return this;
        }

        public Builder setProductoTipoId(int productoTipoId) {
            this.productoTipoId = productoTipoId;
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

        public Builder setCantidadStock(int cantidadStock) {
            this.cantidadStock = cantidadStock;
            return this;
        }

        public Builder setStockMinimo(int stockMinimo) {
            this.stockMinimo = stockMinimo;
            return this;
        }

        public Builder setFechaVencimiento(Date fechaVencimiento) {
            this.fechaVencimiento = fechaVencimiento;
            return this;
        }

        public Builder setPrecioVenta(double precioVenta) {
            this.precioVenta = precioVenta;
            return this;
        }

        public Inventario build() {
            // Validaciones
            if (cantidadStock < 0) {
                throw new IllegalArgumentException("La cantidad en stock no puede ser negativa");
            }
            if (fechaVencimiento != null && fechaVencimiento.before(new Date())) {
                throw new IllegalArgumentException("La fecha de vencimiento no puede ser anterior a la fecha actual");
            }
            return new Inventario(this);
        }
    }

    // Métodos getter
    public int getId() {
        return id;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public int getProductoTipoId() {
        return productoTipoId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFabricante() {
        return fabricante;
    }

    public String getLote() {
        return lote;
    }

    public int getCantidadStock() {
        return cantidadStock;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }
}
