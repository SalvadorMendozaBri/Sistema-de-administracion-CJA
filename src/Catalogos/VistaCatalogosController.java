/*
 *Falta poner todos los campos en los listeners para filtrar las listas 
 */
package Catalogos;

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
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import moduloEntradas.Entradas;
import ococlimassystem.Conexion;

/**
 * FXML Controller class
 *
 * @author chava
 */
public class VistaCatalogosController implements Initializable {

    @FXML
    private TextField buscartxt;
    @FXML
    private ComboBox<String> comboCatalogos;
    @FXML
    private TableView<Object> tablaCatalogos;
    @FXML
    private Button botonAgregar;
    @FXML
    private Button botonModificar;
    @FXML
    private Button botonEliminar;
    @FXML
    private VBox vBox;
    @FXML
    private ComboBox<VistaCatalogosController> comboOculto;
    @FXML
    private ImageView iconoCatalogos;

    private ObservableList<Productos> listaProductos;
    private ObservableList<Proveedores> listaProveedores;
    private ObservableList<Personal> listaPersonal;
    private ObservableList<Herramientas> listaHerramientas;
    private ObservableList<Vendedores> listaVendedores;
    private ObservableList<Empresas> listaEmpresas;
    private ObservableList<Equipos> listaEquipos;
    private ObservableList<Servicios> listaServicios;
    private ObservableList<Contratistas> listaContratistas;
    private ObservableList<ServiciosGenericos> listaServiciosGenericos;
    private ObservableList<Vehiculos> listaVehiculos;
    private ObservableList<Proyectos> listaProyectos;

    //banderas de mexico
    private boolean IsDatosCargadosProductos;
    private boolean IsDatosCargadosProveedores;
    private boolean IsDatosCargadosHerramientas;

    private FilteredList listaFiltrada;
    private String datosARetornar;
    private boolean isOpenByPrincipal;
    private Object objetoARetornar;

    public ComboBox<String> getComboCatalogos() {
        return comboCatalogos;
    }

    public String getDatosARetornar() {
        return datosARetornar;
    }

    public void setDatosARetornar(String datosARetornar) {
        this.datosARetornar = datosARetornar;
    }

    public void initAttributes(String seleccion, VistaCatalogosController controlador) {
        iconoCatalogos.setImage(new Image("/iconos/catalogos.png"));

        //Bloquea la comboBox en caso de que sea abierta para ver solo un catalogo en especifico      
        if (seleccion != null && !seleccion.isEmpty()) {
            if (seleccion.equalsIgnoreCase("Productos y herramientas")) {
                comboCatalogos.getItems().addAll("Productos", "Herramientas");
                comboCatalogos.getSelectionModel().select("Productos");
            } else {
                comboCatalogos.getItems().addAll(seleccion);
                comboCatalogos.getSelectionModel().select(seleccion);
                comboCatalogos.setDisable(true);
            }
        } else {
            comboCatalogos.getItems().addAll("Productos", "Proveedores", "Proyectos", "Herramientas" /*"Personal", "Vendedores", "Empresas", "Equipos", "Servicios", "Contratistas", "Vehiculos", "Servicios genéricos"*/);
            isOpenByPrincipal = true;
            comboCatalogos.getSelectionModel().select("Productos");
        }

        //inicializar listas
        listaProductos = FXCollections.observableArrayList();
        listaProveedores = FXCollections.observableArrayList();
        listaPersonal = FXCollections.observableArrayList();
        listaHerramientas = FXCollections.observableArrayList();
        listaVendedores = FXCollections.observableArrayList();
        listaEmpresas = FXCollections.observableArrayList();
        listaEquipos = FXCollections.observableArrayList();
        listaServicios = FXCollections.observableArrayList();
        listaContratistas = FXCollections.observableArrayList();
        listaServiciosGenericos = FXCollections.observableArrayList();
        listaVehiculos = FXCollections.observableArrayList();
        listaProyectos = FXCollections.observableArrayList();
        crearColumnas();

        //Agregar baclground al VBox
        BackgroundPosition b = new BackgroundPosition(Side.RIGHT, 0, true, Side.BOTTOM, 0, true);
        BackgroundImage bi = new BackgroundImage(new Image("Fondo2.png", 1894, 1420, false, true, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, b, BackgroundSize.DEFAULT);
        vBox.setBackground(new Background(bi));

        ObservableList<VistaCatalogosController> listaControlador = FXCollections.observableArrayList();
        listaControlador.add(controlador);
        comboOculto.setItems(listaControlador);

    }

    public ObservableList<Productos> getListaProductos() {
        return listaProductos;
    }

    public ObservableList<Proveedores> getListaProveedores() {
        return listaProveedores;
    }

    public ObservableList<Personal> getListaPersonal() {
        return listaPersonal;
    }

    public ObservableList<Herramientas> getListaHerramientas() {
        return listaHerramientas;
    }

    public FilteredList getListaFiltrada() {
        return listaFiltrada;
    }

    public Object getObjetoARetornar() {
        return objetoARetornar;
    }

    public void abrirCatalogosPrincipal(String seleccion) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Catalogos/VistaCatalogos.fxml"));
            Parent root = loader.load();
            VistaCatalogosController controlador = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Catalogos");
            controlador.initAttributes(seleccion, controlador);
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.showAndWait();
            //new VistaCatalogoSimplesController().abrirVistaCatalogoSimples();

            datosARetornar = controlador.getDatosARetornar();
            objetoARetornar = controlador.getObjetoARetornar();
            System.out.println(datosARetornar);

        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText(e.toString());
            alerta.setContentText("Error en el loader");
            alerta.showAndWait();
        }
    }

    @FXML
    public void crearColumnas() {
        //Deshabilitar botones de seleccion
        botonModificar.setDisable(true);
        botonEliminar.setDisable(true);

        String tipoCatalogo = comboCatalogos.getSelectionModel().getSelectedItem();
        tablaCatalogos.getColumns().clear();
        // tablaCatalogos.getItems().clear();

        switch (tipoCatalogo) {
            case "Productos": {
                TableColumn id = new TableColumn("ID");
                TableColumn nombreCorto = new TableColumn("Nombre Corto");
                TableColumn descripcion = new TableColumn("Descripción");
                TableColumn unidad = new TableColumn("Unidad");
                TableColumn costo = new TableColumn("Costo");
                TableColumn clase = new TableColumn("Clase");
                TableColumn subclase = new TableColumn("Subclase");
                TableColumn proveedores = new TableColumn("Proveedores");

//                listaProductos.add(new Productos("1234", "Coway", "Purificador de aire de naja potencia", "pieza", "Udemy \nCisco", 6139.85, "Purificadores", "..."));
//                listaProductos.add(new Productos("3421", "AirNet", "Limpiador de sistemas de aire ", "Litro", "Uline", 890, "Limpiador", "Desinfectantes"));
//                listaProductos.add(new Productos("2578", "Lasko T14100", "Aire acondicionado domestico", "Disasco \nOGT", "pieza", 588.92, "Aire acondicionado", "..."));
//                listaProductos.add(new Productos("1214", "Mini enfriador", "Enfriador portatil", "pieza", "Controversie", 668.12, "Aires acondicionado portatil", "..."));
                id.setCellValueFactory(new PropertyValueFactory("id"));
                nombreCorto.setCellValueFactory(new PropertyValueFactory("nombreCorto"));
                descripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
                unidad.setCellValueFactory(new PropertyValueFactory("unidad"));
                proveedores.setCellValueFactory(new PropertyValueFactory("proveedores"));
                costo.setCellValueFactory(new PropertyValueFactory("costo"));
                clase.setCellValueFactory(new PropertyValueFactory("clase"));
                subclase.setCellValueFactory(new PropertyValueFactory("subclase"));

                TableColumn columnas[] = {id, nombreCorto, descripcion, unidad, costo, clase, subclase, proveedores};
                tablaCatalogos.getColumns().setAll(columnas);

                listaFiltrada = new FilteredList(listaProductos);
                tablaCatalogos.setItems(listaFiltrada);

                //Coneccion con la base de datos
                listaProductos.clear();
                Conexion conProveedores = new Conexion();
                Conexion con = new Conexion();
                Statement st;
                ResultSet rs;

                try {

                    st = con.getCon().createStatement();
                    rs = st.executeQuery("SELECT ID, NOMBRE_CORTO, DESCRIPCION, UNIDAD, COSTO, CLASE, SUBCLASE FROM PRODUCTOS WHERE IS_ELIMINATED IS NULL ");

                    st = con.getCon().createStatement();
                    ResultSet rsUnidad = st.executeQuery("SELECT PRODUCTOS.ID, UNIDADES_TIPOS.DATO FROM UNIDADES_TIPOS, PRODUCTOS WHERE PRODUCTOS.UNIDAD = UNIDADES_TIPOS.ID AND PRODUCTOS.ID NOT LIKE '1'");
                    st = con.getCon().createStatement();
                    ResultSet rsClase = st.executeQuery("SELECT PRODUCTOS.ID, UNIDADES_TIPOS.DATO FROM UNIDADES_TIPOS, PRODUCTOS WHERE PRODUCTOS.CLASE = UNIDADES_TIPOS.ID AND PRODUCTOS.ID NOT LIKE '1'");
                    st = con.getCon().createStatement();
                    ResultSet rsSubClase = st.executeQuery("SELECT PRODUCTOS.ID, UNIDADES_TIPOS.DATO FROM UNIDADES_TIPOS, PRODUCTOS WHERE PRODUCTOS.SUBCLASE = UNIDADES_TIPOS.ID AND PRODUCTOS.ID NOT LIKE '1'");

                    while (rs.next()) {

                        Statement stProveedores = conProveedores.getNewStatement(conProveedores.getCon());
                        ResultSet rsproveedores = stProveedores.executeQuery("SELECT PROVEEDORES.NOMBRE FROM PROVEEDORES, PROVEE, PRODUCTOS WHERE PRODUCTOS.ID = PROVEE.ID_PRODUCTO AND PROVEEDORES.CODIGO=PROVEE.CODIGO_PROVEEDOR AND PROVEE.ID_PRODUCTO =" + rs.getInt(1));

                        String proveedoresRs = "";

                        while (rsproveedores.next()) {

                            proveedoresRs = proveedoresRs + rsproveedores.getString(1) + "\n";

                        }

                        rsUnidad.next();
                        rsClase.next();
                        rsSubClase.next();

                        listaProductos.add(new Productos(Integer.toString(rs.getInt(1)), rs.getString(2), rs.getString(3), rsUnidad.getString(2), rs.getDouble(5), rsClase.getString(2), rsSubClase.getString(2), proveedoresRs));
                    }
                    con.getCon().close();
                    conProveedores.getCon().close();

                } catch (Exception e) {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("ERROR");
                    alerta.setHeaderText("Error al realizar una consulta en la base de datos");
                    alerta.setContentText(e.toString());
                    alerta.showAndWait();
                }

                buscartxt.textProperty().addListener((Observable, oldValue, newValue) -> {
                    listaFiltrada = null;
                    listaFiltrada = listaProductos.filtered(new Predicate<Productos>() {
                        @Override
                        public boolean test(Productos t) {
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
                            if (t.getProveedores().contains(newValue)) {
                                return true;
                            }
                            if (Double.toString(t.getCosto()).contains(newValue)) {
                                return true;
                            }
                            if (t.getClase().contains(newValue)) {
                                return true;
                            }
                            if (t.getSubclase().contains(newValue)) {
                                return true;
                            }
                            return false;
                        }
                    });
                    tablaCatalogos.setItems(listaFiltrada);
                    tablaCatalogos.refresh();
                });

            }
            break;

            case "Proveedores": {

                TableColumn codigoProveedor = new TableColumn("Código");
                TableColumn nombre = new TableColumn("Nombre");
                TableColumn telefono = new TableColumn("Teléfono");
                TableColumn domicilio = new TableColumn("Domicilio");
                TableColumn tipo = new TableColumn("Tipo de proveedor");

                codigoProveedor.setCellValueFactory(new PropertyValueFactory("codigo"));
                nombre.setCellValueFactory(new PropertyValueFactory("nombre"));
                telefono.setCellValueFactory(new PropertyValueFactory("telefono"));
                domicilio.setCellValueFactory(new PropertyValueFactory("domicilio"));
                tipo.setCellValueFactory(new PropertyValueFactory("tipo"));

                TableColumn columnasProveedores[] = {codigoProveedor, nombre, telefono, domicilio, tipo};
                tablaCatalogos.getColumns().setAll(columnasProveedores);

                listaFiltrada = new FilteredList(listaProveedores);
                tablaCatalogos.setItems(listaFiltrada);

                listaProveedores.clear();
                Conexion con = new Conexion();
                Statement st;
                ResultSet rs;

                try {

                    st = con.getCon().createStatement();
                    rs = st.executeQuery("SELECT CODIGO, NOMBRE, TELEFONO, DOMICILIO, UNIDADES_TIPOS.DATO FROM PROVEEDORES ,UNIDADES_TIPOS WHERE PROVEEDORES.TIPO = UNIDADES_TIPOS.ID AND PROVEEDORES.IS_ELIMINATED IS NULL");

                    while (rs.next()) {

                        listaProveedores.add(new Proveedores(Integer.toString(rs.getInt(1)), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
                    }
                    con.getCon().close();
                } catch (Exception e) {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("ERROR");
                    alerta.setHeaderText("Error al realizar una consulta en la base de datos");
                    alerta.setContentText(e.toString());
                    alerta.showAndWait();
                }

                buscartxt.textProperty().addListener((Observable, oldValue, newValue) -> {
                    listaFiltrada = null;
                    listaFiltrada = listaProveedores.filtered(new Predicate<Proveedores>() {
                        @Override
                        public boolean test(Proveedores t) {
                            if (newValue == null || newValue.isEmpty()) {
                                return true;
                            }
                            if (t.getCodigo().contains(newValue)) {
                                return true;
                            }
                            if (t.getNombre().contains(newValue)) {
                                return true;
                            }
                            if (t.getTelefono().contains(newValue)) {
                                return true;
                            }
                            if (t.getDomicilio().contains(newValue)) {
                                return true;
                            }
                            if (t.getTipo().contains(newValue)) {
                                return true;
                            }

                            return false;
                        }
                    });
                    tablaCatalogos.setItems(listaFiltrada);
                    tablaCatalogos.refresh();
                });
            }
            break;

            case "Personal": {
                TableColumn codigo_emp = new TableColumn("Código");
                TableColumn nombre_emp = new TableColumn("Nombre");
                TableColumn puesto = new TableColumn("Puesto");
                TableColumn costoPorDia = new TableColumn("Costo por dia");

                codigo_emp.setPrefWidth(120);
                nombre_emp.setPrefWidth(150);
                puesto.setPrefWidth(150);

                listaPersonal.add(new Personal("12545", "Juan Abel Lopez Andrade", "Vendedor", 300));
                listaPersonal.add(new Personal("12546", "Salvador Mendoza Briseño", "Encargado de Ambulantes", 800));
                listaPersonal.add(new Personal("12547", "Jorge Isidro Hernández Ramírez", "Administrativo", 1000));
                listaPersonal.add(new Personal("12548", "Cristian Sahagun Padilla", "Departamento ingenierias", 1200));

                codigo_emp.setCellValueFactory(new PropertyValueFactory("codigo"));
                nombre_emp.setCellValueFactory(new PropertyValueFactory("nombre"));
                puesto.setCellValueFactory(new PropertyValueFactory("puesto"));
                costoPorDia.setCellValueFactory(new PropertyValueFactory("costoPorDia"));

                TableColumn columnasPersonal[] = {codigo_emp, nombre_emp, puesto, costoPorDia};
                tablaCatalogos.getColumns().setAll(columnasPersonal);

                listaFiltrada = new FilteredList(listaPersonal);
                tablaCatalogos.setItems(listaFiltrada);

                buscartxt.textProperty().addListener((Observable, oldValue, newValue) -> {
                    listaFiltrada = null;
                    listaFiltrada = listaPersonal.filtered(new Predicate<Personal>() {
                        @Override
                        public boolean test(Personal t) {
                            if (newValue == null || newValue.isEmpty()) {
                                return true;
                            }
                            if (t.getCodigo().contains(newValue)) {
                                return true;
                            }
                            if (t.getNombre().contains(newValue)) {
                                return true;
                            }
                            if (t.getPuesto().contains(newValue)) {
                                return true;
                            }
                            if (Double.toString(t.getCostoPorDia()).contains(newValue)) {
                                return true;
                            }
                            return false;
                        }
                    });
                    tablaCatalogos.setItems(listaFiltrada);
                    tablaCatalogos.refresh();
                });
            }
            break;
            case "Herramientas": {
                TableColumn codigo_hrta = new TableColumn("Codigo");
                TableColumn nombreCorto_hrta = new TableColumn("Nombre corto");
                TableColumn descripcion_hrta = new TableColumn("Descripcion");
                TableColumn clase_hrta = new TableColumn("Clase");
                TableColumn costo_hrta = new TableColumn("Costo");

                codigo_hrta.setCellValueFactory(new PropertyValueFactory("codigo"));
                nombreCorto_hrta.setCellValueFactory(new PropertyValueFactory("nombreCorto"));
                descripcion_hrta.setCellValueFactory(new PropertyValueFactory("descripcion"));
                clase_hrta.setCellValueFactory(new PropertyValueFactory("clase"));
                costo_hrta.setCellValueFactory(new PropertyValueFactory("costo"));

                TableColumn columnasHerramientas[] = {codigo_hrta, nombreCorto_hrta, descripcion_hrta, clase_hrta, costo_hrta};
                tablaCatalogos.getColumns().setAll(columnasHerramientas);

                listaFiltrada = new FilteredList(listaHerramientas);
                tablaCatalogos.setItems(listaFiltrada);

                listaHerramientas.clear();
                Conexion con = new Conexion();
                Statement st;
                ResultSet rs;

                try {

                    st = con.getCon().createStatement();
                    rs = st.executeQuery("SELECT CODIGO ,NOMBRE_CORTO ,DESCRIPCION ,UNIDADES_TIPOS.DATO,COSTO FROM HERRAMIENTAS,UNIDADES_TIPOS WHERE IS_ELIMINATED IS NULL AND UNIDADES_TIPOS.ID = CLASE");

                    while (rs.next()) {

                        listaHerramientas.add(new Herramientas(Integer.toString(rs.getInt(1)), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDouble(5)));
                    }
                    con.getCon().close();

                } catch (Exception e) {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("ERROR");
                    alerta.setHeaderText("Error al realizar una consulta en la base de datos");
                    alerta.setContentText(e.toString());
                    alerta.showAndWait();
                }

                buscartxt.textProperty().addListener((Observable, oldValue, newValue) -> {
                    listaFiltrada = null;
                    listaFiltrada = listaHerramientas.filtered(new Predicate<Herramientas>() {
                        @Override
                        public boolean test(Herramientas t) {
                            if (newValue == null || newValue.isEmpty()) {
                                return true;
                            }
                            if (t.getCodigo().contains(newValue)) {
                                return true;
                            }
                            if (t.getNombreCorto().contains(newValue)) {
                                return true;
                            }
                            if (t.getDescripcion().contains(newValue)) {
                                return true;
                            }
                            if (t.getClase().contains(newValue)) {
                                return true;
                            }
                            if (Double.toString(t.getCosto()).contains(newValue)) {
                                return true;
                            }

                            return false;
                        }
                    });
                    tablaCatalogos.setItems(listaFiltrada);
                    tablaCatalogos.refresh();
                });
            }
            break;

            case "Vendedores": {

                TableColumn codigo = new TableColumn("Código");
                TableColumn nombre = new TableColumn("Nombre");
                TableColumn alias = new TableColumn("Alias");
                TableColumn division = new TableColumn("División Asignada");
                TableColumn telefono = new TableColumn("Telefono");
                TableColumn correo = new TableColumn("Correo");

                listaVendedores.add(new Vendedores("1", "Leandro Patiño", "LEPTÑO", "Division 1", "543-20-61987", "lpatiño1501@outlook.com"));
                listaVendedores.add(new Vendedores("2", "Paula Piñero", "PLPÑRO", "Division 2", "797-55-46880", "paula_pñ24@outlook.com"));
                listaVendedores.add(new Vendedores("3", "Javier Medina", "JVRMDNA", "Division 3", "407-72-84472", "jv.medina32@outlook.com"));
                listaVendedores.add(new Vendedores("4", "Oscar Pineda", "OSCPDA", "Division 4", "099-79-63410", "os-pda1446-@outlook.com"));

                codigo.setCellValueFactory(new PropertyValueFactory("codigo"));
                nombre.setCellValueFactory(new PropertyValueFactory("nombre"));
                alias.setCellValueFactory(new PropertyValueFactory("alias"));
                division.setCellValueFactory(new PropertyValueFactory("division"));
                telefono.setCellValueFactory(new PropertyValueFactory("telefono"));
                correo.setCellValueFactory(new PropertyValueFactory("correo"));

                TableColumn columnasVendedores[] = {nombre, alias, division, telefono, correo};
                tablaCatalogos.getColumns().setAll(columnasVendedores);

                listaFiltrada = new FilteredList(listaVendedores);
                tablaCatalogos.setItems(listaFiltrada);

                buscartxt.textProperty().addListener((Observable, oldValue, newValue) -> {
                    listaFiltrada = null;
                    listaFiltrada = listaVendedores.filtered(new Predicate<Vendedores>() {
                        @Override
                        public boolean test(Vendedores t) {
                            if (newValue == null || newValue.isEmpty()) {
                                return true;
                            }

                            if (t.getNombre().contains(newValue)) {
                                return true;
                            }
                            if (t.getAlias().contains(newValue)) {
                                return true;
                            }
                            if (t.getDivision().contains(newValue)) {
                                return true;
                            }
                            if (t.getTelefono().contains(newValue)) {
                                return true;
                            }
                            if (t.getCorreo().contains(newValue)) {
                                return true;
                            }
                            return false;
                        }
                    });
                    tablaCatalogos.setItems(listaFiltrada);
                    tablaCatalogos.refresh();
                });

            }
            break;
            case "Empresas": {
                TableColumn id = new TableColumn("ID");
                TableColumn nombre_Emp = new TableColumn("Nombre");
                TableColumn razonSocial_Emp = new TableColumn("Razon Social");
                TableColumn calle_emp = new TableColumn("Calle");
                TableColumn numero_emp = new TableColumn("Número");
                TableColumn CP_emp = new TableColumn("Codigo postal");
                TableColumn municipio_emp = new TableColumn("Municipio");
                TableColumn estado_emp = new TableColumn("Estado");
                TableColumn pais_emp = new TableColumn("Pais");
                TableColumn telefono_Emp = new TableColumn("Telefono");

                //insertar datos de prueba
                listaEmpresas.add(new Empresas("250", "CHILLERS", "EURO CHILLER", "Montes Urales Piso 2, Col. Lomas de Chapultepec ", " 635", "11000", " Del. Miguel Hidalgo", "Ciudad de México", "México", "5552825554"));
                listaEmpresas.add(new Empresas("251", "Climax soluciones integrales", "EMPRESAS DE CLIMATIZACION ", "Pje Trece, Curicó ", "103", "832 0000", "Curicó", "Santiago", "Chile", "232102331"));
                listaEmpresas.add(new Empresas("252", "Air-Ref", "Air-Ref", "Simón Bolívar", "408", "44150", "Guadalajara", "Jalisco", "México", "333630-1660"));
                listaEmpresas.add(new Empresas("253", "Solu-Clima", "Solu-Clima", "Hacienda las Palomas", "305", "45200", "Zapopan", "Jalisco", "México", "3388529738"));

                id.setCellValueFactory(new PropertyValueFactory("id"));
                nombre_Emp.setCellValueFactory(new PropertyValueFactory("nombre"));
                razonSocial_Emp.setCellValueFactory(new PropertyValueFactory("razonSocial"));
                calle_emp.setCellValueFactory(new PropertyValueFactory("calle"));
                numero_emp.setCellValueFactory(new PropertyValueFactory("numero"));
                CP_emp.setCellValueFactory(new PropertyValueFactory("CP"));
                municipio_emp.setCellValueFactory(new PropertyValueFactory("municipio"));
                estado_emp.setCellValueFactory(new PropertyValueFactory("estado"));
                pais_emp.setCellValueFactory(new PropertyValueFactory("pais"));
                telefono_Emp.setCellValueFactory(new PropertyValueFactory("telefono"));

                TableColumn columnasEmpresas[] = {nombre_Emp, razonSocial_Emp, calle_emp, numero_emp, CP_emp, municipio_emp, estado_emp, pais_emp, telefono_Emp};
                tablaCatalogos.getColumns().setAll(columnasEmpresas);

                listaFiltrada = new FilteredList(listaEmpresas);
                tablaCatalogos.setItems(listaFiltrada);

                buscartxt.textProperty().addListener((Observable, oldValue, newValue) -> {
                    listaFiltrada = null;
                    listaFiltrada = listaEmpresas.filtered(new Predicate<Empresas>() {
                        @Override
                        public boolean test(Empresas t) {
                            if (newValue == null || newValue.isEmpty()) {
                                return true;
                            }

                            if (t.getNombre().contains(newValue)) {
                                return true;
                            }
                            if (t.getRazonSocial().contains(newValue)) {
                                return true;
                            }
                            if (t.getNumero().contains(newValue)) {
                                return true;
                            }
                            if (t.getCP().contains(newValue)) {
                                return true;
                            }
                            if (t.getMunicipio().contains(newValue)) {
                                return true;
                            }
                            if (t.getEstado().contains(newValue)) {
                                return true;
                            }
                            if (t.getPais().contains(newValue)) {
                                return true;
                            }
                            if (t.getTelefono().contains(newValue)) {
                                return true;
                            }

                            return false;
                        }
                    });
                    tablaCatalogos.setItems(listaFiltrada);
                    tablaCatalogos.refresh();
                });

            }
            break;
            case "Servicios": {
                TableColumn folio = new TableColumn("Folio");
                TableColumn fechaRegistro = new TableColumn("Fecha registro");
                TableColumn usuarioRegistra = new TableColumn("Usuario que registra");
                TableColumn nombreEquipo = new TableColumn("Nombre equipo");
                TableColumn descripcion = new TableColumn("Descripción");
                TableColumn modelo = new TableColumn("Modelo");
                TableColumn marca = new TableColumn("Marca");
                TableColumn presion = new TableColumn("Presión");
                TableColumn potencia = new TableColumn("Potencia");
                TableColumn voltaje = new TableColumn("Voltaje");
                TableColumn amperaje = new TableColumn("Amperaje");
                TableColumn gasRefrigerante = new TableColumn("Gas refrigerante");
                TableColumn capacidad = new TableColumn("Capacidad");
                TableColumn tipo = new TableColumn("Tipo");
                TableColumn costo = new TableColumn("Costo");

                folio.setCellValueFactory(new PropertyValueFactory("folio"));
                fechaRegistro.setCellValueFactory(new PropertyValueFactory("fechaRegistro"));
                usuarioRegistra.setCellValueFactory(new PropertyValueFactory("usuarioRegistra"));
                nombreEquipo.setCellValueFactory(new PropertyValueFactory("nombreEquipo"));
                descripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
                modelo.setCellValueFactory(new PropertyValueFactory("modelo"));
                marca.setCellValueFactory(new PropertyValueFactory("marca"));
                presion.setCellValueFactory(new PropertyValueFactory("presion"));
                potencia.setCellValueFactory(new PropertyValueFactory("potencia"));
                voltaje.setCellValueFactory(new PropertyValueFactory("voltaje"));
                amperaje.setCellValueFactory(new PropertyValueFactory("amperaje"));
                gasRefrigerante.setCellValueFactory(new PropertyValueFactory("gasRefrigerante"));
                capacidad.setCellValueFactory(new PropertyValueFactory("capacidad"));
                tipo.setCellValueFactory(new PropertyValueFactory("tipo"));
                costo.setCellValueFactory(new PropertyValueFactory("costo"));

                TableColumn columnas[] = {folio, fechaRegistro, usuarioRegistra, nombreEquipo, descripcion, modelo, marca, presion, potencia, voltaje, amperaje, gasRefrigerante, capacidad, tipo, costo};
                tablaCatalogos.getColumns().setAll(columnas);

                listaFiltrada = new FilteredList(listaServicios);
                tablaCatalogos.setItems(listaFiltrada);

//                buscartxt.textProperty().addListener((Observable, oldValue, newValue) -> {
//                    listaFiltrada = null;
//                    listaFiltrada = listaServicios.filtered(new Predicate<Servicios>() {
//                        @Override
//                        public boolean test(Servicios t) {
//                            if (newValue == null || newValue.isEmpty()) {
//                                return true;
//                            }
//                            if (t.getFolio().contains(newValue)) {
//                                return true;
//                            }
//                            return false;
//                        }
//                    });
//                    tablaCatalogos.setItems(listaFiltrada);
//                    tablaCatalogos.refresh();
//                });
            }
            break;
            case "Equipos": {
                TableColumn folio_Eq = new TableColumn("Folio");
                TableColumn fechaRegistro_Eq = new TableColumn("Fecha de registro");
                TableColumn nombreEquipo_Eq = new TableColumn("Nombre");
                TableColumn descripcion_Eq = new TableColumn("Descripcion");
                TableColumn modelo_Eq = new TableColumn("Modelo");
                TableColumn marca_Eq = new TableColumn("Marca");
                TableColumn caudal_Eq = new TableColumn("Caudal");
                TableColumn presion_Eq = new TableColumn("Presion");
                TableColumn potencia_Eq = new TableColumn("Potencia");
                TableColumn voltaje_Eq = new TableColumn("Voltaje");
                TableColumn amperaje_Eq = new TableColumn("Amperaje");
                TableColumn gasRefrigerante_Eq = new TableColumn("Gas Refrigerante");
                TableColumn capacidad_Eq = new TableColumn("Capacidad");
                TableColumn tipo_Eq = new TableColumn("Tipo");
                TableColumn costo_Eq = new TableColumn("Costo");

                double costoCosto = 3;

                listaEquipos.add(new Equipos("250", new Date(200000), "Centrifugador", "Centrigugador de metal de baja potencia", "CM1254", "CNC", "12 L", "125 PA", "25W", "50 V", "250 A", "250 cm^3", "No aplica", "Metalurgico", 250.20));
                listaEquipos.add(new Equipos("251", new Date(200000), "Torno CNC", "Torno especializado en metal", "CNC123", "CNC", "20 L", "20 PA", "10 W", "25 V", "25000 A", "20 cm^3", "No aplica", "Metalurgico", 250000));
                listaEquipos.add(new Equipos("252", new Date(200000), "Cortador Laser", "Cortador laser con alta potecia", "SID250", "SIDECO", "25 L", "120 PA", "500 W", "505 V", "2500 A", "21 cm^3", "300 Kg", "Corte", 31000));
                listaEquipos.add(new Equipos("253", new Date(200000), "Fresadora SIDECO", "Fresadora SIDECO asistida por computadora", "SID520", "SIDECO", "120 L", "25 PA", "50 W", "100 V", "255 A", "120 cm^3", "250 Kg", "Metalurgico", 35000));

                folio_Eq.setCellValueFactory(new PropertyValueFactory("folio"));
                fechaRegistro_Eq.setCellValueFactory(new PropertyValueFactory("fecha"));
                nombreEquipo_Eq.setCellValueFactory(new PropertyValueFactory("nombreEquipo"));
                descripcion_Eq.setCellValueFactory(new PropertyValueFactory("descripcion"));
                modelo_Eq.setCellValueFactory(new PropertyValueFactory("modelo"));
                marca_Eq.setCellValueFactory(new PropertyValueFactory("marca"));
                caudal_Eq.setCellValueFactory(new PropertyValueFactory("caudal"));
                presion_Eq.setCellValueFactory(new PropertyValueFactory("presion"));
                potencia_Eq.setCellValueFactory(new PropertyValueFactory("potencia"));
                voltaje_Eq.setCellValueFactory(new PropertyValueFactory("voltaje"));
                amperaje_Eq.setCellValueFactory(new PropertyValueFactory("amperaje"));
                gasRefrigerante_Eq.setCellValueFactory(new PropertyValueFactory("gasRefrigerante"));
                capacidad_Eq.setCellValueFactory(new PropertyValueFactory("capacidad"));
                tipo_Eq.setCellValueFactory(new PropertyValueFactory("tipo"));
                costo_Eq.setCellValueFactory(new PropertyValueFactory("costo"));

                TableColumn columnasEquipos[] = {folio_Eq, fechaRegistro_Eq, nombreEquipo_Eq, descripcion_Eq, modelo_Eq, marca_Eq, caudal_Eq, presion_Eq, potencia_Eq, voltaje_Eq, amperaje_Eq, gasRefrigerante_Eq, capacidad_Eq, tipo_Eq, costo_Eq};
                tablaCatalogos.getColumns().setAll(columnasEquipos);

                listaFiltrada = new FilteredList(listaEquipos);
                tablaCatalogos.setItems(listaFiltrada);

                buscartxt.textProperty().addListener((Observable, oldValue, newValue) -> {
                    listaFiltrada = null;
                    listaFiltrada = listaEquipos.filtered(new Predicate<Equipos>() {
                        @Override
                        public boolean test(Equipos t) {
                            if (newValue == null || newValue.isEmpty()) {
                                return true;
                            }

                            if (t.getFolio().contains(newValue)) {
                                return true;
                            }
                            return false;
                        }
                    });
                    tablaCatalogos.setItems(listaFiltrada);
                    tablaCatalogos.refresh();
                });
            }
            break;
            case "Contratistas": {
                TableColumn id = new TableColumn("Id");
                TableColumn nombre = new TableColumn("Nombre");
                TableColumn razon_social = new TableColumn("Razón Social");
                TableColumn factura = new TableColumn("Factura");
                TableColumn descripcion = new TableColumn("Descripción");
                TableColumn domicilio = new TableColumn("Domicilio");
                TableColumn telefono = new TableColumn("Teléfono");
                TableColumn correo = new TableColumn("Correo");
                TableColumn nombre_representante = new TableColumn("Nombre de Representantes");

                listaContratistas.add(new Contratistas("1234", "Humberto Millan ", "Contratista", "si", "Buena presentación", "Vicente Guerrero #32", "3314256874", "humberto-mill34@outlook.com", ""));
                listaContratistas.add(new Contratistas("1235", "Victoriano Salas ", "Contratista", "no", "Buena presentación", "Privada de Guadalupe #2", "3347856571", "vic.salas-@outlook.com", ""));
                listaContratistas.add(new Contratistas("1236", "Marcela Flores ", "Contratista", "no", "Buena presentación", "Av.Independencia #105", "3921548754", "marce_F456@outlook.com", ""));
                listaContratistas.add(new Contratistas("1237", "Gregorio Chavez", "Contratista", "si", "Buena presentación", "Morelos #44", "3923254651", "g.chavez_23@outlook.com", ""));

                id.setCellValueFactory(new PropertyValueFactory("id"));
                nombre.setCellValueFactory(new PropertyValueFactory("nombre"));
                razon_social.setCellValueFactory(new PropertyValueFactory("razonSocial"));
                factura.setCellValueFactory(new PropertyValueFactory("factura"));
                descripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
                telefono.setCellValueFactory(new PropertyValueFactory("telefono"));
                correo.setCellValueFactory(new PropertyValueFactory("correo"));
                nombre_representante.setCellValueFactory(new PropertyValueFactory("NombreRepresentatntes"));

                TableColumn columnasEquipos[] = {id, nombre, razon_social, factura, descripcion, telefono, correo, nombre_representante};
                tablaCatalogos.getColumns().setAll(columnasEquipos);

                listaFiltrada = new FilteredList(listaContratistas);
                tablaCatalogos.setItems(listaFiltrada);

                buscartxt.textProperty().addListener((Observable, oldValue, newValue) -> {
                    listaFiltrada = null;
                    listaFiltrada = listaContratistas.filtered(new Predicate<Contratistas>() {
                        @Override
                        public boolean test(Contratistas t) {
                            if (newValue == null || newValue.isEmpty()) {
                                return true;
                            }

                            if (t.getId().contains(newValue)) {
                                return true;
                            }
                            if (t.getNombre().contains(newValue)) {
                                return true;
                            }
                            if (t.getRazonSocial().contains(newValue)) {
                                return true;
                            }
                            if (t.getFactura().contains(newValue)) {
                                return true;
                            }
                            if (t.getDescripcion().contains(newValue)) {
                                return true;
                            }
                            if (t.getDomicilio().contains(newValue)) {
                                return true;
                            }
                            if (t.getTelefono().contains(newValue)) {
                                return true;
                            }
                            if (t.getCorreo().contains(newValue)) {
                                return true;
                            }
                            if (t.getNombreRepresentantes().contains(newValue)) {
                                return true;
                            }
                            return false;
                        }
                    });
                    tablaCatalogos.setItems(listaFiltrada);
                    tablaCatalogos.refresh();
                });
            }
            break;
            case "Servicios genéricos": {
                TableColumn id = new TableColumn("ID");
                TableColumn descripcion = new TableColumn("Descripcion");
                TableColumn costo = new TableColumn("Costo");

                listaServiciosGenericos.add(new ServiciosGenericos("1234", "Mano de obra", 500));
                listaServiciosGenericos.add(new ServiciosGenericos("1235", "Reparacion de equipo", 800));
                listaServiciosGenericos.add(new ServiciosGenericos("1236", "Reconstrucción", 1500));
                id.setCellValueFactory(new PropertyValueFactory("id"));
                descripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
                costo.setCellValueFactory(new PropertyValueFactory("costo"));

                TableColumn columnas[] = {id, descripcion, costo};
                tablaCatalogos.getColumns().setAll(columnas);

                listaFiltrada = new FilteredList(listaServiciosGenericos);
                tablaCatalogos.setItems(listaFiltrada);
                buscartxt.textProperty().addListener((Observable, oldValue, newValue)
                        -> {
                    listaFiltrada = null;
                    listaFiltrada = listaServiciosGenericos.filtered(new Predicate<ServiciosGenericos>() {
                        @Override
                        public boolean test(ServiciosGenericos t) {
                            if (newValue == null || newValue.isEmpty()) {
                                return true;
                            }
                            if (t.getId().contains(newValue)) {
                                return true;
                            }
                            if (t.getDescripcion().contains(newValue)) {
                                return true;
                            }
                            if (Double.toString(t.getCosto()).contains(newValue)) {
                                return true;
                            }

                            return false;
                        }
                    });
                    tablaCatalogos.setItems(listaFiltrada);
                    tablaCatalogos.refresh();
                });

            }
            break;
            case "Vehiculos": {
                TableColumn id = new TableColumn("ID");
                TableColumn tipo = new TableColumn("Tipo");
                TableColumn sucursal = new TableColumn("Sucursal");
                TableColumn gastoGasolina = new TableColumn("Gasto de gasolina (Km/l)");

                listaVehiculos.add(new Vehiculos("1234", "Automatico", "Ocotlan", "10"));
                listaVehiculos.add(new Vehiculos("1334", "Estandar", "Ocotlan", "20"));
                listaVehiculos.add(new Vehiculos("2443", "Hibrido", "Ocotlan", "40"));

                id.setCellValueFactory(new PropertyValueFactory("id"));
                tipo.setCellValueFactory(new PropertyValueFactory("tipo"));
                sucursal.setCellValueFactory(new PropertyValueFactory("sucursal"));
                gastoGasolina.setCellValueFactory(new PropertyValueFactory("gastoGasolina"));

                TableColumn columnasVehiculos[] = {id, tipo, sucursal, gastoGasolina};
                tablaCatalogos.getColumns().setAll(columnasVehiculos);

                listaFiltrada = new FilteredList(listaVehiculos);
                tablaCatalogos.setItems(listaFiltrada);

                buscartxt.textProperty().addListener((Observable, oldValue, newValue) -> {
                    listaFiltrada = null;
                    listaFiltrada = listaVehiculos.filtered(new Predicate<Vehiculos>() {
                        @Override
                        public boolean test(Vehiculos t) {
                            if (newValue == null || newValue.isEmpty()) {
                                return true;
                            }
                            if (t.getId().contains(newValue)) {
                                return true;
                            }
                            if (t.getTipo().contains(newValue)) {
                                return true;
                            }
                            if (t.getSucursal().contains(newValue)) {
                                return true;
                            }
                            if (t.getGastoGasolina().contains(newValue)) {
                                return true;
                            }

                            return false;
                        }
                    });
                    tablaCatalogos.setItems(listaFiltrada);
                    tablaCatalogos.refresh();
                });

            }
            break;
            case "Proyectos": {
                TableColumn idProyecto = new TableColumn("ID proyecto");
                TableColumn nombre = new TableColumn("Nombre");
                TableColumn descripcion = new TableColumn("Descripcion");
                TableColumn fechaContrato = new TableColumn("Fecha Contrato");
                TableColumn fechaEntrega = new TableColumn("Fecha Entrega");

                idProyecto.setCellValueFactory(new PropertyValueFactory("idProyecto"));
                nombre.setCellValueFactory(new PropertyValueFactory("nombre"));
                descripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
                fechaContrato.setCellValueFactory(new PropertyValueFactory("fechaContrato"));
                fechaEntrega.setCellValueFactory(new PropertyValueFactory("fechaEntrega"));

                Conexion con = new Conexion();
                Statement st;
                ResultSet rs;
                try {
                    st = con.getCon().createStatement();
                    rs = st.executeQuery("SELECT ID,NOMBRE,DESCRIPCION,FECHA_CONTRATO,FECHA_ENTREGA FROM PROYECTOS WHERE IS_ELIMINATED IS NULL");
                    while (rs.next()) {
                        listaProyectos.add(new Proyectos(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getDate(5)));
                    }
                    con.getCon().close();
                } catch (Exception eee) {

                }

                TableColumn columnasProyectos[] = {idProyecto, nombre, descripcion, fechaContrato, fechaEntrega};
                tablaCatalogos.getColumns().setAll(columnasProyectos);

                listaFiltrada = new FilteredList(listaProyectos);
                tablaCatalogos.setItems(listaFiltrada);

                buscartxt.textProperty().addListener((Observable, oldValue, newValue) -> {
                    listaFiltrada = null;
                    listaFiltrada = listaProyectos.filtered((new Predicate<Proyectos>() {
                        @Override
                        public boolean test(Proyectos t) {
                            if (newValue == null || newValue.isEmpty()) {
                                return true;
                            }
                            if (t.getIdProyecto().contains(newValue)) {
                                return true;
                            }
                            if (t.getNombre().contains(newValue)) {
                                return true;
                            }
                            if (t.getDescripcion().contains(newValue)) {
                                return true;
                            }
                            if (t.getFechaContrato().toString().contains(newValue)) {
                                return true;
                            }
                            if (t.getFechaEntrega().toString().contains(newValue)) {
                                return true;
                            }
                            return false;
                        }

                    }));
                    tablaCatalogos.setItems(listaFiltrada);
                    tablaCatalogos.refresh();
                });

            }
            break;

            default:

        }
        tablaCatalogos.refresh();
    }

    @Override
    public void initialize(URL url,
            ResourceBundle rb
    ) {

    }

    @FXML
    private void agregar(ActionEvent event
    ) {
        String seleccion = comboCatalogos.getSelectionModel().getSelectedItem();

        switch (seleccion) {
            case "Productos":
                new VistaCatalogosProductosAgregarController().abrirVistaCatalogosProductosAgregar(listaProductos);
                tablaCatalogos.refresh();
                break;
            case "Proveedores":
                new VistaCatalogosProveedoresAgregarController().abrirVistaCatalogosProveedoresAgregar(listaProveedores);
                tablaCatalogos.refresh();
                break;
            case "Personal":
                new VistaCatalogosPersonalAgregarController().abrirVistaCatalogosPersonalAgregar(listaPersonal);
                tablaCatalogos.refresh();
                break;
            case "Herramientas":
                new VistaCatalogosHerramientasAgregarController().abrirVistaCatalogosHerramientasAgregar(listaHerramientas);
                tablaCatalogos.refresh();
                break;
            case "Vendedores":
                new VistaCatalogosVendedoresAgregarController().abrirVistaCatalogosVendedoresAgregar(listaVendedores);
                tablaCatalogos.refresh();
                break;
            case "Empresas":
                new VistaCatalogosEmpresaAgregarController().abrirVistaCatalogosEmpresaAgregar(listaEmpresas);
                tablaCatalogos.refresh();
                break;
            case "Equipos":
                new VistaCatalogosEquiposAgregarController().abrirVistaCatalogosEquiposAgregar(listaEquipos);
                tablaCatalogos.refresh();
                break;
            case "Contratistas":
                new VistaCatalogosContratistasController().abrirVistaCatalogosContratistas(listaContratistas);
                tablaCatalogos.refresh();
                break;
            case "Servicios":

                break;
            case "Vehiculos":
                new VistaCatalogosVehiculosAgregarController().abrirVistaCatalogosVehiculosAgregar(listaVehiculos);
                tablaCatalogos.refresh();
                break;
            case "Servicios genéricos":
                new VistaCatalogosServiciosGenericosAgregarController().abrirVistaCatalogosServiciosGenericosAgregar(listaServiciosGenericos);
                tablaCatalogos.refresh();
                break;
            case "Proyectos":
                new VistaCatalogosProyectosAgregarController().abrirVistaCatalogosProyectosAgregar(listaProyectos);
                tablaCatalogos.refresh();
                break;

            default:

        }

    }

    @FXML
    private void modificar(ActionEvent event
    ) {

        String seleccion = comboCatalogos.getSelectionModel().getSelectedItem();
        int indice = tablaCatalogos.getSelectionModel().getSelectedIndex();

        if (indice >= 0) {
            switch (seleccion) {
                case "Productos": {
                    Productos aux = (Productos) listaFiltrada.get(indice);
                    new VistaCatalogosProductosAgregarController().abrirVistaCatalogosProductosModificar(aux);
                    tablaCatalogos.refresh();
                }
                break;
                case "Proveedores": {
                    Proveedores aux = (Proveedores) listaFiltrada.get(indice);
                    new VistaCatalogosProveedoresAgregarController().abrirVistaCatalogosProveedoresModificar(aux);
                    tablaCatalogos.refresh();
                }
                break;
                case "Personal": {
                    Personal aux = (Personal) listaFiltrada.get(indice);
                    new VistaCatalogosPersonalAgregarController().abrirVistaCatalogosPersonalModificar(aux);
                    tablaCatalogos.refresh();
                }
                break;
                case "Herramientas": {
                    Herramientas aux = (Herramientas) listaFiltrada.get(indice);
                    new VistaCatalogosHerramientasAgregarController().abrirVistaCatalogosHerramientasModificar(aux);
                    tablaCatalogos.refresh();
                }
                break;
                case "Vendedores": {
                    Vendedores aux = (Vendedores) listaFiltrada.get(indice);
                    new VistaCatalogosVendedoresAgregarController().abrirVistaCatalogosVendedoresModificar(aux);
                    tablaCatalogos.refresh();
                }
                break;
                case "Empresas": {
                    Empresas aux = (Empresas) listaFiltrada.get(indice);
                    new VistaCatalogosEmpresaAgregarController().abrirVistaCatalogosEmpresaModificar(aux);
                    tablaCatalogos.refresh();
                }
                break;
                case "Equipos": {
                    Equipos aux = (Equipos) listaFiltrada.get(indice);
                    new VistaCatalogosEquiposAgregarController().abrirVistaCatalogosEquiposModificar(aux);
                    tablaCatalogos.refresh();
                }
                break;
                case "Contratistas": {
                    Contratistas aux = (Contratistas) listaFiltrada.get(indice);
                    new VistaCatalogosContratistasController().abrirVistaCatalogosContratistasModificar(aux);
                    tablaCatalogos.refresh();
                }
                break;
                case "Servicios":

                    break;
                case "Vehiculos": {
                    Vehiculos aux = (Vehiculos) listaFiltrada.get(indice);
                    new VistaCatalogosVehiculosAgregarController().abrirVistaCatalogosVehiculosModificar(aux);
                    tablaCatalogos.refresh();
                }
                break;
                case "Servicios genéricos": {
                    ServiciosGenericos aux = (ServiciosGenericos) listaFiltrada.get(indice);
                    new VistaCatalogosServiciosGenericosAgregarController().abrirVistaCatalogosEmpresaModificar(aux);
                    tablaCatalogos.refresh();
                }
                break;
                case "Proyectos": {
                    Proyectos aux = (Proyectos) listaFiltrada.get(indice);
                    new VistaCatalogosProyectosAgregarController().abrirVistaCatalogosProyectosModificar(aux);
                    tablaCatalogos.refresh();
                }
                break;

                default:
            }
        }
    }

    @FXML
    private void eliminar(ActionEvent event
    ) {
        if (tablaCatalogos.getSelectionModel().getSelectedItem() != null) {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Confirmación");
            alerta.setHeaderText(null);
            alerta.setContentText("¿Seguro que quiere eliminar este elemento?");
            alerta.showAndWait();

            String opcion = alerta.getResult().toString();
            if (opcion.contains("Aceptar")) {

                String seleccion = comboCatalogos.getSelectionModel().getSelectedItem();

                switch (seleccion) {
                    case "Productos": {
                        int aux = tablaCatalogos.getSelectionModel().getSelectedIndex();
                        Productos productoAux = listaProductos.get(aux);
                        Conexion con = new Conexion();
                        ResultSet rs;
                        Statement st;

                        try {
                            st = con.getCon().createStatement();
                            st.executeUpdate("DELETE FROM PRODUCTOS WHERE ID='" + productoAux.getId() + "'");

                            Statement stProvee = con.getCon().createStatement();
                            stProvee.executeUpdate("DELETE FROM PROVEE WHERE ID_PRODUCTO='" + productoAux.getId() + "'");

                            con.getCon().close();

                        } catch (MySQLIntegrityConstraintViolationException e) {

                            try {
                                st = con.getNewStatement(con.getCon());
                                st.executeUpdate("UPDATE PRODUCTOS SET IS_ELIMINATED = '*' WHERE ID='" + productoAux.getId() + "'");

                                con.getCon().close();

                            } catch (Exception ex) {
                                Alert alerta2 = new Alert(Alert.AlertType.ERROR);
                                alerta2.setTitle("ERROR");
                                alerta2.setHeaderText("Error al realizar una consulta en la base de datos");
                                alerta2.setContentText(ex.toString());
                                alerta2.showAndWait();
                            }

                        } catch (Exception e) {
                            Alert alerta2 = new Alert(Alert.AlertType.ERROR);
                            alerta2.setTitle("ERROR");
                            alerta2.setHeaderText("Error al realizar una consulta en la base de datos");
                            alerta2.setContentText(e.toString());
                            alerta2.showAndWait();
                        }
                        listaProductos.remove(aux);
                        tablaCatalogos.refresh();
                    }
                    break;
                    case "Proveedores": {
                        int aux = tablaCatalogos.getSelectionModel().getSelectedIndex();
                        Proveedores proveedorAux = listaProveedores.get(aux);
                        Conexion con = new Conexion();
                        ResultSet rs;
                        Statement st;

                        try {

                            st = con.getCon().createStatement();
                            st.executeUpdate("DELETE FROM PROVEE WHERE CODIGO_PROVEEDOR = '" + proveedorAux.getCodigo() + "'");

                            st = con.getCon().createStatement();
                            st.executeUpdate("DELETE FROM PROVEEDORES WHERE CODIGO='" + proveedorAux.getCodigo() + "'");

                            con.getCon().close();

                        } catch (MySQLIntegrityConstraintViolationException e) {

                            try {
                                st = con.getNewStatement(con.getCon());
                                st.executeUpdate("UPDATE PROVEEDORES SET IS_ELIMINATED = '*' WHERE CODIGO='" + proveedorAux.getCodigo() + "'");

                                con.getCon().close();

                            } catch (Exception ex) {
                                Alert alerta2 = new Alert(Alert.AlertType.ERROR);
                                alerta2.setTitle("ERROR");
                                alerta2.setHeaderText("Error al realizar una consulta en la base de datos");
                                alerta2.setContentText(ex.toString());
                                alerta2.showAndWait();
                            }

                        } catch (Exception e) {
                            Alert alerta2 = new Alert(Alert.AlertType.ERROR);
                            alerta2.setTitle("ERROR");
                            alerta2.setHeaderText("Error al realizar una consulta en la base de datos");
                            alerta2.setContentText(e.toString());
                            alerta2.showAndWait();
                        }
                        listaProveedores.remove(aux);
                        tablaCatalogos.refresh();
                    }
                    break;
                    case "Personal": {
                        int aux = tablaCatalogos.getSelectionModel().getSelectedIndex();
                        listaPersonal.remove(aux);
                        tablaCatalogos.refresh();
                    }
                    break;
                    case "Herramientas": {
                        int aux = tablaCatalogos.getSelectionModel().getSelectedIndex();
                        Herramientas herramientaAux = listaHerramientas.get(aux);
                        Conexion con = new Conexion();
                        ResultSet rs;
                        Statement st;

                        try {
                            st = con.getCon().createStatement();
                            st.executeUpdate("DELETE FROM HERRAMIENTAS WHERE CODIGO='" + herramientaAux.getCodigo() + "'");

                            con.getCon().close();

                        } catch (MySQLIntegrityConstraintViolationException e) {

                            try {
                                st = con.getNewStatement(con.getCon());
                                st.executeUpdate("UPDATE HERRAMIENTAS SET IS_ELIMINATED = '*' WHERE ID='" + herramientaAux.getCodigo() + "'");

                                con.getCon().close();

                            } catch (Exception ex) {
                                Alert alerta2 = new Alert(Alert.AlertType.ERROR);
                                alerta2.setTitle("ERROR");
                                alerta2.setHeaderText("Error al realizar una consulta en la base de datos");
                                alerta2.setContentText(ex.toString());
                                alerta2.showAndWait();
                            }

                        } catch (Exception e) {
                            Alert alerta2 = new Alert(Alert.AlertType.ERROR);
                            alerta2.setTitle("ERROR");
                            alerta2.setHeaderText("Error al realizar una consulta en la base de datos");
                            alerta2.setContentText(e.toString());
                            alerta2.showAndWait();
                        }
                        listaHerramientas.remove(aux);
                        tablaCatalogos.refresh();
                    }
                    break;
                    case "Vendedores": {
                        int aux = tablaCatalogos.getSelectionModel().getSelectedIndex();
                        listaVendedores.remove(aux);
                        tablaCatalogos.refresh();
                    }
                    break;
                    case "Empresas": {
                        int aux = tablaCatalogos.getSelectionModel().getSelectedIndex();
                        listaEmpresas.remove(aux);
                        tablaCatalogos.refresh();
                    }
                    break;
                    case "Equipos": {
                        int aux = tablaCatalogos.getSelectionModel().getSelectedIndex();
                        listaEquipos.remove(aux);
                        tablaCatalogos.refresh();
                    }
                    break;
                    case "Servicios": {
                        int aux = tablaCatalogos.getSelectionModel().getSelectedIndex();
                        listaServicios.remove(aux);
                        tablaCatalogos.refresh();
                        break;
                    }
                    case "Contratistas": {
                        int aux = tablaCatalogos.getSelectionModel().getSelectedIndex();
                        listaContratistas.remove(aux);
                        tablaCatalogos.refresh();
                        break;
                    }
                    case "Vehiculos": {
                        int aux = tablaCatalogos.getSelectionModel().getSelectedIndex();
                        listaVehiculos.remove(aux);
                        tablaCatalogos.refresh();
                        break;
                    }
                    case "Servicios genéricos": {
                        int aux = tablaCatalogos.getSelectionModel().getSelectedIndex();
                        listaServiciosGenericos.remove(aux);
                        tablaCatalogos.refresh();
                        break;
                    }
                    case "Proyectos": {
                        int aux = tablaCatalogos.getSelectionModel().getSelectedIndex();
                        Proyectos proyectoAux = listaProyectos.get(aux);
                        Conexion con = new Conexion();
                        ResultSet rs;
                        Statement st;

                        try {
                            st = con.getCon().createStatement();
                            st.executeUpdate("DELETE FROM PROYECTOS WHERE ID='" + proyectoAux.getIdProyecto() + "'");

                            con.getCon().close();

                        } catch (MySQLIntegrityConstraintViolationException e) {

                            try {
                                st = con.getNewStatement(con.getCon());
                                st.executeUpdate("UPDATE PROYECTOS SET IS_ELIMINATED = '*' WHERE ID='" + proyectoAux.getIdProyecto() + "'");

                                con.getCon().close();

                            } catch (Exception ex) {
                                Alert alerta2 = new Alert(Alert.AlertType.ERROR);
                                alerta2.setTitle("ERROR");
                                alerta2.setHeaderText("Error al realizar una consulta en la base de datos");
                                alerta2.setContentText(ex.toString());
                                alerta2.showAndWait();
                            }

                        } catch (Exception e) {
                            Alert alerta2 = new Alert(Alert.AlertType.ERROR);
                            alerta2.setTitle("ERROR");
                            alerta2.setHeaderText("Error al realizar una consulta en la base de datos");
                            alerta2.setContentText(e.toString());
                            alerta2.showAndWait();
                        }
                        listaProyectos.remove(aux);
                        tablaCatalogos.refresh();
                        break;
                    }
                    default:
                }
            }
        }

    }

    @FXML
    private void seleccionarElementoMouseClicked(MouseEvent event) {
        int indice = tablaCatalogos.getSelectionModel().getSelectedIndex();

        if (indice >= 0) {
            botonModificar.setDisable(false);
            botonEliminar.setDisable(false);
        }

        if (event.getClickCount() >= 2 && !isOpenByPrincipal) {
            comboOculto.getSelectionModel().select(0);
            VistaCatalogosController controladorAux = comboOculto.getSelectionModel().getSelectedItem();
            String catalogoSeleccionado = comboCatalogos.getSelectionModel().getSelectedItem();

            switch (catalogoSeleccionado) {
                case "Proveedores": {
                    if (controladorAux.getListaFiltrada().get(indice) instanceof Proveedores) {
                        Proveedores aux = (Proveedores) controladorAux.getListaFiltrada().get(indice);
                        datosARetornar = aux.getCodigo() + "|" + aux.getNombre() + "|" + aux.getTelefono() + "|" + aux.getDomicilio() + "|" + aux.getTipo();
                    }
                }
                break;
                case "Productos": {
                    if (controladorAux.getListaFiltrada().get(indice) instanceof Productos) {
                        Productos aux = (Productos) controladorAux.getListaFiltrada().get(indice);
                        datosARetornar = aux.getId() + "|" + aux.getNombreCorto() + "|" + aux.getDescripcion() + "|" + aux.getUnidad() + "|" + aux.getCosto() + "|" + "Producto" + "|" + aux.getClase() + "|" + aux.getSubclase();
                    }

                }

                break;
                case "Vendedores": {
                    if (controladorAux.getListaFiltrada().get(indice) instanceof Vendedores) {
                        Vendedores aux = (Vendedores) controladorAux.getListaFiltrada().get(indice);
                        datosARetornar = aux.getCodigo() + "|" + aux.getNombre();
                    }

                }
                break;
                case "Empresas": {
                    if (controladorAux.getListaFiltrada().get(indice) instanceof Empresas) {
                        Empresas aux = (Empresas) controladorAux.getListaFiltrada().get(indice);
                        //ID, Nombre,Calle,Numero, CP, Municipio, Estado, Pais
                        datosARetornar = aux.getId() + "|" + aux.getNombre() + "|" + aux.getCalle() + "|" + aux.getNumero() + "|" + aux.getCP() + "|" + aux.getMunicipio() + "|" + aux.getEstado() + "|" + aux.getPais();
                    }

                }
                break;
                case "Herramientas": {
                    if (controladorAux.getListaFiltrada().get(indice) instanceof Herramientas) {
                        Herramientas aux = (Herramientas) controladorAux.getListaFiltrada().get(indice);
                        datosARetornar = aux.getCodigo() + "|" + aux.getNombreCorto() + "|" + aux.getDescripcion() + "|" + "No aplica" + "|" + aux.getCosto() + "|" + "Herramienta";
                    }

                }
                break;
                case "Personal": {
                    if (controladorAux.getListaFiltrada().get(indice) instanceof Personal) {
                        Personal aux = (Personal) controladorAux.getListaFiltrada().get(indice);
                        datosARetornar = aux.getCodigo() + "|" + aux.getNombre() + "|" + aux.getPuesto() + "|" + aux.getCostoPorDia();
                    }

                }
                break;
                case "Vehiculos": {
                    if (controladorAux.getListaFiltrada().get(indice) instanceof Vehiculos) {
                        Vehiculos aux = (Vehiculos) controladorAux.getListaFiltrada().get(indice);
                        datosARetornar = aux.getId() + "|" + aux.getTipo() + "|" + aux.getSucursal() + "|" + aux.getGastoGasolina();
                    }

                }
                break;
                case "Equipos": {
                    if (controladorAux.getListaFiltrada().get(indice) instanceof Equipos) {
                        Equipos aux = (Equipos) controladorAux.getListaFiltrada().get(indice);
                        datosARetornar = aux.getFolio() + "|" + aux.getFecha() + "|" + aux.getNombreEquipo() + "|" + aux.getDescripcion() + "|" + aux.getModelo() + "|" + aux.getMarca() + "|" + aux.getCaudal() + "|" + aux.getPresion() + "|" + aux.getPotencia() + "|" + aux.getVoltaje() + "|" + aux.getAmperaje() + "|" + aux.getGasRefrigerante() + "|" + aux.getCapacidad() + "|" + aux.getTipo() + "|" + aux.getCosto();
                    }                         //        0                           1                                   2                                       3                                   4                                  5                            6                               7                               8                                   9                               10                              11                                              12                              13                          14                      

                }
                break;
                case "Servicios genéricos": {
                    if (controladorAux.getListaFiltrada().get(indice) instanceof ServiciosGenericos) {
                        ServiciosGenericos aux = (ServiciosGenericos) controladorAux.getListaFiltrada().get(indice);
                        datosARetornar = aux.getId() + "|" + aux.getDescripcion() + "|" + aux.getCosto();
                    }

                }
                break;
                case "Proyectos": {
                    if (controladorAux.getListaFiltrada().get(indice) instanceof Proyectos) {
                        Proyectos aux = (Proyectos) controladorAux.getListaFiltrada().get(indice);
                        datosARetornar = aux.getIdProyecto() + "|" + aux.getNombre() + "|" + aux.getDescripcion() + "|" + aux.getFechaContrato() + "|" + aux.getFechaEntrega();
                    }
                }
                break;

                default:

            }
            Stage stage = (Stage) botonAgregar.getScene().getWindow();
            stage.close();
        }

    }

    @FXML
    private void seleccionarElementoKeyTyped(KeyEvent event
    ) {
        if (event.getCode().toString().equalsIgnoreCase("ENTER") && !isOpenByPrincipal) {
            comboOculto.getSelectionModel().select(0);
            VistaCatalogosController controladorAux = comboOculto.getSelectionModel().getSelectedItem();
            String catalogoSeleccionado = comboCatalogos.getSelectionModel().getSelectedItem();
            int indice = tablaCatalogos.getSelectionModel().getSelectedIndex();

            switch (catalogoSeleccionado) {
                case "Proveedores": {
                    if (controladorAux.getListaFiltrada().get(indice) instanceof Proveedores) {
                        Proveedores aux = (Proveedores) controladorAux.getListaFiltrada().get(indice);
                        datosARetornar = aux.getCodigo() + "|" + aux.getNombre() + "|" + aux.getTelefono() + "|" + aux.getDomicilio() + "|" + aux.getTipo();

                    }
                }
                break;
                case "Productos": {
                    if (controladorAux.getListaFiltrada().get(indice) instanceof Productos) {
                        Productos aux = (Productos) controladorAux.getListaFiltrada().get(indice);
                        datosARetornar = aux.getId() + "|" + aux.getNombreCorto() + "|" + aux.getDescripcion() + "|" + aux.getUnidad() + "|" + aux.getCosto() + "|" + "Producto" + "|" + aux.getClase() + "|" + aux.getSubclase();
                    }

                }

                break;
                case "Vendedores": {
                    if (controladorAux.getListaFiltrada().get(indice) instanceof Vendedores) {
                        Vendedores aux = (Vendedores) controladorAux.getListaFiltrada().get(indice);
                        datosARetornar = aux.getCodigo() + "|" + aux.getNombre();
                    }

                }
                break;
                case "Empresas": {
                    if (controladorAux.getListaFiltrada().get(indice) instanceof Empresas) {
                        Empresas aux = (Empresas) controladorAux.getListaFiltrada().get(indice);
                        //ID, Nombre,Calle,Numero, CP, Municipio, Estado, Pais
                        datosARetornar = aux.getId() + "|" + aux.getNombre() + "|" + aux.getCalle() + "|" + aux.getNumero() + "|" + aux.getCP() + "|" + aux.getMunicipio() + "|" + aux.getEstado() + "|" + aux.getPais();
                    }

                }
                case "Herramientas": {
                    if (controladorAux.getListaFiltrada().get(indice) instanceof Herramientas) {
                        Herramientas aux = (Herramientas) controladorAux.getListaFiltrada().get(indice);
                        datosARetornar = aux.getCodigo() + "|" + aux.getNombreCorto() + "|" + aux.getDescripcion() + "|" + "No aplica" + "|" + aux.getCosto() + "|" + "Herramienta";
                    }

                }
                break;
                case "Personal": {
                    if (controladorAux.getListaFiltrada().get(indice) instanceof Personal) {
                        Personal aux = (Personal) controladorAux.getListaFiltrada().get(indice);
                        datosARetornar = aux.getCodigo() + "|" + aux.getNombre() + "|" + aux.getPuesto() + "|" + aux.getCostoPorDia();
                    }

                }
                break;
                case "Vehiculos": {
                    if (controladorAux.getListaFiltrada().get(indice) instanceof Vehiculos) {
                        Vehiculos aux = (Vehiculos) controladorAux.getListaFiltrada().get(indice);
                        datosARetornar = aux.getId() + "|" + aux.getTipo() + "|" + aux.getSucursal() + "|" + aux.getGastoGasolina();
                    }

                }
                break;
                case "Equipos": {
                    if (controladorAux.getListaFiltrada().get(indice) instanceof Equipos) {
                        Equipos aux = (Equipos) controladorAux.getListaFiltrada().get(indice);
                        datosARetornar = aux.getFolio() + "|" + aux.getFecha() + "|" + aux.getNombreEquipo() + "|" + aux.getDescripcion() + "|" + aux.getModelo() + "|" + aux.getMarca() + "|" + aux.getCaudal() + "|" + aux.getPresion() + "|" + aux.getPotencia() + "|" + aux.getVoltaje() + "|" + aux.getAmperaje() + "|" + aux.getGasRefrigerante() + "|" + aux.getCapacidad() + "|" + aux.getTipo() + "|" + aux.getCosto();
                    }                         //        0                           1                                   2                                       3                                   4                                  5                            6                               7                               8                                   9                               10                              11                                              12                              13                          14                      

                }
                break;
                case "Servicios genéricos": {
                    if (controladorAux.getListaFiltrada().get(indice) instanceof ServiciosGenericos) {
                        ServiciosGenericos aux = (ServiciosGenericos) controladorAux.getListaFiltrada().get(indice);
                        datosARetornar = aux.getId() + "|" + aux.getDescripcion() + "|" + aux.getCosto();
                    }

                }
                break;
                case "Proyectos": {
                    if (controladorAux.getListaFiltrada().get(indice) instanceof Proyectos) {
                        Proyectos aux = (Proyectos) controladorAux.getListaFiltrada().get(indice);
                        datosARetornar = aux.getIdProyecto() + "|" + aux.getNombre() + "|" + aux.getDescripcion() + "|" + aux.getFechaContrato() + "|" + aux.getFechaEntrega();
                    }
                }
                break;

                default:

            }
            Stage stage = (Stage) botonAgregar.getScene().getWindow();
            stage.close();
        }
    }

}
