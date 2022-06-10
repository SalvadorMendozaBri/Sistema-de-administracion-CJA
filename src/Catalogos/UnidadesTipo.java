/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Catalogos;

/**id entero
 * datoString
 * tipo String
 * 
 * @author Jorge Hernandez
 */
public class UnidadesTipo {
    private int id;
    private String datos;
    private String tipo;

    public UnidadesTipo(int id, String datos, String tipo) {
        this.id = id;
        this.datos = datos;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDatos() {
        return datos;
    }

    public void setDatos(String datos) {
        this.datos = datos;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
}
