/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.boscdelacoma.sistemapersistent.model.business.entities;



/**
 *
 * @author Sergi
 */
public class Support extends Jugador {
    public Support (int id, String nom, int edat) {
        super(id, nom, edat, "Support");
        
    }

    @Override
    public void jugar() {
        System.out.println("El support esta fent peel a l'ADC");
    }
    
    
}
