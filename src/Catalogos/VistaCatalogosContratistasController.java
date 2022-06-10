/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Catalogos;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import moduloEntradas.Entradas;

/**
 * FXML Controller class
 *
 * @author chava
 */
public class VistaCatalogosContratistasController implements Initializable {

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtRazonSocial;
    @FXML
    private TextArea txtDescripcion;
    @FXML
    private TextField txtDomicilio;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtCorreo;
    @FXML
    private TextArea txtRepresentantes;
    @FXML
    private Button botonAgregar;
    @FXML
    private Button botonModificar;
    @FXML
    private Button botonEliminar;
    @FXML
    private TableColumn columnaID;
    @FXML
    private TableColumn columnaNombre;
    @FXML
    private TableColumn columnaDescripcion;
    @FXML
    private TableColumn columnaCosto;
    @FXML
    private Button botonGuardar;
    @FXML
    private Button botonCancelar;
    @FXML
    private CheckBox checkFactura;
    @FXML
    private TableView<ServiciosContratistas> tablaServiciosContratistas;

    ObservableList<Contratistas> listaContratistas;
    ObservableList<ServiciosContratistas> listaServiciosContratistas;
    Contratistas contratistaAModificar;
    String motivoApertura;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void initAttributes(ObservableList listaContratistas) {
        motivoApertura = "Agregar";

        botonAgregar.setGraphic(new ImageView(new Image("/iconos/masLogo.png", 20, 20, false, true)));
        botonEliminar.setGraphic(new ImageView(new Image("/iconos/iconoBotonEliminar.png", 20, 20, false, true)));
        botonModificar.setGraphic(new ImageView(new Image("/iconos/iconoBotonModificar.png", 20, 20, false, true)));
        this.listaContratistas = listaContratistas;
        initColumnas();
    }

    public void initAttributesModificar(Contratistas contratistaAModificar) {
        motivoApertura = "Modificar";

        botonAgregar.setGraphic(new ImageView(new Image("/iconos/masLogo.png", 25, 15, false, true)));
        botonEliminar.setGraphic(new ImageView(new Image("/iconos/iconoBotonEliminar.png", 20, 20, false, true)));
        botonModificar.setGraphic(new ImageView(new Image("/iconos/iconoBotonModificar.png", 20, 20, false, true)));
        this.contratistaAModificar = contratistaAModificar;

        txtId.setText(contratistaAModificar.getId());
        txtNombre.setText(contratistaAModificar.getNombre());
        txtRazonSocial.setText(contratistaAModificar.getRazonSocial());
        txtDescripcion.setText(contratistaAModificar.getDescripcion());
        txtDomicilio.setText(contratistaAModificar.getDomicilio());
        txtTelefono.setText(contratistaAModificar.getTelefono());
        txtCorreo.setText(contratistaAModificar.getCorreo());
        txtRepresentantes.setText(contratistaAModificar.getNombreRepresentantes());
        initColumnas();
    }

    public void initColumnas() {
        listaServiciosContratistas = FXCollections.observableArrayList();

        columnaID.setCellValueFactory(new PropertyValueFactory("id"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        columnaDescripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
        columnaCosto.setCellValueFactory(new PropertyValueFactory("costo"));
        tablaServiciosContratistas.setItems(listaServiciosContratistas);
    }

    public void abrirVistaCatalogosContratistas(ObservableList listaContratistas) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Catalogos/VistaCatalogosContratistas.fxml"));
            Parent root = loader.load();
            VistaCatalogosContratistasController controlador = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Agregar Contratistas");
            controlador.initAttributes(listaContratistas);
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.showAndWait();

        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText(e.toString());
            alerta.setContentText("Error en el loader");
            alerta.showAndWait();
        }
    }

    public void abrirVistaCatalogosContratistasModificar(Contratistas contratistaAModificar) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Catalogos/VistaCatalogosContratistas.fxml"));
            Parent root = loader.load();
            VistaCatalogosContratistasController controlador = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Agregar Contratistas");
            controlador.initAttributesModificar(contratistaAModificar);
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.showAndWait();

        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText(e.toString());
            alerta.setContentText("Error en el loader");
            alerta.showAndWait();
        }
    }

    @FXML
    private void agregar(ActionEvent event) {
        new VistaCatalogosContratistasAgregarServiciosController().abrirVistaCatalogosContratistasAgregarServicios(listaServiciosContratistas);
        tablaServiciosContratistas.refresh();

    }

    @FXML
    private void modificar(ActionEvent event) {
        if (tablaServiciosContratistas.getSelectionModel().getSelectedItem() != null) {
            ServiciosContratistas aux = tablaServiciosContratistas.getSelectionModel().getSelectedItem();
            new VistaCatalogosContratistasAgregarServiciosController().abrirVistaCatalogosContratistasAgregarServiciosModificar(aux);
            tablaServiciosContratistas.refresh();

        }

    }

    @FXML
    private void eliminar(ActionEvent event) {
        if (tablaServiciosContratistas.getSelectionModel().getSelectedItem() != null) {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Confirmación");
            alerta.setHeaderText(null);
            alerta.setContentText("¿Seguro que quiere eliminar este elemento?");
            alerta.showAndWait();

            String opcion = alerta.getResult().toString();
            if (opcion.contains("Aceptar")) {
                ServiciosContratistas aux = tablaServiciosContratistas.getSelectionModel().getSelectedItem();
                listaServiciosContratistas.remove(aux);
            }
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
            switch (motivoApertura) {
                case "Agregar":
                    String ID = txtId.getText();
                    String nombre = txtNombre.getText();
                    String razonSocial = txtRazonSocial.getText();
                    //String factura =
                    String descripcion = txtDescripcion.getText();
                    String domicilio = txtDomicilio.getText();
                    String telefono = txtTelefono.getText();
                    String correo = txtCorreo.getText();
                    // String nombreRepresentantes 

                    listaContratistas.add(new Contratistas(ID, nombre, razonSocial, correo, descripcion, domicilio, telefono, correo, nombre));

                    Alert alertaGuardar = new Alert(Alert.AlertType.INFORMATION);
                    alertaGuardar.setTitle("Datos guardados");
                    alertaGuardar.setHeaderText(null);
                    alertaGuardar.setContentText("Los datos han sido guardardos correctamente");
                    alertaGuardar.showAndWait();

                    //Restablecer todos los datos. 
                    txtId.setText("");
                    txtNombre.setText("");
                    txtRazonSocial.setText("");
                    txtDescripcion.setText("");
                    txtDomicilio.setText("");
                    txtTelefono.setText("");
                    txtCorreo.setText("");
                    txtRepresentantes.setText("");
                    break;
                case "Modificar":

                    contratistaAModificar.setNombre(txtNombre.getText());
                    contratistaAModificar.setRazonSocial(txtRazonSocial.getText());
                    contratistaAModificar.setDescripcion(txtDescripcion.getText());
                    contratistaAModificar.setDomicilio(txtDomicilio.getText());
                    contratistaAModificar.setTelefono(txtTelefono.getText());
                    contratistaAModificar.setCorreo(txtCorreo.getText());

                    Stage stage = (Stage) botonGuardar.getScene().getWindow();
                    stage.close();

                    break;
                default:

            }

        }

    }

    @FXML
    private void cancelar(ActionEvent event) {
    }

}
