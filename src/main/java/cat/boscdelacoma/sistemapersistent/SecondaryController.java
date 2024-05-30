package cat.boscdelacoma.sistemapersistent;

import java.io.IOException;
import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
    
    @FXML
    private void mostrarDades() throws IOException {
        App.setRoot("mostrar");
    }
}