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
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ococlimassystem.Conexion;

/**
 * FXML Controller class
 *
 * @author chava
 */
public class VistaCatalogosProveedoresAgregarController implements Initializable {

    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtDomicilio;
    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtTipoDeProveedor;
    @FXML
    private TextField txtNombre;
    @FXML
    private Button botonCancelar;
    @FXML
    private Button botonGuardar;
    @FXML
    private Button seleccionarProveedor;

    ObservableList listaProveedores;
    String motivoApertura;
    Proveedores proveedorAModificar;
    String arrayAux[];

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initAttributes(ObservableList listaProveedores) {

        Conexion con = new Conexion();

        ResultSet rs;
        Statement st;

        try {
            st = con.getCon().createStatement();
            rs = st.executeQuery("SELECT `AUTO_INCREMENT`\n"
                    + "FROM  INFORMATION_SCHEMA.TABLES\n"
                    + "WHERE TABLE_SCHEMA = 'ocsmodular'\n"
                    + "AND   TABLE_NAME   = 'proveedores'");
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
        this.listaProveedores = listaProveedores;
    }

    public void initAttributesModificar(Proveedores proveedorAModificar) {
        motivoApertura = "modificar";
        this.proveedorAModificar = proveedorAModificar;
        txtCodigo.setText(proveedorAModificar.getCodigo());
        txtNombre.setText(proveedorAModificar.getNombre());
        txtTipoDeProveedor.setText(proveedorAModificar.getTipo());
        txtDomicilio.setText(proveedorAModificar.getDomicilio());
        txtTelefono.setText(proveedorAModificar.getTelefono());

    }

    public void abrirVistaCatalogosProveedoresAgregar(ObservableList listaProveedores) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Catalogos/VistaCatalogosProveedoresAgregar.fxml"));
            Parent root = loader.load();
            VistaCatalogosProveedoresAgregarController controlador = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Agregar proveedor");
            stage.setOnCloseRequest(e -> {
                e.consume();
                cancelarOnClose(stage);
            });

            controlador.initAttributes(listaProveedores);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText(e.toString());
            alerta.setContentText("Error en el loader");
            alerta.showAndWait();
        }
    }

    public void abrirVistaCatalogosProveedoresModificar(Proveedores proveedorAModificar) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Catalogos/VistaCatalogosProveedoresAgregar.fxml"));
            Parent root = loader.load();
            VistaCatalogosProveedoresAgregarController controlador = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Agregar proveedor");
            stage.setOnCloseRequest(e -> {
                e.consume();
                cancelarOnClose(stage);
            });

            controlador.initAttributesModificar(proveedorAModificar);
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
                    String nombre = txtNombre.getText();
                    String domicilio = txtDomicilio.getText();
                    String tipoProveedor = txtTipoDeProveedor.getText();
                    String telefono = txtTelefono.getText();

                    Conexion con = new Conexion();
                    ResultSet rs;
                    Statement st;

                    try {
                        st = con.getCon().createStatement();

                        if (arrayAux != null) {
                            st.executeUpdate("INSERT INTO PROVEEDORES (CODIGO, NOMBRE, TELEFONO, DOMICILIO, TIPO) VALUES"
                                    + "(DEFAULT,'" + nombre + "','" + telefono + "','" + domicilio + "','" + arrayAux[0] + "')");
                            
                            listaProveedores.add(new Proveedores(codigo, nombre, telefono, domicilio, tipoProveedor));

                            txtCodigo.setText("");
                            txtNombre.setText("");
                            txtDomicilio.setText("");
                            txtTipoDeProveedor.setText("");
                            txtTelefono.setText("");

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

                    proveedorAModificar.setCodigo(txtCodigo.getText());
                    proveedorAModificar.setNombre(txtNombre.getText());
                    proveedorAModificar.setDomicilio(txtDomicilio.getText());
                    proveedorAModificar.setTipo(txtTipoDeProveedor.getText());
                    proveedorAModificar.setTelefono(txtTelefono.getText());

                    Conexion con = new Conexion();
                    ResultSet rs;
                    Statement st;

                    try {
                        st = con.getCon().createStatement();
                        rs = st.executeQuery("SELECT TIPO FROM PROVEEDORES WHERE CODIGO = '" + proveedorAModificar.getCodigo() + "'");
                        rs.next();
                        int codigoTipoAux = rs.getInt(1);

                        if (arrayAux != null) {
                            codigoTipoAux = Integer.parseInt(arrayAux[0]);
                        }

                        st = con.getCon().createStatement();
                        st.executeUpdate("UPDATE PROVEEDORES SET NOMBRE='" + txtNombre.getText() + "',TELEFONO='" + txtTelefono.getText() + "',TIPO='" + codigoTipoAux + "'"
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
    private void seleccionarProveedor(ActionEvent event) {
        VistaCatalogoSimplesController controlador = new VistaCatalogoSimplesController();
        controlador.abrirVistaCatalogoSimples("Tipo_proveedor");
        arrayAux = controlador.getDatosARetornar().split("\\|");
        txtTipoDeProveedor.setText(arrayAux[1]);
    }

}
