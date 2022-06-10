package Catalogos;

public class Herramientas {

    private String codigo;
    private String nombreCorto;
    private String descripcion;
    private String clase;
    private double costo;

    public Herramientas(String codigo, String nombreCorto, String descripcion, String clase, double costo) {
        this.codigo = codigo;
        this.nombreCorto = nombreCorto;
        this.descripcion = descripcion;
        this.clase = clase;
        this.costo = costo;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreCorto() {
        return nombreCorto;
    }

    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

}
