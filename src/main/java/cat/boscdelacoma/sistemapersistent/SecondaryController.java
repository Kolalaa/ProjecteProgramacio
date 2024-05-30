package cat.boscdelacoma.sistemapersistent;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import cat.boscdelacoma.sistemapersistent.controller.afegirJugador;
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
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void mostrarDades() throws IOException {
        App.setRoot("mostrar");
    }

    @FXML
    private void afegirJugador() throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("afegirJugador.fxml"));
    Parent root = loader.load();

    afegirJugador controller = loader.getController();
    persistencia = new MySQLPersistencia("jdbc:mysql://localhost:3306/lol_equip_db", "root", "");
        
        // Set persistencia and equip objects
    controller.setPersistencia(persistencia);
    controller.setEquip(equip);

    Stage stage = new Stage();
    stage.setTitle("Afegir Jugador");
    stage.setScene(new Scene(root));
    stage.show();
}

}
