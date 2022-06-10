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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author chava
 */
public class VistaPreoportunidadesPendientesController implements Initializable {

    @FXML
    private Button botonSeguimiento;
    @FXML
    private Button botonSalir;
    @FXML
    private TextField txtBuscar;
    @FXML
    private TableView tablaPreoportunidadesPendientes;
    @FXML
    private TableColumn columnaFolio;
    @FXML
    private TableColumn columnaVendedor;
    @FXML
    private TableColumn columnaFechaProximo;
    @FXML
    private TableColumn columnaStatus;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initAttributes() {

    }

    public void abrirVistaPreoportunidadesPendientes() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/preoportunidades/VistaPreoportunidadesPendientes.fxml"));
            Parent root = loader.load();
            VistaPreoportunidadesPendientesController controlador = loader.getController();
            controlador.initAttributes();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Preoportunidades pendientes");
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
    private void seguimiento(ActionEvent event) {
        new VistaConsultaYSeguimientoPreoportunidadesController().abrirVistaConsultaYSeguimientoPreoportunidades();
    }

    @FXML
    private void salir(ActionEvent event) {
        Stage stage = (Stage) botonSalir.getScene().getWindow();
        stage.close();
    }

}
