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
public class Toplaner extends Jugador {
    

    public Toplaner(int id, String nom, int edat) {
        super(id, nom, edat, "Top Laner");
        
    }

    @Override
    public void jugar() {
        System.out.println("El Top Laner està jugant a la línia superior.");
    }
}