package cat.boscdelacoma.sistemapersistent.model.business.entities;

public class Jungla extends Jugador {
    private int barons;

    public Jungla(int id, String nom, int edat, int kills, int assists, int morts, int barons) {
        super(id, nom, edat, "Jungla");
        setKills(kills);
        setAssists(assists);
        setMorts(morts);
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
        System.out.println("El jungla està fent objectius");
    }
}
