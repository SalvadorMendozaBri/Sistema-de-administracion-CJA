/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Catalogos;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
public class VistaCatalogosEquiposAgregarController implements Initializable {

    @FXML
    private TextField txtCodigo;
    @FXML
    private Button botonGuardar;
    @FXML
    private Button botonCancelar;
    @FXML
    private DatePicker dateFechaRegistro;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextArea txtDescripcion;
    @FXML
    private TextField txtModelo;
    @FXML
    private TextField txtMarca;
    @FXML
    private TextField txtCaudal;
    @FXML
    private TextField txtPresion;
    @FXML
    private TextField txtPotencia;
    @FXML
    private TextField txtVoltaje;
    @FXML
    private TextField txtAmperaje;
    @FXML
    private TextField txtGasRefrigerante;
    @FXML
    private TextField txtCapacidad;
    @FXML
    private TextField txtCosto;
    @FXML
    private ComboBox<String> comboTipo;

    ObservableList listaEquipos;
    String motivoApertura;
    Equipos equipoAModificar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initAttributes(ObservableList listaEquipos) {
        motivoApertura = "agregar";
        comboTipo.getItems().addAll("Ventilación", "AC", "Calefacción", "Refirgeración", "Electrico", "Otro");
        comboTipo.getSelectionModel().select(0);
        dateFechaRegistro.setValue(LocalDate.now());

        this.listaEquipos = listaEquipos;
    }

    public void initAttributesModificar(Equipos equipoAModificar) {
        motivoApertura = "modificar";
        this.equipoAModificar = equipoAModificar;

        comboTipo.getItems().addAll("Ventilación", "AC", "Calefacción", "Refirgeración", "Electrico", "Otro");
        comboTipo.getSelectionModel().select(0);

        txtCodigo.setText(equipoAModificar.getFolio());
        dateFechaRegistro.setValue(equipoAModificar.getFecha().toLocalDate());
        txtNombre.setText(equipoAModificar.getNombreEquipo());
        txtDescripcion.setText(equipoAModificar.getDescripcion());
        txtModelo.setText(equipoAModificar.getModelo());
        txtMarca.setText(equipoAModificar.getMarca());
        txtCaudal.setText(equipoAModificar.getCaudal());
        txtPresion.setText(equipoAModificar.getPresion());
        txtPotencia.setText(equipoAModificar.getPotencia());
        txtVoltaje.setText(equipoAModificar.getVoltaje());
        txtAmperaje.setText(equipoAModificar.getAmperaje());
        txtGasRefrigerante.setText(equipoAModificar.getGasRefrigerante());
        txtCapacidad.setText(equipoAModificar.getCapacidad());
        comboTipo.getSelectionModel().select(equipoAModificar.getTipo());
        txtCosto.setText(equipoAModificar.getCosto() + "");

    }

    public void abrirVistaCatalogosEquiposAgregar(ObservableList listaEquipos) {

        // Date fecha = Date.valueOf(dateFechaRegistro.getValue());         
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Catalogos/VistaCatalogosEquiposAgregar.fxml"));
            Parent root = loader.load();
            VistaCatalogosEquiposAgregarController controlador = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Agregar equipo");
            stage.setOnCloseRequest((event) -> {
                event.consume();
                cancelarOnClose(stage);
            });
            controlador.initAttributes(listaEquipos);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText(e.toString());
            alerta.setContentText("Error en el loader");
            alerta.showAndWait();
        }
    }

    public void abrirVistaCatalogosEquiposModificar(Equipos equipoAModificar) {

        // Date fecha = Date.valueOf(dateFechaRegistro.getValue());         
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Catalogos/VistaCatalogosEquiposAgregar.fxml"));
            Parent root = loader.load();
            VistaCatalogosEquiposAgregarController controlador = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Agregar equipo");
            stage.setOnCloseRequest((event) -> {
                event.consume();
                cancelarOnClose(stage);
            });
            controlador.initAttributesModificar(equipoAModificar);
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
    private void guardar(ActionEvent event) {
        try {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Confirmación");
            alerta.setHeaderText(null);
            alerta.setContentText("¿Desea guardar los cambios?");
            alerta.showAndWait();

            String opcion = alerta.getResult().toString();
            if (opcion.contains("Aceptar")) {
                switch (motivoApertura) {
                    case "agregar": {
                        String folio = txtCodigo.getText();
                        Date fecha = Date.valueOf(dateFechaRegistro.getValue());
                        String nombreEquipo = txtNombre.getText();
                        String descripcion = txtDescripcion.getText();
                        String modelo = txtModelo.getText();
                        String marca = txtMarca.getText();
                        String caudal = txtCaudal.getText();
                        String presion = txtPresion.getText();
                        String potencia = txtPotencia.getText();
                        String voltaje = txtVoltaje.getText();
                        String amperaje = txtAmperaje.getText();
                        String gasRefrigerante = txtGasRefrigerante.getText();
                        String capacidad = txtCapacidad.getText();
                        String tipo = comboTipo.getSelectionModel().getSelectedItem();
                        double costo = Double.parseDouble(txtCosto.getText());

                        listaEquipos.add(new Equipos(folio, fecha, nombreEquipo, descripcion, modelo, marca, caudal, presion, potencia, voltaje, amperaje, gasRefrigerante, capacidad, tipo, costo));

                        txtCodigo.setText("");
                        dateFechaRegistro.setValue(LocalDate.now());
                        txtNombre.setText("");
                        txtDescripcion.setText("");
                        txtModelo.setText("");
                        txtMarca.setText("");
                        txtCaudal.setText("");
                        txtPresion.setText("");
                        txtPotencia.setText("");
                        txtVoltaje.setText("");
                        txtAmperaje.setText("");
                        txtGasRefrigerante.setText("");
                        txtCapacidad.setText("");
                        comboTipo.getSelectionModel().select(0);
                        txtCosto.getText();

                        Alert alertaGuardar = new Alert(Alert.AlertType.INFORMATION);
                        alertaGuardar.setTitle("Datos guardados");
                        alertaGuardar.setHeaderText(null);
                        alertaGuardar.setContentText("Los datos han sido guardardos correctamente");
                        alertaGuardar.showAndWait();
                        //Restablecer todos los datos. 

                    }
                    break;
                    case "modificar": {
                        equipoAModificar.setFolio(txtCodigo.getText());
                        Date date = Date.valueOf(dateFechaRegistro.getValue());
                        equipoAModificar.setFecha(date);
                        equipoAModificar.setNombreEquipo(txtNombre.getText());
                        equipoAModificar.setDescripcion(txtDescripcion.getText());
                        equipoAModificar.setModelo(txtModelo.getText());
                        equipoAModificar.setMarca(txtMarca.getText());
                        equipoAModificar.setCaudal(txtCaudal.getText());
                        equipoAModificar.setPresion(txtPresion.getText());
                        equipoAModificar.setPotencia(txtPotencia.getText());
                        equipoAModificar.setVoltaje(txtVoltaje.getText());
                        equipoAModificar.setAmperaje(txtAmperaje.getText());
                        equipoAModificar.setGasRefrigerante(txtGasRefrigerante.getText());
                        equipoAModificar.setCapacidad(txtCapacidad.getText());
                        equipoAModificar.setTipo(comboTipo.getSelectionModel().getSelectedItem());
                        equipoAModificar.setCosto(Double.parseDouble(txtCosto.getText()));

                        Alert alertaGuardar2 = new Alert(Alert.AlertType.INFORMATION);
                        alertaGuardar2.setTitle("Datos guardados");
                        alertaGuardar2.setHeaderText(null);
                        alertaGuardar2.setContentText("Los datos han sido guardardos correctamente");
                        alertaGuardar2.showAndWait();

                        Stage stage = (Stage) botonGuardar.getScene().getWindow();
                        stage.close();
                    }
                    break;

                    default:
                        throw new AssertionError();
                }

            }
        } catch (NumberFormatException e) {

            Alert alertaGuardar = new Alert(Alert.AlertType.ERROR);
            alertaGuardar.setTitle("Formato de costo incorrecto");
            alertaGuardar.setHeaderText(null);
            alertaGuardar.setContentText("El costo debe tener un valor numerico");
            alertaGuardar.showAndWait();

        }
    }

    @FXML
    public void cancelar(ActionEvent event
    ) {

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
