/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.boscdelacoma.sistemapersistent.model.business.entities;

/**
 *
 * @author Sergi
 */
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

    public void afegirJugador(Jugador jugador) {
        this.jugadors.add(jugador);
    }

    public void eliminarJugador(Jugador jugador) {
        this.jugadors.remove(jugador);
    }

    public List<Jugador> getJugadors() {
        return jugadors;
    }

    // Getters i setters...

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setJugadors(List<Jugador> jugadors) {
        this.jugadors = jugadors;
    }
    
}
