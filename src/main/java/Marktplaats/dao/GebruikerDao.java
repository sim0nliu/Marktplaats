package Marktplaats.dao;

import Marktplaats.domain.Gebruiker;

import javax.persistence.EntityManager;

public class GebruikerDao {
    private final EntityManager em;

    public GebruikerDao(EntityManager em) {
        this.em = em;
    }

    public void addGebruiker(Gebruiker g) {
        em.getTransaction().begin();
        em.persist(g);
        em.getTransaction().commit();
        em.detach(g);
    }

    public Gebruiker getGebruiker(int id) {
        return em.find(Gebruiker.class, id);
    }

    public Gebruiker update(Gebruiker p) {
        em.getTransaction().begin();
        Gebruiker merged = em.merge(p);
        em.getTransaction().commit();
        return merged;
    }
}
