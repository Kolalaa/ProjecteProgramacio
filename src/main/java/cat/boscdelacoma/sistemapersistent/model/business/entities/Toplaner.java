package cat.boscdelacoma.sistemapersistent.model.business.entities;

public class Toplaner extends Jugador {
    public Toplaner(int id, String nom, int edat, int kills, int assists, int morts) {
        super(id, nom, edat, "Top Laner");
        setKills(kills);
        setAssists(assists);
        setMorts(morts);
    }

    @Override
    public void jugar() {
        System.out.println("El Toplaner esta lluitant");
    }
}
