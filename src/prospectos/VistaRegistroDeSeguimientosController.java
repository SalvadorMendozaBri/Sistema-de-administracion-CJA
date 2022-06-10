/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prospectos;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author chava
 */
public class VistaRegistroDeSeguimientosController implements Initializable {

    @FXML
    private TextField txtFolio;
    @FXML
    private DatePicker dateFecha;
    @FXML
    private TextField txtUsuario;
    @FXML
    private TextField txtDatosProspectos;
    @FXML
    private Button btnDatosProspecto;
    @FXML
    private DatePicker dateProximoSeguimiento;
    @FXML
    private TextArea txtObservaciones;
    @FXML
    private Button botonAgregarOportunidad;
    @FXML
    private Button botonGuardar;
    @FXML
    private Button botonCancelar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initAttributes() {

    }

    public void abrirVistaRegistroDeSeguimientos() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/prospectos/VistaRegistroDeSeguimientos.fxml"));
            Parent root = loader.load();
            VistaRegistroDeSeguimientosController controlador = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setOnCloseRequest(event -> {
                event.consume();
                cancelarOnClose(stage);
            });
            stage.setScene(scene);
            stage.setTitle("Registro de seguimiento");
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
    private void agregarOportunidad(ActionEvent event) {
    }
    
    private void cancelarOnClose(Stage stage) {
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
}
