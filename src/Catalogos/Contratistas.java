package Catalogos;

/*
 Habia una vez un perro que se llamaba borrador y un día se rascó y se borró ajjajjsasj
 */

public class Contratistas {
    
    private String id;
    private String nombre;
    private String razonSocial;
    private String factura;
    private String descripcion;
    private String domicilio;
    private String telefono;
    private String correo;
    private String nombreRepresentantes;

    public Contratistas(String id, String nombre, String razonSocial, String factura, String descripcion, String domicilio, String telefono, String correo, String nombreRepresentantes) {
        this.id = id;
        this.nombre = nombre;
        this.razonSocial = razonSocial;
        this.factura = factura;
        this.descripcion = descripcion;
        this.domicilio = domicilio;
        this.telefono = telefono;
        this.correo = correo;
        this.nombreRepresentantes = nombreRepresentantes;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public String getFactura() {
        return factura;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getNombreRepresentantes() {
        return nombreRepresentantes;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setNombreRepresentantes(String nombreRepresentantes) {
        this.nombreRepresentantes = nombreRepresentantes;
    }
    
    
    
    
}
