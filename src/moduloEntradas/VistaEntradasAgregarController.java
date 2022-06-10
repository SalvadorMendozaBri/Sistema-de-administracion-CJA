/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moduloEntradas;

import Catalogos.Proveedores;
import Catalogos.VistaCatalogosController;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
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
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import moduloSalidas.ProductoSalidas;
import moduloSalidas.Salidas;
import ococlimassystem.Conexion;

/**
 * FXML Controller class
 *
 * @author chava
 */
public class VistaEntradasAgregarController implements Initializable {

    @FXML
    private Button botonSeleccionarProducto;
    @FXML
    private Button botonSEleccionarProveedor;
    @FXML
    private Button botonGuardar;
    @FXML
    private Button botonCancelar;
    @FXML
    private DatePicker campo_fecha;
    @FXML
    private ComboBox<String> comboMotivo;
    @FXML
    private TextField txtProveedor;
    @FXML
    private TextField txtProducto;
    @FXML
    private TextField txtReferencia;
    @FXML
    private TextField txtFolio;
    @FXML
    private TextField txtCantidad;
    @FXML
    private Button botonAgregarProducto;
    @FXML
    private TextField txtCosto;
    @FXML
    private TextField txtBuscar;
    @FXML
    private TextArea txtObservaciones;
    @FXML
    private TextField txtRecibio;
    @FXML
    private TableView tablaProductos;
    @FXML
    private TableColumn columnaID;
    @FXML
    private TableColumn columnaNombreCorto;
    @FXML
    private TableColumn columnaDescripcion;
    @FXML
    private TableColumn columnaUnidad;
    @FXML
    private TableColumn columnaCantidad;
    @FXML
    private TableColumn columnaCosto;
    @FXML
    private Button botonEliminar;
    @FXML
    private Button botonModificar;
    @FXML
    private TableColumn columnaTipo;
    @FXML
    private Label labelProducto;
    @FXML
    private TextField txtProyecto;
    @FXML
    private Button botonSEleccionarProyecto;

    boolean isModificando;
    ProductoSalidas productoAModificar;
    Entradas entradaAModificar;
    ObservableList listaEntradas;
    ObservableList<ProductoSalidas> listaProductosEntradas;
    FilteredList<ProductoSalidas> listaFiltrada;
    String arrayAuxProducto[];
    String arrayAuxProveedor[];
    String arrayAuxProyecto[];
    String motivoApertura;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void inicializarColumnas() {
        listaProductosEntradas = FXCollections.observableArrayList();
        columnaID.setCellValueFactory(new PropertyValueFactory("id"));
        columnaNombreCorto.setCellValueFactory(new PropertyValueFactory("nombreCorto"));
        columnaDescripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
        columnaUnidad.setCellValueFactory(new PropertyValueFactory("unidad"));
        columnaCantidad.setCellValueFactory(new PropertyValueFactory("cantidad"));
        columnaCosto.setCellValueFactory(new PropertyValueFactory("costo"));
        columnaTipo.setCellValueFactory(new PropertyValueFactory("tipo"));
        tablaProductos.setItems(listaProductosEntradas);
    }

    public void initAttributes(ObservableList listaEntradas) {

        Conexion con = new Conexion();

        ResultSet rs;
        Statement st;

        try {
            st = con.getCon().createStatement();
            rs = st.executeQuery("SELECT `AUTO_INCREMENT`\n"
                    + "FROM  INFORMATION_SCHEMA.TABLES\n"
                    + "WHERE TABLE_SCHEMA = 'ocsmodular'\n"
                    + "AND   TABLE_NAME   = 'entradas'");
            rs.next();
            txtFolio.setText(rs.getInt(1) + "");

            con.getCon().close();

        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERROR");
            alerta.setHeaderText("Error al realizar una consulta en la base de datos");
            alerta.setContentText(e.toString());
            alerta.showAndWait();
        }
        motivoApertura = "Agregar";
        inicializarColumnas();

        comboMotivo.getItems().addAll("Surtir almacen", "Almacenar materiales para proyecto", "Devolución de proveedor");
        comboMotivo.getSelectionModel().select(0);
        //deshabilita por defecto el campo de proyectos
        txtProyecto.setDisable(true);
        botonSEleccionarProyecto.setDisable(true);

        campo_fecha.setValue(LocalDate.now());
        campo_fecha.setEditable(false);
        campo_fecha.setDisable(true);
        botonEliminar.setGraphic(new ImageView(new Image("/iconos/iconoBotonEliminar.png", 25, 25, false, true)));
        botonModificar.setGraphic(new ImageView(new Image("/iconos/iconoBotonModificar.png", 20, 20, false, true)));
        botonEliminar.setDisable(true);
        botonModificar.setDisable(true);

        this.listaEntradas = listaEntradas;

        listaFiltrada = new FilteredList(listaProductosEntradas);
        tablaProductos.setItems(listaFiltrada);

        txtBuscar.textProperty().addListener((Observable, oldValue, newValue) -> {
            listaFiltrada = null;
            listaFiltrada = listaProductosEntradas.filtered((new Predicate<ProductoSalidas>() {
                @Override
                public boolean test(ProductoSalidas t) {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    if (t.getId().contains(newValue)) {
                        return true;
                    }
                    if (t.getNombreCorto().contains(newValue)) {
                        return true;
                    }
                    if (t.getDescripcion().contains(newValue)) {
                        return true;
                    }
                    if (t.getUnidad().contains(newValue)) {
                        return true;
                    }
                    if (Double.toString(t.getCantidad()).contains(newValue)) {
                        return true;
                    }
                    if (Double.toString(t.getCosto()).contains(newValue)) {
                        return true;
                    }
                    if (t.getTipo().contains(newValue)) {
                        return true;
                    }

                    return false;
                }

            }));
            tablaProductos.setItems(listaFiltrada);
            tablaProductos.refresh();
        });
    }

    public void initAttributesModificar(Entradas entradaAModificar) {
        motivoApertura = "Modificar";
        inicializarColumnas();

        comboMotivo.getItems().addAll("Surtir almacen", "Almacenar materiales para proyecto", "Devolución de proveedor");
        comboMotivo.getSelectionModel().select(0);
        campo_fecha.setValue(LocalDate.now());
        campo_fecha.setEditable(false);
        campo_fecha.setDisable(true);
        botonEliminar.setGraphic(new ImageView(new Image("/iconos/iconoBotonEliminar.png", 25, 25, false, true)));
        botonModificar.setGraphic(new ImageView(new Image("/iconos/iconoBotonModificar.png", 20, 20, false, true)));
        botonEliminar.setDisable(true);
        botonModificar.setDisable(true);

        this.entradaAModificar = entradaAModificar;

        Conexion con = new Conexion();
        Statement st;
        ResultSet rs;

        try {

            st = con.getCon().createStatement();
            rs = st.executeQuery("SELECT FOLIO, FECHA, RECIBIO, REFERENCIA, MOTIVO, OBSERVACIONES, PROVEEDORES.NOMBRE, PROYECTOS.NOMBRE FROM ENTRADAS,PROYECTOS, PROVEEDORES "
                    + "WHERE PROYECTOS.ID = ENTRADAS.PROYECTO AND ENTRADAS.CODIGO_PROVEEDOR = PROVEEDORES.CODIGO AND ENTRADAS.FOLIO = '" + entradaAModificar.getFolio() + "'");

            rs.next();
            txtFolio.setText(rs.getString(1));
            campo_fecha.setValue(rs.getDate(2).toLocalDate());
            txtRecibio.setText(rs.getString(3));
            txtReferencia.setText(rs.getString(4));
            comboMotivo.getSelectionModel().select(rs.getString(5));
            txtObservaciones.setText(rs.getString(6));
            txtProveedor.setText(rs.getString(7));
            txtProyecto.setText(rs.getString(8));

            st = con.getCon().createStatement();
            ResultSet rsProductos = st.executeQuery("SELECT IP.ID_PRODUCTO, P.NOMBRE_CORTO, P.DESCRIPCION,U.DATO,IP.CANTIDAD, IP.COSTO "
                    + "FROM INTRODUCE_PRODUCTOS AS IP, PRODUCTOS AS P, UNIDADES_TIPOS AS U WHERE IP.ID_PRODUCTO = P.ID AND P.CLASE = U.ID AND IP.FOLIO_ENTRADA= '" + entradaAModificar.getFolio() + "'");

            while (rsProductos.next()) {
                listaProductosEntradas.add(new ProductoSalidas(rsProductos.getString(1), rsProductos.getString(2), rsProductos.getString(3), rsProductos.getString(4), rsProductos.getDouble(5), rsProductos.getDouble(6), "Producto"));
            }

            st = con.getCon().createStatement();
            ResultSet rsHrtas = st.executeQuery("SELECT IH.ID_HRTA, H.NOMBRE_CORTO, H.DESCRIPCION,IH.CANTIDAD, IH.COSTO "
                    + "FROM INTRODUCE_HERRAMIENTAS AS IH, HERRAMIENTAS AS H, UNIDADES_TIPOS AS U WHERE IH.ID_HRTA = H.CODIGO AND H.CLASE = U.ID AND IH.FOLIO_ENTRADA= '" + entradaAModificar.getFolio() + "'");

            while (rsHrtas.next()) {
                listaProductosEntradas.add(new ProductoSalidas(rsHrtas.getString(1), rsHrtas.getString(2), rsHrtas.getString(3), "No aplica", rsHrtas.getDouble(4), rsHrtas.getDouble(5), "Herramienta"));
            }

            con.getCon().close();
        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERROR");
            alerta.setHeaderText("Error al realizar una consulta en la base de datos");
            alerta.setContentText(e.toString());
            alerta.showAndWait();
        }

        listaFiltrada = new FilteredList(listaProductosEntradas);
        tablaProductos.setItems(listaFiltrada);

        txtBuscar.textProperty().addListener((Observable, oldValue, newValue) -> {
            listaFiltrada = null;
            listaFiltrada = listaProductosEntradas.filtered((new Predicate<ProductoSalidas>() {
                @Override
                public boolean test(ProductoSalidas t) {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    if (t.getId().contains(newValue)) {
                        return true;
                    }
                    if (t.getNombreCorto().contains(newValue)) {
                        return true;
                    }
                    if (t.getDescripcion().contains(newValue)) {
                        return true;
                    }
                    if (t.getUnidad().contains(newValue)) {
                        return true;
                    }
                    if (Double.toString(t.getCantidad()).contains(newValue)) {
                        return true;
                    }
                    if (Double.toString(t.getCosto()).contains(newValue)) {
                        return true;
                    }
                    if (t.getTipo().contains(newValue)) {
                        return true;
                    }

                    return false;
                }

            }));
            tablaProductos.setItems(listaFiltrada);
            tablaProductos.refresh();
        });


        //tablaProductos.setItems(listaProductosEntradas);
    }

    public void abrirVistaEntradasAgregar(ObservableList listaEntradas) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/moduloEntradas/VistaEntradasAgregar.fxml"));
            Parent root = loader.load();
            VistaEntradasAgregarController controlador = loader.getController();
            controlador.initAttributes(listaEntradas);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.setTitle("Agregar entradas");
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

    public void abrirVistaEntradasModificar(Entradas entradaAModificar) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/moduloEntradas/VistaEntradasAgregar.fxml"));
            Parent root = loader.load();
            VistaEntradasAgregarController controlador = loader.getController();
            controlador.initAttributesModificar(entradaAModificar);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.setTitle("Agregar entradas");
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
    private void guardar(ActionEvent event) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Confirmación");
        alerta.setHeaderText(null);
        alerta.setContentText("¿Desea guardar los cambios?");
        alerta.showAndWait();

        String opcion = alerta.getResult().toString();
        if (opcion.contains("Aceptar")) {
            switch (motivoApertura) {
                case "Agregar": {
                    String folio = txtFolio.getText();
                    LocalDate date = campo_fecha.getValue();
                    Date fecha = Date.valueOf(date);
                    String proveedor = txtProveedor.getText();
                    String recibio = txtRecibio.getText();
                    String referencia = txtReferencia.getText();
                    String motivo = comboMotivo.getSelectionModel().getSelectedItem();
                    String observaciones = txtObservaciones.getText();

                    Conexion con = new Conexion();
                    ResultSet rs;
                    Statement st;

                    try {
                        String proyecto = "1";
                        if (arrayAuxProyecto != null) {
                            proyecto = arrayAuxProyecto[0];
                        }

                        st = con.getCon().createStatement();
                        st.executeUpdate("INSERT INTO ENTRADAS (FOLIO, FECHA, RECIBIO, REFERENCIA, MOTIVO, OBSERVACIONES, CODIGO_PROVEEDOR, PROYECTO) VALUES"
                                + "(DEFAULT,'" + fecha.toString() + "','" + recibio + "','" + referencia + "','" + motivo + "','" + observaciones + "','" + arrayAuxProveedor[0] + "','" + proyecto + "')");

                        int i = 0;
                        while (i < listaProductosEntradas.size()) {
                            String tipo = listaProductosEntradas.get(i).getTipo();
                            ProductoSalidas aux = listaProductosEntradas.get(i);

                            switch (tipo) {
                                case "Producto": {
                                    st = con.getNewStatement(con.getCon());
                                    st.executeUpdate("INSERT INTO REQUIEREN (FOLIO_ENTRADA,ID_PRODUCTO,CODIGO_HRTA,TIPO) VALUES ('" + folio + "','" + listaProductosEntradas.get(i).getId() + "','1','Producto')");

                                    st = con.getNewStatement(con.getCon());
                                    ResultSet rsValidacion = st.executeQuery("SELECT * FROM PRODUCTOS_INVENTARIO WHERE ID='" + aux.getId() + "'");
                                    boolean isEnInventario = rsValidacion.next();

                                    if (!isEnInventario) {
                                        st = con.getNewStatement(con.getCon());
                                        st.executeUpdate("INSERT INTO PRODUCTOS_INVENTARIO (ID,COSTO, EXISTENCIA) VALUES('" + aux.getId() + "','" + aux.getCosto() + "','" + aux.getCantidad() + "')");
                                    }

                                    st = con.getNewStatement(con.getCon());
                                    st.executeUpdate("INSERT INTO INTRODUCE_PRODUCTOS (FOLIO_ENTRADA,ID_PRODUCTO,COSTO,CANTIDAD) VALUES ('" + folio + "','" + aux.getId() + "','" + aux.getCosto() + "','" + aux.getCantidad() + "')");

                                    if (isEnInventario) {
                                        st = con.getNewStatement(con.getCon());
                                        st.executeUpdate("UPDATE PRODUCTOS_INVENTARIO SET COSTO = (SELECT AVG(COSTO) FROM INTRODUCE_PRODUCTOS WHERE ID_PRODUCTO='" + aux.getId() + "' GROUP BY ID_PRODUCTO),"
                                                + "EXISTENCIA = (SELECT SUM(CANTIDAD) FROM INTRODUCE_PRODUCTOS WHERE ID_PRODUCTO ='" + aux.getId() + "' GROUP BY ID_PRODUCTO ) WHERE ID='" + aux.getId() + "'");
                                    }
                                }
                                break;
                                case "Herramienta": {
                                    st = con.getNewStatement(con.getCon());
                                    st.executeUpdate("INSERT INTO REQUIEREN (FOLIO_ENTRADA,ID_PRODUCTO,CODIGO_HRTA, TIPO) VALUES ('" + folio + "','1','" + listaProductosEntradas.get(i).getId() + "','Herramienta')");

                                    st = con.getNewStatement(con.getCon());
                                    ResultSet rsValidacion = st.executeQuery("SELECT * FROM HERRAMIENTAS_INVENTARIO WHERE ID='" + aux.getId() + "'");
                                    boolean isEnInventario = rsValidacion.next();

                                    if (!isEnInventario) {
                                        st = con.getNewStatement(con.getCon());
                                        st.executeUpdate("INSERT INTO HERRAMIENTAS_INVENTARIO (ID,COSTO, EXISTENCIA) VALUES('" + aux.getId() + "','" + aux.getCosto() + "','" + aux.getCantidad() + "')");
                                    }

                                    st = con.getNewStatement(con.getCon());
                                    st.executeUpdate("INSERT INTO INTRODUCE_HERRAMIENTAS (FOLIO_ENTRADA,ID_HRTA,COSTO,CANTIDAD) VALUES ('" + folio + "','" + aux.getId() + "','" + aux.getCosto() + "','" + aux.getCantidad() + "')");

                                    if (isEnInventario) {
                                        st = con.getNewStatement(con.getCon());
                                        st.executeUpdate("UPDATE HERRAMIENTAS_INVENTARIO SET COSTO = (SELECT AVG(COSTO) FROM INTRODUCE_HERRAMIENTAS WHERE ID_HRTA='" + aux.getId() + "' GROUP BY ID_HRTA),"
                                                + "EXISTENCIA = (SELECT SUM(CANTIDAD) FROM INTRODUCE_HERRAMIENTAS WHERE ID_HRTA ='" + aux.getId() + "' GROUP BY ID_HRTA) WHERE ID='" + aux.getId() + "'");
                                    }
                                }
                                break;
                                default:

                            }
                            i++;
                        }
                        con.getCon().close();

                    } catch (Exception e) {
                        Alert alerta2 = new Alert(Alert.AlertType.ERROR);
                        alerta2.setTitle("ERROR");
                        alerta2.setHeaderText("Error al realizar una consulta en la base de datos");
                        alerta2.setContentText(e.toString());
                        alerta2.showAndWait();
                    }

                    listaEntradas.add(new Entradas(folio, listaProductosEntradas.size() + " Articulos", fecha, proveedor, recibio, referencia, observaciones));

                    Alert alertaGuardar = new Alert(Alert.AlertType.INFORMATION);
                    alertaGuardar.setTitle("Datos guardados");
                    alertaGuardar.setHeaderText(null);
                    alertaGuardar.setContentText("Los datos han sido guardardos correctamente");
                    alertaGuardar.showAndWait();

                    //Restablecer todos los datos. 
                    Conexion con2 = new Conexion();

                    ResultSet rs2;
                    Statement st2;

                    try {
                        st2 = con2.getCon().createStatement();
                        rs2 = st2.executeQuery("SELECT `AUTO_INCREMENT`\n"
                                + "FROM  INFORMATION_SCHEMA.TABLES\n"
                                + "WHERE TABLE_SCHEMA = 'ocsmodular'\n"
                                + "AND   TABLE_NAME   = 'entradas'");
                        rs2.next();
                        txtFolio.setText(rs2.getInt(1) + "");

                        con2.getCon().close();

                    } catch (Exception e) {
                        Alert alerta2 = new Alert(Alert.AlertType.ERROR);
                        alerta2.setTitle("ERROR");
                        alerta2.setHeaderText("Error al realizar una consulta en la base de datos");
                        alerta2.setContentText(e.toString());
                        alerta2.showAndWait();
                    }
                    txtReferencia.setText("");
                    txtProveedor.setText("");
                    txtRecibio.setText("");
                    txtProducto.setText("");
                    txtCantidad.setText("");
                    txtCosto.setText("");
                    txtObservaciones.setText("");
                    txtProyecto.setText("");
                    listaProductosEntradas.clear();
                }
                break;
                case "Modificar": {

                    Conexion con = new Conexion();

                    Statement st;
                    ResultSet rs;

                    try {
                        st = con.getCon().createStatement();
                        rs = st.executeQuery("SELECT CODIGO_PROVEEDOR, PROYECTO FROM ENTRADAS WHERE FOLIO = '" + entradaAModificar.getFolio() + "'");
                        rs.next();
                        String proveedor = rs.getString(1);
                        String proyecto = rs.getString(2);

                        if (arrayAuxProveedor != null) {
                            proveedor = arrayAuxProveedor[0];
                        }
                        if (arrayAuxProyecto != null) {
                            proyecto = arrayAuxProyecto[0];
                        }
                        st = con.getCon().createStatement();

                        st.executeUpdate("UPDATE ENTRADAS SET FECHA = '" + campo_fecha.getValue().toString() + "', RECIBIO = '" + txtRecibio.getText() + "', REFERENCIA = '" + txtReferencia.getText() + "',"
                                + "MOTIVO = '" + comboMotivo.getSelectionModel().getSelectedItem() + "',OBSERVACIONES = '" + txtObservaciones.getText() + "', CODIGO_PROVEEDOR = '" + proveedor + "',PROYECTO = '" + proyecto + "' WHERE FOLIO= '" + entradaAModificar.getFolio() + "'");

                        st = con.getCon().createStatement();
                        st.executeUpdate("DELETE FROM REQUIEREN WHERE FOLIO_ENTRADA = " + entradaAModificar.getFolio());

                        st = con.getCon().createStatement();
                        st.executeUpdate("DELETE FROM INTRODUCE_PRODUCTOS WHERE FOLIO_ENTRADA = " + entradaAModificar.getFolio());

                        st = con.getCon().createStatement();
                        st.executeUpdate("DELETE FROM INTRODUCE_HERRAMIENTAS WHERE FOLIO_ENTRADA = " + entradaAModificar.getFolio());

                        int i = 0;
                        while (i < listaProductosEntradas.size()) {
                            String tipo = listaProductosEntradas.get(i).getTipo();
                            ProductoSalidas aux = listaProductosEntradas.get(i);

                            switch (tipo) {
                                case "Producto": {
                                    st = con.getNewStatement(con.getCon());
                                    st.executeUpdate("INSERT INTO REQUIEREN (FOLIO_ENTRADA,ID_PRODUCTO,CODIGO_HRTA,TIPO) VALUES ('" + entradaAModificar.getFolio() + "','" + listaProductosEntradas.get(i).getId() + "','1','Producto')");

                                    st = con.getNewStatement(con.getCon());
                                    ResultSet rsValidacion = st.executeQuery("SELECT * FROM PRODUCTOS_INVENTARIO WHERE ID='" + aux.getId() + "'");
                                    boolean isEnInventario = rsValidacion.next();

                                    if (!isEnInventario) {
                                        st = con.getNewStatement(con.getCon());
                                        st.executeUpdate("INSERT INTO PRODUCTOS_INVENTARIO (ID,COSTO, EXISTENCIA) VALUES('" + aux.getId() + "','" + aux.getCosto() + "','" + aux.getCantidad() + "')");
                                    }

                                    st = con.getNewStatement(con.getCon());
                                    st.executeUpdate("INSERT INTO INTRODUCE_PRODUCTOS (FOLIO_ENTRADA,ID_PRODUCTO,COSTO,CANTIDAD) VALUES ('" + entradaAModificar.getFolio() + "','" + aux.getId() + "','" + aux.getCosto() + "','" + aux.getCantidad() + "')");

                                    if (isEnInventario) {
                                        st = con.getNewStatement(con.getCon());
                                        st.executeUpdate("UPDATE PRODUCTOS_INVENTARIO SET COSTO = (SELECT AVG(COSTO) FROM INTRODUCE_PRODUCTOS WHERE ID_PRODUCTO='" + aux.getId() + "' GROUP BY ID_PRODUCTO),"
                                                + "EXISTENCIA = (SELECT SUM(CANTIDAD) FROM INTRODUCE_PRODUCTOS WHERE ID_PRODUCTO ='" + aux.getId() + "' GROUP BY ID_PRODUCTO ) WHERE ID='" + aux.getId() + "'");
                                    }
                                }
                                break;
                                case "Herramienta": {
                                    st = con.getNewStatement(con.getCon());
                                    st.executeUpdate("INSERT INTO REQUIEREN (FOLIO_ENTRADA,ID_PRODUCTO,CODIGO_HRTA, TIPO) VALUES ('" + entradaAModificar.getFolio() + "','1','" + listaProductosEntradas.get(i).getId() + "','Herramienta')");

                                    st = con.getNewStatement(con.getCon());
                                    ResultSet rsValidacion = st.executeQuery("SELECT * FROM HERRAMIENTAS_INVENTARIO WHERE ID='" + aux.getId() + "'");
                                    boolean isEnInventario = rsValidacion.next();

                                    if (!isEnInventario) {
                                        st = con.getNewStatement(con.getCon());
                                        st.executeUpdate("INSERT INTO HERRAMIENTAS_INVENTARIO (ID,COSTO, EXISTENCIA) VALUES('" + aux.getId() + "','" + aux.getCosto() + "','" + aux.getCantidad() + "')");
                                    }

                                    st = con.getNewStatement(con.getCon());
                                    st.executeUpdate("INSERT INTO INTRODUCE_HERRAMIENTAS (FOLIO_ENTRADA,ID_HRTA,COSTO,CANTIDAD) VALUES ('" + entradaAModificar.getFolio() + "','" + aux.getId() + "','" + aux.getCosto() + "','" + aux.getCantidad() + "')");

                                    if (isEnInventario) {
                                        st = con.getNewStatement(con.getCon());
                                        st.executeUpdate("UPDATE HERRAMIENTAS_INVENTARIO SET COSTO = (SELECT AVG(COSTO) FROM INTRODUCE_HERRAMIENTAS WHERE ID_HRTA='" + aux.getId() + "' GROUP BY ID_HRTA),"
                                                + "EXISTENCIA = (SELECT SUM(CANTIDAD) FROM INTRODUCE_HERRAMIENTAS WHERE ID_HRTA ='" + aux.getId() + "' GROUP BY ID_HRTA) WHERE ID='" + aux.getId() + "'");
                                    }
                                }
                                break;
                                default:

                            }
                            i++;
                        }

                        con.getCon().close();

                    } catch (Exception e) {
                        Alert alerta2 = new Alert(Alert.AlertType.ERROR);
                        alerta2.setTitle("ERROR");
                        alerta2.setHeaderText("Error al realizar una consulta en la base de datos");
                        alerta2.setContentText(e.toString());
                        alerta2.showAndWait();
                    }

                    entradaAModificar.setRecibio(txtRecibio.getText());
                    entradaAModificar.setFolio(txtFolio.getText());
                    entradaAModificar.setObservaciones(txtObservaciones.getText());
                    entradaAModificar.setProveedor(txtProveedor.getText());
                    entradaAModificar.setReferencia(txtReferencia.getText());

                    Stage stage = (Stage) botonGuardar.getScene().getWindow();
                    stage.close();

                }
                break;
                default:

            }

        }

    }

    @FXML
    private void cancelar(ActionEvent event) {

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
    private void seleccionarProveedorKeyTyped(KeyEvent event) {

        if (event.getCode().toString().equalsIgnoreCase("ENTER")) {
            VistaCatalogosController controlador = new VistaCatalogosController();
            controlador.abrirCatalogosPrincipal("Proveedores");
            arrayAuxProveedor = controlador.getDatosARetornar().split("\\|");
            txtProveedor.setText(arrayAuxProveedor[1]);
        }

    }

    @FXML
    private void seleccionarProductoKeyTyped(KeyEvent event) {

        if (event.getCode().toString().equalsIgnoreCase("ENTER")) {
            VistaCatalogosController controlador = new VistaCatalogosController();
            controlador.abrirCatalogosPrincipal("Productos y herramientas");
            arrayAuxProducto = controlador.getDatosARetornar().split("\\|");

            if (!arrayAuxProducto[5].equalsIgnoreCase("Producto")) {
                txtCosto.setEditable(false);
                labelProducto.setText("Herramienta:");
            } else {
                txtCosto.setEditable(false);
                labelProducto.setText("Producto:");
            }

            txtProducto.setText(arrayAuxProducto[1]);
        }
    }

    @FXML
    private void seleccionarProveedor(ActionEvent event) {
        VistaCatalogosController controlador = new VistaCatalogosController();
        controlador.abrirCatalogosPrincipal("Proveedores");
        arrayAuxProveedor = controlador.getDatosARetornar().split("\\|");
        txtProveedor.setText(arrayAuxProveedor[1]);

        //La posicion 0 del array se guardara en una variable global para su conexion con la base de datos.
    }

    @FXML
    private void seleccionarProducto(ActionEvent event) {
        VistaCatalogosController controlador = new VistaCatalogosController();
        controlador.abrirCatalogosPrincipal("Productos y herramientas");
        arrayAuxProducto = controlador.getDatosARetornar().split("\\|");

        if (!arrayAuxProducto[5].equalsIgnoreCase("Producto")) {
            txtCosto.setEditable(false);
            labelProducto.setText("Herramienta:");
        } else {
            txtCosto.setEditable(false);
            labelProducto.setText("Producto:");
        }

        txtProducto.setText(arrayAuxProducto[1]);
        txtCosto.setText(arrayAuxProducto[4]);

        //La posicion 0 del array se guardara en una variable global para su conexion con la base de datos.
    }

    @FXML
    private void agregarProducto(ActionEvent event) {
        if (!(txtProducto.getText().isEmpty()) && txtProducto.getText() != null) {
            try {
                if (!isModificando) {

                    if (!buscarBinaria(arrayAuxProducto[0])) {
                        ProductoSalidas aux = new ProductoSalidas(arrayAuxProducto[0], arrayAuxProducto[1], arrayAuxProducto[2], arrayAuxProducto[3], Double.parseDouble(txtCantidad.getText()), Double.parseDouble(txtCosto.getText()), arrayAuxProducto[5]);
                        listaProductosEntradas.add(aux);
                    } else {
                        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                        alerta.setTitle("Duplicidad");
                        alerta.setHeaderText(null);
                        alerta.setContentText("Este elemento ya se encuentra en la lista");
                        alerta.showAndWait();
                    }

                    txtProducto.setText("");
                    txtCantidad.setText("");
                    txtCosto.setText("");
                } else {//Modificar productos

                    productoAModificar.setId(arrayAuxProducto[0]);
                    productoAModificar.setNombreCorto(arrayAuxProducto[1]);
                    productoAModificar.setDescripcion(arrayAuxProducto[2]);
                    productoAModificar.setUnidad(arrayAuxProducto[3]);
                    productoAModificar.setCantidad(Double.parseDouble(txtCantidad.getText()));
                    productoAModificar.setCosto(Double.parseDouble(txtCosto.getText()));
                    tablaProductos.refresh();
                    isModificando = false;
                    botonAgregarProducto.setText("Agregar producto");

                    txtProducto.setText("");
                    txtCantidad.setText("");
                    txtCosto.setText("");

                }
            } catch (NumberFormatException e) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Formato de costo o cantidad incorrecto");
                alerta.setHeaderText(null);
                alerta.setContentText("El costo y la cantidad deben de ser valores numericos");
                alerta.showAndWait();
            }
        }
    }

    public boolean buscarBinaria(String codigo) {
        int inicio = 0;
        int fin = listaProductosEntradas.size() - 1;
        int pos;
        while (inicio <= fin) {
            pos = (inicio + fin) / 2;
            if (listaProductosEntradas.get(pos).getId().equalsIgnoreCase(codigo)) {
                if (listaProductosEntradas.get(pos).getTipo().equalsIgnoreCase(arrayAuxProducto[5])) {
                    return true;
                } else {
                    return false;
                }
            } else if (listaProductosEntradas.get(pos).getId().compareTo(codigo) < 0) {
                inicio = pos + 1;
            } else {
                fin = pos - 1;
            }
        }
        return false;
    }

    @FXML
    private void seleccionarElementoMouseClicked(MouseEvent event) {
        if (tablaProductos.getSelectionModel().getSelectedItem() != null) {
            botonEliminar.setDisable(false);
            botonModificar.setDisable(false);
        }
    }

    @FXML
    private void eliminarProductoTabla(ActionEvent event) {
        if (tablaProductos.getSelectionModel().getSelectedItem() != null) {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Confirmación");
            alerta.setHeaderText(null);
            alerta.setContentText("¿Seguro que quiere eliminar este elemento?");
            alerta.showAndWait();

            String opcion = alerta.getResult().toString();
            if (opcion.contains("Aceptar")) {
                int indice = tablaProductos.getSelectionModel().getSelectedIndex();
                listaProductosEntradas.remove(indice);
            }

        }
    }

    @FXML
    private void modificarProductoTabla(ActionEvent event) {
        if (tablaProductos.getSelectionModel().getSelectedItem() != null) {
            productoAModificar = (ProductoSalidas) tablaProductos.getSelectionModel().getSelectedItem();
            txtProducto.setText(productoAModificar.getNombreCorto());
            txtCantidad.setText(Double.toString(productoAModificar.getCantidad()));
            txtCosto.setText(Double.toString(productoAModificar.getCosto()));
            arrayAuxProducto = new String[5];
            arrayAuxProducto[0] = productoAModificar.getId();
            arrayAuxProducto[1] = productoAModificar.getNombreCorto();
            arrayAuxProducto[2] = productoAModificar.getDescripcion();
            arrayAuxProducto[3] = productoAModificar.getUnidad();
            System.out.println("producto a modificar en modificar " + productoAModificar);
            botonAgregarProducto.setText("Modificar");
            isModificando = true;
        }
    }

    private void validarSeleccion(ActionEvent event) {
        String seleccion = comboMotivo.getSelectionModel().getSelectedItem();

        switch (seleccion) {
            case "Devolucion de proyecto":
                txtProveedor.setDisable(true);
                botonSEleccionarProveedor.setDisable(true);
                txtProveedor.setText("");
                break;
            default:
                txtProveedor.setDisable(false);
                botonSEleccionarProveedor.setDisable(false);
        }
    }

    @FXML
    private void seleccionarProyectoKeyTyped(KeyEvent event) {

        if (event.getCode().toString().equalsIgnoreCase("ENTER")) {
            VistaCatalogosController controlador = new VistaCatalogosController();
            controlador.abrirCatalogosPrincipal("Proyectos");
            arrayAuxProyecto = controlador.getDatosARetornar().split("\\|");
            txtProyecto.setText(arrayAuxProyecto[1]);

        }
    }

    @FXML
    private void seleccionarProyecto(ActionEvent event) {
        VistaCatalogosController controlador = new VistaCatalogosController();
        controlador.abrirCatalogosPrincipal("Proyectos");
        arrayAuxProyecto = controlador.getDatosARetornar().split("\\|");
        txtProyecto.setText(arrayAuxProyecto[1]);

    }

    @FXML
    private void validarSeleccionComboMotivo(ActionEvent event) {
        String seleccion = comboMotivo.getSelectionModel().getSelectedItem();

        switch (seleccion) {
            case "Almacenar materiales para proyecto":
                txtProyecto.setDisable(false);
                botonSEleccionarProyecto.setDisable(false);
                break;
            default:
                txtProyecto.setDisable(true);
                txtProyecto.setText("");
                botonSEleccionarProyecto.setDisable(true);

        }
    }

}
