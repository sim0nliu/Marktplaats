package Marktplaats.service;

import Marktplaats.dao.GebruikerDao;
import Marktplaats.domain.Bezorgwijze;
import Marktplaats.domain.Gebruiker;
import Marktplaats.domain.Product;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.swing.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.StringJoiner;


public class GebruikerService {

    @Inject
    private EntityManager em;
    @Inject
    GebruikerDao gebruikerDao;

    private Gebruiker gebruiker;

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
//                start();
                break;
        }
    }

    private void maakDatabase() {
        gebruikerDao.gebruikerToevoegen(new Gebruiker("s", "ww", "Het kasteel 115, Apeldoorn",
                new Bezorgwijze("afhalenThuis", "afhalenMagazijn", "versturen", "versturenOnderRembours")));
        gebruikerDao.gebruikerToevoegen(new Gebruiker("b", "ww", "Ergens 1, Leusden",
                new Bezorgwijze("afhalenMagazijn")));
        gebruikerDao.gebruikerToevoegen(new Gebruiker("chantal@marktplaats.nl", "haarWachtwoord"));

        Gebruiker simon = gebruikerDao.getGebruikerEmailEnWachtwoord("s", "ww");
        simon.artikelToevoegen(new Product("Schoenen", "Adidas Ultraboost", "Heren sportschoen", new BigDecimal("119.90"), new Bezorgwijze("afhalenThuis")));
        simon.artikelToevoegen(new Product("Schoenen", "Nike Flyknit", "Heren sportschoen", new BigDecimal("98.97"), new Bezorgwijze("afhalenMagazijn")));
        simon.artikelToevoegen(new Product("Jassen", "Hugo Boss Jas", "Heren jas", new BigDecimal("279.30"), new Bezorgwijze("versturen")));
        gebruikerDao.update(simon);

        Gebruiker bram = gebruikerDao.getGebruikerEmailEnWachtwoord("b", "ww");
        bram.artikelToevoegen(new Product("Consoles", "PS4", "Gloednieuwe PS4", new BigDecimal("289.00"), new Bezorgwijze("versturenOnderRembours")));
        bram.artikelToevoegen(new Product("TV", "Samsung TV", "Zwarte LED TV - 60 inch", new BigDecimal("477"), new Bezorgwijze("afhalenThuis")));
        bram.artikelToevoegen(new Product("Kast", "Billy", "Zwarte Billy kast", new BigDecimal("39.95"), new Bezorgwijze("afhalenMagazijn")));
        gebruikerDao.update(bram);

        start();
    }

    //TODO: Registreren
    public void registreerAccount(){}

    public void logIn() {
        String email = JOptionPane.showInputDialog("Voer je e-mailadres in.");
        String wachtwoord = JOptionPane.showInputDialog("Voer je wachtwoord in.");

        System.out.println("Controleren of de invoer matcht met de database");
        if (gebruikerDao.getGebruikerEmailEnWachtwoord(email, wachtwoord) != null) {
            this.gebruiker = gebruikerDao.getGebruikerEmailEnWachtwoord(email, wachtwoord);
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
        //TODO: baseer categorie op mogelijheden van accountinvoer
        String categorie = "testcat";

        String artikelNaam = JOptionPane.showInputDialog("Product aanbieden\n" +
                "Artikelnaam:");
        String omschrijving = JOptionPane.showInputDialog("Product aanbieden\n" +
                "Omschrijving:");
        //TODO: Alleen getallen
        String prijs = JOptionPane.showInputDialog("Product aanbieden\n" +
                "Prijs:");
/*        String bijlagen = JOptionPane.showInputDialog("Product aanbieden\n" +
                "Bijlagen:");*/
//        String bezorgwijze = vraagCategorieProduct();
        String bezorgwijze = JOptionPane.showInputDialog("Product aanbieden\n" +
                "Bezorgwijze:");

        gebruiker.artikelToevoegen(new Product(categorie, artikelNaam, omschrijving, new BigDecimal(String.valueOf(prijs)), new Bezorgwijze(bezorgwijze)));
        gebruikerDao.update(gebruiker);
    }

    //TODO: Nakijken
    private String vraagCategorieProduct() {
        int optienummer = 1;
        StringJoiner opties = new StringJoiner(" | ", "| ", " |");
        List<Bezorgwijze> mogelijkeBezorgwijze = gebruiker.getBezorgwijzenVanGebruiker();
        for (Bezorgwijze b : mogelijkeBezorgwijze) {
            opties.add(optienummer + ". " + b.toString());
            optienummer++;
        }
        String categorie = JOptionPane.showInputDialog("Product aanbieden\n" +
                opties + "\n" +
                "Categorie:");
        switch (categorie) {
            case "1":
                return "afhalenThuis";
            case "2":
                return "afhalenMagazijn";
            case "3":
                return "verzenden";
            case "4":
                return "verzendenOnderRembours";
            default:
                vraagCategorieProduct();
                break;
        }
        return categorie;
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
