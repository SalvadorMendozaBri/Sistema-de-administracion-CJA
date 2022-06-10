/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Catalogos;

/**
 *
 * @author chava
 */
public class Proveedores {

    private String codigo;
    private String nombre;
    private String telefono;
    private String domicilio;
    private String tipo;

    public Proveedores(String codigo, String nombre, String telefono, String domicilio, String tipo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.telefono = telefono;
        this.domicilio = domicilio;
        this.tipo = tipo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
