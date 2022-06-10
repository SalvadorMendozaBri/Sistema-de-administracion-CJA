package moduloInventarios;

public class ProductosInventario {

    private String id;
    private String nombreCorto;
    private String descripcion;
    private String unidad;
    private double costo;
    private String clase;
    private String subclase1;
    private String subclase2;
    private double existencia;

    public ProductosInventario(String id, String nombreCorto, String descripcion, String unidad, double costo, String clase, String subclase1, String subclase2, double existencia) {
        this.id = id;
        this.nombreCorto = nombreCorto;
        this.descripcion = descripcion;
        this.unidad = unidad;
        this.costo = costo;
        this.clase = clase;
        this.subclase1 = subclase1;
        this.subclase2 = subclase2;
        this.existencia = existencia;
    }

    public ProductosInventario(String id, String nombreCorto, double costo, double existencia) {
        this.id = id;
        this.nombreCorto = nombreCorto;
        this.costo = costo;
        this.existencia = existencia;
    }

    public ProductosInventario(String id, String nombreCorto, String descripcion, double costo, double existencia) {
        this.id = id;
        this.nombreCorto = nombreCorto;
        this.descripcion = descripcion;
        this.costo = costo;
        this.existencia = existencia;
    }

    public String getId() {
        return id;
    }

    public String getNombreCorto() {
        return nombreCorto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getUnidad() {
        return unidad;
    }

    public double getCosto() {
        return costo;
    }

    public String getClase() {
        return clase;
    }

    public String getSubclase1() {
        return subclase1;
    }

    public String getSubclase2() {
        return subclase2;
    }

    public double getExistencia() {
        return existencia;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public void setSubclase1(String subclase1) {
        this.subclase1 = subclase1;
    }

    public void setSubclase2(String subclase2) {
        this.subclase2 = subclase2;
    }

    public void setExistencia(double existencia) {
        this.existencia = existencia;
    }

}
