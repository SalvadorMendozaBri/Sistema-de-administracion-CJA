/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Catalogos;

public class Productos {

    private String id;
    private String nombreCorto;
    private String descripcion;
    private String unidad;
    private double costo;
    private String clase;
    private String subclase;
    private String proveedores;

    public Productos(String id, String nombreCorto, String descripcion, String unidad, double costo, String clase, String subclase, String proveedores) {
        this.id = id;
        this.nombreCorto = nombreCorto;
        this.descripcion = descripcion;
        this.unidad = unidad;
        this.costo = costo;
        this.clase = clase;
        this.subclase = subclase;
        this.proveedores = proveedores;
    }

  

    public String getProveedores() {
        return proveedores;
    }

    public void setProveedores(String proveedores) {
        this.proveedores = proveedores;
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

    public void setNombreCorto(String nombre_corto) {
        this.nombreCorto = nombre_corto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getSubclase() {
        return subclase;
    }

    public void setSubclase(String subclase) {
        this.subclase = subclase;
    }

}
