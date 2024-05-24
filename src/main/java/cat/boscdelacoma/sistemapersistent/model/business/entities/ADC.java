package cat.boscdelacoma.sistemapersistent.model.business.entities;

public class ADC extends Jugador {
    public ADC(int id, String nom, int edat, int kills, int assists, int morts) {
        super(id, nom, edat, "ADC");
        setKills(kills);
        setAssists(assists);
        setMorts(morts);
    }

    @Override
    public void jugar() {
        System.out.println("El ADC esta en la botlane");
    }
}
