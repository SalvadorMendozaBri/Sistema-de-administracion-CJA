/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moduloEntradas;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
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
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import moduloSalidas.Salidas;
import moduloSalidas.VistaSalidasAgregarController;
import ococlimassystem.Conexion;

/**
 * FXML Controller class
 *
 * @author chava
 */
public class VistaEntradasController implements Initializable {

    @FXML
    private Button botonAgregar;
    @FXML
    private Button botonModificar;
    @FXML
    private Button botonEliminar;
    @FXML
    private ImageView icono;
    @FXML
    private HBox hbox;
    @FXML
    private Button botonSalir;
    @FXML
    private TextField txtBuscar;
    @FXML
    private TableColumn columnaFolio;
    @FXML
    private TableColumn columnaProducto;
    @FXML
    private TableColumn columnaFecha;
    @FXML
    private TableColumn columnaProveedor;
    @FXML
    private TableColumn columnaRecibio;
    @FXML
    private TableColumn columnaReferencia;
    @FXML
    private TableView tablaEntradas;

    ObservableList listaEntradas;
    private FilteredList listaFiltrada;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void initAttributes() {
        Image imagen = new Image("/iconos/Entradas.png");
        icono.setImage(imagen);

        BackgroundPosition b = new BackgroundPosition(Side.RIGHT, 0, true, Side.BOTTOM, 0, true);
        BackgroundImage bi = new BackgroundImage(new Image("Fondo2.png", 1894, 1420, false, true, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, b, BackgroundSize.DEFAULT);
        hbox.setBackground(new Background(bi));

        listaEntradas = FXCollections.observableArrayList();
        columnaFolio.setCellValueFactory(new PropertyValueFactory("folio"));
        columnaProducto.setCellValueFactory(new PropertyValueFactory("productos"));
        columnaFecha.setCellValueFactory(new PropertyValueFactory("fecha"));
        columnaProveedor.setCellValueFactory(new PropertyValueFactory("proveedor"));
        columnaRecibio.setCellValueFactory(new PropertyValueFactory("recibio"));
        columnaReferencia.setCellValueFactory(new PropertyValueFactory("referencia"));

        TableColumn columnasEntradas[] = {columnaFolio, columnaProducto, columnaFecha, columnaProveedor, columnaRecibio, columnaReferencia};
        tablaEntradas.getColumns().setAll(columnasEntradas);

        listaFiltrada = new FilteredList(listaEntradas);
        tablaEntradas.setItems(listaFiltrada);

        txtBuscar.textProperty().addListener((Observable, oldValue, newValue) -> {
            listaFiltrada = null;
            listaFiltrada = listaEntradas.filtered((new Predicate<Entradas>() {
                @Override
                public boolean test(Entradas t) {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    if (t.getFolio().contains(newValue)) {
                        return true;
                    }
                    if (t.getProductos().contains(newValue)) {
                        return true;
                    }
                    if (t.getFecha().toString().contains(newValue)) {
                        return true;
                    }
                    if (t.getProveedor().contains(newValue)) {
                        return true;
                    }
                    if (t.getRecibio().contains(newValue)) {
                        return true;
                    }
                    if (t.getReferencia().contains(newValue)) {
                        return true;
                    }
                    return false;
                }

            }));
            tablaEntradas.setItems(listaFiltrada);
            tablaEntradas.refresh();
        });

        Conexion con = new Conexion();

        ResultSet rs;
        Statement st;

        try {
            st = con.getCon().createStatement();
            rs = st.executeQuery("SELECT FOLIO,FECHA,RECIBIO,REFERENCIA, MOTIVO, OBSERVACIONES, PROVEEDORES.NOMBRE FROM ENTRADAS,PROVEEDORES WHERE ENTRADAS.CODIGO_PROVEEDOR = PROVEEDORES.CODIGO AND ENTRADAS.IS_ELIMINATED IS NULL ORDER BY ENTRADAS.FOLIO");

            while (rs.next()) {
                st = con.getNewStatement(con.getCon());
                ResultSet rsProductos = st.executeQuery("SELECT COUNT(*) FROM ENTRADAS,REQUIEREN WHERE ENTRADAS.FOLIO = REQUIEREN.FOLIO_ENTRADA AND ENTRADAS.FOLIO = '" + rs.getInt(1) + "'");
                rsProductos.next();
                String productos = rsProductos.getInt(1) + " Articulos";

                listaEntradas.add(new Entradas(Integer.toString(rs.getInt(1)), productos, rs.getDate(2), rs.getString(7), rs.getString(3), rs.getString(4), rs.getString(6)));
            }

            con.getCon().close();

        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERROR");
            alerta.setHeaderText("Error al realizar una consulta en la base de datos");
            alerta.setContentText(e.toString());
            alerta.showAndWait();
        }
    }

    public void abrirVentanaEntradas() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/moduloEntradas/VistaEntradas3.0.fxml"));
            Parent root = loader.load();
            VistaEntradasController controlador = loader.getController();
            controlador.initAttributes();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setTitle("Entradas");
            stage.showAndWait();

        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText(e.toString());
            alerta.setContentText("No se ha encontrado el archivo");
        }
    }

    @FXML
    private void agregar(ActionEvent event) {
        new VistaEntradasAgregarController().abrirVistaEntradasAgregar(listaEntradas);

    }

    @FXML
    private void modificar(ActionEvent event) {
        if (tablaEntradas.getSelectionModel().getSelectedItem() != null) {
            Entradas entradaAModificar = (Entradas) tablaEntradas.getSelectionModel().getSelectedItem();
            new VistaEntradasAgregarController().abrirVistaEntradasModificar(entradaAModificar);
            tablaEntradas.refresh();
        }
    }

    @FXML
    private void eliminar(ActionEvent event) {
        if (tablaEntradas.getSelectionModel().getSelectedItem() != null) {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Confirmación");
            alerta.setHeaderText(null);
            alerta.setContentText("¿Seguro que quiere eliminar este elemento?");
            alerta.showAndWait();

            String opcion = alerta.getResult().toString();
            if (opcion.contains("Aceptar")) {

                Entradas entradaAEliminar = (Entradas) tablaEntradas.getSelectionModel().getSelectedItem();
                Conexion con = new Conexion();

                ResultSet rs;
                Statement st;

                try {

                    st = con.getCon().createStatement();
                    st.executeUpdate("DELETE FROM ENTRADAS WHERE FOLIO='" + entradaAEliminar.getFolio() + "'");

                    st = con.getCon().createStatement();
                    st.executeUpdate("DELETE FROM REQUIEREN WHERE FOLIO_ENTRADA='" + entradaAEliminar.getFolio() + "'");

                    st = con.getCon().createStatement();
                    st.executeUpdate("DELETE FROM INTRODUCE_HERRAMIENTAS WHERE FOLIO_ENTRADA='" + entradaAEliminar.getFolio() + "'");

                    st = con.getCon().createStatement();
                    st.executeUpdate("DELETE FROM INTRODUCE_PRODUCTOS WHERE FOLIO_ENTRADA='" + entradaAEliminar.getFolio() + "'");

                    con.getCon().close();

                } catch (MySQLIntegrityConstraintViolationException e) {
                    Conexion con2 = new Conexion();

                    ResultSet rs2;
                    Statement st2;

                    try {

                        st2 = con2.getNewStatement(con2.getCon());
                        st2.executeUpdate("UPDATE ENTRADAS SET IS_ELIMINATED = '*' WHERE FOLIO = '" + entradaAEliminar.getFolio() + "'");

                        con.getCon().close();

                    } catch (Exception ex) {
                        Alert alerta2 = new Alert(Alert.AlertType.ERROR);
                        alerta2.setTitle("ERROR");
                        alerta2.setHeaderText("Error al realizar una eliminacion logica");
                        alerta2.setContentText(e.toString());
                        alerta2.showAndWait();
                    }
                } catch (Exception e) {

                    Alert alerta2 = new Alert(Alert.AlertType.ERROR);
                    alerta2.setTitle("ERROR");
                    alerta2.setHeaderText("Error al realizar una consulta en la base de datos");
                    alerta2.setContentText(e.toString());
                    alerta2.showAndWait();
                }

                listaEntradas.remove(entradaAEliminar);
            }
        }

    }

    @FXML
    private void salir(ActionEvent event) {
        Stage stage = (Stage) botonSalir.getScene().getWindow();
        stage.close();
    }

}
