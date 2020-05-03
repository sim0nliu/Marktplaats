package Marktplaats.dao;

import Marktplaats.domain.Artikel;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class ArtikelDao {

    @Inject
    EntityManager em;

    public void artikelToevoegen(Artikel artikel) {
        em.getTransaction().begin();
        em.persist(artikel);
        em.getTransaction().commit();
        em.detach(artikel);
    }

    public Artikel update(Artikel artikel) {
        em.getTransaction().begin();
        Artikel merged = em.merge(artikel);
        em.getTransaction().commit();
        return merged;
    }
}