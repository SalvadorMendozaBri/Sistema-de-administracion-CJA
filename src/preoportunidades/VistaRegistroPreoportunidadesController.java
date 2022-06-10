/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package preoportunidades;

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
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import prospectos.VistaListadoDeProspectosController;

/**
 * FXML Controller class
 *
 * @author chava
 */
public class VistaRegistroPreoportunidadesController implements Initializable {

    @FXML
    private TextField txtFolio;
    @FXML
    private DatePicker dateFechaRegistro;
    @FXML
    private TextField txtUsuario;
    @FXML
    private TextField txtVendedor;
    @FXML
    private DatePicker dateFechaRegistro1;
    @FXML
    private Button botonCancelar;
    @FXML
    private Button botonGuardar;
    @FXML
    private TextField txtProspecto;
    @FXML
    private Button botonSeleccionarProspecto;
    @FXML
    private TextField txtTitulo;
    @FXML
    private TextArea txtAreaDescripcion;
    @FXML
    private ListView<?> listArchivos;
    @FXML
    private Button botonAdjuntarArchivo;
    @FXML
    private Button botonEliminar;
    @FXML
    private Button botonSeleccionarVendedor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initAttributes() {

    }

    public void abrirVistaRegistroPreoportunidades() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/preoportunidades/VistaRegistroPreoportunidades.fxml"));
            Parent root = loader.load();
            VistaRegistroPreoportunidadesController controlador = loader.getController();
            controlador.initAttributes();
            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setOnCloseRequest(e -> {
                e.consume();
                cancelarOnClose(stage);
            });

            stage.setScene(scene);
            stage.setTitle("Registro de preoportunidades");
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

    @FXML
    private void seleccionarProspecto(ActionEvent event) {
        VistaListadoDeProspectosController controlador = new VistaListadoDeProspectosController();
        controlador.abrirVistaListadoDeProspectos("Preoportunidades");
        String array[] = controlador.getDatosARetornar().split(",");
        txtProspecto.setText(array[1]);

    }

    @FXML
    private void seleccionarProspectoKeyTyped(KeyEvent event) {

        if (event.getCode().toString().equalsIgnoreCase("ENTER")) {
            VistaListadoDeProspectosController controlador = new VistaListadoDeProspectosController();
            controlador.abrirVistaListadoDeProspectos("Preoportunidades");
            String array[] = controlador.getDatosARetornar().split(",");
            txtProspecto.setText(array[1]);
        }

    }

    @FXML
    private void seleccionarVendedorKeyTyped(KeyEvent event) {

        if (event.getCode().toString().equalsIgnoreCase("ENTER")) {
            VistaCatalogosController controlador = new VistaCatalogosController();
            controlador.abrirCatalogosPrincipal("Vendedores");
            String arrayAux[] = controlador.getDatosARetornar().split(",");
            txtVendedor.setText(arrayAux[1]);
        }

    }

    @FXML
    private void seleccionarVendedor(ActionEvent event) {
        VistaCatalogosController controlador = new VistaCatalogosController();
        controlador.abrirCatalogosPrincipal("Vendedores");
        String arrayAux[] = controlador.getDatosARetornar().split(",");
        txtVendedor.setText(arrayAux[1]);

        //La posicion 0 del array se guardara en una variable global para su conexion con la base de datos.
    }
   

    @FXML
    private void adjuntarArchivo(ActionEvent event) {
    }

    @FXML
    private void eliminarArchivo(ActionEvent event) {
    }

}
