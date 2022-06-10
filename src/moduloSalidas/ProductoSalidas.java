/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moduloSalidas;

import Catalogos.Productos;

/**
 *
 * @author chava
 */
public class ProductoSalidas  {
    private String id;
    private String nombreCorto;
    private String descripcion;
    private String unidad;
    private double cantidad;
    private double costo;
    private String tipo;
    private double cantidadTotal;

    public ProductoSalidas(String id, String nombreCorto, String descripcion, String unidad, double cantidad, double costo, String tipo) {
        this.id = id;
        this.nombreCorto = nombreCorto;
        this.descripcion = descripcion;
        this.unidad = unidad;
        this.cantidad = cantidad;
        this.costo = costo;
        this.tipo = tipo;
    }

    public ProductoSalidas(String id, String nombreCorto, String descripcion, String unidad, double cantidad, double costo, String tipo, double cantidad_total) {
        this.id = id;
        this.nombreCorto = nombreCorto;
        this.descripcion = descripcion;
        this.unidad = unidad;
        this.cantidad = cantidad;
        this.costo = costo;
        this.tipo = tipo;
        this.cantidadTotal = cantidad_total;
    }

    public double getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(double cantidad_total) {
        this.cantidadTotal = cantidad_total;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

   

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreCorto() {
        return nombreCorto;
    }

    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.descripcion = Descripcion;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String Unidad) {
        this.unidad = Unidad;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }
    
    
}
