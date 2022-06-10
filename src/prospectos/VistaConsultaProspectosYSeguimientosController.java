/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prospectos;

import Catalogos.VistaCatalogosController;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import preoportunidades.VistaRegistroPreoportunidadesController;

/**
 * FXML Controller class
 *
 * @author chava
 */
public class VistaConsultaProspectosYSeguimientosController implements Initializable {

    @FXML
    private TextField txtFolio;
    @FXML
    private DatePicker dateFechaRegistro;
    @FXML
    private TextField txtUsuario;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtCalle;
    @FXML
    private TextField txtNumero;
    @FXML
    private TextField txtCP;
    @FXML
    private TextField txtMunicipio;
    @FXML
    private TextField txtEstado;
    @FXML
    private TextField txtPais;
    @FXML
    private TextField txtCorreo;
    @FXML
    private ComboBox<?> comboTipo;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtCelular;
    @FXML
    private TextArea txtAreaEntero;
    @FXML
    private TextField txtVendedor;
    @FXML
    private TextField txtBuscar;
    @FXML
    private TableView<?> tablaSeguimientos;
    @FXML
    private Button botonAgregarSeguimiento;
    @FXML
    private Button botonModificar;
    @FXML
    private Button botonEliminar;
    @FXML
    private Button botonAgregarPreoportunidad;
    @FXML
    private TextField txtEmpresa;
    @FXML
    private Button botonSeleccionarEmpresa;
    @FXML
    private Button botonSeleccionarVendedor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initAttributes() {

    }

    public void abrirVistaConsultaProspectosYSeguimientos() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/prospectos/VistaConsultaProspectosYSeguimientos.fxml"));
            Parent root = loader.load();
            VistaConsultaProspectosYSeguimientosController controlador = loader.getController();
            controlador.initAttributes();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Consulta de prospecto");
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
    private void seleccionarMouse(MouseEvent event) {
    }

    @FXML
    private void seleccionarEnter(KeyEvent event) {
    }

    @FXML
    private void agregarSeguimiento(ActionEvent event) {
        new VistaRegistroDeSeguimientosController().abrirVistaRegistroDeSeguimientos();
    }

    @FXML
    private void modificar(ActionEvent event) {
    }

    @FXML
    private void eliminar(ActionEvent event) {
    }

    @FXML
    private void agregarPreoportunidad(ActionEvent event) {
        new VistaRegistroPreoportunidadesController().abrirVistaRegistroPreoportunidades();
    }

    @FXML
    private void seleccionarEmpresa(ActionEvent event) {
        VistaCatalogosController controlador = new VistaCatalogosController();
        controlador.abrirCatalogosPrincipal("Empresas");
        String arrayAux[] = controlador.getDatosARetornar().split(",");
        txtEmpresa.setText(arrayAux[1]);
        txtCalle.setText(arrayAux[2]);
        txtNumero.setText(arrayAux[3]);
        txtCP.setText(arrayAux[4]);
        txtMunicipio.setText(arrayAux[5]);
        txtEstado.setText(arrayAux[6]);
        txtPais.setText(arrayAux[7]);

        //La posicion 0 del array se guardara en una variable global para su conexion con la base de datos.
    }

    @FXML
    private void seleccionarVendedor(ActionEvent event) {
        VistaCatalogosController controlador = new VistaCatalogosController();
        controlador.abrirCatalogosPrincipal("Vendedores");
        String arrayAux[] = controlador.getDatosARetornar().split(",");
        txtVendedor.setText(arrayAux[1]);

        //La posicion 0 del array se guardara en una variable global para su conexion con la base de datos.
    }

    @FXML
    private void seleccionarEmpresaKeyTyped(KeyEvent event) {

        if (event.getCode().toString().equalsIgnoreCase("ENTER")) {
            VistaCatalogosController controlador = new VistaCatalogosController();
            controlador.abrirCatalogosPrincipal("Empresas");
            String arrayAux[] = controlador.getDatosARetornar().split(",");
            txtEmpresa.setText(arrayAux[1]);    
            txtCalle.setText(arrayAux[2]);
            txtNumero.setText(arrayAux[3]);
            txtCP.setText(arrayAux[4]);
            txtMunicipio.setText(arrayAux[5]);
            txtEstado.setText(arrayAux[6]);
            txtPais.setText(arrayAux[7]);
        }
    }

    @FXML
    private void seleccionarVendedorKeyTyped(KeyEvent event) {

        if (event.getCode().toString().equalsIgnoreCase("ENTER")) {
            VistaCatalogosController controlador = new VistaCatalogosController();
            controlador.abrirCatalogosPrincipal("Vendedores");
            String arrayAux[] = controlador.getDatosARetornar().split(",");
            txtVendedor.setText(arrayAux[1]);
        }

    }

}
