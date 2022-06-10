/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moduloPrestamos;

import Catalogos.VistaCatalogosController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author chava
 */
public class VistaNuevoPrestamoController implements Initializable {

    @FXML
    private DatePicker campo_fecha;
    @FXML
    private Button botonSEleccionarProveedor;
    @FXML
    private Button botonGuardar;
    @FXML
    private Button botonCancelar;
    @FXML
    private Button botonSeleccionarHerramienta;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void abrirVentanaNuevoPrestamo() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/moduloPrestamos/VistaNuevoPrestamo.fxml"));
            Parent root = loader.load();
            VistaNuevoPrestamoController controlador = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setTitle("Agregar entradas");
            stage.setOnCloseRequest(event -> {              
                event.consume();
                cancelarOnClose(stage);
            });
            stage.showAndWait();

        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText(e.toString());
            alerta.setContentText("No se ha encontrado el archivo");
            alerta.show();
        }
    }

    @FXML
    private void seleccionarPersonal(ActionEvent event) {
        new VistaCatalogosController().abrirCatalogosPrincipal("Proveedores");
    }

    @FXML
    private void seleccionarHerramientas(ActionEvent event) {
         new VistaCatalogosController().abrirCatalogosPrincipal("Proveedores");
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
            Alert alertaGuardar = new Alert(Alert.AlertType.INFORMATION);
            alertaGuardar.setTitle("Datos guardados");
            alertaGuardar.setHeaderText(null);
            alertaGuardar.setContentText("Los datos han sido guardardos correctamente");
            alertaGuardar.showAndWait();
            //Restablecer todos los datos. 
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
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
