package cat.boscdelacoma.sistemapersistent.model.persistence.daos;

import cat.boscdelacoma.sistemapersistent.model.business.entities.*;
import javax.persistence.*;
import java.util.List;

public class ObjectDBPersistencia {
    private static final String PERSISTENCE_UNIT_NAME = "lol_equip_db";
    private EntityManagerFactory emf;

    public ObjectDBPersistencia() {
        emf = Persistence.createEntityManagerFactory("$objectdb/db/lol_equip_db.odb");
    }
    
    public void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

    public void guardarEquip(Equip equip) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(equip);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public List<Equip> obtenirTotsElsEquips() {
        EntityManager em = emf.createEntityManager();
        List<Equip> equips = null;
        try {
            TypedQuery<Equip> query = em.createQuery("SELECT e FROM Equip e", Equip.class);
            equips = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return equips;
    }

    public void eliminarEquip(Equip equip) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Equip toRemove = em.find(Equip.class, equip.getId());
            if (toRemove != null) {
                em.remove(toRemove);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void guardarJugador(Jugador jugador) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(jugador);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public List<Jugador> obtenirTotsElsJugadors() {
        EntityManager em = emf.createEntityManager();
        List<Jugador> jugadors = null;
        try {
            TypedQuery<Jugador> query = em.createQuery("SELECT j FROM Jugador j", Jugador.class);
            jugadors = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return jugadors;
    }

    public void eliminarJugador(Jugador jugador) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Jugador toRemove = em.find(Jugador.class, jugador.getId());
            if (toRemove != null) {
                em.remove(toRemove);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
