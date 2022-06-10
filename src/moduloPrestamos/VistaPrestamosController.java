package moduloPrestamos;

import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

public class VistaPrestamosController implements Initializable {

    @FXML
    private ComboBox comboPrestamos;
    @FXML
    private ImageView icono;
    @FXML
    private TableView tablaPrestamos;
    @FXML
    private TableColumn columnaSubclase1;
    @FXML
    private Button botonPrestamo;
    @FXML
    private Button botonDevolucion;
    @FXML
    private Button botonReposiciones;
    @FXML
    private TableColumn columnaFolio;
    @FXML
    private TableColumn columnaHerramienta;
    @FXML
    private TableColumn columnaPrestadoA;
    @FXML
    private TableColumn columnaFechaPrestamo;
    @FXML
    private TableColumn columnaFechaDevolucion;
    @FXML
    private TableColumn columnaEstado;
    @FXML
    private TableColumn columnaObservacionesPres;
    @FXML
    private TableColumn columnaObservacionesDev;
    @FXML
    private HBox hbox;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        icono.setImage(new Image("/iconos/prestamos.png"));
        
        BackgroundPosition b = new BackgroundPosition(Side.RIGHT, 0, true, Side.BOTTOM, 0, true);
        BackgroundImage bi = new BackgroundImage(new Image("Fondo2.png", 1894, 1420, false, true, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, b, BackgroundSize.DEFAULT);      
        hbox.setBackground(new Background(bi));       
        
    }
    
    public void abrirVistaPrestamos() {
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/moduloPrestamos/VistaPrestamos.fxml"));
            Parent root = loader.load();
            VistaPrestamosController controlador = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Nuevo prestamo");
            stage.setScene(scene);
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
    private void cambiarFiltro(ActionEvent event) {
    }

    @FXML
    private void abrirNuevoPrestamo(ActionEvent event) {
        new VistaNuevoPrestamoController().abrirVentanaNuevoPrestamo();
    }

    @FXML
    private void abrirDevoluciones(ActionEvent event) {
        new VistaPrestamosDevolucionesController().abrirVistaPrestamosDevoluciones();
    }
    
}
