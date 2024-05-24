package cat.boscdelacoma.sistemapersistent.model.business.entities;

import java.util.ArrayList;
import java.util.List;

public class Equip {
    private int id;
    private String nom;
    private List<Jugador> jugadors;

    public Equip(int id, String nom) {
        this.id = id;
        this.nom = nom;
        this.jugadors = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public List<Jugador> getJugadors() {
        return jugadors;
    }

    public void afegirJugador(Jugador jugador) {
        this.jugadors.add(jugador);
    }

    public void eliminarJugador(Jugador jugador) {
        this.jugadors.remove(jugador);
    }

    public void mostrarJugadors() {
        for (Jugador jugador : jugadors) {
            System.out.println(jugador.getNom() + " - " + jugador.getRol());
        }
    }
}
