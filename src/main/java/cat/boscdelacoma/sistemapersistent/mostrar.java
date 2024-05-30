/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.boscdelacoma.sistemapersistent;

import cat.boscdelacoma.sistemapersistent.model.business.entities.ADC;
import cat.boscdelacoma.sistemapersistent.model.business.entities.Jugador;
import cat.boscdelacoma.sistemapersistent.model.business.entities.Jungla;
import cat.boscdelacoma.sistemapersistent.model.business.entities.Support;
import cat.boscdelacoma.sistemapersistent.model.business.entities.Toplaner;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

public class mostrar implements Initializable {
    
    @FXML
    private TableView<Jugador> tableView;
    
    @FXML
    private TableColumn<Jugador, Integer> column1;
    
    @FXML
    private TableColumn<Jugador, String> column2;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize your TableView and TableColumns here
        column1.setCellValueFactory(new PropertyValueFactory<>("id"));
        column2.setCellValueFactory(new PropertyValueFactory<>("nom"));

        // Connect to your database and fetch data
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lol_equip_db", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM jugador");
            ObservableList<Jugador> list = FXCollections.observableArrayList();
            while (rs.next()) {
                Jugador j = null;
                String rol = rs.getString("rol");
                if ("Top Laner".equals(rol)) {
                    j = new Toplaner(rs.getInt("id"), rs.getString("nom"), rs.getInt("edat"), rs.getInt("kills"), rs.getInt("assists"), rs.getInt("morts"));
                } else if ("Jungla".equals(rol)) {
                    j = new Jungla(rs.getInt("id"), rs.getString("nom"), rs.getInt("edat"), rs.getInt("kills"), rs.getInt("assists"), rs.getInt("morts"), rs.getInt("barons"));
                } else if ("Support".equals(rol)) {
                    j = new Support(rs.getInt("id"), rs.getString("nom"), rs.getInt("edat"), rs.getInt("kills"), rs.getInt("assists"), rs.getInt("morts"));
                } else if ("ADC".equals(rol)) {
                    j = new ADC(rs.getInt("id"), rs.getString("nom"), rs.getInt("edat"), rs.getInt("kills"), rs.getInt("assists"), rs.getInt("morts"));
                }
                list.add(j);
            }
            tableView.setItems(list);
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
