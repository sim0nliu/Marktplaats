package Marktplaats.dao;

import Marktplaats.domain.Bezorgwijze;
import Marktplaats.domain.Gebruiker;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;


public class GebruikerDao {

    @Inject
    EntityManager em;

    public void gebruikerToevoegen(Gebruiker gebruiker) {
        em.getTransaction().begin();
        em.persist(gebruiker);
        em.getTransaction().commit();
        em.detach(gebruiker);
    }

    public Gebruiker getGebruikerById(int id) {
        return em.find(Gebruiker.class, id);
    }

    public Gebruiker getGebruikerEmailEnWachtwoord(String email, String wachtwoord) {
        TypedQuery<Gebruiker> query = em.createQuery("SELECT g FROM Gebruiker g WHERE  g.email = :email AND g.wachtwoord = :wachtwoord", Gebruiker.class);
        query.setParameter("email", email);
        query.setParameter("wachtwoord", wachtwoord);
        return query.getSingleResult();
    }

    public Gebruiker update(Gebruiker gebruiker) {
        em.getTransaction().begin();
        Gebruiker merged = em.merge(gebruiker);
        em.getTransaction().commit();
        return merged;
    }
}
