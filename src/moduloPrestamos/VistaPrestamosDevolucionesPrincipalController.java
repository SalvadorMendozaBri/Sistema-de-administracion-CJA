/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moduloPrestamos;

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
public class VistaPrestamosDevolucionesPrincipalController implements Initializable {

    @FXML
    private DatePicker campo_fecha;
    @FXML
    private Button botonSeleccionarHerramienta;
    @FXML
    private Button botonSEleccionarProveedor;
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
    
    public void abrirVentanaPrestamosDevolucionesPrincipal(){
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/moduloPrestamos/VistaPrestamosDevolucionesPrincipal.fxml"));
            Parent root = loader.load();
            VistaPrestamosDevolucionesPrincipalController controlador = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Devoluciones");
            stage.setScene(scene);
            stage.showAndWait();
            
        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText(e.toString());
            alerta.setContentText("No se ha encontrado el archivo");
            alerta.showAndWait();
        }
    }

    @FXML
    private void seleccionarHerramientas(ActionEvent event) {
    }

    @FXML
    private void seleccionarPersonal(ActionEvent event) {
    }

    @FXML
    private void guardar(ActionEvent event) {
    }

    @FXML
    private void cancelar(ActionEvent event) {
    }
    
}
