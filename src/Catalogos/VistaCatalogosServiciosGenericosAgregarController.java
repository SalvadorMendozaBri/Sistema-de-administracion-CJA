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
 * @author Jorge Hernandez
 */
public class VistaCatalogosServiciosGenericosAgregarController implements Initializable {

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtCosto;
    @FXML
    private TextArea txtDescripcion;
    @FXML
    private Button botonGuardar;
    @FXML
    private Button botonCancelar;

    ObservableList listaServiciosGenericos;
    String motivoApertura;
    ServiciosGenericos ServicioGenericoAModificar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initAttributes(ObservableList listaServiciosGenericos) {
        motivoApertura = "agregar";
        this.listaServiciosGenericos = listaServiciosGenericos;
    }

    public void initAttributesModificar(ServiciosGenericos ServiciosGenericosAModificar) {
        motivoApertura = "modificar";
        this.ServicioGenericoAModificar = ServiciosGenericosAModificar;

        txtId.setText(ServiciosGenericosAModificar.getId());
        txtDescripcion.setText(ServiciosGenericosAModificar.getDescripcion());
        txtCosto.setText(Double.toString(ServiciosGenericosAModificar.getCosto()));

    }

    public void abrirVistaCatalogosServiciosGenericosAgregar(ObservableList listaServiciosGenericos) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Catalogos/VistaCatalogosServiciosGenericosAgregar.fxml"));
            Parent root = loader.load();
            VistaCatalogosServiciosGenericosAgregarController controlador = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Agregar Servicios Genéricos");
            stage.setOnCloseRequest((event) -> {
                event.consume();
                cancelarOnClose(stage);
            });
            System.out.println(listaServiciosGenericos);
            controlador.initAttributes(listaServiciosGenericos);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText(e.toString());
            alerta.setContentText("Error en el loader");
            alerta.showAndWait();
        }
    }

    public void abrirVistaCatalogosEmpresaModificar(ServiciosGenericos serviciosGenericosAModificar) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Catalogos/VistaCatalogosServiciosGenericosAgregar.fxml"));
            Parent root = loader.load();
            VistaCatalogosServiciosGenericosAgregarController controlador = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Modificar Servicios Genéricos");
            stage.setOnCloseRequest((event) -> {
                event.consume();
                cancelarOnClose(stage);
            });
            controlador.initAttributesModificar(serviciosGenericosAModificar);
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
                    String descripcion = txtDescripcion.getText();
                    String costo = txtCosto.getText();

                    listaServiciosGenericos.add(new ServiciosGenericos(id, descripcion, Double.parseDouble(costo)));

                    txtId.setText("");
                    txtDescripcion.setText("");
                    txtCosto.setText("");

                    Alert alertaGuardar = new Alert(Alert.AlertType.INFORMATION);
                    alertaGuardar.setTitle("Datos guardados");
                    alertaGuardar.setHeaderText(null);
                    alertaGuardar.setContentText("Los datos han sido guardardos correctamente");
                    alertaGuardar.showAndWait();
                    //Restablecer todos los datos.

                }
                break;
                case "modificar": {
                    ServicioGenericoAModificar.setId(txtId.getText());
                    ServicioGenericoAModificar.setDescripcion(txtDescripcion.getText());
                    ServicioGenericoAModificar.setCosto(Double.parseDouble(txtCosto.getText()));

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
