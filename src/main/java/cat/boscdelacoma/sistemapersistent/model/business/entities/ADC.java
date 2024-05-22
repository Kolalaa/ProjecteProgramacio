/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.boscdelacoma.sistemapersistent.model.business.entities;

/**
 *
 * @author Sergi
 */
public class ADC extends Jugador {
    
    public ADC(int id, String nom, int edat) {
        super(id, nom, edat, "ADC");
        
    }

    @Override
    public void jugar() {
        System.out.println("L'ADC està jugant a la línia inferior.");
    }
}
