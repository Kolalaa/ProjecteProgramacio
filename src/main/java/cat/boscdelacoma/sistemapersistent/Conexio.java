/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.boscdelacoma.sistemapersistent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexio {
    Connection con;

    public Conexio() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lol_equip_db", "root", "");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found: " + e);
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e);
        }
    }

    public static void main(String[] args) {
        Conexio cn = new Conexio();

        if (cn.con != null) {
            try (Statement st = cn.con.createStatement();
                 ResultSet rs = st.executeQuery("SELECT * FROM jugador")) {

                while (rs.next()) {
                    System.out.println(rs.getInt("id") + " " + rs.getString("nom"));
                }

            } catch (SQLException e) {
                System.err.println("SQL error: " + e);
            } finally {
                try {
                    cn.con.close();
                } catch (SQLException e) {
                    System.err.println("Error closing the connection: " + e);
                }
            }
        }
    }
}
