package Catalogos;

//Tengo sueño hoy no hay easter egg

public class Vehiculos {
    
    private String id;
    private String tipo;
    private String sucursal;
    private String gastoGasolina;

    public Vehiculos(String id, String tipo, String sucursal, String gastoGasolina) {
        this.id = id;
        this.tipo = tipo;
        this.sucursal = sucursal;
        this.gastoGasolina = gastoGasolina;
    }

    public String getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getSucursal() {
        return sucursal;
    }

    public String getGastoGasolina() {
        return gastoGasolina;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public void setGastoGasolina(String gastoGasolina) {
        this.gastoGasolina = gastoGasolina;
    }
    
    
}
