/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package preoportunidades;

import java.sql.Date;
import javafx.scene.control.CheckBox;

public class Preoportunidades {
     private String folio;
     private Date fechaRegistro;
     private String usuario;
     private String prospecto;
     private Date fechaCompromiso;
     private String verificacionIngenieria;
     private CheckBox checkIngenieria;
     private String vendedor;
     private String status;

    public Preoportunidades(String folio, Date fechaRegistro, String usuario, String prospecto, Date fechaCompromiso, String verificacionIngenieria, String vendedor, String status) {
        this.folio = folio;
        this.fechaRegistro = fechaRegistro;
        this.usuario = usuario;
        this.prospecto = prospecto;
        this.fechaCompromiso = fechaCompromiso;
        this.verificacionIngenieria = verificacionIngenieria;       
        this.vendedor = vendedor;
        this.status = status;
    }

    public CheckBox getCheckIngenieria() {
        return checkIngenieria;
    }

    public void setCheckIngenieria(CheckBox checkIngenieria) {
        this.checkIngenieria = checkIngenieria;
    }

    
    
    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getProspecto() {
        return prospecto;
    }

    public void setProspecto(String prospecto) {
        this.prospecto = prospecto;
    }

    public Date getFechaCompromiso() {
        return fechaCompromiso;
    }

    public void setFechaCompromiso(Date fechaCompromiso) {
        this.fechaCompromiso = fechaCompromiso;
    }

    public String getVerificacionIngenieria() {
        return verificacionIngenieria;
    }

    public void setVerificacionIngenieria(String verificacionIngenieria) {
        this.verificacionIngenieria = verificacionIngenieria;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
     
     
}
