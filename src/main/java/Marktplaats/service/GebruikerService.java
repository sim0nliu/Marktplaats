package Marktplaats.service;

import Marktplaats.dao.GebruikerDao;
import Marktplaats.domain.Gebruiker;
import Marktplaats.domain.Product;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.swing.*;
import java.math.BigDecimal;


public class GebruikerService {

    @Inject
    private EntityManager em;
    @Inject
    GebruikerDao gebruikerDao;

    private Gebruiker user;

    public GebruikerService() {

    }

    public void start() {
        String keuze = JOptionPane.showInputDialog("Om gebruik te kunnen maken moet je ingelogd zijn.\n " +
                "| 1. Registreren | 2. Inloggen |");

        switch (keuze) {
            case "1":
                System.out.println("keuze was Registreren");
                break;
            case "2":
                logIn();
                break;
            case "3":
                maakDatabase();
            default:
                start();
                break;
        }
    }

    private void maakDatabase() {
        gebruikerDao.gebruikerToevoegen(new Gebruiker("simon", "ww", "Het kasteel 115, Apeldoorn",
                true, true, true, true));
        gebruikerDao.gebruikerToevoegen(new Gebruiker("bram@marktplaats.nl", "zijnWachtwoord", "Ergens 1, Leusden",
                false, true, false, false));
        gebruikerDao.gebruikerToevoegen(new Gebruiker("chantal@marktplaats.nl", "haarWachtwoord"));

        Gebruiker simon = gebruikerDao.getGebruikerById(1);
        Gebruiker bram = gebruikerDao.getGebruikerById(2);
        Gebruiker chantal = gebruikerDao.getGebruikerById(3);


        simon.artikelToevoegen(new Product("Schoenen", "Adidas Ultraboost", "Heren sportschoen", new BigDecimal("119.90")));
        simon.artikelToevoegen(new Product("Schoenen", "Nike Flyknit", "Heren sportschoen", new BigDecimal("98.97")));
        simon.artikelToevoegen(new Product("Jassen", "Hugo Boss Jas", "Heren jas", new BigDecimal("279.30")));
        gebruikerDao.update(simon);

        bram.artikelToevoegen(new Product("Consoles", "PS4", "Gloednieuwe PS4", new BigDecimal("289.00")));
        bram.artikelToevoegen(new Product("TV", "Samsung TV", "Zwarte LED TV - 60 inch", new BigDecimal("477")));
        bram.artikelToevoegen(new Product("Kast", "Billy", "Zwarte Billy kast", new BigDecimal("39.95")));
        gebruikerDao.update(bram);

        start();
    }

    public void logIn() {
        String email = JOptionPane.showInputDialog("Voer je e-mailadres in.");
        String wachtwoord = JOptionPane.showInputDialog("Voer je wachtwoord in.");
//        String email = "simon";
//        String wachtwoord = "ww";
        System.out.println("Controleren of de invoer matcht met de database");

        TypedQuery<Gebruiker> query = em.createQuery("SELECT g FROM Gebruiker g WHERE g.email = :email AND g.wachtwoord = :wachtwoord", Gebruiker.class);
        query.setParameter("email", email);
        query.setParameter("wachtwoord", wachtwoord);
        if (query.getResultList().size() >= 1) {
            this.user = query.getSingleResult();
            homepage();
        } else {
            String antwoord = JOptionPane.showInputDialog("Inloggen mislukt. Opnieuw proberen?\n" +
                    "| 1. Ja | 2. Nee |");
            if (antwoord.equals("1")) {
                logIn();
            } else {
                start();
            }
        }
    }

    private void homepage() {
        String keuze = JOptionPane.showInputDialog("Homepage\n" +
                "| 1. Contactpagina | 2. Artikel zoeken | 3. Artikel aanbieden | 4. Artikel terugtrekken |");

        switch (keuze) {
            case "1":
                break;
            case "2":
                break;
            case "3":
//                user.artikelToevoegen(new Artikel());

                break;
            case "4":
                break;
        }
    }
}
