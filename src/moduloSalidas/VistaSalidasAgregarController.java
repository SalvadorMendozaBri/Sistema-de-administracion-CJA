package moduloSalidas;

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
import moduloInventarios.VistaInventariosController;
import ococlimassystem.Conexion;

/**
 * FXML Controller class
 *
 * @author chava
 */
public class VistaSalidasAgregarController implements Initializable {

    @FXML
    private Button botonSeleccionarProducto;
    @FXML
    private Button botonGuardar;
    @FXML
    private Button botonCancelar;
    @FXML
    private ComboBox<String> comboMotivo;
    @FXML
    private DatePicker campo_fecha;
    @FXML
    private TextField txtProducto;
    @FXML
    private TextField txtReferencia;
    @FXML
    private TextField txtFolio;
    @FXML
    private TextField txtCantidad;
    @FXML
    private TextField txtCosto;
    @FXML
    private TextField txtBuscar;
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
    private TextArea txtObservaciones;
    @FXML
    private TextField txtEntrego;
    @FXML
    private Button botonAgregarProducto;
    @FXML
    private Button botonEliminar;
    @FXML
    private Button botonModificar;
    @FXML
    private Label labelProducto;
    @FXML
    private TableColumn columnaTipo;
    @FXML
    private TextField txtProveedor;
    @FXML
    private TextField txtProyecto;
    @FXML
    private Button botonSeleccionarProveedor;
    @FXML
    private Button botonSeleccionarProyecto;
    @FXML
    private Label labelCantidad;

    boolean isModificando;
    FilteredList<ProductoSalidas> listaFiltrada;
    ProductoSalidas productoAModificar;
    Salidas salidaAModificar;
    ObservableList listaSalidas;
    ObservableList<ProductoSalidas> listaProductosSalidas;
    String[] arrayAuxProducto;
    String[] arrayAuxProveedor;
    String[] arrayAuxProyecto;
    String motivoApertura;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void inicializarColumnas() {
        listaProductosSalidas = FXCollections.observableArrayList();
        columnaID.setCellValueFactory(new PropertyValueFactory("id"));
        columnaNombreCorto.setCellValueFactory(new PropertyValueFactory("nombreCorto"));
        columnaDescripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
        columnaUnidad.setCellValueFactory(new PropertyValueFactory("unidad"));
        columnaCantidad.setCellValueFactory(new PropertyValueFactory("cantidad"));
        columnaCosto.setCellValueFactory(new PropertyValueFactory("costo"));
        columnaTipo.setCellValueFactory(new PropertyValueFactory("tipo"));
        tablaProductos.setItems(listaProductosSalidas);
    }

    public void initAttributes(ObservableList listaSalidas) {
        motivoApertura = "Agregar";
        inicializarColumnas();

        Conexion con = new Conexion();

        ResultSet rs;
        Statement st;

        try {
            st = con.getCon().createStatement();
            rs = st.executeQuery("SELECT `AUTO_INCREMENT`\n"
                    + "FROM  INFORMATION_SCHEMA.TABLES\n"
                    + "WHERE TABLE_SCHEMA = 'ocsmodular'\n"
                    + "AND   TABLE_NAME   = 'salidas'");
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

        comboMotivo.getItems().addAll("Surtir proyecto", "Devolución de proveedor");
        comboMotivo.getSelectionModel().select(0);

        txtProveedor.setDisable(true);
        txtProveedor.setText("");
        botonSeleccionarProveedor.setDisable(true);
        txtProyecto.setDisable(false);
        botonSeleccionarProyecto.setDisable(false);

        campo_fecha.setValue(LocalDate.now());
        campo_fecha.setEditable(false);
        campo_fecha.setDisable(true);

        botonEliminar.setGraphic(new ImageView(new Image("/iconos/iconoBotonEliminar.png", 25, 25, false, true)));
        botonModificar.setGraphic(new ImageView(new Image("/iconos/iconoBotonModificar.png", 20, 20, false, true)));
        botonEliminar.setDisable(true);
        botonModificar.setDisable(true);

        this.listaSalidas = listaSalidas;

        listaFiltrada = new FilteredList(listaProductosSalidas);
        tablaProductos.setItems(listaFiltrada);

        txtBuscar.textProperty().addListener((Obsercable, oldValue, newValue) -> {
            listaFiltrada = null;
            listaFiltrada = listaProductosSalidas.filtered(new Predicate<ProductoSalidas>() {
                @Override
                public boolean test(ProductoSalidas t) {
                    if (newValue == null || newValue.isEmpty()) {

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
            });
            tablaProductos.setItems(listaFiltrada);
            tablaProductos.refresh();
        });

    }

    public void initAttributesModificar(Salidas salidaAModificar) {
        motivoApertura = "Modificar";
        inicializarColumnas();

        comboMotivo.getItems().addAll("Surtir proyecto", "Devolución de proyecto", "Devolución de proveedor");
        comboMotivo.getSelectionModel().select(0);
        campo_fecha.setValue(LocalDate.now());
        campo_fecha.setEditable(false);
        campo_fecha.setDisable(true);

        botonEliminar.setGraphic(new ImageView(new Image("/iconos/iconoBotonEliminar.png", 25, 25, false, true)));
        botonModificar.setGraphic(new ImageView(new Image("/iconos/iconoBotonModificar.png", 20, 20, false, true)));
        botonEliminar.setDisable(true);
        botonModificar.setDisable(true);

        this.salidaAModificar = salidaAModificar;

        Conexion con = new Conexion();
        Statement st;
        ResultSet rs;

        try {

            st = con.getCon().createStatement();
            rs = st.executeQuery("SELECT FOLIO, FECHA, ENTREGO, REFERENCIA, MOTIVO, OBSERVACIONES, PROVEEDORES.NOMBRE, PROYECTOS.NOMBRE FROM SALIDAS,PROYECTOS, PROVEEDORES "
                    + "WHERE PROYECTOS.ID = SALIDAS.PROYECTO AND SALIDAS.PROVEEDOR = PROVEEDORES.CODIGO AND SALIDAS.FOLIO = '" + salidaAModificar.getFolio() + "'");

            rs.next();
            txtFolio.setText(rs.getString(1));
            campo_fecha.setValue(rs.getDate(2).toLocalDate());
            txtEntrego.setText(rs.getString(3));
            txtReferencia.setText(rs.getString(4));
            comboMotivo.getSelectionModel().select(rs.getString(5));
            txtObservaciones.setText(rs.getString(6));
            txtProveedor.setText(rs.getString(7));
            txtProyecto.setText(rs.getString(8));

            st = con.getCon().createStatement();             //                 1                   2                   3                       4               5               6               7               8                       9                           10                      11
            ResultSet rsProductos = st.executeQuery("SELECT SP.ID_PRODUCTO_I, SP.ID_HRTA, P.NOMBRE_CORTO, P.DESCRIPCION,U.DATO,SP.CANTIDAD, SP.COSTO, SP.TIPO AS TIPO, H.NOMBRE_CORTO , H.DESCRIPCION, SP.CANTIDAD_TOTAL "
                    + "FROM SALEN_POR AS SP, PRODUCTOS AS P, UNIDADES_TIPOS AS U, HERRAMIENTAS AS H  WHERE SP.ID_PRODUCTO_I = P.ID AND P.CLASE = U.ID AND H.CODIGO = SP.ID_HRTA AND SP.FOLIO_SALIDA= '" + salidaAModificar.getFolio() + "'");

            while (rsProductos.next()) {
                String tipo = rsProductos.getString(8);
                System.out.println("Tipo: " + tipo);

                switch (tipo) {
                    case "Producto":
                        listaProductosSalidas.add(new ProductoSalidas(rsProductos.getString(1), rsProductos.getString(3), rsProductos.getString(4), rsProductos.getString(5), rsProductos.getDouble(6), rsProductos.getDouble(7), tipo, rsProductos.getDouble(11)));
                        break;
                    case "Herramienta":
                        listaProductosSalidas.add(new ProductoSalidas(rsProductos.getString(2), rsProductos.getString(9), rsProductos.getString(10), "No aplica", rsProductos.getDouble(6), rsProductos.getDouble(7), tipo, rsProductos.getDouble(11)));
                        break;
                    default:

                }
            }

            con.getCon().close();
        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERROR");
            alerta.setHeaderText("Error al realizar una consulta en la base de datos");
            alerta.setContentText(e.toString());
            alerta.showAndWait();
        }

        listaFiltrada = new FilteredList(listaProductosSalidas);
        tablaProductos.setItems(listaFiltrada);

        txtBuscar.textProperty().addListener((Obsercable, oldValue, newValue) -> {
            listaFiltrada = null;
            listaFiltrada = listaProductosSalidas.filtered(new Predicate<ProductoSalidas>() {
                @Override
                public boolean test(ProductoSalidas t) {
                    if (newValue == null || newValue.isEmpty()) {

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
            });
            tablaProductos.setItems(listaFiltrada);
            tablaProductos.refresh();
        });

        txtEntrego.setText(salidaAModificar.getEntrego());
        txtFolio.setText(salidaAModificar.getFolio());
        txtObservaciones.setText(salidaAModificar.getObservaciones());
        txtReferencia.setText(salidaAModificar.getReferencia());
    }

    public void abrirVentanaSalidasAgregar(ObservableList listaSalidas) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/moduloSalidas/VistaSalidasAgregar.fxml"));
            Parent root = loader.load();
            VistaSalidasAgregarController controlador = loader.getController();
            controlador.initAttributes(listaSalidas);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.setTitle("Agregar salidas");
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

    public void abrirVentanaSalidasModificar(Salidas salidaAModificar) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/moduloSalidas/VistaSalidasAgregar.fxml"));
            Parent root = loader.load();
            VistaSalidasAgregarController controlador = loader.getController();
            controlador.initAttributesModificar(salidaAModificar);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.setTitle("Modificar salidas");
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
                    String entrego = txtEntrego.getText();
                    String referencia = txtReferencia.getText();
                    String observaciones = txtObservaciones.getText();
                    String motivo = comboMotivo.getSelectionModel().getSelectedItem();

                    Conexion con = new Conexion();

                    Statement st;

                    try {
                        st = con.getNewStatement(con.getCon());
                        String proyecto = "1";
                        String proveedor = "1";
                        if (arrayAuxProyecto != null) {
                            proyecto = arrayAuxProyecto[0];
                        }
                        if (arrayAuxProveedor != null) {
                            proveedor = arrayAuxProveedor[0];
                        }

                        st.executeUpdate("INSERT INTO SALIDAS (FOLIO, FECHA, ENTREGO, REFERENCIA, MOTIVO, OBSERVACIONES, PROYECTO, PROVEEDOR) "
                                + "VALUES ('" + folio + "','" + date.toString() + "','" + entrego + "','" + referencia + "','" + motivo + "','" + observaciones + "','" + proyecto + "','" + proveedor + "')");

                        int i = 0;
                        while (i < listaProductosSalidas.size()) {
                            ProductoSalidas aux = listaProductosSalidas.get(i);
                            String seleccion = aux.getTipo();
                            switch (seleccion) {
                                case "Producto":

                                    //Introduccion de regsitros en salen por 
                                    st = con.getNewStatement(con.getCon());
                                    st.executeUpdate("INSERT INTO SALEN_POR (FOLIO_SALIDA, ID_PRODUCTO_I, ID_HRTA,CANTIDAD, TIPO, COSTO, CANTIDAD_TOTAL) VALUES "
                                            + "('" + folio + "','" + aux.getId() + "','1','" + aux.getCantidad() + "','Producto','" + aux.getCosto() + "',(SELECT EXISTENCIA FROM PRODUCTOS_INVENTARIO WHERE ID='" + aux.getId() + "'))");

                                    //Se reduce la cantidad de producto que salio enel registro de la tabla de introduce productos
                                    st = con.getNewStatement(con.getCon());
                                    st.executeUpdate("UPDATE INTRODUCE_PRODUCTOS SET CANTIDAD = CANTIDAD - " + aux.getCantidad() + " WHERE ID_PRODUCTO = '" + aux.getId() + "' AND COSTO = '" + aux.getCosto() + "'");

                                    st = con.getNewStatement(con.getCon());
                                    ResultSet rs = st.executeQuery("SELECT CANTIDAD FROM INTRODUCE_PRODUCTOS WHERE ID_PRODUCTO = '" + aux.getId() + "' AND COSTO = '" + aux.getCosto() + "'");

                                    rs.next();
                                    //Controla si despues de la salida, los productos en inventario con ese precio se agotaron
                                    if (rs.getInt(1) <= 0) {
                                        st = con.getNewStatement(con.getCon());
                                        st.executeUpdate("DELETE FROM INTRODUCE_PRODUCTOS WHERE ID_PRODUCTO = '" + aux.getId() + "' AND COSTO = '" + aux.getCosto() + "'");

                                        //Controla si las existencias de ese producto se agortaron por completo, en cualquier precio
                                        st = con.getNewStatement(con.getCon());
                                        ResultSet rsUltimo = st.executeQuery("SELECT * FROM INTRODUCE_PRODUCTOS WHERE ID_PRODUCTO = '" + aux.getId() + "'");
                                        if (!rsUltimo.next()) {
                                            st = con.getNewStatement(con.getCon());
                                            st.executeUpdate("DELETE FROM PRODUCTOS_INVENTARIO WHERE ID = '" + aux.getId() + "'");
                                        } else {
                                            st = con.getNewStatement(con.getCon());
                                            st.executeUpdate("UPDATE PRODUCTOS_INVENTARIO SET COSTO = (SELECT AVG(COSTO) FROM INTRODUCE_PRODUCTOS WHERE ID_PRODUCTO='" + aux.getId() + "' GROUP BY ID_PRODUCTO),"
                                                    + "EXISTENCIA = (SELECT SUM(CANTIDAD) FROM INTRODUCE_PRODUCTOS WHERE ID_PRODUCTO ='" + aux.getId() + "' GROUP BY ID_PRODUCTO ) WHERE ID='" + aux.getId() + "'");
                                        }

                                    } else {
                                        st = con.getNewStatement(con.getCon());
                                        st.executeUpdate("UPDATE PRODUCTOS_INVENTARIO SET COSTO = (SELECT AVG(COSTO) FROM INTRODUCE_PRODUCTOS WHERE ID_PRODUCTO='" + aux.getId() + "' GROUP BY ID_PRODUCTO),"
                                                + "EXISTENCIA = (SELECT SUM(CANTIDAD) FROM INTRODUCE_PRODUCTOS WHERE ID_PRODUCTO ='" + aux.getId() + "' GROUP BY ID_PRODUCTO ) WHERE ID='" + aux.getId() + "'");
                                    }

                                    break;
                                case "Herramienta":
                                    st = con.getNewStatement(con.getCon());
                                    st.executeUpdate("INSERT INTO SALEN_POR (FOLIO_SALIDA, ID_PRODUCTO_I, ID_HRTA,CANTIDAD, TIPO, COSTO, CANTIDAD_TOTAL) VALUES "
                                            + "('" + folio + "','1','" + aux.getId() + "','" + aux.getCantidad() + "','Herramienta','" + aux.getCosto() + "',(SELECT EXISTENCIA FROM HERRAMIENTAS_INVENTARIO WHERE ID='" + aux.getId() + "'))");

                                    st = con.getNewStatement(con.getCon());
                                    st.executeUpdate("UPDATE INTRODUCE_HERRAMIENTAS SET CANTIDAD = CANTIDAD - " + aux.getCantidad() + " WHERE ID_HRTA = '" + aux.getId() + "' AND COSTO = '" + aux.getCosto() + "'");

                                    st = con.getNewStatement(con.getCon());
                                    ResultSet rsCantidadHrta = st.executeQuery("SELECT CANTIDAD FROM INTRODUCE_HERRAMIENTAS WHERE ID_HRTA = '" + aux.getId() + "' AND COSTO = '" + aux.getCosto() + "'");

                                    rsCantidadHrta.next();
                                    //Controla si despues de la salida, los productos en inventario con ese precio se agotaron
                                    if (rsCantidadHrta.getInt(1) <= 0) {
                                        st = con.getNewStatement(con.getCon());
                                        st.executeUpdate("DELETE FROM INTRODUCE_HERRAMIENTAS WHERE ID_HRTA = '" + aux.getId() + "' AND COSTO = '" + aux.getCosto() + "'");

                                        //Controla si las existencias de ese producto se agortaron por completo, en cualquier precio
                                        st = con.getNewStatement(con.getCon());
                                        ResultSet rsUltimo = st.executeQuery("SELECT * FROM INTRODUCE_HERRAMIENTAS WHERE ID_HRTA = '" + aux.getId() + "'");

                                        if (!rsUltimo.next()) {
                                            st = con.getNewStatement(con.getCon());
                                            st.executeUpdate("DELETE FROM HERRAMIENTAS_INVENTARIO WHERE ID = '" + aux.getId() + "'");
                                        } else {
                                            st = con.getNewStatement(con.getCon());
                                            st.executeUpdate("UPDATE HERRAMIENTAS_INVENTARIO SET COSTO = (SELECT AVG(COSTO) FROM INTRODUCE_HERRAMIENTAS WHERE ID_HRTA='" + aux.getId() + "' GROUP BY ID_HRTA),"
                                                    + "EXISTENCIA = (SELECT SUM(CANTIDAD) FROM INTRODUCE_HERRAMIENTAS WHERE ID_HRTA ='" + aux.getId() + "' GROUP BY ID_HRTA ) WHERE ID='" + aux.getId() + "'");
                                        }

                                    } else {
                                        st = con.getNewStatement(con.getCon());
                                        st.executeUpdate("UPDATE HERRAMIENTAS_INVENTARIO SET COSTO = (SELECT AVG(COSTO) FROM INTRODUCE_HERRAMIENTAS WHERE ID_HRTA='" + aux.getId() + "' GROUP BY ID_HRTA),"
                                                + "EXISTENCIA = (SELECT SUM(CANTIDAD) FROM INTRODUCE_HERRAMIENTAS WHERE ID_HRTA ='" + aux.getId() + "' GROUP BY ID_HRTA ) WHERE ID='" + aux.getId() + "'");
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

                    listaSalidas.add(new Salidas(folio, listaProductosSalidas.size() + " Articulos", fecha, entrego, referencia, observaciones));

                    Alert alertaGuardar = new Alert(Alert.AlertType.INFORMATION);
                    alertaGuardar.setTitle("Datos guardados");
                    alertaGuardar.setHeaderText(null);
                    alertaGuardar.setContentText("Los datos han sido guardardos correctamente");
                    alertaGuardar.showAndWait();

                    //Restablecer todos los datos. 
                    txtFolio.setText("");
                    txtReferencia.setText("");
                    txtEntrego.setText("");
                    txtProducto.setText("");
                    txtCantidad.setText("");
                    txtCosto.setText("");
                    txtObservaciones.setText("");

                }
                break;
                case "Modificar": {
                    String folio = salidaAModificar.getFolio();
                    Conexion con = new Conexion();

                    Statement st;
                    ResultSet rs;

                    try {
                        st = con.getCon().createStatement();
                        rs = st.executeQuery("SELECT PROVEEDOR, PROYECTO FROM SALIDAS WHERE FOLIO = '" + salidaAModificar.getFolio() + "'");
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

                        st.executeUpdate("UPDATE SALIDAS SET FECHA = '" + campo_fecha.getValue().toString() + "', ENTREGO = '" + txtEntrego.getText() + "', REFERENCIA = '" + txtReferencia.getText() + "',"
                                + "MOTIVO = '" + comboMotivo.getSelectionModel().getSelectedItem() + "',OBSERVACIONES = '" + txtObservaciones.getText() + "', PROVEEDOR = '" + proveedor + "',PROYECTO = '" + proyecto + "' WHERE FOLIO= '" + salidaAModificar.getFolio() + "'");

                        st = con.getCon().createStatement();                //          1               2               3                 4           5           6   
                        ResultSet rsProductos = st.executeQuery("SELECT ID_PRODUCTO_I, ID_HRTA, CANTIDAD, TIPO, COSTO, CANTIDAD_TOTAL FROM SALEN_POR WHERE FOLIO_SALIDA = '"+salidaAModificar.getFolio()+"'");

                        //Devolucion de los registros a las tablas de introduce productos
                        while (rsProductos.next()) {
                            String tipo = rsProductos.getString(4);
                            switch (tipo) {
                                case "Producto": {
                                    st = con.getCon().createStatement();                            //  1               2           3   
                                    ResultSet rsComprobacion = st.executeQuery("SELECT ID_PRODUCTO, COSTO, CANTIDAD FROM INTRODUCE_PRODUCTOS WHERE ID_PRODUCTO= '" + rsProductos.getString(1) + "' AND COSTO = '" + rsProductos.getDouble(5) + "'");

                                    if (rsComprobacion.next()) {
                                        st = con.getCon().createStatement();
                                        System.out.println("UPDATE INTRODUCE_PRODUCTOS SET CANTIDAD = CANTIDAD + " + rsProductos.getInt(3) + "  WHERE ID_PRODUCTO= '" + rsProductos.getString(1) + "' AND COSTO = '" + rsProductos.getDouble(5) + "'");
                                        st.executeUpdate("UPDATE INTRODUCE_PRODUCTOS SET CANTIDAD = CANTIDAD + " + rsProductos.getInt(3) + "  WHERE ID_PRODUCTO= '" + rsProductos.getString(1) + "' AND COSTO = '" + rsProductos.getDouble(5) + "'");
                                    } else {
                                        st = con.getCon().createStatement();
                                        st.executeUpdate("INSERT INTO INTRODUCE_PRODUCTOS(ID_PRODUCTO, FOLIO_ENTRADA, COSTO, CANTIDAD) VALUES ('" + rsProductos.getString(1) + "','1','" + rsProductos.getDouble(5) + "','" + rsProductos.getString(3) + "')");
                                    }
                                }
                                break;
                                case "Herramienta": {
                                    st = con.getCon().createStatement();                            //  1               2           3   
                                    ResultSet rsComprobacion = st.executeQuery("SELECT ID_HRTA, COSTO, CANTIDAD FROM INTRODUCE_HERRAMIENTAS WHERE ID_HRTA= '" + rsProductos.getString(2) + "' AND COSTO = '" + rsProductos.getDouble(5) + "'");

                                    if (rsComprobacion.next()) {
                                        st = con.getCon().createStatement();
                                        System.out.println("UPDATE INTRODUCE_HERRAMIENTAS SET CANTIDAD = CANTIDAD + " + rsProductos.getInt(3) + "  WHERE ID_HRTA= '" + rsProductos.getString(2) + "' AND COSTO = '" + rsProductos.getDouble(5) + "'");
                                        st.executeUpdate("UPDATE INTRODUCE_HERRAMIENTAS SET CANTIDAD = CANTIDAD + " + rsProductos.getInt(3) + "  WHERE ID_HRTA= '" + rsProductos.getString(2) + "' AND COSTO = '" + rsProductos.getDouble(5) + "'");
                                    } else {
                                        st = con.getCon().createStatement();
                                        st.executeUpdate("INSERT INTO INTRODUCE_HERRAMIENTAS (ID_HRTA, FOLIO_ENTRADA, COSTO, CANTIDAD) VALUES ('" + rsProductos.getString(2) + "','1','" + rsProductos.getDouble(5) + "','" + rsProductos.getString(3) + "')");
                                    }
                                }
                                break;
                                default:

                            }

                            st = con.getCon().createStatement();
                            st.executeUpdate("DELETE FROM SALEN_POR WHERE FOLIO_SALIDA = " + salidaAModificar.getFolio());

                            int i = 0;
                            while (i < listaProductosSalidas.size()) {
                                ProductoSalidas aux = listaProductosSalidas.get(i);
                                String seleccion = aux.getTipo();
                                switch (seleccion) {
                                    case "Producto":

                                        //Introduccion de regsitros en salen por 
                                        st = con.getNewStatement(con.getCon());
                                        st.executeUpdate("INSERT INTO SALEN_POR (FOLIO_SALIDA, ID_PRODUCTO_I, ID_HRTA,CANTIDAD, TIPO, COSTO, CANTIDAD_TOTAL) VALUES "
                                                + "('" + folio + "','" + aux.getId() + "','1','" + aux.getCantidad() + "','Producto','" + aux.getCosto() + "',(SELECT EXISTENCIA FROM PRODUCTOS_INVENTARIO WHERE ID='" + aux.getId() + "'))");

                                        //Se reduce la cantidad de producto que salio enel registro de la tabla de introduce productos
                                        st = con.getNewStatement(con.getCon());
                                        st.executeUpdate("UPDATE INTRODUCE_PRODUCTOS SET CANTIDAD = CANTIDAD - " + aux.getCantidad() + " WHERE ID_PRODUCTO = '" + aux.getId() + "' AND COSTO = '" + aux.getCosto() + "'");

                                        st = con.getNewStatement(con.getCon());
                                        ResultSet rsCantidad = st.executeQuery("SELECT CANTIDAD FROM INTRODUCE_PRODUCTOS WHERE ID_PRODUCTO = '" + aux.getId() + "' AND COSTO = '" + aux.getCosto() + "'");

                                        rsCantidad.next();
                                        //Controla si despues de la salida, los productos en inventario con ese precio se agotaron
                                        if (rsCantidad.getInt(1) <= 0) {
                                            st = con.getNewStatement(con.getCon());
                                            st.executeUpdate("DELETE FROM INTRODUCE_PRODUCTOS WHERE ID_PRODUCTO = '" + aux.getId() + "' AND COSTO = '" + aux.getCosto() + "'");

                                            //Controla si las existencias de ese producto se agortaron por completo, en cualquier precio
                                            st = con.getNewStatement(con.getCon());
                                            ResultSet rsUltimo = st.executeQuery("SELECT * FROM INTRODUCE_PRODUCTOS WHERE ID_PRODUCTO = '" + aux.getId() + "'");
                                            if (!rsUltimo.next()) {
                                                st = con.getNewStatement(con.getCon());
                                                st.executeUpdate("DELETE FROM PRODUCTOS_INVENTARIO WHERE ID = '" + aux.getId() + "'");
                                            } else {
                                                st = con.getNewStatement(con.getCon());
                                                st.executeUpdate("UPDATE PRODUCTOS_INVENTARIO SET COSTO = (SELECT AVG(COSTO) FROM INTRODUCE_PRODUCTOS WHERE ID_PRODUCTO='" + aux.getId() + "' GROUP BY ID_PRODUCTO),"
                                                        + "EXISTENCIA = (SELECT SUM(CANTIDAD) FROM INTRODUCE_PRODUCTOS WHERE ID_PRODUCTO ='" + aux.getId() + "' GROUP BY ID_PRODUCTO ) WHERE ID='" + aux.getId() + "'");
                                            }

                                        } else {
                                            st = con.getNewStatement(con.getCon());
                                            st.executeUpdate("UPDATE PRODUCTOS_INVENTARIO SET COSTO = (SELECT AVG(COSTO) FROM INTRODUCE_PRODUCTOS WHERE ID_PRODUCTO='" + aux.getId() + "' GROUP BY ID_PRODUCTO),"
                                                    + "EXISTENCIA = (SELECT SUM(CANTIDAD) FROM INTRODUCE_PRODUCTOS WHERE ID_PRODUCTO ='" + aux.getId() + "' GROUP BY ID_PRODUCTO ) WHERE ID='" + aux.getId() + "'");
                                        }

                                        break;
                                    case "Herramienta":
                                        st = con.getNewStatement(con.getCon());
                                        st.executeUpdate("INSERT INTO SALEN_POR (FOLIO_SALIDA, ID_PRODUCTO_I, ID_HRTA,CANTIDAD, TIPO, COSTO, CANTIDAD_TOTAL) VALUES "
                                                + "('" + folio + "','1','" + aux.getId() + "','" + aux.getCantidad() + "','Herramienta','" + aux.getCosto() + "',(SELECT EXISTENCIA FROM HERRAMIENTAS_INVENTARIO WHERE ID='" + aux.getId() + "'))");

                                        st = con.getNewStatement(con.getCon());
                                        st.executeUpdate("UPDATE INTRODUCE_HERRAMIENTAS SET CANTIDAD = CANTIDAD - " + aux.getCantidad() + " WHERE ID_HRTA = '" + aux.getId() + "' AND COSTO = '" + aux.getCosto() + "'");

                                        st = con.getNewStatement(con.getCon());
                                        ResultSet rsCantidadHrta = st.executeQuery("SELECT CANTIDAD FROM INTRODUCE_HERRAMIENTAS WHERE ID_HRTA = '" + aux.getId() + "' AND COSTO = '" + aux.getCosto() + "'");

                                        rsCantidadHrta.next();
                                        //Controla si despues de la salida, los productos en inventario con ese precio se agotaron
                                        if (rsCantidadHrta.getInt(1) <= 0) {
                                            st = con.getNewStatement(con.getCon());
                                            st.executeUpdate("DELETE FROM INTRODUCE_HERRAMIENTAS WHERE ID_HRTA = '" + aux.getId() + "' AND COSTO = '" + aux.getCosto() + "'");

                                            //Controla si las existencias de ese producto se agortaron por completo, en cualquier precio
                                            st = con.getNewStatement(con.getCon());
                                            ResultSet rsUltimo = st.executeQuery("SELECT * FROM INTRODUCE_HERRAMIENTAS WHERE ID_HRTA = '" + aux.getId() + "'");

                                            if (!rsUltimo.next()) {
                                                st = con.getNewStatement(con.getCon());
                                                st.executeUpdate("DELETE FROM HERRAMIENTAS_INVENTARIO WHERE ID = '" + aux.getId() + "'");
                                            } else {
                                                st = con.getNewStatement(con.getCon());
                                                st.executeUpdate("UPDATE HERRAMIENTAS_INVENTARIO SET COSTO = (SELECT AVG(COSTO) FROM INTRODUCE_HERRAMIENTAS WHERE ID_HRTA='" + aux.getId() + "' GROUP BY ID_HRTA),"
                                                        + "EXISTENCIA = (SELECT SUM(CANTIDAD) FROM INTRODUCE_HERRAMIENTAS WHERE ID_HRTA ='" + aux.getId() + "' GROUP BY ID_HRTA ) WHERE ID='" + aux.getId() + "'");
                                            }

                                        } else {
                                            st = con.getNewStatement(con.getCon());
                                            st.executeUpdate("UPDATE HERRAMIENTAS_INVENTARIO SET COSTO = (SELECT AVG(COSTO) FROM INTRODUCE_HERRAMIENTAS WHERE ID_HRTA='" + aux.getId() + "' GROUP BY ID_HRTA),"
                                                    + "EXISTENCIA = (SELECT SUM(CANTIDAD) FROM INTRODUCE_HERRAMIENTAS WHERE ID_HRTA ='" + aux.getId() + "' GROUP BY ID_HRTA ) WHERE ID='" + aux.getId() + "'");
                                        }
                                        break;
                                    default:

                                }

                                i++;
                            }
                        }

                    } catch (Exception e) {
                        Alert alerta2 = new Alert(Alert.AlertType.ERROR);
                        alerta2.setTitle("ERROR");
                        alerta2.setHeaderText("Error al realizar una consulta en la base de datos");
                        alerta2.setContentText(e.toString());
                        alerta2.showAndWait();
                    }

                    salidaAModificar.setEntrego(txtEntrego.getText());
                    salidaAModificar.setFolio(txtFolio.getText());
                    salidaAModificar.setObservaciones(txtObservaciones.getText());
                    salidaAModificar.setReferencia(txtReferencia.getText());

                    Stage stage = (Stage) botonGuardar.getScene().getWindow();
                    stage.close();

                }
                break;
                default:

            }

        }

    }

    public boolean buscarBinaria(String codigo) {
        int inicio = 0;
        int fin = listaProductosSalidas.size() - 1;
        int pos;
        while (inicio <= fin) {
            pos = (inicio + fin) / 2;
            if (listaProductosSalidas.get(pos).getId().equalsIgnoreCase(codigo)) {
                if (listaProductosSalidas.get(pos).getCosto() == Double.parseDouble(arrayAuxProducto[5])) {
                    return true;
                } else {
                    return false;
                }
            } else if (listaProductosSalidas.get(pos).getId().compareTo(codigo) < 0) {
                inicio = pos + 1;
            } else {
                fin = pos - 1;
            }
        }
        return false;
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
            VistaInventariosController controlador = new VistaInventariosController();
            controlador.abrirVentanaInventarios(false);
            arrayAuxProducto = controlador.getDatosARetornar().split("\\|");
            if (!arrayAuxProducto[6].equalsIgnoreCase("Producto")) {
                txtCosto.setEditable(false);
                labelProducto.setText("Herramienta:");
            } else {
                txtCosto.setEditable(false);
                labelProducto.setText("Producto:");
            }
            txtProducto.setText(arrayAuxProducto[1]);
            txtCosto.setText(arrayAuxProducto[5]);
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
        VistaInventariosController controlador = new VistaInventariosController();
        controlador.abrirVentanaInventarios(false);
        System.out.println(controlador.getDatosARetornar());
        arrayAuxProducto = controlador.getDatosARetornar().split("\\|");
        if (!arrayAuxProducto[6].equalsIgnoreCase("Producto")) {
            txtCosto.setEditable(false);
            labelProducto.setText("Herramienta:");
        } else {
            txtCosto.setEditable(false);
            labelProducto.setText("Producto:");
        }
        txtProducto.setText(arrayAuxProducto[1]);
        txtCosto.setText(arrayAuxProducto[5]);

        labelCantidad.setText("/" + arrayAuxProducto[4]);

//        La posicion 0 del array se guardara en una variable global para su conexion con la base de datos.
    }

    @FXML
    private void agregarProducto(ActionEvent event) {
        if (!(txtProducto.getText().isEmpty()) && txtProducto.getText() != null) {

            try {
                if (!isModificando) {

                    if (Double.parseDouble(txtCantidad.getText()) <= Double.parseDouble(arrayAuxProducto[4])) {
                        if (!buscarBinaria(arrayAuxProducto[0])) {
                            ProductoSalidas aux = new ProductoSalidas(arrayAuxProducto[0], arrayAuxProducto[1], arrayAuxProducto[2], arrayAuxProducto[3], Double.parseDouble(txtCantidad.getText()), Double.parseDouble(txtCosto.getText()), arrayAuxProducto[6], Double.parseDouble(arrayAuxProducto[4]));
                            listaProductosSalidas.add(aux);
                        } else {
                            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                            alerta.setTitle("Duplicidad");
                            alerta.setHeaderText(null);
                            alerta.setContentText("Este elemento ya se encuentra en la lista");
                            alerta.showAndWait();
                        }

                        txtProducto.setText("");
                        txtCantidad.setText("");
                        txtCosto.setText("");
                        labelCantidad.setText("/");
                    } else {
                        Alert alerta = new Alert(Alert.AlertType.ERROR);
                        alerta.setTitle("Cantidad demasiado elevada");
                        alerta.setHeaderText(null);
                        alerta.setContentText("Se ha digititado una cantidad de productos que excede al numero de productos existentes en inventario");
                        alerta.showAndWait();
                    }
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
                listaProductosSalidas.remove(indice);
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
            labelCantidad.setText("/" + productoAModificar.getCantidadTotal());
            isModificando = true;
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
            case "Surtir proyecto":
                txtProveedor.setDisable(true);
                txtProveedor.setText("");
                botonSeleccionarProveedor.setDisable(true);
                txtProyecto.setDisable(false);
                botonSeleccionarProyecto.setDisable(false);
                break;
            case "Devolución de proveedor":
                txtProveedor.setDisable(false);
                botonSeleccionarProveedor.setDisable(false);
                txtProyecto.setDisable(true);
                txtProyecto.setText("");
                botonSeleccionarProyecto.setDisable(true);

                break;
            default:
        }
    }

}
