/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.boscdelacoma.sistemapersistent;

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
import javafx.scene.control.cell.PropertyValueFactory;

public class mostrar implements Initializable {
    
    @FXML
    private TableView<Data> tableView;
    
    @FXML
    private TableColumn<Data, String> column1;
    
    @FXML
    private TableColumn<Data, String> column2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize your TableView and TableColumns here
        column1.setCellValueFactory(cellData -> cellData.getValue().value1Property());
        column2.setCellValueFactory(cellData -> cellData.getValue().value2Property());
        
        // Connect to your database and fetch data
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lol_equip_db", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM jugadors");
            
            column1.setCellFactory(new PropertyValueFactory<>("value1"));
            column2.setCellFactory(new PropertyValueFactory<>("value2"));
            
            while (rs.next()) {
                tableView.getItems().add(new Data(rs.getString("id"), rs.getString("nom")));
            }
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public class Data {
        private final StringProperty value1 = new SimpleStringProperty();
        private final StringProperty value2 = new SimpleStringProperty();
        
        public Data(String value1, String value2) {
            this.value1.set(value1);
            this.value2.set(value2);
        }
        
        public StringProperty value1Property() {
            return value1;
        }
        
        public StringProperty value2Property() {
            return value2;
        }
    }
}
