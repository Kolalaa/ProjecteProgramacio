package cat.boscdelacoma.sistemapersistent.model.persistence.daos;

import cat.boscdelacoma.sistemapersistent.model.business.entities.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLPersistencia {
    public static final String URL = "jdbc:mysql://localhost:3306/lol_equip_db";
    public static final String USER = "root";
    public static final String PASSWORD = "";

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void guardarEquip(Equip equip) throws SQLException {
        try (Connection conn = connect()) {
            String query = "INSERT INTO equips (id, nom) VALUES (?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, equip.getId());
                pstmt.setString(2, equip.getNom());
                pstmt.executeUpdate();
            }

            for (Jugador jugador : equip.getJugadors()) {
                guardarJugador(conn, jugador, equip.getId());
            }
        }
    }

    public void guardarJugador(Connection conn, Jugador jugador, int equipId) throws SQLException {
        String query = "INSERT INTO jugador (id, nom, edat, rol, kills, assists, morts, barons, id_equip) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, jugador.getId());
            pstmt.setString(2, jugador.getNom());
            pstmt.setInt(3, jugador.getEdat());
            pstmt.setString(4, jugador.getRol());
            pstmt.setInt(5, jugador.getKills());
            pstmt.setInt(6, jugador.getAssists());
            pstmt.setInt(7, jugador.getMorts());
            if (jugador instanceof Jungla) {
                pstmt.setInt(8, ((Jungla) jugador).getBarons());
            } else {
                pstmt.setNull(8, java.sql.Types.INTEGER);
            }
            pstmt.setInt(9, equipId);
            pstmt.executeUpdate();
        }
    }

    public void updateJugador(Connection conn, Jugador jugador) throws SQLException {
        String query = "UPDATE jugador SET nom = ?, edat = ?, rol = ?, kills = ?, assists = ?, morts = ?, barons = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, jugador.getNom());
            pstmt.setInt(2, jugador.getEdat());
            pstmt.setString(3, jugador.getRol());
            pstmt.setInt(4, jugador.getKills());
            pstmt.setInt(5, jugador.getAssists());
            pstmt.setInt(6, jugador.getMorts());
            if (jugador instanceof Jungla) {
                pstmt.setInt(7, ((Jungla) jugador).getBarons());
            } else {
                pstmt.setNull(7, java.sql.Types.INTEGER);
            }
            pstmt.setInt(8, jugador.getId());
            pstmt.executeUpdate();
        }
    }

    public Jugador obtenirJugadorPerId(int id) throws SQLException {
        String query = "SELECT * FROM jugador WHERE id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String rol = rs.getString("rol");
                    Jugador jugador;
                    switch (rol) {
                        case "Top Laner":
                            jugador = new Toplaner(rs.getInt("id"), rs.getString("nom"), rs.getInt("edat"), rs.getInt("kills"), rs.getInt("assists"), rs.getInt("morts"));
                            break;
                        case "ADC":
                            jugador = new ADC(rs.getInt("id"), rs.getString("nom"), rs.getInt("edat"), rs.getInt("kills"), rs.getInt("assists"), rs.getInt("morts"));
                            break;
                        case "Jungla":
                            jugador = new Jungla(rs.getInt("id"), rs.getString("nom"), rs.getInt("edat"), rs.getInt("kills"), rs.getInt("assists"), rs.getInt("morts"), rs.getInt("barons"));
                            break;
                        case "Support":
                            jugador = new Support(rs.getInt("id"), rs.getString("nom"), rs.getInt("edat"), rs.getInt("kills"), rs.getInt("assists"), rs.getInt("morts"));
                            break;
                        default:
                            throw new SQLException("Invalid role found in database");
                    }
                    return jugador;
                } else {
                    throw new SQLException("Player not found with ID: " + id);
                }
            }
        }
    }

    public List<Equip> obtenirTotsElsEquips() throws SQLException {
        List<Equip> equips = new ArrayList<>();
        try (Connection conn = connect()) {
            String query = "SELECT * FROM equips";
            try (PreparedStatement pstmt = conn.prepareStatement(query);
                 ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Equip equip = new Equip(rs.getInt("id"), rs.getString("nom"));
                    equips.add(equip);
                    carregarJugadors(equip, conn);
                }
            }
        }
        return equips;
    }

    private void carregarJugadors(Equip equip, Connection conn) throws SQLException {
        String query = "SELECT * FROM jugadors WHERE equip_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, equip.getId());
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String rol = rs.getString("rol");
                    Jugador jugador;
                    switch (rol) {
                        case "Top Laner":
                            jugador = new Toplaner(rs.getInt("id"), rs.getString("nom"), rs.getInt("edat"), rs.getInt("kills"), rs.getInt("assists"), rs.getInt("morts"));
                            break;
                        case "ADC":
                            jugador = new ADC(rs.getInt("id"), rs.getString("nom"), rs.getInt("edat"), rs.getInt("kills"), rs.getInt("assists"), rs.getInt("morts"));
                            break;
                        case "Jungla":
                            jugador = new Jungla(rs.getInt("id"), rs.getString("nom"), rs.getInt("edat"), rs.getInt("kills"), rs.getInt("assists"), rs.getInt("morts"), rs.getInt("barons"));
                            break;
                        case "Support":
                            jugador = new Support(rs.getInt("id"), rs.getString("nom"), rs.getInt("edat"), rs.getInt("kills"), rs.getInt("assists"), rs.getInt("morts"));
                            break;
                        default:
                            continue;
                    }
                    equip.afegirJugador(jugador);
                }
            }
        }
    }
}
