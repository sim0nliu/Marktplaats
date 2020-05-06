package Marktplaats.dao;

import Marktplaats.domain.Artikel;
import Marktplaats.domain.Categorie;
import Marktplaats.domain.Verkoper;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

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

    public List<Artikel> zoekOpNaam(String zoekterm) {
        zoekterm = "%" + zoekterm + "%";
        TypedQuery<Artikel> query = em.createQuery("SELECT a FROM Artikel a WHERE  a.artikelNaam LIKE :email", Artikel.class);
        query.setParameter("email", zoekterm);
        return query.getResultList();
    }
}