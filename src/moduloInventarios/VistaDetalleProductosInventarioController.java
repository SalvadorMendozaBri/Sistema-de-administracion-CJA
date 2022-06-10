/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moduloInventarios;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import moduloEntradas.VistaEntradasController;
import ococlimassystem.Conexion;

/**
 * FXML Controller class
 *
 * @author chava
 */
public class VistaDetalleProductosInventarioController implements Initializable {

    @FXML
    private TextField txtBuscar;
    @FXML
    private TableView<ProductosInventarioDetalles> tablaDetalle;
    @FXML
    private TableColumn columnaNombreCorto;
    @FXML
    private TableColumn columnaCosto;
    @FXML
    private TableColumn columnaCantidad;
    @FXML
    private Button botonSeleccionar;
    @FXML
    private Button botonSalir;
    @FXML
    private TableColumn ColumnaID;
    @FXML
    private ComboBox<VistaDetalleProductosInventarioController> comboOculto;
    
    private boolean isOpenByPrincipal;
    private ProductosInventario productoSeleccionado;
    private ObservableList<ProductosInventarioDetalles> listaDetalles;
    private String datosAretornar;
    private String seleccion;
    private FilteredList<ProductosInventarioDetalles> listaFiltrada;
    
    public String getDatosAretornar() {
        return datosAretornar;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void initAttributes(ProductosInventario productoSeleccionado, String seleccion, VistaDetalleProductosInventarioController controlador, boolean isOpenByPrincipal) {
        listaDetalles = FXCollections.observableArrayList();
        this.productoSeleccionado = productoSeleccionado;
        this.seleccion = seleccion;
        this.isOpenByPrincipal = isOpenByPrincipal;
        comboOculto.getItems().add(controlador);

        ColumnaID.setCellValueFactory(new PropertyValueFactory("id"));
        columnaNombreCorto.setCellValueFactory(new PropertyValueFactory("nombreCorto"));
        columnaCosto.setCellValueFactory(new PropertyValueFactory("costo"));
        columnaCantidad.setCellValueFactory(new PropertyValueFactory("existencia"));

        switch (seleccion) {
            case "Productos": {
                Conexion con = new Conexion();

                ResultSet rs;
                Statement st;

                try {
                    st = con.getCon().createStatement();
                    rs = st.executeQuery("SELECT P.ID, P.NOMBRE_CORTO, IP.COSTO, SUM(IP.CANTIDAD) FROM PRODUCTOS AS P, INTRODUCE_PRODUCTOS AS IP WHERE P.ID = IP.ID_PRODUCTO AND IP.ID_PRODUCTO='" + productoSeleccionado.getId() + "' GROUP BY IP.COSTO");

                    while (rs.next()) {

                        listaDetalles.add(new ProductosInventarioDetalles(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4)));

                    }

                    con.getCon().close();

                } catch (Exception e) {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("ERROR");
                    alerta.setHeaderText("Error al realizar una consulta en la base de datos");
                    alerta.setContentText(e.toString());
                    alerta.showAndWait();
                }
                tablaDetalle.setItems(listaDetalles);

            }
            break;
            case "Herramientas": {
                Conexion con = new Conexion();

                ResultSet rs;
                Statement st;

                try {
                    st = con.getCon().createStatement();
                    rs = st.executeQuery("SELECT H.CODIGO, H.NOMBRE_CORTO, IH.COSTO, SUM(IH.CANTIDAD) FROM HERRAMIENTAS AS H, INTRODUCE_HERRAMIENTAS AS IH WHERE H.CODIGO = IH.ID_HRTA AND IH.ID_HRTA='" + productoSeleccionado.getId() + "' GROUP BY IH.COSTO");

                    while (rs.next()) {

                        listaDetalles.add(new ProductosInventarioDetalles(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4)));

                    }

                    con.getCon().close();

                } catch (Exception e) {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("ERROR");
                    alerta.setHeaderText("Error al realizar una consulta en la base de datos");
                    alerta.setContentText(e.toString());
                    alerta.showAndWait();
                }
                tablaDetalle.setItems(listaDetalles);
                tablaDetalle.refresh();

            }
            break;
            default:

        }

//        listaFiltrada = new FilteredList(listaDetalles);
//        tablaDetalle.setItems(listaFiltrada);
//
//        txtBuscar.textFormatterProperty().addListener((observable, oldValue, newV) -> {
//            listaFiltrada = null;
//            listaFiltrada = listaDetalles.filtered(new Predicate<ProductosInventarioDetalles>() {
//                @Override
//                public boolean test(ProductosInventarioDetalles t) {
//                    
//                    if (newV == null || newV.isEmpty()) {
//                        return true;
//
//                    }
//                    if (t.getId().contains(newV)) {
//                        return true;
//
//                    }
//                    if (t.getNombreCorto().contains(newV)) {
//                        return true;
//                    }
//                    if (Double.toString(t.getCosto()).contains(newV)) {
//                        return true;
//                    }
//                    if (Double.toString(t.getExistencia()).contains(newV)) {
//                        return true;
//                    }
//                    return false;
//                }
//
//            });
//            tablaDetalle.setItems(listaFiltrada);
//            tablaDetalle.refresh();
//
//        });

    }

    public void abrirVistaDetalleProductosInventario(ProductosInventario productoSeleccionado, String seleccion, boolean isOpenByPrincipal) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/moduloInventarios/VistaDetalleProductosInventario.fxml"));
            Parent root = loader.load();
            VistaDetalleProductosInventarioController controlador = loader.getController();
            controlador.initAttributes(productoSeleccionado, seleccion, controlador, isOpenByPrincipal);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setTitle("Detalle de productos en inventario");
            stage.showAndWait();
            this.datosAretornar = controlador.datosAretornar;

        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText(e.toString());
            alerta.setContentText("No se ha encontrado el archivo");
            alerta.showAndWait();
        }
    }

    @FXML
    private void seleccionar(ActionEvent event) {
    }

    @FXML
    private void salir(ActionEvent event) {
    }

    @FXML
    private void selecconarProductoMouseClicked(MouseEvent event) {
        comboOculto.getSelectionModel().select(0);
        VistaDetalleProductosInventarioController controladorAux = comboOculto.getSelectionModel().getSelectedItem();
        if (event.getClickCount() >= 2 && !controladorAux.isOpenByPrincipal) {
            ProductosInventarioDetalles aux = tablaDetalle.getSelectionModel().getSelectedItem();

            switch (controladorAux.seleccion) {
                case "Productos":
                    controladorAux.datosAretornar = aux.getId() + "|" + aux.getNombreCorto() + "|" + productoSeleccionado.getDescripcion() + "|" + productoSeleccionado.getUnidad() + "|" + aux.getExistencia() + "|" + aux.getCosto() + "|" + "Producto";
                    break;
                case "Herramientas":
                    controladorAux.datosAretornar = aux.getId() + "|" + aux.getNombreCorto() + "|" + productoSeleccionado.getDescripcion() + "|" + "No aplica" + "|" + aux.getExistencia() + "|" + aux.getCosto() + "|" + "Herramienta";
                default:

            }

            Stage stage = (Stage) botonSeleccionar.getScene().getWindow();
            stage.close();
        }

    }

}
