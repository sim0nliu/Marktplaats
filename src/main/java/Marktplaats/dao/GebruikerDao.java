package Marktplaats.dao;

import Marktplaats.domain.Gebruiker;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


public class GebruikerDao {

    @Inject
    EntityManager em;

    public GebruikerDao() {
    }

    public void gebruikerToevoegen(Gebruiker g) {
        em.getTransaction().begin();
        em.persist(g);
        em.getTransaction().commit();
        em.detach(g);
    }

    public Gebruiker getGebruikerById(int id) {
        return em.find(Gebruiker.class, id);
    }

    public Gebruiker getGebruikerByEmail(String email) {
        TypedQuery<Gebruiker> query = em.createQuery("SELECT g FROM Gebruiker g WHERE  g.email = :email", Gebruiker.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    }

    public Gebruiker update(Gebruiker p) {
        em.getTransaction().begin();
        Gebruiker merged = em.merge(p);
        em.getTransaction().commit();
        return merged;
    }
}
