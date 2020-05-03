package Marktplaats.dao;

import Marktplaats.domain.Gebruiker;
import Marktplaats.domain.Verkoper;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


public class GebruikerDao {

    @Inject
    EntityManager em;

    public void gebruikerToevoegen(Gebruiker gebruiker) {
        em.getTransaction().begin();
        em.persist(gebruiker);
        em.getTransaction().commit();
        em.detach(gebruiker);
    }

    public void verkoperToevoegen(Verkoper verkoper) {
        em.getTransaction().begin();
        em.persist(verkoper);
        em.getTransaction().commit();
        em.detach(verkoper);
    }

    //TODO: creeer ook voor verkoper
    public Gebruiker getGebruikerEmailEnWachtwoord(String email, String wachtwoord) {
        TypedQuery<Gebruiker> query = em.createQuery("SELECT g FROM Gebruiker g WHERE  g.email = :email AND g.wachtwoord = :wachtwoord", Gebruiker.class);
        query.setParameter("email", email);
        query.setParameter("wachtwoord", wachtwoord);
        return query.getSingleResult();
    }

    public Verkoper getVerkoperEmailEnWachtwoord(String email, String wachtwoord) {
        TypedQuery<Verkoper> query = em.createQuery("SELECT g FROM Verkoper g WHERE  g.email = :email AND g.wachtwoord = :wachtwoord", Verkoper.class);
        query.setParameter("email", email);
        query.setParameter("wachtwoord", wachtwoord);
        return query.getSingleResult();
    }

    public Gebruiker updateGebruiker(Gebruiker gebruiker) {
        em.getTransaction().begin();
        Gebruiker merged = em.merge(gebruiker);
        em.getTransaction().commit();
        return merged;
    }

    public Verkoper updateVerkoper(Verkoper verkoper) {
        em.getTransaction().begin();
        Verkoper merged = em.merge(verkoper);
        em.getTransaction().commit();
        return merged;
    }
}
