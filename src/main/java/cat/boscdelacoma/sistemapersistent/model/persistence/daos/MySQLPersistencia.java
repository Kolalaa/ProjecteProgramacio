/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.boscdelacoma.sistemapersistent.model.persistence.daos;
import cat.boscdelacoma.sistemapersistent.model.business.entities.Jugador;
import cat.boscdelacoma.sistemapersistent.model.business.entities.Equip;
import cat.boscdelacoma.sistemapersistent.model.business.entities.Jungla;
import cat.boscdelacoma.sistemapersistent.model.business.entities.Toplaner;
import cat.boscdelacoma.sistemapersistent.model.business.entities.ADC;
import cat.boscdelacoma.sistemapersistent.model.business.entities.Support;

/**
 *
 * @author Sergi
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLPersistencia {
    private static final String URL = "jdbc:mysql://localhost:3306/lol_equip_db";
    private String user;
    private String password;

    public MySQLPersistencia(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, user, password);
    }

    public void guardarEquip(Equip equip) throws SQLException {
        try (Connection conn = connect()) {
            String query = "INSERT INTO equips (id, nom) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, equip.getId());
            pstmt.setString(2, equip.getNom());
            pstmt.executeUpdate();

            for (Jugador jugador : equip.getJugadors()) {
                guardarJugador(conn, jugador, equip.getId());
            }
        }
    }

    private void guardarJugador(Connection conn, Jugador jugador, int equipId) throws SQLException {
        String query = "INSERT INTO jugadors (id, nom, edat, rol, kills, assists, equip_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, jugador.getId());
        pstmt.setString(2, jugador.getNom());
        pstmt.setInt(3, jugador.getEdat());
        pstmt.setString(4, jugador.getRol());

        if (jugador instanceof Toplaner) {
            pstmt.setInt(5, ((Toplaner) jugador).getKills());
            pstmt.setNull(6, java.sql.Types.INTEGER);
        } else if (jugador instanceof ADC) {
            pstmt.setNull(5, java.sql.Types.INTEGER);
            pstmt.setInt(6, ((ADC) jugador).getAssists());
        }

        pstmt.setInt(7, equipId);
        pstmt.executeUpdate();
    }

    public List<Equip> obtenirTotsElsEquips() throws SQLException {
        List<Equip> equips = new ArrayList<>();
        try (Connection conn = connect()) {
            String query = "SELECT * FROM equips";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Equip equip = new Equip(rs.getInt("id"), rs.getString("nom"));
                equips.add(equip);
                carregarJugadors(equip, conn);
            }
        }
        return equips;
    }

    private void carregarJugadors(Equip equip, Connection conn) throws SQLException {
        String query = "SELECT * FROM jugadors WHERE equip_id = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, equip.getId());
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            String rol = rs.getString("rol");
            Jugador jugador;
            if (rol.equals("Top Laner")) {
                jugador = new Toplaner(rs.getInt("id"), rs.getString("nom"), rs.getInt("edat"), rs.getInt("kills"));
            } else if (rol.equals("ADC")) {
                jugador = new ADC(rs.getInt("id"), rs.getString("nom"), rs.getInt("edat"), rs.getInt("assists"));
            } else {
                continue;
            }
            equip.afegirJugador(jugador);
        }
    }
}

