package Marktplaats.service;

import Marktplaats.dao.ArtikelDao;
import Marktplaats.dao.CategorieDao;
import Marktplaats.dao.GebruikerDao;
import Marktplaats.domain.*;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.swing.*;
import java.math.BigDecimal;
import java.util.List;


public class GebruikerService {
    @Inject
    protected EntityManager em;

    @Inject
    protected GebruikerDao gebruikerDao;
    protected Gebruiker gebruiker;

    @Inject
    protected ArtikelDao artikelDao;
    protected Verkoper verkoper;

    @Inject
    protected CategorieDao categorieDao;

    public void start() {
        registerOrLogin();
    }

    public void registerOrLogin() {
        String keuze = JOptionPane.showInputDialog("Om gebruik te kunnen maken moet je ingelogd zijn.\n " +
                "| 1. Registreren | 2. Inloggen |");

        switch (keuze) {
            case "1":
                System.out.println("keuze was Registreren");
                break;
            case "2":
                logIn();
                break;
            //TODO: Remove when App is done
            case "3":
                maakDatabase();
                break;
            default:
                start();
        }
    }

    public void maakDatabase() {
        System.out.println("Database aan het maken");
        categorieDao.categorieToevoegen(new Categorie("Kleding"));
        categorieDao.categorieToevoegen(new Categorie("Apparatuur"));

        System.out.println("Verkoper (Simon) aan het maken");
        gebruikerDao.verkoperToevoegen(new Verkoper("s", "ww", "Het kasteel 115, Apeldoorn",
                Bezorgwijze.AfhalenThuis, Bezorgwijze.AfhalenMagazijn, Bezorgwijze.Versturen, Bezorgwijze.VersturenOnderRembours));
        gebruikerDao.verkoperToevoegen(new Verkoper("b", "ww", "Ergens 1, Leusden",
                Bezorgwijze.Versturen, Bezorgwijze.VersturenOnderRembours));
        gebruikerDao.gebruikerToevoegen(new Gebruiker("chantal@marktplaats.nl", "haarWachtwoord"));

        System.out.println("Inloggen verkoper");
        Verkoper simon = gebruikerDao.getVerkoperEmailEnWachtwoord("s", "ww");
        System.out.println("Verkoop plaatsen");
        simon.verkoopArtikel(new Product("Kleding", "Adidas Ultraboost", "Heren sportschoen", new BigDecimal("119.90"),
                Bezorgwijze.AfhalenThuis, Bezorgwijze.AfhalenMagazijn, Bezorgwijze.Versturen, Bezorgwijze.VersturenOnderRembours));
        simon.verkoopArtikel(new Product("Kleding", "Nike Flyknit", "Heren sportschoen", new BigDecimal("98.97"),
                Bezorgwijze.AfhalenThuis, Bezorgwijze.AfhalenMagazijn));
        simon.verkoopArtikel(new Dienst("Klussen", "Tegels leggen", "Voortuin tegels leggen", new BigDecimal("500")));
        gebruikerDao.updateVerkoper(simon);

        Verkoper bram = (Verkoper) gebruikerDao.getGebruikerEmailEnWachtwoord("b", "ww");
        bram.verkoopArtikel(new Product("Apparatuur", "PS4", "Gloednieuwe PS4", new BigDecimal("289.00"),
                Bezorgwijze.VersturenOnderRembours));
        bram.verkoopArtikel(new Dienst("Klussen", "Behangen", "Zeer kundig", new BigDecimal("500")));
        bram.verkoopArtikel(new Dienst("Verzorging", "Knippen", "Alleen mannenkapsels", new BigDecimal("30")));
        gebruikerDao.updateVerkoper(bram);

        //TODO: Remove when App is done
        logIn();
    }

    //TODO: Registreren
    public void registreerAccount() {
    }

    public void logIn() {
        System.out.println("Logging in");
        String email = JOptionPane.showInputDialog("Voer je e-mailadres in.");
        String wachtwoord = JOptionPane.showInputDialog("Voer je wachtwoord in.");

        System.out.println("Controleren of de invoer matcht met de database");
        if (gebruikerDao.getVerkoperEmailEnWachtwoord(email, wachtwoord) != null) {
            this.verkoper = gebruikerDao.getVerkoperEmailEnWachtwoord(email, wachtwoord);
            homepage();
        } else if (gebruikerDao.getGebruikerEmailEnWachtwoord(email, wachtwoord) != null) {
            this.gebruiker = gebruikerDao.getGebruikerEmailEnWachtwoord(email, wachtwoord);

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

    public void artikelAanbieden() {
        String typeArtikel = JOptionPane.showInputDialog("Wat wilt u toevoegen?\n" +
                "| 1. Dienst | 2. Product |");

        switch (typeArtikel) {
            case "1":
                dienstAanbieden();
                break;
            case "2":
                productAanbieden();
                break;
            default:
                System.out.println("Invoer is onjuist. Probeer opnieuw");
                artikelAanbieden();
        }
    }

    //TODO: dienstAanbieden afmaken
    private void dienstAanbieden() {

    }

    //TODO productAanbieden afmaken
    private void productAanbieden() {
        System.out.println("Product aanbieden. Wachten op invoer.");
        //TODO: baseer categorie op mogelijheden van systeem
        String categorie = "testcat";
        String artikelNaam = JOptionPane.showInputDialog("Product aanbieden\n" +
                "Artikelnaam:");
        String omschrijving = JOptionPane.showInputDialog("Product aanbieden\n" +
                "Omschrijving:");
        //TODO: Alleen getallen
        String prijs = JOptionPane.showInputDialog("Product aanbieden\n" +
                "Prijs:");
        //TODO: optie om bijlagen toe te voegen
/*        String bijlagen = JOptionPane.showInputDialog("Product aanbieden\n" +
                "Bijlagen:");*/

        //TODO: Opknappen
        List<Bezorgwijze> mogelijkeBezorgwijzen;
        mogelijkeBezorgwijzen = verkoper.getMogelijkeBezorgwijzen();
        Bezorgwijze bezorgwijze1 = null, bezorgwijze2 = null, bezorgwijze3 = null, bezorgwijze4 = null;
        String antwoord;
        for (int i = 0; i < mogelijkeBezorgwijzen.size(); i++) {
            switch (i) {
                case 0:
                    antwoord = JOptionPane.showInputDialog("Wil je gebruik maken van " + mogelijkeBezorgwijzen.get(i) + "?\n"
                            + "| 1. Ja | 2. Nee |");
                    while (!(antwoord.equals("1") || (antwoord.equals("2")))) {
                        antwoord = JOptionPane.showInputDialog("Wil je gebruik maken van " + mogelijkeBezorgwijzen.get(i) + "?\n"
                                + "| 1. Ja | 2. Nee |");
                    }
                    if (antwoord.equals("1")) {
                        bezorgwijze1 = mogelijkeBezorgwijzen.get(i);
                    }
                    break;
                case 1:
                    antwoord = JOptionPane.showInputDialog("Wil je gebruik maken van " + mogelijkeBezorgwijzen.get(i) + "?\n"
                            + "| 1. Ja | 2. Nee |");
                    while (!(antwoord.equals("1") || (antwoord.equals("2")))) {
                        antwoord = JOptionPane.showInputDialog("Wil je gebruik maken van " + mogelijkeBezorgwijzen.get(i) + "?\n"
                                + "| 1. Ja | 2. Nee |");
                    }
                    if (antwoord.equals("1")) {
                        bezorgwijze2 = mogelijkeBezorgwijzen.get(i);
                    }
                    break;
                case 2:
                    antwoord = JOptionPane.showInputDialog("Wil je gebruik maken van " + mogelijkeBezorgwijzen.get(i) + "?\n"
                            + "| 1. Ja | 2. Nee |");
                    while (!(antwoord.equals("1") || (antwoord.equals("2")))) {
                        antwoord = JOptionPane.showInputDialog("Wil je gebruik maken van " + mogelijkeBezorgwijzen.get(i) + "?\n"
                                + "| 1. Ja | 2. Nee |");
                    }
                    if (antwoord.equals("1")) {
                        bezorgwijze3 = mogelijkeBezorgwijzen.get(i);
                    }
                    break;
                case 3:
                    antwoord = JOptionPane.showInputDialog("Wil je gebruik maken van " + mogelijkeBezorgwijzen.get(i) + "?\n"
                            + "| 1. Ja | 2. Nee |");
                    while (!(antwoord.equals("1") || (antwoord.equals("2")))) {
                        antwoord = JOptionPane.showInputDialog("Wil je gebruik maken van " + mogelijkeBezorgwijzen.get(i) + "?\n"
                                + "| 1. Ja | 2. Nee |");
                    }
                    if (antwoord.equals("1")) {
                        bezorgwijze4 = mogelijkeBezorgwijzen.get(i);
                    }
                    break;
            }
        }

        verkoper.verkoopArtikel(new Product(categorie, artikelNaam, omschrijving,
                new BigDecimal(String.valueOf(prijs)), bezorgwijze1, bezorgwijze2, bezorgwijze3, bezorgwijze4));
        gebruikerDao.updateVerkoper(verkoper);
        System.out.println("Product toegevoegd");
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
                artikelAanbieden();
                break;
            case "4":
                break;
        }
    }
}
