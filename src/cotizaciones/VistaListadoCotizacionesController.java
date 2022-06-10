/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotizaciones;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import prospectos.VistaListadoDeProspectosController;

/**
 * FXML Controller class
 *
 * @author chava
 */
public class VistaListadoCotizacionesController implements Initializable {

    @FXML
    private HBox hBox;
    @FXML
    private TextField txtBuscar;
    @FXML
    private ComboBox comboOculto;
    @FXML
    private ImageView icono;
    @FXML
    private TableView tablaProspectos;
    @FXML
    private TableColumn columnaFolio;
    @FXML
    private TableColumn columnaDescripcion;
    @FXML
    private TableColumn columnVendedor;
    @FXML
    private TableColumn columnaMonto;
    @FXML
    private TableColumn columnaStatus;
    @FXML
    private TableColumn columnaFecha;
    @FXML
    private Button botonAgregar;
    @FXML
    private Button botonModificar;
    @FXML
    private Button botonEliminar;
    @FXML
    private Button botonSalir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initAttributes() {
        Image imagen = new Image("/iconos/logoCotizaciones.png");
        icono.setImage(imagen);
        
        BackgroundPosition b = new BackgroundPosition(Side.RIGHT, 0, true, Side.BOTTOM, 0, true);
        BackgroundImage bi = new BackgroundImage(new Image("FondoVerde.png", 1894, 1420, false, true, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, b, BackgroundSize.DEFAULT);
        hBox.setBackground(new Background(bi));
    }

    public void abrirVistaListadoDeCotizaciones() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/cotizaciones/VistaListadoCotizaciones.fxml"));
            Parent root = loader.load();
            VistaListadoCotizacionesController controlador = loader.getController();
            controlador.initAttributes();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Listado de cotizaciones");
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
    private void seleccionarMouseClicked(MouseEvent event) {
    }

    @FXML
    private void seleccionarEnter(KeyEvent event) {
    }

    @FXML
    private void agregar(ActionEvent event) {
        new VistaCotizacionesAgregarController().abrirVistaCotizacionesAgregar();
    }

    @FXML
    private void modificar(ActionEvent event) {
    }

    @FXML
    private void eliminar(ActionEvent event) {
    }

    @FXML
    private void salir(ActionEvent event) {
    }

}
