/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moduloInventarios;

import Catalogos.Catalogos;
import Catalogos.VistaCatalogosController;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import moduloEntradas.Entradas;
import moduloEntradas.VistaEntradasController;
import moduloPrestamos.VistaPrestamosController;
import moduloSalidas.VistaSalidasController;
import ococlimassystem.Conexion;

/**
 * FXML Controller class
 *
 * @author chava
 */
public class VistaInventariosController implements Initializable {

    @FXML
    private TableView tablaInventario;
    @FXML
    private ImageView icono;
    @FXML
    private ComboBox<String> comboInventarios;
    @FXML
    private Button botonPrestamos;
    @FXML
    private Button botonEntradas;
    @FXML
    private Button botonSalidas;
    @FXML
    private Button botonCatalogos;
    @FXML
    private HBox hboxRoot;
    @FXML
    private Button botonSalir;
    @FXML
    private TextField buscartxt;

    //Columnas de Herramientas
    private TableColumn columnaID_Hrtas;
    private TableColumn columnaNombreCorto_Hrtas;
    private TableColumn columnaDescripcion_Hrtas;
    private TableColumn columnaCosto_Hrtas;
    private TableColumn columnaClase_Hrtas;
    private TableColumn columnaExistencia_Hrtas;
    private boolean bandCreacionColumnasHerramientas = false;

    private String datosARetornar;
    private ObservableList<ProductosInventario> listaProductos;
    private ObservableList<HerramientasInventario> listaHerramientas;
    boolean isOpenByPrincipal;
    private FilteredList listaFiltrada;

    public String getDatosARetornar() {
        return datosARetornar;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void initAttributes(boolean isOpenByPrincipal) {
        //Se agrega el icono
        this.isOpenByPrincipal = isOpenByPrincipal;
        Image imagen = new Image("/iconos/iconoInventario.png");
        icono.setImage(imagen);

        listaProductos = FXCollections.observableArrayList();
        listaHerramientas = FXCollections.observableArrayList();

        BackgroundPosition b = new BackgroundPosition(Side.RIGHT, 0, true, Side.BOTTOM, 0, true);
        BackgroundImage bi = new BackgroundImage(new Image("Fondo2.png", 1894, 1420, false, true, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, b, BackgroundSize.DEFAULT);

        hboxRoot.setBackground(new Background(bi));
        comboInventarios.getItems().addAll("Productos", "Herramientas");
        comboInventarios.getSelectionModel().select("Productos");
        botonPrestamos.setDisable(true);
        cambiarContenidoTabla();

        /*tablaInventario.getColumns().addListener(new ListChangeListener() {
            public boolean suspended;

            @Override
            public void onChanged(Change change) {
                change.next();
                if (change.wasReplaced() && !suspended) {
                    this.suspended = true;
                    tablaInventario.getColumns().setAll(columnas);
                    this.suspended = false;
                }
            }
        });*/
    }

    public void abrirVentanaInventarios(boolean isOpenByPrincipal) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/moduloInventarios/VistaInventarios_1.fxml"));
            Parent root = loader.load();
            VistaInventariosController controlador = loader.getController();
            controlador.initAttributes(isOpenByPrincipal);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setTitle("Inventario");
            stage.showAndWait();
            this.datosARetornar = controlador.getDatosARetornar();

        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText(e.toString());
            alerta.setContentText("No se ha encontrado el archivo");
            alerta.showAndWait();
        }
    }

    @FXML
    private void abrirEntradas(ActionEvent event) {
        new VistaEntradasController().abrirVentanaEntradas();
    }

    @FXML
    private void abrirSalidas(ActionEvent event) {
        new VistaSalidasController().abrirVentanaSalidas();
    }

    @FXML
    private void abrirCatalogos(ActionEvent event) {
        new VistaCatalogosController().abrirCatalogosPrincipal(null);
    }

    public TableColumn[] crearColumnas_hrtas() {

        columnaID_Hrtas = new TableColumn("ID");
        columnaNombreCorto_Hrtas = new TableColumn("Nombre corto");
        columnaDescripcion_Hrtas = new TableColumn("Descripcion");
        columnaCosto_Hrtas = new TableColumn("Costo");
        columnaClase_Hrtas = new TableColumn("Clase");
        columnaExistencia_Hrtas = new TableColumn("Existencia");

        columnaID_Hrtas.setCellValueFactory(new PropertyValueFactory("codigo_hrtas"));
        columnaNombreCorto_Hrtas.setCellValueFactory(new PropertyValueFactory("nombreCorto_hrtas"));
        columnaDescripcion_Hrtas.setCellValueFactory(new PropertyValueFactory("descripcion_hrtas"));
        columnaCosto_Hrtas.setCellValueFactory(new PropertyValueFactory("costo_hrtas"));
        columnaClase_Hrtas.setCellValueFactory(new PropertyValueFactory("clase_hrtas"));
        columnaExistencia_Hrtas.setCellValueFactory(new PropertyValueFactory("existencia_hrtas"));

        columnaNombreCorto_Hrtas.setPrefWidth(100);
        columnaDescripcion_Hrtas.setPrefWidth(150);
        TableColumn columnas_hrtas[] = {columnaID_Hrtas, columnaNombreCorto_Hrtas, columnaDescripcion_Hrtas, columnaCosto_Hrtas, columnaClase_Hrtas, columnaExistencia_Hrtas};
        return columnas_hrtas;
    }

    @FXML
    private void cambiarContenidoTabla() {
        String opcion = comboInventarios.getSelectionModel().getSelectedItem();
        tablaInventario.getColumns().clear();

        switch (opcion) {
            case "Productos": {
                listaProductos.clear();
                TableColumn columnaID = new TableColumn("ID");
                TableColumn columnaNombreCorto = new TableColumn("Nombre corto");
                TableColumn columnaDescripcion = new TableColumn("Descripcion");
                TableColumn columnaUnidad = new TableColumn("Unidad");
                TableColumn columnaCosto = new TableColumn("Costo");
                TableColumn columnaClase = new TableColumn("Clase");
                TableColumn columnaSubclase1 = new TableColumn("Subclase 1");
                TableColumn columnaSubclase2 = new TableColumn("Subclase 2");
                TableColumn columnaExistencia = new TableColumn("Existencia");

                columnaID.setCellValueFactory(new PropertyValueFactory("id"));
                columnaNombreCorto.setCellValueFactory(new PropertyValueFactory("nombreCorto"));
                columnaDescripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
                columnaUnidad.setCellValueFactory(new PropertyValueFactory("unidad"));
                columnaCosto.setCellValueFactory(new PropertyValueFactory("costo"));
                columnaClase.setCellValueFactory(new PropertyValueFactory("clase"));
                columnaSubclase1.setCellValueFactory(new PropertyValueFactory("subclase1"));
                columnaSubclase2.setCellValueFactory(new PropertyValueFactory("subclase2"));
                columnaExistencia.setCellValueFactory(new PropertyValueFactory("existencia"));

                Conexion con = new Conexion();

                ResultSet rs;
                Statement st;

                try {
                    st = con.getCon().createStatement();
                    rs = st.executeQuery("SELECT PI.ID, P.NOMBRE_CORTO, P.DESCRIPCION, PI.COSTO,PI.EXISTENCIA  "
                            + "FROM PRODUCTOS_INVENTARIO AS PI, PRODUCTOS AS P WHERE PI.ID = P.ID");

                    st = con.getCon().createStatement();
                    ResultSet rsUnidad = st.executeQuery("SELECT DATO FROM UNIDADES_TIPOS AS U , PRODUCTOS AS P WHERE U.ID = P.UNIDAD AND P.ID NOT LIKE '1'");
                    st = con.getCon().createStatement();
                    ResultSet rsClase = st.executeQuery("SELECT DATO FROM UNIDADES_TIPOS AS U , PRODUCTOS AS P WHERE U.ID = P.CLASE AND P.ID NOT LIKE '1'");
                    st = con.getCon().createStatement();
                    ResultSet rsSubClase = st.executeQuery("SELECT DATO FROM UNIDADES_TIPOS AS U , PRODUCTOS AS P WHERE U.ID = P.SUBCLASE AND P.ID NOT LIKE '1'");

                    while (rs.next()) {

                        rsUnidad.next();
                        rsClase.next();
                        rsSubClase.next();
                        listaProductos.add(new ProductosInventario(rs.getString(1), rs.getString(2), rs.getString(3), rsUnidad.getString(1), rs.getDouble(4), rsClase.getString(1), rsSubClase.getString(1), "", rs.getDouble(5)));
                    }

                    con.getCon().close();

                } catch (Exception e) {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("ERROR");
                    alerta.setHeaderText("Error al realizar una consulta en la base de datos");
                    alerta.setContentText(e.toString());
                    alerta.showAndWait();
                }

                TableColumn columnas_productos[] = {columnaID, columnaNombreCorto, columnaDescripcion, columnaUnidad, columnaCosto, columnaClase, columnaSubclase1, columnaSubclase2, columnaExistencia};
                tablaInventario.getColumns().setAll(columnas_productos);

                listaFiltrada = new FilteredList(listaProductos);
                tablaInventario.setItems(listaFiltrada);

                buscartxt.textProperty().addListener((Observable, oldValue, newValue) -> {
                    listaFiltrada = null;
                    listaFiltrada = listaProductos.filtered(new Predicate<ProductosInventario>() {

                        @Override
                        public boolean test(ProductosInventario t) {
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
                            if (Double.toString(t.getCosto()).contains(newValue)) {
                                return true;
                            }
                            if (t.getClase().contains(newValue)) {
                                return true;
                            }
                            if (t.getSubclase1().contains(newValue)) {
                                return true;
                            }
                            if (Double.toString(t.getExistencia()).contains(newValue)) {
                                return true;
                            }
                            return false;
                        }

                    });
                    tablaInventario.setItems(listaFiltrada);
                    tablaInventario.refresh();
                });

                tablaInventario.setItems(listaProductos);
                botonPrestamos.setDisable(true);
            }
            break;
            case "Herramientas": {

                TableColumn columnas_hrtas[] = crearColumnas_hrtas();
                listaHerramientas.clear();
                tablaInventario.getColumns().setAll(columnas_hrtas);
                botonPrestamos.setDisable(false);
                bandCreacionColumnasHerramientas = true;

                Conexion con = new Conexion();

                ResultSet rs;
                Statement st;

                try {
                    st = con.getCon().createStatement();
                    rs = st.executeQuery("SELECT HI.ID, H.NOMBRE_CORTO, H.DESCRIPCION,U.DATO , HI.COSTO, HI.EXISTENCIA  "
                            + "FROM HERRAMIENTAS_INVENTARIO AS HI, HERRAMIENTAS AS H, UNIDADES_TIPOS AS U WHERE HI.ID = H.CODIGO AND U.ID = H.CLASE");

                    while (rs.next()) {

                        listaHerramientas.add(new HerramientasInventario(rs.getInt(1), rs.getString(2), rs.getString(3), "", rs.getDouble(5), rs.getString(4), "", "", rs.getDouble(6)));

                    }

                    con.getCon().close();

                } catch (Exception e) {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("ERROR");
                    alerta.setHeaderText("Error al realizar una consulta en la base de datos");
                    alerta.setContentText(e.toString());
                    alerta.showAndWait();
                }

                listaFiltrada = new FilteredList(listaHerramientas);
                tablaInventario.setItems(listaFiltrada);

                buscartxt.textProperty().addListener((Observable, oldValue, newValue) -> {
                    listaFiltrada = null;
                    listaFiltrada = listaHerramientas.filtered((new Predicate<HerramientasInventario>() {
                        @Override
                        public boolean test(HerramientasInventario t) {
                            if (newValue == null || newValue.isEmpty()) {
                                return true;
                            }
                            if (Integer.toString(t.getCodigo_hrtas()).contains(newValue)) {
                                return true;
                            }
                            if (t.getNombreCorto_hrtas().contains(newValue)) {
                                return true;
                            }
                            if (t.getDescripcion_hrtas().contains(newValue)) {
                                return true;
                            }
                            if (Double.toString(t.getCosto_hrtas()).contains(newValue)) {
                                return true;
                            }
                            if (t.getClase_hrtas().contains(newValue)) {
                                return true;
                            }
                            if (Double.toString(t.getExistencia_hrtas()).contains(newValue)) {
                                return true;
                            }
                            return false;
                        }

                    }));
                    tablaInventario.setItems(listaFiltrada);
                    tablaInventario.refresh();
                });

            }
            break;

        }
        tablaInventario.refresh();
    }

    @FXML
    private void abrirPrestamos(ActionEvent event) {
        new VistaPrestamosController().abrirVistaPrestamos();
    }

    @FXML
    private void salir(ActionEvent event) {
        Stage stage = (Stage) botonSalir.getScene().getWindow();
        stage.close();
    }

//    private void seleccionarItem(MouseEvent event) {
//
//        if (event.getClickCount() >= 2) {
//            String seleccion = comboInventarios.getSelectionModel().getSelectedItem();
//            ProductosInventario aux = (ProductosInventario) tablaInventario.getSelectionModel().getSelectedItem();
//            new VistaDetalleProductosInventarioController().abrirVistaDetalleProductosInventario(aux,seleccion);
//        }
//
////            if (tablaInventario.getSelectionModel().getSelectedItem() != null && !isOpenByPrincipal) {
////                int indice = tablaInventario.getSelectionModel().getSelectedIndex();
////                String seleccion = comboInventarios.getSelectionModel().getSelectedItem();
////                switch (seleccion) {
////                    case "Productos":
////                        ProductosInventario aux = listaProductos.get(indice);
////                        datosARetornar = aux.getId() + "|" + aux.getNombreCorto() + "|" + aux.getDescripcion() + "|" + aux.getUnidad() + "|" + aux.getCosto() + "|" + "Producto";
////                        break;
////                    case "Herramientas":
////                        HerramientasInventario aux_Hrta = listaHerramientas.get(indice);
////                        datosARetornar = aux_Hrta.getCodigo_hrtas() + "|" + aux_Hrta.getNombreCorto_hrtas() + "|" + aux_Hrta.getDescripcion_hrtas() + "|" + aux_Hrta.getUnidad_hrtas() + "|" + aux_Hrta.getCosto_hrtas() + "|" + "Herramienta";
////                        break;
////                    default:
////                        throw new AssertionError();
////                }
////
////                Stage stage = (Stage) botonEntradas.getScene().getWindow();
////                stage.close();
////            }
//    }
    @FXML
    private void seleccionarItemMouseClicked(MouseEvent event) {

        if (event.getClickCount() >= 2) {
            String seleccion = comboInventarios.getSelectionModel().getSelectedItem();
            ProductosInventario aux = null;
            switch (seleccion) {
                case "Productos": {
                    aux = (ProductosInventario) tablaInventario.getSelectionModel().getSelectedItem();
                }

                break;
                case "Herramientas": {
                    HerramientasInventario auxHerramienta = (HerramientasInventario) tablaInventario.getSelectionModel().getSelectedItem();
                    aux = new ProductosInventario(Integer.toString(auxHerramienta.getCodigo_hrtas()), auxHerramienta.getNombreCorto_hrtas(),auxHerramienta.getDescripcion_hrtas(), auxHerramienta.getCosto_hrtas(), auxHerramienta.getExistencia_hrtas());
                }

                default:
            }
            VistaDetalleProductosInventarioController auxVista = new VistaDetalleProductosInventarioController();
            auxVista.abrirVistaDetalleProductosInventario(aux, seleccion, isOpenByPrincipal);
            this.datosARetornar = auxVista.getDatosAretornar();
            
            if(!isOpenByPrincipal){
                Stage stage = (Stage) botonSalidas.getScene().getWindow();
                stage.close();
            }
        }
    }

    @FXML
    private void seleccionarItemKeyRealeaed(KeyEvent event) {

    }

}
