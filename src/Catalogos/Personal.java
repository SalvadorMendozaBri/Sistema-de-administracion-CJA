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
public class Personal {

    private String codigo;
    private String nombre;
    private String puesto;
    private double costoPorDia;

    public Personal(String codigo, String nombre, String puesto, double costoPorDia) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.puesto = puesto;
        this.costoPorDia = costoPorDia;
    }

    public double getCostoPorDia() {
        return costoPorDia;
    }

    public void setCostoPorDia(double costoPorDia) {
        this.costoPorDia = costoPorDia;
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

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

}
