package cat.boscdelacoma.sistemapersistent;

import cat.boscdelacoma.sistemapersistent.model.business.entities.*;
import cat.boscdelacoma.sistemapersistent.model.persistence.daos.MySQLPersistencia;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class App extends Application {
    private MySQLPersistencia persistencia;

    @Override
    public void start(Stage primaryStage) {
        
        
        String usuari = "root";
        
        String password = "";
        persistencia = new MySQLPersistencia(usuari, password);

        VBox root = new VBox();
        Scene scene = new Scene(root, 800, 600);

        Label teamLabel = new Label("Teams");
        ListView<Equip> teamListView = new ListView<>();
        Button addTeamButton = new Button("Afegir Equip");
        Button editTeamButton = new Button("Editar Equip");
        Button deleteTeamButton = new Button("Eliminar Equip");
        Button addPlayerButton = new Button("Afegir jugador");

        root.getChildren().addAll(teamLabel, teamListView, addTeamButton, editTeamButton, deleteTeamButton);

        addTeamButton.setOnAction(e -> {
            // Logic to add a team
        });

        editTeamButton.setOnAction(e -> {
            // Logic to edit a team
        });

        deleteTeamButton.setOnAction(e -> {
            // Logic to delete a team
        });
        
        addPlayerButton.setOnAction(e -> {
            
        });

        primaryStage.setScene(scene);
        primaryStage.setTitle("Team Manager");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
