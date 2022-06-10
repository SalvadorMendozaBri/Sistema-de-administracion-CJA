/*
 *¡¡¡Ayuda estoy atrapado en el sotano de mireya!!!
 */
package Catalogos;

public class Empresas {

    private String id;
    private String nombre;
    private String razonSocial;
    private String calle;
    private String numero;
    private String CP;
    private String municipio;
    private String estado;
    private String pais;
    private String telefono;

    public Empresas(String id, String nombre, String razonSocial, String calle, String numero, String CP, String municipio, String estado, String pais, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.razonSocial = razonSocial;
        this.calle = calle;
        this.numero = numero;
        this.CP = CP;
        this.municipio = municipio;
        this.estado = estado;
        this.pais = pais;
        this.telefono = telefono;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCP() {
        return CP;
    }

    public void setCP(String CP) {
        this.CP = CP;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
