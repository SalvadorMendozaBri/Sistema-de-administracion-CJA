
package moduloPrestamos;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import moduloEntradas.VistaEntradasAgregarController;

public class VistaPrestamosDevolucionesController implements Initializable {

    @FXML
    private Button botonAceptar;
    @FXML
    private Button botonCancelar;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
    public void abrirVistaPrestamosDevoluciones(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/moduloPrestamos/VistaPrestamosDevoluciones.fxml"));
            Parent root = loader.load();
            VistaPrestamosDevolucionesController controlador = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setTitle("Seleccionar prestamo");                    
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
    private void aceptar(ActionEvent event) {
        new VistaPrestamosDevolucionesPrincipalController().abrirVentanaPrestamosDevolucionesPrincipal();
    }

    @FXML
    private void cancelar(ActionEvent event) {
    }
    
}
