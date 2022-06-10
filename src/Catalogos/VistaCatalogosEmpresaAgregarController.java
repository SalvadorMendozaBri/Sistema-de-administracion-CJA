/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Catalogos;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author chava
 */
public class VistaCatalogosEmpresaAgregarController implements Initializable {

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtTelefono;
    @FXML
    private Button botonGuardar;
    @FXML
    private Button botonCancelar;
    @FXML
    private TextField txtRazonSocial;
    @FXML
    private TextField txtPais;
    @FXML
    private TextField txtCalle;
    @FXML
    private TextField txtNumero;
    @FXML
    private TextField txtCP;
    @FXML
    private TextField txtMunicipio;
    @FXML
    private TextField txtEstado;

    ObservableList listaEmpresas;
    String motivoApertura;
    Empresas empresaAModificar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initAttributes(ObservableList listaEmpresas) {
        motivoApertura = "agregar";
        this.listaEmpresas = listaEmpresas;
    }

    public void initAttributesModificar(Empresas empresaAModificar) {
        motivoApertura = "modificar";
        this.empresaAModificar = empresaAModificar;

        txtId.setText(empresaAModificar.getId());
        txtNombre.setText(empresaAModificar.getNombre());
        txtRazonSocial.setText(empresaAModificar.getRazonSocial());
        txtCalle.setText(empresaAModificar.getCalle());
        txtNumero.setText(empresaAModificar.getNumero());
        txtCP.setText(empresaAModificar.getCP());
        txtMunicipio.setText(empresaAModificar.getMunicipio());
        txtEstado.setText(empresaAModificar.getEstado());
        txtPais.setText(empresaAModificar.getPais());
        txtTelefono.setText(empresaAModificar.getTelefono());
        
    }

    public void abrirVistaCatalogosEmpresaAgregar(ObservableList listaEmpresas) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Catalogos/VistaCatalogosEmpresaAgregar.fxml"));
            Parent root = loader.load();
            VistaCatalogosEmpresaAgregarController controlador = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Agregar empresa");
            stage.setOnCloseRequest((event) -> {
                event.consume();
                cancelarOnClose(stage);
            });
            System.out.println(listaEmpresas);
            controlador.initAttributes(listaEmpresas);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText(e.toString());
            alerta.setContentText("Error en el loader");
            alerta.showAndWait();
        }
    }

    public void abrirVistaCatalogosEmpresaModificar(Empresas empresaAModificar) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Catalogos/VistaCatalogosEmpresaAgregar.fxml"));
            Parent root = loader.load();
            VistaCatalogosEmpresaAgregarController controlador = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Modificar empresa");
            stage.setOnCloseRequest((event) -> {
                event.consume();
                cancelarOnClose(stage);
            });
            controlador.initAttributesModificar(empresaAModificar);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText(e.toString());
            alerta.setContentText("Error en el loader");
            alerta.showAndWait();
        }
    }

    @FXML
    public void cancelar(ActionEvent event) {

        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Confirmación");
        alerta.setHeaderText(null);
        alerta.setContentText("¿Seguro que quiere salir?");
        alerta.showAndWait();

        String opcion = alerta.getResult().toString();
        if (opcion.contains("Aceptar")) {
            Stage stage = (Stage) botonCancelar.getScene().getWindow();
            stage.close();
        }
    }

    public void cancelarOnClose(Stage stage) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Confirmación");
        alerta.setHeaderText(null);
        alerta.setContentText("¿Seguro que quiere salir?");
        alerta.showAndWait();

        String opcion = alerta.getResult().toString();
        if (opcion.contains("Aceptar")) {
            stage.close();
        }
    }

    @FXML
    private void guardar(ActionEvent event) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Confirmación");
        alerta.setHeaderText(null);
        alerta.setContentText("¿Desea guardar los cambios?");
        alerta.showAndWait();

        String opcion = alerta.getResult().toString();
        if (opcion.contains("Aceptar")) {
            switch (motivoApertura) {
                case "agregar": {
                    String id = txtId.getText();
                    String nombre = txtNombre.getText();
                    String razonSocial = txtRazonSocial.getText();
                    String calle = txtCalle.getText();
                    String numero = txtNumero.getText();
                    String CP = txtCP.getText();
                    String municipio = txtMunicipio.getText();
                    String estado = txtEstado.getText();
                    String pais = txtPais.getText();
                    String telefono = txtTelefono.getText();

                    listaEmpresas.add(new Empresas(id, nombre, razonSocial, calle, numero, CP, municipio, estado, pais, telefono));
                    
                    txtId.setText("");
                    txtNombre.setText("");
                    txtRazonSocial.setText("");
                    txtCalle.setText("");
                    txtNumero.setText("");
                    txtCP.setText("");
                    txtMunicipio.setText("");
                    txtEstado.setText("");
                    txtPais.setText("");
                    txtTelefono.setText("");

                    Alert alertaGuardar = new Alert(Alert.AlertType.INFORMATION);
                    alertaGuardar.setTitle("Datos guardados");
                    alertaGuardar.setHeaderText(null);
                    alertaGuardar.setContentText("Los datos han sido guardardos correctamente");
                    alertaGuardar.showAndWait();
                    //Restablecer todos los datos.

                }
                break;
                case "modificar": {
                    empresaAModificar.setId(txtId.getText());
                    empresaAModificar.setNombre(txtNombre.getText());
                    empresaAModificar.setRazonSocial(txtRazonSocial.getText());                   
                    empresaAModificar.setNumero(txtNumero.getText());           
                    empresaAModificar.setCP(txtCP.getText());
                    empresaAModificar.setMunicipio(txtMunicipio.getText());
                    empresaAModificar.setEstado(txtEstado.getText());
                    empresaAModificar.setPais(txtPais.getText());
                    empresaAModificar.setTelefono(txtTelefono.getText());

                    Alert alertaGuardar2 = new Alert(Alert.AlertType.INFORMATION);
                    alertaGuardar2.setTitle("Datos guardados");
                    alertaGuardar2.setHeaderText(null);
                    alertaGuardar2.setContentText("Los datos han sido guardardos correctamente");
                    alertaGuardar2.showAndWait();

                    Stage stage = (Stage) botonGuardar.getScene().getWindow();
                    stage.close();
                }
                break;
                default:
                    throw new AssertionError();
            }

        }

    }

}
