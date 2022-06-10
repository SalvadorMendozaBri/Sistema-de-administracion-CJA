package Catalogos;

import java.sql.Date;

public class Proyectos {

    private String idProyecto;
    private String nombre;
    private String descripcion;
    private Date fechaContrato;
    private Date fechaEntrega;

    public Proyectos(String idProyecto, String nombre, String descripcion, Date fechaContrato, Date fechaEntrega) {
        this.idProyecto = idProyecto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaContrato = fechaContrato;
        this.fechaEntrega = fechaEntrega;
    }

    public String getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(String idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaContrato() {
        return fechaContrato;
    }

    public void setFechaContrato(Date fechaContrato) {
        this.fechaContrato = fechaContrato;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }
    

   
  

}
