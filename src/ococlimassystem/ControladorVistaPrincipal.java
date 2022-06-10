/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ococlimassystem;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import moduloEntradas.VistaEntradasController;
import moduloInventarios.VistaInventariosController;
import moduloSalidas.VistaSalidasController;
import Catalogos.Catalogos;
import Catalogos.VistaCatalogosController;
import cotizaciones.VistaListadoCotizacionesController;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import moduloPrestamos.VistaPrestamosController;
import preoportunidades.VistaListadoDePreoportunidadesController;
import prospectos.VistaListadoDeProspectosController;

/**
 *
 * @author chava
 */
public class ControladorVistaPrincipal implements Initializable {

    @FXML
    private Button botonInventario;
    @FXML
    private Button botonEntradas;
    @FXML
    private Button botonCatalogos;
    @FXML
    private Button botonSalidas;
    
    private ImageView imagenFondoPrincipal;
    
    @FXML
    private ImageView ImagenLogoPrincipal;
    @FXML
    private StackPane stack;
    @FXML
    private AnchorPane Anchor;
    @FXML
    private Button botonIPrestamos;
    @FXML
    private ImageView ImagenLogoPrincipal2;
    @FXML
    private Button botonProspectos;
    @FXML
    private Button botonPreoportunidades;
    @FXML
    private Button botonCotizaciones;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ImagenLogoPrincipal.setImage(new Image("/iconos/logoPrincipal.png"));
        ImagenLogoPrincipal2.setImage(new Image("/iconos/logoPrincipal.png"));
        botonInventario.setGraphic(new ImageView(new Image("/iconos/iconoInventario.png",30,30,false,true)));
        botonEntradas.setGraphic(new ImageView(new Image("/iconos/Entradas.png",30,30,false,true)));
        botonSalidas.setGraphic(new ImageView(new Image("/iconos/Salidas.png",30,30,false,true)));
        botonCatalogos.setGraphic(new ImageView(new Image("/iconos/catalogos.png",30,30,false,true)));
        botonIPrestamos.setGraphic(new ImageView(new Image("/iconos/prestamos.png",30,30,false,true)));
        botonCotizaciones.setGraphic(new ImageView(new Image("/iconos/logoCotizaciones.png",30,30,false,true)));
        botonProspectos.setGraphic(new ImageView(new Image("/iconos/prospecto1.png",30,30,false,true)));
        botonPreoportunidades.setGraphic(new ImageView(new Image("/iconos/LogoPreoportunidad.png",30,30,false,true)));
        
    }
    
    public ImageView getImagenFondoPrincipal() {
        return imagenFondoPrincipal;
    }

    @FXML
    private void abrirInventario(ActionEvent event) {
        new VistaInventariosController().abrirVentanaInventarios(true);   

    }

    @FXML
    private void abrirEntradas(ActionEvent event) {
        new VistaEntradasController().abrirVentanaEntradas();

    }

    @FXML
    private void abrirCatalogos(ActionEvent event) {
         new VistaCatalogosController().abrirCatalogosPrincipal(null);
    }

    @FXML
    private void abrirSalidas(ActionEvent event) {
       new VistaSalidasController().abrirVentanaSalidas();
    }

    @FXML
    private void abrirPrestamos(ActionEvent event) {
        new VistaPrestamosController().abrirVistaPrestamos();
    }

    @FXML
    private void abrirProspectos(ActionEvent event) {
        new VistaListadoDeProspectosController().abrirVistaListadoDeProspectos(null);
    }

    @FXML
    private void abrirPreoportunidades(ActionEvent event) {
        new VistaListadoDePreoportunidadesController().abrirVistaListadoDePreoportunidades();
    }

    @FXML
    private void abrirCotizaciones(ActionEvent event) {
        new VistaListadoCotizacionesController().abrirVistaListadoDeCotizaciones();
    }

}
