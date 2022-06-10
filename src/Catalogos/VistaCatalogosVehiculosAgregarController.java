package Catalogos;

import java.net.URL;
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
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class VistaCatalogosVehiculosAgregarController implements Initializable {

    @FXML
    private TextField txtID;
    @FXML
    private TextField txtTipo;
    @FXML
    private TextField txtSucursal;
    @FXML
    private TextField txtGasolina;
    @FXML
    private Button botonGuardar;
    @FXML
    private Button botonCancelar;

    ObservableList<Vehiculos> listaVehiculos;
    Vehiculos vehiculoAModificar;
    String motivoApertura;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void initAttributesModificar(Vehiculos vehiculoAModificar) {
        motivoApertura = "modificar";
        this.vehiculoAModificar = vehiculoAModificar;
        
        txtID.setText(vehiculoAModificar.getId());
        txtSucursal.setText(vehiculoAModificar.getSucursal());
        txtTipo.setText(vehiculoAModificar.getTipo());
        txtGasolina.setText(vehiculoAModificar.getGastoGasolina());

    }

    public void initAttributes(ObservableList listaVehiculos) {
        motivoApertura = "agregar";
        this.listaVehiculos = listaVehiculos;

    }

    public void abrirVistaCatalogosVehiculosAgregar(ObservableList listaVehiculos) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Catalogos/VistaCatalogosVehiculosAgregar.fxml"));
            Parent root = loader.load();
            VistaCatalogosVehiculosAgregarController controlador = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Agregar empresa");
            stage.setOnCloseRequest((event) -> {
                event.consume();
                cancelarOnClose(stage);
            });
            controlador.initAttributes(listaVehiculos);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText(e.toString());
            alerta.setContentText("Error en el loader");
            alerta.showAndWait();
        }
    }

    public void abrirVistaCatalogosVehiculosModificar(Vehiculos vehiculoAModificar) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Catalogos/VistaCatalogosVehiculosAgregar.fxml"));
            Parent root = loader.load();
            VistaCatalogosVehiculosAgregarController controlador = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Agregar empresa");
            stage.setOnCloseRequest((event) -> {
                event.consume();
                cancelarOnClose(stage);
            });
            controlador.initAttributesModificar(vehiculoAModificar);
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
                        String id = txtID.getText();
                        String tipo = txtTipo.getText();
                        String sucursal = txtSucursal.getText();
                        double gasolina = Double.parseDouble(txtGasolina.getText());

                        listaVehiculos.add(new Vehiculos(id, tipo, sucursal, gasolina + ""));

                        txtID.setText("");
                        txtTipo.setText("");
                        txtSucursal.setText("");
                        txtGasolina.setText("");

                        Alert alertaGuardar = new Alert(Alert.AlertType.INFORMATION);
                        alertaGuardar.setTitle("Datos guardados");
                        alertaGuardar.setHeaderText(null);
                        alertaGuardar.setContentText("Los datos han sido guardardos correctamente");
                        alertaGuardar.showAndWait();
                        //Restablecer todos los datos.

                    }
                    break;
                    case "modificar": {
                        vehiculoAModificar.setId(txtID.getText());
                        vehiculoAModificar.setTipo(txtTipo.getText());
                        vehiculoAModificar.setSucursal(txtSucursal.getText());
                        vehiculoAModificar.setGastoGasolina(txtGasolina.getText() + "");

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

                }

            }
        } catch (NumberFormatException e) {
            Alert alertaGuardar = new Alert(Alert.AlertType.ERROR);
            alertaGuardar.setTitle("Formato de gasolina incorrecto");
            alertaGuardar.setHeaderText(null);
            alertaGuardar.setContentText("El campo de gasolina debe tener un valor númerico");
            alertaGuardar.showAndWait();
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
