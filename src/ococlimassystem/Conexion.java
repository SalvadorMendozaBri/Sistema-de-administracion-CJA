/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ococlimassystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.Alert;

public class Conexion {

    private Connection con;

    public Conexion() {
        try {

            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ocsmodular", "root", "");

        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERROR");
            alerta.setHeaderText("Error al vincular con la base de datos");
            alerta.setContentText(e.toString());
            alerta.showAndWait();
        }
    }

    public Connection getCon() {
        return con;
    }

    public Statement getNewStatement(Connection con) {
        try {
            return con.createStatement();
        } catch (Exception e) {

        }
        return null;
    }

}
