package Marktplaats.service;

import Marktplaats.dao.ArtikelDao;
import Marktplaats.dao.CategorieDao;
import Marktplaats.dao.GebruikerDao;
import Marktplaats.domain.*;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.swing.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GebruikerService {
    @Inject
    protected EntityManager em;

    @Inject
    protected GebruikerDao gebruikerDao;
    protected Gebruiker gebruiker;
    protected Verkoper verkoper;

    @Inject
    protected ArtikelDao artikelDao;

    @Inject
    protected CategorieDao categorieDao;

    public void start() {
        maakDatabase();
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
            default:
                start();
        }
    }

    public void maakDatabase() {
        System.out.println("Database aan het maken");

        System.out.println("Verkoper (Simon) aan het maken");
        gebruikerDao.verkoperToevoegen(new Verkoper("s", "ww", "Het kasteel 115, Apeldoorn",
                Bezorgwijze.AfhalenThuis, Bezorgwijze.AfhalenMagazijn, Bezorgwijze.Versturen, Bezorgwijze.VersturenOnderRembours));
        gebruikerDao.verkoperToevoegen(new Verkoper("bram@marktplaats.nl", "zijnWachtwoord", "Ergens 1, Leusden",
                Bezorgwijze.Versturen, Bezorgwijze.VersturenOnderRembours));
        gebruikerDao.gebruikerToevoegen(new Gebruiker("chantal@marktplaats.nl", "haarWachtwoord"));

        System.out.println("Inloggen verkoper");
        Verkoper simon = gebruikerDao.getVerkoperEmailEnWachtwoord("s", "ww");
        System.out.println("Verkoop plaatsen");
        simon.verkoopArtikel(new Product(Arrays.asList("Kleding"), "Adidas Ultraboost", "Heren sportschoen", new BigDecimal("119.90"),
                Arrays.asList(Bezorgwijze.AfhalenThuis, Bezorgwijze.AfhalenMagazijn, Bezorgwijze.Versturen, Bezorgwijze.VersturenOnderRembours)));
        simon.verkoopArtikel(new Product(Arrays.asList("Kleding"), "Merk Sneakers", "Adidas sportschoen", new BigDecimal("119.90"),
                Arrays.asList(Bezorgwijze.AfhalenThuis, Bezorgwijze.AfhalenMagazijn, Bezorgwijze.Versturen, Bezorgwijze.VersturenOnderRembours)));
        simon.verkoopArtikel(new Product(Arrays.asList("Kleding"), "Nike Flyknit", "Heren sportschoen", new BigDecimal("98.97"),
                Arrays.asList(Bezorgwijze.AfhalenThuis, Bezorgwijze.AfhalenMagazijn)));
        simon.verkoopArtikel(new Dienst(Arrays.asList("Klussen"), "Tegels leggen", "Voortuin tegels leggen", new BigDecimal("500")));
        gebruikerDao.updateVerkoper(simon);

        Verkoper bram = gebruikerDao.getVerkoperEmailEnWachtwoord("bram@marktplaats.nl", "zijnWachtwoord");
        bram.verkoopArtikel(new Product(Arrays.asList("Apparatuur"), "PS4", "Gloednieuwe PS4", new BigDecimal("289.00"),
                Arrays.asList(Bezorgwijze.VersturenOnderRembours)));
        bram.verkoopArtikel(new Dienst(Arrays.asList("Klussen"), "Behangen", "Zeer kundig", new BigDecimal("500")));
        bram.verkoopArtikel(new Dienst(Arrays.asList("Verzorging"), "Knippen", "Alleen mannenkapsels", new BigDecimal("30")));
        gebruikerDao.updateVerkoper(bram);
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

    //TODO bijlagen toevoegen aanbieden
    private void productAanbieden() {
        System.out.println("Product aanbieden. Wachten op invoer.");

        List<String> mogelijkeCategorieen = getMogelijkeCategorieen();
        String artikelNaam = JOptionPane.showInputDialog("Product aanbieden\n" +
                "Artikelnaam:");
        String omschrijving = JOptionPane.showInputDialog("Product aanbieden\n" +
                "Omschrijving:");

        String prijs = voerPrijsIn();

        //TODO: optie om bijlagen toe te voegen
/*        String bijlagen = JOptionPane.showInputDialog("Product aanbieden\n" +
                "Bijlagen:");*/

        //TODO: Opknappen
        List<Bezorgwijze> gekozenBezorgwijzen = getGekozenBezorgwijzen();
        verkoper.verkoopArtikel(new Product(mogelijkeCategorieen, artikelNaam, omschrijving,
                new BigDecimal(prijs), gekozenBezorgwijzen));

        gebruikerDao.updateVerkoper(verkoper);
        System.out.println("Product toegevoegd");
    }

    private String voerPrijsIn() {
        boolean isGetal = false;
        String prijs = "";
        while (!isGetal) {
            prijs = JOptionPane.showInputDialog("Product aanbieden\n" +
                    "Prijs:");
            isGetal = checkOfDouble(prijs);
        }
        return prijs;
    }

    private boolean checkOfDouble(String prijs) {
        try {
            Double.parseDouble(prijs);
            return true;
        } catch (Exception e) {
            System.out.println("Invoer ongeldig. Probeer opnieuw.");
            return false;
        }
    }

    private List<Bezorgwijze> getGekozenBezorgwijzen() {
        List<Bezorgwijze> mogelijkeBezorgwijzen = verkoper.getMogelijkeBezorgwijzen();
        List<Bezorgwijze> gekozenBezorgwijzen = new ArrayList<>();
        for (Bezorgwijze mogelijkheid : mogelijkeBezorgwijzen) {
            String antwoord = JOptionPane.showInputDialog("Wil je gebruik maken van " + mogelijkheid + "?\n"
                    + "| 1. Ja | 2. Nee |");
            while (!(antwoord.equals("1") || (antwoord.equals("2")))) {
                antwoord = JOptionPane.showInputDialog("Wil je gebruik maken van " + mogelijkheid + "?\n"
                        + "| 1. Ja | 2. Nee |");
            }
            if (antwoord.equals("1")) {
                gekozenBezorgwijzen.add(mogelijkheid);
            }
        }
        return gekozenBezorgwijzen;
    }

    private List<String> getMogelijkeCategorieen() {
        List<String> mogelijkeCategorieen = categorieDao.vindAlleDistinctCategorieen();
        List<String> gekozenCategorieen = new ArrayList<>();
        JOptionPane.showConfirmDialog(null, "Geef aan onder welke categorie het artikel past.", "ArtikelKeuze", JOptionPane.DEFAULT_OPTION);
        for (String mogelijkheid : mogelijkeCategorieen) {
            String antwoord = JOptionPane.showInputDialog("Wil je gebruik maken van " + mogelijkheid + "?\n"
                    + "| 1. Ja | 2. Nee |");
            while (!(antwoord.equals("1") || (antwoord.equals("2")))) {
                antwoord = JOptionPane.showInputDialog("Wil je gebruik maken van " + mogelijkheid + "?\n"
                        + "| 1. Ja | 2. Nee |");
            }
            if (antwoord.equals("1")) {
                gekozenCategorieen.add(mogelijkheid);
            }
        }
        return gekozenCategorieen;
    }

    private void artikelZoeken() {
        String manierVanZoeken = "";
        while (!(manierVanZoeken.equals("1") || (manierVanZoeken.equals("2")))) {
            manierVanZoeken = JOptionPane.showInputDialog("Wil je een uitgebreide zoekopdracht uitvoeren?\n"
                    + "| 1. Ja | 2. Nee |");
        }
        if (manierVanZoeken.equals("1")) {
//            artikelUitgebreidZoeken();
        } else {
            String zoekterm = JOptionPane.showInputDialog("Waar wil je naar zoeken?");
            List<Artikel> gevondenArtikelen = artikelDao.zoekOpNaam(zoekterm);
            for (Artikel gevondenArtikel : gevondenArtikelen) {
                System.out.println(gevondenArtikel.getArtikelNaam());
            }
            homepage();
        }
    }

    private void homepage() {
        String keuze = JOptionPane.showInputDialog("Homepage\n" +
                "| 1. Contactpagina | 2. Artikel zoeken | 3. Artikel aanbieden | 4. Artikel terugtrekken |");

        switch (keuze) {
            case "1":
                break;
            case "2":
                artikelZoeken();
                break;
            case "3":
                artikelAanbieden();
                break;
            case "4":
                break;
        }
    }
}
