/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prospectos;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
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
import javafx.scene.control.ComboBox;
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
import javafx.util.converter.LocalDateTimeStringConverter;

/**
 * FXML Controller class
 *
 * @author chava
 */
public class VistaListadoDeProspectosController implements Initializable {

    @FXML
    private TextField txtBuscar;
    @FXML
    private TableView<Prospectos> tablaProspectos;
    @FXML
    private Button botonAgregar;
    @FXML
    private Button botonModificar;
    @FXML
    private Button botonEliminar;
    @FXML
    private Button botonSeguimiento;
    @FXML
    private Button botonSalir;
    @FXML
    private HBox hBox;
    @FXML
    private TableColumn<Prospectos, String> columnaFolio;
    @FXML
    private ImageView icono;
    @FXML
    private ComboBox<VistaListadoDeProspectosController> comboOculto;

    private boolean isOpenByPrincipal;
    private String datosARetornar;
    private ObservableList<Prospectos> listaProspectos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public ObservableList<Prospectos> getListaProspectos() {
        return listaProspectos;
    }

    public String getDatosARetornar() {
        return datosARetornar;
    }

    public void initAttributes(VistaListadoDeProspectosController controlador, String ventanaSolicita) {
        if (ventanaSolicita == null || ventanaSolicita.isEmpty()) {
            isOpenByPrincipal = true;
        } else {
            isOpenByPrincipal = false;
            botonAgregar.setDisable(true);
            botonModificar.setDisable(true);
            botonEliminar.setDisable(true);
            botonSeguimiento.setDisable(true);
        }

        Image imagen = new Image("/iconos/prospecto1.png");
        icono.setImage(imagen);

        BackgroundPosition b = new BackgroundPosition(Side.RIGHT, 0, true, Side.BOTTOM, 0, true);
        BackgroundImage bi = new BackgroundImage(new Image("FondoVerde.png", 1894, 1420, false, true, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, b, BackgroundSize.DEFAULT);
        hBox.setBackground(new Background(bi));

        listaProspectos = FXCollections.observableArrayList();
        Date date = new Date(120000000);

        inicializarColumnas();
        listaProspectos.add(new Prospectos("123", date, "Paquita", "Google", "San Pedro", "google@gmail.com", "3931014033", "Sutanito", "Pendiente"));
        tablaProspectos.setItems(listaProspectos);

        comboOculto.getItems().add(controlador);
        comboOculto.getSelectionModel().select(0);
    }

    public void inicializarColumnas() {
        columnaFolio.setCellValueFactory(new PropertyValueFactory("folio"));
    }

    public void abrirVistaListadoDeProspectos(String ventanaSolicita) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/prospectos/VistaListadoDeProspectos.fxml"));
            Parent root = loader.load();
            VistaListadoDeProspectosController controlador = loader.getController();
            controlador.initAttributes(controlador, ventanaSolicita);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Listado de prospectos");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            
            datosARetornar= controlador.getDatosARetornar();
            
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
        new VistaRegistroProspectosController().abrirRegistroProspectos();
    }

    @FXML
    private void modificar(ActionEvent event) {
    }

    @FXML
    private void eliminar(ActionEvent event) {
    }

    @FXML
    private void seguimiento(ActionEvent event) {
        new VistaConsultaProspectosYSeguimientosController().abrirVistaConsultaProspectosYSeguimientos();
    }

    @FXML
    private void salir(ActionEvent event) {
        Stage stage = (Stage) botonSalir.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void seleccionarMouseClicked(MouseEvent event) {
        if (event.getClickCount() >= 2) {
            if (isOpenByPrincipal) {
                new VistaConsultaProspectosYSeguimientosController().abrirVistaConsultaProspectosYSeguimientos();
            } else {
                VistaListadoDeProspectosController controladorAux = comboOculto.getSelectionModel().getSelectedItem();
                int indice = tablaProspectos.getSelectionModel().getSelectedIndex();
                Prospectos aux = controladorAux.getListaProspectos().get(indice);
                datosARetornar = aux.getFolio() + "," + aux.getNombre();
                
                Stage stage = (Stage)botonAgregar.getScene().getWindow();
                stage.close();
            }
        }

    }

    @FXML
    private void seleccionarEnter(KeyEvent event) {
        if (event.getCode().toString().contains("ENTER")) {
            if (isOpenByPrincipal) {
                new VistaConsultaProspectosYSeguimientosController().abrirVistaConsultaProspectosYSeguimientos();
            } else {
                VistaListadoDeProspectosController controladorAux = comboOculto.getSelectionModel().getSelectedItem();
                int indice = tablaProspectos.getSelectionModel().getSelectedIndex();
                Prospectos aux = controladorAux.getListaProspectos().get(indice);
                datosARetornar = aux.getFolio() + "," + aux.getNombre();
                
                Stage stage = (Stage)botonAgregar.getScene().getWindow();
                stage.close();

            }
        }
    }

}
