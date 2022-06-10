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
public class VistaCatalogosPersonalAgregarController implements Initializable {

    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtPuesto;
    @FXML
    private Button botonGuardar;
    @FXML
    private Button botonCancelar;
    @FXML
    private TextField txtCosto;

    ObservableList listaPersonal;
    String motivoApertura;
    Personal personalAModificar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initAttributes(ObservableList listaPersonal) {
        motivoApertura = "agregar";
        this.listaPersonal = listaPersonal;
    }

    public void initAttributesModificar(Personal personalAModificar) {
        motivoApertura = "modificar";
        this.personalAModificar = personalAModificar;

        txtCodigo.setText(personalAModificar.getCodigo());
        txtNombre.setText(personalAModificar.getNombre());
        txtPuesto.setText(personalAModificar.getPuesto());
        txtCosto.setText(Double.toString(personalAModificar.getCostoPorDia()));

    }

    public void abrirVistaCatalogosPersonalAgregar(ObservableList listaPersonal) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Catalogos/VistaCatalogosPersonalAgregar.fxml"));
            Parent root = loader.load();
            VistaCatalogosPersonalAgregarController controlador = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Agregar personal");
            stage.setOnCloseRequest((event) -> {
                event.consume();
                cancelarOnClose(stage);
            });

            controlador.initAttributes(listaPersonal);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText(e.toString());
            alerta.setContentText("Error en el loader");
            alerta.showAndWait();
        }
    }

    public void abrirVistaCatalogosPersonalModificar(Personal personalAModificar) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Catalogos/VistaCatalogosPersonalAgregar.fxml"));
            Parent root = loader.load();
            VistaCatalogosPersonalAgregarController controlador = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Agregar personal");
            stage.setOnCloseRequest((event) -> {
                event.consume();
                cancelarOnClose(stage);
            });

            controlador.initAttributesModificar(personalAModificar);
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
        try {
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
                        String puesto = txtPuesto.getText();
                        double costo = Double.parseDouble(txtCosto.getText());

                        listaPersonal.add(new Personal(codigo, nombre, puesto, costo));
                        txtCodigo.setText("");
                        txtNombre.setText("");
                        txtPuesto.setText("");
                        txtCosto.setText("");

                        Alert alertaGuardar = new Alert(Alert.AlertType.INFORMATION);
                        alertaGuardar.setTitle("Datos guardados");
                        alertaGuardar.setHeaderText(null);
                        alertaGuardar.setContentText("Los datos han sido guardardos correctamente");
                        alertaGuardar.showAndWait();
                    }

                    break;
                    case "modificar": {
                        personalAModificar.setCodigo(txtCodigo.getText());
                        personalAModificar.setNombre(txtNombre.getText());
                        personalAModificar.setPuesto(txtPuesto.getText());
                        personalAModificar.setCostoPorDia(Double.parseDouble(txtCosto.getText()));

                        Alert alertaGuardar = new Alert(Alert.AlertType.INFORMATION);
                        alertaGuardar.setTitle("Datos guardados");
                        alertaGuardar.setHeaderText(null);
                        alertaGuardar.setContentText("Los datos han sido guardardos correctamente");
                        alertaGuardar.showAndWait();

                        Stage stage = (Stage) botonGuardar.getScene().getWindow();
                        stage.close();

                    }
                    break;

                }

                //Restablecer todos los datos. 
            }

        } catch (NumberFormatException e) {

            Alert alertaGuardar = new Alert(Alert.AlertType.ERROR);
            alertaGuardar.setTitle("Formato de costo incorrecto");
            alertaGuardar.setHeaderText(null);
            alertaGuardar.setContentText("El costo debe tener un valor numerico");
            alertaGuardar.showAndWait();

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

}
