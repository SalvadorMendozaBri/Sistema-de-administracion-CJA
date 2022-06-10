package moduloInventarios;

public class HerramientasInventario {
    private int codigo_hrtas;
    private String nombreCorto_hrtas;
    private String descripcion_hrtas;
    private String unidad_hrtas;
    private double costo_hrtas;
    private String clase_hrtas;
    private String subClase1_hrtas;
    private String subClase2_hrtas;
    private double existencia_hrtas;
    
     public HerramientasInventario(int id, String nombreCorto, String descripcion, String unidad, double costo, String clase, String subClase1, String subClase2, double existencia) {
        this.codigo_hrtas = id;
        this.nombreCorto_hrtas = nombreCorto;
        this.descripcion_hrtas = descripcion;
        this.unidad_hrtas = unidad;
        this.costo_hrtas = costo;
        this.clase_hrtas = clase;
        this.subClase1_hrtas = subClase1;
        this.subClase2_hrtas = subClase2;
        this.existencia_hrtas = existencia;
    }

    public int getCodigo_hrtas() {
        return codigo_hrtas;
    }

    public String getNombreCorto_hrtas() {
        return nombreCorto_hrtas;
    }

    public String getDescripcion_hrtas() {
        return descripcion_hrtas;
    }

    public String getUnidad_hrtas() {
        return unidad_hrtas;
    }

    public double getCosto_hrtas() {
        return costo_hrtas;
    }

    public String getClase_hrtas() {
        return clase_hrtas;
    }

    public String getSubClase1_hrtas() {
        return subClase1_hrtas;
    }

    public String getSubClase2_hrtas() {
        return subClase2_hrtas;
    }

    public double getExistencia_hrtas() {
        return existencia_hrtas;
    }

    public void setCodigo_hrtas(int codigo_hrtas) {
        this.codigo_hrtas = codigo_hrtas;
    }

    public void setNombreCorto_hrtas(String nombreCorto_hrtas) {
        this.nombreCorto_hrtas = nombreCorto_hrtas;
    }

    public void setDescripcion_hrtas(String descripcion_hrtas) {
        this.descripcion_hrtas = descripcion_hrtas;
    }

    public void setUnidad_hrtas(String unidad_hrtas) {
        this.unidad_hrtas = unidad_hrtas;
    }

    public void setCosto(float costo) {
        this.costo_hrtas = costo;
    }

    public void setClase_hrtas(String clase_hrtas) {
        this.clase_hrtas = clase_hrtas;
    }

    public void setSubClase1_hrtas(String subClase1_hrtas) {
        this.subClase1_hrtas = subClase1_hrtas;
    }

    public void setSubClase2_hrtas(String subClase2_hrtas) {
        this.subClase2_hrtas = subClase2_hrtas;
    }

    public void setExistencia_hrtas(double existencia_hrtas) {
        this.existencia_hrtas = existencia_hrtas;
    }

   
    
    
}
