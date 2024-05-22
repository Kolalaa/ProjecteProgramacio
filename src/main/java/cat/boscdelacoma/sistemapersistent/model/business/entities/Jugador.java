/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.boscdelacoma.sistemapersistent.model.business.entities;

/**
 *
 * @author Sergi
 */
public abstract class Jugador {
    private int id;
    private int idEquip;
    private String nom;
    private int edat;
    private String rol;
    private int kills;
    private int assists;
    private int morts;

    public Jugador(int id, String nom, int edat, String rol) {
        this.id = id;
        this.nom = nom;
        this.edat = edat;
        this.rol = rol;
    }

    public abstract void jugar();


    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getEdat() {
        return edat;
    }

    public String getRol() {
        return rol;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setEdat(int edat) {
        this.edat = edat;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getKills() {
        return kills;
    }

    public int getAssists() {
        return assists;
    }

    public int getMorts() {
        return morts;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public void setMorts(int morts) {
        this.morts = morts;
    }
    
}
