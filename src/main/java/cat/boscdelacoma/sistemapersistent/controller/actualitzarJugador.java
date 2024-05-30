package cat.boscdelacoma.sistemapersistent.controller;

import cat.boscdelacoma.sistemapersistent.model.persistence.daos.MySQLPersistencia;
import cat.boscdelacoma.sistemapersistent.model.business.entities.Jugador;
import cat.boscdelacoma.sistemapersistent.model.business.entities.Jungla;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;

public class actualitzarJugador {
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

    public void setPersistencia(MySQLPersistencia persistencia) {
        this.persistencia = persistencia;
    }

    @FXML
    private void handleUpdatePlayer() {
        try (Connection conn = persistencia.connect()) {
            // Validate input fields
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String role = roleField.getText();
            int kills = Integer.parseInt(killsField.getText());
            int assists = Integer.parseInt(assistsField.getText());
            int deaths = Integer.parseInt(deathsField.getText());
            int barons = "Jungla".equalsIgnoreCase(role) ? Integer.parseInt(baronsField.getText()) : 0;

            // Retrieve the player details from the database
            Jugador jugador = persistencia.obtenirJugadorPerId(id);

            // Update the player details based on the input fields
            jugador.setNom(name);
            jugador.setEdat(age);
            jugador.setRol(role);
            jugador.setKills(kills);
            jugador.setAssists(assists);
            jugador.setMorts(deaths);

            // If the player is a Jungla, update the barons field
            if (jugador instanceof Jungla) {
                ((Jungla) jugador).setBarons(barons);
            }

            // Update the player in the database
            persistencia.updateJugador(conn, jugador);

            // Close the window
            Stage stage = (Stage) idField.getScene().getWindow();
            stage.close();
        } catch (SQLException | NumberFormatException e) {
            showError("Error updating player", e);
        }
    }

    private void showError(String message, Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(message);
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }
}
