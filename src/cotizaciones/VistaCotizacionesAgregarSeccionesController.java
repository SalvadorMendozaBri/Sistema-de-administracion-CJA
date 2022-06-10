/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotizaciones;

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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author chava
 */
public class VistaCotizacionesAgregarSeccionesController implements Initializable {

    @FXML
    private TextArea txtDescripcion;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtModelo;
    @FXML
    private TextField txtCaudal;
    @FXML
    private TextField txtMarca;
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
    private TextField txtTipo;
    @FXML
    private TextField txtMult;
    @FXML
    private TextField txtCostoR;
    @FXML
    private TextField txtDescuento;
    @FXML
    private TextField txtCantidad;
    @FXML
    private TextField txtVenta;
    @FXML
    private Button botonAgregarEquipo;
    @FXML
    private TextField txtBuscar;
    @FXML
    private TableView tablaEquipos;
    @FXML
    private TableColumn ColumnaID;
    @FXML
    private TableColumn ColumnaNombre;
    @FXML
    private TableColumn ColumnaCosto;
    @FXML
    private TableColumn ColumnaCostoR;
    @FXML
    private TableColumn ColumnaDescuento;
    @FXML
    private TableColumn ColumnaCantidad;
    @FXML
    private TableColumn ColumnaVenta;
    @FXML
    private Button botonSalir;
    @FXML
    private TextArea txtDescripcion1;
    @FXML
    private TextField txtNombreCorto;
    @FXML
    private TextField txtClase;
    @FXML
    private TextField txtCosto1;
    @FXML
    private TextField txtVenta1;
    @FXML
    private TextField txtUnidad;
    @FXML
    private TextField txtxSubClase;
    @FXML
    private TextField txtMulti;
    @FXML
    private TextField txtDescuento1;
    @FXML
    private TextField txtCostoR1;
    @FXML
    private TextField txtCantidad1;
    @FXML
    private Button botonAgregarProducto;
    @FXML
    private TableColumn columnaProducto;
    @FXML
    private TableColumn columnaCantidad;
    @FXML
    private TableColumn columnaCosto;
    @FXML
    private TableColumn columnaMulti;
    @FXML
    private TableColumn columnaCostoR;
    @FXML
    private TableColumn columnaVenta;
    @FXML
    private TableColumn columnaObservaciones;
    @FXML
    private Button botonSalir1;
    @FXML
    private Label labelNombre;
    @FXML
    private Button botonModificarEquipos;
    @FXML
    private Button botonEliminarEquipos;
    @FXML
    private TextField txtBuscarProdcuto;
    @FXML
    private Button botonModificarProductos;
    @FXML
    private Button botonEliminarProductos;
    @FXML
    private Button botonseleccionarEquipo;
    @FXML
    private TextArea txtDescripcionGeneral;
    @FXML
    private TextField txtContratistaPersonal;
    @FXML
    private Button seleccionarContratistaPersonal;
    @FXML
    private TextField txtRazonSocialPersonal;
    @FXML
    private Button seleccionarPersonal;
    @FXML
    private TextArea txtDescripcionPersonal;
    @FXML
    private TextField txtServicioPersonal;
    @FXML
    private TextField txtCostoPersonal;
    @FXML
    private TextField txtMultiplicadorPersonal;
    @FXML
    private TextField txtCostoRealPersonal;
    @FXML
    private TextField txtCantidadPersonal;
    @FXML
    private TextField txtDescuentoPersonal;
    @FXML
    private TextField txtUtilidadPersonal;
    @FXML
    private TextField txtPVentaPersonal;
    @FXML
    private Button BotonAgregarPersonal;
    @FXML
    private TextField txtIDServiciosOcoclimas;
    @FXML
    private Button botonseleccionarServicioOcoclimas;
    @FXML
    private Button botonSeleccionarPersonalOcoclimas;
    @FXML
    private TextField txtNombreServicioOcoclimas;
    @FXML
    private TextField txtNombrePersonalOcoclimas;
    @FXML
    private TextArea txtDescripcionOcoclimas;
    @FXML
    private TextField txtCostoOcoclimas;
    @FXML
    private TextField txtMultiplicadorOcoclimas;
    @FXML
    private TextField txtCostoRealOcoclimas;
    @FXML
    private TextField txtCantidadDOcoclimas;
    @FXML
    private TextField txtDescuentoOcoclimas;
    @FXML
    private TextField txtUnidadOcoclimas;
    @FXML
    private TextField txtPVentasOcoclimas;
    @FXML
    private TextField txtCantidadPOcoclimas;
    @FXML
    private TextField txtTipoVehiculo;
    @FXML
    private Button botonSeleccionarVehiculo;
    @FXML
    private TextField txtCostoRealVehiculo;
    @FXML
    private TextField txtCostoVehiculo;
    @FXML
    private TextField txtMultiplicadorVehiculo;
    @FXML
    private TextField txtCantidadPVehiculo;
    @FXML
    private TextField txtCantidadVehiculo;
    @FXML
    private TextField txtDescuentoVehiculo;
    @FXML
    private TextField txtUtilidadVehiculo;
    @FXML
    private TextField txtPVentaVehiculo;
    @FXML
    private Button BotonAgregarVehiculo;
    @FXML
    private TextField txtSucursalVehiculo;
    @FXML
    private TextField txtGastoGasolina;
    @FXML
    private Button botonSeleccionarServicioGenerico;
    @FXML
    private TextArea txtDescripcionGenericos;
    @FXML
    private TextField txtCostoGenericos;
    @FXML
    private TextField txtMultiplicadorGenericos;
    @FXML
    private TextField txtCostoRealGenericos;
    @FXML
    private TextField txtCantidadDGenericos;
    @FXML
    private TextField txtDescuentoGenericos;
    @FXML
    private TextField txtUnidadGenericos;
    @FXML
    private TextField txtPVentasGenericos;
    @FXML
    private TextField txtCantidadPGenericos;
    @FXML
    private TextField txtBucar;
    @FXML
    private TableColumn columnaIdServicios;
    @FXML
    private TableColumn columnaNombreServicios;
    @FXML
    private TableColumn columnaDescripcionServicios;
    @FXML
    private TableColumn columnaCostoServicios;
    @FXML
    private Button botonModificarPersonal;
    @FXML
    private Button botonSalir2;
    @FXML
    private TextField txtIDProducto;
    @FXML
    private TextField txtIDContratistaPersonal;
    @FXML
    private TextField txtIDPersonal;
    @FXML
    private TextField txtIDPersonalOcoclimas;
    @FXML
    private TextField txtIDVehiculo;
    @FXML
    private TextField txtIDServicioGenericos;
    @FXML
    private TextField txtFolioEquipos;
    @FXML
    private Button botonSeleccionarProducto;
    @FXML
    private Button botonEliminarPersonal;

    String arrayAux[];

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initAttributes(String nombreSeccion) {
        labelNombre.setText(nombreSeccion);

        botonEliminarProductos.setGraphic(new ImageView(new Image("/iconos/iconoBotonEliminar.png", 20, 20, false, true)));
        botonModificarProductos.setGraphic(new ImageView(new Image("/iconos/iconoBotonModificar.png", 20, 20, false, true)));

        botonEliminarEquipos.setGraphic(new ImageView(new Image("/iconos/iconoBotonEliminar.png", 20, 20, false, true)));
        botonModificarEquipos.setGraphic(new ImageView(new Image("/iconos/iconoBotonModificar.png", 20, 20, false, true)));

        botonEliminarPersonal.setGraphic(new ImageView(new Image("/iconos/iconoBotonEliminar.png", 20, 20, false, true)));
        botonModificarPersonal.setGraphic(new ImageView(new Image("/iconos/iconoBotonModificar.png", 20, 20, false, true)));

    }

    public void abrirVistaCotizacionesAgregarSecciones(String nombreSeccion) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/cotizaciones/VistaCotizacionesAgregarSecciones.fxml"));
            Parent root = loader.load();
            VistaCotizacionesAgregarSeccionesController controlador = loader.getController();
            controlador.initAttributes(nombreSeccion);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Agregar secciones");
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
    private void seleccionarEquipo(ActionEvent event) {
        VistaCatalogosController controlador = new VistaCatalogosController();
        controlador.abrirCatalogosPrincipal("Equipos");
        arrayAux = controlador.getDatosARetornar().split("\\|");
        txtFolioEquipos.setText(arrayAux[0]);
        txtNombre.setText(arrayAux[2]);
        txtDescripcion.setText(arrayAux[3]);
        txtModelo.setText(arrayAux[4]);
        txtMarca.setText(arrayAux[5]);
        txtCaudal.setText(arrayAux[6]);
        txtPresion.setText(arrayAux[7]);
        txtPotencia.setText(arrayAux[8]);
        txtVoltaje.setText(arrayAux[9]);
        txtAmperaje.setText(arrayAux[10]);
        txtGasRefrigerante.setText(arrayAux[11]);
        txtCapacidad.setText(arrayAux[12]);
        txtTipo.setText(arrayAux[13]);
        txtCosto.setText(arrayAux[14]);
    }

    @FXML
    private void seleccionarContratista(ActionEvent event) {
        //pendiente
    }

    @FXML
    private void seleccionarPersonal(ActionEvent event) {
        VistaCatalogosController controlador = new VistaCatalogosController();
        controlador.abrirCatalogosPrincipal("Personal");
        arrayAux = controlador.getDatosARetornar().split("\\|");
        txtIDPersonal.setText(arrayAux[0]);
    }

    @FXML
    private void seleccionarPersonalOcoclimas(ActionEvent event) {
        VistaCatalogosController controlador = new VistaCatalogosController();
        controlador.abrirCatalogosPrincipal("Personal");
        arrayAux = controlador.getDatosARetornar().split("\\|");
        txtIDPersonalOcoclimas.setText(arrayAux[0]);
        txtCostoOcoclimas.setText(arrayAux[3]);
        txtNombrePersonalOcoclimas.setText(arrayAux[1]);
    }

    @FXML
    private void seleccionarVehiculo(ActionEvent event) {
        VistaCatalogosController controlador = new VistaCatalogosController();
        controlador.abrirCatalogosPrincipal("Vehiculos");
        arrayAux = controlador.getDatosARetornar().split("\\|");
        txtIDVehiculo.setText(arrayAux[0]);
        txtTipoVehiculo.setText(arrayAux[1]);
        txtSucursalVehiculo.setText(arrayAux[2]);
        txtGastoGasolina.setText(arrayAux[3]);
    }

    @FXML
    private void seleccionarServicioGenerico(ActionEvent event) {
        VistaCatalogosController controlador = new VistaCatalogosController();
        controlador.abrirCatalogosPrincipal("Servicios genéricos");
        arrayAux = controlador.getDatosARetornar().split("\\|");
        txtIDServicioGenericos.setText(arrayAux[0]);
        txtDescripcionGenericos.setText(arrayAux[1]);
        txtCostoGenericos.setText(arrayAux[2]);

    }

    @FXML
    private void seleccionarProducto(ActionEvent event) {
        VistaCatalogosController controlador = new VistaCatalogosController();
        controlador.abrirCatalogosPrincipal("Productos");
        arrayAux = controlador.getDatosARetornar().split("\\|");
        txtIDProducto.setText(arrayAux[0]);
        txtDescripcion1.setText(arrayAux[2]);
        txtNombreCorto.setText(arrayAux[1]);
        txtUnidad.setText(arrayAux[3]);
        txtCosto1.setText(arrayAux[4]);
        txtDescuento1.setText(arrayAux[5]);
        txtClase.setText(arrayAux[6]);
    }

    @FXML
    private void seleccionarServicioOcoclimas(ActionEvent event) {
    }

}
