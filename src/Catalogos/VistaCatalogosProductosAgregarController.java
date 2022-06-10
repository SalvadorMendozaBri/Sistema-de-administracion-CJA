/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Catalogos;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Comparator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ococlimassystem.Conexion;

/**
 * FXML Controller class
 *
 * @author chava
 */
public class VistaCatalogosProductosAgregarController implements Initializable {

    @FXML
    private TextArea txtDescripcion;
    @FXML
    private TextField txtUnidad;
    @FXML
    private TextField txtID;
    @FXML
    private TextField txtCosto;
    @FXML
    private TextField txtClase;
    @FXML
    private TextField txtSubclase;
    @FXML
    private TextField txtNombreCorto;
    @FXML
    private Button botonCancelar;
    @FXML
    private Button botonGuardar;
    @FXML
    private Button seleccionarUnidad;
    @FXML
    private TableView tablaProveedores;
    @FXML
    private TableColumn columnaCodigo;
    @FXML
    private TableColumn columnaNombre;
    @FXML
    private TableColumn columnaTelefono;
    @FXML
    private TableColumn columnaDomicilio;
    @FXML
    private TableColumn columnaTipo;
    @FXML
    private Button botonAgregar;
    @FXML
    private Button botonEliminar;

    @FXML
    private Button seleccionarClase;
    @FXML
    private Button seleccionarSubclase;

    ObservableList listaProductos;
    Productos productoAmodificar;
    String motivoApertura;
    ObservableList<Proveedores> listaProveedores;
    String arrayAux[];
    String arrayUnidad[];
    String arrayClase[];
    String arraySubClase[];
    int arrayIDsAModificar[];

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initAttributes(ObservableList listaProductos) {
        motivoApertura = "agregar";
        this.listaProductos = listaProductos;

        Conexion con = new Conexion();

        ResultSet rs;
        Statement st;

        try {
            st = con.getCon().createStatement();
            rs = st.executeQuery("SELECT `AUTO_INCREMENT`\n"
                    + "FROM  INFORMATION_SCHEMA.TABLES\n"
                    + "WHERE TABLE_SCHEMA = 'ocsmodular'\n"
                    + "AND   TABLE_NAME   = 'productos'");
            rs.next();
            txtID.setText(rs.getInt(1) + "");

            con.getCon().close();

        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERROR");
            alerta.setHeaderText("Error al realizar una consulta en la base de datos");
            alerta.setContentText(e.toString());
            alerta.showAndWait();
        }
        botonAgregar.setGraphic(new ImageView(new Image("/iconos/masLogo.png", 20, 20, false, true)));
        botonEliminar.setGraphic(new ImageView(new Image("/iconos/iconoBotonEliminar.png", 20, 20, false, true)));
        initColumnas();
    }

    public void initAttributesModificar(Productos productoAModificar) {
        arrayIDsAModificar = new int[3];
        motivoApertura = "modificar";
        this.productoAmodificar = productoAModificar;

        botonAgregar.setGraphic(new ImageView(new Image("/iconos/masLogo.png", 20, 20, false, true)));
        botonEliminar.setGraphic(new ImageView(new Image("/iconos/iconoBotonEliminar.png", 20, 20, false, true)));
        initColumnas();

        Conexion con = new Conexion();
        ResultSet rs;
        Statement st;

        try {
            st = con.getCon().createStatement();
            rs = st.executeQuery("SELECT PROVEEDORES.CODIGO, PROVEEDORES.NOMBRE, PROVEEDORES.TELEFONO, PROVEEDORES.DOMICILIO, PROVEEDORES.TIPO "
                    + "FROM PROVEE, PRODUCTOS, PROVEEDORES WHERE PRODUCTOS.ID = PROVEE.ID_PRODUCTO AND PROVEEDORES.CODIGO = PROVEE.CODIGO_PROVEEDOR "
                    + "AND PROVEE.ID_PRODUCTO = '" + productoAModificar.getId() + "'");

            while (rs.next()) {
                listaProveedores.add(new Proveedores(Integer.toString(rs.getInt(1)), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }

            st = con.getCon().createStatement();
            ResultSet rsIDs = st.executeQuery("SELECT UNIDAD, CLASE, SUBCLASE FROM PRODUCTOS WHERE ID = '" + productoAModificar.getId() + "'");
            rsIDs.next();

            arrayIDsAModificar[0] = rsIDs.getInt(1);
            arrayIDsAModificar[1] = rsIDs.getInt(2);
            arrayIDsAModificar[2] = rsIDs.getInt(3);

            con.getCon().close();

        } catch (Exception e) {
            Alert alerta2 = new Alert(Alert.AlertType.ERROR);
            alerta2.setTitle("ERROR");
            alerta2.setHeaderText("Error al realizar una consulta en la base de datos");
            alerta2.setContentText(e.toString());
            alerta2.showAndWait();
        }

        txtID.setText(productoAmodificar.getId());
        txtNombreCorto.setText(productoAmodificar.getNombreCorto());
        txtDescripcion.setText(productoAmodificar.getDescripcion());
        txtUnidad.setText(productoAmodificar.getUnidad());
        //proveedores de obtienen desde la base de datos
        txtCosto.setText(productoAmodificar.getCosto() + "");
        txtClase.setText(productoAmodificar.getClase());
        txtSubclase.setText(productoAmodificar.getSubclase());

    }

    public void initColumnas() {

        listaProveedores = FXCollections.observableArrayList();
        columnaCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        columnaTelefono.setCellValueFactory(new PropertyValueFactory("telefono"));
        columnaDomicilio.setCellValueFactory(new PropertyValueFactory("domicilio"));
        columnaTipo.setCellValueFactory(new PropertyValueFactory("tipo"));
        tablaProveedores.setItems(listaProveedores);

    }

    public void abrirVistaCatalogosProductosAgregar(ObservableList listaProductos) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Catalogos/VistaCatalogosProductosAgregar.fxml"));
            Parent root = loader.load();
            VistaCatalogosProductosAgregarController controlador = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Agregar producto");
            stage.setOnCloseRequest(e -> {
                e.consume();
                cancelarOnClose(stage);
            });

            controlador.initAttributes(listaProductos);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText(e.toString());
            alerta.setContentText("Error en el loader");
            alerta.showAndWait();
        }
    }

    public void abrirVistaCatalogosProductosModificar(Productos productoAModificar) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Catalogos/VistaCatalogosProductosAgregar.fxml"));
            Parent root = loader.load();
            VistaCatalogosProductosAgregarController controlador = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Modificar producto");
            stage.setOnCloseRequest(e -> {
                e.consume();
                cancelarOnClose(stage);
            });

            controlador.initAttributesModificar(productoAModificar);
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
        try {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Confirmación");
            alerta.setHeaderText(null);
            alerta.setContentText("¿Desea guardar los cambios?");
            alerta.showAndWait();

            String opcion = alerta.getResult().toString();
            if (opcion.contains("Aceptar")) {
                System.out.println(motivoApertura);
                switch (motivoApertura) {
                    case "agregar": {
                        String id = txtID.getText();
                        String nombreCorto = txtNombreCorto.getText();
                        String descripcion = txtDescripcion.getText();
                        String unidad = txtUnidad.getText();
                        int i = 0;
                        String proveedores = "";
                        while (i < listaProveedores.size()) {
                            proveedores = proveedores + listaProveedores.get(i).getNombre() + "\n";
                            i++;
                        }
                        double costo = Double.parseDouble(txtCosto.getText());
                        String clase = txtClase.getText();
                        String subClase = txtSubclase.getText();

                        listaProductos.add(new Productos(id, nombreCorto, descripcion, unidad, costo, clase, subClase, proveedores));

                        Conexion con = new Conexion();
                        ResultSet rs;
                        Statement st;

                        try {
                            st = con.getCon().createStatement();
                            st.executeUpdate("INSERT INTO PRODUCTOS (ID, NOMBRE_CORTO, DESCRIPCION, UNIDAD, COSTO, CLASE, SUBCLASE) VALUES"
                                    + "(DEFAULT,'" + nombreCorto + "','" + descripcion + "','" + arrayUnidad[0] + "','" + costo + "','" + arrayClase[0] + "','" + arraySubClase[0] + "')");
                            int j = 0;
                            while (j < listaProveedores.size()) {
                                Statement stProveedores = con.getNewStatement(con.getCon());
                                stProveedores.executeUpdate("INSERT INTO PROVEE (ID_PRODUCTO,CODIGO_PROVEEDOR) VALUES ('" + id + "','" + listaProveedores.get(j).getCodigo() + "')");
                                j++;
                            }

                            con.getCon().close();

                        } catch (Exception e) {
                            Alert alerta2 = new Alert(Alert.AlertType.ERROR);
                            alerta2.setTitle("ERROR");
                            alerta2.setHeaderText("Error al realizar una consulta en la base de datos");
                            alerta2.setContentText(e.toString());
                            alerta2.showAndWait();
                        }

                        txtID.setText("");
                        txtNombreCorto.setText("");
                        txtDescripcion.setText("");
                        txtUnidad.setText("");
                        txtCosto.setText("");
                        txtClase.setText("");
                        txtSubclase.setText("");
                        listaProveedores.clear();

                        Alert alertaGuardar = new Alert(Alert.AlertType.INFORMATION);
                        alertaGuardar.setTitle("Datos guardados");
                        alertaGuardar.setHeaderText(null);
                        alertaGuardar.setContentText("Los datos han sido guardardos correctamente");
                        alertaGuardar.showAndWait();

                    }
                    break;
                    case "modificar": {

                        productoAmodificar.setId(txtID.getText());
                        productoAmodificar.setNombreCorto(txtNombreCorto.getText());
                        productoAmodificar.setDescripcion(txtDescripcion.getText());
                        productoAmodificar.setUnidad(txtUnidad.getText());
                        int i = 0;
                        String proveedores = "";
                        while (i < listaProveedores.size()) {
                            proveedores = proveedores + listaProveedores.get(i).getNombre() + "\n";
                            i++;
                        }

                        productoAmodificar.setProveedores(proveedores);
                        productoAmodificar.setCosto(Double.parseDouble(txtCosto.getText()));
                        productoAmodificar.setClase(txtClase.getText());
                        productoAmodificar.setSubclase(txtSubclase.getText());

                        Conexion con = new Conexion();
                        ResultSet rs;
                        Statement st;

                        if (arrayUnidad != null) {
                            arrayIDsAModificar[0] = Integer.parseInt(arrayUnidad[0]);
                        }
                        if (arrayClase != null) {
                            arrayIDsAModificar[1] = Integer.parseInt(arrayClase[0]);
                        }
                        if (arraySubClase != null) {
                            arrayIDsAModificar[2] = Integer.parseInt(arraySubClase[0]);
                        }

                        try {
                            st = con.getCon().createStatement();
                            st.executeUpdate("UPDATE PRODUCTOS SET NOMBRE_CORTO='" + txtNombreCorto.getText() + "',DESCRIPCION='" + txtDescripcion.getText() + "',CLASE='" + arrayIDsAModificar[1] + "',COSTO='" + txtCosto.getText() + "',UNIDAD='" + arrayIDsAModificar[0] + "',SUBCLASE='" + arrayIDsAModificar[2] + "'"
                                    + "WHERE ID = '" + txtID.getText() + "'");

                            //Eliminar los registros de provee para despues volver a registrar los nuevos proveedores 
                            Statement stDelete = con.getNewStatement(con.getCon());
                            stDelete.executeUpdate("DELETE FROM PROVEE WHERE ID_PRODUCTO = '" + txtID.getText() + "'");

                            int j = 0;
                            while (j < listaProveedores.size()) {
                                Statement stProveedores = con.getNewStatement(con.getCon());
                                stProveedores.executeUpdate("INSERT INTO PROVEE (ID_PRODUCTO,CODIGO_PROVEEDOR) VALUES ('" + txtID.getText() + "','" + listaProveedores.get(j).getCodigo() + "')");
                                j++;
                            }

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
                    default:

                }

            }
        } catch (NumberFormatException e) {

            Alert alertaGuardar = new Alert(Alert.AlertType.ERROR);
            alertaGuardar.setTitle("Formato de costo incorrecto");
            alertaGuardar.setHeaderText(null);
            alertaGuardar.setContentText("El costo debe tener un valor numerico");
            alertaGuardar.showAndWait();

        }

    }

    @FXML
    private void seleccionarUnidad(ActionEvent event) {
        VistaCatalogoSimplesController controlador = new VistaCatalogoSimplesController();
        controlador.abrirVistaCatalogoSimples("Unidad");
        arrayUnidad = controlador.getDatosARetornar().split("\\|");
        txtUnidad.setText(arrayUnidad[1]);
    }

    @FXML
    private void agregar(ActionEvent event) {

        VistaCatalogosController controlador = new VistaCatalogosController();
        controlador.abrirCatalogosPrincipal("Proveedores");
        arrayAux = controlador.getDatosARetornar().split("\\|");

        if (!buscarBinaria(arrayAux[0])) {

            listaProveedores.add(new Proveedores(arrayAux[0], arrayAux[1], arrayAux[2], arrayAux[3], arrayAux[4]));

        } else {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Duplicidad");
            alerta.setHeaderText(null);
            alerta.setContentText("Este elemento ya se encuentra en la lista");
            alerta.showAndWait();
        }

    }

    public boolean buscarBinaria(String codigo) {
        int inicio = 0;
        int fin = listaProveedores.size() - 1;
        int pos;
        while (inicio <= fin) {
            pos = (inicio + fin) / 2;
            if (listaProveedores.get(pos).getCodigo().equalsIgnoreCase(codigo)) {
                return true;
            } else if (listaProveedores.get(pos).getCodigo().compareTo(codigo) < 0) {
                inicio = pos + 1;
            } else {
                fin = pos - 1;
            }
        }
        return false;
    }

    @FXML
    private void eliminar(ActionEvent event) {
        if (tablaProveedores.getSelectionModel().getSelectedItem() != null) {

            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Confirmación");
            alerta.setHeaderText(null);
            alerta.setContentText("¿Seguro que quiere eliminar este elemento?");
            alerta.showAndWait();

            String opcion = alerta.getResult().toString();
            if (opcion.contains("Aceptar")) {

                Proveedores aux = (Proveedores) tablaProveedores.getSelectionModel().getSelectedItem();

                listaProveedores.remove(aux);
            }
        }
    }

    @FXML
    private void seleccionarClase(ActionEvent event) {
        VistaCatalogoSimplesController controlador = new VistaCatalogoSimplesController();
        controlador.abrirVistaCatalogoSimples("Clase");
        arrayClase = controlador.getDatosARetornar().split("\\|");
        txtClase.setText(arrayClase[1]);

    }

    @FXML
    private void seleccionarSubClase(ActionEvent event) {
        VistaCatalogoSimplesController controlador = new VistaCatalogoSimplesController();
        controlador.abrirVistaCatalogoSimples("SubClase");
        arraySubClase = controlador.getDatosARetornar().split("\\|");
        txtSubclase.setText(arraySubClase[1]);
    }

}
