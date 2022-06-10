package moduloSalidas;

import java.sql.Date;


public class Salidas {
    private String folio;
    private String productos;
    private Date fecha;
    private String entrego;
    private String referencia;
    private String observaciones;

    public Salidas(String folio, String productos, Date fecha, String entrego, String referencia, String observaciones) {
        this.folio = folio;
        this.productos = productos;
        this.fecha = fecha;
        this.entrego = entrego;
        this.referencia = referencia;
        this.observaciones = observaciones;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getProductos() {
        return productos;
    }

    public void setProductos(String productos) {
        this.productos = productos;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEntrego() {
        return entrego;
    }

    public void setEntrego(String entrego) {
        this.entrego = entrego;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

}