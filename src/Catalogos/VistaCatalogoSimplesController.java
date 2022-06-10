package Catalogos;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ococlimassystem.Conexion;

public class VistaCatalogoSimplesController implements Initializable {

    @FXML
    private TableView<UnidadesTipo> listView;
    @FXML
    private Button botonAgregar;
    @FXML
    private Button botonModificar;
    @FXML
    private Button botonEliminar;
    @FXML
    private Button botonSalir;
    @FXML
    private TextField txtBuscar;
    @FXML
    private ComboBox<VistaCatalogoSimplesController> comboOculto;

    ObservableList<UnidadesTipo> listaFiltrada;
    ObservableList<UnidadesTipo> lista;

    String datosARetornar;
    String catalogoACargar;
    @FXML
    private TableColumn columnaDato;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void initAttributes(String catalogoACargar) {
        this.catalogoACargar = catalogoACargar;
        lista = FXCollections.observableArrayList();
        columnaDato.setCellValueFactory(new PropertyValueFactory("datos"));

        switch (catalogoACargar) {
            case "Unidad": {

                Conexion con = new Conexion();

                ResultSet rs;
                Statement st;

                try {
                    st = con.getCon().createStatement();
                    rs = st.executeQuery("SELECT ID,DATO,TIPO FROM UNIDADES_TIPOS WHERE TIPO='Unidad' ");

                    while (rs.next()) {
                        lista.add(new UnidadesTipo(rs.getInt(1), rs.getString(2), rs.getString(3)));
                    }

                    con.getCon().close();

                } catch (Exception e) {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("ERROR");
                    alerta.setHeaderText("Error al realizar una consulta en la base de datos");
                    alerta.setContentText(e.toString());
                    alerta.showAndWait();
                }
                listView.setItems(lista);
            }
            break;
            case "Tipo_proveedor": {

                Conexion con = new Conexion();

                ResultSet rs;
                Statement st;

                try {
                    st = con.getCon().createStatement();
                    rs = st.executeQuery("SELECT ID,DATO,TIPO FROM UNIDADES_TIPOS WHERE TIPO='Tipo_proveedor' ");

                    while (rs.next()) {
                        lista.add(new UnidadesTipo(rs.getInt(1), rs.getString(2), rs.getString(3)));
                    }

                    con.getCon().close();

                } catch (Exception e) {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("ERROR");
                    alerta.setHeaderText("Error al realizar una consulta en la base de datos");
                    alerta.setContentText(e.toString());
                    alerta.showAndWait();
                }

                listView.setItems(lista);
            }
            break;
            case "Division_asignada": {

                lista.add(new UnidadesTipo(25, "Uno", "Division"));
                listView.setItems(lista);
            }
            break;
            case "Clase": {
                Conexion con = new Conexion();

                ResultSet rs;
                Statement st;

                try {
                    st = con.getCon().createStatement();
                    rs = st.executeQuery("SELECT ID,DATO,TIPO FROM UNIDADES_TIPOS WHERE TIPO='Clase' ");

                    while (rs.next()) {
                        lista.add(new UnidadesTipo(rs.getInt(1), rs.getString(2), rs.getString(3)));
                    }

                    con.getCon().close();

                } catch (Exception e) {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("ERROR");
                    alerta.setHeaderText("Error al realizar una consulta en la base de datos");
                    alerta.setContentText(e.toString());
                    alerta.showAndWait();
                }
                listView.setItems(lista);

            }
            break;
            case "SubClase": {
                Conexion con = new Conexion();

                ResultSet rs;
                Statement st;

                try {
                    st = con.getCon().createStatement();
                    rs = st.executeQuery("SELECT ID,DATO,TIPO FROM UNIDADES_TIPOS WHERE TIPO='SubClase' ");

                    while (rs.next()) {
                        lista.add(new UnidadesTipo(rs.getInt(1), rs.getString(2), rs.getString(3)));
                    }

                    con.getCon().close();

                } catch (Exception e) {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("ERROR");
                    alerta.setHeaderText("Error al realizar una consulta en la base de datos");
                    alerta.setContentText(e.toString());
                    alerta.showAndWait();
                }
                listView.setItems(lista);

            }
            break;
            case "Clase_hrta": {
                Conexion con = new Conexion();

                ResultSet rs;
                Statement st;

                try {
                    st = con.getCon().createStatement();
                    rs = st.executeQuery("SELECT ID,DATO,TIPO FROM UNIDADES_TIPOS WHERE TIPO='Clase_hrta' ");

                    while (rs.next()) {
                        lista.add(new UnidadesTipo(rs.getInt(1), rs.getString(2), rs.getString(3)));
                    }

                    con.getCon().close();

                } catch (Exception e) {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("ERROR");
                    alerta.setHeaderText("Error al realizar una consulta en la base de datos");
                    alerta.setContentText(e.toString());
                    alerta.showAndWait();
                }
                listView.setItems(lista);

            }
            break;
            default:

        }

        txtBuscar.textProperty().addListener((Observable, oldValue, newValue) -> {
            listaFiltrada = null;
            listaFiltrada = lista.filtered(new Predicate<UnidadesTipo>() {
                @Override
                public boolean test(UnidadesTipo t) {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    if (t.getDatos().contains(newValue)) {
                        return true;
                    }

                    return false;
                }
            });
            listView.setItems(listaFiltrada);
        });
        listView.setItems(lista);
    }

    public void abrirVistaCatalogoSimples(String catalogoACargar) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Catalogos/VistaCatalogoSimple.fxml"));
            Parent root = loader.load();
            VistaCatalogoSimplesController controlador = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Seleccionar");
            controlador.initAttributes(catalogoACargar);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            datosARetornar = controlador.getDatosARetornar();

        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText(e.toString());
            alerta.setContentText("Error en el loader");
            alerta.showAndWait();
        }
    }

    @FXML
    private void agregar(ActionEvent event) {
        HBox hbox = new HBox(10);
        hbox.setPadding(new Insets(10, 10, 10, 10));

        TextField txtString = new TextField();
        Button botonAgregar = new Button("Agregar");
        botonAgregar.setOnAction(e -> {

            Conexion con = new Conexion();

            ResultSet rs;
            Statement st;

            try {
                st = con.getCon().createStatement();

                switch (catalogoACargar) {
                    case "Tipo_proveedor":
                        st.executeUpdate("INSERT INTO UNIDADES_TIPOS (DATO, TIPO) VALUES ('" + txtString.getText() + "','Tipo_proveedor') ");
                        st = con.getCon().createStatement();
                        rs = st.executeQuery("SELECT ID,DATO,TIPO FROM UNIDADES_TIPOS WHERE TIPO='Tipo_proveedor' AND ID = (SELECT MAX(ID) FROM UNIDADES_TIPOS)");
                        rs.next();
                        listView.getItems().add(new UnidadesTipo(rs.getInt(1), rs.getString(2), rs.getString(3)));
                        break;
                    case "Unidad":
                        st.executeUpdate("INSERT INTO UNIDADES_TIPOS (DATO, TIPO) VALUES ('" + txtString.getText() + "','Unidad') ");
                        st = con.getCon().createStatement();
                        rs = st.executeQuery("SELECT ID,DATO,TIPO FROM UNIDADES_TIPOS WHERE TIPO = 'Unidad' AND ID = (SELECT MAX(ID) FROM UNIDADES_TIPOS)");
                        rs.next();
                        listView.getItems().add(new UnidadesTipo(rs.getInt(1), rs.getString(2), rs.getString(3)));
                        break;
                    case "Clase":
                        st.executeUpdate("INSERT INTO UNIDADES_TIPOS (DATO, TIPO) VALUES ('" + txtString.getText() + "','Clase') ");
                        st = con.getCon().createStatement();
                        rs = st.executeQuery("SELECT ID,DATO,TIPO FROM UNIDADES_TIPOS WHERE TIPO = 'Clase' AND ID = (SELECT MAX(ID) FROM UNIDADES_TIPOS)");
                        rs.next();
                        listView.getItems().add(new UnidadesTipo(rs.getInt(1), rs.getString(2), rs.getString(3)));
                        break;
                    case "SubClase":
                        st.executeUpdate("INSERT INTO UNIDADES_TIPOS (DATO, TIPO) VALUES ('" + txtString.getText() + "','SubClase') ");
                        st = con.getCon().createStatement();
                        rs = st.executeQuery("SELECT ID,DATO,TIPO FROM UNIDADES_TIPOS WHERE TIPO = 'SubClase' AND ID = (SELECT MAX(ID) FROM UNIDADES_TIPOS)");
                        rs.next();
                        listView.getItems().add(new UnidadesTipo(rs.getInt(1), rs.getString(2), rs.getString(3)));
                        break;
                    case "Clase_hrta":
                        st.executeUpdate("INSERT INTO UNIDADES_TIPOS (DATO, TIPO) VALUES ('" + txtString.getText() + "','Clase_hrta') ");
                        st = con.getCon().createStatement();
                        rs = st.executeQuery("SELECT ID,DATO,TIPO FROM UNIDADES_TIPOS WHERE TIPO = 'Clase_hrta' AND ID = (SELECT MAX(ID) FROM UNIDADES_TIPOS)");
                        rs.next();
                        listView.getItems().add(new UnidadesTipo(rs.getInt(1), rs.getString(2), rs.getString(3)));
                        break;
                    default:

                }

                con.getCon().close();

            } catch (Exception ex) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("ERROR");
                alerta.setHeaderText("Error al realizar una consulta en la base de datos");
                alerta.setContentText(ex.toString());
                alerta.showAndWait();
            }

            Stage stage = (Stage) botonAgregar.getScene().getWindow();
            stage.close();
        });

        hbox.getChildren().addAll(txtString, botonAgregar);
        Scene scene = new Scene(hbox);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.showAndWait();

    }

    @FXML
    private void modificar(ActionEvent event) {
        HBox hbox = new HBox(10);
        hbox.setPadding(new Insets(10, 10, 10, 10));

        TextField txtString = new TextField(listView.getSelectionModel().getSelectedItem().getDatos());
        Button botonGuardar = new Button("Guardar");
        botonGuardar.setOnAction(e -> {
            int indice = listView.getSelectionModel().getSelectedIndex();
            UnidadesTipo objetoAModificar = listView.getItems().get(indice);
            Conexion con = new Conexion();

            ResultSet rs;
            Statement st;

            try {
                st = con.getCon().createStatement();
                st.executeUpdate("UPDATE UNIDADES_TIPOS SET DATO = '" + txtString.getText() + "' WHERE ID = '" + objetoAModificar.getId() + "'");

                st = con.getNewStatement(con.getCon());
                con.getCon().close();

            } catch (Exception ex) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("ERROR");
                alerta.setHeaderText("Error al realizar una consulta en la base de datos");
                alerta.setContentText(ex.toString());
                alerta.showAndWait();
            }

            listView.refresh();

            Stage stage = (Stage) botonGuardar.getScene().getWindow();
            stage.close();

        });

        hbox.getChildren().addAll(txtString, botonGuardar);
        Scene scene = new Scene(hbox);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.showAndWait();
    }

    @FXML
    private void eliminar(ActionEvent event) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Confirmación");
        alerta.setHeaderText(null);
        alerta.setContentText("¿Seguro que quiere eliminar el elemento " + listView.getSelectionModel().getSelectedItem().getDatos() + " ?");
        alerta.showAndWait();

        if (alerta.getResult().toString().contains("Aceptar")) {
            int indice = listView.getSelectionModel().getSelectedIndex();
            UnidadesTipo objetoAEliminar = listView.getItems().get(indice);
            Conexion con = new Conexion();

            ResultSet rs;
            Statement st;

            try {
                st = con.getCon().createStatement();
                st.executeUpdate("DELETE FROM UNIDADES_TIPOS WHERE ID='" + objetoAEliminar.getId() + "'");

                con.getCon().close();

            } catch (Exception ex) {
                Alert alerta2 = new Alert(Alert.AlertType.ERROR);
                alerta2.setTitle("ERROR");
                alerta2.setHeaderText("Error al realizar una consulta en la base de datos");
                alerta2.setContentText(ex.toString());
                alerta2.showAndWait();
            }

            listView.getItems().remove(indice);
        }

    }

    @FXML
    private void salir(ActionEvent event) {
        Stage stage = (Stage) botonAgregar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void seleccionarMouseClicked(MouseEvent event) {

        if (listView.getSelectionModel().getSelectedItem() != null) {
            botonModificar.setDisable(false);
            botonEliminar.setDisable(false);
        }

        if (event.getClickCount() >= 2) {
            UnidadesTipo aux = listView.getSelectionModel().getSelectedItem();
            datosARetornar = aux.getId() + "|"+aux.getDatos()+"|"+aux.getTipo();
            
            Stage stage = (Stage) botonAgregar.getScene().getWindow();
            stage.close();
        }

    }

    private void seleccionarKeyTyped(KeyEvent event) {
        if (event.getCode().toString().equalsIgnoreCase("ENTER")) {
            datosARetornar = listView.getSelectionModel().getSelectedItem().getDatos();
            System.out.println(datosARetornar);
            Stage stage = (Stage) botonAgregar.getScene().getWindow();
            stage.close();
        }
    }

    public String getDatosARetornar() {
        return datosARetornar;
    }

}
