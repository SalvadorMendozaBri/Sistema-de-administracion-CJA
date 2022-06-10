/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Catalogos;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ococlimassystem.Conexion;

/**
 * FXML Controller class
 *
 * @author chava
 */
public class VistaCatalogosHerramientasAgregarController implements Initializable {

    @FXML
    private TextField txtCodigo;
    @FXML
    private TextArea txtDescripcion;
    @FXML
    private TextField txtNombreCorto;
    @FXML
    private TextField txtClase;
    @FXML
    private Button botonGuardar;
    @FXML
    private Button botonCancelar;
    @FXML
    private TextField txtCosto;
    @FXML
    private Button botonSeleccionarClaseHrta;

    ObservableList listaHerramientas;
    String motivoApertura;
    Herramientas herramientaAModificar;
    String arrayAux[];

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initAttributes(ObservableList listaHerramientas) {
        Conexion con = new Conexion();

        ResultSet rs;
        Statement st;

        try {
            st = con.getCon().createStatement();
            rs = st.executeQuery("SELECT `AUTO_INCREMENT`\n"
                    + "FROM  INFORMATION_SCHEMA.TABLES\n"
                    + "WHERE TABLE_SCHEMA = 'ocsmodular'\n"
                    + "AND   TABLE_NAME   = 'herramientas'");
            rs.next();
            txtCodigo.setText(rs.getInt(1) + "");

            con.getCon().close();

        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERROR");
            alerta.setHeaderText("Error al realizar una consulta en la base de datos");
            alerta.setContentText(e.toString());
            alerta.showAndWait();
        }

        motivoApertura = "agregar";
        this.listaHerramientas = listaHerramientas;
    }

    public void initAttributesModificar(Herramientas herramientasAModificar) {
        motivoApertura = "modificar";
        this.herramientaAModificar = herramientasAModificar;
        txtCodigo.setText(herramientasAModificar.getCodigo());
        txtClase.setText(herramientasAModificar.getClase());
        txtDescripcion.setText(herramientasAModificar.getDescripcion());
        txtNombreCorto.setText(herramientasAModificar.getNombreCorto());
        txtCosto.setText(Double.toString(herramientasAModificar.getCosto()));
    }

    public void abrirVistaCatalogosHerramientasAgregar(ObservableList listaHerramientas) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Catalogos/VistaCatalogosHerramientasAgregar.fxml"));
            Parent root = loader.load();
            VistaCatalogosHerramientasAgregarController controlador = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Agregar herramienta");
            stage.setOnCloseRequest((event) -> {
                event.consume();
                cancelarOnClose(stage);
            });

            controlador.initAttributes(listaHerramientas);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText(e.toString());
            alerta.setContentText("Error en el loader");
            alerta.showAndWait();
        }
    }

    public void abrirVistaCatalogosHerramientasModificar(Herramientas herramientaAModificar) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Catalogos/VistaCatalogosHerramientasAgregar.fxml"));
            Parent root = loader.load();
            VistaCatalogosHerramientasAgregarController controlador = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Agregar herramienta");
            stage.setOnCloseRequest((event) -> {
                event.consume();
                cancelarOnClose(stage);
            });

            controlador.initAttributesModificar(herramientaAModificar);
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
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Confirmación");
        alerta.setHeaderText(null);
        alerta.setContentText("¿Desea guardar los cambios?");
        alerta.showAndWait();

        String opcion = alerta.getResult().toString();
        if (opcion.contains("Aceptar")) {
            switch (motivoApertura) {
                case "agregar": {
                    String codigo = txtCodigo.getText();
                    String nombreCorto = txtNombreCorto.getText();
                    String descripcion = txtDescripcion.getText();
                    String clase = txtClase.getText();
                    String costo = txtCosto.getText();

                    Conexion con = new Conexion();
                    ResultSet rs;
                    Statement st;

                    try {
                        st = con.getCon().createStatement();

                        if (arrayAux != null) {
                            st.executeUpdate("INSERT INTO HERRAMIENTAS (CODIGO, NOMBRE_CORTO, DESCRIPCION, CLASE, COSTO) VALUES"
                                    + "(DEFAULT,'" + nombreCorto + "','" + descripcion + "','" + arrayAux[0] + "','" + costo + "')");
                            listaHerramientas.add(new Herramientas(codigo, nombreCorto, descripcion, clase, Double.parseDouble(costo)));

                            txtCodigo.setText("");
                            txtNombreCorto.setText("");
                            txtDescripcion.setText("");
                            txtClase.setText("");
                            txtCosto.setText("");

                            Alert alertaGuardar = new Alert(Alert.AlertType.INFORMATION);
                            alertaGuardar.setTitle("Datos guardados");
                            alertaGuardar.setHeaderText(null);
                            alertaGuardar.setContentText("Los datos han sido guardardos correctamente");
                            alertaGuardar.showAndWait();
                        } else {
                            Alert alerta3 = new Alert(Alert.AlertType.ERROR);
                            alerta3.setTitle("ERROR");
                            alerta3.setHeaderText("Error al guadar los datos");
                            alerta3.setContentText("Debes de elegir una clase");
                            alerta3.showAndWait();
                        }

                        con.getCon().close();

                    } catch (Exception e) {
                        Alert alerta2 = new Alert(Alert.AlertType.ERROR);
                        alerta2.setTitle("ERROR");
                        alerta2.setHeaderText("Error al realizar una consulta en la base de datos");
                        alerta2.setContentText(e.toString());
                        alerta2.showAndWait();
                    }

                }

                break;
                case "modificar": {

                    herramientaAModificar.setCodigo(txtCodigo.getText());
                    herramientaAModificar.setClase(txtClase.getText());
                    herramientaAModificar.setDescripcion(txtDescripcion.getText());
                    herramientaAModificar.setNombreCorto(txtNombreCorto.getText());
                    herramientaAModificar.setCosto(Double.parseDouble(txtCosto.getText()));

                    Conexion con = new Conexion();
                    ResultSet rs;
                    Statement st;

                    try {
                        st = con.getCon().createStatement();
                        rs = st.executeQuery("SELECT CLASE FROM HERRAMIENTAS WHERE CODIGO = '" + herramientaAModificar.getCodigo() + "'");
                        rs.next();
                        int codigoClaseAux = rs.getInt(1);

                        if (arrayAux != null) {
                            codigoClaseAux = Integer.parseInt(arrayAux[0]);
                        }

                        st = con.getCon().createStatement();
                        st.executeUpdate("UPDATE HERRAMIENTAS SET NOMBRE_CORTO='" + txtNombreCorto.getText() + "',DESCRIPCION='" + txtDescripcion.getText() + "',CLASE='" + codigoClaseAux + "',COSTO='" + txtCosto.getText() + "'"
                                + "WHERE CODIGO = '" + txtCodigo.getText() + "'");

                        con.getCon().close();

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
    private void seleccionarClaseHrta(ActionEvent event) {
        VistaCatalogoSimplesController controlador = new VistaCatalogoSimplesController();
        controlador.abrirVistaCatalogoSimples("Clase_hrta");
        arrayAux = controlador.getDatosARetornar().split("\\|");
        txtClase.setText(arrayAux[1]);
    }
}
