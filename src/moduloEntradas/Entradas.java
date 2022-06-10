package moduloEntradas;

import java.sql.Date;

public class Entradas {

    private String folio;
    private String productos;
    private Date fecha;
    private String proveedor;
    private String recibio;
    private String referencia;
    private String observaciones;

    public Entradas(String folio, String productos, Date fecha, String proveedor, String recibio, String referencia, String observaciones) {
        this.folio = folio;
        this.productos = productos;
        this.fecha = fecha;
        this.proveedor = proveedor;
        this.recibio = recibio;
        this.referencia = referencia;
        this.observaciones = observaciones;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getFolio() {
        return folio;
    }

    public String getProductos() {
        return productos;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getProveedor() {
        return proveedor;
    }

    public String getRecibio() {
        return recibio;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public void setProductos(String productos) {
        this.productos = productos;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public void setRecibio(String recibio) {
        this.recibio = recibio;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

}
