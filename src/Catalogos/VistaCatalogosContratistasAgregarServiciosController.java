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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author chava
 */
public class VistaCatalogosContratistasAgregarServiciosController implements Initializable {

    @FXML
    private TextField txtID;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextArea txtDescripcion;
    @FXML
    private TextField txtCosto;
    @FXML
    private Button botonGuardar;
    @FXML
    private Button botonCancelar;

    ObservableList<ServiciosContratistas> listaServiciosContratistas;
    ServiciosContratistas servicioContratistaAModificar;
    String motivoApertura;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void initAttributes(ObservableList listaServiciosContratistas) {
        motivoApertura = "agregar";
        this.listaServiciosContratistas = listaServiciosContratistas;

    }

    public void initAttributesModificar(ServiciosContratistas servicioContratistaAModificar) {

        motivoApertura = "modificar";
        this.servicioContratistaAModificar = servicioContratistaAModificar;
        txtID.setText(servicioContratistaAModificar.getId());
        txtNombre.setText(servicioContratistaAModificar.getNombre());
        txtDescripcion.setText(servicioContratistaAModificar.getDescripcion());
        txtCosto.setText(Double.toString(servicioContratistaAModificar.getCosto()));

    }

    public void abrirVistaCatalogosContratistasAgregarServicios(ObservableList listaServiciosContratistas) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Catalogos/VistaCatalogosContratistasAgregarServicios.fxml"));
            Parent root = loader.load();
            VistaCatalogosContratistasAgregarServiciosController controlador = loader.getController();
            controlador.initAttributes(listaServiciosContratistas);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setOnCloseRequest((event) -> {
                event.consume();
                cancelarOnClose(stage);
            });
            stage.setScene(scene);
            stage.setTitle("Agregar servicio de contratista");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERROR");
            alerta.setHeaderText("Error al cargar el archivo");
            alerta.setContentText(e.toString());
            alerta.showAndWait();
        }

    }

    public void abrirVistaCatalogosContratistasAgregarServiciosModificar(ServiciosContratistas servicioContratistaAModificar) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Catalogos/VistaCatalogosContratistasAgregarServicios.fxml"));
            Parent root = loader.load();
            VistaCatalogosContratistasAgregarServiciosController controlador = loader.getController();
            controlador.initAttributesModificar(servicioContratistaAModificar);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setOnCloseRequest((event) -> {
                event.consume();
                cancelarOnClose(stage);
            });
            stage.setScene(scene);
            stage.setTitle("Agregar servicio de contratista");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERROR");
            alerta.setHeaderText("Error al cargar el archivo");
            alerta.setContentText(e.toString());
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
                    String id = txtID.getText();
                    String nombre = txtNombre.getText();
                    String descripcion = txtDescripcion.getText();
                    String costo = txtCosto.getText();

                    listaServiciosContratistas.add(new ServiciosContratistas(id, nombre, descripcion, Double.parseDouble(costo)));

                    txtID.setText("");
                    txtNombre.setText("");
                    txtDescripcion.setText("");
                    txtCosto.setText("");

                    Alert alertaGuardar = new Alert(Alert.AlertType.INFORMATION);
                    alertaGuardar.setTitle("Datos guardados");
                    alertaGuardar.setHeaderText(null);
                    alertaGuardar.setContentText("Los datos han sido guardardos correctamente");
                    alertaGuardar.showAndWait();

                }

                break;
                case "modificar": {
                    servicioContratistaAModificar.setId(txtID.getText());
                    servicioContratistaAModificar.setNombre(txtNombre.getText());
                    servicioContratistaAModificar.setDescripcion(txtDescripcion.getText());
                    servicioContratistaAModificar.setCosto(Double.parseDouble(txtCosto.getText()));

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
