package Marktplaats.service;

import Marktplaats.dao.GebruikerDao;
import Marktplaats.dao.VerkoperDao;
import Marktplaats.domain.Gebruiker;
import Marktplaats.domain.Product;
import Marktplaats.domain.Verkoper;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.*;
import java.math.BigDecimal;
import java.util.Scanner;

public class GebruikerService {
    private String email;
    private String wachtwoord;

    public void start() {
        EntityManager em = Persistence.createEntityManagerFactory("MySQL").createEntityManager();

        GebruikerDao gebruikerDao = new GebruikerDao(em);
        VerkoperDao verkoperDao = new VerkoperDao(em);

/*        verkoperDao.addGebruiker(new Verkoper(
                "simon",
                "ww",
                "Het kasteel 115, Apeldoorn",
                true, true, true, true
        ));

        verkoperDao.addGebruiker(new Verkoper(
                "bram@marktplaats.nl",
                "zijnWachtwoord",
                "Ergens 1, Leusden",
                false, true, false, false
        ));

        gebruikerDao.addGebruiker(new Gebruiker("chantal@marktplaats.nl", "haarWachtwoord"));

        Verkoper simon = (Verkoper) verkoperDao.getGebruiker(1);
        Verkoper bram = (Verkoper) verkoperDao.getGebruiker(2);
        Gebruiker chantal = gebruikerDao.getGebruiker(3);

        simon.addArtikel(new Product("Schoenen", "Adidas Ultraboost", "Heren sportschoen", new BigDecimal("119.90")));
        simon.addArtikel(new Product("Schoenen", "Nike Flyknit", "Heren sportschoen", new BigDecimal("98.97")));
        simon.addArtikel(new Product("Jassen", "Hugo Boss Jas", "Heren jas", new BigDecimal("279.30")));
        verkoperDao.update(simon);

        bram.addArtikel(new Product("Consoles", "PS4", "Gloednieuwe PS4", new BigDecimal("289.00")));
        bram.addArtikel(new Product("TV", "Samsung TV", "Zwarte LED TV - 60 inch", new BigDecimal("477")));
        bram.addArtikel(new Product("Kast", "Billy", "Zwarte Billy kast", new BigDecimal("39.95")));
        verkoperDao.update(bram);

        gebruikerDao.update(chantal);

        System.out.println(simon);
        System.out.println(bram);
        System.out.println(chantal);*/

        //Sluit verbinding
        em.close();
    }

    public void logIn(EntityManager em) {
        email = JOptionPane.showInputDialog("Voer je e-mailadres in.");
        wachtwoord = JOptionPane.showInputDialog("Voer je wachtwoord in.");
        String keuze = "";
        System.out.println("Controleren of de invoer matcht met de database");
        if (vindAccount(em, email, wachtwoord)) {
            keuze = JOptionPane.showInputDialog("Inloggen succesvol.\n" +
                    "| 1. Contactpagina | 2. Artikel zoeken | 3. Artikel aanbieden | 4. Artikel terugtrekken |");

        } else {
            System.out.println("Inloggen mislukt. Probeer het opnieuw");
            logIn(em);
        }
        switch (keuze) {
            case "1":
                break;
            case "2":
                break;
            case "3":
                artikelAanbieden();
                break;
            case "4":
                break;
        }
    }

    private boolean vindAccount(EntityManager em, String email, String wachtwoord) {
        TypedQuery<Gebruiker> query = em.createQuery("SELECT g FROM Gebruiker g WHERE g.email = :email AND g.wachtwoord = :wachtwoord", Gebruiker.class);
        query.setParameter("email", email);
        query.setParameter("wachtwoord", wachtwoord);
        System.out.println(query.getResultList().size());
        if (query.getResultList().size() >= 1) {
            return true;
        } else {
            System.out.println("Inloggen Mislukt. Probeer opnieuw.");
            logIn(em);
        }
        return false;
    }

    private void artikelAanbieden() {
//        user.addArtikel(new Product("Schoenen", "Adidas Ultraboost", "Heren sportschoen", new BigDecimal("119.90")));

    }
}
