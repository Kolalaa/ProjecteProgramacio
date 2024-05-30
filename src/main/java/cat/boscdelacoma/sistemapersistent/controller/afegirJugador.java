package cat.boscdelacoma.sistemapersistent.controller;

import cat.boscdelacoma.sistemapersistent.model.persistence.daos.MySQLPersistencia;
import cat.boscdelacoma.sistemapersistent.model.business.entities.*;
import java.sql.Connection;
import java.sql.DriverManager;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class afegirJugador {
    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField ageField;
    @FXML
    private TextField roleField;
    @FXML
    private TextField killsField;
    @FXML
    private TextField assistsField;
    @FXML
    private TextField deathsField;
    @FXML
    private TextField baronsField;

    private MySQLPersistencia persistencia;
    private Equip equip;

    public void setPersistencia(MySQLPersistencia persistencia) {
    this.persistencia = persistencia;
}


    public void setEquip(Equip equip) {
        this.equip = equip;
    }

    @FXML
private void handleAddPlayer() {
    try {
        Connection conn = DriverManager.getConnection(MySQLPersistencia.URL, MySQLPersistencia.USER, "");
        
        int id = Integer.parseInt(idField.getText());
        String name = nameField.getText();
        int age = Integer.parseInt(ageField.getText());
        String role = roleField.getText();
        int kills = Integer.parseInt(killsField.getText());
        int assists = Integer.parseInt(assistsField.getText());
        int deaths = Integer.parseInt(deathsField.getText());
        
        Jugador jugador = null;
        if ("Jungla".equalsIgnoreCase(role)) {
            int barons = Integer.parseInt(baronsField.getText());
            jugador = new Jungla(id, name, age, kills, assists, deaths, barons);
        } else if ("Top Laner".equalsIgnoreCase(role)) {
            jugador = new Toplaner(id, name, age, kills, assists, deaths);
        } else if ("ADC".equalsIgnoreCase(role)) {
            jugador = new ADC(id, name, age, kills, assists, deaths);
        } else if ("Support".equalsIgnoreCase(role)) {
            jugador = new Support(id, name, age, kills, assists, deaths);
        }

        if (jugador != null) {
            persistencia.guardarJugador(conn, jugador, 3); // Assuming equip ID is 3
            equip.afegirJugador(jugador);
        }

        // Close the connection
        conn.close();

        // Close the window
        Stage stage = (Stage) idField.getScene().getWindow();
        stage.close();
    } catch (SQLException | NumberFormatException e) {
        e.printStackTrace();
    }
}

}

