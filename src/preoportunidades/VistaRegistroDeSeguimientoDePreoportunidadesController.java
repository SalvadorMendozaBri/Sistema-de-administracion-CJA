/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package preoportunidades;

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
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author chava
 */
public class VistaRegistroDeSeguimientoDePreoportunidadesController implements Initializable {

    @FXML
    private TextField txtFolio;
    @FXML
    private DatePicker dateFechaRegistro;
    @FXML
    private TextField txtUsuario;
    @FXML
    private DatePicker dateFechaProximo;
    @FXML
    private TextArea txtObservaciones;
    @FXML
    private TextArea txtArchivosAdjuntos;
    @FXML
    private Button botonAñadir;
    @FXML
    private Button botonEliminar;
    @FXML
    private Button botonArchivosAdjuntos;
    @FXML
    private CheckBox checkProblema;
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

    public void abrirRegistroDeSeguimientoDePreoportunidades() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/preoportunidades/VistaRegistroDeSeguimientoDePreoportunidades.fxml"));
            Parent root = loader.load();
            VistaRegistroDeSeguimientoDePreoportunidadesController controlador = loader.getController();
            controlador.initAttributes();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setOnCloseRequest(e->{
                e.consume();
                cancelarOnClose(stage);
            });
            
            stage.setScene(scene);
            stage.setTitle("Registro de seguimiento de preoportunidad");
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
    private void abrirArchivosAdjuntos(ActionEvent event) {
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
