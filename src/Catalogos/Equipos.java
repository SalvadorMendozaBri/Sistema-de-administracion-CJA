
package Catalogos;

import java.sql.Date;

public class Equipos {
    private String folio;
    private Date fecha;
    private String nombreEquipo;
    private String descripcion;
    private String modelo;
    private String marca;
    private String caudal;
    private String presion;
    private String potencia;
    private String voltaje;
    private String amperaje;
    private String gasRefrigerante;
    private String capacidad;
    private String tipo;
    private double costo;

    public Equipos(String folio, Date fecha, String nombreEquipo, String descripcion, String modelo, String marca, String caudal, String presion, String potencia, String voltaje, String amperaje, String gasRefrigerante, String capacidad, String tipo, double costo) {
        this.folio = folio;
        this.fecha = fecha;
        this.nombreEquipo = nombreEquipo;
        this.descripcion = descripcion;
        this.modelo = modelo;
        this.marca = marca;
        this.caudal = caudal;
        this.presion = presion;
        this.potencia = potencia;
        this.voltaje = voltaje;
        this.amperaje = amperaje;
        this.gasRefrigerante = gasRefrigerante;
        this.capacidad = capacidad;
        this.tipo = tipo;
        this.costo = costo;
    }

    

    public String getPotencia() {
        return potencia;
    }

    public void setPotencia(String potencia) {
        this.potencia = potencia;
    }

  

    public String getFolio() {
        return folio;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getModelo() {
        return modelo;
    }

    public String getMarca() {
        return marca;
    }

    public String getCaudal() {
        return caudal;
    }

    public String getPresion() {
        return presion;
    }

    public String getVoltaje() {
        return voltaje;
    }

    public String getAmperaje() {
        return amperaje;
    }

    public String getGasRefrigerante() {
        return gasRefrigerante;
    }

    public String getCapacidad() {
        return capacidad;
    }

    public String getTipo() {
        return tipo;
    }
    
    public double getCosto(){
        return costo;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setCaudal(String caudal) {
        this.caudal = caudal;
    }

    public void setPresion(String presion) {
        this.presion = presion;
    }

    public void setVoltaje(String voltaje) {
        this.voltaje = voltaje;
    }

    public void setAmperaje(String amperaje) {
        this.amperaje = amperaje;
    }

    public void setGasRefrigerante(String gasRefrigerante) {
        this.gasRefrigerante = gasRefrigerante;
    }

    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public void setCosto(double costo){
        this.costo = costo;
    }
    
}
