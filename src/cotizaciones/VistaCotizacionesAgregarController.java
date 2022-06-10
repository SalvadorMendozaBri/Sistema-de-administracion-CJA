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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author chava
 */
public class VistaCotizacionesAgregarController implements Initializable {

    @FXML
    private TreeTableView tableTree;
    @FXML
    private TreeTableColumn columnaSecciones;
    @FXML
    private TreeTableColumn columnaDescripcion;
    @FXML
    private TreeTableColumn columnaCosto;
    @FXML
    private TreeTableColumn columnaPrecio;
    @FXML
    private TreeTableColumn columnaAgrupacion;
    @FXML
    private TreeTableColumn columnaSubAgrupador;
    @FXML
    private TreeTableColumn columnaMostrarEnResumen;
    @FXML
    private Button botonNuevaSeccion;
    @FXML
    private Button botonModificar;
    @FXML
    private Button botonEliminar;
    @FXML
    private Button botonResumen;
    @FXML
    private Button botonGuardar;
    @FXML
    private Button botonDescartar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initAttributes() {

    }

    public void abrirVistaCotizacionesAgregar() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/cotizaciones/VistaCotizacionesAgregar.fxml"));
            Parent root = loader.load();
            VistaCotizacionesAgregarController controlador = loader.getController();
            controlador.initAttributes();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Agregar cotizacion");
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
    private void nuevaSeccion(ActionEvent event) {
        Label label = new Label("Nombre de la sección:");
        label.setFont(new Font(14));

        TextField txtNombre = new TextField();

        Button botonGuardar = new Button("Guardar");
        Button botonCancelar = new Button("Cancelar");

        VBox vbox = new VBox(10);
        HBox hbox = new HBox(10);

        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setPrefWidth(250);
        hbox.setAlignment(Pos.CENTER);

        hbox.getChildren().addAll(botonGuardar, botonCancelar);

        vbox.getChildren().addAll(label, txtNombre, hbox);

        Scene scene = new Scene(vbox);
        Stage stage = new Stage();

        botonGuardar.setOnAction(e -> {
            new VistaCotizacionesAgregarSeccionesController().abrirVistaCotizacionesAgregarSecciones(txtNombre.getText());
            stage.close();
        });

        botonCancelar.setOnAction(e -> stage.close());

        stage.setScene(scene);
        stage.showAndWait();

    }

    @FXML
    private void modificar(ActionEvent event) {
    }

    @FXML
    private void eliminar(ActionEvent event) {
    }

    @FXML
    private void resumen(ActionEvent event) {
    }

    @FXML
    private void guardar(ActionEvent event) {
    }

    @FXML
    private void descartar(ActionEvent event) {
    }

}
