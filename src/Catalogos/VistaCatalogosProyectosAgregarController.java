/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Catalogos;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import moduloEntradas.Entradas;
import moduloSalidas.Salidas;
import moduloSalidas.VistaSalidasAgregarController;
import ococlimassystem.Conexion;

/**
 * FXML Controller class
 *
 * @author chava
 */
public class VistaCatalogosProyectosAgregarController implements Initializable {

    @FXML
    private TextField txtIdProyecto;
    @FXML
    private TextArea txtDescripcionProyecto;
    @FXML
    private DatePicker dateFechaContrato;
    @FXML
    private DatePicker dateFechaEntrega;
    @FXML
    private TextField txtNombreProyecto;
    @FXML
    private Button botonCancelar;
    @FXML
    private Button botonGuardar;

    String motivoApertura;
    ObservableList<Proyectos> listaProyectos;
    Proyectos proyectoAModificar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initAttributes(ObservableList listaProyectos) {

        Conexion con = new Conexion();

        ResultSet rs;
        Statement st;

        try {
            st = con.getCon().createStatement();
            rs = st.executeQuery("SELECT `AUTO_INCREMENT`\n"
                    + "FROM  INFORMATION_SCHEMA.TABLES\n"
                    + "WHERE TABLE_SCHEMA = 'ocsmodular'\n"
                    + "AND   TABLE_NAME   = 'proyectos'");
            rs.next();
            txtIdProyecto.setText(rs.getInt(1) + "");

            con.getCon().close();

        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERROR");
            alerta.setHeaderText("Error al realizar una consulta en la base de datos");
            alerta.setContentText(e.toString());
            alerta.showAndWait();
        }
        motivoApertura = "Agregar";

        this.listaProyectos = listaProyectos;
    }

    public void initAttributesModificar(Proyectos proyectoAModificar) {
        motivoApertura = "Modificar";

        this.proyectoAModificar = proyectoAModificar;

        txtIdProyecto.setText(proyectoAModificar.getIdProyecto());
        txtNombreProyecto.setText(proyectoAModificar.getNombre());
        txtDescripcionProyecto.setText(proyectoAModificar.getDescripcion());
        dateFechaContrato.setValue(LocalDate.parse(this.proyectoAModificar.getFechaContrato().toString()));
        dateFechaEntrega.setValue(LocalDate.parse(this.proyectoAModificar.getFechaEntrega().toString()));
    }

    public void abrirVistaCatalogosProyectosAgregar(ObservableList listaProyectos) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Catalogos/VistaCatalogosProyectosAgregar.fxml"));
            Parent root = loader.load();
            VistaCatalogosProyectosAgregarController controlador = loader.getController();
            controlador.initAttributes(listaProyectos);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.setTitle("Agregar proyectos");
            stage.setOnCloseRequest(event -> {
                event.consume();
                cancelarOnClose(stage);
            });
            stage.showAndWait();

        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText(e.toString());
            alerta.setContentText("No se ha encontrado el archivo");
            alerta.show();
        }
    }

    public void abrirVistaCatalogosProyectosModificar(Proyectos proyectoAModificar) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Catalogos/VistaCatalogosProyectosAgregar.fxml"));
            Parent root = loader.load();
            VistaCatalogosProyectosAgregarController controlador = loader.getController();
            controlador.initAttributesModificar(proyectoAModificar);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.setTitle("Modificar proyectos");
            stage.setOnCloseRequest(event -> {
                event.consume();
                cancelarOnClose(stage);
            });
            stage.showAndWait();

        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText(e.toString());
            alerta.setContentText("No se ha encontrado el archivo");
            alerta.show();
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
                    case "Agregar": {
                        String ID = txtIdProyecto.getText();
                        String nombre = txtNombreProyecto.getText();
                        String descripcion = txtDescripcionProyecto.getText();
                        Date fechaContrato = Date.valueOf(dateFechaContrato.getValue());
                        Date fechaEntrega = Date.valueOf(dateFechaEntrega.getValue());

                        Conexion con = new Conexion();
                        ResultSet rs;
                        Statement st;

                        try {

                            st = con.getCon().createStatement();
                            st.executeUpdate("INSERT INTO PROYECTOS (ID, NOMBRE, DESCRIPCION, FECHA_CONTRATO, FECHA_ENTREGA) VALUES"
                                    + "(DEFAULT,'" + nombre + "','" + descripcion + "','" + fechaContrato.toString() + "','" + fechaEntrega.toString() + "')");

                        } catch (Exception e) {
                            Alert alerta2 = new Alert(Alert.AlertType.ERROR);
                            alerta2.setTitle("ERROR");
                            alerta2.setHeaderText("Error al realizar una consulta en la base de datos");
                            alerta2.setContentText(e.toString());
                            alerta2.showAndWait();
                        }

                        listaProyectos.add(new Proyectos(ID, nombre, descripcion, fechaContrato, fechaEntrega));

                        txtIdProyecto.setText("");
                        txtNombreProyecto.setText("");
                        txtDescripcionProyecto.setText("");
                        dateFechaContrato.setValue(LocalDate.now());
                        dateFechaEntrega.setValue(LocalDate.now());

                        Alert alertaGuardar = new Alert(Alert.AlertType.INFORMATION);
                        alertaGuardar.setTitle("Datos guardados");
                        alertaGuardar.setHeaderText(null);
                        alertaGuardar.setContentText("Los datos han sido guardardos correctamente");
                        alertaGuardar.showAndWait();
                    }

                    break;
                    case "Modificar": {
                        proyectoAModificar.setNombre(txtNombreProyecto.getText());
                        proyectoAModificar.setDescripcion(txtDescripcionProyecto.getText());
                        proyectoAModificar.setFechaContrato(Date.valueOf(dateFechaContrato.getValue()));
                        proyectoAModificar.setFechaContrato(Date.valueOf(dateFechaEntrega.getValue()));
                        
                        Conexion con = new Conexion();
                        ResultSet rs;
                        Statement st;

                        try {

                            st = con.getCon().createStatement();
                            st.executeUpdate("UPDATE PROYECTOS SET NOMBRE='" + txtNombreProyecto.getText() + "',DESCRIPCION='" + txtDescripcionProyecto.getText() + "',FECHA_CONTRATO='" + dateFechaContrato.getValue().toString() + "',FECHA_ENTREGA='"+dateFechaContrato.getValue().toString()+"'"
                                + "WHERE ID = '" + txtIdProyecto.getText() + "'");

                        } catch (Exception e) {
                            Alert alerta2 = new Alert(Alert.AlertType.ERROR);
                            alerta2.setTitle("ERROR");
                            alerta2.setHeaderText("Error al realizar una consulta en la base de datos");
                            alerta2.setContentText(e.toString());
                            alerta2.showAndWait();
                        }
                        

                        Alert alertaGuardar = new Alert(Alert.AlertType.INFORMATION);
                        alertaGuardar.setTitle("Datos guardados");
                        alertaGuardar.setHeaderText(null);
                        alertaGuardar.setContentText("Los datos han sido guardardos correctamente");
                        alertaGuardar.showAndWait();

                        Stage stage = (Stage) botonGuardar.getScene().getWindow();
                        stage.close();

                    }
                    break;

                }

                //Restablecer todos los datos. 
            }

        } catch (NumberFormatException e) {

            Alert alertaGuardar = new Alert(Alert.AlertType.ERROR);
            alertaGuardar.setTitle("Formato de costo incorrecto");
            alertaGuardar.setHeaderText(null);
            alertaGuardar.setContentText("El costo debe tener un valor numerico");
            alertaGuardar.showAndWait();

        }
    }

}
