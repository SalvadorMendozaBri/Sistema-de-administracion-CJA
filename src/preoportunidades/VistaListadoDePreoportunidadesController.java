/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package preoportunidades;

import com.sun.deploy.uitoolkit.impl.fx.ui.FXConsole;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
import javax.swing.text.TabableView;
import prospectos.VistaConsultaProspectosYSeguimientosController;
import prospectos.VistaListadoDeProspectosController;

/**
 * FXML Controller class
 *
 * @author chava
 */
public class VistaListadoDePreoportunidadesController implements Initializable {

    @FXML
    private HBox hBox;
    @FXML
    private TextField txtBuscar;
    @FXML
    private ImageView icono;
    @FXML
    private TableView tablaPreoportunidades;
    @FXML
    private TableColumn columnaFolio;
    @FXML
    private Button botonAgregar;
    @FXML
    private Button botonModificar;
    @FXML
    private Button botonEliminar;
    @FXML
    private Button botonSeguimiento;
    @FXML
    private Button botonCotizar;
    @FXML
    private Button botonSalir;
    @FXML
    private Button botonPendientes;
    @FXML
    private TableColumn columnaFechaRegistro;
    @FXML
    private TableColumn columnaFechaCompromiso;
    @FXML
    private TableColumn <?,CheckBox> columnaVerificacionIng;
    
    
    private ObservableList <Preoportunidades> listaPreoportunidades;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initAttributes() {
        Image imagen = new Image("/iconos/LogoPreoportunidad.png");
        icono.setImage(imagen);
        
        BackgroundPosition b = new BackgroundPosition(Side.RIGHT, 0, true, Side.BOTTOM, 0, true);
        BackgroundImage bi = new BackgroundImage(new Image("FondoVerde.png", 1894, 1420, false, true, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, b, BackgroundSize.DEFAULT);
        hBox.setBackground(new Background(bi));
        crearColumnas();
        
        listaPreoportunidades = FXCollections.observableArrayList();
        
        listaPreoportunidades.add(new Preoportunidades("456", new Date(99999999*999999999*999999999), "Pancho", "123", new Date(120000000), "Si", "Celeste Art", "Pendiente"));
        
        tablaPreoportunidades.setItems(listaPreoportunidades);

    }

    public void crearColumnas(){
        columnaFolio.setCellValueFactory(new PropertyValueFactory("folio"));
        columnaFechaRegistro.setCellValueFactory(new PropertyValueFactory("fechaRegistro"));
        columnaFechaCompromiso.setCellValueFactory(new PropertyValueFactory("fechaCompromiso"));
        columnaVerificacionIng.setCellValueFactory(new PropertyValueFactory("checkIngenieria"));
    }
    
    
    public void abrirVistaListadoDePreoportunidades() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/preoportunidades/VistaListadoDePreoportunidades.fxml"));
            Parent root = loader.load();
            VistaListadoDePreoportunidadesController controlador = loader.getController();
            controlador.initAttributes();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Listado de preoportunidades");
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
    private void agregar(ActionEvent event) {
        new VistaRegistroPreoportunidadesController().abrirVistaRegistroPreoportunidades();
    }

    @FXML
    private void modificar(ActionEvent event) {
    }

    @FXML
    private void eliminar(ActionEvent event) {
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

    @FXML
    private void pendientes(ActionEvent event) {
        new VistaPreoportunidadesPendientesController().abrirVistaPreoportunidadesPendientes();
    }

    @FXML
    private void seleccionarMouseClicked(MouseEvent event) {
        if (event.getClickCount() >= 2) {
            new VistaConsultaYSeguimientoPreoportunidadesController().abrirVistaConsultaYSeguimientoPreoportunidades();
        }

    }

    @FXML
    private void seleccionarEnter(KeyEvent event) {
        if(event.getCode().toString().contains("ENTER")){
             new VistaConsultaYSeguimientoPreoportunidadesController().abrirVistaConsultaYSeguimientoPreoportunidades();
        }
    }
    
}
