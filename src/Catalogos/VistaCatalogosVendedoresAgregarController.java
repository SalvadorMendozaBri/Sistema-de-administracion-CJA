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
public class VistaCatalogosVendedoresAgregarController implements Initializable {

    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtDivisionAsignada;
    @FXML
    private TextField txtAlias;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtCorreo;
    @FXML
    private Button botonGuardar;
    @FXML
    private Button botonCancelar;

    ObservableList listaVendedores;
    String motivoApertura;
    Vendedores vendedorAModificar;
    @FXML
    private Button seleccionarDivisionAsignada;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initAttributes(ObservableList listaVendedores) {
        motivoApertura = "agregar";
        this.listaVendedores = listaVendedores;
    }

    public void initAttributesModificar(Vendedores vendedorAModificar) {
        motivoApertura = "modificar";
        this.vendedorAModificar = vendedorAModificar;

        txtCodigo.setText(vendedorAModificar.getCodigo());
        txtNombre.setText(vendedorAModificar.getNombre());
        txtAlias.setText(vendedorAModificar.getAlias());
        txtDivisionAsignada.setText(vendedorAModificar.getDivision());
        txtTelefono.setText(vendedorAModificar.getTelefono());
        txtCorreo.setText(vendedorAModificar.getCorreo());

    }

    public void abrirVistaCatalogosVendedoresAgregar(ObservableList listaVendedores) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Catalogos/VistaCatalogosVendedoresAgregar.fxml"));
            Parent root = loader.load();
            VistaCatalogosVendedoresAgregarController controlador = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Agregar vendedor");
            stage.setOnCloseRequest((event) -> {
                event.consume();
                cancelarOnClose(stage);
            });
            controlador.initAttributes(listaVendedores);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText(e.toString());
            alerta.setContentText("Error en el loader");
            alerta.showAndWait();
        }
    }

    public void abrirVistaCatalogosVendedoresModificar(Vendedores vendedorAModificar) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Catalogos/VistaCatalogosVendedoresAgregar.fxml"));
            Parent root = loader.load();
            VistaCatalogosVendedoresAgregarController controlador = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Agregar vendedor");
            stage.setOnCloseRequest((event) -> {
                event.consume();
                cancelarOnClose(stage);
            });
            controlador.initAttributesModificar(vendedorAModificar);
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
                    String codigo = txtCodigo.getText();
                    String nombre = txtNombre.getText();
                    String alias = txtAlias.getText();
                    String division = txtDivisionAsignada.getText();
                    String telefono = txtTelefono.getText();
                    String correo = txtCorreo.getText();

                    listaVendedores.add(new Vendedores(codigo, nombre, alias, division, telefono, correo));

                    txtCodigo.setText("");
                    txtNombre.setText("");
                    txtAlias.setText("");
                    txtDivisionAsignada.setText("");
                    txtTelefono.setText("");
                    txtCorreo.setText("");

                    Alert alertaGuardar = new Alert(Alert.AlertType.INFORMATION);
                    alertaGuardar.setTitle("Datos guardados");
                    alertaGuardar.setHeaderText(null);
                    alertaGuardar.setContentText("Los datos han sido guardardos correctamente");
                    alertaGuardar.showAndWait();
                    //Restablecer todos los datos. 
                    
                    
                }
                break;
                case "modificar": {
                    vendedorAModificar.setCodigo(txtCodigo.getText());
                    vendedorAModificar.setNombre(txtNombre.getText());
                    vendedorAModificar.setAlias(txtAlias.getText());
                    vendedorAModificar.setDivision(txtDivisionAsignada.getText());
                    vendedorAModificar.setTelefono(txtTelefono.getText());
                    vendedorAModificar.setCorreo(txtCorreo.getText());

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
    private void seleccionarDivisionAsignada(ActionEvent event) {
        VistaCatalogoSimplesController controlador = new VistaCatalogoSimplesController();
        controlador.abrirVistaCatalogoSimples("divisionAsignada");
        txtDivisionAsignada.setText(controlador.getDatosARetornar());
    }

}
