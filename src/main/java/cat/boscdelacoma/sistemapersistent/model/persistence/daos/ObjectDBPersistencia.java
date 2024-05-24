/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.boscdelacoma.sistemapersistent.model.persistence.daos;

/**
 *
 * @author Sergi
 */
//import cat.boscdelacoma.sistemapersistent.model.jugadors.Equip;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import java.util.List;
//
//public class ObjectDBPersistencia {
//    private EntityManagerFactory emf;
//
//    public ObjectDBPersistencia(String user, String password) {
//        String url = "objectdb://localhost:6136/lol_equip.odb;user=" + user + ";password=" + password;
//        emf = Persistence.createEntityManagerFactory(url);
//    }
//
//    public void guardarEquip(Equip equip) {
//        EntityManager em = emf.createEntityManager();
//        em.getTransaction().begin();
//        em.persist(equip);
//        em.getTransaction().commit();
//        em.close();
//    }
//
//    public List<Equip> obtenirTotsElsEquips() {
//        EntityManager em = emf.createEntityManager();
//        List<Equip> equips = em.createQuery("SELECT e FROM Equip e", Equip.class).getResultList();
//        em.close();
//        return equips;
//    }
//}
