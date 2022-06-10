/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prospectos;

import java.util.Date;

public class Prospectos {

    private String folio;
    private Date fecha_Registro;
    private String usuario_Registro;
    private String nombre;
    private String calle;
    private String correo;
    private String telefono;
    private String vendedor_Encargado;
    private String status;

    public Prospectos(String folio, Date fecha_Registro, String usuario_Registro, String nombre, String calle, String correo, String telefono, String vendedor_Encargado, String status) {
        this.folio = folio;
        this.fecha_Registro = fecha_Registro;
        this.usuario_Registro = usuario_Registro;
        this.nombre = nombre;
        this.calle = calle;
        this.correo = correo;
        this.telefono = telefono;
        this.vendedor_Encargado = vendedor_Encargado;
        this.status = status;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public void setFecha_Registro(Date fecha_Registro) {
        this.fecha_Registro = fecha_Registro;
    }

    public void setUsuario_Registro(String usuario_Registro) {
        this.usuario_Registro = usuario_Registro;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setVendedor_Encargado(String vendedor_Encargado) {
        this.vendedor_Encargado = vendedor_Encargado;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFolio() {
        return folio;
    }

    public Date getFecha_Registro() {
        return fecha_Registro;
    }

    public String getUsuario_Registro() {
        return usuario_Registro;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCalle() {
        return calle;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getVendedor_Encargado() {
        return vendedor_Encargado;
    }

    public String getStatus() {
        return status;
    }

}
