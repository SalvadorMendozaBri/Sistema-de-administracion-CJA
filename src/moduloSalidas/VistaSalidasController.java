/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moduloSalidas;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.net.URL;
import java.sql.Date;
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
import moduloEntradas.Entradas;
import moduloEntradas.VistaEntradasAgregarController;
import ococlimassystem.Conexion;

/**
 * FXML Controller class
 *
 * @author chava
 */
public class VistaSalidasController implements Initializable {

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
    private TableView tablaSalidas;
    @FXML
    private TableColumn columnaFolio;
    @FXML
    private TableColumn columnaProductos;
    @FXML
    private TableColumn columnaFecha;
    @FXML
    private TableColumn columnaEntrego;
    @FXML
    private TableColumn columnaReferencia;

    ObservableList<Salidas> listaSalidas;
    private FilteredList listaFiltrada;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image imagen = new Image("/iconos/Salidas.png");
        icono.setImage(imagen);

        BackgroundPosition b = new BackgroundPosition(Side.RIGHT, 0, true, Side.BOTTOM, 0, true);
        BackgroundImage bi = new BackgroundImage(new Image("Fondo2.png", 1894, 1420, false, true, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, b, BackgroundSize.DEFAULT);
        hbox.setBackground(new Background(bi));

        columnaFolio.setCellValueFactory(new PropertyValueFactory("folio"));
        columnaProductos.setCellValueFactory(new PropertyValueFactory("productos"));
        columnaFecha.setCellValueFactory(new PropertyValueFactory("fecha"));
        columnaEntrego.setCellValueFactory(new PropertyValueFactory("entrego"));
        columnaReferencia.setCellValueFactory(new PropertyValueFactory("referencia"));

        listaSalidas = FXCollections.observableArrayList();

        Conexion con = new Conexion();

        ResultSet rs;
        Statement st;

        try {
            st = con.getCon().createStatement();
            rs = st.executeQuery("SELECT FOLIO,FECHA,ENTREGO,REFERENCIA, MOTIVO, OBSERVACIONES, PROVEEDORES.NOMBRE FROM SALIDAS,PROVEEDORES  WHERE SALIDAS.PROVEEDOR = PROVEEDORES.CODIGO AND SALIDAS.IS_ELIMINATED IS NULL ORDER BY SALIDAS.FOLIO");

            while (rs.next()) {
                st = con.getNewStatement(con.getCon());
                ResultSet rsProductos = st.executeQuery("SELECT COUNT(*) FROM SALIDAS,SALEN_POR WHERE SALIDAS.FOLIO = SALEN_POR.FOLIO_SALIDA AND SALIDAS.FOLIO = '" + rs.getInt(1) + "'");
                rsProductos.next();
                String productos = rsProductos.getInt(1) + " Articulos";

                listaSalidas.add(new Salidas(rs.getString(1), productos, rs.getDate(2), rs.getString(3), rs.getString(4), rs.getString(6)));
            }

            con.getCon().close();

        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERROR");
            alerta.setHeaderText("Error al realizar una consulta en la base de datos");
            alerta.setContentText(e.toString());
            alerta.showAndWait();
        }

        listaFiltrada = new FilteredList(listaSalidas);
        tablaSalidas.setItems(listaFiltrada);

        txtBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
            listaFiltrada = null;
            listaFiltrada = listaSalidas.filtered(new Predicate<Salidas>() {
                @Override
                public boolean test(Salidas t) {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    if (t.getEntrego().contains(newValue)) {
                        return true;

                    }
                    if (t.getFecha().toString().contains(newValue)) {
                        return true;

                    }
                    if (t.getFolio().contains(newValue)) {
                        return true;
                    }
                    if (t.getObservaciones().contains(newValue)) {
                        return true;
                    }
                    if (t.getProductos().contains(newValue)) {
                        return true;
                    }
                    if (t.getReferencia().contains(newValue)) {
                        return true;
                    }
                    return false;
                }

            });
            tablaSalidas.setItems(listaFiltrada);
            tablaSalidas.refresh();
        });

        tablaSalidas.setItems(listaSalidas);

    }

    public void abrirVentanaSalidas() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/moduloSalidas/VistaSalidas2.0.fxml"));
            Parent root = loader.load();
            VistaSalidasController controlador = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setTitle("Salidas");
            stage.showAndWait();
        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText(e.toString());
            alerta.setContentText("No se ha encontrado el archivo");
            alerta.showAndWait();
        }

    }

    @FXML
    private void agregar(ActionEvent event) {
        new VistaSalidasAgregarController().abrirVentanaSalidasAgregar(listaSalidas);

    }

    @FXML
    private void modificar(ActionEvent event) {
        if (tablaSalidas.getSelectionModel().getSelectedItem() != null) {
            Salidas salidaAModificar = (Salidas) tablaSalidas.getSelectionModel().getSelectedItem();
            new VistaSalidasAgregarController().abrirVentanaSalidasModificar(salidaAModificar);
            tablaSalidas.refresh();
        }
    }

    @FXML
    private void eliminar(ActionEvent event) {
         if (tablaSalidas.getSelectionModel().getSelectedItem() != null) {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Confirmación");
            alerta.setHeaderText(null);
            alerta.setContentText("¿Seguro que quiere eliminar este elemento?");
            alerta.showAndWait();

            String opcion = alerta.getResult().toString();
            if (opcion.contains("Aceptar")) {

                Salidas salidaAEliminar = (Salidas) tablaSalidas.getSelectionModel().getSelectedItem();
                Conexion con = new Conexion();

                ResultSet rs;
                Statement st;

                try {

                    st = con.getCon().createStatement();
                    st.executeUpdate("DELETE FROM SALIDAS WHERE FOLIO='" + salidaAEliminar.getFolio() + "'");

                    st = con.getCon().createStatement();
                    st.executeUpdate("DELETE FROM SALEN_POR WHERE FOLIO_SALIDA='" + salidaAEliminar.getFolio() + "'");


                    con.getCon().close();

                } catch (MySQLIntegrityConstraintViolationException e) {
                    Conexion con2 = new Conexion();

                    ResultSet rs2;
                    Statement st2;

                    try {

                        st2 = con2.getNewStatement(con2.getCon());
                        st2.executeUpdate("UPDATE SALIDAS SET IS_ELIMINATED = '*' WHERE FOLIO = '" + salidaAEliminar.getFolio() + "'");

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

                listaSalidas.remove(salidaAEliminar);
            }
        }
    }

    @FXML
    private void salir(ActionEvent event) {
        Stage stage = (Stage) botonSalir.getScene().getWindow();
        stage.close();
    }

}
