/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.boscdelacoma.sistemapersistent.model.business.entities;

import cat.boscdelacoma.sistemapersistent.model.business.entities.Jugador;

/**
 *
 * @author Sergi
 */
public class Jungla extends Jugador{
    int barons;
    public Jungla(int id, String nom, int edat,int barons) {
        super(id, nom, edat, "Top Laner");
        this.barons = barons;
        
    }

    public int getBarons() {
        return barons;
    }

    public void setBarons(int barons) {
        this.barons = barons;
    }

    @Override
    public void jugar() {
        System.out.println("El Jungla esta gankejant top");
    }
    
}
