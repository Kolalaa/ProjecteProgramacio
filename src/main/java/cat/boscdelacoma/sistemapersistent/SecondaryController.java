package cat.boscdelacoma.sistemapersistent;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import cat.boscdelacoma.sistemapersistent.controller.afegirJugador;
import cat.boscdelacoma.sistemapersistent.controller.actualitzarJugador;
import cat.boscdelacoma.sistemapersistent.model.persistence.daos.MySQLPersistencia;
import cat.boscdelacoma.sistemapersistent.model.business.entities.Equip;

public class SecondaryController {

    private MySQLPersistencia persistencia;
    private Equip equip;

    public void setPersistencia(MySQLPersistencia persistencia) {
        this.persistencia = persistencia;
    }

    public void setEquip(Equip equip) {
        this.equip = equip;
    }

    @FXML
    private void switchToPrimary() {
        try {
            App.setRoot("primary");
        } catch (IOException e) {
            showError("Error switching to primary view", e);
        }
    }

    @FXML
    private void mostrarDades() {
        try {
            App.setRoot("mostrar");
        } catch (IOException e) {
            showError("Error switching to mostrar view", e);
        }
    }

    @FXML
    private void afegirJugador() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/cat/boscdelacoma/sistemapersistent/afegirJugador.fxml"));
            Parent root = loader.load();

            afegirJugador controller = loader.getController();
            if (persistencia == null) {
                persistencia = new MySQLPersistencia();
            }
            if (equip == null) {
                equip = new Equip(3, "Patata");
            }

            // Set persistencia and equip objects
            controller.setPersistencia(persistencia);
            controller.setEquip(equip);

            Stage stage = new Stage();
            stage.setTitle("Afegir Jugador");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            showError("Error loading afegirJugador view", e);
        }
    }

    @FXML
    private void modificarJugador() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("modificarJugador.fxml"));
            Parent root = loader.load();

            actualitzarJugador controller = loader.getController();
            if (persistencia == null) {
                persistencia = new MySQLPersistencia();
            }

            // Set persistencia object
            controller.setPersistencia(persistencia);

            Stage stage = new Stage();
            stage.setTitle("Modificar Jugador");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            showError("Error loading actualitzarJugador view", e);
        }
    }

    private void showError(String message, Exception e) {
        // Implement a method to show error alerts to the user
        System.err.println(message);
        e.printStackTrace();
    }
}
