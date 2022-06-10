package Catalogos;

public class Vendedores {

    private String codigo;
    private String nombre;
    private String alias;
    private String division;
    private String telefono;
    private String correo;

    public Vendedores(String codigo, String nombre, String alias, String division, String telefono, String correo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.alias = alias;
        this.division = division;
        this.telefono = telefono;
        this.correo = correo;
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

    public String getAlias() {
        return alias;
    }

    public String getDivision() {
        return division;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

}
