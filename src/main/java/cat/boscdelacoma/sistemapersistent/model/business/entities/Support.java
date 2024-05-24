package cat.boscdelacoma.sistemapersistent.model.business.entities;

public class Support extends Jugador {
    public Support(int id, String nom, int edat, int kills, int assists, int morts) {
        super(id, nom, edat, "Support");
        setKills(kills);
        setAssists(assists);
        setMorts(morts);
    }

    @Override
    public void jugar() {
        System.out.println("El support esta ajudant a l'equip.");
    }
}
